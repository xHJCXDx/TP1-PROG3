package org.example;

import javax.swing.*;

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
            opciones = Funciones.OpcionesMenu(Botones,"Selecione el tipo de pago","Tipo de pago");
            switch (opciones){
                case 0: lector = "C"; break;
                case 1: lector = "TC"; break;
                case 2: lector = "TD"; break;
                default: lector = "Error"; break;
            }
        }while(lector.compareTo("Error") == 0);

        return lector;
    }

    public static double calcularRecargo(Factura factura){
        double total = 0;
        switch (factura.getTipoPago()){
            case "C":
                factura.setRecargo(0);
                break;

            case "TC":
                factura.setRecargo(total * 0.10);
                break;

            case "TD":
                factura.setRecargo(total * 0.05);
                break;
            default:
                factura.setRecargo(0);
                JOptionPane.showMessageDialog(null,"ERROR DE TIPO DE PAGO");
                break;
        }
        return total;
    }

    public static void ImprimirFactura(Factura factura,String[] cantidades, double[] subtotales){
        // Encabezado de la factura
        System.out.printf("%-10s%-20s\n", "Cliente: ", factura.getCliente().getRazonSocial());
        System.out.printf("%-10s%-20s\n", "Fecha", factura.getFecha());
        System.out.printf("%-10s%-20s\n", "Numero:", factura.getNroFactura());
        System.out.printf("%-10s%-20s\n", "Tipo Pago", factura.getTipoPago());

        System.out.println(); // Línea en blanco

        // Encabezado de la tabla de ítems
        System.out.printf("%-10s%-20s%-10s%-10s%-10s\n", "Código ", "Denominación", "Precio", "Cantidad", "Subtotal");

        // Items de la tabla
        String[][] Items = factura.getItemsFactura();
        for(int i = 0; i < Items[0].length; i++){
            System.out.printf("%-10s%-20s%-10s%-10s%-10s\n", Items[0][i] ,Items[1][i], Items[2][i], cantidades[i],subtotales[i]);
        }
        System.out.println(); // Línea en blanco

        // Totales
        System.out.printf("%-40s%-10s\n", "Total Ítems: ", factura.getMontoTotalItems());
        System.out.printf("%-40s%-10s\n", "Recargo: ", factura.getRecargo());
        System.out.printf("%-40s%-10s\n", "Total Final: ", factura.getMontoFinal());

    }

}
