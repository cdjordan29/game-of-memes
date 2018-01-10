package javagame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * WinScreen.java - The win screen of the game
 * @author Michael Taylor, Daniel Jordan
 *
 */
public class WinScreen extends BasicGameState{
	
	GameContainer gc;
	StateBasedGame sbg;
	Graphics g;
	private Data data;
	private Image thiefFrame;
	private Image wizardFrame;
	private Image warriorFrame;
	
	/**
	 * Gets the data from the data class
	 * @param state the WinScreen state
	 * @param data the data from the data class
	 * @throws SlickException
	 */
	public WinScreen(int state, Data data) throws SlickException{
		this.data = data;
	}
	
	/**
	 * Initializes the win images
	 */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
			this.thiefFrame = new Image("res/Win_Screen_Images/thiefFrame.png");
			this.wizardFrame = new Image("res/Win_Screen_Images/wizardFrame.png");
			this.warriorFrame = new Image("res/Win_Screen_Images/warriorFrame.png");
		
	}
	
	/**
	 * Renders the win image based on who won
	 */
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		this.gc = gc;
		this.sbg = sbg;
		this.g = g;
		
		if(data.getWinner().equals("wizard"))
			wizardFrame.draw();
		else if(data.getWinner().equals("thief"))
			thiefFrame.draw();
		else if(data.getWinner().equals("warrior"))
			warriorFrame.draw();


	}
	
	/**
	 * contains logic for updating the game
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		
		
	}
	
	/**
	 * Gets the state ID
	 */
	public int getID(){
		return 4;
	}
}
