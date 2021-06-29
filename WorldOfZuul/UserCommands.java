import java.util.Random;
import java.util.ArrayList;
/**
 * This class stores all of the methods which are to be called whenever the player inputs
 * a specific command.
 *
 * @author Alexandra-Maria Anastase (k-number: 20022789)
 * @version 30.11.2020
 */
public class UserCommands
{
    private Parser parser;
    private Player player;
    private DialoguePrinter dialoguePrinter;
    private Character currentAdversary;
    private Rooms roomsObject;
    private RoomTasks roomTasks;
    private Room currentRoom;
    private Room previousRoom;
    private Characters charactersObject;
    private ArrayList<Room> rooms;
    private CharacterMovement characterMovement;

    /**
     * Constructor for objects of class UserCommands
     */
    public UserCommands(Player player, Character currentAdversary, Rooms roomsObject, 
    RoomTasks roomTasks, Room currentRoom, Room previousRoom, ArrayList<Room> rooms, 
    Characters charactersObject, CharacterMovement characterMovement)
    {
        parser = new Parser();
        this.currentRoom = currentRoom;
        this.previousRoom = previousRoom;
        this.charactersObject = charactersObject;
        this.player = player;
        this.rooms = rooms;
        dialoguePrinter = new DialoguePrinter();
        this.currentAdversary = currentAdversary;
        this.roomsObject = roomsObject;
        this.roomTasks = roomTasks;
        this.characterMovement = characterMovement;
    }

    /**
     * Print out some help information.
     * Here we print a message and a list of the command words.
     */
    public void printHelp() 
    {
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    public boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /**
     * Adds item to inventory.
     */
    public void putInInventory(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what item to take...
            System.out.println("Which item?");
            return;
        }
        if(command.hasThirdWord()){
            // if there is a third word, the command is invalid
            System.out.println("This is not a valid command. Use the 'take (item)' command.");
            return;
        }

        String itemString = command.getSecondWord();
        Item item = currentRoom.getItem(itemString);

        if(item == null){
            System.out.println("There is no such item here!");
            return;
        }

        if( item.getItemAvailability() == true){
            if(player.getInventoryWeight() < player.getMaximumInventoryWeight()){
                player.addToInventory(item);                    
                System.out.println(item.getItemName() + " was added to Inventory.");
                System.out.println("Your inventory weight is now " + player.getInventoryWeight() + ".");
                item.setItemAvailability(false);
                currentRoom.removeItemFromRoom(item);
                if(itemString.equals("goldKey")){
                    roomTasks.goldRoomTask();
                }
                return;
            }            
            System.out.print("Your inventory is full. ");
            player.listMaximumInventoryWeight();
            System.out.println("You need to give up some items. To do this, use command 'throw (item)'.");  
            return;
        }        
        System.out.println("Hm...Seeems like this item isn't available for you to take. Maybe try another one.");               
    }

    /**
     * Takes the item out of the inventory.
     */
    public void takeOutOfInventory(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what item to throw away...
            System.out.println("Which item?");
            return;
        }
        if(command.hasThirdWord()){
            System.out.println("This command is not valid. Use the 'throw (item)' command.");
            return;
        }
        String itemString = command.getSecondWord();
        Item item = player.getItemInInventory(itemString);

        if(item != null){
            player.removeFromInventory(item);
            item.setItemAvailability(true);
            currentRoom.addItemToRoom(item);
            System.out.println(item.getItemName() + " was taken out of your inventory.");
            System.out.println("Your inventory weight is now " + player.getInventoryWeight());
        }
        else{
            System.out.println("You do not have this item in you inventory.");
        }
    }

    /**
     * Lists the items in the inventory.
     */
    public void listInventory(Command command){
        if(command.hasSecondWord()){
            System.out.println("That is not a valid command. Use the 'listInventory' command.");
            return;
        }
        player.listInventory();
    }

    /**
     * Implementation of 'use' command.
     * @param command The player's command
     */
    public void useCommand(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, which item to use...
            System.out.println("Use what?");
            return;
        }
        if(command.hasThirdWord()){
            System.out.println("That is not a valid command. Use the 'use (item)' command.");
            return;
        }
        String itemName = command.getSecondWord();
        if(player.getItemInInventory(itemName) == null){
            System.out.println("This item is not in your inventory.");
            return;
        }

        if(itemName.equals("diamondKey")){                            
            roomsObject.getRoomByName("royaltyRoom").setLockedStatus(false);
            System.out.println("Spy: You can now enter the royaltyRoom! {{ (>_<) }}");
            return;                            
        }
        else{
            System.out.println("You cannot use this command with this item.");
        }        
    }

    /**
     * Method for 'ingest' command.
     * Adds/Deducts to/from healthBar of the player/dragon.
     * @param command The player's command.
     */
    public void ingestCommand(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know which item to ingest...
            System.out.println("Which potion/food?");
            return;
        }
        if(command.hasThirdWord()){
            System.out.println("That is not a valid command. Use the 'ingest (item)' command.");
            return;
        }
        String secondWord = command.getSecondWord();
        Item item = player.getItemInInventory(secondWord);
        if(!player.inventoryContainsItem(item)){
            System.out.println("You do not have this item in your inventory.");
            return;
        }

        if(secondWord.equals("healingPotion")){
            player.addToHealthBar(item.getHealthChangePoints());
        }
        else if(secondWord.equals("magicMushroom")){
            player.addToHealthBar(item.getHealthChangePoints());
        }
        else{
            System.out.println("You cannot use this command with this item.");
            return;
        }  
        player.removeFromInventory(item);
        player.listInventory();
        System.out.println("Inventory weight: " + player.getInventoryWeight());

        System.out.println("");
        System.out.println("Your healthBar: " + player.getHealthBar());
    }

    /**
     * Inflicts damage onto adversary.
     * @param command The user's command.
     * @return Returns true if game is lost and false otherwise.
     */
    public boolean doDamageToAdversary(Command command){
        if(!command.hasSecondWord()) {
            System.out.println("Who are you attacking?");
            return false;
        }
        if(!command.hasThirdWord()){
            System.out.println("Which weapon/potion?");
            return false;
        }
        String weaponString = command.getThirdWord();
        Item weapon = player.getItemInInventory(weaponString);
        currentAdversary = currentRoom.getCharacter(command.getSecondWord());        

        if(weapon == null){
            System.out.println("This weapon/potion is not in your inventory.");
            return false;
        }
        if(currentAdversary == null){
            System.out.println("You have no one to fight at the moment.");
            return false;
        }

        if(currentAdversary.getIsAttackable() == false){
            System.out.println("You can not attack " + currentAdversary.getCharacterName() + "!");
            return false;
        }
        if(weapon.getIsWeapon() == false){
            System.out.println("This item is not a weapon.");
            return false;
        }

        currentAdversary.deductFromHealthBar(weapon.getHealthChangePoints());
        if(weapon.getIsConsumable()){
            player.removeFromInventory(weapon);
            player.listInventory();
        }

        if(currentAdversary.getHealthBar() > 0){
            System.out.println(currentAdversary.getCharacterName() + " health bar: " + currentAdversary.getHealthBar());
            //after doing damage, if the adversary is alive, it will fight back
            boolean gameIsLost = takeDamageFromAdversary(command);
            if(gameIsLost == true){
                currentAdversary = null;
                return gameIsLost;
            }
        }
        else{
            dialoguePrinter.printAdversaryDefeat(currentAdversary);
            fightAftermathDragon();
            currentAdversary = null;
        }
        return false;
    }

    /**
     * Implementation for the 'answer' command.
     * @param command The player's command
     * @param currentRoom The room in which the player is in at the moment.
     */
    public void answerCommand(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know the actual answer...
            System.out.println("What is your answer?");
            return;
        }   
        if(command.hasThirdWord()){
            System.out.println("This is not a valid command. Use only two words.");
            return;
        }

        String secondWord = command.getSecondWord();
        if(currentRoom.getRoomName().equals("sphinxRoom")){
            roomTasks.answerForSphinxRoom(secondWord);
        }
        else{
            System.out.println("You have nothing to answer at the moment.");
        }
    }

    //commands related with going to/from rooms

    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * @param command The command which contains the direction
     * where the player wants to go.
     * @return gameIsWon Returns true if game is won and false otherwise.
     */
    public boolean goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return false;
        }
        if(command.hasThirdWord()){
            System.out.println("This is not a valid command. Use the 'go (direction)' command.");
            return false;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        boolean gameIsWon = false;

        if (nextRoom == null) {
            System.out.println("There is no door!");
            return false;
        }
        if(nextRoom.getLockedStatus() == true){
            System.out.println("Oops! It seems like this room is off limits at the moment!");            
            return false;
        }

        previousRoom = currentRoom;
        currentRoom = nextRoom;

        characterMovement.moveCharacters(currentRoom, previousRoom);

        System.out.println(currentRoom.getLongDescription());
        currentRoom.listCharacters();
        if(currentRoom.hasItems() == true){
            currentRoom.listItems(player);
        }

        if(currentRoom.getRoomName().equals("sphinxRoom")){
            if(currentRoom.getTaskCompletion() == false){
                dialoguePrinter.printSphinxRoomTask();                        
            }
        }
        else if(currentRoom.getRoomName().equals("guardRoom")){
            if(currentRoom.getTaskCompletion() == false){   
                dialoguePrinter.printGuardRoomTask();
                currentRoom.setTaskCompletion(true);
            }
        }
        else if(currentRoom.getRoomName().equals("goldRoom")){
            roomTasks.goldRoomTask();
        }
        else if(currentRoom.getRoomName().equals("transporterRoom")){
            gameIsWon = roomTasks.transportToRandomRoom(currentRoom, previousRoom);
            currentRoom = roomTasks.getCurrentRoom();
            previousRoom = roomTasks.getPreviousRoom();
        }
        else if(currentRoom.getRoomName().equals("dungeon")){
            if(currentRoom.getTaskCompletion() == false){
                dialoguePrinter.printDungeonTask();
                currentRoom.setTaskCompletion(true);
            }
        }
        else if(currentRoom.getRoomName().equals("diamondRoom")){
            roomTasks.diamondRoomTask();
        }
        else if(currentRoom.getRoomName().equals("royaltyRoom")){
            roomTasks.royaltyRoomTask();
        }
        else if(currentRoom.getRoomName().equals("courtyard")){
            gameIsWon = roomTasks.courtyardFinalTask();
        }
        return gameIsWon;
    }

    /**
     * Takes player to the last room they have been in.
     * @param command The command from the player.
     */
    public void goBack(Command command){
        if(command.hasSecondWord()){
            System.out.println("This is not a valid command. Use the 'goBack' command.");
            return;
        }
        //if there is no previous room, the user is prevented from going back
        if(previousRoom == null){
            System.out.println("You have nowhere to go back to.");
            return;
        }
        //if the previous room is the transporter, the user is prevented from going back
        if(previousRoom.getRoomName().equals("transporterRoom")){  
            System.out.println("You can not get back to the transporter!");
            return;
        }

        Room copyRoom = currentRoom;
        currentRoom = previousRoom;
        previousRoom = copyRoom;

        characterMovement.moveCharacters(currentRoom, previousRoom);
        System.out.println(currentRoom.getLongDescription());
        currentRoom.listCharacters();
        if(currentRoom.hasItems() == true){
            currentRoom.listItems(player);
        }
    }

    //private methods which are called by the other methods
    /**
     * Takes damage from dragon and checks to see if player died.
     * @param command Command of the user.
     * @return True if the game is lost and false otherwise.
     */
    private boolean takeDamageFromAdversary(Command command){
        player.deductFromHealthBar(currentAdversary.getAttackPoints());
        if(player.getHealthBar() > 0){
            System.out.println("Your health bar: " + player.getHealthBar());
            return false;
        }
        else{
            System.out.println("Oh, no! You died!");
            System.out.println("(×﹏×) GAME OVER (×﹏×)");
            return true;
        }
    }

    /**
     * Sets the aftermath of the fight with the dragon.
     * @param currentRoom The room the player is in at the moment.
     */
    private void fightAftermathDragon(){
        if(currentAdversary.getCharacterName().equals("dragon")){
            currentRoom.removeCharacter(currentAdversary);
            currentRoom.getExit("east").setLockedStatus(false);
        }
        currentAdversary = null;
    }
}
