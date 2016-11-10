/**
 * When executed it will initiate interaction with NPC's.
 * 
 * @author Kaylee payne
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
       this.target = target; 
    }
    
    /**
     * Will call on the target NPC interact() method to return the proper response.
     * 
     * @return answer The response taken from the target NPC conversation list in interact().
     */
    public String execute() {
        
    }
}
