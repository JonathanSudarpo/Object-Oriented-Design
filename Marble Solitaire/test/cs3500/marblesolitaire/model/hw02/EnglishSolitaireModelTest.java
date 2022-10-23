package cs3500.marblesolitaire.model.hw02;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState.Empty;
import static cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState.Invalid;
import static cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState.Marble;
import static cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Represents tests for the {@code EnglishSolitaireModel} class.
 */
public class EnglishSolitaireModelTest {
  EnglishSolitaireModel cons1;
  EnglishSolitaireModel cons2;
  EnglishSolitaireModel cons3;
  EnglishSolitaireModel cons4;
  ArrayList<SlotState> arr1;
  ArrayList<SlotState> arr2;
  ArrayList<SlotState> arr3;

  ArrayList<ArrayList<SlotState>> result1;
  ArrayList<ArrayList<SlotState>> result2;

  // Initializing fields for the four different constructors.
  @Before
  public void init() {
    this.cons1 = new EnglishSolitaireModel();
    this.cons2 = new EnglishSolitaireModel(3, 3);
    this.cons3 = new EnglishSolitaireModel(5);
    this.cons4 = new EnglishSolitaireModel(3, 4, 3);

    // GenerateBoard Variables.
    this.arr1 = new ArrayList<SlotState>(Arrays.asList(Invalid, Invalid, Marble, Marble,
            Marble, Invalid, Invalid));
    this.arr2 = new ArrayList<SlotState>(Arrays.asList(Marble, Marble, Marble, Marble,
            Marble, Marble, Marble));
    this.arr3 = new ArrayList<SlotState>(Arrays.asList(Marble, Marble, Marble,
            Empty, Marble, Marble, Marble));

    this.result1 = new ArrayList<ArrayList<SlotState>>(Arrays.asList(this.arr1, this.arr1,
            this.arr2, this.arr3, this.arr2, this.arr1, this.arr1));
    this.result2 = new ArrayList<ArrayList<SlotState>>(Arrays.asList(this.arr1, this.arr1,
            this.arr2, this.arr2, this.arr3, this.arr1, this.arr1));

  }

  // Tests Invalid Initialization.
  @Test
  public void testInvalidInit() {
    try {
      this.cons2 = new EnglishSolitaireModel(1, 1);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as sRow 1 and sCol 1 is outside the board boundary.
      // Both fields are not in the board.
    }
    try {
      this.cons2 = new EnglishSolitaireModel(-2, -3);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as sRow -2 and sCol -3 is outside the board boundary.
      // Both fields need to be positive and in the board.
    }

    try {
      this.cons3 = new EnglishSolitaireModel(2);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as the armThickness is not an odd number
    }

    try {
      this.cons3 = new EnglishSolitaireModel(-1);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as the armThickness is a negative number
    }
    try {
      this.cons3 = new EnglishSolitaireModel(2);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as the armThickness is not an odd number
    }

    try {
      this.cons3 = new EnglishSolitaireModel(2, 3, 3);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as the armThickness is not an odd number
    }

    try {
      this.cons3 = new EnglishSolitaireModel(-1, 3, 3);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as the armThickness can't be a negative number
    }

    try {
      this.cons3 = new EnglishSolitaireModel(3, 7, 7);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as the sRow and sCol falls outside the board boundaries
    }
  }

  // tests the Move method
  @Test
  public void testMove() {
    // tests rightwards movement
    assertEquals(this.cons1.board.get(3).get(1), SlotState.Marble);
    assertEquals(this.cons1.board.get(3).get(2), SlotState.Marble);
    assertEquals(this.cons1.board.get(3).get(3), SlotState.Empty);
    this.cons1.move(3, 1, 3, 3);
    assertEquals(this.cons1.board.get(3).get(1), SlotState.Empty);
    assertEquals(this.cons1.board.get(3).get(2), SlotState.Empty);
    assertEquals(this.cons1.board.get(3).get(3), SlotState.Marble);

    // tests leftwards movement
    assertEquals(this.cons2.board.get(3).get(5), SlotState.Marble);
    assertEquals(this.cons2.board.get(3).get(4), SlotState.Marble);
    assertEquals(this.cons2.board.get(3).get(3), SlotState.Empty);
    this.cons2.move(3, 5, 3, 3);
    assertEquals(this.cons2.board.get(3).get(5), SlotState.Empty);
    assertEquals(this.cons2.board.get(3).get(4), SlotState.Empty);
    assertEquals(this.cons2.board.get(3).get(3), SlotState.Marble);

    // tests upwards movement
    assertEquals(this.cons3.board.get(8).get(6), SlotState.Marble);
    assertEquals(this.cons3.board.get(7).get(6), SlotState.Marble);
    assertEquals(this.cons3.board.get(6).get(6), SlotState.Empty);
    this.cons3.move(8, 6, 6, 6);
    assertEquals(this.cons3.board.get(8).get(6), SlotState.Empty);
    assertEquals(this.cons3.board.get(7).get(6), SlotState.Empty);
    assertEquals(this.cons3.board.get(6).get(6), SlotState.Marble);

    // tests downward movement
    assertEquals(this.cons3.board.get(5).get(6), SlotState.Marble);
    assertEquals(this.cons3.board.get(6).get(6), SlotState.Marble);
    assertEquals(this.cons3.board.get(7).get(6), SlotState.Empty);
    this.cons3.move(5, 6, 7, 6);
    assertEquals(this.cons3.board.get(5).get(6), SlotState.Empty);
    assertEquals(this.cons3.board.get(6).get(6), SlotState.Empty);
    assertEquals(this.cons3.board.get(7).get(6), SlotState.Marble);

    // less than 2 spaces of movement (invalid)
    try {
      this.cons1.move(3, 3, 3, 4);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as the movement is less than 2 slots away
    }

    // more than 2 spaces of movement (invalid)
    try {
      this.cons1.move(3, 3, 3, 6);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as the movement is more than 2 slots away
    }

    // marble in the from position
    try {
      this.cons1.move(4, 4, 4, 6);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as there is a marble in the from position
    }

    // no marble in the middle position
    try {
      this.cons4.move(4, 2, 4, 4);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as there is no marble in the middle position.
    }

    // marble in the to position
    try {
      this.cons4.move(3, 3, 3, 4);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as there is a marble in the to position.
    }
  }

  // tests the isGameOver Method
  @org.junit.Test
  public void testIsGameOver() {
    assertEquals(this.cons1.isGameOver(), false);
    this.cons1.board.get(3).set(3, SlotState.Marble);
    assertEquals(this.cons1.isGameOver(), true);

    assertEquals(this.cons2.isGameOver(), false);
    this.cons2.board.get(3).set(3, SlotState.Marble);
    assertEquals(this.cons2.isGameOver(), true);

    assertEquals(this.cons3.isGameOver(), false);
    this.cons3.board.get(6).set(6, SlotState.Marble);
    assertEquals(this.cons3.isGameOver(), true);

    // sets all the marble slots as empty
    for (int i = 0; i < this.cons4.getBoardSize(); i++) {
      for (int j = 0; j < this.cons4.getBoardSize(); j++) {
        if (this.cons4.board.get(i).get(j) == SlotState.Marble) {
          this.cons4.board.get(i).set(j, SlotState.Empty);
        }
      }
    }
    this.cons4.board.get(3).set(3, SlotState.Marble);
    assertEquals(this.cons4.isGameOver(), true);
  }

  // Tests the getBoardSize method
  @org.junit.Test
  public void testGetBoardSize() {
    assertEquals(this.cons1.getBoardSize(), 7);
    assertEquals(this.cons2.getBoardSize(), 7);
    assertEquals(this.cons3.getBoardSize(), 13);
    assertEquals(this.cons4.getBoardSize(), 7);
  }

  //Tests the getSlotAt method.
  @org.junit.Test
  public void testGetSlotAt() {
    try {
      this.cons1.getSlotAt(3, 9);
      fail("Should've thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // The supplied slot is outside the board.
    }

    try {
      this.cons2.getSlotAt(-2, -4);
      fail("Should've thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // The supplied slot is outside the board.
    }
    assertEquals(this.cons1.getSlotAt(1, 1), Invalid);
    assertEquals(this.cons1.getSlotAt(3, 3), Empty);
    assertEquals(this.cons1.getSlotAt(2, 3), Marble);

    try {
      this.cons2.getSlotAt(-1, -1);
      fail("Should've thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // The supplied slot is outside the board.
    }
    try {
      this.cons2.getSlotAt(9, 3);
      fail("Should've thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // The supplied slot is outside the board.
    }
    assertEquals(this.cons2.getSlotAt(1, 1), Invalid);
    assertEquals(this.cons2.getSlotAt(3, 3), Empty);
    assertEquals(this.cons2.getSlotAt(4, 3), Marble);

    try {
      this.cons3.getSlotAt(1, -1);
      fail("Should've thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // The supplied slot is outside the board.
    }

    try {
      this.cons3.getSlotAt(13, 8);
      fail("Should've thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // The supplied slot is outside the board.
    }
    assertEquals(this.cons3.getSlotAt(3, 3), Invalid);
    assertEquals(this.cons3.getSlotAt(6, 6), Empty);
    assertEquals(this.cons3.getSlotAt(5, 5), Marble);

    try {
      this.cons4.getSlotAt(-1, 1);
      fail("Should've thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // The supplied slot is outside the board.
    }
    try {
      this.cons4.getSlotAt(8, 7);
      fail("Should've thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // The supplied slot is outside the board.
    }
    assertEquals(this.cons4.getSlotAt(1, 1), Invalid);
    assertEquals(this.cons4.getSlotAt(4, 3), Empty);
    assertEquals(this.cons4.getSlotAt(4, 4), Marble);
  }

  // tests the getScore method.
  @org.junit.Test
  public void testGetScore() {
    // Note: for a board size of 3, there are 32 marbles.
    //       for a board size of 5, there are 104 marbles.
    assertEquals(this.cons2.getScore(), 32);
    assertEquals(this.cons2.getScore(), 32);
    assertEquals(this.cons3.getScore(), 104);
    assertEquals(this.cons4.getScore(), 32);
  }

  // Tests the outsideBoard method
  @org.junit.Test
  public void testOutsideBoard() {
    // top left outside
    assertEquals(this.cons1.outsideBoard(1, 1), true);
    // bottom right outside
    assertEquals(this.cons1.outsideBoard(5, 5), true);
    // bottom left outside
    assertEquals(this.cons1.outsideBoard(1, 5), true);
    // top right outside
    assertEquals(this.cons1.outsideBoard(1, 5), true);
    //inside the board outside
    assertEquals(this.cons1.outsideBoard(2, 3), false);
    // negative number xCor
    assertEquals(this.cons1.outsideBoard(-1, 3), true);
    // negative number yCor
    assertEquals(this.cons1.outsideBoard(3, -1), true);
    // too large
    assertEquals(this.cons1.outsideBoard(7, 8), true);

  }

}