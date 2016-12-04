import java.io.*;
/**
 * University of Mary Washington
 * CPSC 240 Section 2
 * 
 */
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
    }

    /**
     * Activates the DieEvent.
     * 
     * @return message The end of game message
     */
    @Override
    void trigger() {
        GameState instance = GameState.instance();
        try {
            instance.restore(instance.DEFAULT_SAVE_FILE + instance.SAVE_FILE_EXTENSION);
        } catch (IOException | GameState.IllegalSaveFormatException | Dungeon.IllegalDungeonFormatException e) {}
        System.out.println("You have died :(  You have respawned at your last save point.");
        System.out.println(instance.getAdventurersCurrentRoom().getTitle());
        return message;
    }

}

