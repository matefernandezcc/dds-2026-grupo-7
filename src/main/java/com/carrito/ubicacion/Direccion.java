package com.carrito.ubicacion;

import java.util.List;
import java.util.ArrayList;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class Direccion{
    private String calle1;
    private String calle2;
    private Integer altura;
    private boolean sinAltura;
    private Integer piso;
    private Integer cuerpo;
    private String departamento;
    private Ciudad ciudad;
    private Double latitud;
    private Double longitud;

    // Lista de ciudades habilitadas
    private List<Ciudad> ciudadesHabilitadas;
 
    public Direccion(String calle1, Integer altura, Ciudad ciudad){
        this.calle1 = calle1;
        this.calle2 = "";
        this.altura = altura;
        this.sinAltura = false;
        this.piso = 0;
        this.cuerpo = 0;
        this.departamento = "";
        this.ciudad = ciudad;
        this.ciudadesHabilitadas = new ArrayList<>(); // Inicializa la lista
    }
    
    public String getCalle1(){ return calle1; }
    public String getCalle2(){ return calle2; }
    public Integer getAltura(){ return altura; }
    public boolean getSinAltura(){ return sinAltura;}
    public Integer getPiso(){ return piso; }
    public Integer getCuerpo(){ return cuerpo; }
    public String getDepartamento(){ return departamento; }
    public Ciudad getCiudad(){ return ciudad; }

    public void addCiudadHabilitada(Ciudad ciudad){ ciudadesHabilitadas.add(ciudad); }

    // Modificado para no mostrar " y " si no hay calle2
    public String getCalle(){
        if (this.calle2 != null && !this.calle2.isEmpty()) {
            return this.getCalle1() + " y " + this.getCalle2();
        }
        return this.getCalle1();
    }

    // Modificado para no mostrar "Piso: 0, Departamento: " si no hay datos
    public String getDepartamentoCompleto(){
        StringBuilder sb = new StringBuilder();
        boolean hasPiso = this.getPiso() != null && this.getPiso() != 0;
        boolean hasDepartamento = this.getDepartamento() != null && !this.getDepartamento().isEmpty();

        if (hasPiso) {
            sb.append("Piso: ").append(this.getPiso());
        }
        if (hasDepartamento) {
            if (hasPiso) { // Si ya agregamos el piso, ponemos una coma
                sb.append(", ");
            }
            sb.append("Departamento: ").append(this.getDepartamento());
        }
        return sb.toString();
    }

    // Modificado para construir la dirección de forma más dinámica y legible
    public String getDireccion(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.getCalle());

        if (!this.getSinAltura() && this.getAltura() != null && this.getAltura() != 0) {
            sb.append(" ").append(this.getAltura());
        }

        String departamentoInfo = this.getDepartamentoCompleto();
        if (!departamentoInfo.isEmpty()) {
            sb.append(", ").append(departamentoInfo);
        }

        sb.append(", ").append(this.getCiudad().getNombre());
        return sb.toString();
    }

    public boolean estaHabilitadoEnvio(){
        return ciudadesHabilitadas.contains(this.getCiudad());
    }

    public Double getLatitud() {
        if (this.latitud == null) cargarCoordenadas();
        return this.latitud;
    }

    public Double getLongitud() {
        if (this.longitud == null) cargarCoordenadas();
        return this.longitud;
    }

    // Integracion con la API de nominatim para obtener las coordenadas de la direccion
    private void cargarCoordenadas() {
        try {
            // String limpio para buscar: Ej: "Calle Falsa 123, Springfield"
            String query = calle1 + " " + altura + ", " + ciudad.getNombre();
            String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
            String url = "https://nominatim.openstreetmap.org/search?q=" + encodedQuery + "&format=json";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("User-Agent", "DDS-2026-Grupo7") // Nominatim exige identificarse
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();

            if (json != null && json.contains("\"lat\"") && json.contains("\"lon\"")) {
                this.latitud = Double.parseDouble(json.split("\"lat\":\"")[1].split("\"")[0]);
                this.longitud = Double.parseDouble(json.split("\"lon\":\"")[1].split("\"")[0]);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener coordenadas de Nominatim: " + e.getMessage());
        }
    }
}
