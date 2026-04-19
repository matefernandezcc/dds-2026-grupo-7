package com.carrito.ubicacion;

//import com.carrito.ubicacion.Ciudad;

public class Direccion{
    private String calle1;
    private String calle2;
    private Integer altura;
    private boolean sinAltura;
    private Integer piso;
    private Integer cuerpo;
    private String departamento;
    private Ciudad ciudad;

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
        return self.getCalle1() + " y " + self.getCalle2();
    }

    public Integer getAltura(){
        return this.altura;
    }

    public boolean getSinAltura(){
        return this.sinAltura;
    }

    public Integer getPiso(){
        return this.piso;
    }

    public Integer getCuerpo(){
        return this.cuerpo;
    }

    public String getDepartamento(){
        return this.departamento;
    }

    private Boolean tieneDepartamento(){
        return self.getPiso() != null && self.getDepartamento() != null;
    }

    public String getDepartamentoCompleto(){
        return "Piso: " + self.getPiso() + ", Departamento: " + self.getDepartamento();
    }

    public Ciudad getCiudad(){
        return this.ciudad.getNombre() + ", " + this.ciudad.getProvincia();
    }

    public String getDireccion(){
        if (self.getSinAltura()){
            return self.getCalle() + self.getCiudad()
        }

        if (!self.tieneDepartamento()){
            return self.getCalle() + self.getAltura() + ", " + self.getCiudad()
        }

        return self.getCalle() + self.getAltura() + ", " + self.getDepartamentoCompleto()
     }

     //public getLatitud(){}
     //public getLongitud(){}
     //public Boolean estaHabilitadoEnvio(){}
}
