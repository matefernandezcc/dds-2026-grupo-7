package com.carrito.compras;

import com.carrito.pagos.Pago;
import com.carrito.ubicacion.Direccion;
import com.carrito.usuarios.Cliente;

import java.time.LocalDate;
import java.util.List;

public class Carrito {
    private List<Item> items;
    private LocalDate fechaCompra;
    private Cliente cliente;
    private Direccion direccionEnvio;
    private Direccion direccionCobro;
    private List<Pago> pagos;
    private Estado estado;

    public Carrito(List<Item> items, LocalDate fechaCompra, Cliente cliente, Direccion direccionEnvio, Direccion direccionCobro, List<Pago> pagos) {
        this.items = items;
        this.fechaCompra = fechaCompra;
        this.cliente = cliente;
        this.direccionEnvio = direccionEnvio;
        this.direccionCobro = direccionCobro;
        this.pagos = pagos;
        this.estado = Estado.EN_PROCESO;
    }

    public LocalDate getFechaCompra() {return fechaCompra;}
    public List<Pago> getPagos() {return pagos;}
    public void setEstado(Estado estado) {this.estado = estado;}

    public void cerrar() {
        this.setEstado(Estado.CERRADO);
    }

    public Double getMontoPagado() {
        return pagos.stream().mapToDouble(p -> p.getMonto()).sum();
    }

    public Double getMontoCarrito() {
        Double total = 0.0;
        for (Item item : items) {
            total += item.getPrecioOficial();
        }
        return total;
    }
}