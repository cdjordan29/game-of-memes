package javagame;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.*;
import java.util.*;

/**
 * PickHeroMenu.java - The hero menu for the first player
 * @author Michael Taylor, Daniel Jordan
 * @version 1.0
 */
public class PickHeroMenu extends BasicGameState{
	
	GameContainer gc;
	StateBasedGame sbg;
	Graphics g;
	TrueTypeFont font;
	TextField textfield;
	Player player;
	Image beginButton;
	Image decks;
	Image playerName;
	Image twoPlayersUnchecked;
	Image twoPlayersChecked;
	Image deckHighlight;
	Image backgroundImage;
	Data data;
	boolean isButtonChecked;
	boolean wizardSelected;
	boolean thiefSelected;
	boolean warriorSelected;
	
	/**
	 * Gets the Data class
	 * @param state the state number
	 * @param data the data class which stores game information
	 * @throws SlickException
	 */
	public PickHeroMenu(int state, Data data) throws SlickException{
		this.data = data;
	}
	
	/**
	 * Initializes data for PickHeroMenu
	 */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		
		backgroundImage  = new Image("res/Player_Select_Menu_Images/backgroundImageP1.png");
		decks = new Image("res/Player_Select_Menu_Images/decks.png");
		deckHighlight = new Image("res/Player_Select_Menu_Images/deckHighlight.png");
		this.isButtonChecked = false;
		
		
	}
	
	/**
	 * Renders graphics for PickHeroMenu
	 */
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		this.gc = gc;
		this.sbg = sbg;
		this.g = g;
		
		backgroundImage.draw();
		decks.draw(343, 200);
		
		if(wizardSelected)
		{
			deckHighlight.draw(778, 292);
		}
		if(warriorSelected)
		{
			deckHighlight.draw(1232, 292);
		}
		if(thiefSelected)
		{
			deckHighlight.draw(313, 292);
		}

	}
	
	/**
	 * Contains logic for PickHeroMenu
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		int posX = Mouse.getX(); //gets X coordinate of mouse
		int posY = Mouse.getY(); //gets Y coordinate of mouse
		
		//Checks if mouse is clicked over the two buttons label
		if((posX>675 && posX < 1192) && (posY > 165 && posY < 223)){
			if(Mouse.isButtonDown(0)){
				if(isButtonChecked)
					isButtonChecked = false;
				else if(!isButtonChecked)
					isButtonChecked = true;
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		//Checks if mouse is clicked over the thief deck
		if((posX>345 && posX < 615) && (posY > 275 && posY < 690)){
			if(Mouse.isButtonDown(0)){
				thiefSelected = true;
				wizardSelected = false;
				warriorSelected= false;
			}
		}
		
		//Checks if the mouse is clicked over the wizard deck
		if((posX>808 && posX < 1080) && (posY > 275 && posY < 690)){
			if(Mouse.isButtonDown(0)){
				thiefSelected = false;
				wizardSelected = true;
				warriorSelected= false;
			}
		}
		
		//Checks if the mouse is clicked over the warrior deck
		if((posX>1262 && posX < 1535) && (posY > 275 && posY < 690)){
			if(Mouse.isButtonDown(0)){
				thiefSelected = false;
				wizardSelected = false;
				warriorSelected= true;
			}
		}
		
		if((posX>630 && posX < 1270) && (posY > 10 && posY < 150)){
			if(Mouse.isButtonDown(0)){
				if(thiefSelected || wizardSelected || warriorSelected){
					createPlayer();			
					sbg.enterState(3); //enters game state
				}
			}
		}
		
	}
	
	/**
	 * Creates Player 1 and Player 2 then passes them to Data class
	 * @throws SlickException
	 */
	private void createPlayer() throws SlickException{
		if(thiefSelected)
			player = new Player("Player1", "thief", 1);
		else if(wizardSelected)
			player = new Player("Player1", "wizard", 1);
		else if(warriorSelected)
			player = new Player("Player1", "warrior", 1);
		data.setPlayer1(player);
		
		if(!isButtonChecked)
		{
			boolean uniqueHeroes = false;
			Random rand = new Random();
			
			while(uniqueHeroes == false){
				int randNum = rand.nextInt(3);
				if(randNum == 0)
					data.setPlayer2(new Player("Player2", "thief", 2));
				else if(randNum == 1)
					data.setPlayer2(new Player("Player2", "wizard", 2));
				else if(randNum == 2)
					data.setPlayer2(new Player("Player2", "warrior", 2));
				
				if(!(data.getPlayer1().getHeroName().equals(data.getPlayer2().getHeroName())))
					uniqueHeroes = true;
			}
		}
		
	}
	
	/**
	 * Gets the ID of the PickHeroMenu state
	 */
	public int getID(){
		return 2;
	}
}
