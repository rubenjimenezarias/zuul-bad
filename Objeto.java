
/**
 * Write a description of class Objeto here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Objeto
{
    // instance variables - replace the example below with your own
    private String descripcion;
    private double peso;

    /**
     * Constructor for objects of class Objeto
     * @param descripcion breve descripcion del objeto
     * @param peso peso en kg del objeto
     */
    public Objeto(String descripcion,double peso)
    {
        // initialise instance variables
        this.descripcion = descripcion;
        this.peso = peso;
    }
    
    /**
     * Devuelve la infomracion completa del objeto en un String
     * @return Informacion
     */
    public String informacion()
    {
        return descripcion + " " + peso + " kg\n";
    }
}
