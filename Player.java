import java.util.Stack;

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
    
    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        // initialise instance variables
        camino = new Stack<Room>();
        currentRoom = null;
        
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
    }
}
