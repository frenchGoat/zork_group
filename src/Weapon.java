import java.util.Scanner;

/**
 * University of Mary Washington
 * CPSC 240 Section 2
 */

/**
 * @author Jacques Troussard
 * @author Kaylee Payne
 *
 */
public class Weapon extends Item {
	
	/**
	 * Determines the amount of damage that this item deals when used.
	 */
	int strength;

	/**
	 * Given a Scanner object positioned at the beginning of a "weapon" file
	 * entry, read and return a Weapon object representing it. 
	 * 
	 * @param s scanner object received from GameState Instance
	 * @throws NoItemException
	 * @throws Dungeon.IllegalDungeonFormatException
	 */
	public Weapon(Scanner s) throws NoItemException,
			Dungeon.IllegalDungeonFormatException {
		super(s);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Takes in weight and strength parameters and assigns them to their respective 
	 * instance variables.
	 * 
	 * @param s determines the amount of damage dealt
	 * @param w determines the amount of shape this takes in playerCap variable
	 * (GameState)
	 */
	public Weapon(int s, int w) {
		super(w);
		this.strength = s;
		
	}

	/**
	 * Returns the strength of the weapon
	 * 
	 * @return int strength
	 */
	public int getStrength() {
		return strength;
	}
	

}
