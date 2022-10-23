package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * An abstract class with operations for an abstract {@code MarbleSolitaireModel}.
 * This class includes all methodologies expected for the model of a
 * {@code MarbleSolitaire} game.
 */
public abstract class AMarbleSolitaireModel implements MarbleSolitaireModel {

  // thickness of the central board (excluding the joined sides).
  protected int armThickness;
  // the X-coordinate of the empty slot.
  protected int sRow;
  // the Y-coordinate of the empty slot.
  protected int sCol;
  // represents the game board.
  public ArrayList<ArrayList<SlotState>> board;
  // represents the largest length of the game board.


  /**
   * Determines if the supplied movement is valid or invalid.
   *
   * @param fromRow The X-Coordinate that the Marble is moving from.
   * @param fromCol The Y-Coordinate that the Marble is moving from.
   * @param toRow   The X-Coordinate that the Marble is moving to.
   * @param toCol   The Y-Coordinate that the Marble is moving to.
   * @return true when the move is invalid, false if the move is valid.
   */
  protected boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    // if neither are outside, return true. Else, return false instantly.
    return (!((this.outsideBoard(fromRow, fromCol) || (this.outsideBoard(toRow, toCol))))
            // If the position is valid (within the board), then:
            // Verifies that there is a marble in the FROM position.
            // if there is a marble, then it's valid
            && (this.board.get(fromRow).get(fromCol)) == SlotState.Marble
            // Makes sure that the to position is empty.
            // if it is empty, then it's a valid move.
            && (this.board.get(toRow).get(toCol)) == SlotState.Empty
            // Verifies that either it's same row and two apart column wise (horizontal)
            // OR it's same col and two rows apart (vertical)
            && (((toRow == fromRow) && Math.abs(toCol - fromCol) == 2)
            || ((toCol == fromCol) && Math.abs(toRow - fromRow) == 2))
            // Checks if there is a marble in between them.
            && (this.board.get((toRow - fromRow) / 2 + fromRow).get((toCol - fromCol) / 2
            + fromCol)) == SlotState.Marble);
  }

  /**
   * Moves a Marble from the supplied fromRow and fromCol position
   * to the supplied toRow and toCol position.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException if the supplied coordinates denote an invalid movement.
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    // Makes sure that the from and to coordinates are valid. If not, throw an exception.
    if (this.isValidMove(fromRow, fromCol, toRow, toCol)) {
      // sets the from slot to empty.
      this.board.get(fromRow).set(fromCol, SlotState.Empty);
      // sets the slot in between the from slot and to slot as empty.
      this.board.get(fromRow + (toRow - fromRow) / 2).set((fromCol + (toCol - fromCol) / 2),
              SlotState.Empty);
      // sets the to slot as a marble.
      this.board.get(toRow).set(toCol, SlotState.Marble);
    } else {
      throw new IllegalArgumentException("Not a Valid Move.");
    }
  }

  /**
   * Determines if there exists any valid move left in the game board.
   *
   * @return True if the game is over, false if an acceptable move still exists in the board.
   */
  @Override
  public boolean isGameOver() {
    // traverse the board.
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        // Checks if left move is valid
        if (i - 2 >= 0 && (isValidMove(i, j, i - 2, j))) {
          return false;
          // Checks if right is a valid move
        } else if (i + 2 < this.getBoardSize() && (isValidMove(i, j, i + 2, j))) {
          return false;
          // Checks if up is a valid move
        } else if (j + 2 < this.getBoardSize() && (isValidMove(i, j, i, j + 2))) {
          return false;
          // Checks if down is a valid move
        } else if (j - 2 >= 0 && (isValidMove(i, j, i, j - 2))) {
          return false;
        } else if (j + 2 < this.getBoardSize() && i + 2 < this.getBoardSize()
                && (isValidMove(i, j, i + 2, j + 2))) {
          return false;
        } else if (j - 2 >= 0 && i - 2 >= 0 && (isValidMove(i, j, i - 2, j - 2))) {
          return false;
        }
      }
    }
    // If none of the moves are valid, then the game is over, and we return true.
    return true;
  }

  /**
   * Gets the maximum length/height of the game board.
   *
   * @return the integer value that denotes the game board's maximum length/height.
   */
  @Override
  public int getBoardSize() {
    return ((this.armThickness - 1) * 2) + this.armThickness;
  }

  /**
   * Get the state of the slot at a given position on the board.
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return the slot's state. Either Empty, Marble, or Invalid.
   * @throws IllegalArgumentException if the row and column is outside the board.
   */
  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    // If it's outside the board, then it's invalid or not within board restrictions.
    if (row < 0 || col < 0 || row >= this.getBoardSize() || col >= this.getBoardSize()) {
      throw new IllegalArgumentException("Not within board restrictions");
    } else {
      return this.board.get(row).get(col);
    }
  }

  /**
   * Returns the number of marbles currently on the board.
   *
   * @return an integer value denoting the number of marbles currently on the board.
   */
  @Override
  public int getScore() {
    // traverse board.
    int score = 0;
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        // everytime we see a marble, add one to the score.
        if (this.getSlotAt(i, j) == SlotState.Marble) {
          score = score + 1;
        }
      }
    }
    // returns the score (number of marbles on the board).
    return score;
  }

  /**
   * Generates the board based on the given constructor parameters.
   *
   * @param sRow The X-coordinate of the empty slot on the board.
   * @param sCol The Y-coordinate of the empty slot on the board
   * @return an ArrayList of ArrayList of SlotState representing the
   *         various slots on the game board (row and column).
   */
  protected ArrayList<ArrayList<SlotState>> generateBoard(int sRow, int sCol) {
    //say that we're given a boardSize of 7 (armThickness of 3)
    ArrayList<ArrayList<SlotState>> result = new ArrayList<ArrayList<SlotState>>();
    ArrayList<SlotState> vertical;
    // so, we do loop up util the edge of the board (< this.getBoardSize())
    for (int i = 0; i < this.getBoardSize(); i++) {
      // for each column, make a row.
      vertical = new ArrayList<SlotState>();
      for (int j = 0; j < this.getBoardSize(); j++) {
        // if it falls outside the board, make the slot invalid
        if (outsideBoard(i, j)) {
          vertical.add(SlotState.Invalid);
          // if it's in the center, make it empty
        } else if (i == sRow && j == sCol) {
          vertical.add(SlotState.Empty);
          // everything else fill with marble
        } else {
          vertical.add(SlotState.Marble);
        }
      }
      result.add(vertical);
    }
    // returns the resulting ArrayList<ArrayList<SlotState>>
    return result;
  }

  /**
   * Determines if the given slot position falls outside the game board.
   *
   * @param xCor the x-position of a slot.
   * @param yCor the y-position of a slot.
   * @return False if either the xCor or yCor falls outside playable slots of the board.
   *         else, returns true;
   */
  protected abstract boolean outsideBoard(int xCor, int yCor);

}
