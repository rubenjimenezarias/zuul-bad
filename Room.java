import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private HashMap <String,Room> exits;
    
    private ArrayList<Objeto> objetos;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description,String objeto, int peso) 
    {
        this.description = description;
        exits = new HashMap<>();
        objetos = new ArrayList<>();
    }
    
    /**
    * Define an exit from this room.
    * @param direction The direction of the exit.
    * @param neighbor The room in the given direction.
    */
    public void setExit(String direction, Room neighbor){
        exits.put(direction,neighbor);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Devuelve las salidas
     * @param descripcion de la salida
     */
    public Room getExit(String direccion)
    {
        return exits.get(direccion);
    }
    
    /**
    * Return a description of the room's exits.
    * For example: "Exits: north east west"
    *
    * @ return A description of the available exits.
    */
    public String getExitString(){
        String informacion = "/--/ Exits: ";
        Iterator it = exits.keySet().iterator();
        while (it.hasNext()) {
            informacion += it.next() + " ";
        }
        return informacion;
    }
    
    /**
    * Return a long description of this room, of the form:
    *     You are in the 'name of room'
    *     Exits: north west southwest
    * @return A description of the room, including exits.
    */
    public String getLongDescription(){
        return "You are in " + description + 
               "\n /--/ Objetos" +
               "\n" + informacionObjetos() +
               "\n " + getExitString();       
    }
    
    /**
     * Añadimos un objeto a la habitacion.
     */
    public void addObjeto(String descripcion,double peso)
    {
        objetos.add(new Objeto(descripcion, peso));
    }
    
    /**
     * Muestra la informacion de los objetos de la habitacion
     */
    public String informacionObjetos()
    {
        String listaObjetos = " ";
        if (objetos.size() == 0){
            listaObjetos = "No hay objetos en esta habitacion";
        }
        else
        {
            for (Objeto elemento:objetos)
            {
                listaObjetos += elemento.informacion();
            }
        }
        return listaObjetos;
    }
}
