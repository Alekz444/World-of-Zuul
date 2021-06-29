import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
/**
 * This class stores methods which are specific to certain rooms.
 *
 * @author modified by Alexandra-Maria Anastase (k-number: 20022789)
 * @version 30.11.2020
 */
public class RoomTasks
{
    private Player player;
    private DialoguePrinter dialoguePrinter;
    private Rooms roomsObject;
    private HashMap<String, Item> items;
    private ArrayList<Room> rooms;
    private Room currentRoom;
    private Room previousRoom;
    private CharacterMovement characterMovement;

    /**
     * Constructor for objects of class RoomTasks. Takes multiple parameters.
     */
    public RoomTasks(Player player, Rooms roomsObject, HashMap<String, Item> items,
    ArrayList<Room> rooms, Room currentRoom, Room previousRoom, CharacterMovement characterMovement)
    {
        this.player = player;
        this.items = items;
        this.roomsObject = roomsObject;
        dialoguePrinter = new DialoguePrinter();
        this.roomsObject = roomsObject;   
        this.currentRoom = currentRoom;
        this.previousRoom = previousRoom;
        this.rooms = rooms;
        this.characterMovement = characterMovement;
    }    

    /**
     * Method for diamondRoom
     */
    public void diamondRoomTask(){
        if(roomsObject.getRoomByName("diamondRoom").getTaskCompletion() == false){
            dialoguePrinter.printDiamondRoomTask();
            roomsObject.getRoomByName("diamondRoom").setTaskCompletion(true);
        }
    }

    /**
     * Method for royaltyRoom.
     */
    public void royaltyRoomTask(){
        if(roomsObject.getRoomByName("royaltyRoom").getTaskCompletion() == false){
            dialoguePrinter.printRoyaltyRoomTask();
        }
        roomsObject.getRoomByName("royaltyRoom").setTaskCompletion(true);
    }

    /**
     * Method for courtyard, after the task from royaltyRoom is completed.
     */
    public boolean courtyardFinalTask(){        
        if(roomsObject.getRoomByName("royaltyRoom").getTaskCompletion() == true){
            dialoguePrinter.printGameWon();
            return true;
        }
        return false;
    }

    /**
     * Method for sphinxRoom (sphinx's riddle).
     * @param secondWord The second word of the player's command.
     */
    public void answerForSphinxRoom(String secondWord){
        Room sphinxRoom = roomsObject.getRoomByName("sphinxRoom");
        if(sphinxRoom.getTaskCompletion() == false){
            if(secondWord.equals("man") || secondWord.equals("human") || secondWord.equals("person")){
                Room bronzeRoom = sphinxRoom.getExit("east");
                bronzeRoom.getItem("bronzeKey").setItemAvailability(true);
                System.out.println("Well done. Now the key and continue your quest.");
                sphinxRoom.setTaskCompletion(true);
                bronzeRoom.setLockedStatus(false);
            }
            else{
                System.out.println("Sphinx: ((╬◣﹏◢)) You have failed. Try again.");
            }
        }
    }

    /**
     * The task from the goldRoom.
     */
    public void goldRoomTask(){
        if(roomsObject.getRoomByName("goldRoom").getTaskCompletion() == false){              
            Item bronzeKey = items.get("bronzeKey");
            Item silverKey = items.get("silverKey");
            Item goldKey = items.get("goldKey");            
            if(player.inventoryContainsItem(bronzeKey) && player.inventoryContainsItem(silverKey) && player.inventoryContainsItem(goldKey)){
                roomsObject.getRoomByName("dungeon").setLockedStatus(false);
                System.out.println("Spy: You are now free to enter the dungeon. Beware. ＼(〇_ｏ)／");
                roomsObject.getRoomByName("goldRoom").setTaskCompletion(true);
            }
            else{
                System.out.println("Spy: Attention! You need to have the three keys in order to enter the dungeon. ┐(︶▽︶)┌");
            }            
        }
    }              

    /**
     * Transports player to a random room.
     * @param currentRoomParameter The room the player is in at the moment.
     * @param previousRoomParameter The previous room the player was in.
     * @return True if game is won (the player reached the courtyard) and fale otherwise.
     */
    public boolean transportToRandomRoom(Room currentRoomParameter, Room previousRoomParameter){
        currentRoom = currentRoomParameter;
        previousRoom = previousRoomParameter;

        Random random = new Random();
        int index = random.nextInt(rooms.size());
        //makes sure the random int picked will not correspond to the transporterRoom               

        while(rooms.get(index) == currentRoom){
            index = random.nextInt(rooms.size());
        }            

        previousRoom = currentRoom;           
        currentRoom = rooms.get(index);                   

        characterMovement.moveCharacters(currentRoom, previousRoom);        
        System.out.println(currentRoom.getLongDescription()); 
        currentRoom.listCharacters();
        if(currentRoom.hasItems() == true){
            currentRoom.listItems(player);
        }
        if(roomsObject.getRoomByName("courtyard") == currentRoom){
            dialoguePrinter.printGameWon();
            return true;          
        }
        return false;
    }      

    /**
     * Returns the changed value of currentRoom.
     */
    public Room getCurrentRoom(){
        return currentRoom;
    }

    /**
     * Returns the changed value of previousRoom.
     */
    public Room getPreviousRoom(){
        return previousRoom;
    }
}
