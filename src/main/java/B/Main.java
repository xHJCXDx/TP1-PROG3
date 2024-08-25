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
        factura.setNumero(Integer.parseInt(Funciones.InputDialogNoVacio("Numero de Factura")));
        cliente.setNumero(Integer.parseInt(Funciones.InputDialogNoVacio("Numero de Cliente")));
        cliente.setRazonSocial(Funciones.InputDialogNoVacio("Razon Social"));
        cliente.setCuit(Long.parseLong(Funciones.InputDialogNoVacio("Cuit Cliente")));
        factura.setTipoPago(factura.TipodefacturaMetodo());
        factura.setLetra(Funciones.InputDialogNoVacio("Tipo de factura"));
        factura.setCliente(cliente);

        //Articulos
        ArrayList<Articulo> listaArticulos = new ArrayList<Articulo>();
        listaArticulos.add(new Articulo(100,"Azucar", 20, "U"));
        listaArticulos.add(new Articulo(101,"Leche" , 30 , "U"));
        listaArticulos.add(new Articulo(102, "Aceite", 50, "U"));
        listaArticulos.add(new Articulo(103, "Sal", 45, "U"));
        listaArticulos.add(new Articulo(104, "Fideos", 15, "U"));
        listaArticulos.add(new Articulo(105, "Arroz", 28, "U"));
        listaArticulos.add(new Articulo(106, "Galletas", 26, "U"));
        listaArticulos.add(new Articulo(107, "Carne Molida", 220, "Kg"));
        listaArticulos.add(new Articulo(108, "Shampoo", 42, "U"));
        listaArticulos.add(new Articulo(109, "Queso Mantecoso", 178, "Kg"));
        listaArticulos.add(new Articulo(110, "Jamon Cocido", 320, "Kg"));
        listaArticulos.add(new Articulo(111, "Naranjas", 80, "Kg"));

        //[CODIGO DE ITEM - DENOMINACION - PRECIO UNITARIO - CANTIDAD - SUBTOTAL]

        //Solicito el ingreso de articulos
        JOptionPane.showMessageDialog(null,"Artículos a Facturar");

        int contador = 0;
        boolean cargar;

        do{
            Factura.IngresoDeArticulos(factura, listaArticulos);
            contador++;
            cargar = Funciones.CuadroDialogoVoF("Cargar otro articulo?","Articulos");
        }while(cargar);

        //SUBTOTAL
        double total = Factura.CalculoSubtotal(factura,contador);

        //Monto total
        factura.setTotalItems(total);

        //Opciones de recargo tipo de pago
        factura.setRecargo(Factura.CalculoDeRecargo(factura.getTipoPago(),total));

        //Monto total a final:
        factura.setTotalFinal(factura.getTotalItems() + factura.getRecargo());

        //Imprpimo factura
        Factura.ImprimirFactura(factura);
    }
}
