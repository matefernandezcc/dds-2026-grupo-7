package com.carrito.pagos;

public class Tarjeta {
    private String nombre;
    private MarcaTarjeta marca;
    private Integer montoDisponible;
    private String ultimos4digitos;
    
    public String getNombre(){ return nombre; }
    public MarcaTarjeta getMarcaTarjeta(){ return marca; }
    public Integer getMontoDisponible(){ return montoDisponible; }
    public void setMontoDisponible(Integer monto){ this.montoDisponible = monto; }
    public String getUltimos4digitos(){ return ultimos4digitos; }

    public Tarjeta (String nombre, MarcaTarjeta marca, String ultimos4digitos){
        this.nombre=nombre;
        this.marca=marca;
        this.ultimos4digitos= ultimos4digitos;
    }
}
