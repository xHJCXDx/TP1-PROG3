package B;

import org.example.Funciones;

import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Factura factura = new Factura();
        Cliente cliente = new Cliente();
        //Solicitar datos

        factura.setFecha(Funciones.convertirStringADate());
        factura.setNumero(Funciones.LimitacionNumericaInt("Numero de Factura","Numero",999999,1));
        cliente.setNumero(Funciones.LimitacionNumericaInt("Numero de Cliente","Numero",999999,1));
        cliente.setRazonSocial(Funciones.InputDialogNoVacio("Razon Social"));
        cliente.setCuit(Long.parseLong(Funciones.InputDialogNoVacio("Cuit Cliente")));
        factura.setTipoPago(factura.TipodefacturaMetodo());
        factura.setLetra(Funciones.InputDialogNoVacio("Tipo de factura"));

        //Cantidad de articulos a facturar
        int cantidadArticulos = Funciones.LimitacionNumericaInt("Cantidad de articulos","ARTICULOS",999999,0);

        //Articulos
        ArrayList<Articulo> ARTICULOS = new ArrayList<Articulo>();
        Articulo articulo1 = new Articulo(100,"Azucar", 20, "U");
        Articulo articulo2 = new Articulo(101,"Leche" , 30 , "U");
        Articulo articulo3 = new Articulo(102, "Aceite", 50, "U");
        Articulo articulo4 = new Articulo(103, "Sal", 45, "U");
        Articulo articulo5 = new Articulo(104, "Fideos", 15, "U");
        Articulo articulo6 = new Articulo(105, "Arroz", 28, "U");
        Articulo articulo7 = new Articulo(106, "Galletas", 26, "U");
        Articulo articulo8 = new Articulo(107, "Carne Molida", 220, "Kg");
        Articulo articulo9 = new Articulo(108, "Shampoo", 42, "U");
        Articulo articulo10 = new Articulo(109, "Queso Mantecoso", 178, "Kg");
        Articulo articulo11 = new Articulo(110, "Jamon Cocido", 320, "Kg");
        Articulo articulo12 = new Articulo(111, "Naranjas", 80, "Kg");
        ARTICULOS.add(articulo1);
        ARTICULOS.add(articulo2);
        ARTICULOS.add(articulo3);
        ARTICULOS.add(articulo4);
        ARTICULOS.add(articulo5);
        ARTICULOS.add(articulo6);
        ARTICULOS.add(articulo7);
        ARTICULOS.add(articulo8);
        ARTICULOS.add(articulo9);
        ARTICULOS.add(articulo10);
        ARTICULOS.add(articulo11);

        //[CODIGO DE ITEM - DENOMINACION - PRECIO UNITARIO - CANTIDAD - SUBTOTAL]

        //Solicito el ingreso de articulos
        JOptionPane.showMessageDialog(null,"Artículos a Facturar");
        String codigo = "";
        int contador = 0;
        double cantidad = 0;
        ArrayList<DetalleFactura> DETELLEDEFACTURA = new ArrayList<>();
        do{
            //Detalle factura
            DetalleFactura detalleFactura = new DetalleFactura();
            codigo = Funciones.InputDialogNoVacio("Ingrese el código del artículo");
            detalleFactura.setArticulo(Articulo.BuscarEnListaYDevolver(ARTICULOS,codigo,"Se encontro ","No se encontro "));

            //Cantidad a facturar
            if (detalleFactura.getArticulo().getUnidadMedida().equals("U")){
                detalleFactura.setCantidad(Funciones.LimitacionNumericaInt("Cantidad en [Unidades]:",detalleFactura.getArticulo().getDenominacion(),999999,0));
            }else{
                detalleFactura.setCantidad(Funciones.LimitacionNumericaInt("Cantidad en [KG]:",detalleFactura.getArticulo().getDenominacion(),999999,0));
            }
            detalleFactura.setSubtotal(detalleFactura.getArticulo().getPrecio() * detalleFactura.getCantidad());
            DETELLEDEFACTURA.add(detalleFactura);
            contador++;
        }while(contador < cantidadArticulos);
        //Agregamos a factura
        factura.setDetalleFactura(DETELLEDEFACTURA);

        //SUBTOTAL
        double total = 0;
        for (int i = 0; i < cantidadArticulos; i++){
            total = total+factura.getDetalleFactura().get(i).getSubtotal();
        }

        //Monto total
        factura.setTotalItems(total);

        //Opciones de recargo tipo de pago
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

        //Monto total a final:
        factura.setTotalFinal(factura.getTotalItems() + factura.getRecargo());

        //Agrego a cliente
        ArrayList<Factura> FACTURAS = new ArrayList<>();
        FACTURAS.add(factura);
        cliente.setFactura(FACTURAS);

        //Imprimir la factura
        // Encabezado de la factura
        System.out.printf("%-10s%-20s\n", "Cliente: ", cliente.getRazonSocial());
        System.out.printf("%-10s%-20s\n", "Fecha", Funciones.formatDate(cliente.getFactura().get(0).getFecha()));
        System.out.printf("%-10s%-20s\n", "Numero:", cliente.getFactura().get(0).getNumero());
        System.out.printf("%-10s%-20s\n", "Tipo Pago", cliente.getFactura().get(0).getTipoPago());

        System.out.println(); // Línea en blanco

        // Encabezado de la tabla de ítems
        System.out.printf("%-10s%-20s%-10s%-10s%-10s\n", "Código ", "Denominación", "Precio", "Cantidad", "Subtotal");

        // Items de la tabla
        for(int i = 0; i < cliente.getFactura().get(0).getDetalleFactura().size(); i++){
            System.out.printf("%-10s%-20s%-10s%-10s%-10s\n", cliente.getFactura().get(0).getDetalleFactura().get(i).getArticulo().getCodigo(),
                    cliente.getFactura().get(0).getDetalleFactura().get(i).getArticulo().getDenominacion(),
                    cliente.getFactura().get(0).getDetalleFactura().get(i).getArticulo().getPrecio(),
                    cliente.getFactura().get(0).getDetalleFactura().get(i).getArticulo().getUnidadMedida(),
                    cliente.getFactura().get(0).getDetalleFactura().get(i).getSubtotal());
        }
        System.out.println(); // Línea en blanco

        // Totales
        System.out.printf("%-40s%-10s\n", "Total Ítems: ", cliente.getFactura().get(0).getTotalItems());
        System.out.printf("%-40s%-10s\n", "Recargo: ", cliente.getFactura().get(0).getRecargo());
        System.out.printf("%-40s%-10s\n", "Total Final: ", cliente.getFactura().get(0).getTotalFinal());

    }
}
