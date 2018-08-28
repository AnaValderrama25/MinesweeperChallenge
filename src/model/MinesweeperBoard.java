/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Minesweeper Game
 * @author AnaValderrama25 - 25/08/2018
 * Universidad Icesi
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package model;

import java.util.Random;

/**
 * This class represents Minesweeper game board. <br>
 */
public class MinesweeperBoard {

	// -----------------------------------------------------------------
	// Constants
	// -----------------------------------------------------------------

	/**
	 * Player can select another cell
	 */
	public final static int NEXT_MOVE = 1;

	/**
	 * Player win the game
	 */
	public final static int WIN = 2;

	/**
	 * Player lost the game
	 */
	public final static int GAME_OVER = 3;

	/**
	 * Player mark a cell
	 */
	public final static String MARK = "M";

	/**
	 * Player uncover a cell
	 */
	public final static String UNCOVER = "U";

	// -----------------------------------------------------------------
	// Attributes
	// -----------------------------------------------------------------

	/**
	 * It's the board's height
	 */
	private int height;

	/**
	 * It's the board's width
	 */
	private int width;

	/**
	 * Number of mines on the board
	 */
	private int mines;

	/**
	 * Cells' matrix
	 */
	private Cell boardCells[][];

	/**
	 * Change depending player's moves
	 */
	private int gameStatus;

	/**
	 * Random for use at initializeMines() method
	 */
	private Random rand;

	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	/**
	 * Builds a new game board with the characteristics player has given at the
	 * beginning Initialize board with the mines on it
	 * 
	 * @param height
	 *            board's height
	 * @param width
	 *            board's width
	 * @param mines
	 *            board´s number of mines
	 */
	public MinesweeperBoard(int height, int width, int mines) {
		super();
		this.height = height;
		this.width = width;
		this.mines = mines;
		this.gameStatus = NEXT_MOVE;

		boardCells = new Cell[height][width];
		// Initialize cells
		initializeCellsGameBoard();

		// Initialize mines on board
		rand = new Random();
		initializeMines();

		// Initialize other cells with mines around
		countMinesAround();
	}

	// -----------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------

	/**
	 * Initialize each Cell of the board
	 */
	public void initializeCellsGameBoard() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				boardCells[i][j] = new Cell();
			}
		}
	}

	/**
	 * Initialize the cells that contains a mine
	 */
	public void initializeMines() {
		// Generate random position for the mines
		for (int i = 0; i < mines; i++) {
			int randomRow = rand.nextInt(height);
			int randomCol = rand.nextInt(width);
			if (boardCells[randomRow][randomCol].isHasMine()) {
				i--;
			} else {
				boardCells[randomRow][randomCol].setHasMine(true);
			}

		}

	}

	/**
	 * Count mines around each cell of the board
	 */
	public void countMinesAround() {
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int numMines = 0;
				if (!boardCells[i][j].isHasMine()) {
					// Check up-left
					if (i > 0 && j > 0) {
						if (boardCells[i - 1][j - 1].isHasMine()) {
							numMines++;
						}
					}
					// Check up
					if (i > 0) {
						if (boardCells[i - 1][j].isHasMine()) {
							numMines++;
						}
					}
					// Check up-right
					if (i > 0 && j < width - 1) {
						if (boardCells[i - 1][j + 1].isHasMine()) {
							numMines++;
						}
					}
					// Check left
					if (j > 0) {
						if (boardCells[i][j - 1].isHasMine()) {
							numMines++;
						}
					}
					// Check right
					if (j < width - 1) {
						if (boardCells[i][j + 1].isHasMine()) {
							numMines++;
						}
					}
					// Check down-left
					if (i < (height - 1) && j > 0) {
						if (boardCells[i + 1][j - 1].isHasMine()) {
							numMines++;
						}
					}
					// Check down
					if (i < height - 1) {
						if (boardCells[i + 1][j].isHasMine()) {
							numMines++;
						}
					}
					// Check down-right
					if (i < (height - 1) && j < (width - 1)) {
						if (boardCells[i + 1][j + 1].isHasMine()) {
							numMines++;
						}
					}
					boardCells[i][j].setNumMines(numMines);
				}
			}
		}
	}

	/**
	 * Mark a cell that a player thinks contains a mine
	 * 
	 * @param r
	 *            indicates cell's row that it's going to be marked
	 * @param c
	 *            indicates cell's column that it's going to be marked
	 */
	public void markCell(int r, int c) {
		if (boardCells[r][c].getState() == Cell.UNSELECTED) {
			boardCells[r][c].setState(Cell.MARKED);
		}
	}

	/**
	 * Uncover a cell that a player thinks contains a mine
	 * 
	 * @param r
	 *            indicates cell's row that it's going to be uncover
	 * @param c
	 *            indicates cell's column that it's going to be uncover
	 */
	public void uncoverCell(int r, int c) {
		if (boardCells[r][c].getState() == Cell.UNSELECTED) {
			if (boardCells[r][c].isHasMine()) {
				setGameStatus(MinesweeperBoard.GAME_OVER);
				showDeadBoard();
			}
			if (boardCells[r][c].getNumMines() != 0) {
				boardCells[r][c].setState(Cell.UNCOVERED);
				if (!finishGame()) {
					setGameStatus(MinesweeperBoard.NEXT_MOVE);
				} else {
					setGameStatus(MinesweeperBoard.WIN);
				}
			} else if (boardCells[r][c].getNumMines() == 0 && !boardCells[r][c].isHasMine()) {
				boardCells[r][c].setState(Cell.DISABLE);
				findEmptiesAround(boardCells[r][c], r, c);
				setGameStatus(MinesweeperBoard.NEXT_MOVE);
			}
		}
	}

	/**
	 * Show the mines if the user lose
	 */
	public void showDeadBoard() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (boardCells[i][j].isHasMine()) {
					boardCells[i][j].setState(Cell.MINE);
				}
			}
		}
	}

	/**
	 * Discover which cells can be uncovered
	 * 
	 * @param cell
	 *            Cell that is going to be uncovered with others nearby
	 * @param r
	 *            indicates cell's row that it's going to be uncover
	 * @param c
	 *            indicates cell's column that it's going to be uncover
	 */
	public void findEmptiesAround(Cell cell, int r, int c) {

		// We have to check adjacent cells to uncover the ones that doesn't have
		// any mine near
		// just like the checking did before on countMinesAround()
		// If the cell is unselected and doesn't have a mine it can be
		// uncovered

		// Check up-left
		if (r > 0 && c > 0) {

			Cell tempCell = boardCells[r - 1][c - 1];
			int r1 = r - 1;
			int c1 = c - 1;
			if (tempCell.getState() == Cell.UNSELECTED
					&& !(tempCell.isHasMine())) {
				if (tempCell.getNumMines() != 0) {
					tempCell.setState(Cell.UNCOVERED);
				} else if (tempCell.getNumMines() == 0) {
					tempCell.setState(Cell.DISABLE);
					findEmptiesAround(tempCell, r1, c1);
				}
			}
		}
		// Check up
		if (r > 0) {
			Cell tempCell = boardCells[r - 1][c];
			int r1 = r - 1;
			int c1 = c;
			if (tempCell.getState() == Cell.UNSELECTED
					&& !(tempCell.isHasMine())) {
				if (tempCell.getNumMines() != 0) {
					tempCell.setState(Cell.UNCOVERED);
				} else if (tempCell.getNumMines() == 0) {
					tempCell.setState(Cell.DISABLE);
					findEmptiesAround(tempCell, r1, c1);
				}
			}
		}
		// Check up-right
		if (r > 0 && c < width - 1) {
			Cell tempCell = boardCells[r - 1][c + 1];
			int r1 = r - 1;
			int c1 = c + 1;
			if (tempCell.getState() == Cell.UNSELECTED
					&& !(tempCell.isHasMine())) {
				if (tempCell.getNumMines() != 0) {
					tempCell.setState(Cell.UNCOVERED);
				} else if (tempCell.getNumMines() == 0) {
					tempCell.setState(Cell.DISABLE);
					findEmptiesAround(tempCell, r1, c1);
				}
			}
		}
		// Check left
		if (c > 0) {
			Cell tempCell = boardCells[r][c - 1];
			int r1 = r;
			int c1 = c - 1;
			if (tempCell.getState() == Cell.UNSELECTED
					&& !(tempCell.isHasMine())) {
				if (tempCell.getNumMines() != 0) {
					tempCell.setState(Cell.UNCOVERED);
				} else if (tempCell.getNumMines() == 0) {
					tempCell.setState(Cell.DISABLE);
					findEmptiesAround(tempCell, r1, c1);
				}
			}
		}
		// Check right
		if (c < width - 1) {
			Cell tempCell = boardCells[r][c + 1];
			int r1 = r;
			int c1 = c + 1;
			if (tempCell.getState() == Cell.UNSELECTED
					&& !(tempCell.isHasMine())) {
				if (tempCell.getNumMines() != 0) {
					tempCell.setState(Cell.UNCOVERED);
				} else if (tempCell.getNumMines() == 0) {
					tempCell.setState(Cell.DISABLE);
					findEmptiesAround(tempCell, r1, c1);
				}
			}
		}
		// Check down-left
		if (r < (height - 1) && c > 0) {
			Cell tempCell = boardCells[r + 1][c - 1];
			int r1 = r + 1;
			int c1 = c - 1;
			if (tempCell.getState() == Cell.UNSELECTED
					&& !(tempCell.isHasMine())) {
				if (tempCell.getNumMines() != 0) {
					tempCell.setState(Cell.UNCOVERED);
				} else if (tempCell.getNumMines() == 0) {
					tempCell.setState(Cell.DISABLE);
					findEmptiesAround(tempCell, r1, c1);
				}
			}
		}
		// Check down
		if (r < height - 1) {
			Cell tempCell = boardCells[r + 1][c];
			int r1 = r + 1;
			int c1 = c;
			if (tempCell.getState() == Cell.UNSELECTED
					&& !(tempCell.isHasMine())) {
				if (tempCell.getNumMines() != 0) {
					tempCell.setState(Cell.UNCOVERED);
				} else if (tempCell.getNumMines() == 0) {
					tempCell.setState(Cell.DISABLE);
					findEmptiesAround(tempCell, r1, c1);
				}
			}
		}
		// Check down-right
		if (r < (height - 1) && c < (width - 1)) {
			Cell tempCell = boardCells[r + 1][c + 1];
			int r1 = r + 1;
			int c1 = c + 1;
			if (tempCell.getState() == Cell.UNSELECTED
					&& !(tempCell.isHasMine())) {
				if (tempCell.getNumMines() != 0) {
					tempCell.setState(Cell.UNCOVERED);
				} else if (tempCell.getNumMines() == 0) {
					tempCell.setState(Cell.DISABLE);
					findEmptiesAround(tempCell, r1, c1);
				}
			}
		}

	}

	/**
	 * Check if the game is finished
	 * 
	 * @return true if the game has finished
	 */
	public boolean finishGame() {
		boolean finish = false;
		int numRealMines = 0;
		int uncovered = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (boardCells[i][j].isHasMine()
						&& boardCells[i][j].getState() == Cell.MARKED) {
					numRealMines++;
				}
				if (boardCells[i][j].getState() == Cell.UNCOVERED) {
					uncovered++;
				}
			}
		}
		if ((height * width) == (uncovered + numRealMines)) {
			finish = true;
		}
		return finish;
	}

	/**
	 * Give Board's height
	 * 
	 * @return height of the board
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Change Board's height
	 * 
	 * @param height
	 *            Board's new height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Gives Board's width
	 * 
	 * @return Board's width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Change Board's height
	 * 
	 * @param height
	 *            Board's new height
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Gives the number of mines on the board
	 * 
	 * @return Board's width
	 */
	public int getMines() {
		return mines;
	}

	/**
	 * Change number of mines that are on the board
	 * 
	 * @param mines
	 *            Number of mines of the board
	 */
	public void setMines(int mines) {
		this.mines = mines;
	}

	/**
	 * Gives Board's cells
	 * 
	 * @return Board's cells
	 */
	public Cell[][] getBoardCells() {
		return boardCells;
	}

	/**
	 * Change Board's matrix of cells
	 * 
	 * @param boardCells
	 *            Board's cells
	 */
	public void setBoardCells(Cell[][] boardCells) {
		this.boardCells = boardCells;
	}

	/**
	 * Gives game status
	 * 
	 * @return if the player can continue, if he wins or loses
	 */
	public int getGameStatus() {
		return gameStatus;
	}

	/**
	 * Change game status
	 * 
	 * @param gameStatus
	 *            Game actual status
	 */
	public void setGameStatus(int gameStatus) {
		this.gameStatus = gameStatus;
	}

	/**
	 * Gives matrix structure of the board
	 */
	public String toString() {
		String gameBoard = "";
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				gameBoard += boardCells[i][j].toString() + " ";
			}
			gameBoard += "\n";
		}
		return gameBoard;
	}

}
