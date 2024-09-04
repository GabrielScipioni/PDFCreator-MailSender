package Gabriel.Dev.com;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfGenerator {

    private static final String FACTURAS_DIR = "Facturas";

    public void createPdf(Factura factura, String fileName) throws IOException, DocumentException {
        // Asegúrate de que la carpeta "Facturas" exista
        File dir = new File(FACTURAS_DIR);
        if (!dir.exists()) {
            dir.mkdir();
        }

        // Construye la ruta completa para el archivo PDF
        String filePath = FACTURAS_DIR + File.separator + fileName;

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        document.add(new Paragraph(factura.getNombreConsorcio()));
        document.add(new Paragraph("Nombre: " + factura.getNombrePropietario()));
        document.add(new Paragraph("DNI: " + factura.getDniPropietario()));
        document.add(new Paragraph("Ciclo: " + factura.getCicloCobro()));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(3);
        table.addCell("Descripción");
        table.addCell("Tipo");
        table.addCell("Monto");

        for (Gasto gasto : factura.getGastos()) {
            table.addCell(gasto.getDescripcion());
            table.addCell(gasto.getTipo());
            table.addCell(String.format("$%.2f", gasto.getMonto()));
        }

        document.add(table);
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Total: $" + String.format("%.2f", factura.getTotal())));

        document.close();
    }
}
