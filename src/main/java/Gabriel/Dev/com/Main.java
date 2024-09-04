package Gabriel.Dev.com;

import com.itextpdf.text.DocumentException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Crear factura
        Factura factura = new Factura("Consorcio ABC", "Victor Gabriel Castillo Scipioni", "12345678", "AGOSTO-2024");
        factura.addGasto(new Gasto("Multa por caca de perro", "INDIVIDUAL", 50.0));
        factura.addGasto(new Gasto("Expensas", "EXPENSA", 200.0));
        factura.addGasto(new Gasto("Servicio de limpieza", "INDIVIDUAL", 100.0));

        // Generar PDF
        String pdfPath = "factura.pdf";
        PdfGenerator pdfGenerator = new PdfGenerator();
        try {
            pdfGenerator.createPdf(factura, pdfPath);
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }

        // Enviar Email
        System.out.print("Ingrese su correo electrónico de Gmail: ");
        String email = scanner.nextLine();
        String password = "uykp thdb gqya hdfk"; //TODO aca iria la app password (leer Readme.md)
        System.out.print("Ingrese el correo electrónico del destinatario: ");
        String destinatario = scanner.nextLine();
        String subject = "Factura: " + factura.getNombrePropietario() + ", " + factura.getCicloCobro();
        String body = "Adjunto encontrará la factura correspondiente al ciclo " + factura.getCicloCobro();

        EmailSender emailSender = new EmailSender(email, password);
        try {
            emailSender.sendEmail(destinatario, subject, body, pdfPath);
            System.out.println("Factura enviada con éxito.");
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}
