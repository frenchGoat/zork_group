import Item.NoItemException;

/**
 * When executed it will initiate interaction with NPC's.
 * 
 * @author Kaylee payne
 * @author Jacques Troussard
 */
public class TalkCommand extends Command {
	
    /**
     * A string that is the name of the NPC the user wants to interact with.
     */
    private String target;
    
    /**
     * Creates a TalkCommand for the given NPC.
     * 
     * @param target The string name of an NPC.
     */
    TalkCommand(String target) {
       this.target = target.substring(3);
    }
    
    /**
     * Will call on the target NPC interact() method to return the proper response.
     * 
     * @return answer The response taken from the target NPC conversation list in interact().
     */
    public String execute() {
    	if (target == null || target.trim().length() == 0) {
            return "Talk to who?\n";
        }
    	Room currentRoom = 
                GameState.instance().getAdventurersCurrentRoom();
            try {
				NPC theTarget = currentRoom.getNpcNamed(target);
				theTarget.openDialogue();
			} catch (NPC.NoNPCException | Item.NoItemException e) {
				// TODO Auto-generated catch block
				return target + " isn't here to listen to your ramblings.\n";
			}
            
            return Interpreter.cmdBrd + "\nOk now what?\n";
    }
}
