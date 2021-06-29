import java.util.HashSet;
/**
 * This class represents the player/user of the game.
 *
 * @author Alexandra-Maria Anastase (k-number: k20022789)
 * @version 30.11.2020
 */
public class Player
{
    private HashSet<Item> inventory;
    private int healthBar = 100;
    private int inventoryWeight = 0;
    private int maximumInventoryWeight;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        inventory = new HashSet<>();
    }

    //inventory methods
    /**
     * Sets the maximum weight of the inventory.
     * @param weight The maximum weight the inventory could be.
     */
    public void setMaximumInventoryWeight(int weight){
        maximumInventoryWeight = weight;
    }

    /**
     * Returns the maximum weight of the inventory.
     */
    public int getMaximumInventoryWeight(){
        return maximumInventoryWeight;
    }

    /**
     * Prints a statement about the maximum weight of the inventory.
     */
    public void listMaximumInventoryWeight(){
        System.out.println("You can only carry " + maximumInventoryWeight + " kg.");
    }

    /**
     * Adds an item to inventory.
     * @param item The item to be added to the inventory.
     */
    public void addToInventory(Item item)
    {
        inventory.add(item);
        inventoryWeight = inventoryWeight + item.getItemWeight();
    }

    /**
     * Removes an item from inventory.
     * @param item The item to be removed.
     */
    public void removeFromInventory(Item item){
        inventory.remove(item);
        inventoryWeight = inventoryWeight - item.getItemWeight();
    }

    /**
     * Returns the weight of the inventory.
     */
    public int getInventoryWeight(){
        return inventoryWeight;
    }

    /**
     * Returns the inventory.
     */
    public HashSet<Item> getInventory(){
        return inventory;
    }

    /**
     * Checks if a particular item is in the inventory.
     * @param item The item that needs the checking.
     * @return Returns true if the item is in the inventory and false if it is not. 
     */
    public boolean inventoryContainsItem(Item item){
        if(inventory.contains(item)){
            return true;
        }
        else return false;
    }

    /**
     * Returns an item from the inventory and null if there is no such item.
     * @param item The name of the item needed.
     */
    public Item getItemInInventory(String item){
        for(Item item0 : inventory){
            if(item0.getItemName().equals(item)){
                return item0;
            }
        }
        return null;
    }

    /**
     * Prints the items which are in the inventory.
     */
    public void listInventory(){
        if(!inventory.isEmpty()){
            System.out.print("Your inventory now contains: ");
            for(Item item : inventory){
                System.out.print(item.getItemName() + " ");
            }
            System.out.println("");
        }
        else{
            System.out.println("There are no items in your inventory.");
        }
    }

    //healthBar methods
    /**
     * Adds points to health bar.
     * @param points The points to be added to healthBar.
     */
    public void addToHealthBar(int points){
        healthBar = healthBar + points;
        if(healthBar > 100){
            healthBar = 100;
        }
    }

    /**
     * Deduct points from health bar.
     * @param points The points to be deducted from healthBar.
     */
    public void deductFromHealthBar(int points){
        healthBar = healthBar - points;
        if(healthBar < 0){
            healthBar = 0;
        }
    }

    /**
     * Returns the healthBar.
     */
    public int getHealthBar(){
        return healthBar;
    }

    /**
     * Sets the healthBar to a new value.
     * @param newHealthBar The value that the healthBar needs to be set to.
     */
    public void setHealthBar(int newHealthBar){
        healthBar = newHealthBar;
    }
}
