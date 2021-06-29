import java.util.ArrayList;
import java.util.Random;
/**
 * Class Character - a character in the adventure game.
 *
 * @author Alexandra-Maria Anastase (k-number: 20022789)
 * @version 30.11.2020
 */
public class Character
{
    private String characterName;
    private int healthBar;
    private String greetingDialogue;
    private int attackPoints;
    private boolean isAttackable = false;

    /**
     * Constructor for objects of class Character
     * @param characterName The name of the character
     * @param healthBar The value of the healthBar of the character
     */
    public Character(String characterName, int healthBar)
    {
        this.characterName = characterName;
        greetingDialogue = new String();
        this.healthBar = healthBar;
    }

    /**
     * Sets the name of the character.
     * @param name the string represents the name that the character should be set to.
     */
    public void setCharacterName(String name){
        characterName = name;
    }

    /**
     * Returns the name of the character.
     */
    public String getCharacterName(){
        return characterName;
    }

    /**
     * Returns health bar of the character.
     */
    public int getHealthBar(){
        return healthBar;
    }

    /**
     * Deducts from health bar.
     * @param points The number of points that should be deducted from the health bar.
     */
    public void deductFromHealthBar(int points){
        healthBar = healthBar - points;
        if(healthBar < 0){
            healthBar = 0;
        }
    }

    /**
     * Adds points to health bar.
     * @param points The number of points that should be added to the health bar.
     */
    public void addToHealthBar(int points){
        healthBar = healthBar + points;
        if(healthBar > 100){
            healthBar = 100;
        }
    }

    /**
     * Sets the attack points of the character.
     * @param points The value the attackpoints should be set to.
     */
    public void setAttackPoints(int points){
        attackPoints = points;
    }

    /**
     * Returns the attack points of the character.
     */
    public int getAttackPoints(){
        return attackPoints;
    }

    /**
     * Sets whether the character can be attacked.
     * @param attackable The value the isAttackable should be set to
     */
    public void setIsAttackable(boolean attackable){
        isAttackable = attackable;
    }

    /**
     * Returns whether the character can be attacked.
     * @return isAttackable True if the character can be attacked and
     * false otherwise.
     */
    public boolean getIsAttackable(){
        return isAttackable;
    }
}
