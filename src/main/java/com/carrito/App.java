package com.carrito;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.List;
import com.carrito.catalogo.PrecioProducto;
import com.carrito.catalogo.Producto;
import com.carrito.compras.Carrito;
import com.carrito.compras.Item;
import com.carrito.pagos.Pago;
import com.carrito.pagos.MarcaTarjeta;
import com.carrito.pagos.Tarjeta;
import com.carrito.ubicacion.Ciudad;
import com.carrito.ubicacion.Direccion;
import com.carrito.usuarios.Cliente;

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
        System.out.println("--- INICIANDO PRUEBAS ---");

        // 1. Fechas de prueba
        LocalDate hoy = LocalDate.now();
        LocalDate ayer = hoy.minusDays(1);
        LocalDate manana = hoy.plusDays(1);
        LocalDate haceUnMes = hoy.minusMonths(1);
        LocalDate enUnMes = hoy.plusMonths(1);
        LocalDate enUnAnio = hoy.plusYears(1);

        // 2. Ciudades
        Ciudad buenosAires = new Ciudad("Buenos Aires");
        Ciudad cordoba = new Ciudad("Cordoba");

        // 3. Productos y Precios
        PrecioProducto precioBotinesHoy = new PrecioProducto(ayer, manana, 199.99d);
        PrecioProducto precioBotinesFuturo = new PrecioProducto(enUnMes, enUnMes.plusDays(30), 229.99d);
        List<PrecioProducto> preciosBotinesMessi = new ArrayList<>();
        preciosBotinesMessi.add(precioBotinesHoy);
        preciosBotinesMessi.add(precioBotinesFuturo);
        Producto botinesMessi = new Producto("2022121820051", "Botines F50 Elite", preciosBotinesMessi);

        PrecioProducto precioCamisetaHoy = new PrecioProducto(ayer, manana, 89.99d);
        List<PrecioProducto> preciosCamiseta = new ArrayList<>();
        preciosCamiseta.add(precioCamisetaHoy);
        Producto camisetaArgentina = new Producto("2022121820052", "Camiseta Argentina", preciosCamiseta);

        // 4. Cliente
        Cliente leoMessi = new Cliente("Lionel", "Messi", "leo@messi.com");
        System.out.println("\n--- Cliente Creado ---");
        System.out.println("Cliente: " + leoMessi.getNombre() + " " + leoMessi.getApellido() + " (" + leoMessi.getEmail() + ")");

        // 5. Direcciones
        Direccion direccionCasa = new Direccion("Av. Libertador", 1234, buenosAires);
        Direccion direccionTrabajo = new Direccion("Corrientes", 500, buenosAires);
        leoMessi.addDireccion(direccionCasa);
        leoMessi.addDireccion(direccionTrabajo);
        
        // Habilitar Buenos Aires para envíos en direccionCasa para probar estaHabilitadoEnvio()
        direccionCasa.addCiudadHabilitada(buenosAires);

        System.out.println("Direccion Casa: " + direccionCasa.getDireccion());
        System.out.println("Direccion Trabajo: " + direccionTrabajo.getDireccion());
        System.out.println("¿Envío habilitado a Direccion Casa? " + direccionCasa.estaHabilitadoEnvio());
        System.out.println("¿Envío habilitado a Direccion Trabajo? " + direccionTrabajo.estaHabilitadoEnvio());

        // Test Nominatim (Lat/Lon) - Puede tardar un poco en la primera llamada
        System.out.println("Latitud Casa: " + direccionCasa.getLatitud());
        System.out.println("Longitud Casa: " + direccionCasa.getLongitud());
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); } // Pequeña pausa para evitar límites de API
        System.out.println("Latitud Trabajo: " + direccionTrabajo.getLatitud());
        System.out.println("Longitud Trabajo: " + direccionTrabajo.getLongitud());

        // 6. Carrito 1
        // Creamos el carrito primero (con lista de items vacía), luego agregamos los items
        Carrito carrito1 = new Carrito(new ArrayList<>(), leoMessi);
        leoMessi.addCarrito(carrito1); // Añadimos el carrito al cliente

        Item itemBotines = new Item(carrito1, botinesMessi, 1, 190.00); // Precio unitario con descuento
        Item itemCamiseta = new Item(carrito1, camisetaArgentina, 2, 89.99); // Precio unitario sin descuento
        carrito1.addItem(itemBotines);
        carrito1.addItem(itemCamiseta);

        carrito1.setDireccionEnvio(direccionCasa);
        carrito1.setDireccionCobro(direccionCasa);

        System.out.println("\n--- Carrito 1 Creado ---");
        System.out.println("Fecha de compra Carrito 1: " + carrito1.getFechaCompra()); // No es un Double
        System.out.println(String.format("Monto Carrito 1 (Oficial): $%.2f", carrito1.getMontoCarrito())); // 1 * 199.99 + 2 * 89.99 = 199.99 + 179.98 = 379.97
        System.out.println(String.format("Monto Carrito 1 (con descuentos): $%.2f", (itemBotines.getPrecio() + itemCamiseta.getPrecio()))); // 1 * 190 + 2 * 89.99 = 190 + 179.98 = 369.98
        System.out.println(String.format("Descuento en Botines: $%.2f", itemBotines.getDescuento())); // 199.99 - 190 = 9.99

        // 7. Pagos para Carrito 1
        // Creamos una tarjeta de prueba para los pagos
        // El constructor de Tarjeta es: Tarjeta(String nombre, MarcaTarjeta marca, String ultimos4digitos)
        // Y el montoDisponible se setea aparte y es Integer.
        MarcaTarjeta marcaVisa = MarcaTarjeta.VISA;
        Tarjeta tarjetaVisa = new Tarjeta("Visa", marcaVisa, "9010");
        tarjetaVisa.setMontoDisponible((int) 500.00);
        leoMessi.addTarjeta(tarjetaVisa); // Añadimos la tarjeta al cliente

        // Ahora el constructor de Pago coincide con Pago.java
        // Se pasa el carrito, la tarjeta y el monto. La fecha de pago no es un atributo del Pago en tu modelo actual.
        Pago pago1 = new Pago(carrito1, tarjetaVisa, 100.00);
        Pago pago2 = new Pago(carrito1, tarjetaVisa, 200.00);
        carrito1.addPago(pago1);
        carrito1.addPago(pago2);

        System.out.println(String.format("Monto Pagado Carrito 1: $%.2f", carrito1.getMontoPagado())); // 300.00
        System.out.println(String.format("Monto Deuda Carrito 1: $%.2f", carrito1.getMontoDeuda())); // 379.97 - 300 = 79.97

        // 8. Carrito 2 (sin pagos, con deuda)
        Carrito carrito2 = new Carrito(new ArrayList<>(), leoMessi);
        Item itemBotines2 = new Item(carrito2, botinesMessi, 1, 199.99);
        carrito2.addItem(itemBotines2);
        leoMessi.addCarrito(carrito2);

        System.out.println("\n--- Carrito 2 Creado ---");
        System.out.println(String.format("Monto Carrito 2 (Oficial): $%.2f", carrito2.getMontoCarrito())); // 1 * 199.99 = 199.99
        System.out.println(String.format("Monto Pagado Carrito 2: $%.2f", carrito2.getMontoPagado())); // 0.0
        System.out.println(String.format("Monto Deuda Carrito 2: $%.2f", carrito2.getMontoDeuda())); // 199.99

        // 9. Estado del Cliente
        System.out.println("\n--- Estado del Cliente ---");
        System.out.println(String.format("Deuda total de %s: $%.2f", leoMessi.getNombre(), leoMessi.getMontoDeuda())); // 79.97 + 199.99 = 279.96
        System.out.println("Cliente habilitado para comprar: " + leoMessi.estaHabilitado()); // True (279.96 <= 1000)

        // 10. Probar un producto con precio futuro
        System.out.println("\n--- Prueba de precio futuro (espera excepción) ---");
        try {
            System.out.println(String.format("Precio botines en un mes: $%.2f", botinesMessi.getPrecio(enUnAnio)));
        } catch (NoSuchElementException e) {
            System.out.println("Excepción esperada: " + e.getMessage());
        }

        System.out.println("\n--- FIN DE PRUEBAS ---");
    }
}
