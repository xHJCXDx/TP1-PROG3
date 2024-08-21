package B;

import org.example.Funciones;

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

    public Factura() {
    }

    public ArrayList<DetalleFactura> getDetalleFactura() {
        return detalleFactura;
    }

    public void setDetalleFactura(ArrayList<DetalleFactura> detalleFactura) {
        this.detalleFactura = detalleFactura;
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
}
