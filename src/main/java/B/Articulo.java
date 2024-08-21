package B;

import org.example.Funciones;

import javax.swing.*;
import java.util.ArrayList;

public class Articulo {
    private int codigo;
    private String denominacion;
    private double precio;
    private String unidadMedida;
    public Articulo(int codigo, String denominacion, double precio, String unidadMedida) {
        this.codigo = codigo;
        this.denominacion = denominacion;
        this.precio = precio;
        this.unidadMedida = unidadMedida;
    }

    public Articulo() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public static Articulo BuscarEnListaYDevolver(ArrayList<Articulo> LISTA, String TextoDeIngreso, String TextoEncontrada, String TextoNoEncontrado) { //Se seguira ejecutando mientras el texto ingresado se encuentra dentro de la lista

        boolean NoEncontro = true;
        Articulo articulo = new Articulo();
        do {
            for (int i = 0; i <= LISTA.size(); i++) {
                if (LISTA.get(i).codigo == Integer.parseInt(TextoDeIngreso)) {
                    JOptionPane.showMessageDialog(null,TextoEncontrada + TextoDeIngreso);
                    articulo = LISTA.get(i);
                    NoEncontro = false;
                    break;
                }
            }
            if (NoEncontro) {
                JOptionPane.showMessageDialog(null, TextoNoEncontrado + TextoDeIngreso);
                TextoDeIngreso = Funciones.InputDialogNoVacio("Ingrese otro código de artículo");
            }
        } while (NoEncontro);

        return articulo;
    }
}
