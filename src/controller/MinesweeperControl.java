/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Minesweeper Game
 * @author AnaValderrama25 - 25/08/2018
 * Universidad Icesi
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package controller;

import model.MinesweeperBoard;
import view.MinesweeperView;

/**
 * This class represents Minesweeper control. <br>
 */
public class MinesweeperControl {

	// -----------------------------------------------------------------
	// Attributes
	// -----------------------------------------------------------------

	/**
	 * It's the game view
	 */
	public static MinesweeperView view;

	/**
	 * It's the game board (logic)
	 */
	public static MinesweeperBoard gameBoard;

	// -----------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------

	/**
	 * Run Minesweeper Game
	 * 
	 * @param args
	 *            parameters needed to run the application
	 */
	public static void main(String[] args) {

		// Initialize view
		view = new MinesweeperView();

		try {
			while (true) {
				// Display start game message
				view.startMessage();

				// Game board config
				String config = view.boardConfigInput();
				buildGameBoard(config);

				// Show game instructions
				view.instructions();

				// Show the board built before
				view.showBoard(gameBoard.toString());

				while (gameBoard.getGameStatus() == MinesweeperBoard.NEXT_MOVE) {
					String move = view.nextMove();
					makeaMove(move);
					view.showBoard(gameBoard.toString());

					if (gameBoard.getGameStatus() == MinesweeperBoard.GAME_OVER) {
						view.gameOver();
					} else if (gameBoard.getGameStatus() == MinesweeperBoard.WIN) {
						view.winner();
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Make a move
	 * 
	 * @param moveSentence
	 *            Player's move
	 */
	public static void makeaMove(String moveSentence) {
		String[] move = moveSentence.split(" ");
		int row = 0;
		int column = 0;
		String type = "";
		try {
			row = Integer.parseInt(move[0]);
			column = Integer.parseInt(move[1]);
			type = move[2];

			// Check if it's a valid move
			if (type.equals(MinesweeperBoard.MARK.toString())
					|| type.equals(MinesweeperBoard.UNCOVER.toString())) {
				if (type.equals(MinesweeperBoard.MARK.toString())) {
					gameBoard.markCell(row - 1, column - 1);
				} else if (type.equals(MinesweeperBoard.UNCOVER.toString())) {
					gameBoard.uncoverCell(row - 1, column - 1);
				}

			} else {
				view.wrongMove();
			}
		} catch (Exception e) {
			view.wrongMove();
			;
		}
	}

	/**
	 * Builds a new game board
	 * 
	 * @param config
	 *            Settings input
	 */
	public static void buildGameBoard(String config) {
		String[] settings = config.split(" ");
		int rows = 0;
		int columns = 0;
		int mines = 0;
		try {
			rows = Integer.parseInt(settings[0]);
			columns = Integer.parseInt(settings[1]);
			mines = Integer.parseInt(settings[2]);
			// Check if the number of mines is valid
			if (mines < (rows * columns)) {
				gameBoard = new MinesweeperBoard(rows, columns, mines);
				System.out.println("tablerocreado");
			} else {
				view.wrongConfig();
			}
		} catch (Exception e) {
			view.wrongConfig();
		}

	}
}
