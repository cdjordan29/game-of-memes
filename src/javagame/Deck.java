package javagame;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Deck class represents the deck of cards in Project 5
 * @author Michael Taylor, Daniel Jordan
 * @version 1.0
 */
public class Deck {

	private ArrayList<Card> deck;
	
	/**
	 * Initializes variables of deck class
	 * @param hero the hero of which the deck blongs
	 * @throws SlickException
	 */
	public Deck(String hero) throws SlickException{
		deck = new ArrayList<Card>();
		
		if(hero.equals("thief")){
			try {
				importCards("res/Thief_Deck/thiefDeck.csv");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(hero.equals("wizard")){
			try {
				importCards("res/Wizard_Deck/wizardDeck.csv");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(hero.equals("warrior")){
			try {
				importCards("res/Warrior_Deck/warriorDeck.csv");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * shuffle allows the Player to shuffle the Deck
	 */
	public void shuffle(){
		Random rand = new Random();
		int shuffleTimes = rand.nextInt(10000); //shuffles a random number of times because the Collection.shuffle() method only displaces cards once
		
		for(int i = 0; i < shuffleTimes; i++)
			Collections.shuffle(deck);
	}
	
	/**
	 * getCardCount gets the Card count from the Deck
	 */
	public int getCardCount(){
		return deck.size();
	}
	
	/**
	 * Imports cards to the deck from a text file
	 * @param deckLocation the file location of the text file
	 * @throws FileNotFoundException
	 * @throws SlickException
	 */
	public void importCards(String deckLocation) throws FileNotFoundException, SlickException{
		File deckList = new File(deckLocation); //deckLocation is a link to the text file
		Scanner scan = new Scanner(deckList); //scanner scans the decklist file
		scan.useDelimiter("\\s*,\\s*"); //sets comma as delimeter and ignores whitespace
		System.out.println(deckList); 
		
		while(scan.hasNextLine()) //loops while cards still exist
		{
			String cardName = scan.next(); 
			String hero = scan.next();
			String fileName = scan.next();
			Image cardLarge = new Image(fileName);
			int attack = scan.nextInt();
			int defense = scan.nextInt();
			int speed = scan.nextInt();
			
			Card card = new Card(cardName, hero, cardLarge, attack, defense, speed); //creates new card using information from text document
			
			deck.add(card); // adds card to deck 4 times (loop later? this is already O(1))
			deck.add(card); 
			deck.add(card);
			deck.add(card);
			
			if(scan.hasNextLine()) //goes to next line if the next line exists
				scan.nextLine();
			
		}
		
		scan.close();
	}
	
	/**
	 * Draws one card and returns it
	 * @return card the card that was drawn
	 */
	public Card drawCard(){
			Random rand = new Random();
			int index = rand.nextInt(deck.size());
			
			Card card = deck.get(index);
			deck.remove(card);
		return card;
	}
	
	/**
	 * Gets the arraylist which contains cards in the deck
	 * @return deck arraylist of cards
	 */
	public ArrayList<Card> getDeck(){
		return this.deck;
	}
	
	/**
	 * Gets the string representation of the deck
	 */
	public String toString(){
		String result = "Your Deck: \n";
		for(Card c: deck)
			result += c.toString() + "\n";
		
		return result;
	}
}
