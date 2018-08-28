/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Minesweeper Game
 * @author AnaValderrama25 - 25/08/2018
 * Universidad Icesi
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package model;

/**
 * This class represents a cell of the game board. <br>
 */
public class Cell {

	// -----------------------------------------------------------------
	// Constants
	// -----------------------------------------------------------------

	/**
	 * Unselected cell that can be uncovered or marked
	 */
	public final static int UNSELECTED = 1;

	/**
	 * Uncovered cell
	 */
	public final static int UNCOVERED = 2;

	/**
	 * Disable cell that can't be modified by the user
	 */
	public final static int DISABLE = 3;

	/**
	 * Marked cell is represented with a flag to indicate that there is a mine
	 */
	public final static int MARKED = 4;

	/**
	 * Cell has a mine
	 */
	public final static int MINE = 5;

	// -----------------------------------------------------------------
	// Attributes
	// -----------------------------------------------------------------

	/**
	 * It's the state of the cell
	 */
	private int state;

	/**
	 * It's the number of mines that a cell has around
	 */
	private int numMines;

	/**
	 * If a cell has a mine or not
	 */
	private boolean hasMine;

	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	/**
	 * Builds a cell with unselected state, with 0 mines around and without a
	 * mine inside.
	 */
	public Cell() {
		super();
		this.state = UNSELECTED;
		this.numMines = 0;
		this.hasMine = false;
	}

	// -----------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------

	/**
	 * Gives cell state
	 * 
	 * @return the state of the cell
	 */
	public int getState() {
		return state;
	}

	/**
	 * Change cell sate
	 * 
	 * @param state
	 *            new state of a cell
	 */
	public void setState(int state) {
		this.state = state;
	}

	/**
	 * Gives number of mines that a cell has around
	 * 
	 * @return number of mines around a cell
	 */
	public int getNumMines() {
		return numMines;
	}

	/**
	 * Change number of mines around a cell
	 * 
	 * @param numMines
	 *            number of mines around a cell
	 */
	public void setNumMines(int numMines) {
		this.numMines = numMines;
	}

	/**
	 * Returns if a cell has a mine or not
	 * 
	 * @return true when a cell has a mine
	 */
	public boolean isHasMine() {
		return hasMine;
	}

	/**
	 * Change the cell state putting a mine inside
	 * 
	 * @param hasMine
	 *            that is true if a cell has a mine
	 */
	public void setHasMine(boolean hasMine) {
		this.hasMine = hasMine;
	}

	/**
	 * Returns the characters that are needed for the representation of the
	 * board depending cell state
	 * 
	 * @return String representation of cell state
	 */
	public String toString() {

		String representation = "";

		if (getState() == UNSELECTED) {
			representation = ".";
		}
		if (getState() == DISABLE) {
			representation = "-";
		}
		if (getState() == MINE) {
			representation = "*";
		}
		if (getState() == MARKED) {
			representation = "P";
		}
		if (getState() == UNCOVERED) {
			if (getNumMines() == 0) {
				representation = "-";
			} else {
				representation = getNumMines() + "";
			}
		}

		return representation;
	}

}
