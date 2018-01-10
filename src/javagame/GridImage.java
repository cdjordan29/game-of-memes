package javagame;
import org.newdawn.slick.Image;

/**
 * GridImage.java - the yellow highlights which appear on the board
 * @author Michael Taylor, Daniel Jordan
 *
 */
public class GridImage {

	Image yellowHighlight;
	int xLeftBound;
	int yTopBound;
	int xRightBound;
	int yBottomBound;

	/**
	 * Initializes the variables of GridImage
	 * @param yellowHighlight the image of the yellow square
	 * @param x the X location of the yellow square
	 * @param y the Y location of the yellow square
	 */
	public GridImage(Image yellowHighlight, int x, int y) {
		this.yellowHighlight = yellowHighlight;
		this.xLeftBound = x;
		this.yTopBound = y;
		this.xRightBound = x+100;
		this.yBottomBound = y+100;
	}
	
	/**
	 * Gets the left X bound of the highlight
	 * @return the X left bound
	 */
	public int getXLeftBound(){
		return this.xLeftBound;
	}
	
	/**
	 * Gets the right X bound of the highlight
	 * @return the X right bound
	 */
	public int getXRightBound(){
		return this.xRightBound;
	}
	
	/**
	 * Gets the top Y bound of the highlight
	 * @return the Y top bound
	 */
	public int getYTopBound(){
		return this.yTopBound;
	}
	
	/**
	 * Gets the Y bottom bound of the highlight
	 * @return the Y bottom bound
	 */
	public int getYBottomBound(){
		return this.yBottomBound;
	}

	/**
	 * Draws the yellow highlight on the board at the top left bound.
	 */
	public void drawImage() {
		if(xLeftBound < 900){
			xLeftBound = 900;
		}
		yellowHighlight.draw(xLeftBound, yTopBound);
	}
}
