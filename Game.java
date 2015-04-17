import java.util.Stack;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Player player;
    
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        player = new Player();
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room JUNTA, SANTODOMINGO, GUZMAN, SANPEDRO, PLAZATOROS, LASTRA, MCDONALD;
         
        // create the rooms
        JUNTA = new Room("PARKING LA JUNTA","cuchillo",150);
        SANTODOMINGO = new Room("PARKING DE SANTO DOMINGO","tenedor",100);
        GUZMAN = new Room("PARKING DE GUZMAN","cuchara", 150);
        SANPEDRO = new Room("PARKING DE SAN PEDRO","bota",150);
        PLAZATOROS = new Room("PARKING DE LA PLAZA DE TOROS","reloj",150);
        LASTRA = new Room("PARKING DE LA LASTRA","pulsera",200);
        MCDONALD = new Room("PARKING DEL MC DONALD","hamburguesa",200);
        
        // initialise room exits
        SANTODOMINGO.setExit("north",SANPEDRO);
        SANTODOMINGO.setExit("east",PLAZATOROS);
        SANTODOMINGO.setExit("oeste",JUNTA);
        SANTODOMINGO.setExit("southeast",GUZMAN);
        
        JUNTA.setExit("east",SANTODOMINGO);
        
        GUZMAN.setExit("northwest",SANTODOMINGO);
        
        SANPEDRO.setExit("south",SANTODOMINGO);
        
        PLAZATOROS.setExit("west",SANTODOMINGO);
        PLAZATOROS.setExit("north",LASTRA);
        PLAZATOROS.setExit("southeast",MCDONALD);
        
        MCDONALD.setExit("northwest",PLAZATOROS);
        
        LASTRA.setExit("south",PLAZATOROS);
        //asignamos el comienzo del camino
        player.setCurrentRoom(SANTODOMINGO);
        // introducimos objetos en las habitaciones
        SANTODOMINGO.addObjeto("Pelota", 2);
        SANTODOMINGO.addObjeto("Guantes",0.5);
        GUZMAN.addObjeto("Botella",1);
        
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
    }
    
     /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.printAllCommands();
    }
    
     /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            player.goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            player.printLocationInfo();
        }
        else if (commandWord.equals("eat")) {
            System.out.println("You have eaten now and you are not hungry any more.");
        }
        else if (commandWord.equals("back")){
            player.deleteRoom();
        }

        return wantToQuit;
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
