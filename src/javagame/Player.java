
package javagame;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Player class represents each player in Project 5
 * @author Michael Taylor, Daniel Jordan
 * @version 1.0
 */
public class Player {

	/**
	 * Instance variables for Player class
	 */
	private String name;
	private Deck deck;
	private Hand hand;
	private String heroName;
	private Hero hero;
	private Image handArea;
	private Image cardBack;
	private Image deckImage;
	private Image heroToken;
	private Boolean heroTokenPlaced;
	private int playerMana;

	/**
	 * Constructor for the Player class
	 * 
	 * @throws SlickException
	 */
	public Player(String name, String heroName, int playerNum) throws SlickException {
		this.name = name;
		this.heroName = heroName;
		this.deck = new Deck(heroName);
		heroTokenPlaced = false;
		// this.deck.shuffle();
		this.hand = new Hand(this.deck);
		this.heroName = heroName;
		this.handArea = new Image("res/Game_Images/handArea.png");
		this.playerMana = 1;
		
		
		if(heroName.equals("thief")){
			cardBack = new Image("res/Thief_Deck/cardBack.png");
			deckImage = new Image("res/Thief_Deck/deck.png");
			heroToken = new Image("res/Thief_Deck/herotoken.png");
		}
		else if(heroName.equals("wizard")){
			cardBack = new Image("res/Wizard_Deck/cardBack.png");
			deckImage = new Image("res/Wizard_Deck/deck.png");
			heroToken = new Image("res/Wizard_Deck/herotoken.png");
		}
		else if(heroName.equals("warrior")){
			cardBack = new Image("res/Warrior_Deck/cardBack.png");
			deckImage = new Image("res/Warrior_Deck/deck.png");
			heroToken = new Image("res/Warrior_Deck/herotoken.png");
		}
		
		this.hero = new Hero(heroToken, heroName);
	}

	/**
	 * getName returns the name of the Player
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets hero name in string form
	 * @return hero's name
	 */
	public String getHeroName(){
		return heroName;
	}

	/**
	 * setName sets the name for the Player
	 * 
	 * @param s the name of the player
	 */
	public void setName(String s) {
		this.name = s;
	}

	/**
	 * getDeck returns the Deck for the Player
	 * 
	 * @return deck the deck the player is using
	 */
	public Deck getDeck() {
		return deck;
	}

	/**
	 * setDeck sets the Deck for the Player
	 * 
	 * @param deck the deck the player will use
	 */
	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	/**
	 * getHand returns the Hand for the Player
	 * 
	 * @return hand the cards in player's hand
	 */
	public Hand getHand() {
		return hand;
	}

	/**
	 * Draws the initial cards in the hand and draws the image on screen
	 */
	public void drawHand() {
		int x;
		int y;

		x = 0;
		y = 867;
		for (Card c : getHand().getHandList()) {
			c.getImage().draw(x, y, .235f);
			x += c.getImage().getWidth() * .235;
		}

	}
	
	/**
	 * gets the cardback of the player
	 * @return cardback of the player
	 */
	public Image getCardBack(){
		return this.cardBack;
	}
	
	/**
	 * Draws cardbacks on screen
	 */
	public void drawCardBacks(){
		this.handArea.draw(0,0);
		int x;
		int y;

		x = 0;
		y = 0;
		for (@SuppressWarnings("unused") Card c : getHand().getHandList()) {
			cardBack.draw(x, y, .235f);
			x += cardBack.getWidth() * .235;
		}
	}
	
	/**
	 * Gets the art that represents the deck
	 * @return an image of the deck
	 */
	public Image getDeckImage(){
		return this.deckImage;
	}

	/**
	 * setHand sets the Hand for the Player
	 * 
	 * @param hand the player's hand
	 */
	public void setHand(Hand hand) {
		this.hand = hand;
	}

	/**
	 * getHero returns the Hero for the Player
	 * 
	 * @return hero the player's hero
	 */
	public Hero getHero() {
		return hero;
	}

	/**
	 * Draws the empty cards in the card area
	 */
	public void drawHandArea() {
		this.handArea.draw(0, 868);
	}
	
	/**
	 * Sets heroTokenPlaced to true, which means the token is on the board
	 */
	public void placeHeroToken(){
		this.heroTokenPlaced = true;
	}
	
	/**
	 * Gets if the hero token has been placed
	 * @return heroTokenPlaced if the token has been placed on the board
	 */
	public Boolean isheroTokenPlaced(){
		return this.heroTokenPlaced;
	}
	
	/**
	 * setHero sets the Hero for the Player
	 * 
	 * @param hero the player's hero
	 */
	public void setHero(Hero hero) {
		this.hero = hero;
	}

	/**
	 * Gets the string representation of the player's hand
	 * @return string representation of the player's hand
	 */
	public String handToString() {
		return this.hand.toString();
	}
	
	/**
	 * Gets the string representation of the player's deck
	 * @return string representation of the player's deck
	 */
	public String deckToString() {
		return this.deck.toString();
	}
	
	/**
	 * Increases player's mana by one
	 */
	public void incrementPlayerMana(){
		this.playerMana++;
	}
	
	/**
	 * Gets the player's mana
	 * @return the player's mana count
	 */
	public int getPlayerMana(){
		return this.playerMana;
	}

}
