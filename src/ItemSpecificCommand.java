import java.util.regex.Pattern;

/**
 * @author Jacques Troussard
 *
 */
class ItemSpecificCommand extends Command {

    private String verb;
    private String noun;
                        

    /**
     * Command structure takes in parsed user input from CommandFactory and assigns 
     * instance variables; verb and noun.  
     * 
     * @param verb
     * @param noun
     */
    ItemSpecificCommand(String verb, String noun) {
    	this.verb = verb;
        this.noun = noun;
    }

    /**
     * After confirming target Item object(noun) exists within the vicinity of the player, the
     * message/event bank for that item is accessed by the verb and the appropriate
     * events are triggered. Return string is printed to the console.
     * 
     * @return item/event message to be printed to console
     */
    public String execute() {
        
        Item itemReferredTo = null;
        try {
            itemReferredTo = GameState.instance().getItemInVicinityNamed(noun);
        } catch (Item.NoItemException e) {
            return "There's no " + noun + " here.";
        }
        
        String msg = itemReferredTo.getMessageForVerb(verb);
        return (msg == null ? 
            "Sorry, you can't " + verb + " the " + noun + "." : msg) + "\n";
    }
}
