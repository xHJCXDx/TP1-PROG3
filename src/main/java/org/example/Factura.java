package org.example;

public class Factura {
    private String fecha;
    private long nroFactura;
    private String tipoPago;
    private double montoTotalItems;
    private double recargo;
    private double montoFinal;
    private String [][] itemsFactura;
    Cliente cliente = new Cliente();

    public Factura(String fecha, long nroFactura, String tipoPago, double montoTotalItems, double recargo, double montoFinal, String[][] itemsFactura) {
        this.fecha = fecha;
        this.nroFactura = nroFactura;
        this.tipoPago = tipoPago;
        this.montoTotalItems = montoTotalItems;
        this.recargo = recargo;
        this.montoFinal = montoFinal;
        this.itemsFactura = itemsFactura;
    }

    public Factura() {
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public long getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(long nroFactura) {
        this.nroFactura = nroFactura;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public double getMontoTotalItems() {
        return montoTotalItems;
    }

    public void setMontoTotalItems(double montoTotalItems) {
        this.montoTotalItems = montoTotalItems;
    }

    public double getRecargo() {
        return recargo;
    }

    public void setRecargo(double recargo) {
        this.recargo = recargo;
    }

    public double getMontoFinal() {
        return montoFinal;
    }

    public void setMontoFinal(double montoFinal) {
        this.montoFinal = montoFinal;
    }

    public String[][] getItemsFactura() {
        return itemsFactura;
    }

    public void setItemsFactura(String[][] itemsFactura) {
        this.itemsFactura = itemsFactura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    //Metodos

    public String TipodefacturaMetodo(){
        String lector = "";

        String[] Botones = {"C","TC","TD"};

        int opciones = 0;
        do {
            opciones = Funciones.OpcionesMenu(Botones,"Selecione el tipo de facura","Tipo de factura");
            switch (opciones){
                case 0: lector = "C"; break;
                case 1: lector = "TC"; break;
                case 2: lector = "TD"; break;
                default: lector = "Error"; break;
            }
        }while(lector.compareTo("Error") == 0);

        return lector;
    }

}
