package com.ore.vicse.integrador4toclient.models;

public abstract class Producto {


    private Integer id_almacen;
    private Integer id_proveedor;
    private Boolean estado;
    private String detalles;
    private String imagen;
    private String precio;
    private String nombre;
    private Integer id_producto;

    public int getId_almacen() {
        return id_almacen;
    }

    public void setId_almacen(int id_almacen) {
        this.id_almacen = id_almacen;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id_almacen=" + id_almacen +
                ", id_proveedor=" + id_proveedor +
                ", estado=" + estado +
                ", detalles='" + detalles + '\'' +
                ", imagen='" + imagen + '\'' +
                ", precio='" + precio + '\'' +
                ", nombre='" + nombre + '\'' +
                ", id_producto=" + id_producto +
                '}';
    }

}
