
package javagame;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Grid class represents the grid playing field in Project 5
 * @author Michael Taylor, Daniel Jordan
 * @version 1.0
 */
public class Grid {

	/**
	 * Instance variables for Grid class
	 */
	private Cell[][] grid;
	private Image gridImage;

	/**
	 * Constructor for the Grid class
	 * 
	 * @throws SlickException
	 */
	public Grid() throws SlickException {
		Image image = new Image("res/Game_Images/grid.png");
		grid = new Cell[10][10];
		initCellArray();
		this.gridImage = image;
	}
	
	/**
	 * Creates the cell array for both rotated and normal orientations
	 */
	public void createCellArray(){
		int x = 900;
		int y = 0;
		
		int xFlipped = 1900;
		int yFlipped = 1000;
		
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[i].length; j++)
			{
				grid[i][j].setXLowerBound(x+(100*i));
				grid[i][j].setXUpperBound(x+(100*i)+100);
				grid[i][j].setYLowerBound(y+(100*j));
				grid[i][j].setYUpperBound(y+(100*j)+100);
				grid[i][j].setXLowerBoundFlipped(xFlipped-(100*i)-100);
				grid[i][j].setXUpperBoundFlipped(xFlipped-(100*i));
				grid[i][j].setYLowerBoundFlipped(yFlipped-(100*j)-100);
				grid[i][j].setYUpperBoundFlipped(yFlipped-(100*j));
				System.out.println(grid[i][j].getXLowerBound() + ", " + grid[i][j].getYLowerBound());
			}
			
			xFlipped = 1900;
			yFlipped = 1000;
			x = 900;
			y = 0;

		}
	}
	
	/**
	 * Gets the cell array which represents the play grid
	 * @return grid the cell array grid
	 */
	public Cell[][] getCellArray(){
		return this.grid;
	}

	/**
	 * Fights two cards against each other
	 * @param card1 a card being fought
	 * @param card2 another card being fought
	 * @return the winner between the two cards
	 */
	public Card combat(Card card1, Card card2) {

		Card winner = null;
		if (card1.getCardSpeed() > card2.getCardSpeed()) {

			if (card1.getCardAttack() > card2.getCardDefense()) {

				winner = card1;
			}
		}
		
		return winner;
	}

	/**
	 * Rotates the grid
	 * @param arr the current grid array
	 * @return the new grid which was made
	 */
	public int[][] rorateGrid(int[][] arr) {
		int[][] newArray = new int[arr[0].length][arr.length];

		int ii = 0;
		int jj = 0;
		
		for (int i = 0; i < arr[0].length; i++) {
			for (int j = arr.length - 1; j >= 0; j--) {
				newArray[ii][jj] = arr[i][j];

				jj++;
			}
			ii++;
		}

		return newArray;
	}
	
	/**
	 * Initializes all cells in the array.
	 */
	private void initCellArray(){
		for(int i = 0; i < grid.length; i++)
			for(int j = 0; j < grid[i].length; j++)
				grid[i][j] = new Cell();
	}
	
	/**
	 * Draws the grid board image
	 */
	public void drawGrid(){
		this.gridImage.draw(900, 0);
	}
	
	/**
	 * Draws the rotated version of the board image.
	 */
	public void rotateGrid(){
		this.gridImage.rotate(180);
	}
}
