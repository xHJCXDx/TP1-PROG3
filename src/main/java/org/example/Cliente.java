package org.example;

public class Cliente {
    private String razonSocial;
    private long cuitCliente;

    public Cliente(String razonSocial, long cuitCliente) {
        this.razonSocial = razonSocial;
        this.cuitCliente = cuitCliente;
    }

    public Cliente() {
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public long getCuitCliente() {
        return cuitCliente;
    }

    public void setCuitCliente(long cuitCliente) {
        this.cuitCliente = cuitCliente;
    }
}
