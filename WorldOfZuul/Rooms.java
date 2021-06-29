import java.util.ArrayList;
/**
 * This class creates and stores all of the rooms in the game.
 *
 * @author Alexandra-Maria Anastase (k-number: 20022789)
 * @version 30.11.2020
 */
public class Rooms
{
    private ArrayList<Room> rooms;
    private Characters charactersObject;
    private Items itemsObject; 

    /**
     * Constructor for objects of class Rooms
     */
    public Rooms(Items itemsObject, Characters charactersObject)
    {
        rooms = new ArrayList<>();
        this.charactersObject = charactersObject;
        this.itemsObject = itemsObject;
        createRooms();
    }

    /**
     * Creates the rooms (including their items and characters) and stores them in an ArrayList.
     */
    private void createRooms(){
        // create the rooms
        Room courtyard = new Room("in the courtyard outside of the castle.", false, "courtyard");
        Room potionsStoreroom = new Room("in the Potions Storeroom.", false, "potionsStoreroom");                   
        Room armory = new Room("in the armory.", false, "armory");                   
        Room sphinxRoom = new Room("in a the sphinx's chamber.", false, "sphinxRoom");
        Room bronzeRoom = new Room("in the Bronze Chamber!", false, "bronzeRoom");                       
        Room guardRoom = new Room("in the Guard Room.", false, "guardRoom");           
        Room silverRoom = new Room("in the Silver Chamber!", false, "silverRoom");
        Room kitchen = new Room("in the kitchen", false, "kitchen");            
        Room goldRoom = new Room("in the Gold Chamber!", false, "goldRoom");            
        Room dungeon = new Room("in the dungeon!", true, "dungeon");
        Room diamondRoom = new Room("in the Diamond Chamber!", true, "diamondRoom");            
        Room royaltyRoom = new Room("in the Royal Chamber!", true, "royaltyRoom");
        Room transporterRoom = new Room("in the Transporter. You will be transported to another location shortly...", 
                false, "transporterRoom");

        //set task completion
        goldRoom.setTaskCompletion(false);
        diamondRoom.setTaskCompletion(false);
        royaltyRoom.setTaskCompletion(false);
        dungeon.setTaskCompletion(false);

        //add rooms to the ArrayList rooms
        rooms.add(courtyard);
        rooms.add(potionsStoreroom);
        rooms.add(armory);
        rooms.add(sphinxRoom);
        rooms.add(bronzeRoom);
        rooms.add(guardRoom);
        rooms.add(silverRoom);
        rooms.add(kitchen);
        rooms.add(goldRoom);
        rooms.add(dungeon);
        rooms.add(diamondRoom);
        rooms.add(royaltyRoom);
        rooms.add(transporterRoom);

        // initialise room exits
        courtyard.setExit("east", potionsStoreroom);
        courtyard.setExit("south", sphinxRoom);
        courtyard.setExit("west", armory);

        potionsStoreroom.setExit("west", courtyard);

        armory.setExit("east", courtyard);

        sphinxRoom.setExit("north", courtyard);
        sphinxRoom.setExit("east", bronzeRoom);
        sphinxRoom.setExit("west", guardRoom);

        guardRoom.setExit("east", sphinxRoom);
        guardRoom.setExit("south", silverRoom);

        silverRoom.setExit("north", guardRoom);
        silverRoom.setExit("east", kitchen);

        bronzeRoom.setExit("west", sphinxRoom);

        kitchen.setExit("west", silverRoom);
        kitchen.setExit("east", goldRoom);

        goldRoom.setExit("west",kitchen);
        goldRoom.setExit("down",dungeon);

        dungeon.setExit("up", goldRoom);
        dungeon.setExit("east", diamondRoom);

        diamondRoom.setExit("west", dungeon);
        diamondRoom.setExit("down", royaltyRoom);

        royaltyRoom.setExit("up", diamondRoom);
        royaltyRoom.setExit("east", transporterRoom);

        transporterRoom.setExit("west", royaltyRoom);

        //adds characters to rooms           
        royaltyRoom.addCharacter(charactersObject.getCharacterByName("prince"));
        courtyard.addCharacter(charactersObject.getCharacterByName("guard"));
        sphinxRoom.addCharacter(charactersObject.getCharacterByName("sphinx"));
        dungeon.addCharacter(charactersObject.getCharacterByName("dragon"));
        guardRoom.addCharacter(charactersObject.getCharacterByName("spy"));

        charactersObject.getCharacterByName("dragon").setAttackPoints(25);

        //adds items to rooms       
        potionsStoreroom.addItem(itemsObject.getItemByName("healingPotion"));
        potionsStoreroom.addItem(itemsObject.getItemByName("poisonPotion"));
        potionsStoreroom.addItem(itemsObject.getItemByName("randomPotion"));
        armory.addItem(itemsObject.getItemByName("sword"));
        armory.addItem(itemsObject.getItemByName("axe"));
        armory.addItem(itemsObject.getItemByName("spear"));
        bronzeRoom.addItem(itemsObject.getItemByName("bronzeKey"));
        silverRoom.addItem(itemsObject.getItemByName("silverKey"));
        goldRoom.addItem(itemsObject.getItemByName("goldKey"));
        diamondRoom.addItem(itemsObject.getItemByName("diamondKey"));
        kitchen.addItem(itemsObject.getItemByName("magicMushroom"));       
    }

    /**
     * Returns an ArrayList of the rooms in the game.
     */
    public ArrayList<Room> getRoomArrayList(){
        return rooms;
    }

    /**
     * Returns a room by receiving its name.
     * @param roomName The name of the room we are looking for.
     * @return room The room we are looking for.
     */
    public Room getRoomByName(String roomName){
        for(Room room : rooms){
            if(room.getRoomName().equals(roomName)){
                return room;
            }
        }
        return null;
    }
}
