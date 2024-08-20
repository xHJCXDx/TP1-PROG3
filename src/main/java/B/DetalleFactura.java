package B;

public class DetalleFactura {
    private Articulo articulo;
    private int cantidad;
    private double subtotal;

    public DetalleFactura(Articulo articulo, int cantidad, double subtotal) {
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public DetalleFactura() {
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
