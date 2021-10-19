package domain;

public class Venta {
    private Integer idVenta;
    private Auto auto;
    private Cliente cliente;

    public Venta() {
    }

    public Venta(Integer idVenta, Auto auto, Cliente cliente) {
        this.idVenta = idVenta;
        this.auto = auto;
        this.cliente = cliente;
    }

    public Venta(Integer ventaSeleccionada) {
        this.idVenta = ventaSeleccionada;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "idVenta=" + idVenta +
                ", auto=" + auto +
                ", cliente=" + cliente +
                '}';
    }
}
