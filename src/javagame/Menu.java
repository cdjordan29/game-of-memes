package javagame;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 * Menu class represents the menu screen in Project 5
 * @author Michael Taylor, Daniel Jordan
 * @version 1.0
 */
public class Menu extends BasicGameState{
	
	GameContainer gc;
	StateBasedGame sbg;
	Graphics g;
	Image playButtonUnpressed;
	Image playButtonPressed;
	Image exitButtonUnpressed;
	Image exitButtonPressed;
	Image menuLogo;
	Image menuBackground;
	Image dinduNuffin;
	Image gnomeGlasses;
	boolean isPlayButtonPressed;
	boolean isExitButtonPressed;
	boolean harambeHovered;
	boolean gnomeChildHovered;

	/**
	 * Gets the menu state
	 * @param state menu state number
	 * @throws SlickException
	 */
	public Menu(int state) throws SlickException{
		
	}
	
	/**
	 * Initializes the images for the menu
	 */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		playButtonUnpressed = new Image("res/Menu_Images/playButtonUnpressed.png");
		playButtonPressed = new Image("res/Menu_Images/playButtonPressed.png");
		exitButtonUnpressed = new Image("res/Menu_Images/exitButtonUnpressed.png");
		exitButtonPressed = new Image("res/Menu_Images/exitButtonPressed.png");
		menuLogo = new Image("res/Menu_Images/menuLogo3.png");
		menuBackground = new Image("res/Menu_Images/menuBackground.png");
		dinduNuffin = new Image("res/Menu_Images/dinduNuffinBubble.png");
		gnomeGlasses = new Image("res/Menu_Images/gnomeChildGlasses.png");
	}
	
	/**
	 * Renders the menu graphics
	 */
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		this.gc = gc;
		this.sbg = sbg;
		this.g = g;
		menuBackground.draw();
		g.drawString("MAIN MENU", 50, 50);
		menuLogo.draw(300, 0);
		playButtonUnpressed.draw(700,600);
		exitButtonUnpressed.draw(700,800);
		
		
		if(isPlayButtonPressed)
			playButtonPressed.draw(700,600); //draws pressed image over unpressed image.
		else
			playButtonPressed.draw(-1000, -1000); //draws pressed image off screen (there is no remove method)
		
		if(isExitButtonPressed)
			exitButtonPressed.draw(700,800); //draws press image over unpressed image.
		else
			exitButtonPressed.draw(-1000, -1000); //draws pressed image off screen (there is no remove method)
		
		if(harambeHovered)
			dinduNuffin.draw(5, 300);
		else
			dinduNuffin.draw(-1000, -1000);
		
		if(gnomeChildHovered)
			gnomeGlasses.draw(1520, 760);
		else
			gnomeGlasses.draw(-1000, -1000);
	}
	
	/**
	 * Handles the menu logic
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		int posX = Mouse.getX(); //gets X coordinate of mouse
		int posY = Mouse.getY(); //gets Y coordinate of mouse
		
		//Checks if mouse is over Play button
		if((posX>700 && posX < 1100) && (posY > 250 && posY < 400)){
			isPlayButtonPressed = true; //updates variable to say the button is being pressed down
			render(gc, sbg, g); //renders frame again to update the pressed play button
			if(Mouse.isButtonDown(0)){ //checks if left button is being held down
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sbg.enterState(2); //enters game state
			}
		}
		else //if the mouse is not inside the play button, the variable gets set back to false
			isPlayButtonPressed = false;
		
		//Checks if mouse is over Exit button
		if((posX>700 && posX < 1100) && (posY > 50 && posY < 200))
		{
			isExitButtonPressed = true; //updates variable to say button is being pressed down
			if(Mouse.isButtonDown(0)){ //checks if left button is being held down
				gc.exit(); //closes the window
			}
		}
		else //if the mouse is not inside the exit button, the variable gets set back to false
			isExitButtonPressed = false;
		
		//Checks if mouse is over Harambe
		if((posX < 420) && (posY < 550))
		{
			harambeHovered = true;
			render(gc, sbg, g);
		}
		else
			harambeHovered = false;
		
		//Checks if mouse is over gnomechild
		if((posX > 1550) && (posY < 570))
			gnomeChildHovered = true;
		else
			gnomeChildHovered = false;
		
	}
	
	/**
	 * Gets the menu state number
	 */
	public int getID(){
		return 0;
	}
}
