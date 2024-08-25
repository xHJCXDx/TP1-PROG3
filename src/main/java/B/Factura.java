package B;

import org.example.Funciones;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;

public class Factura {
    private ArrayList<DetalleFactura> detalleFactura = new ArrayList<>();
    private String letra;
    private int numero;
    private double recargo;
    private String tipoPago;
    private double totalItems;
    private double totalFinal;
    private Date fecha;
    private Cliente cliente;

    public Factura(ArrayList<DetalleFactura> detalleFactura, String letra, int numero, double recargo, String tipoPago, double totalItems, double totalFinal, Date fecha) {
        this.detalleFactura = detalleFactura;
        this.letra = letra;
        this.numero = numero;
        this.recargo = recargo;
        this.tipoPago = tipoPago;
        this.totalItems = totalItems;
        this.totalFinal = totalFinal;
        this.fecha = fecha;
    }

    public Factura(ArrayList<DetalleFactura> detalleFactura, String letra, int numero, double recargo, String tipoPago, double totalItems, double totalFinal, Date fecha, Cliente cliente) {
        this.detalleFactura = detalleFactura;
        this.letra = letra;
        this.numero = numero;
        this.recargo = recargo;
        this.tipoPago = tipoPago;
        this.totalItems = totalItems;
        this.totalFinal = totalFinal;
        this.fecha = fecha;
        this.cliente = cliente;
    }

    public Factura() {
    }

    public ArrayList<DetalleFactura> getDetalleFactura() {
        return detalleFactura;
    }

    public void setDetalleFactura(ArrayList<DetalleFactura> detalleFactura) {
        this.detalleFactura = detalleFactura;
    }

    public void agregarDetalleFactura(DetalleFactura detalle) {
        if(detalleFactura == null) {
            detalleFactura = new ArrayList<DetalleFactura>();
        }
        this.detalleFactura.add(detalle);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getRecargo() {
        return recargo;
    }

    public void setRecargo(double recargo) {
        this.recargo = recargo;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public double getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(double totalItems) {
        this.totalItems = totalItems;
    }

    public double getTotalFinal() {
        return totalFinal;
    }

    public void setTotalFinal(double totalFinal) {
        this.totalFinal = totalFinal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

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

    public static double CalculoDeRecargo(String tipoDePago, double total){
        double recargo = 0;
        switch (tipoDePago){
            case "C":
                recargo = 0;
                break;

            case "TC":
                recargo = total * 0.10;
                break;

            case "TD":
                recargo = total * 0.05;
                break;
            default:
                recargo = 0;
                JOptionPane.showMessageDialog(null,"ERROR DE TIPO DE PAGO");
                break;
        }
        return recargo;
    }

    public static double CalculoSubtotal(Factura factura, int contador){
        double total = 0;
        for (int i = 0; i < contador; i++){
            total = total+factura.getDetalleFactura().get(i).getSubtotal();
        }
        return total;
    }

    public static void ImprimirFactura(Factura factura){
        //Imprimir la factura
        // Encabezado de la factura
        System.out.printf("%-10s%-20s\n", "Cliente: ", factura.getCliente().getRazonSocial());
        System.out.printf("%-10s%-20s\n", "Fecha", Funciones.formatDate(factura.getFecha()));
        System.out.printf("%-10s%-20s\n", "Numero:", factura.getNumero());
        System.out.printf("%-10s%-20s\n", "Tipo Pago", factura.getTipoPago());

        System.out.println(); // Línea en blanco

        // Encabezado de la tabla de ítems
        System.out.printf("%-10s%-20s%-10s%-10s%-10s\n", "Código ", "Denominación", "Precio", "Cantidad", "Subtotal");

        // Items de la tabla
        for(int i = 0; i < factura.getDetalleFactura().size(); i++){
            System.out.printf("%-10s%-20s%-10s%-10s%-10s\n", factura.getDetalleFactura().get(i).getArticulo().getCodigo(),
                    factura.getDetalleFactura().get(i).getArticulo().getDenominacion(),
                    factura.getDetalleFactura().get(i).getArticulo().getPrecio(),
                    factura.getDetalleFactura().get(i).getCantidad(),
                    factura.getDetalleFactura().get(i).getSubtotal());
        }
        System.out.println(); // Línea en blanco

        // Totales
        System.out.printf("%-40s%-10s\n", "Total Ítems: ", factura.getTotalItems());
        System.out.printf("%-40s%-10s\n", "Recargo: ", factura.getRecargo());
        System.out.printf("%-40s%-10s\n", "Total Final: ", factura.getTotalFinal());
    }

    public static Factura IngresoDeArticulos(Factura factura, ArrayList<Articulo> listaArticulos) {
        String codigo = "";
        //Detalle factura
        DetalleFactura detalleFactura = new DetalleFactura();
        codigo = Funciones.InputDialogNoVacio("Ingrese el código del artículo");
        detalleFactura.setArticulo(Articulo.BuscarEnListaYDevolver(listaArticulos, codigo,"Se encontro ","No se encontro "));

        //Cantidad a facturar
        if (detalleFactura.getArticulo().getUnidadMedida().equals("U")){
            detalleFactura.setCantidad(Funciones.LimitacionNumericaInt("Cantidad en [Unidades]:",detalleFactura.getArticulo().getDenominacion(),999999,0));
        }else{
            detalleFactura.setCantidad(Funciones.LimitacionNumericaInt("Cantidad en [KG]:",detalleFactura.getArticulo().getDenominacion(),999999,0));
        }
        detalleFactura.setSubtotal(detalleFactura.getArticulo().getPrecio() * detalleFactura.getCantidad());
        factura.agregarDetalleFactura(detalleFactura);
        return factura;
    }
}