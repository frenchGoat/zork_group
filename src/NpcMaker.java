import java.util.regex.Pattern;
import java.util.Scanner;

public class NpcMaker {

	public NpcMaker() {

	}

	public NPC makeNpc(Scanner sc) {
		String subclass = sc.nextLine();
		switch (subclass) {
		case "Villager":
			return new Villager(sc);
		case "Vendor":
			return new Vendor(sc);
		case "Mob":
			return new Mob(sc);
		default:
			return null;
		}
	}
}
