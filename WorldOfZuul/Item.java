
/**
 * Class Item - represents an item in the game
 *
 * @author Alexandra-Maria Anastase (k-number: 20022789)
 * @version 30.11.2020
 */
public class Item
{
    private int itemWeight;
    private String itemName;
    private boolean itemAvailability;
    private int healthChangePoints;  
    private String itemDescription;
    private String methodOfUse;
    private String finalDescription;
    private boolean isWeapon = false; 
    private boolean isConsumable = false;

    /**
     * Constructor for objects of class Item
     * @param itemName The name of the item
     * @param itemWeight The weight of the item
     * @param itemAvailability The availability of the item
     * @param healthChangePoints The healthChangePoints of the item
     * @param itemDescription The description of the item
     * @param methodOfUse The method of use of the item
     */
    public Item(String itemName, int itemWeight, boolean itemAvailability,
    int healthChangePoints, String itemDescription, String methodOfUse)
    {
        this.itemName = itemName;
        this.itemWeight = itemWeight;
        this.healthChangePoints = healthChangePoints;
        this.itemAvailability = itemAvailability;   
        this.itemDescription = itemDescription;
        this.methodOfUse = methodOfUse;
    }

    /**
     * Returns the name of the item.
     */
    public String getItemName(){
        return itemName;
    }

    /**
     * Returns the weight of the item.
     */
    public int getItemWeight()
    {
        return itemWeight;
    }

    /**
     * Lists the item and its description.
     */
    public void listItem(){
        System.out.print("This is " + itemName + ". " + itemDescription + " " + methodOfUse + " Weight: " + itemWeight + " kg.");
        System.out.println("");
    }

    /**
     * Returns the availability of the item.
     */
    public boolean getItemAvailability()
    {
        return itemAvailability;
    }

    /**
     * Set item availability to the value of the parameter.
     * @param status - false (not available) or true (available)
     */
    public void setItemAvailability(boolean status){
        itemAvailability =  status;
    }

    /**
     * Returns the health change points of the item.
     */
    public int getHealthChangePoints(){
        return healthChangePoints;
    }

    //methods for types of items
    /**
     * Sets whether the item is a weapon.
     * @param weapon True if it is a weapon false otherwise.
     */
    public void setIsWeapon(boolean weapon){
        isWeapon = weapon;
    }

    /**
     * Returns whether the item is a weapom.
     * @return isWeapon True if it is a weapon, false otherwise
     */
    public boolean getIsWeapon(){
        return isWeapon;
    }

    /**
     * Sets whether the item is consumable (able to be used only once).
     * @param consumable True if is is consumable, false otherwise
     */
    public void setIsConsumable(boolean consumable){
        isConsumable = consumable;
    }

    /**
     * Returns whether the item is consumable.
     * @return isConsumable True if it is consumable, false otherwise
     */
    public boolean getIsConsumable(){
        return isConsumable;
    }
}
