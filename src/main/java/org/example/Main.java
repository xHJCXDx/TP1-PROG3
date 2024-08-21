package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Factura factura = new Factura();
        Cliente cliente = new Cliente();
        //Solicitar datos

        factura.setFecha(Funciones.InputDialogNoVacio("Fecha"));
        factura.setNroFactura(Long.parseLong(Funciones.InputDialogNoVacio("Numero de factura")));
        cliente.setRazonSocial(Funciones.InputDialogNoVacio("Razon Social"));
        cliente.setCuitCliente(Long.parseLong(Funciones.InputDialogNoVacio("Cuit Cliente")));
        factura.setTipoPago(factura.TipodefacturaMetodo());
        factura.setCliente(cliente);

        //Cantidad de articulos a facturar
        int cantidadArticulos = Funciones.LimitacionNumericaInt("Cantidad de articulos","ARTICULOS",999999,0);

        //Matriz
        String[][] MATRIZ = new String[4][cantidadArticulos];
        CalculoFactura calculoFactura = new CalculoFactura();

        String[][] MATRIZFactura = Funciones.ReordenamosColumnas(calculoFactura.articulos);
        String[] CANTIDAD = new String[cantidadArticulos];

        //[CODIGO DE ITEM - DENOMINACION - PRECIO UNITARIO - CANTIDAD - SUBTOTAL]

        //Solicito el ingreso de articulos
        JOptionPane.showMessageDialog(null,"Artículos a Facturar");
        int posicion = 0;
        int contador = 0;
        double cantidad = 0;
        do{
            posicion = Funciones.BusquedaDeElementoArray(Funciones.CopiarColumnaDeMatriz(MATRIZFactura,0),Funciones.InputDialogNoVacio("Ingrese el código del artículo"));
            if (posicion != -1){
                for(int i = 0; i <= 3;i++){
                    MATRIZ[i][contador] = MATRIZFactura[i][posicion];
                }
                //Cantidad a facturar
                if (MATRIZ[3][contador].equals("U")){
                    cantidad = Funciones.LimitacionNumericaDouble("Cantidad en [Unidades]:",MATRIZ[1][contador].toUpperCase(),999999,0);
                }else{
                    cantidad = Funciones.LimitacionNumericaDouble("Cantidad en [KG]:",MATRIZ[1][contador].toUpperCase(),999999,0);
                }
                CANTIDAD[contador] = String.valueOf(cantidad);
                contador++;
            }
        }while(posicion == -1 || contador < cantidadArticulos);

        factura.setItemsFactura(MATRIZ);

        //SUBTOTAL
        double[] SUBTOTAL = new double[cantidadArticulos];
        double total = 0;
        for (int i = 0; i < cantidadArticulos; i++){
            double precioD = Double.parseDouble(MATRIZ[2][i]);
            SUBTOTAL[i] += precioD * Double.parseDouble(CANTIDAD[i]);
            total = total+SUBTOTAL[i];
        }
        //Monto total
        factura.setMontoTotalItems(total);

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
        double Totalfinal = total + factura.getRecargo();
        factura.setMontoFinal(Totalfinal);

        //Imprimir la factura
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
            System.out.printf("%-10s%-20s%-10s%-10s%-10s\n", Items[0][i] ,Items[1][i], Items[2][i], CANTIDAD[i],SUBTOTAL[i]);
        }
        System.out.println(); // Línea en blanco

        // Totales
        System.out.printf("%-40s%-10s\n", "Total Ítems: ", factura.getMontoTotalItems());
        System.out.printf("%-40s%-10s\n", "Recargo: ", factura.getRecargo());
        System.out.printf("%-40s%-10s\n", "Total Final: ", factura.getMontoFinal());

    }
}