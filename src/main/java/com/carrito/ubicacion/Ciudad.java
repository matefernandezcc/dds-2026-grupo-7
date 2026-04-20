package com.carrito.ubicacion;

public class Ciudad {
    private String nombre;
    private Provincia provincia;

    public Ciudad(String nombre, Provincia provincia){
        this.nombre = nombre;
        this.provincia = provincia;
    }

    public String getNombre(){return nombre;}
    public Provincia getProvincia(){return provincia;}
}
