import java.util.Stack;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    // instance variables - replace the example below with your own
    private Room currentRoom;
    private Stack<Room> camino;
    private ArrayList<Objeto> mochila;
    
    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        // initialise instance variables
        camino = new Stack<Room>();
        currentRoom = null;
        mochila = new ArrayList<>();
        mochila.add(new Objeto("primero",1000));
        
    }
    
    /**
     * 
     * @param  habitacion para introducir al camino
     */
    public void addRoom(Room room)
    {
        // put your code here
        camino.push(room);
    }
    
    /**
     * Borra la ultima habitacion si volvemos con back
     */
    public void goBack()
    {
        if (caminoVacio()){
            System.out.println("No hay mas caminos atras");
        }
        else
        {
            currentRoom = camino.pop();
        }
        printLocationInfo();
    }
    
    /**
     * cambiar current room
     */
    public void setCurrentRoom(Room room)
    {
        currentRoom = room;
    }
    
    /**
     * Devuelve true si esta vacia el camino
     */
    public boolean caminoVacio()
    {
        return camino.empty();
    }
    
    /**
     * Devuelve la habitacion en la que estamos.
     */
    public Room currentRoom()
    {
        return currentRoom;
    }
    
    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    public void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            camino.push(currentRoom);
            currentRoom = nextRoom;            
            printLocationInfo();
        }
    }
    
    /**
     * 
     */
    public void printLocationInfo()
    {
        System.out.println("---");
        System.out.println(currentRoom.getLongDescription());
        System.out.println("---");
        System.out.println("EN LA MOCHILA TIENES");
        verMochila();
        System.out.println("---");
    }
    
    /**
     * Añadir objeto a la mochila
     */
    public void addObjeto(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("What object do you want to take?");
            return;
        }

        Objeto objeto = currentRoom.getObject(command.getSecondWord());
        

        if (objeto == null) {
            System.out.println("There is no object!");
        }
        else {
            mochila.add(objeto);
            currentRoom.deleteObject(objeto.descripcion());            
            printLocationInfo();
        }
    }
    
    /**
     * Dejar objeto a la habitacion
     */
    public void dropObjeto(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("What object do you want to take?");
            return;
        }
        
        //buscamos el objeto en la mochila
        Objeto objeto = null;
        boolean encontrado = false;
        int cont = 0;
        while (!encontrado && cont < mochila.size())
        {
            if (mochila.get(cont).descripcion() == command.getSecondWord())
            {
                objeto = mochila.get(cont);
                encontrado = true;
            }
            cont ++;
        }
        

        if (objeto == null) {
            System.out.println("There is no object (mochila)!");
        }
        else {
            mochila.remove(cont);
            currentRoom.dejaObjeto(objeto);            
            printLocationInfo();
        }
    }
    
    /**
     * 
     */
    public void verMochila(){
       for(Objeto elemento: mochila)
       {
           System.out.println(elemento.descripcion()+ " " + elemento.peso() );
       }
    }
    
}