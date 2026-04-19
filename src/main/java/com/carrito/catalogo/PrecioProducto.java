package com.carrito.catalogo;

import java.time.LocalDate;

public class PrecioProducto {
    private LocalDate fechaInicioVigencia;
    private LocalDate fechaFinVigencia;
    private Double precio;

    // Constructor
    public PrecioProducto(LocalDate fechaInicioVigencia, LocalDate fechaFinVigencia, double precio) {
        this.fechaInicioVigencia = fechaInicioVigencia;
        this.fechaFinVigencia = fechaFinVigencia;
        this.precio = precio;
    }

    public LocalDate getFechaInicioVigencia() { return fechaInicioVigencia; }
    public LocalDate getFechaFinVigencia() { return fechaFinVigencia; }
    public Double getPrecio() { return precio; }

    // Lógica de validación de fechas
    public boolean getCumpleVigencia(LocalDate fecha) {
        // Devuelve true si la fecha no es antes de la fecha de inicio y no es después de la fecha de fin
        return !fecha.isBefore(fechaInicioVigencia) && !fecha.isAfter(fechaFinVigencia);
    }

    @Override
    public String toString() {
        return this.fechaInicioVigencia + " - " + this.fechaFinVigencia + " : " + String.valueOf(this.precio);
    }
}
