package cs3500.marblesolitaire.model.hw04;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

import static cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState.Empty;
import static cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState.Invalid;
import static cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState.Marble;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Represents tests for the {@code TriangleSolitaireModel} code.
 */
public class TriangleSolitaireModelTest {

  TriangleSolitaireModel cons1;
  TriangleSolitaireModel cons2;
  TriangleSolitaireModel cons3;
  TriangleSolitaireModel cons4;

  ArrayList<MarbleSolitaireModelState.SlotState> arr1;
  ArrayList<MarbleSolitaireModelState.SlotState> arr2;
  ArrayList<MarbleSolitaireModelState.SlotState> arr3;
  ArrayList<MarbleSolitaireModelState.SlotState> arr4;
  ArrayList<MarbleSolitaireModelState.SlotState> arr5;

  // Initializing fields for the four different constructors.
  @Before
  public void init() {
    this.cons1 = new TriangleSolitaireModel();
    this.cons2 = new TriangleSolitaireModel(6);
    this.cons3 = new TriangleSolitaireModel(1, 0);
    this.cons4 = new TriangleSolitaireModel(2, 1, 1);
  }

  // tests the Invalid initialization of fields.
  @Test
  public void testInvalidInit() {
    try {
      this.cons1 = new TriangleSolitaireModel(7, 7);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as sRow 7 and sCol 2 is outside the board boundary.
      // Both fields are not on the board
    }

    try {
      this.cons1 = new TriangleSolitaireModel(0, 2);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as sCol 2 is outside the board boundary.
    }

    try {
      this.cons1 = new TriangleSolitaireModel(7, 2);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as sRow 7 is outside the board boundary.
    }

    try {
      this.cons1 = new TriangleSolitaireModel(-1, -1);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as both fields are negative
    }

    try {
      this.cons1 = new TriangleSolitaireModel(0, -1);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as both fields should not be negative
    }

    try {
      this.cons1 = new TriangleSolitaireModel(-1, 0);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as both fields should not be negative
    }

    try {
      this.cons1 = new TriangleSolitaireModel(-1);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as the dimensions must be non-negative
    }

    try {
      this.cons1 = new TriangleSolitaireModel(-1, 0, 0);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as dimensions must be non-negative.
    }

    try {
      this.cons1 = new TriangleSolitaireModel(2, 5, 5);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as sRow and sCol falls outside the board boundaries.
    }
  }

  // tests the move method
  @Test
  public void testMove() {
    // tests upwards movement
    assertEquals(this.cons1.board.get(2).get(2), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.cons1.board.get(1).get(1), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.cons1.board.get(0).get(0), MarbleSolitaireModelState.SlotState.Empty);
    this.cons1.move(2, 2, 0, 0);
    assertEquals(this.cons1.board.get(2).get(2), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.cons1.board.get(1).get(1), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.cons1.board.get(0).get(0), MarbleSolitaireModelState.SlotState.Marble);

    // tests rightwards movement
    assertEquals(this.cons1.board.get(2).get(0), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.cons1.board.get(2).get(1), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.cons1.board.get(2).get(2), MarbleSolitaireModelState.SlotState.Empty);
    this.cons1.move(2, 0, 2, 2);
    assertEquals(this.cons1.board.get(2).get(0), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.cons1.board.get(2).get(1), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.cons1.board.get(2).get(2), MarbleSolitaireModelState.SlotState.Marble);

    // tests downward movement
    assertEquals(this.cons1.board.get(0).get(0), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.cons1.board.get(1).get(0), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.cons1.board.get(2).get(0), MarbleSolitaireModelState.SlotState.Empty);
    this.cons1.move(0, 0, 2, 0);
    assertEquals(this.cons1.board.get(0).get(0), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.cons1.board.get(1).get(0), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.cons1.board.get(2).get(0), MarbleSolitaireModelState.SlotState.Marble);

    // tests leftwards movement
    this.cons1.board.get(2).set(1, MarbleSolitaireModelState.SlotState.Marble);
    this.cons1.board.get(2).set(0, MarbleSolitaireModelState.SlotState.Empty);

    assertEquals(this.cons1.board.get(2).get(2), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.cons1.board.get(2).get(1), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.cons1.board.get(2).get(0), MarbleSolitaireModelState.SlotState.Empty);
    this.cons1.move(2, 2, 2, 0);
    assertEquals(this.cons1.board.get(2).get(2), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.cons1.board.get(2).get(1), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.cons1.board.get(2).get(0), MarbleSolitaireModelState.SlotState.Marble);


    // movement less than 2 spaces
    this.cons1 = new TriangleSolitaireModel();

    try {
      this.cons1.move(0, 0, 1, 0);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as movement is less than 2 slots away.
    }

    // movements more than 2 spaces
    try {
      this.cons1.move(0, 0, 3, 0);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as the movement is more than 2 slots away
    }

    // no marble in from position
    try {
      this.cons1.move(0, 0, 2, 0);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as there is no marble in the from position
    }

    // no marble in middle position
    this.cons1.board.get(0).set(0, MarbleSolitaireModelState.SlotState.Marble);
    this.cons1.board.get(1).set(0, MarbleSolitaireModelState.SlotState.Empty);
    this.cons1.board.get(2).set(0, MarbleSolitaireModelState.SlotState.Empty);
    try {
      this.cons1.move(0, 0, 2, 0);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as there is no marble in the middle position.
    }

    // marble in to position
    this.cons1.board.get(0).set(0, MarbleSolitaireModelState.SlotState.Marble);
    this.cons1.board.get(1).set(0, MarbleSolitaireModelState.SlotState.Marble);
    this.cons1.board.get(2).set(0, MarbleSolitaireModelState.SlotState.Marble);
    try {
      this.cons1.move(2, 0, 0, 0);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as there is no marble in the middle position.
    }

    // invalid to position
    try {
      this.cons1.move(0, 0, -2, 0);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as invalid position supplied
    }

    // invalid from position.
    this.cons1.board.get(0).set(0, MarbleSolitaireModelState.SlotState.Marble);
    this.cons1.board.get(1).set(0, MarbleSolitaireModelState.SlotState.Empty);
    this.cons1.board.get(2).set(0, MarbleSolitaireModelState.SlotState.Empty);
    try {
      this.cons1.move(-2, -2, 0, 0);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as there is no marble in the middle position.
    }
  }

  // tests the isGameOver method.
  @Test
  public void testIsGameOver() {
    // All marbles on the board
    assertEquals(this.cons1.isGameOver(), false);
    this.cons1.board.get(0).set(0, MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.cons1.isGameOver(), true);

    assertEquals(this.cons2.isGameOver(), false);
    this.cons2.board.get(0).set(0, MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.cons2.isGameOver(), true);

    assertEquals(this.cons3.isGameOver(), false);
    this.cons3.board.get(1).set(0, MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.cons3.isGameOver(), true);

    // No valid moves left
    assertEquals(this.cons4.isGameOver(), true);

    // One Marble only
    this.cons4 = new TriangleSolitaireModel();
    // sets all the marble slots as empty
    for (int i = 0; i < this.cons4.getBoardSize(); i++) {
      for (int j = 0; j < this.cons4.getBoardSize(); j++) {
        if (this.cons4.board.get(i).get(j) == MarbleSolitaireModelState.SlotState.Marble) {
          this.cons4.board.get(i).set(j, MarbleSolitaireModelState.SlotState.Empty);
        }
      }
    }
    // so that there is only one marble left.
    // and game is once again over!
    this.cons4.board.get(0).set(0, MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.cons4.isGameOver(), true);
  }

  // tests the getBoardSize method.
  @Test
  public void testGetBoardSize() {
    assertEquals(this.cons1.getBoardSize(), 5);
    assertEquals(this.cons2.getBoardSize(), 6);
    assertEquals(this.cons3.getBoardSize(), 5);
    assertEquals(this.cons4.getBoardSize(), 2);

  }

  // tests the getSlotAt method.
  @Test
  public void testGetSlotAt() {
    // Outside the board (too large)
    try {
      this.cons1.getSlotAt(8, 8);
      fail("Should've thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // The supplied slot is outside the board.
    }

    // Outside the board (too small)
    try {
      this.cons2.getSlotAt(-1, -1);
      fail("Should've thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // The supplied slot is outside the board.
    }

    // Cons1
    assertEquals(this.cons1.getSlotAt(0, 1), Invalid);
    assertEquals(this.cons1.getSlotAt(0, 0), Empty);
    assertEquals(this.cons1.getSlotAt(1, 0), Marble);

    // Cons2
    assertEquals(this.cons2.getSlotAt(0, 1), Invalid);
    assertEquals(this.cons2.getSlotAt(0, 0), Empty);
    assertEquals(this.cons2.getSlotAt(5, 5), Marble);

    // Cons3
    assertEquals(this.cons3.getSlotAt(1, 3), Invalid);
    assertEquals(this.cons3.getSlotAt(1, 0), Empty);
    assertEquals(this.cons3.getSlotAt(0, 0), Marble);

    // Cons4
    assertEquals(this.cons4.getSlotAt(0, 1), Invalid);
    assertEquals(this.cons4.getSlotAt(1, 1), Empty);
    assertEquals(this.cons4.getSlotAt(0, 0), Marble);

  }

  // tests the getScore method.
  @Test
  public void testGetScore() {
    assertEquals(this.cons1.getScore(), 14);
    assertEquals(this.cons2.getScore(), 20);
    assertEquals(this.cons3.getScore(), 14);
    assertEquals(this.cons4.getScore(), 2);
  }

  // Tests the outsideBoard method.
  @Test
  public void testOutsideBoard() {
    // too large
    assertEquals(this.cons1.outsideBoard(0, 3), true);
    // Invalid
    assertEquals(this.cons1.outsideBoard(2, 4), true);
    // Negative xCor
    assertEquals(this.cons1.outsideBoard(-1, 3), true);
    // Negative yCor
    assertEquals(this.cons1.outsideBoard(3, -3), true);
    // Negative xCor and yCor
    assertEquals(this.cons1.outsideBoard(-3, -3), true);
    // Inside board
    assertEquals(this.cons1.outsideBoard(1, 1), false);
    // Default empty slot
    assertEquals(this.cons1.outsideBoard(0, 0), false);
  }


}