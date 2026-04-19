package com.carrito.catalogo;

import java.util.Date;

public class PrecioProducto {
    private Date fechaInicioVigencia;
    private Date fechaFinVigencia;
    private int precio;


    public Date getFechaInicioVigencia(){return fechaInicioVigencia;}
    public Date getFechaFinVigencia(){return fechaFinVigencia;}

    public boolean getCumpleVigencia(Date fecha){
        return fecha.after(fechaInicioVigencia) && fecha.before(fechaFinVigencia);
    }

    public int getPrecio() {return precio;}
}
