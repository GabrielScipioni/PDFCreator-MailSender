package Gabriel.Dev.com;

import java.util.ArrayList;
import java.util.List;

public class Factura {
    private String nombreConsorcio;
    private String nombrePropietario;
    private String dniPropietario;
    private String cicloCobro;
    private List<Gasto> gastos;

    public Factura(String nombreConsorcio, String nombrePropietario, String dniPropietario, String cicloCobro) {
        this.nombreConsorcio = nombreConsorcio;
        this.nombrePropietario = nombrePropietario;
        this.dniPropietario = dniPropietario;
        this.cicloCobro = cicloCobro;
        this.gastos = new ArrayList<>();
    }

    public void addGasto(Gasto gasto) {
        this.gastos.add(gasto);
    }

    public String getNombreConsorcio() {
        return nombreConsorcio;
    }

    public String getNombrePropietario() {
        return nombrePropietario;
    }

    public String getDniPropietario() {
        return dniPropietario;
    }

    public String getCicloCobro() {
        return cicloCobro;
    }

    public List<Gasto> getGastos() {
        return gastos;
    }

    public double getTotal() {
        return gastos.stream().mapToDouble(Gasto::getMonto).sum();
    }
}
