import java.util.Scanner;

/**
 * @author Jacques Troussard
 *
 */
public class Exit {

    @SuppressWarnings("serial")
	class NoExitException extends Exception {}

    /**
     * Location of exit with respect to its source Room object.
     */
    private String dir;
    /**
     * Room object identifying the source location of the Exit object.
     */
    private Room src;
    /**
     * Room object identifying the linked Room object from the source Room. 
     */
    private Room dest;

    /**
     * Takes a String, and two Room objects to construct an Exit object. After 
     * initialization and assignments, the source Room object's addExit method is called
     * to add the exit into the Room's exit data structure.
     * 
     * @param dir direction of the exit with respect to the source room
     * @param src room object containing the exit
     * @param dest destination room object
     */
    Exit(String dir, Room src, Room dest) {
        init();
        this.dir = dir;
        this.src = src;
        this.dest = dest;
        src.addExit(this);
    }

    /** Given a Scanner object positioned at the beginning of an "exit" file
        entry, read and return an Exit object representing it. 
        @param d The dungeon that contains this exit (so that Room objects 
        may be obtained.)
        @throws NoExitException The reader object is not positioned at the
        start of an exit entry. A side effect of this is the reader's cursor
        is now positioned one line past where it was.
        @throws IllegalDungeonFormatException A structural problem with the
        dungeon file itself, detected when trying to read this room.
     */
    Exit(Scanner s, Dungeon d) throws NoExitException,
        Dungeon.IllegalDungeonFormatException {

        init();
        String srcTitle = s.nextLine();
        if (srcTitle.equals(Dungeon.TOP_LEVEL_DELIM)) {
            throw new NoExitException();
        }
        src = d.getRoom(srcTitle);
        dir = s.nextLine();
        dest = d.getRoom(s.nextLine());
        
        // I'm an Exit object. Great. Add me as an exit to my source Room too,
        // though.
        src.addExit(this);

        // throw away delimiter
        if (!s.nextLine().equals(Dungeon.SECOND_LEVEL_DELIM)) {
            throw new Dungeon.IllegalDungeonFormatException("No '" +
                Dungeon.SECOND_LEVEL_DELIM + "' after exit.");
        }
    }

    // Common object initialization tasks.
    private void init() {
    }

    /**
     * Method used to return a message describing the exit object's dir variable plus the 
     * name variable of the destination Room object.
     * 
     * @return message describing the exits direction and destination
     */
    String describe() {
        return "You can go " + dir + " to " + dest.getTitle() + ".";
    }

    /**
     * Returns the direction variable.
     * 
     * @return exit direction
     */
    String getDir() { return dir; }
    /**
     * Returns the Room which the exit is contained in.
     * 
     * @return exit source room
     */
    Room getSrc() { return src; }
    /**
     * Returns the destination Room of the exit.
     * 
     * @return destination of exit
     */
    Room getDest() { return dest; }
}
