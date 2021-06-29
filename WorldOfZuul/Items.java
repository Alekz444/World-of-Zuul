import java.util.HashMap;
/**
 * This class creates and stores all of the items in the game.
 *
 * @author Alexandra-Maria Anastase (k-number: 20022789)
 * @version 30.11.2020
 */
public class Items
{
    private HashMap <String, Item> items;

    /**
     * Constructor for objects of class Items.
     * Initialises items and puts them in the HashMap items
     * @param HashMap of items from Game class
     */
    public Items(HashMap<String, Item> items)
    {
        this.items = items;
        createItems();
    }

    /**
     * Creates items and stores them in the HashMap 'items'.
     */
    private void createItems(){
        //creates items
        Item bronzeKey = new Item("bronzeKey", 1, false, 0, "You will need this to get into the dungeon.", 
                "Use the 'take bronzeKey' command.");
        Item silverKey = new Item("silverKey", 1, true, 0, "You will need this to get into the dungeon.", 
                "Use the 'take silverKey' command.");
        Item goldKey = new Item("goldKey", 1, true, 0, "You will need this to get into the dungeon."
            , "Use the 'take goldKey' command.");
        Item diamondKey = new Item("diamondKey", 1, true, 0, "You can get into the Royalty room with it."
            , "Use the 'take diamondKey' or 'use diamondKey' command.");
        Item sword = new Item("sword", 2, true, 25, "Your opponent's health will drop by 25 points."
            , "Use the 'take sword' or 'attack with sword' command.");
        Item axe = new Item("axe", 2, false, 10, "Your opponent's health will drop by 10 points every time you attack."
            , "Use the 'take axe' or 'attack with axe' command.");
        Item spear = new Item("spear", 2, true, 15, "Your opponent's health will decrease by 15 points every time you attack."
            , "Use the 'take spear' or 'attack with spear' command.");        
        Item healingPotion = new Item("healingPotion", 1, true, 30, "You can restore your health by 30 points."
            , "Use the 'take healingPotion' or 'ingest healingPotion'");
        Item poisonPotion = new Item("poisonPotion", 1, true, 35, "Your opponent's health will drop down by 35 one time."
            , "Use the 'take randomPotion' or 'attack (adversary) randomPotion' command");
        Item randomPotion = new Item("randomPotion", 1, false, 50, "Surprise.", 
                "Try me. Use the 'take randomPotion' or 'ingest randomPotion'.");
        Item magicMushroom = new Item("magicMushroom", 1, true, 20
            , "You can restore your health by 20 points.", "Use 'take magicMushroom' or 'ingest magicMushroom' command.");

        //set weapons
        axe.setIsWeapon(true);
        sword.setIsWeapon(true);
        spear.setIsWeapon(true);
        poisonPotion.setIsWeapon(true);

        //set consumable items
        healingPotion.setIsConsumable(true);
        randomPotion.setIsConsumable(true);
        poisonPotion.setIsConsumable(true);
        magicMushroom.setIsConsumable(true);

        //add items to HashMap 'items'
        items.put("bronzeKey", bronzeKey);
        items.put("silverKey", silverKey);
        items.put("goldKey", goldKey);
        items.put("diamondKey",diamondKey);
        items.put("sword", sword);
        items.put("axe", axe);
        items.put("spear", spear);
        items.put("healingPotion", healingPotion);
        items.put("poisonPotion", poisonPotion);
        items.put("randomPotion", randomPotion);
        items.put("magicMushroom", magicMushroom);
    }

    /**
     * Returns the HashMap 'items'.
     */
    public HashMap<String, Item> getItemHashMap(){
        return items;
    }

    /**
     * Returns an item based on its name.
     * @param itemName The name of the item we need.
     */
    public Item getItemByName(String itemName){
        return items.get(itemName);
    }
}
