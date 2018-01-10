package javagame;

/**
 * Data.java - Holds data for use inbetween states
 * @author Michael Taylor, Daniel Jordan
 *
 */
public class Data 
{
	Player player1;
	Player player2;
	String heroWinner;
	
	/**
	 * Sets the winner of the match
	 * @param heroWinner The hero name of the winner
	 */
	public void setWinner(String heroWinner){
		this.heroWinner = heroWinner;
	}
	
	/**
	 * Gets the winner of the match
	 * @return hero name of the winner of the match
	 */
	public String getWinner(){
		return this.heroWinner;
	}
	
	/**
	 * Sets player 1 so it can be accessed through other classes
	 * @param player the player data for player1
	 */
	public void setPlayer1(Player player)
	{
		this.player1 = player;
	}
	
	/**
	 * gets player 1's information
	 * @return player1's information
	 */
	public Player getPlayer1()
	{
		return this.player1;
	}
	
	/**
	 * Sets player 2 so it can be accessed through other classes
	 * @param player the player data for player2
	 */
	public void setPlayer2(Player player){
		this.player2 = player;
	}
	
	/**
	 * gets player 2's information
	 * @return player2's information
	 */
	public Player getPlayer2(){
		return this.player2;
	}
}
