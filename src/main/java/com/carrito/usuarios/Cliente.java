package com.carrito.usuarios;

import java.util.List;
import java.util.ArrayList;
import com.carrito.ubicacion.Direccion;
import com.carrito.compras.Carrito;
import com.carrito.pagos.Tarjeta;
import com.carrito.pagos.Pago;

public class Cliente {
    private String nombre;
    private String apellido;
    private String email;
    private List<Direccion> direcciones;
    private List<Carrito> carritos;
    private List<Tarjeta> tarjetas;
    private boolean esPreferencial;

    public Cliente(String nombre, String apellido, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.direcciones = new ArrayList<>();
        this.carritos = new ArrayList<>();
        this.tarjetas = new ArrayList<>();
        this.esPreferencial = false;
    }

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

    public Double getMontoDeuda() {
        return carritos.stream()
                .mapToDouble(Carrito::getMontoDeuda)
                .sum();
    }

    public boolean estaHabilitado() {
        return this.getMontoDeuda() <= 1000.0;
    }

    public boolean esPreferencial() {
        return esPreferencial;
    }

    public void addDireccion(Direccion direccion) {
        direcciones.add(direccion);
    }

    public void addCarrito(Carrito carrito) {
        carritos.add(carrito);
    }

    public void addTarjeta(Tarjeta tarjeta) {
        tarjetas.add(tarjeta);
    }
}
