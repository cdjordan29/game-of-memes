package javagame;
import java.util.*;

/**
 * Hand class represents each player's hand in Project 5
 * @author Michael Taylor, Daniel Jordan
 * @version 1.0
 */

public class Hand {

	/**
	 * Instance variables for Hand class
	 */
	private ArrayList<Card> hand;
	private int max, cardCount;
	private Card selectedCard;
	private Deck deck;

	/**
	 * Constructor for Hand class
	 * 
	 * @param deck the deck you will be drawing cards from.
	 *            
	 */
	public Hand(Deck deck) {
		this.max = 7;
		this.hand = new ArrayList<Card>();
		this.deck = deck;

	}

	/**
	 * place allows the Player to place a Card on the Grid
	 */
	public void place(Card c) {
		c = this.selectedCard;

	}

	/**
	 * drawCard allows the Player to draw a Card from the Deck
	 */
	public void drawCard() {
		Card drawnCard = deck.drawCard();
		hand.add(drawnCard);
		cardCount++;
	}

	/**
	 * drawFirstHand draws cards until you hit your max card count.
	 */
	public void drawFirstHand() {
		for (int i = 0; i < this.max; i++) {
			drawCard();
		}
	}

	/**
	 * checkHandLimit checks the Hand for the maximum number of Cards allowed
	 */
	public boolean checkHandLimit() {

		boolean limit = false;

		return limit;
	}

	/**
	 * Gets the card count
	 * 
	 * @return number of cards in the Hand.
	 */
	public int getCardcount() {
		return this.cardCount;
	}

	/**
	 * Converts the cards in hand to a readable string (for debug mainly)
	 */
	public String toString() {
		String result = "Your Hand: ";

		for (Card c : hand)
			result += c.toString() + ", ";

		return result;
	}

	public ArrayList<Card> getHandList() {
		return this.hand;
	}
}
