package com.carrito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.carrito.catalogo.PrecioProducto;
import com.carrito.catalogo.Producto;

/**
 * CONSIGNA:
 * NO habra procesos desde el lado del usuario, no habra interfaz de usuario,
 * sólo las clases del diagrama de clases y la implementación de métodos.
 * Todos los atributos deben ser privados (todo acceso a atributos será a través de métodos)
 */

public class App
{
    public static void main( String[] args )
    {
        LocalDate enero = LocalDate.of(2026,1, 1);
        LocalDate finEnero = LocalDate.of(2026,1,31);
        LocalDate febrero = LocalDate.of(2026,2,1);
        LocalDate finfebrero = LocalDate.of(2026,2,28);

        PrecioProducto precioEnero = new PrecioProducto(enero, finEnero, 199.99d);
        PrecioProducto precioFebrero = new PrecioProducto(febrero, finfebrero, 229.99d);
        List<PrecioProducto> preciosBotinesMessi = new ArrayList<>();

        preciosBotinesMessi.add(precioEnero);
        preciosBotinesMessi.add(precioFebrero);

        Producto botinesMessi = new Producto("2022121820051", "Botines F50 Elite", preciosBotinesMessi);

        System.out.println(botinesMessi.getPrecio(LocalDate.of(2026,1,1)));
        System.out.println(botinesMessi.getPrecio(LocalDate.of(2026,2,1)));
    }
}


        /*
         Segun la consigna, aca no va:
         Ningun input para pedir al usuario sus datos, ni menus,  Aca solo se instancian datos hardcodeados para ver si la lógica funciona
         */

        //1. Creacion de datos de prueba
        //Producto botinesAdidas = new Producto("1812202232004","Adidas F50 Elite", 149.99);
        //Cliente leoMessi = new Cliente(parametro1, parametro2, ...)
        //Carrito carrito = new Carrito(leoMessi);
        // otros datos más ...

        //2. Probar metodos
        //carrito.agregarItem(botines, 2);

        //3. Imprimir resultados para chequear
        //System.out.println("Total del carrito: $" + carrito.calcularTotal());
