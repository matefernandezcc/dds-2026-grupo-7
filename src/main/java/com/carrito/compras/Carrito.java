package com.carrito.compras;

import com.carrito.pagos.Pago;
import com.carrito.ubicacion.Direccion;
import com.carrito.usuarios.Cliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<Item> items;
    private LocalDate fechaCompra;
    private Cliente cliente;
    private Direccion direccionEnvio;
    private Direccion direccionCobro;
    private List<Pago> pagos;
    private Estado estado;

    public Carrito(List<Item> items, Cliente cliente) {
        this.items = items;
        this.fechaCompra = LocalDate.now();
        this.cliente = cliente;
        this.direccionEnvio = null;
        this.direccionCobro = null;
        this.pagos = new ArrayList<>();
        this.estado = Estado.EN_PROCESO;
    }
 
    public LocalDate getFechaCompra(){ return fechaCompra; }
    public List<Pago> getPagos(){ return pagos; }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
    
    public void setDireccionCobro(Direccion direccionCobro){
        this.direccionCobro = direccionCobro;
    }

    public void setDireccionEnvio(Direccion direccionEnvio){
        this.direccionEnvio = direccionEnvio;
    }

    public void addPago(Pago pago){
        pagos.add(pago);
    }
    
    public Double getMontoPagado() {
        return pagos.stream()
                    .mapToDouble(Pago::getMonto)
                    .sum();
    }

    public Double getMontoCarrito() {
        return items.stream()
                    .mapToDouble(Item::getPrecioOficial)
                    .sum();
    }

    public Double getMontoDeuda(){
        return getMontoCarrito() - getMontoPagado(); // Total - Pagado = Adeudado
    }

    public void setEstado(Estado estado){ this.estado = estado; }

    public void cerrar() {
        this.setEstado(Estado.CERRADO);
    }

    public void addItem(Item item) {
        this.items.add(item);
    }
}