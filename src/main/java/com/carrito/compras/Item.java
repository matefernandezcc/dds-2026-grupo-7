package com.carrito.compras;

import com.carrito.catalogo.Producto;

public class Item {
    private Carrito carrito;
    private Producto producto;
    private Integer cantidad;
    private Double precioUnitario;

    // Constructor
    public Item(Carrito carrito, Producto producto, Integer cantidad, Double precioUnitario) {
        this.carrito = carrito;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public Carrito getCarrito() { return carrito; }
    public Producto getProducto() { return producto; }
    public Integer getCantidad() { return cantidad; }
    public Double getPrecioUnitario() { return precioUnitario; }

    public Double getPrecio() {
        return this.cantidad * this.precioUnitario;
    }
    
    public Double getPrecioOficial() {       
        // Asumo que el "precio oficial" del UML también se multiplica por cantidad
        Double precioUnitarioOficial = this.producto.getPrecio(this.carrito.getFechaCompra());
        return precioUnitarioOficial * this.cantidad; 
    }
    
    public Double getDescuento() {
        return this.getPrecioOficial() - this.getPrecio();
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }
}