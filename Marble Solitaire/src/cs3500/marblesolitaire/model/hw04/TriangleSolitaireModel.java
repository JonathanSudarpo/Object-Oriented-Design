package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Represents a Triangular implementation of the Peg Solitaire game.
 */
public class TriangleSolitaireModel extends AMarbleSolitaireModel implements MarbleSolitaireModel {

  // the largest row length of the board.
  private final int dimensions;


  /**
   * A default constructor (no parameters) that creates a 5-row triangular {@code MarbleSolitaire}
   * game with the empty slot at (0,0).
   */
  public TriangleSolitaireModel() {
    this.sRow = 0;
    this.sCol = 0;
    this.dimensions = 5;
    this.board = this.generateBoard(this.sRow, this.sCol);
  }


  /**
   * A constructor that takes in a single parameter (dimensions) and creates a
   * {@code MarbleSolitaire} with the supplied dimensions and the empty slot at (0,0).
   *
   * @param dimensions is the number of slots in the bottom-most row.
   * @throws IllegalArgumentException if the specified dimensions is invalid (non-positive).
   */
  public TriangleSolitaireModel(int dimensions) throws IllegalArgumentException {
    if (dimensions < 0) {
      throw new IllegalArgumentException("The dimensions must be non-negative.");
    } else {
      this.sRow = 0;
      this.sCol = 0;
      this.dimensions = dimensions;
      this.board = this.generateBoard(this.sRow, this.sCol);
    }
  }

  /**
   * A constructor that takes in two parameters (sRow, sCol) that creates a 5-row triangular
   * {@code MarbleSolitaire} game with the empty slot at the specified position.
   *
   * @param sRow the X-Coordinate of the empty slot.
   * @param sCol the Y-Coordinate of the empty slot.
   * @throws IllegalArgumentException if the specified position is invalid.
   */
  public TriangleSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    this.dimensions = 5;
    if (this.outsideBoard(sRow, sCol)) {
      throw new IllegalArgumentException("Invalid empty slot position (" + sRow + "," + sCol
              + ").");
    } else {
      this.sRow = sRow;
      this.sCol = sCol;
      this.board = this.generateBoard(this.sRow, this.sCol);
    }

  }

  /**
   * A constructor that takes in three parameters that creates a triangular {@code MarbleSolitaire}
   * game with the specified dimensions, and an empty slot at the specified (sRow, sCol).
   *
   * @param dimensions is the number of slots in the bottom-most row.
   * @param sRow       the X-Coordinate of the empty slot.
   * @param sCol       the Y-Coordinate of the empty slot.
   * @throws IllegalArgumentException if the specified dimension is invalid (non-positive) or
   *                                  the specified empty-slot position is invalid.
   */
  public TriangleSolitaireModel(int dimensions, int sRow, int sCol)
          throws IllegalArgumentException {
    if (dimensions < 0) {
      throw new IllegalArgumentException("The dimensions must be non-negative.");
    } else {
      this.dimensions = dimensions;
    }

    if (this.outsideBoard(sRow, sCol)) {
      throw new IllegalArgumentException("Invalid empty slot position (" + sRow + "," + sCol
              + ").");
    } else {
      this.sRow = sRow;
      this.sCol = sCol;
      this.board = this.generateBoard(this.sRow, this.sCol);
    }

  }

  /**
   * Determines if the supplied movement is valid or invalid.
   *
   * @param fromRow The X-Coordinate that the Marble is moving from.
   * @param fromCol The Y-Coordinate that the Marble is moving from.
   * @param toRow   The X-Coordinate that the Marble is moving to.
   * @param toCol   The Y-Coordinate that the Marble is moving to.
   * @return true when the move is invalid, false if the move is valid.
   */
  @Override
  protected boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    return (!((this.outsideBoard(fromRow, fromCol) || (this.outsideBoard(toRow, toCol))))
            && (this.board.get(fromRow).get(fromCol)) == SlotState.Marble
            && (this.board.get(toRow).get(toCol)) == SlotState.Empty
            && (((toRow == fromRow) && Math.abs(toCol - fromCol) == 2)
            || ((toCol == fromCol) && Math.abs(toRow - fromRow) == 2)
            // can't be abstracted due to the cardinality (left/right movement) condition of the
            // other models. In essence, we need to access the from and to position and check
            // their movement. Unlike the two other models, diagonal movement is allowed.
            || (Math.abs(toRow - fromRow) == 2 && Math.abs(toCol - fromCol) == 2))
            && (this.board.get((toRow - fromRow) / 2 + fromRow).get((toCol - fromCol) / 2
            + fromCol)) == SlotState.Marble);
  }

  /**
   * Determines if the given slot position falls outside the game board.
   *
   * @param xCor the x-position of a slot.
   * @param yCor the y-position of a slot.
   * @return False if either the xCor or yCor falls outside playable slots of the board.
   *         else, returns true;
   */
  @Override
  protected boolean outsideBoard(int xCor, int yCor) {
    return yCor > xCor || xCor < 0 || yCor < 0
            || yCor >= this.dimensions || xCor >= this.dimensions;
  }

  /**
   * The largest row length of a {@code TriangleSolitaire} game.
   *
   * @return the integer value that represents the largest row length of the game board.
   */
  @Override
  public int getBoardSize() {
    // chose to not abstract as dimensions represents a unique field (unlike armThickness).
    return this.dimensions;
  }


}
