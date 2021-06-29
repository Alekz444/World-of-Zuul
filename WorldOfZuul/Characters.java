import java.util.HashSet;
/**
 * This class creates and stores all of the characters in the game.
 *
 * @author Alexandra-Maria Anastase (k-number: 20022789)
 * @version 30.11.2020
 */
public class Characters
{
    private HashSet<Character> characters;

    /**
     * Constructor for objects of class Characters
     */
    public Characters()
    {
        characters = new HashSet<>();
        createCharacters();
    }

    /**
     * Creates characters and adds them to the HashSet 'characters'.
     */
    private void createCharacters(){
        //creates characters
        Character prince = new Character("prince", 0);
        Character guard = new Character("guard", 0);
        Character sphinx = new Character("sphinx", 0);
        Character dragon = new Character("dragon", 100);
        Character spy = new Character("spy", 0);

        //sets character's isAttackable status
        dragon.setIsAttackable(true);

        //adds characters to HashSet characters
        characters.add(prince);
        characters.add(guard);
        characters.add(sphinx);
        characters.add(dragon);
        characters.add(spy);
    }

    /**
     * Returns the HashSet 'characters'.
     */
    public HashSet<Character> getCharacterHashSet(){
        return characters;
    }

    /**
     * Returns a character based on their name.
     * @param characterName The name of the character we are looking for.
     * @return character The character we are looking for.
     */
    public Character getCharacterByName(String characterName){
        for(Character character : characters){
            if(character.getCharacterName().equals(characterName)){
                return character;
            }
        }
        return null;
    }
}
