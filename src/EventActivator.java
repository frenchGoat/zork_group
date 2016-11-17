import java.io.FileNotFoundException;
import java.util.regex.Pattern;

/**
 * University of Mary Washington
 * CPSC 240 Section 2
 */

/**
 * @author Jacques Troussard
 *
 */
public class EventActivator {

	String eventDetails;
	Event manualEvent;

	public EventActivator() {
	}

	public EventActivator(String eventDetails) {
		this.eventDetails = eventDetails;
	}

	public EventActivator(Event manualEvent) {
		this.manualEvent = manualEvent;
	}
	
	public void setManualEvent(Event e) {
		this.manualEvent = e;
	}

	public void activate() throws Item.NoItemException {
		String[] eventBuffer = eventDetails.split(Pattern.quote(","));
		if (manualEvent != null) {
			EventFactory.instance().parse(manualEvent.getDescription()).trigger();
		} else if (eventBuffer.length == 1) {
			// EventFactory.instance().parse(eventDetails).trigger();
			System.out.println(eventDetails);
		} else {
			int numOfEvents = eventBuffer.length;
			int eventIndex = 0;
			for (int i = numOfEvents; i > 0; i--) {
				// EventFactory.instance().parse(eventBuffer[eventIndex]).trigger();
				System.out.println(eventBuffer[eventIndex]);
				eventIndex++;
			}
		}
	}

	/**
	 * For testing purposes only.
	 * 
	 * @param args
	 * @throws Item.NoItemException
	 * @throws Dungeon.IllegalDungeonFormatException
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args)
			throws Item.NoItemException, FileNotFoundException, Dungeon.IllegalDungeonFormatException {
		// Master event activator
		EventActivator ac = new EventActivator();

		// Test dungeon(map)
		Dungeon d = new Dungeon("trinkle.bork", true);
		GameState.instance().setAdventurersCurrentRoom(d.getEntry());

		// Events to test
		String teleportDesc = "Teleport()";
		Event teleportEvent = new TeleportEvent();

		// Activation
		ac.setEventDetails(teleportDesc);
		ac.activate();
		ac.setManualEvent(teleportEvent);
		ac.activate();

		// Print Statements and log testing

//		String singleEvent0 = "Print()";
//		String singleEvent1 = "Print(999)";
//
//		String multiEvent0 = "Print(-10),Print()";
//		String multiEvent1 = "Print(),Print(),Print()";
//
//		EventActivator ac = new EventActivator(singleEvent0);
//		ac.activate();
//		ac.setEventDetails(singleEvent1);
//		ac.activate();
//		ac.setEventDetails(multiEvent0);
//		ac.activate();
//		ac.setEventDetails(multiEvent1);
//		ac.activate();
//
//		String die = "Die";
//		String trs = "Transform(\"squishedCan\")";
//
//		String[] split;
//
//		split = die.split(Pattern.quote("("));
//		for (int i = split.length; i > 0; i--) {
//			System.out.println(split[i - 1]);
//		}
//		System.out.println(split[1]);
//		split = trs.split(Pattern.quote("("));
//		for (int i = split.length; i > 0; i--) {
//			System.out.println(split[i - 1]);
//		}

	}

	/**
	 * For testing purposes only
	 * 
	 * @param eventDetails
	 *            for testing purposes only
	 */
	public void setEventDetails(String eventDetails) {
		this.eventDetails = eventDetails;
	}

}
