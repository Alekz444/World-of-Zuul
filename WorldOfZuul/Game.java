import java.util.Random;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
/**
 *  This class is the main class of the "Zuul Rescue" application. 
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes, modified by Alexandra-Maria Anastase (k-number: 20022789)
 * Anastase
 * @version 30.11.2020 
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room previousRoom;
    private Character currentAdversary;
    private DialoguePrinter dialoguePrinter;
    private UserCommands userCommands;
    private RoomTasks roomTasks;
    private Player player;
    private Rooms roomsObject;
    private Items itemsObject;
    private Characters charactersObject;
    private CharacterMovement characterMovement;

    private HashMap <String, Item> items;
    private ArrayList<Room> rooms;    
    private HashSet<Character> characters;
    /**
     * Create the game and initialise its internal map.
     */
    public Game()
    {
        player = new Player();
        dialoguePrinter = new DialoguePrinter();
        parser = new Parser();
        items = new HashMap<>();

        player.setMaximumInventoryWeight(6);

        itemsObject = new Items(items);
        charactersObject = new Characters();
        roomsObject = new Rooms(itemsObject, charactersObject);

        createItems();
        createCharacters();
        createRooms();

        characterMovement = new CharacterMovement(charactersObject);
        roomTasks = new RoomTasks(player, roomsObject, items, rooms, currentRoom,
            previousRoom, characterMovement);
        userCommands = new UserCommands(player, currentAdversary, roomsObject, 
            roomTasks, currentRoom, previousRoom, rooms, charactersObject, characterMovement);       
    }

    /**
     * Creates the items for the game.
     */
    private void createItems(){
        items = itemsObject.getItemHashMap();        
    }

    /**
     * Creates the characters for the game.
     */
    private void createCharacters(){       
        characters = charactersObject.getCharacterHashSet();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {                
        rooms = roomsObject.getRoomArrayList();
        currentRoom = roomsObject.getRoomByName("courtyard");    // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        dialoguePrinter.printWelcome(currentRoom);        
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
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean quit = false;
        boolean wantToQuit = false;
        boolean gameIsLost = false;
        boolean gameIsWon = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();

        if(commandWord.equals("help")) {
            userCommands.printHelp();
        }
        else if (commandWord.equals("go")) {
            gameIsWon = userCommands.goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = userCommands.quit(command);
        }
        else if(commandWord.equals("take")) {
            userCommands.putInInventory(command);
        }
        else if(commandWord.equals("throw")) {
            userCommands.takeOutOfInventory(command);
        }
        else if(commandWord.equals("listInventory")) {
            userCommands.listInventory(command);
        }
        else if(commandWord.equals("back")) {
            userCommands.goBack(command);
        }
        else if(commandWord.equals("answer")) {
            userCommands.answerCommand(command);
        }
        else if(commandWord.equals("attack")) {
            gameIsLost = userCommands.doDamageToAdversary(command);
        }
        else if(commandWord.equals("use")) {
            userCommands.useCommand(command);
        }
        else if(commandWord.equals("ingest")) {
            userCommands.ingestCommand(command);
        }

        // else command not recognised.
        if(gameIsLost == true){
            quit = true;
        }
        else if(gameIsWon == true){
            quit = true;
        }
        else if(wantToQuit == true){
            quit = true;
        }
        return quit;
    }  
}
