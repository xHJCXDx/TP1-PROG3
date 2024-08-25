package B;

import java.util.ArrayList;

public class Cliente {
    private int numero;
    private String razonSocial;
    private long cuit;

    public Cliente(int numero, String razonSocial, long cuit) {

        this.numero = numero;
        this.razonSocial = razonSocial;
        this.cuit = cuit;
    }

    public Cliente() {
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
