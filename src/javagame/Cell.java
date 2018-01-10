package javagame;

/**
 * Cell class represents the individual cells on the grid in Project 5
 * @author Michael Taylor, Daniel Jordan
 * @version 1.0
 */
public class Cell {

	/**
	 * Instance variables for the Cell class
	 */
	private Card card;
	private Hero hero;
	private int row, col, xLowerBound, xUpperBound, yLowerBound, yUpperBound, xLowerBoundFlipped,
			xUpperBoundFlipped, yLowerBoundFlipped, yUpperBoundFlipped;
	private boolean occupied;
	
	/**
	 * Constructor for the Cell class
	 */
	public Cell() {
		this.xLowerBound = 0;
		this.xUpperBound = 0;
		this.yLowerBound = 0;
		this.yUpperBound = 0;
	}

	/**
	 * getCard returns the Card that's inside the Cell
	 * 
	 * @return Card card that is in the cell
	 */
	public Card getCard() {

		Card result = this.card;
		result = card;
		return result;
	}

	/**
	 * getCellRow returns the value of the Cell's row
	 * 
	 * @return row the row the cell is in
	 */
	public int getCellRow() {
		return this.row;
	}

	/**
	 * getCellCol returns the value of the Cell's column
	 * 
	 * @return col the column the cell is in
	 */
	public int getCellCol() {
		return this.col;
	}

	/**
	 * isOccupied returns the boolean occupied to determine if the Cell is
	 * occupied
	 * 
	 * @return occupied if the cell is occupied
	 */
	public boolean isOccupied() {
		if (card != null)
			occupied = true;
		else
			occupied = false;

		return this.occupied;
	}

	/**
	 * Adds card to the cell
	 * @param c the card being added to the cell
	 */
	public void addCard(Card c) {
		this.card = c;
	}

	/**
	 * removeCard sets the Cell value back to not occupied when a Player moves
	 * their Card out of the Cell
	 */
	public void removeCard() {
		this.card = null;
	}
	
	/**
	 * Adds a hero to the cell
	 * @param h the hero being added to the cell
	 */
	public void addHero(Hero h){
		this.hero = h;
	}
	
	/**
	 * Gets the hero in the cell
	 * @return hero in the cell
	 */
	public Hero getHero(){
		return this.hero;
	}
	
	/**
	 * removes the hero in the cell
	 */
	public void removeHero(){
		this.hero = null;
	}

	/**
	 * Sets the lower x bound of the cell based on screen coordinates
	 * @param x the lower x bound
	 */
	public void setXLowerBound(int x) {
		this.xLowerBound = x;
	}
	
	/**
	 * Gets the x lower bound
	 * @return the x lower bound of the cell
	 */
	public int getXLowerBound() {
		return this.xLowerBound;
	}

	/**
	 * Sets the upper x bound of the cell based on screen coordinates
	 * @param x the upper x bound
	 */
	public void setXUpperBound(int x) {
		this.xUpperBound = x;
	}
	
	/**
	 * Gets the x upper bound
	 * @return the x upper bound of the cell
	 */
	public int getXUpperBound() {
		return this.xUpperBound;
	}
	
	/**
	 * Sets the lower y bound of the cell based on screen coordinates
	 * @param y the lower y bound
	 */
	public void setYLowerBound(int y) {
		this.yLowerBound = y;
	}

	/**
	 * Gets the y lower bound
	 * @return the y lower bound of the cell
	 */
	public int getYLowerBound() {
		return this.yLowerBound;
	}
	
	/**
	 * Gets the y upper bound
	 * @return the y upper bound of the cell
	 */
	public int getYUpperBound() {
		return this.yUpperBound;
	}
	
	/**
	 * Sets the upper y bound fo the cell based on screen coordinates
	 * @param y the upper y bound
	 */
	public void setYUpperBound(int y) {
		this.yUpperBound = y;
	}
	
	/**
	 * gets the string representation of the cell
	 */
	public String toString() {
		return "lower " + this.xLowerBound;
	}
	
	/**
	 * Draws the image of the card in the cell at 18% of its original size
	 */
	public void drawCardImage() {
		card.getImage().draw(this.xLowerBound + 13, this.yLowerBound, .18f);
	}
	
	/**
	 * Draws the image of the card in the cell, when the board is turned, at 18% of its original size
	 */
	public void drawCardImageFlipped(){
		card.getImage().draw(this.xLowerBoundFlipped + 13, this.yLowerBoundFlipped, .18f);
	}
	
	/**
	 * Gets lower x bound of the cell when the board is flipped
	 * @return lower x bound of the cell when the board is flipped
	 */
	public int getXLowerBoundFlipped() {
		return xLowerBoundFlipped;
	}
	
	/**
	 * Sets the lower x bound of the cell when the board is flipped
	 * @param xLowerBoundFlipped the value of x lower bound when flipped
	 */
	public void setXLowerBoundFlipped(int xLowerBoundFlipped) {
		this.xLowerBoundFlipped = xLowerBoundFlipped;
	}

	/**
	 * Gets upper x bound of the cell when the board is flipped
	 * @return upper x bound of the cell when the board is flipped
	 */
	public int getXUpperBoundFlipped() {
		return xUpperBoundFlipped;
	}

	/**
	 * Sets the upper x bound of the cell when the board is flipped
	 * @param xUpperBoundFlipped the value of x upper bound when flipped
	 */
	public void setXUpperBoundFlipped(int xUpperBoundFlipped) {
		this.xUpperBoundFlipped = xUpperBoundFlipped;
	}
	
	/**
	 * Gets lower y bound of the cell when the board is flipped
	 * @return lower y bound of the cell when the board is flipped
	 */
	public int getYLowerBoundFlipped() {
		return yLowerBoundFlipped;
	}
	
	/**
	 * Sets the lower y bound of the cell when the board is flipped
	 * @param yLowerBoundFlipped the value of y lower bound when flipped
	 */
	public void setYLowerBoundFlipped(int yLowerBoundFlipped) {
		this.yLowerBoundFlipped = yLowerBoundFlipped;
	}

	/**
	 * Gets upper y bound of the cell when the board is flipped
	 * @return upper y bound of the cell when the board is flipped
	 */
	public int getYUpperBoundFlipped() {
		return yUpperBoundFlipped;
	}

	/**
	 * Sets the upper y bound of the cell when the board is flipped
	 * @param yUpperBoundFlipped the value of y Upper bound when flipped
	 */
	public void setYUpperBoundFlipped(int yUpperBoundFlipped) {
		this.yUpperBoundFlipped = yUpperBoundFlipped;
	}
}
