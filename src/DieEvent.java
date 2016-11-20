import java.io.*;
/**
 * @author Kaylee Payne
 *
 */
public class DieEvent extends Event {
    private String message = "You have died";
    /**
     * This is an end game trigger, in which the player has lost. Die events can be
     * caused by reaching 0 in either player health or hunger status. This trigger 
     * will reload the game from last restore point.
     * 
     */
    public DieEvent() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Activates the DieEvent.
     * 
     * @return message The end of game message
     */
    @Override
    String trigger() {
        GameState instance = GameState.instance();
        try {
            instance.restore(instance.DEFAULT_SAVE_FILE);
        } catch (IOException | GameState.IllegalSaveFormatException | Dungeon.IllegalDungeonFormatException e) {}
        return message;
    }

}
