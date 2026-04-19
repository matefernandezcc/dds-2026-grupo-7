public class Tarjeta {
    private String nombre;
    private MarcaTarjeta marca;
    private int montoDisponible;
    public int getMontoDisponible()
    {
        return montoDisponible;
    }
    public String getNombre()
    {
        return nombre;
    }
    public MarcaTarjeta getMarcaTarjeta()
    {
        return marca;
    }
    public Tarjeta (String nombre, MarcaTarjeta marca,int montoDisponible){
        this.nombre=nombre;
        this.marca=marca;
        this.montoDisponible= montoDisponible;

    }

