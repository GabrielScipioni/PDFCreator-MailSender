package Gabriel.Dev.com;

public class Gasto {
    private String descripcion;
    private String tipo;
    private double monto;

    public Gasto(String descripcion, String tipo, double monto) {
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public double getMonto() {
        return monto;
    }
}
