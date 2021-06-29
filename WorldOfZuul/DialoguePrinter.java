
/**
 * DialoguePrinter - class consisting of methods which only print specific dialogue.
 *
 * @author Alexandra-Maria Anastase (k-number: 20022789)
 * @version 30.11.2020
 */
public class DialoguePrinter
{

    /**
     * Constructor for objects of class DialoguePrinter
     */
    public DialoguePrinter()
    { 

    }

    /**
     * Print out the opening message for the player.
     * @param room The room in which the player is located.
     */
    public void printWelcome(Room room)
    {
        System.out.println("");
        System.out.println("Welcome to the World of Zuul, hero!");
        System.out.println("Hero! The Prince of Zuul has been abducted and is in great danger!");
        System.out.println("Your quest is to save the Prince of Zuul!");
        System.out.println("Remember: To get where you need to be, three small things are the key!");
        System.out.println("First of all, explore your surroundings and find the items you need.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(room.getLongDescription());     
    }

    /**
     * Prints the sphinx's task.
     */
    public void printSphinxRoomTask(){
        System.out.println("Sphinx: Greetings, I am the Sphinx. ଲ(ⓛ ω ⓛ)ଲ");
        System.out.println("Sphinx: In order to gain access to the bronzeKey, you must answer my riddle first.(=`ω´=)");
        System.out.println("Sphinx: Which creature walks on four legs in the morning two legs in the afternoon and three legs in the evening? ╮(￣ω￣;)╭");
        System.out.println("Sphinx: You can answer using the 'answer (response)' command. Only one word is permitted as a response. ┐(￣ヘ￣;)┌");
    }

    /**
     * Prints the dungeon task.
     */
    public void printDungeonTask(){
        System.out.println("Spy: Oh no! It seems that a mighty dragon is blocking our way! 〣( ºΔº )〣");
        System.out.println("Spy: You will have to fight your way out of this! Gyaa! Protect me! ..・ヾ(。＞＜)シ");
        System.out.println("Spy: You do your thing... I'll just be here...  |ʘ‿ʘ)╯");
        System.out.println("To fight, you need to use the 'attack (adversary) (weapon)' command.");
        System.out.println("Key: The dragon attacks you only after you attack him!");
    }

    /**
     * Print the guardRoom's task.
     */
    public void printGuardRoomTask(){
        System.out.println("Spy: Hi! I am Spy. (^０^)ノ");
        System.out.println("Spy: I will be joining you on your quest. ヽ(*⌒▽⌒*)ﾉ");
    }

    /**
     * Prints the diamond room's task.
     */
    public void printDiamondRoomTask(){
        System.out.println("Spy: Good grief. We're safe now! (⌒‿⌒)");
        System.out.println("You need to use the 'use (key)' command here to unlock the door.");
        System.out.println("Spy: Our prince is held captive in the next room! Let's goooo ヽ(￣д￣;)ノ=3=3=3");
    }

    /**
     * Prints the dialogue after the game is won.
     */
    public void printGameWon(){
        System.out.println("Spy: Good job! We are ooouuut (╯✧▽✧)╯");
        System.out.println("Prince: ☆*:.｡.o(≧▽≦)o.｡.:*☆ Thank you for saving me! ☆⌒ヽ(*'､^*)chu");
        System.out.println("Prince: Let's meet again, hero! (^ω~)");
        System.out.println("");
        System.out.println("    CONGRATULATIONS");
        System.out.println("٩(｡•́‿•̀｡)۶  GAME WON ٩(｡•́‿•̀｡)۶");          
    }

    /**
     * Prints the dialogue of defeating an adversary.
     */
    public void printAdversaryDefeat(Character adversary){
        if(adversary.getCharacterName().equals("dragon")){
            printDragonDefeatDialogue();   //prints the dialogue for defeating the dragon
        }
    }

    /**
     * Prints the royaltyRoom's task.
     */
    public void printRoyaltyRoomTask(){
        System.out.println("Spy: Hooray! We made it! o(≧▽≦)o");
        System.out.println("Spy: We are here, your highness! m(_ _)m");
        System.out.println("");
        System.out.println("Prince: Oh! You are here! Come, come ☆⌒(≧▽​° )");
        System.out.println("Prince:  Σ>―(〃°ω°〃)-→ Thank you for saving me! ⊂( ´ ▽ ` )⊃");
        System.out.println("( ˘⌣˘)♡(˘⌣˘ )");
        System.out.println("");
        System.out.println("Spy: Ok, ok now. ( ◡‿◡ *) We should go now.");
        System.out.println("Spy: The next room from here is the Transporter.");
        System.out.println("Prince: We need to get to the courtyard, my hero! (￣З￣)");
    }

    /**
     * Prints dialogue for the dragon's defeat.
     */
    public void printDragonDefeatDialogue(){
        System.out.println("Spy: Wow! Well done for defeating the dragon! ＼(＾▽＾)／");
        System.out.println("Spy: Thanks for protecting me, as well  (*￣▽￣)b.");
        System.out.println("Spy: Now, we can go save the prince! (ﾉ´ヮ`)ﾉ*: ･ﾟ");
    }
}
