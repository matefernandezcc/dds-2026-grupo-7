package com.carrito.compras;

import com.carrito.catalogo.Producto;

public class Item {
    private Carrito carrito;
    private Producto producto;
    private int cantidad;
    private Double precioUnitario;

    // Constructor
    public Item(Carrito carrito, Producto producto, int cantidad, double precioUnitario) {
        this.carrito = carrito;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public Carrito getCarrito() { return carrito; }
    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }

    public double getPrecio() {
        return this.cantidad * this.precioUnitario;
    }
    
    public double getPrecioOficial() {       
        // Asumo que el "precio oficial" del UML también se multiplica por cantidad
        double precioUnitarioOficial = this.producto.getPrecio(this.carrito.getFechaCompra());
        return precioUnitarioOficial * this.cantidad; 
    }
    
    public double getDescuento() {
        return this.getPrecioOficial() - this.getPrecio();
    }
}