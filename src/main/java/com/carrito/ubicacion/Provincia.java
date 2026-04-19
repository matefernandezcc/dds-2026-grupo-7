package com.carrito.ubicacion;

public class Provincia {
    private String nombre;
    private Pais pais;

    public Provincia(String nombre){
        this.nombre = nombre;
    }

    public String getNombre(){return nombre;}
    public Pais getPais(){return pais;}
}
