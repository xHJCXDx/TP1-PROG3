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

        String[][] matrizFactura = Funciones.ReorColumnas(calculoFactura.articulos);
        String[] cantidades = new String[cantidadArticulos];

        //[CODIGO DE ITEM - DENOMINACION - PRECIO UNITARIO - CANTIDAD - SUBTOTAL]

        //Solicito el ingreso de articulos
        JOptionPane.showMessageDialog(null,"Artículos a Facturar");
        int posicion = 0;
        int contador = 0;
        double cantidad = 0;
        do{
            posicion = Funciones.BusquedaDeElementoArray(Funciones.CopiarColumnaDeMatriz(matrizFactura,0),Funciones.InputDialogNoVacio("Ingrese el código del artículo"));
            if (posicion != -1){
                for(int i = 0; i <= 3;i++){
                    MATRIZ[i][contador] = matrizFactura[i][posicion];
                }
                //Cantidad a facturar
                if (MATRIZ[3][contador].equals("U")){
                    cantidad = Funciones.LimitacionNumericaDouble("Cantidad en [Unidades]:",MATRIZ[1][contador].toUpperCase(),999999,0);
                }else{
                    cantidad = Funciones.LimitacionNumericaDouble("Cantidad en [KG]:",MATRIZ[1][contador].toUpperCase(),999999,0);
                }
                cantidades[contador] = String.valueOf(cantidad);
                contador++;
            }
        }while(posicion == -1 || contador < cantidadArticulos);

        factura.setItemsFactura(MATRIZ);

        //SUBTOTAL
        double[] subtotales = new double[cantidadArticulos];
        double total = 0;
        for (int i = 0; i < cantidadArticulos; i++){
            double precioD = Double.parseDouble(MATRIZ[2][i]);
            subtotales[i] += precioD * Double.parseDouble(cantidades[i]);
            total = total+subtotales[i];
        }
        //Monto total
        factura.setMontoTotalItems(total);

        //Opciones de recargo tipo de pago
        factura.setRecargo(Factura.calcularRecargo(factura));

        //Monto total a final:
        double Totalfinal = total + factura.getRecargo();
        factura.setMontoFinal(Totalfinal);

        //Imprimir la factura
        Factura.ImprimirFactura(factura,cantidades,subtotales);

    }
}