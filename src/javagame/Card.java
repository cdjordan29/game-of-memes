package javagame;
import org.newdawn.slick.Image;

/**
 * Card class represents the individual cards in Project 5
 * @author Michael Taylor, Daniel Jordan
 * @version 1.0
 */
public class Card {

	/**
	 * Instance variables for Card class
	 */
	private int row, col, attack, defense, speed;
	private String name, hero;
	private Image cardLarge;

	/**
	 * Initializes variables of card class
	 * @param name Player's name
	 * @param hero Hero type
	 * @param cardLarge The large version of the card art
	 * @param attack card attack value
	 * @param defense card defense value
	 * @param speed card speed value
	 */
	public Card(String name, String hero, Image cardLarge, int attack, int defense, int speed) {
		this.name = name;
		this.hero = hero;
		this.cardLarge = cardLarge;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
	}

	/**
	 * getCell gets the Cell that the Card is in
	 * 
	 * @return Cell c the cell the card is located in
	 */
	public Cell getCell() {

		Cell c = new Cell();

		return c;
	}

	/**
	 * getCardRow returns the current row value of the Card
	 * 
	 * @return int row gets the row value of the card
	 */
	public int getCardRow() {
		return this.row;
	}

	/**
	 * getCardCol returns the current column value of the Card
	 * 
	 * @return int col gets the column value of the card
	 */
	public int getCardCol() {
		return this.col;
	}

	/**
	 * setCardRow sets the row value of the Card
	 */
	public void setCardRow(int r) {
		this.row = r;
	}

	/**
	 * setCardCol sets the column value of the Card
	 */
	public void setCardCol(int c) {
		this.col = c;
	}

	/**
	 * getCardAttack returns the current attack value of the Card
	 * 
	 * @return int attack gets the attack value of the card
	 */
	public int getCardAttack() {
		return this.attack;
	}

	/**
	 * getCardDefense returns the current defense value of the Card
	 * 
	 * @return int defense gets the defense value of the card
	 */
	public int getCardDefense() {
		return this.defense;
	}

	/**
	 * getCardSpeed returns the current speed value of the Card
	 * 
	 * @return int speed gets the speed value of the card
	 */
	public int getCardSpeed() {
		return this.speed;
	}

	/**
	 * setCardAttack sets the attack value of the Card
	 */
	public void setCardAttack(int a) {
		this.attack = a;
	}

	/**
	 * setCardDefense sets the defense value of the Card
	 */
	public void setCardDefense(int d) {
		this.defense = d;
	}

	/**
	 * setCardSpeed sets the speed value of the Card
	 */
	public void setCardSpeed(int s) {
		this.speed = s;
	}

	/**
	 * validateMove validates the move of the Card
	 */
	public boolean validateMove() {

		boolean valid = false;

		return valid;
	}
	
	/**
	 * Gets the hero name
	 * @return name of th ehero
	 */
	public String getHero(){
		return this.hero;
	}

	/**
	 * returns a text version of the card
	 */
	public String toString() {
		return this.name + " " + this.attack + " " + this.defense + " " + this.speed;
	}
	
	/**
	 * gets the Image of the card
	 * @return card's Image
	 */
	public Image getImage() {
		return this.cardLarge;
	}
}
