package com.carrito.ubicacion;

public class Ciudad {
    private String nombre;
    private Provincia provincia;

    public Ciudad(String nombre){
        this.nombre = nombre;
    }

    public String getNombre(){return nombre;}
    public Provincia getProvincia(){return provincia;}
}
