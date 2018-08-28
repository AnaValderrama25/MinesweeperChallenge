/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Minesweeper Game
 * @author AnaValderrama25 - 25/08/2018
 * Universidad Icesi
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package view;

import java.util.Scanner;

/**
 * This class represents Minesweeper view. <br>
 */
public class MinesweeperView {

	// -----------------------------------------------------------------
	// Attributes
	// -----------------------------------------------------------------

	/**
	 * Scanner to read the user input
	 */
	public Scanner scnr;

	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	/**
	 * Builds Minesweeper view Initialize scanner for player inputs
	 */
	public MinesweeperView() {
		super();
		scnr = new Scanner(System.in);
	}

	/**
	 * Prints start message
	 */
	public void startMessage() {
		System.out
				.println("Hello World! \n"
						+ "Do you remember me? I'm the funniest game of your childhood, MINESWEEPER! \n"
						+ "In case you didn't recognize me, I present the instructions bellow: \n"
						+ "To start choose a height, width and number of mines that you want for the board \n"
						+ "Example: 3 4 5, it creates a board 3x4 and put 5 mines inside it \n"
						+ " *You can't choose a number of mines bigger than the quantity of cells numberMines<(height*width) \n");
	}

	/**
	 * Input of board's characteristics
	 * 
	 * @return a String with board's characteristics
	 */
	public String boardConfigInput() {
		String charact = scnr.nextLine();
		return charact;
	}

	/**
	 * Prints game instructions
	 */
	public void instructions() {
		System.out
				.println("Now you create the board, I'm going to explain you my moves ;) \n"
						+ "* You have to make a choise between two type of moves: mark (M) - to put a flag where you think there is a mine or uncover (U) \n"
						+ "* The structure defined to make a move is A B C where A and B reference to row and column index of the cell position on the board - cell(A,B) \n"
						+ "  And C corresponds type of movement - M or U \n"
						+ "Let's start!!!");
	}

	/**
	 * User input making a move
	 * 
	 * @return a String with player's movement
	 */
	public String nextMove() {
		System.out.println("It's your turn! \n");
		String input = scnr.nextLine();
		return input;
	}

	/**
	 * Print game board
	 * 
	 * @param board
	 *            current game board
	 */
	public void showBoard(String board) {
		System.out.println("Gameboard:\n" + "\n"+ board);
	}

	/**
	 * Prints game over message
	 */
	public void gameOver() {
		System.out.println("Sorry, You lose! \n" + "G A M E  O V E R ! ! ! \n");
	}

	/**
	 * Prints congratulations to the winner
	 */
	public void winner() {
		System.out.println("Congrats! You're a great player \n");
	}

	/**
	 * Prints an error message about a wrong move
	 */
	public void wrongMove() {
		System.out.println("Check your move sintaxis! \n");
	}

	/**
	 * Prints an error message about a wrong config given
	 */
	public void wrongConfig() {
		System.out.println("You're creating an invalid board :( \n");
	}

}
