package javagame;
import org.newdawn.slick.Image;

/**
 * Hero class represents the heroes for each player in Project 5
 * @author Michael Taylor, Daniel Jordan
 * @version 1.0 
 */
public class Hero {

	/**
	 * Instance variables for Hero class
	 */
	private String heroName;
	private int health;
	private boolean hasBeenAttacked;
	private Image heroToken;
	
	/**
	 * Constructor for the Hero class
	 */
	public Hero(Image heroToken, String heroName){
		this.health = 30;
		this.heroToken = heroToken;
		this.heroName = heroName;
		this.setHasBeenAttacked(false);
		System.out.println("Hero Name: " + heroName);
	}
	
	/**
	 * getHealth returns the health value of the Hero
	 */
	public int getHealth(){
		return this.health;
	}
	
	/**
	 * Gets the string representation of the player's hero
	 * @return heroName the name of the player's hero
	 */
	public String getHeroName(){
		return this.heroName;
	}
	
	/**
	 * damageHealth removes health of the Hero
	 * @param int h the amount of health being removed
	 */
	public void damageHealth(int h){
		this.health -= h;
	}
	
	/**
	 * validateMove validates the selected move of the Hero
	 */
	public boolean validateMove(){
		 
		boolean valid = false;
		
		return valid;
	}
	
	/**
	 * Checks if the cell is occupied
	 * @return valid if cell is occupied
	 */
	public boolean isOccupied(){
		
		boolean valid = false;
		
		return valid;
	}
	
	/**
	 * Draws the hero token on the x and y coordinates
	 * @param x the x screen coordinate
	 * @param y the y screen coordinate
	 */
	public void drawHeroToken(int x, int y){
		this.heroToken.draw(x, y, .275f);
	}

	/**
	 * Checks if hero has been attacked
	 * @return hasBeenAttacked if hero has been attacked
	 */
	public boolean getHasBeenAttacked() {
		return hasBeenAttacked;
	}

	/**
	 * Sets hasBeenAttacked to true
	 * @param hasBeenAttacked the boolean being changed.
	 */
	public void setHasBeenAttacked(boolean hasBeenAttacked) {
		this.hasBeenAttacked = hasBeenAttacked;
	}
}
