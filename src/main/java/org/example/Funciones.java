package org.example;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class Funciones {

    //Fecha
    public static String formatDate(Date date) {
        String sdf = "20"+(date.getYear()-100)+"-"+(date.getMonth()+1)+"-"+date.getDate();
        return sdf;
    }

    // Función que convierte un String en Date y se repite hasta que se ingrese una fecha válida
    public static Date convertirStringADate() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setLenient(false); // Para validar correctamente fechas no válidas como 32/13/2023
        Date fecha = null;

        while (fecha == null) {
            String fechaStr = InputDialogNoVacio("Ingresa una fecha (dd/MM/yyyy): ");
            try {
                fecha = formato.parse(fechaStr);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null,"Fecha inválida. Por favor, ingresa una fecha en el formato dd/MM/yyyy.");
            }
        }

        return fecha;
    }

    public static String InputDialogNoVacio(String texto) { //Para que no incerten un espacio en blanco en los datos
        String Input = "";
        do {
            Input = JOptionPane.showInputDialog(texto);
            if (Input == null || Input.equals("")) {
                JOptionPane.showMessageDialog(null, "DEBE INGRESAR UN DATO");
                Input = "";
            }
        } while (Input.equals("")); //V
        return Input;
    } //Nos devuelve obligatoriamente un Sring, No acepta que el usuario no cargue datos

    public static int LimitacionNumericaInt(String texto, String dato, int max, int min) { //Limita los parametros a ingresar
        int Numero = 0;
        String Input = "";
        do {
            Input = JOptionPane.showInputDialog(texto);
            Numero = Integer.parseInt(Input);
            if (Numero > max) {
                JOptionPane.showMessageDialog(null, "EL DATO NO CUMPLE CON EL LIMITE MAXIMO DE " + dato);
                Input = "";
            } else if (Numero < min) {
                JOptionPane.showMessageDialog(null, "EL DATO NO CUMPLE CON EL LIMITE MINIMO DE " + dato);
                Input = "";
            }
        } while (Numero > max || Numero < min); //V

        return Numero;
    } //Toma los valores del extremo

    public static double LimitacionNumericaDouble(String texto, String dato, double max, double min) { //Limita los parametros a ingresar
        double Numero = 0;
        String Input = "";
        do {
            Input = InputDialogNoVacio(texto); //ES NECESARIO IMPORTAR LA FUNCION ESTA
            Numero = Integer.parseInt(Input);
            if (Numero > max) {
                JOptionPane.showMessageDialog(null, "EL DATO NO CUMPLE CON EL LIMITE MAXIMO DE " + dato);
                Input = "";
            } else if (Numero < min) {
                JOptionPane.showMessageDialog(null, "EL DATO NO CUMPLE CON EL LIMITE MINIMO DE " + dato);
                Input = "";
            }
        } while (Numero > max || Numero < min); //V

        return Numero;
    } //Toma los valores del extremo

    public static int BusquedaDeElementoArray(String[] Arreglo, String buscar) { //Devuelve la posicion en la que se encuentra el elemento, En caso de no encontrarse devuelve "-1"

        int busqueda = 0;

        //Varibles
        boolean seEncontro = false;

        // recorremos la lista, revisando cada elemento de la misma, para ver
        // si es el alumno a.
        for (int i = 0; i < Arreglo.length; i++) {
            // comparamos el alumno de la posición actual con el alumno buscado: a
            if (Arreglo[i].compareTo(buscar) == 0) {
                // encontramos el alumno buscado
                seEncontro = true;
                busqueda = i;
                JOptionPane.showMessageDialog(null, "Se encontro: [" + buscar + "]");
                break;
            }
            // si nunca se cumple L[i] == a, entonces la variable que indica si se
            // encontró o no el alumno: seEncontró, quedará valiendo falso.
        }
        if (!seEncontro) {
            busqueda = -1;
            JOptionPane.showMessageDialog(null, "No se encontro: " + buscar);
        }

        return busqueda;
    }

    //Matrices
    public static String[] CopiarColumnaDeMatriz(String[][] MATRIZ, int ColumnaACopiar){
        String[] COLUMNA = new String[MATRIZ[0].length];

        // Copiar los valores de la columna especificada al array
        for (int i = 0; i < MATRIZ[0].length; i++) {
            COLUMNA[i] = MATRIZ[ColumnaACopiar][i];
        }

        return COLUMNA;
    }

    public static String[][] ReorColumnas(String[][] arreglo) {
        String[][] arregloInvertir = new String[arreglo[0].length][arreglo.length];

        //x = i | y = j
        for (int j = 0; j < arreglo[0].length; j++) {   //Columnas
            for (int i = 0; i < arreglo.length; i++) {    //Filas
                arregloInvertir[j][i] = arreglo[i][j];
            }
        }
        return arregloInvertir;
    }

    public static int OpcionesMenu(String[] Botones, String mensaje, String titulo) {
        int seleccion = JOptionPane.showOptionDialog(null, mensaje, titulo, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, Botones, Botones[0]);

        return seleccion;
    }

    public static boolean CuadroDialogoVoF(String texto, String titulo) { //Nos deja responder SI: true // NO/cerrar: false
        boolean respuesta = false;

        int x = JOptionPane.showConfirmDialog(null, texto, titulo, JOptionPane.YES_NO_OPTION); //SI = 0 || NO = 1 || CERRAR = -1
        if (x == 0) {
            respuesta = true;
        } if (x == 1) {
            respuesta = false;
        } if (x == -1){
            System.exit(0);
        }

        return respuesta;
    }
}
