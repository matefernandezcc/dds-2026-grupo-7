package com.carrito.pagos;

import com.carrito.compras.Carrito;

public class Pago {
    private Carrito  carrito;
    private Tarjeta tarjeta;
    private Double monto;

    public boolean verficarTarjeta(){return tarjeta.getMontoDisponible()>0;}
    public Carrito getCarrito(){return carrito;}
    public Tarjeta getTarjeta(){return tarjeta;}
    public Double getMonto(){return monto;}

    public Pago (Carrito carrito, Tarjeta tarjeta,Double monto){
        this.carrito=carrito;
        this.tarjeta=tarjeta;
        this.monto= monto;
    }
}

