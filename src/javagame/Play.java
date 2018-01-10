package javagame;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.Timer;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 * Play class represents the play screen for Project 5
 * @author Michael Taylor, Daniel Jordan
 * @version 1.0
 */
public class Play extends BasicGameState {

	
	GameContainer gc;
	StateBasedGame sbg;
	Graphics g;
	Timer timer;
	Image player1HandArea;
	Image player2HandArea;
	Image endTurnButtonUnpressed;
	Image endTurnButtonPressed;
	Image cardSelectHand;
	Image yellowHighlight;
	Image startTurnButtonUnpressed;
	Image startTurnButtonPressed;
	Image yourManaBox;
	Image yourHealthBox;
	Image enemyManaBox;
	Image enemyHealthBox;
	Cell[][] cells;
	Card selectedCard;
	Card selectedGridCard;
	Hero selectedHero;
	Hero selectedGridHero;
	Card dummyCard;
	Player player1;
	Player player2;
	Player currentPlayer;
	Grid grid;
	ArrayList<GridImage> yellowHighlights;
	ArrayList<GridImage> playerHighlights;
	Data data;
	int tempMana;
	int timerNum;
	int renderLoopCount;
	int cardSelectedNum;
	int highlightTopBound;
	int highlightBottomBound;
	int highlightRightBound;
	int highlightLeftBound;
	int ii;
	int jj;
	int iii;
	int jjj;
	Boolean isEndTurnPressed;
	Boolean isStartTurnPressed;
	Boolean hasStartBeenClicked;
	Boolean heroFirstSelectedP1 = false;
	Boolean heroFirstSelectedP2 = false;
	Boolean drawCard = false;
	Boolean placeCardFromHandPhase = true;
	Boolean selectCardFromGridPhase = false;
	Boolean placeCardFromGridPhase = false;
	Boolean startCountDown = false;
	Boolean drawPlayerCard1 = false;
	Boolean drawPlayerCard2 = false;
	Boolean drawPlayerCard3 = false;
	Boolean drawPlayerCard4 = false;
	Boolean drawPlayerCard5 = false;
	Boolean drawPlayerCard6 = false;
	Boolean drawPlayerCard7 = false;
	Font font;
	TrueTypeFont ttf;

	/**
	 * Gets the data for the game
	 * @param state the state of the game menu
	 * @param data the game data which contains player information
	 */
	public Play(int state, Data data) {
		this.data = data;

	}

	/**
	 * Gets player1 information and initializes it
	 * @param player1 the Player1 information
	 */
	public void updatePlayer1(Player player1) {
		this.player1 = player1;
	}

	/**
	 * Initializes data for Play.java
	 */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		font = new Font("Verdana", Font.BOLD, 40);
		ttf = new TrueTypeFont(font, true);
		this.yellowHighlights = new ArrayList<GridImage>();
		this.playerHighlights = new ArrayList<GridImage>();
		this.highlightLeftBound = 2500;
		this.highlightBottomBound = 0;
		this.highlightRightBound = 0;
		this.highlightTopBound = 2500;
		this.timerNum = 0;
		selectedCard = null;
		selectedGridCard = null;
		grid = new Grid();
		grid.createCellArray();
		renderLoopCount = 0;
		player1HandArea = new Image("res/Game_Images/handArea.png");
		player2HandArea = new Image("res/Game_Images/handArea.png");
		endTurnButtonUnpressed = new Image("res/Game_Images/endTurnUnpressed.png");
		endTurnButtonPressed = new Image("res/Game_Images/endTurnPressed.png");
		startTurnButtonUnpressed = new Image("res/Game_Images/startTurnUnpressed.png");
		startTurnButtonPressed = new Image("res/Game_Images/startTurnPressed.png");
		cardSelectHand = new Image("res/Game_Images/cardSelectHand.png");
		yellowHighlight = new Image("res/Game_Images/yellowHighlight.png");
		yourManaBox = new Image("res/Game_Images/yourMana.png");
		enemyManaBox = new Image("res/Game_Images/enemyMana.png");
		yourHealthBox = new Image("res/Game_Images/yourHealth.png");
		enemyHealthBox = new Image("res/Game_Images/enemyHealth.png");
		cardSelectedNum = -1;
		isEndTurnPressed = false;
		isStartTurnPressed = false;
		hasStartBeenClicked = false;
		tempMana = 1;

		setUpPlayerHighlights();

	}

	@SuppressWarnings({ "unused" })
	/**
	 * Renders graphics for Play.java
	 */
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		int posX = Mouse.getX(); // gets X coordinate of mouse
		int posY = Mouse.getY(); // gets Y coordinate of mouse
		
		if (renderLoopCount == 0) {
			player1 = data.getPlayer1();
			player1.getHand().drawFirstHand();
			player2 = data.getPlayer2();
			player2.getHand().drawFirstHand();
			currentPlayer = player1;
		}

		grid.drawGrid();
		yourManaBox.draw(730, 700, .4f);
		yourHealthBox.draw(730, 575, .4f);
		enemyManaBox.draw(730, 190, .4f);
		enemyHealthBox.draw(730, 315, .4f);
		player1.drawHandArea();
		player2.drawHandArea();
		
		if(hasStartBeenClicked){
			currentPlayer.drawHand();
			highlightSelectedHandCard(cardSelectedNum);
		}
		
		renderLoopCount++;
		drawCardsOnGrid();
		drawHerosOnGrid();
		hoverCardsOnGrid(posX, posY);
		

		

		if (currentPlayer == player1) {
			
			player2.drawCardBacks();
			player2.getDeckImage().draw(700, 10, .235f);
			player1.getDeckImage().draw(700, 855, .235f);
			ttf.drawString(662, 10, player2.getDeck().getCardCount() + "");
			ttf.drawString(662, 855, player1.getDeck().getCardCount() + "");
			
			if(player1.getPlayerMana() < 10)
				ttf.drawString(765, 740, tempMana + "/" + player1.getPlayerMana());	
			if(player1.getPlayerMana() >= 10 && tempMana >= 10)
				ttf.drawString(735, 740, tempMana + "/" + player1.getPlayerMana());	
			if(player1.getPlayerMana() >= 10 && tempMana < 10)
				ttf.drawString(745, 740, tempMana + "/" + player1.getPlayerMana());	
			if(player2.getPlayerMana() < 10)
				ttf.drawString(790, 230, player2.getPlayerMana() + "");	
			if(player2.getPlayerMana() >= 10)
				ttf.drawString(775, 230, player2.getPlayerMana() + "");
			
			if(player1.getHero().getHealth() < 10)
				ttf.drawString(790, 615, player1.getHero().getHealth() + "");	
			if(player1.getHero().getHealth() >= 10)
				ttf.drawString(775, 615, player1.getHero().getHealth() + "");	
			if(player2.getHero().getHealth() < 10)
				ttf.drawString(790, 354, player2.getHero().getHealth() + "");	
			if(player2.getHero().getHealth() >= 10)
				ttf.drawString(775, 354, player2.getHero().getHealth() + "");

		} else if (currentPlayer == player2) {
			player2.drawCardBacks();
			player2.getDeckImage().draw(700, 10, .235f);
			player1.getDeckImage().draw(700, 855, .235f);
			player1.drawCardBacks();
			player1.getDeckImage().draw(700, 10, .235f);
			player2.getDeckImage().draw(700, 855, .235f);
			
			ttf.drawString(662, 10, player1.getDeck().getCardCount() + "");
			ttf.drawString(662, 855, player2.getDeck().getCardCount() + "");
			
			if(player2.getPlayerMana() < 10)
				ttf.drawString(765, 740, tempMana + "/" + player2.getPlayerMana());	
			if(player2.getPlayerMana() >= 10 && tempMana >= 10)
				ttf.drawString(735, 740, tempMana + "/" + player2.getPlayerMana());	
			if(player2.getPlayerMana() >= 10 && tempMana < 10)
				ttf.drawString(745, 740, tempMana + "/" + player2.getPlayerMana());	
			if(player1.getPlayerMana() < 10)
				ttf.drawString(790, 230, player1.getPlayerMana() + "");	
			if(player1.getPlayerMana() >= 10)
				ttf.drawString(775, 230, player1.getPlayerMana() + "");
			
			if(player2.getHero().getHealth() < 10)
				ttf.drawString(790, 615, player2.getHero().getHealth() + "");	
			if(player2.getHero().getHealth() >= 10)
				ttf.drawString(775, 615, player2.getHero().getHealth() + "");	
			if(player1.getHero().getHealth() < 10)
				ttf.drawString(790, 354, player1.getHero().getHealth() + "");	
			if(player1.getHero().getHealth() >= 10)
				ttf.drawString(775, 354, player1.getHero().getHealth() + "");
		}

		if (!currentPlayer.isheroTokenPlaced())
			currentPlayer.getHero().drawHeroToken(10, 700);

		if (isEndTurnPressed) {
			endTurnButtonPressed.draw(725, 450, .4f);
		} else {
			endTurnButtonUnpressed.draw(725, 450, .4f);
		}
		if (isStartTurnPressed) {
			startTurnButtonPressed.draw(550, 450, .4f);
		} else {
			startTurnButtonUnpressed.draw(550, 450, .4f);
		}

		if (selectedGridCard != null)
			selectedGridCard.getImage().draw(125, 225);

		if ((selectedCard != null && playerHighlights.size() > 0 && hasStartBeenClicked && selectedCard.getCardSpeed() <= tempMana)
				|| (selectedHero != null && !currentPlayer.isheroTokenPlaced())) {
			for (GridImage ph : playerHighlights)
				ph.drawImage();
		}
		
		if(hasStartBeenClicked)
			drawEnlargedCards(currentPlayer);
		else{
			int xback = 0;
			int yback = 870;
			for(Card c : currentPlayer.getHand().getHandList()){
				currentPlayer.getCardBack().draw(xback, yback, .235f);
				xback += currentPlayer.getCardBack().getWidth() * .235;
			}
		}
		
		if (yellowHighlights.size() != 0) {
			for (GridImage gi : yellowHighlights) {
				gi.drawImage();
			}
		}
	}

	/**
	 * Contains logic for Play.java
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		int posX = Mouse.getX(); // gets X coordinate of mouse
		int posY = Mouse.getY(); // gets Y coordinate of mouse
		
		if(player1 != null && player2 != null){
			if (player1.getHero().getHealth() <= 0){
				data.setWinner(player2.getHeroName());
				sbg.enterState(4);
			}
			if (player2.getHero().getHealth() <= 0){
				data.setWinner(player1.getHeroName());
				sbg.enterState(4);
			}
		}

		if (selectedGridCard == null && hasStartBeenClicked) {
			try {
				overPlayerCards(posX, posY);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		if (selectedGridCard != null || (selectedHero != null)) {
			calculateHighlightBounds();
		} else {
			this.highlightLeftBound = 2500;
			this.highlightBottomBound = 0;
			this.highlightRightBound = 0;
			this.highlightTopBound = 2500;
		}

		try {
			endTurnPressed(posX, posY);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			startTurnPressed(posX, posY);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		cells = grid.getCellArray();
	}

	/**
	 * Executes when the left mouse button is clicked
	 */
	public void mouseClicked(int button, int x, int y, int clickCount) {
		int posX = Mouse.getX(); // gets X coordinate of mouse
		int posY = Mouse.getY(); // gets Y coordinate of mouse

		
		if ((!currentPlayer.isheroTokenPlaced() && ((posX >= 10 && posX <= 110) && (posY >= 200 && posY <= 300)))) {
			selectHero();
		} 
		else if (selectedHero != null && !currentPlayer.isheroTokenPlaced()){
			placeHeroOnGridFromHand(posX, posY);
			selectedHero = null;
		}

		if (selectedCard != null)
			drawCard = true;

		
		
		if (selectedGridCard == null)
			selectCardsOnGridFromGrid(posX, posY);
		else if (selectedGridCard != null) {
			placeCardsOnGridFromGrid(posX, posY);
			selectedGridCard = null;

			if (yellowHighlights.size() > 0){
				for(int i = 0; i < yellowHighlights.size(); i++)
					yellowHighlights.set(i, null);
				yellowHighlights.removeAll(yellowHighlights);
			}
		}

		placeCardsOnGridFromHand(posX, posY);
	}
	
	/**
	 * Gets the game state of the Play state
	 */
	public int getID() {
		return 1;
	}

	/**
	 * Checks if mouse is over a specific card in the hand area
	 * @param x Mouse X coordinate
	 * @param y Mouse Y coordinate
	 * @throws InterruptedException
	 */
	private void overPlayerCards(int x, int y) throws InterruptedException {

		if ((x > 0 && x < 94) && (y > 0 && y < 131)) {
			drawPlayerCard1 = true;
			selectHandCard(0);
		} else {
			drawPlayerCard1 = false;
		}

		if ((x > 94 && x < 188) && (y > 0 && y < 131)) {
			drawPlayerCard2 = true;
			selectHandCard(1);
		} else {
			drawPlayerCard2 = false;
		}

		if ((x > 188 && x < 282) && (y > 0 && y < 131)) {
			drawPlayerCard3 = true;
			selectHandCard(2);
		} else {
			drawPlayerCard3 = false;
		}

		if ((x > 282 && x < 376) && (y > 0 && y < 131)) {
			drawPlayerCard4 = true;
			selectHandCard(3);
		} else {
			drawPlayerCard4 = false;
		}

		if ((x > 376 && x < 470) && (y > 0 && y < 131)) {
			drawPlayerCard5 = true;
			selectHandCard(4);
		} else {
			drawPlayerCard5 = false;
		}

		if ((x > 470 && x < 564) && (y > 0 && y < 131)) {
			drawPlayerCard6 = true;
			selectHandCard(5);
		} else {
			drawPlayerCard6 = false;
		}

		if ((x > 564 && x < 658) && (y > 0 && y < 131)) {
			drawPlayerCard7 = true;
			selectHandCard(6);
		} else {
			drawPlayerCard7 = false;
		}

	}
	
	/**
	 * Enlarges the card if it has been selected
	 * @param player the player who's cards will be enlarged
	 */
	private void drawEnlargedCards(Player player) {

		if (drawPlayerCard1 && player.getHand().getHandList().size() >= 1) {
			player.getHand().getHandList().get(0).getImage().draw(125, 225);
		} else if (player.getHand().getHandList().size() >= 1) {
			player.getHand().getHandList().get(0).getImage().draw(-1000, -1000);
		}

		if (drawPlayerCard2 && player.getHand().getHandList().size() >= 2) {
			player.getHand().getHandList().get(1).getImage().draw(125, 225);
		} else if (player.getHand().getHandList().size() >= 2) {
			player.getHand().getHandList().get(1).getImage().draw(-1000, -1000);
		}

		if (drawPlayerCard3 && player.getHand().getHandList().size() >= 3) {
			player.getHand().getHandList().get(2).getImage().draw(125, 225);
		} else if (player.getHand().getHandList().size() >= 3) {
			player.getHand().getHandList().get(2).getImage().draw(-1000, -1000);
		}

		if (drawPlayerCard4 && player.getHand().getHandList().size() >= 4) {
			player.getHand().getHandList().get(3).getImage().draw(125, 225);
		} else if (player.getHand().getHandList().size() >= 4) {
			player.getHand().getHandList().get(3).getImage().draw(-1000, -1000);
		}

		if (drawPlayerCard5 && player.getHand().getHandList().size() >= 5) {
			player.getHand().getHandList().get(4).getImage().draw(125, 225);
		} else if (player.getHand().getHandList().size() >= 5) {
			player.getHand().getHandList().get(4).getImage().draw(-1000, -1000);
		}

		if (drawPlayerCard6 && player.getHand().getHandList().size() >= 6) {
			player.getHand().getHandList().get(5).getImage().draw(125, 225);
		} else if (player.getHand().getHandList().size() >= 6) {
			player.getHand().getHandList().get(5).getImage().draw(-1000, -1000);
		}

		if (drawPlayerCard7 && player.getHand().getHandList().size() >= 7) {
			player.getHand().getHandList().get(6).getImage().draw(125, 225);
		} else if (player.getHand().getHandList().size() >= 7) {
			player.getHand().getHandList().get(6).getImage().draw(-1000, -1000);
		}
	}

	/**
	 * Checks if mouse is over the end turn button and executes endTurn() when clicked
	 * @param x The mouse X coordinate
	 * @param y The mouse Y coordinate
	 * @throws InterruptedException
	 */
	private void endTurnPressed(int x, int y) throws InterruptedException {
		if ((x > 725 && x < 885) && (y > 450 && y < 550)) {
			isEndTurnPressed = true;
			if (Mouse.isButtonDown(0) && currentPlayer.isheroTokenPlaced()) { // checks if left button is being held
				Thread.sleep(200);
				endTurn();
			}
		} else {
			isEndTurnPressed = false;
		}
	}
	
	/**
	 * Checks if mouse is over the start turn button
	 * @param x The X coordinate of the mouse
	 * @param y The Y coordinate of the mouse
	 * @throws InterruptedException
	 */
	private void startTurnPressed(int x, int y) throws InterruptedException {
		if ((x > 550 && x < 710) && (y > 450 && y < 550)) {
			isStartTurnPressed = true;
			if (Mouse.isButtonDown(0) && currentPlayer.isheroTokenPlaced()) { // checks if left button is being held
				Thread.sleep(200);
				hasStartBeenClicked = true;
			}
		} else {
			isStartTurnPressed = false;
		}
	}

	/**
	 * Places a highlight over the selected card in hand
	 * @param cardNum the card selected
	 */
	private void highlightSelectedHandCard(int cardNum) {
		int x = -15;
		int y = 850;

		if (cardNum >= 0 && cardNum <= 7)
			cardSelectHand.draw(x + (94 * cardNum), y, .235f);
	}

	/**
	 * Sets selectedCard to the card selected in player's hand
	 * @param cardNum the place of the card in the player's hand
	 * @throws InterruptedException
	 */
	private void selectHandCard(int cardNum) throws InterruptedException {
		if (Mouse.isButtonDown(0)) { // checks if left button is being held down
			
			selectedCard = null;
			
			if (cardSelectedNum == cardNum) {
				cardSelectedNum = -1;
				selectedCard = null;
			} else
				cardSelectedNum = cardNum;

			if (cardSelectedNum != -1 && cardNum < currentPlayer.getHand().getHandList().size()){
				if(currentPlayer.getHand().getHandList().get(cardNum).getCardSpeed() <= tempMana){
					selectedCard = currentPlayer.getHand().getHandList().get(cardNum);
				}
			}
			
			Thread.sleep(200);
		}
	}
	
	/**
	 * Updates the hero to the current player's hero
	 */
	private void selectHero() {
		selectedHero = currentPlayer.getHero();

	}

	/**
	 * Checks mouse location then places the hero on the grid at that location
	 * @param posX the mouse X coordinate
	 * @param posY the mouse Y coordinate
	 */
	private void placeHeroOnGridFromHand(int posX, int posY) {
		if (posX > 900 && posY > 0 && selectedHero != null) {
			for (int i = 0; i < cells.length; i++) {
				for (int j = 0; j < cells[i].length; j++) {
					if (currentPlayer == player1
							&& (posX > cells[i][j].getXLowerBound() && posX < cells[i][j].getXUpperBound())
							&& (Math.abs(posY - 1000) > cells[i][j].getYLowerBound()
									&& Math.abs(posY - 1000) < cells[i][j].getYUpperBound())) {
						if (j >= 8 && cells[i][j].getCard() == null && cells[i][j].getHero() == null) {
							cells[i][j].addHero(currentPlayer.getHero());
							currentPlayer.placeHeroToken();
							selectedHero = null;
						}

						try {
							Thread.sleep(300);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					else if (currentPlayer == player2
							&& (posX > cells[i][j].getXLowerBoundFlipped()
									&& posX < cells[i][j].getXUpperBoundFlipped())
							&& (Math.abs(posY - 1000) > cells[i][j].getYLowerBoundFlipped()
									&& Math.abs(posY - 1000) < cells[i][j].getYUpperBoundFlipped())) {
						try {
							Thread.sleep(300);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (j <= 1 && cells[i][j].getCard() == null && cells[i][j].getHero() == null) {
							cells[i][j].addHero(currentPlayer.getHero());
							currentPlayer.placeHeroToken();
							selectedHero = null;
						}
					}

					placeCardFromHandPhase = false;
					selectCardFromGridPhase = true;
				}
			}
		}

	}
	
	/**
	 * Operations which fire when the end turn button is clicked
	 */
	private void endTurn() {
		if (currentPlayer == player1){
			currentPlayer = player2;
			tempMana = player2.getPlayerMana()+1;
			
			if(tempMana > 15)
				tempMana = 15;
			}
		else if (currentPlayer == player2){
			currentPlayer = player1;
			tempMana = player1.getPlayerMana()+1;
			
			if(tempMana > 15)
				tempMana = 15;
		}
		
		if(currentPlayer.getPlayerMana() < 15)
			currentPlayer.incrementPlayerMana();
		
		cardSelectedNum = -1;
		selectedCard = null;
		selectedHero = null;
		grid.rotateGrid();
		yellowHighlights.removeAll(yellowHighlights);
		player1.getHero().setHasBeenAttacked(false);
		player2.getHero().setHasBeenAttacked(false);
		if (currentPlayer.getHand().getHandList().size() < 7 && currentPlayer.getDeck().getCardCount() > 0)
			currentPlayer.getHand().drawCard();
		hasStartBeenClicked = false;
		
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Draws an enlarged version of the card when the mouse is over that card on the grid
	 * @param posX the X coordinate of the mouse
	 * @param posY the Y coordinate of the mouse
	 */
	private void hoverCardsOnGrid(int posX, int posY) {
		if (posX > 900 && posY > 0) {
			for (int i = 0; i < cells.length; i++) {
				for (int j = 0; j < cells[i].length; j++) {
					if (currentPlayer == player1
							&& (posX > cells[i][j].getXLowerBound() && posX < cells[i][j].getXUpperBound())
							&& (Math.abs(posY - 1000) > cells[i][j].getYLowerBound()
									&& Math.abs(posY - 1000) < cells[i][j].getYUpperBound())) {
						if (cells[i][j].getCard() != null) {
							cells[i][j].getCard().getImage().draw(125, 225);
						}
					}

					if (currentPlayer == player2
							&& (posX > cells[i][j].getXLowerBoundFlipped()
									&& posX < cells[i][j].getXUpperBoundFlipped())
							&& (Math.abs(posY - 1000) > cells[i][j].getYLowerBoundFlipped()
									&& Math.abs(posY - 1000) < cells[i][j].getYUpperBoundFlipped())) {
						if (cells[i][j].getCard() != null) {
							cells[i][j].getCard().getImage().draw(125, 225);
						}
					}

				}
			}
		}
	}
	
	/**
	 * Goes through the cells 2D array and draws card on the grid if there is a card in that cell
	 */
	private void drawCardsOnGrid() {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				if (cells[i][j].getCard() != null) {
					if (currentPlayer == player1)
						cells[i][j].drawCardImage();
					else if (currentPlayer == player2)
						cells[i][j].drawCardImageFlipped();
				}
			}
		}
	}

	/**
	 * Goes through the cells 2D array and draws the hero on the grid if there is a hero in that cell
	 */
	private void drawHerosOnGrid() {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				if (cells[i][j].getHero() != null) {
					if (currentPlayer == player1)
						cells[i][j].getHero().drawHeroToken(cells[i][j].getXLowerBound(), cells[i][j].getYLowerBound());
					else if (currentPlayer == player2)
						cells[i][j].getHero().drawHeroToken(cells[i][j].getXLowerBoundFlipped(),
								cells[i][j].getYLowerBoundFlipped());
				}
			}
		}
	}

	/**
	 * Sets the card at the mouse coordinates on the grid to selectedCard
	 * @param posX the X mouse coordinate
	 * @param posY the Y mouse coordinate
	 */
	private void selectCardsOnGridFromGrid(int posX, int posY) {
		if (posX > 900 && posY > 0 && selectedCard == null) {
			for (int i = 0; i < cells.length; i++) {
				for (int j = 0; j < cells[i].length; j++) {
					if (currentPlayer == player1
							&& (posX > cells[i][j].getXLowerBound() && posX < cells[i][j].getXUpperBound())
							&& (Math.abs(posY - 1000) > cells[i][j].getYLowerBound()
									&& Math.abs(posY - 1000) < cells[i][j].getYUpperBound())) {
						if (cells[i][j].getCard() != null) {
							if ((cells[i][j].getCard().getHero().equals(currentPlayer.getHeroName()) && cells[i][j].getCard().getCardSpeed() <= tempMana)) {
								selectedGridCard = cells[i][j].getCard();
								ii = i;
								jj = j;
								if (selectedGridCard != null) {
									highlightGridBounds(i, j);
								}
								try {
									Thread.sleep(300);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					}

					if (currentPlayer == player2
							&& (posX > cells[i][j].getXLowerBoundFlipped()
									&& posX < cells[i][j].getXUpperBoundFlipped())
							&& (Math.abs(posY - 1000) > cells[i][j].getYLowerBoundFlipped()
									&& Math.abs(posY - 1000) < cells[i][j].getYUpperBoundFlipped())) {
						if (cells[i][j].getCard() != null) {
							if ((cells[i][j].getCard().getHero().equals(currentPlayer.getHeroName()) && cells[i][j].getCard().getCardSpeed() <= tempMana)) {
								selectedGridCard = cells[i][j].getCard();
								ii = i;
								jj = j;
								if (selectedGridCard != null) {
									highlightGridBounds(i, j);
								}
								try {
									Thread.sleep(300);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					}

					selectCardFromGridPhase = false;
					placeCardFromGridPhase = true;
				}
			}
		}
	}

	/**
	 * Places the selected card on the grid at the mouse coordinate
	 * @param posX The mouse X coordinate
	 * @param posY The mouse Y coordinate
	 */
	private void placeCardsOnGridFromGrid(int posX, int posY) {
		if (posX > 900 && posY > 0) {
			for (int i = 0; i < cells.length; i++) {
				for (int j = 0; j < cells[i].length; j++) {
					if (currentPlayer == player1
							&& (posX > cells[i][j].getXLowerBound() && posX < cells[i][j].getXUpperBound())
							&& (Math.abs(posY - 1000) > cells[i][j].getYLowerBound()
									&& Math.abs(posY - 1000) < cells[i][j].getYUpperBound())) {

						if (((cells[i][j].getXLowerBound() >= highlightLeftBound
								&& cells[i][j].getXUpperBound() <= highlightRightBound)
								&& (cells[i][j].getYLowerBound() == cells[ii][jj].getYLowerBound()))
								|| (cells[i][j].getYLowerBound() >= highlightTopBound
										&& cells[i][j].getYUpperBound() <= highlightBottomBound)
										&& (cells[i][j].getXLowerBound() == cells[ii][jj].getXLowerBound())) {

							if (cells[ii][jj] != cells[i][j]) {
								if (cells[i][j].getCard() != null) {
									if (combat(selectedGridCard, cells[i][j].getCard()) == 0) {
										cells[i][j].removeCard();
										cells[i][j].addCard(selectedGridCard);
										if(ii < i)
											tempMana -= i-ii;
										if(i < ii)
											tempMana -= ii-i;
										if(j < jj)
											tempMana -= jj - j;
										if(jj < j)
											tempMana -= j - jj;
										cells[ii][jj].removeCard();
									} else if (combat(selectedGridCard, cells[i][j].getCard()) == 1) {
										cells[ii][jj].removeCard();
										if(ii < i)
											tempMana -= i-ii;
										if(i < ii)
											tempMana -= ii-i;
										if(j < jj)
											tempMana -= jj - j;
										if(jj < j)
											tempMana -= j - jj;
										
										tempMana++;
									} else if (combat(selectedGridCard, cells[i][j].getCard()) == 2) {
										if (ii > i) { // right
											cells[ii][jj].removeCard();
											cells[i + 1][j].addCard(selectedGridCard);
											tempMana -= ii-i;
											tempMana++;
										}
										if (ii < i) { // left
											cells[ii][jj].removeCard();
											cells[i - 1][j].addCard(selectedGridCard);
											tempMana -= i-ii;
											tempMana++;
										}
										if (jj > j) { // bot
											cells[ii][jj].removeCard();
											cells[i][j + 1].addCard(selectedGridCard);
											tempMana -= jj -j;
											tempMana++;
										}
										if (jj < j) { // top
											cells[ii][jj].removeCard();
											cells[i][j - 1].addCard(selectedGridCard);
											tempMana -= j-jj;
											tempMana++;
										}
									} else if (combat(selectedGridCard, cells[i][j].getCard()) == 3) {
										cells[i][j].removeCard();
										cells[ii][jj].removeCard();
										
										if(ii < i)
											tempMana -= i-ii;
										if(i < ii)
											tempMana -= ii-i;
										if(j < jj)
											tempMana -= jj - j;
										if(jj < j)
											tempMana -= j - jj;
										
										tempMana++;
									}
								} else if (cells[i][j].getHero() != null) {
									if (ii > i && !cells[i][j].getHero().getHasBeenAttacked()) { // right
										cells[ii][jj].removeCard();
										cells[i + 1][j].addCard(selectedGridCard);
										tempMana -= ii-i;
										tempMana++;
									}
									if (ii < i && !cells[i][j].getHero().getHasBeenAttacked()) { // left
										cells[ii][jj].removeCard();
										cells[i - 1][j].addCard(selectedGridCard);
										tempMana -= i-ii;
										tempMana++;
									}
									if (jj > j && !cells[i][j].getHero().getHasBeenAttacked()) { // bot
										cells[ii][jj].removeCard();
										cells[i][j + 1].addCard(selectedGridCard);
										tempMana -= jj -j;
										tempMana++;
									}
									if (jj < j && !cells[i][j].getHero().getHasBeenAttacked()) { // top
										cells[ii][jj].removeCard();
										cells[i][j - 1].addCard(selectedGridCard);
										tempMana -= j-jj;
										tempMana++;
									}
									combatHero(selectedGridCard, cells[i][j].getHero());
								} else {
									cells[i][j].addCard(selectedGridCard);
									cells[ii][jj].removeCard();
									
									if(ii < i)
										tempMana -= i-ii;
									if(i < ii)
										tempMana -= ii-i;
									if(j < jj)
										tempMana -= jj - j;
									if(jj < j)
										tempMana -= j - jj;
										
								}
							}
						}
					}

					if (currentPlayer == player2
							&& (posX > cells[i][j].getXLowerBoundFlipped()
									&& posX < cells[i][j].getXUpperBoundFlipped())
							&& (Math.abs(posY - 1000) > cells[i][j].getYLowerBoundFlipped()
									&& Math.abs(posY - 1000) < cells[i][j].getYUpperBoundFlipped())) {

						if (((cells[i][j].getXLowerBoundFlipped() >= highlightLeftBound
								&& cells[i][j].getXUpperBoundFlipped() <= highlightRightBound)
								&& (cells[i][j].getYLowerBoundFlipped() == cells[ii][jj].getYLowerBoundFlipped()))
								|| (cells[i][j].getYLowerBoundFlipped() >= highlightTopBound
										&& cells[i][j].getYUpperBoundFlipped() <= highlightBottomBound)
										&& (cells[i][j].getXLowerBoundFlipped() == cells[ii][jj]
												.getXLowerBoundFlipped())) {

							if (cells[ii][jj] != cells[i][j]) {
								if (cells[i][j].getCard() != null) {
									if (combat(selectedGridCard, cells[i][j].getCard()) == 0) {
										cells[i][j].removeCard();
										cells[i][j].addCard(selectedGridCard);
										if(ii < i)
											tempMana -= i-ii;
										if(i < ii)
											tempMana -= ii-i;
										if(j < jj)
											tempMana -= jj - j;
										if(jj < j)
											tempMana -= j - jj;
										cells[ii][jj].removeCard();
									} else if (combat(selectedGridCard, cells[i][j].getCard()) == 1) {
										cells[ii][jj].removeCard();
										if(ii < i)
											tempMana -= i-ii;
										if(i < ii)
											tempMana -= ii-i;
										if(j < jj)
											tempMana -= jj - j;
										if(jj < j)
											tempMana -= j - jj;
										
										tempMana++;
									} else if (combat(selectedGridCard, cells[i][j].getCard()) == 2) {
										if (ii > i) { // right
											cells[ii][jj].removeCard();
											cells[i + 1][j].addCard(selectedGridCard);
											tempMana -= ii-i;
											tempMana++;
										}
										if (ii < i) { // left
											cells[ii][jj].removeCard();
											cells[i - 1][j].addCard(selectedGridCard);
											tempMana -= i-ii;
											tempMana++;
										}
										if (jj > j) { // bot
											cells[ii][jj].removeCard();
											cells[i][j + 1].addCard(selectedGridCard);
											tempMana -= jj -j;
											tempMana++;
										}
										if (jj < j) { // top
											cells[ii][jj].removeCard();
											cells[i][j - 1].addCard(selectedGridCard);
											tempMana -= j-jj;
											tempMana++;
										}
									} else if (combat(selectedGridCard, cells[i][j].getCard()) == 3) {
										cells[i][j].removeCard();
										cells[ii][jj].removeCard();
										
										if(ii < i)
											tempMana -= i-ii;
										if(i < ii)
											tempMana -= ii-i;
										if(j < jj)
											tempMana -= jj - j;
										if(jj < j)
											tempMana -= j - jj;
										
										tempMana++;
									}
								} else if (cells[i][j].getHero() != null) {
									if (ii > i && !cells[i][j].getHero().getHasBeenAttacked()) { // right
										cells[ii][jj].removeCard();
										cells[i + 1][j].addCard(selectedGridCard);
										tempMana -= ii-i;
										tempMana++;
									}
									if (ii < i && !cells[i][j].getHero().getHasBeenAttacked()) { // left
										cells[ii][jj].removeCard();
										cells[i - 1][j].addCard(selectedGridCard);
										tempMana -= i-ii;
										tempMana++;
									}
									if (jj > j && !cells[i][j].getHero().getHasBeenAttacked()) { // bot
										cells[ii][jj].removeCard();
										cells[i][j + 1].addCard(selectedGridCard);
										tempMana -= jj -j;
										tempMana++;
									}
									if (jj < j && !cells[i][j].getHero().getHasBeenAttacked()) { // top
										cells[ii][jj].removeCard();
										cells[i][j - 1].addCard(selectedGridCard);
										tempMana -= j-jj;
										tempMana++;
									}
									combatHero(selectedGridCard, cells[i][j].getHero());
								} else {
									cells[i][j].addCard(selectedGridCard);
									cells[ii][jj].removeCard();
									
									if(ii < i)
										tempMana -= i-ii;
									if(i < ii)
										tempMana -= ii-i;
									if(j < jj)
										tempMana -= jj - j;
									if(jj < j)
										tempMana -= j - jj;
										
								}
							}
						}
					}

					selectCardFromGridPhase = true;
					placeCardFromGridPhase = false;
				}
			}
		}
	}

	/**
	 * Places selected hand card on the grid at the mouse coordinate
	 * @param posX the X mouse coordinate
	 * @param posY the Y mouse coordinate
	 */
	private void placeCardsOnGridFromHand(int posX, int posY) {
		if (posX > 900 && posY > 0 && selectedCard != null) {
			for (int i = 0; i < cells.length; i++) {
				for (int j = 0; j < cells[i].length; j++) {
					if (currentPlayer == player1
							&& (posX > cells[i][j].getXLowerBound() && posX < cells[i][j].getXUpperBound())
							&& (Math.abs(posY - 1000) > cells[i][j].getYLowerBound()
									&& Math.abs(posY - 1000) < cells[i][j].getYUpperBound())) {

						if (j >= 8 && cells[i][j].getCard() == null && cells[i][j].getHero() == null) {
							cells[i][j].addCard(selectedCard);
							currentPlayer.getHand().getHandList().remove(selectedCard);
							tempMana -= selectedCard.getCardSpeed();
							cardSelectedNum = -1;
							selectedCard = null;
						}

						try {
							Thread.sleep(300);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					else if (currentPlayer == player2
							&& (posX > cells[i][j].getXLowerBoundFlipped()
									&& posX < cells[i][j].getXUpperBoundFlipped())
							&& (Math.abs(posY - 1000) > cells[i][j].getYLowerBoundFlipped()
									&& Math.abs(posY - 1000) < cells[i][j].getYUpperBoundFlipped())) {
						try {
							Thread.sleep(300);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (j <= 1 && cells[i][j].getCard() == null && cells[i][j].getHero() == null) {
							cells[i][j].addCard(selectedCard);
							currentPlayer.getHand().getHandList().remove(selectedCard);
							tempMana -= selectedCard.getCardSpeed();
							cardSelectedNum = -1;
							selectedCard = null;
						}
					}

					placeCardFromHandPhase = false;
					selectCardFromGridPhase = true;
				}
			}
		}

	}

	/**
	 * Puts the yellow highlights on the bottom two rows of the grid
	 */
	private void setUpPlayerHighlights() {
		Boolean done = false;
		int x = 900;
		int y = 800;

		while (!done) {
			playerHighlights.add(new GridImage(yellowHighlight, x, y));
			playerHighlights.add(new GridImage(yellowHighlight, x, y + 100));
			if (x < 1900)
				x += 100;

			System.out.println(x + "  " + y);
			if (x >= 1900)
				done = true;
		}
	}

	/**
	 * Fights two cards against each other and returns result of the battle
	 * @param card1 card 1 being fought
	 * @param card2 card 2 being fought
	 * @return 0 if card 1 wins, 1 if card 2 wins, 2 if it is a stalemate, and 3 if they both die
	 */
	private int combat(Card card1, Card card2) {
		int winner = 0;

		if (card1.getCardAttack() >= card2.getCardDefense())
			winner = 0;
		if (card2.getCardAttack() >= card1.getCardDefense())
			winner = 1;
		if (card1.getCardAttack() < card2.getCardDefense() && card2.getCardAttack() < card1.getCardDefense())
			winner = 2;
		if (card1.getCardAttack() >= card2.getCardDefense() && card2.getCardAttack() >= card1.getCardDefense())
			winner = 3;

		return winner;
	}

	/**
	 * Fights a card against the hero and decrements the heroe's health
	 * @param card the card fighting the hero
	 * @param hero the hero being attacked
	 */
	private void combatHero(Card card, Hero hero) {

		if (!hero.getHasBeenAttacked()) {
			hero.damageHealth(card.getCardAttack());
			hero.setHasBeenAttacked(true);
		}
	}

	/**
	 * Highlights bounds when placing card from hand
	 */
	private void calculateHighlightBounds() {

		for (GridImage hb : yellowHighlights) {
			if (hb.getXLeftBound() < highlightLeftBound)
				highlightLeftBound = hb.getXLeftBound();
			if (hb.getYTopBound() < highlightTopBound)
				highlightTopBound = hb.getYTopBound();
			if (hb.getXRightBound() > highlightRightBound)
				highlightRightBound = hb.getXRightBound();
			if (hb.getYBottomBound() > highlightBottomBound)
				highlightBottomBound = hb.getYBottomBound();
		}
	}

	/**
	 * Highlights bounds when moving card from grid
	 */
	private void highlightGridBounds(int i, int j) {
		int speed = 0;
		if(cells[i][j].getCard() != null){
			if(cells[i][j].getCard().getCardSpeed() <= tempMana)
				speed = cells[i][j].getCard().getCardSpeed();
			else if(cells[i][j].getCard().getCardSpeed() > tempMana)
				speed = 0;
		}
		if(cells[i][j].getHero() != null)
			speed = 10;
		Boolean done = false;
		int num = 0;
		int cellNum = 0;

		if (currentPlayer == player1) {
			while (!done) { // X right side
				num += 1;
				cellNum = i + num;

				if (cellNum >= 10)
					cellNum = 9;

				// highlights empty cells
				if (cells[cellNum][j].getCard() == null && cells[cellNum][j].getHero() == null && selectedGridCard.getCardSpeed() <= tempMana) {
					yellowHighlights.add(new GridImage(yellowHighlight, cells[cellNum][j].getXLowerBound(),
							cells[cellNum][j].getYLowerBound()));
				} else
					done = true;

				// highlights cell if enemy card is inside
				if (cells[cellNum][j].getCard() != null && selectedGridCard.getCardSpeed() <= tempMana) {
					if (!cells[cellNum][j].getCard().getHero().equals(currentPlayer.getHeroName()))
						yellowHighlights.add(new GridImage(yellowHighlight, cells[cellNum][j].getXLowerBound(),
								cells[cellNum][j].getYLowerBound()));
				}
				// highlights cell if enemy hero is inside
				if (cells[cellNum][j].getHero() != null && selectedGridCard.getCardSpeed() <= tempMana) {
					if (!cells[cellNum][j].getHero().getHeroName().equals(currentPlayer.getHeroName())
							&& !cells[cellNum][j].getHero().getHasBeenAttacked())
						yellowHighlights.add(new GridImage(yellowHighlight, cells[cellNum][j].getXLowerBound(),
								cells[cellNum][j].getYLowerBound()));
				}

				if (num >= speed)
					done = true;
			}

			done = false;
			num = 0;
			cellNum = 0;

			// X left side
			while (!done) {
				num -= 1;
				cellNum = i + num;

				if (cellNum <= 0)
					cellNum = 0;

				if (cells[cellNum][j].getCard() == null && cells[cellNum][j].getHero() == null && selectedGridCard.getCardSpeed() <= tempMana) {
					yellowHighlights.add(new GridImage(yellowHighlight, cells[cellNum][j].getXLowerBound(),
							cells[cellNum][j].getYLowerBound()));
				} else
					done = true;

				if (cells[cellNum][j].getCard() != null && selectedGridCard.getCardSpeed() <= tempMana) {
					if (!cells[cellNum][j].getCard().getHero().equals(currentPlayer.getHeroName()))
						yellowHighlights.add(new GridImage(yellowHighlight, cells[cellNum][j].getXLowerBound(),
								cells[cellNum][j].getYLowerBound()));
				}
				if (cells[cellNum][j].getHero() != null && selectedGridCard.getCardSpeed() <= tempMana) {
					if (!cells[cellNum][j].getHero().getHeroName().equals(currentPlayer.getHeroName())
							&& !cells[cellNum][j].getHero().getHasBeenAttacked())
						yellowHighlights.add(new GridImage(yellowHighlight, cells[cellNum][j].getXLowerBound(),
								cells[cellNum][j].getYLowerBound()));
				}

				if (Math.abs(num) >= speed)
					done = true;
			}

			done = false;
			num = 0;
			cellNum = 0;

			// Y top
			while (!done) {
				num -= 1;
				cellNum = j + num;

				if (cellNum <= 0)
					cellNum = 0;

				if (cells[i][cellNum].getCard() == null && cells[i][cellNum].getHero() == null && selectedGridCard.getCardSpeed() <= tempMana) {
					yellowHighlights.add(new GridImage(yellowHighlight, cells[i][cellNum].getXLowerBound(),
							cells[i][cellNum].getYLowerBound()));
				} else
					done = true;

				if (cells[i][cellNum].getCard() != null && selectedGridCard.getCardSpeed() <= tempMana) {
					if (!cells[i][cellNum].getCard().getHero().equals(currentPlayer.getHeroName()))
						yellowHighlights.add(new GridImage(yellowHighlight, cells[i][cellNum].getXLowerBound(),
								cells[i][cellNum].getYLowerBound()));
				}
				if (cells[i][cellNum].getHero() != null && selectedGridCard.getCardSpeed() <= tempMana) {
					if (!cells[i][cellNum].getHero().getHeroName().equals(currentPlayer.getHeroName())
							&& !cells[i][cellNum].getHero().getHasBeenAttacked())
						yellowHighlights.add(new GridImage(yellowHighlight, cells[i][cellNum].getXLowerBound(),
								cells[i][cellNum].getYLowerBound()));
				}

				if (Math.abs(num) >= speed)
					done = true;
			}

			done = false;
			num = 0;
			cellNum = 0;

			// y bot
			while (!done) {
				num += 1;
				cellNum = j + num;

				if (cellNum >= 9)
					cellNum = 9;

				if (cells[i][cellNum].getCard() == null && cells[i][cellNum].getHero() == null && selectedGridCard.getCardSpeed() <= tempMana) {
					yellowHighlights.add(new GridImage(yellowHighlight, cells[i][cellNum].getXLowerBound(),
							cells[i][cellNum].getYLowerBound()));
				} else
					done = true;

				if (cells[i][cellNum].getCard() != null && selectedGridCard.getCardSpeed() <= tempMana) {
					if (!cells[i][cellNum].getCard().getHero().equals(currentPlayer.getHeroName()))
						yellowHighlights.add(new GridImage(yellowHighlight, cells[i][cellNum].getXLowerBound(),
								cells[i][cellNum].getYLowerBound()));
				}
				if (cells[i][cellNum].getHero() != null && selectedGridCard.getCardSpeed() <= tempMana) {
					if (!cells[i][cellNum].getHero().getHeroName().equals(currentPlayer.getHeroName())
							&& !cells[i][cellNum].getHero().getHasBeenAttacked())
						yellowHighlights.add(new GridImage(yellowHighlight, cells[i][cellNum].getXLowerBound(),
								cells[i][cellNum].getYLowerBound()));
				}

				if (Math.abs(num) >= speed)
					done = true;
			}
		}
		
		if (currentPlayer == player2) {
			while (!done) { // X right side
				num += 1;
				cellNum = i + num;

				if (cellNum >= 10)
					cellNum = 9;

				// highlights empty cells
				if (cells[cellNum][j].getCard() == null && cells[cellNum][j].getHero() == null && selectedGridCard.getCardSpeed() <= tempMana) {
					yellowHighlights.add(new GridImage(yellowHighlight, cells[cellNum][j].getXLowerBoundFlipped(),
							cells[cellNum][j].getYLowerBoundFlipped()));
				} else
					done = true;

				// highlights cell if enemy card is inside
				if (cells[cellNum][j].getCard() != null && selectedGridCard.getCardSpeed() <= tempMana) {
					if (!cells[cellNum][j].getCard().getHero().equals(currentPlayer.getHeroName()))
						yellowHighlights.add(new GridImage(yellowHighlight, cells[cellNum][j].getXLowerBoundFlipped(),
								cells[cellNum][j].getYLowerBoundFlipped()));
				}
				// highlights cell if enemy hero is inside
				if (cells[cellNum][j].getHero() != null && selectedGridCard.getCardSpeed() <= tempMana) {
					if (!cells[cellNum][j].getHero().getHeroName().equals(currentPlayer.getHeroName())
							&& !cells[cellNum][j].getHero().getHasBeenAttacked())
						yellowHighlights.add(new GridImage(yellowHighlight, cells[cellNum][j].getXLowerBoundFlipped(),
								cells[cellNum][j].getYLowerBoundFlipped()));
				}

				if (num >= speed)
					done = true;
			}

			done = false;
			num = 0;
			cellNum = 0;

			// X left side
			while (!done) {
				num -= 1;
				cellNum = i + num;

				if (cellNum <= 0)
					cellNum = 0;

				if (cells[cellNum][j].getCard() == null && cells[cellNum][j].getHero() == null && selectedGridCard.getCardSpeed() <= tempMana) {
					yellowHighlights.add(new GridImage(yellowHighlight, cells[cellNum][j].getXLowerBoundFlipped(),
							cells[cellNum][j].getYLowerBoundFlipped()));
				} else
					done = true;

				if (cells[cellNum][j].getCard() != null && selectedGridCard.getCardSpeed() <= tempMana) {
					if (!cells[cellNum][j].getCard().getHero().equals(currentPlayer.getHeroName()))
						yellowHighlights.add(new GridImage(yellowHighlight, cells[cellNum][j].getXLowerBoundFlipped(),
								cells[cellNum][j].getYLowerBoundFlipped()));
				}
				if (cells[cellNum][j].getHero() != null && selectedGridCard.getCardSpeed() <= tempMana) {
					if (!cells[cellNum][j].getHero().getHeroName().equals(currentPlayer.getHeroName())
							&& !cells[cellNum][j].getHero().getHasBeenAttacked())
						yellowHighlights.add(new GridImage(yellowHighlight, cells[cellNum][j].getXLowerBoundFlipped(),
								cells[cellNum][j].getYLowerBoundFlipped()));
				}

				if (Math.abs(num) >= speed)
					done = true;
			}

			done = false;
			num = 0;
			cellNum = 0;

			// Y top
			while (!done) {
				num -= 1;
				cellNum = j + num;

				if (cellNum <= 0)
					cellNum = 0;

				if (cells[i][cellNum].getCard() == null && cells[i][cellNum].getHero() == null && selectedGridCard.getCardSpeed() <= tempMana) {
					yellowHighlights.add(new GridImage(yellowHighlight, cells[i][cellNum].getXLowerBoundFlipped(),
							cells[i][cellNum].getYLowerBoundFlipped()));
				} else
					done = true;

				if (cells[i][cellNum].getCard() != null && selectedGridCard.getCardSpeed() <= tempMana) {
					if (!cells[i][cellNum].getCard().getHero().equals(currentPlayer.getHeroName()))
						yellowHighlights.add(new GridImage(yellowHighlight, cells[i][cellNum].getXLowerBoundFlipped(),
								cells[i][cellNum].getYLowerBoundFlipped()));
				}
				if (cells[i][cellNum].getHero() != null && selectedGridCard.getCardSpeed() <= tempMana) {
					if (!cells[i][cellNum].getHero().getHeroName().equals(currentPlayer.getHeroName())
							&& !cells[i][cellNum].getHero().getHasBeenAttacked())
						yellowHighlights.add(new GridImage(yellowHighlight, cells[i][cellNum].getXLowerBoundFlipped(),
								cells[i][cellNum].getYLowerBoundFlipped()));
				}

				if (Math.abs(num) >= speed)
					done = true;
			}

			done = false;
			num = 0;
			cellNum = 0;

			// y bot
			while (!done) {
				num += 1;
				cellNum = j + num;

				if (cellNum >= 9)
					cellNum = 9;

				if (cells[i][cellNum].getCard() == null && cells[i][cellNum].getHero() == null && selectedGridCard.getCardSpeed() <= tempMana) {
					yellowHighlights.add(new GridImage(yellowHighlight, cells[i][cellNum].getXLowerBoundFlipped(),
							cells[i][cellNum].getYLowerBoundFlipped()));
				} else
					done = true;

				if (cells[i][cellNum].getCard() != null) {
					if (!cells[i][cellNum].getCard().getHero().equals(currentPlayer.getHeroName()) && selectedGridCard.getCardSpeed() <= tempMana)
						yellowHighlights.add(new GridImage(yellowHighlight, cells[i][cellNum].getXLowerBoundFlipped(),
								cells[i][cellNum].getYLowerBoundFlipped()));
				}
				if (cells[i][cellNum].getHero() != null && selectedGridCard.getCardSpeed() <= tempMana) {
					if (!cells[i][cellNum].getHero().getHeroName().equals(currentPlayer.getHeroName())
							&& !cells[i][cellNum].getHero().getHasBeenAttacked())
						yellowHighlights.add(new GridImage(yellowHighlight, cells[i][cellNum].getXLowerBoundFlipped(),
								cells[i][cellNum].getYLowerBoundFlipped()));
				}

				if (Math.abs(num) >= speed)
					done = true;
			}
		}
	}
}