package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Represents a European implementation of the Peg Solitaire game.
 * This class contains methodologies that are expected for the model of a {@code MarbleSolitaire}
 * game.
 */
public class EuropeanSolitaireModel extends AMarbleSolitaireModel implements MarbleSolitaireModel {

  /**
   * A default constructor (no parameters) that creates an octagonal {@code MarbleSolitaire} board
   * whose sides have a length of 3, with the empty slot in the center of the board.
   */
  public EuropeanSolitaireModel() {
    this.armThickness = 3;
    this.sRow = armThickness - 1 + ((armThickness - 1) / 2);
    this.sCol = armThickness - 1 + ((armThickness - 1) / 2);
    this.board = this.generateBoard(this.sRow, this.sCol);
  }

  /**
   * A constructor with a single parameter (the side length) that creates a {@code MarbleSolitaire}
   * board with the specified side length, and the empty slot in the center of the board.
   */

  public EuropeanSolitaireModel(int armThickness) throws IllegalArgumentException {
    if (armThickness % 2 != 1 || armThickness <= 0) {
      throw new IllegalArgumentException("Side length of board must be non-negative and odd");
    } else {
      this.armThickness = armThickness;
      this.sRow = armThickness - 1 + ((armThickness - 1) / 2);
      this.sCol = armThickness - 1 + ((armThickness - 1) / 2);
      this.board = this.generateBoard(this.sRow, this.sCol);
    }
  }

  /**
   * A constructor with two parameters (row, col), to specify the initial position of the empty
   * slot, in a board of default size 3 that creates a {@code MarbleSolitaire} game.
   */

  public EuropeanSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    this.armThickness = 3;
    if (this.outsideBoard(sRow, sCol)) {
      throw new IllegalArgumentException("Invalid empty cell position (" +
              sRow + "," + sCol + ")");
    } else {
      this.sRow = sRow;
      this.sCol = sCol;
      this.board = this.generateBoard(sRow, sCol);
    }
  }

  /**
   * A constructor with three parameters (side length, row, col) to specify the size of the board
   * and the initial position of the empty slot that creates a {@code MarbleSolitaire} game.
   */
  public EuropeanSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    if (armThickness % 2 != 1 || armThickness <= 0) {
      throw new IllegalArgumentException("Side length of board must be non-negative and odd");
    } else {
      this.armThickness = armThickness;
    }
    if (this.outsideBoard(sRow, sCol)) {
      throw new IllegalArgumentException("Invalid empty cell position ("
              + sRow + "," + sCol + ")");
    } else {
      this.sCol = sCol;
      this.sRow = sRow;
      this.board = this.generateBoard(this.sRow, this.sCol);
    }
  }

  /**
   * Determines if the given slot position (represented by xCor and yCor)
   * falls outside the game board.
   *
   * @param xCor the x-position of a slot.
   * @param yCor the y-position of a slot.
   * @return False if either xCor, yCor, or xCor and yCor falls outside
   *         the playable slots of the board. Else, returns true.
   */
  @Override
  protected boolean outsideBoard(int xCor, int yCor) {
    return (xCor < 0 || yCor < 0
            || xCor + yCor >= (5 * this.armThickness) - 4
            || xCor + yCor < this.armThickness - 1
            || xCor <= yCor - (2 * this.armThickness) + 1
            || yCor <= xCor - (2 * this.armThickness) + 1);
  }

}
