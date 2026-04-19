package com.carrito.usuarios;

import java.util.List;
import com.carrito.compras.Carrito;

public class Cliente {
    private String nombre;
    private String apellido;
    private String email;
    private List<Direccion> direcciones;
    private List<Carrito> carritos;
    private List<Tarjeta> tarjetas;
    private Boolean esPreferencial;

    public Cliente(String nombre, String apellido, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.direcciones = new ArrayList<>();
        this.carritos = new ArrayList<>();
        this.tarjetas = new ArrayList<>();
        this.esPreferencial = false;
    }

    /* ////////////////// Getters ////////////////// */
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public Direccion getDireccion(Integer idDireccion) {
        return direcciones.get(idDireccion);
    }

    public Carrito getCarrito(Integer idCarrito) {
        return carritos.get(idCarrito);
    }

    public Tarjeta getTarjeta(Integer idTarjeta) {
        return tarjetas.get(idTarjeta);
    }

    /* ////////////////// Methods ////////////////// */
    public Double getMontoDeuda() {
        return carritos.stream()
                .flatMap(t -> t.getPagos().stream()) // Esto aplana la lista de List<Pago> por cada Tarjeta
                .mapToDouble(p -> p.getMonto()) // a cada Pago de cada Tarjeta lo voy haciendo el map
                .sum();
    }

    public Boolean estaHabilitado() {
        return true;
    }

    public Boolean esPreferencial() {
        return esPreferencial;
    }

    public void addDireccion(Direccion direccion) {
        direcciones.add(direccion);
    }

    public void addCarrito(Carrito carrito) {
        carritos.add(carrito);
    }
}
