package B;

import java.util.ArrayList;

public class Cliente {
    private ArrayList<Factura> Factura = new ArrayList<Factura>();
    private int numero;
    private String razonSocial;
    private long cuit;

    public Cliente(ArrayList<B.Factura> factura, int numero, String razonSocial, long cuit) {
        Factura = factura;
        this.numero = numero;
        this.razonSocial = razonSocial;
        this.cuit = cuit;
    }

    public Cliente() {
    }

    public ArrayList<B.Factura> getFactura() {
        return Factura;
    }

    public void setFactura(ArrayList<B.Factura> factura) {
        Factura = factura;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public long getCuit() {
        return cuit;
    }

    public void setCuit(long cuit) {
        this.cuit = cuit;
    }
}
