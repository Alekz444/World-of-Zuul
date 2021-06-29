import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes, modified by Alexandra-Maria Anastase (k-number: 20022789)
 * @version 30.11.2020
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;  // stores exits of this room.
    private HashSet<Item> items; //stores items of this room.
    private HashSet<Character> characters; //stores characters of this room.
    private boolean isLocked;
    private String roomName;
    private boolean taskCompletion;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     * @param isLocked The locked status of the room.
     * @param roomName The name of the room.
     */
    public Room(String description, boolean isLocked, String roomName) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new HashSet<>();
        characters = new HashSet<>();
        this.isLocked = isLocked;
        this.roomName = roomName;
        taskCompletion = false;
    }

    /**
     * Returns the task completion status of the room.
     */
    public boolean getTaskCompletion(){
        return taskCompletion;
    }

    /**
     * Sets the task completion status of the room.
     * @param completed The status taskCompletion should be set to
     * (true or false)
     */
    public void setTaskCompletion(boolean completed){
        taskCompletion = completed;
    }

    /**
     * Returns the name of the room.
     */
    public String getRoomName(){
        return roomName;
    }

    /**
     * Sets whether the room is locked or unlocked.
     * @param locked The status (true or false) that isLocked should be chaged to.
     */
    public void setLockedStatus(boolean locked){
        isLocked = locked;
    }

    /**
     * Returns the isLocked status.
     */
    public boolean getLockedStatus(){
        return isLocked;
    }

    //items methods
    /**
     * Returns the set of items in a room.
     */
    public HashSet<Item> getItemSet(){
        return items;
    }

    /**
     * Returns the item requested by the command.
     * @param itemNeeded The name of the item in the room 
     * we are looking for.
     * @return item The item we are looking for.
     */
    public Item getItem(String itemNeeded){
        for(Item item : items){
            if(item.getItemName().equals(itemNeeded)){
                return item;
            }
        }
        return null;
    }

    /**
     * Returns true if the room has item and false if it does not.
     */ 
    public boolean hasItems(){
        if(items.isEmpty())
        { return false; }
        else return true;
    }

    /**
     *  Lists all items and their weights available in the room.
     *  @param player The object of the class Player.
     */
    public void listItems(Player player){
        System.out.println("This room has items available for you to take.");
        System.out.println("Do not forget that the maximum weight you can carry is " + player.getMaximumInventoryWeight() + " kg.");
        System.out.println("");

        for(Item item : items){
            item.listItem();
        }
        System.out.println("");
    }

    /**
     * Adds an item to the set 'items'.
     * @param item Is the item to be added to the set.
     */
    public void addItem(Item item){
        items.add(item);
    }

    /**
     * Adds an item to the items HashSet of a room.
     * @param item The item that needs to be added.
     */
    public void addItemToRoom(Item item){
        items.add(item);
    }

    /**
     * Removes an item from the HashSet of a room.
     * @param item The item that needs to be removed.
     */
    public void removeItemFromRoom(Item item){
        items.remove(item);
    }

    //descriptions
    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    //exit methods
    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }

    //character methods
    /**
     * Checks to see whether a character is in a room.
     * @param characterChecked The character who needs checking
     * @return true if the character is in the room and false otherwise
     */
    public boolean containsCharacter(Character characterChecked){
        for(Character character : characters){
            if(character == characterChecked){
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a character to the set 'characters'.
     * @param character The character who needs to be added.
     */   
    public void addCharacter(Character character){
        characters.add(character);
    }

    /**
     * Removes a character from the set 'characters'.
     * @param character The character who needs to be removed.
     */ 
    public void removeCharacter(Character character){
        characters.remove(character);
    }

    /**
     * Returns character based on its name.
     * @param name The name of the character who needs to be returned.
     */
    public Character getCharacter(String name){
        for(Character character0 : characters){
            if(character0.getCharacterName().equals(name)){
                return character0;
            }
        }
        return null;
    }

    /**
     * Removes a character from the set 'characters' and adds it to the HashSet of another room.
     * @param character The character who need to be moved to another room.
     * @param room The room where said character need to be moved to.
     */
    public void moveCharacter(Character character, Room room){
        room.addCharacter(character);
        characters.remove(character);
    } 

    /**
     * Prints all of the characters in this room.
     */
    public void listCharacters(){
        if(!characters.isEmpty()){
            System.out.print("You are here with: ");
            for(Character character : characters){
                System.out.print("" + character.getCharacterName() + " ");
            }
            System.out.println("");
        }
    }

    //private methods
    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }
}

