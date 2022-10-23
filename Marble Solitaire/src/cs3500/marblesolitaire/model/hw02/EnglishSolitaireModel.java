package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.AMarbleSolitaireModel;

/**
 * Represents an English implementation of the Peg Solitaire game.
 */
public class EnglishSolitaireModel extends AMarbleSolitaireModel implements MarbleSolitaireModel {

  /**
   * A Constructor which takes in no parameters, and initializes the game
   * with an armThickness of 3 and an empty slot in the center (3,3).
   */
  public EnglishSolitaireModel() {
    this.armThickness = 3;
    // For an armThickness of 3, sRow =3 , and sCol =3
    // For an armThickness of 5, sRow = 6, and sCol = 6
    this.sRow = armThickness - 1 + ((armThickness - 1) / 2);
    this.sCol = armThickness - 1 + ((armThickness - 1) / 2);
    this.board = generateBoard(this.sRow, this.sCol);
  }

  /**
   * A second Constructor which takes an sRow and an sCol and sets the armThickness as 3.
   *
   * @param sRow the X-Position of the empty slot.
   * @param sCol the Y-Position of the empty slot.
   */
  public EnglishSolitaireModel(int sRow, int sCol) {
    this.armThickness = 3;
    // throws an IllegalArgumentException if the supplied
    // sRow and sCol is outside the board (in an Invalid position
    // or outside the boundaries of the board).
    if (this.outsideBoard(sRow, sCol)) {
      throw new IllegalArgumentException("Invalid empty cell position ("
              + sRow + "," + sCol + ")");

      // else, initializes the fields.
    } else {
      this.sRow = sRow;
      this.sCol = sCol;
      this.board = this.generateBoard(sRow, sCol);
    }
  }

  /**
   * A third Constructor which takes in only an armThickness, and sets the empty
   * slot in the center of the board.
   *
   * @param armThickness represents the number of marbles in the top, bottom, left, or right row
   */
  public EnglishSolitaireModel(int armThickness) {
    // if the armThickness is odd, or if it's less than or equal to 0
    // then throw an exception.
    if (armThickness % 2 != 1 || armThickness <= 0) {
      throw new IllegalArgumentException("armThickness must be non-negative and odd");
    } else {
      this.armThickness = armThickness;
      this.sRow = armThickness - 1 + ((armThickness - 1) / 2);
      this.sCol = armThickness - 1 + ((armThickness - 1) / 2);
      this.board = this.generateBoard(this.sRow, this.sCol);
    }
  }

  /**
   * A fourth Constructor which takes in an armThickness, sRow, and sCol.
   *
   * @param armThickness represents the number of marbles in the top, bottom, left, or right row
   * @param sRow         the X-Position of the empty slot.
   * @param sCol         the Y-Position of the empty slot.
   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol) {
    // if armThickness odd or less than or equal to 0, throw an exception.
    if (armThickness % 2 != 1 || armThickness <= 0) {
      throw new IllegalArgumentException("armThickness must be non-negative and odd");
    } else {
      this.armThickness = armThickness;
    } // if the supplied sRow and sCol is outside the board boundaries and restrictions,
    // throw an exception too.
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
   * Determines if the given slot position falls outside the game board.
   *
   * @param xCor the x-position of a slot.
   * @param yCor the y-position of a slot.
   * @return False if either the xCor or yCor falls outside playable slots of the board.
   *        else, returns true;
   */
  @Override
  protected boolean outsideBoard(int xCor, int yCor) {
    // if either the row or column is outside board in top left empty region
    // for armThickness of 3, it's if xCor < index 2, and yCor < index 2
    // for armThickness of 5, it's if xCor < index 4, and yCor < index 4
    return (xCor < 0 || yCor < 0
            || (xCor < (armThickness - 1) && yCor < (armThickness - 1))
            // if either coords is outside board in bottom left empty region
            // for armThickness of 5, it's if xCor >= index 9, and yCor < index 4
            || (xCor >= ((armThickness - 1) + armThickness)
            && yCor < (armThickness - 1))
            // if either coords is outside board in top right empty region
            // for armThickness of 5, it's if xCor < index 4, and yCor >= 9
            || (xCor < (armThickness - 1) && yCor >= ((armThickness - 1) + armThickness))
            // if either is too large
            // for armThickness of 5, it's if xCor >= index 13, and yCor >= index 13
            || xCor >= ((armThickness - 1) * 2) + armThickness
            || yCor >= ((armThickness - 1) * 2) + armThickness
            // if either coords is outside board in bottom right empty region
            // for armThickness of 5, it's if xCor >= index 9, and yCor >= index 9
            || (xCor >= ((armThickness - 1) + armThickness)
            && yCor >= ((armThickness - 1) + armThickness)));
  }
}
