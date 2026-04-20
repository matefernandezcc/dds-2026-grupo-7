package com.carrito.ubicacion;

public class Ciudad {
    private String nombre;
    private Provincia provincia;

    public Ciudad(String nombre){
        this.nombre = nombre;
        this.provincia = null;
    }

    public String getNombre(){return nombre;}
    public Provincia getProvincia(){return provincia;}
    public void setProvincia(Provincia provincia){ this.provincia = provincia; }
}
