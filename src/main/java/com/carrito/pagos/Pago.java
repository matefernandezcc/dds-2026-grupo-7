package com.carrito.pagos;

import com.carrito.compras.Carrito;

public class Pago {
    private Carrito  carrito;
    private Tarjeta tarjeta;
    private int decimal;

    public boolean verficarTarjeta()
    {
        return tarjeta.getMontoDisponible()>0;
    }
    public Carrito getCarrito()
    {
        return carrito;
    }
    public Tarjeta getTarjeta()
    {
        return tarjeta;
    }
    public int getDecimal()
    {
        return decimal;
    }
    public Pago (Carrito carrito, Tarjeta tarjeta,int decimal)
    {
        this.carrito=carrito;
        this.tarjeta=tarjeta;
        this.decimal= decimal;
    }
}

