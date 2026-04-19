package com.carrito.catalogo;

import java.util.List;
import java.util.Date;
import java.util.NoSuchElementException;


public class Producto {
    private String ean13;
    private String nombre;
    private List<PrecioProducto> preciosHistoricos;

    public String getean13() {return ean13;}
    public String getNombre() {return nombre;}
    // public String getNombreCorto()
    public int getPrecio(Date fecha){
        for (PrecioProducto precioProducto : preciosHistoricos) {
            if (precioProducto.getCumpleVigencia(fecha)){
                return precioProducto.getPrecio();
            }
        }
        throw new NoSuchElementException("No existe precio para la fecha " + fecha);
    }

}
