package javagame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * PickHeroMenu2.java - The hero menu for the second player
 * @author Michael Taylor, Daniel Jordan
 *
 */
public class PickHeroMenu2 extends BasicGameState{
	
	GameContainer gc;
	StateBasedGame sbg;
	Graphics g;
	TrueTypeFont font;
	TextField textfield;
	Player player;
	Image beginButton;
	Image backgroundImage;
	Image decks;
	Image decksBlack;
	Image playerName;
	Image twoPlayersUnchecked;
	Image twoPlayersChecked;
	Image deckHighlight;
	Data data;
	boolean isButtonChecked;
	boolean wizardSelected;
	boolean thiefSelected;
	boolean warriorSelected;
	
	/**
	 * Gets data for the game
	 * @param state The state of this menu
	 * @param data The data which stores game information
	 * @throws SlickException
	 */
	public PickHeroMenu2(int state, Data data) throws SlickException{
		this.data = data;
	}
	
	/**
	 * Initializes game data
	 */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		
		backgroundImage = new Image("res/Player_Select_Menu_Images/backgroundImageP2.png");
		decks = new Image("res/Player_Select_Menu_Images/decks.png");
		decksBlack = new Image("res/Player_Select_Menu_Images/deckBlack.png");;
		deckHighlight = new Image("res/Player_Select_Menu_Images/deckHighlight.png");
		this.isButtonChecked = false;
		
		
	}
	
	/**
	 * Renders graphics for the PickHeroMenu2 class
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
		
		if(data.getPlayer1().getHeroName().equals("thief"))
			decksBlack.draw(313, 280);
		if(data.getPlayer1().getHeroName().equals("wizard"))
			decksBlack.draw(778, 280);
		if(data.getPlayer1().getHeroName().equals("warrior"))
			decksBlack.draw(1232, 280);

	}
	
	/**
	 * Contains logic for PickHeroMenu2
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
		if((posX>345 && posX < 615) && (posY > 275 && posY < 690) && !data.getPlayer1().getHeroName().equals("thief")){
			if(Mouse.isButtonDown(0)){
				thiefSelected = true;
				wizardSelected = false;
				warriorSelected= false;
			}
		}
		
		//Checks if the mouse is clicked over the wizard deck
		if((posX>808 && posX < 1080) && (posY > 275 && posY < 690) && !data.getPlayer1().getHeroName().equals("wizard")){
			if(Mouse.isButtonDown(0)){
				thiefSelected = false;
				wizardSelected = true;
				warriorSelected= false;
			}
		}
		
		//Checks if the mouse is clicked over the warrior deck
		if((posX>1262 && posX < 1535) && (posY > 275 && posY < 690) && !data.getPlayer1().getHeroName().equals("warrior")){
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
					sbg.enterState(1); //enters game state
				}
			}
		}
		
	}
	
	/**
	 * Creates Player2
	 * @throws SlickException
	 */
	private void createPlayer() throws SlickException{
		if(thiefSelected)
			player = new Player("Player2", "thief", 1);
		else if(wizardSelected)
			player = new Player("Player2", "wizard", 1);
		else if(warriorSelected)
			player = new Player("Player2", "warrior", 1);
		data.setPlayer2(player);
	}
	
	/**
	 * Gets the menu game state
	 */
	public int getID(){
		return 3;
	}
}
