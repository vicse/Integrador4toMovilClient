package com.ore.vicse.integrador4toclient.models;

public class Pedido {


    private Integer id_cliente;
    private Integer id_producto;
    private String estado;
    private String fecha;
    private Integer id_pedido;

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(Integer id_producto) {
        this.id_producto = id_producto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Integer id_pedido) {
        this.id_pedido = id_pedido;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id_cliente=" + id_cliente +
                ", id_producto=" + id_producto +
                ", estado='" + estado + '\'' +
                ", fecha='" + fecha + '\'' +
                ", id_pedido=" + id_pedido +
                '}';
    }
}
