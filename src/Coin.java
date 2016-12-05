import java.util.Scanner;

public class Coin extends Item {
	
	String name;
	int value;

	public Coin(String name, int value) {
		this.name = name + " coin";
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Coin(Scanner s) throws NoItemException, Dungeon.IllegalDungeonFormatException {
		super(s);
		// TODO Auto-generated constructor stub
	}

}
