
/**
 * This class is responsible for the movement of all characters.
 * It utilizes an object of class Characters so it can accesss the
 * method for moving the characters.
 *
 * @author Alexandra-Maria Anastase (k-number: 20022789)
 * @version 02.12.2020
 */
public class CharacterMovement
{
    private Characters charactersObject;

    /**
     * Constructor for objects of class CharacterMovement
     */
    public CharacterMovement(Characters charactersObject)
    {
        this.charactersObject = charactersObject;
    }

    /**
     * Moves characters from one room to another.
     * @param currentRoom The room the character needs to be moved to.
     * @param previousRoom The room the character was in.
     */
    public void moveCharacters(Room currentRoom, Room previousRoom){
        //when the player moves, these characters move with him
        if(previousRoom.containsCharacter(charactersObject.getCharacterByName("spy"))){     
            previousRoom.moveCharacter(charactersObject.getCharacterByName("spy"), currentRoom);
        }

        if(previousRoom.containsCharacter(charactersObject.getCharacterByName("prince"))){     
            previousRoom.moveCharacter(charactersObject.getCharacterByName("prince"), currentRoom);
        }
    }
}
