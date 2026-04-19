package com.carrito.catalogo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Producto {
    private String ean13;
    private String nombre;
    private List<PrecioProducto> preciosHistoricos;

    // Constructor
    public Producto(String ean13, String nombre, List<PrecioProducto> preciosHistoricos) {
        this.ean13 = ean13;
        this.nombre = nombre;
        this.preciosHistoricos = preciosHistoricos; 
    }

    public String getEan13() { return ean13; }
    public String getNombre() { return nombre; }
    public String getNombreCorto() {
        if (this.nombre != null && this.nombre.length() > 10) {
            return this.nombre.substring(0, 10) + "...";
        }
        return this.nombre;
    }

    // Método extra para poder cargarle precios al producto desde el App.java
    public void agregarPrecioHistorico(PrecioProducto precio) {
        this.preciosHistoricos.add(precio);
    }

    // Busca el precio correspondiente a una fecha específica
    public double getPrecio(LocalDate fechaVigencia) {
        for (PrecioProducto precioProducto : preciosHistoricos) {
            if (precioProducto.getCumpleVigencia(fechaVigencia)) {
                return precioProducto.getPrecio();
            }
        }
        throw new NoSuchElementException("No existe un precio cargado para la fecha " + fechaVigencia);
    }
}