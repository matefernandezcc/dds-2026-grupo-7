package com.carrito.ubicacion;

//import com.carrito.ubicacion.Ciudad;
import java.util.List;

public class Direccion{
    private String calle1;
    private String calle2;
    private Integer altura;
    private boolean sinAltura;
    private Integer piso;
    private Integer cuerpo;
    private String departamento;
    private Ciudad ciudad;

    private List<Ciudad> ciudadesHabilitadas;
 
    public Direccion (String calle1, String calle2, Integer altura, boolean sinAltura, Integer piso, Integer cuerpo, String departamento, Ciudad ciudad){
        this.calle1 = calle1;
        this.calle2 = calle2;
        this.altura = altura;
        this.sinAltura = sinAltura;
        this.piso = piso;
        this.cuerpo = cuerpo;
        this.departamento = departamento;
        this.ciudad = ciudad;
    }

    public String getCalle(){
        return this.getCalle1() + " y " + this.getCalle2();
    }
    
    public String getCalle1(){ return this.calle1;}
    public String getCalle2(){ return this.calle2;}
    public Integer getAltura(){return this.altura;}
    public boolean getSinAltura(){return this.sinAltura;}
    public Integer getPiso(){return this.piso;}
    public String getDepartamento(){return this.departamento;}
    public Ciudad getCiudad(){return ciudad;}

    private Boolean tieneDepartamento(){
        return this.getPiso() != null && this.getDepartamento() != null;
    }

    public String getDepartamentoCompleto(){
        return "Piso: " + this.getPiso() + ", Departamento: " + this.getDepartamento();
    }

    public String getDireccion(){
        if (this.getSinAltura()){
            return this.getCalle() + this.getCiudad();
        }

        if (!this.tieneDepartamento()){
            return this.getCalle() + this.getAltura() + ", " + this.getCiudad();
        }

        return this.getCalle() + this.getAltura() + ", " + this.getDepartamentoCompleto() + ", " + this.getCiudad();
    }

    public Boolean estaHabilitadoEnvio(){
        return ciudadesHabilitadas.contains(this.getCiudad());
    }

    //public getLatitud(){}
    //public getLongitud(){}
}

