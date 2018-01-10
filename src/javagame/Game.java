package javagame;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 * Game class represents the driver for Project 5
 * @author Michael Taylor, Daniel Jordan
 * @version 1.0
 */
public class Game extends StateBasedGame{

	public static final String gameName = "Game of Memes";
	public static final int menu = 0;
	public static final int play = 1;
	public static final int pickHeroMenu = 2;
	public static final int pickHeroMenu2 = 3;
	public static final int winScreen = 4;
	public Player player1;
	public Player player2;
	public Data data;
	
	/**
	 * Initializes game data
	 * @param gameName the name of the game
	 * @throws SlickException
	 */
	public Game (String gameName) throws SlickException{
		super(gameName);
		this.data = new Data();
		this.addState(new Menu(menu));
		this.addState(new Play(play, data));
		this.addState(new PickHeroMenu(pickHeroMenu, data));
		this.addState(new PickHeroMenu2(pickHeroMenu2, data));
		this.addState(new WinScreen(winScreen, data));
	}
	
	/**
	 * Initializes the states of the game
	 */
	public void initStatesList(GameContainer gc) throws SlickException{
		this.getState(menu).init(gc, this);
		this.getState(play).init(gc, this);
		this.getState(pickHeroMenu).init(gc, this);
		this.getState(pickHeroMenu2).init(gc, this);
		this.getState(winScreen).init(gc, this);
		this.enterState(menu);
	}
	
	/**
	 * Main method of the game.
	 * @param args n/a
	 */
	public static void main(String[] args){
		
		AppGameContainer appgc;
		try{
			appgc = new AppGameContainer(new Game(gameName));
			appgc.setDisplayMode(1900, 1000, false);
			appgc.start();
		}catch(SlickException e){
			e.printStackTrace();
		}
	}
	
}
