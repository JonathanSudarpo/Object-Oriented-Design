package cs3500.marblesolitaire.controller;

import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireCorruptibleView;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Represents tests for the functionality and inputs/outputs
 * of the {@code MarbleSolitaireController} class.
 */
public class MarbleSolitaireControllerImplTest {

  // Triangle Solitaire //

  // null model
  TriangleSolitaireModel triangleModelNull;
  // normal model
  TriangleSolitaireModel triangle2;
  // model with changed empty slot
  TriangleSolitaireModel triangle3;
  // dimension 2 board
  TriangleSolitaireModel triangle4;

  // European Solitaire //

  // null model
  EuropeanSolitaireModel europeanModelNull;
  // normal model
  EuropeanSolitaireModel europeanModel2;
  // model with skewed empty slot
  EuropeanSolitaireModel europeanModel3;
  // 5 x 5 armThickness
  EuropeanSolitaireModel europeanModel4;

  // English Solitaire //

  // Default invalid move
  String s1;
  // Default quit game
  String s2;
  // Null Model
  MarbleSolitaireModel m0;
  // Normal Model
  MarbleSolitaireModel m1;
  // Model with skewed empty
  MarbleSolitaireModel m2;
  // 5 x 5 board
  MarbleSolitaireModel m3;

  // Null view
  MarbleSolitaireView v0;
  // Normal view
  MarbleSolitaireView v1;
  // Normal view with the skewed empty
  MarbleSolitaireView v2;

  // null readable
  Readable r0;
  // Normal readable
  Readable r1;
  // Invalid readable
  Readable r2;
  Readable r3;
  // empty readable
  Readable r4;
  // outside the board reader
  Readable r5;
  Readable r6;
  // negative
  Readable r7;
  // less than 2 reader
  Readable r8;
  // more than 2 reader
  Readable r9;
  // invalid placement reader
  Readable r10;
  Readable r11;
  // marble in to  reader
  Readable r12;
  // no marble in from reader
  Readable r13;
  // no marble in between reader
  Readable r14; // Make sure that 4 5 is empty first
  // leftwards movement reader
  Readable r15;
  // rightwards movement reader
  Readable r16;
  // upwards movement reader
  Readable r17;
  // downwards movement reader
  Readable r18;
  // quit reader
  Readable r19;
  Readable r20;
  // big Q quit reader
  Readable r21;
  Readable r22;
  // IllegalStateException(?)
  // IO stuff reader


  //Constructor
  // normal model (with basic constructor, supplied view, and empty readab le
  MarbleSolitaireControllerImpl c1;
  // shifted empty model
  MarbleSolitaireControllerImpl c2;
  // controller with non-q readable
  MarbleSolitaireControllerImpl c3;
  // controller with negative readable
  MarbleSolitaireControllerImpl c4;
  // controller with outside board readable
  MarbleSolitaireControllerImpl c5;
  // controller with no marble in between reader
  MarbleSolitaireControllerImpl c6;
  // controller with no marble in from reader
  MarbleSolitaireControllerImpl c7;
  // controller with marble in to reader
  MarbleSolitaireControllerImpl c8;
  // controller with less than 2 slots reader
  MarbleSolitaireControllerImpl c9;
  // controller with more than 2 slots reader
  MarbleSolitaireControllerImpl c10;
  // controller with invalid readable
  MarbleSolitaireControllerImpl c11;
  // Controller with valid move readable
  MarbleSolitaireControllerImpl c12;


  // initializing all the variables
  @Before
  public void init() {
    // Triangle Solitaire //
    this.triangleModelNull = null;
    this.triangle2 = new TriangleSolitaireModel();
    this.triangle3 = new TriangleSolitaireModel(1, 0);
    this.triangle4 = new TriangleSolitaireModel(2);

    // European Solitaire //
    this.europeanModelNull = null;
    this.europeanModel2 = new EuropeanSolitaireModel();
    this.europeanModel3 = new EuropeanSolitaireModel(2, 2);
    this.europeanModel4 = new EuropeanSolitaireModel(5);


    // English Solitaire  //

    // Default Invalid Move String
    this.s1 = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again. Please try other values.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n";
    // Default quit game
    this.s2 = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n";
    // Null Model
    this.m0 = null;
    // Normal Model
    this.m1 = new EnglishSolitaireModel();
    // Model with skewed empty
    this.m2 = new EnglishSolitaireModel(2, 3);
    // 5 x5 board
    this.m3 = new EnglishSolitaireModel(5);

    // Null view
    this.v0 = null;
    // Normal view
    this.v1 = new MarbleSolitaireTextView(this.m1);
    // Normal view with the skewed empty
    this.v2 = new MarbleSolitaireTextView(this.m2);

    // null readable
    this.r0 = null;
    // Normal readable
    this.r1 = new StringReader("4 2 4 4 q");
    // Invalid readable
    this.r2 = new StringReader("4 a 4 4 q");
    this.r3 = new StringReader("a b c d q");
    // empty readable
    this.r4 = new StringReader(" ");
    // outside the board reader
    this.r5 = new StringReader("7 7 7 7 q");
    this.r6 = new StringReader("2 2 2 4 q");
    // negative
    this.r7 = new StringReader("-1 2 -1 2 q");
    // less than 2 reader
    this.r8 = new StringReader("4 3 4 4 q");
    // more than 2 reader
    this.r9 = new StringReader("4 1 4 4 q");
    // invalid placement reader
    this.r10 = new StringReader("1 1 1 3 q");
    this.r11 = new StringReader("8 8 8 10 q");
    // marble in to  reader
    this.r12 = new StringReader("4 1 4 3 q");
    // no marble in from reader
    this.r13 = new StringReader("4 4 4 6 q");
    // no marble in between reader
    this.r14 = new StringReader("4 3 4 5 q"); // Make sure that 4 5 is empty first
    // leftwards movement reader
    this.r15 = new StringReader("4 6 4 4 q");
    // rightwards movement reader
    this.r16 = new StringReader("4 2 4 4 q");
    // upwards movement reader
    this.r17 = new StringReader("6 4 4 4 q");
    // downwards movement reader
    this.r18 = new StringReader("2 4 4 4 q");
    // quit reader
    this.r19 = new StringReader("4 2 4 q");
    this.r20 = new StringReader("q 2 4 4");
    // big Q quit reader
    this.r21 = new StringReader("4 2 4 Q");
    this.r22 = new StringReader("Q 2 4 4");
    // IllegalStateException(?)
    // IO stuff reader

    //Constructor
    // normal model (with basic constructor, supplied view, and empty readab le
    this.c1 = new MarbleSolitaireControllerImpl(this.m1, this.v1, this.r4);
    // shifted empty model
    this.c2 = new MarbleSolitaireControllerImpl(this.m2, this.v2, this.r1);
    // controller with non-q readable
    this.c3 = new MarbleSolitaireControllerImpl(this.m1, this.v1, this.r3);
    // controller with negative readable
    this.c4 = new MarbleSolitaireControllerImpl(this.m1, this.v1, this.r7);
    // controller with outside board readable
    this.c5 = new MarbleSolitaireControllerImpl(this.m1, this.v1, this.r5);
    // controller with no marble in between reader
    this.c6 = new MarbleSolitaireControllerImpl(this.m1, this.v1, this.r14);
    // controller with no marble in from reader
    this.c7 = new MarbleSolitaireControllerImpl(this.m1, this.v1, this.r13);
    // controller with marble in to reader
    this.c8 = new MarbleSolitaireControllerImpl(this.m1, this.v1, this.r12);
    // controller with less than 2 slots reader
    this.c9 = new MarbleSolitaireControllerImpl(this.m1, this.v1, this.r8);
    // controller with more than 2 slots reader
    this.c10 = new MarbleSolitaireControllerImpl(this.m1, this.v1, this.r9);
    // controller with invalid readable
    this.c11 = new MarbleSolitaireControllerImpl(this.m1, this.v1, this.r2);
    // Controller with valid move readable
    this.c12 = new MarbleSolitaireControllerImpl(this.m1, this.v1, this.r1);
  }

  // tests for invalid constructors.
  @Test
  public void testInvalidConstructors() {

    // Triangle Solitaire //

    // Model Null
    try {
      this.c1 = new MarbleSolitaireControllerImpl(this.triangleModelNull, this.v1, this.r1);
    } catch (IllegalArgumentException e) {
      // fails as model is null
    }
    // View null
    try {
      this.c1 = new MarbleSolitaireControllerImpl(this.triangle2, this.v0, this.r1);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // Fails as view is null
    }
    // Readable null
    try {
      this.c1 = new MarbleSolitaireControllerImpl(this.triangle2, this.v1, this.r0);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // Fails as readable is null
    }
    // Model okay, view and readable null
    try {
      this.c1 = new MarbleSolitaireControllerImpl(this.triangle3, this.v0, this.r0);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // Fails as view and readable are null
    }
    // Readable okay, model and view null
    try {
      this.c1 = new MarbleSolitaireControllerImpl(this.triangleModelNull, this.v0, this.r1);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // Fails as model and view are null
    }
    // View okay, model and readable null
    try {
      this.c1 = new MarbleSolitaireControllerImpl(this.triangleModelNull, this.v1, this.r0);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // Fails as model and readable are null
    }
    // all null
    try {
      this.c1 = new MarbleSolitaireControllerImpl(this.triangleModelNull, this.v0, this.r0);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // Fails as all fields are null
    }

    // European Solitaire //

    // Model Null
    try {
      this.c1 = new MarbleSolitaireControllerImpl(this.europeanModelNull, this.v1, this.r1);
    } catch (IllegalArgumentException e) {
      // fails as model is null
    }
    // View null
    try {
      this.c1 = new MarbleSolitaireControllerImpl(this.europeanModel2, this.v0, this.r1);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // Fails as view is null
    }
    // Readable null
    try {
      this.c1 = new MarbleSolitaireControllerImpl(this.europeanModel2, this.v1, this.r0);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // Fails as readable is null
    }
    // Model okay, view and readable null
    try {
      this.c1 = new MarbleSolitaireControllerImpl(this.europeanModel2, this.v0, this.r0);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // Fails as view and readable are null
    }
    // Readable okay, model and view null
    try {
      this.c1 = new MarbleSolitaireControllerImpl(this.europeanModelNull, this.v0, this.r1);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // Fails as model and view are null
    }
    // View okay, model and readable null
    try {
      this.c1 = new MarbleSolitaireControllerImpl(this.europeanModelNull, this.v1, this.r0);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // Fails as model and readable are null
    }
    // all null
    try {
      this.c1 = new MarbleSolitaireControllerImpl(this.europeanModelNull, this.v0, this.r0);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // Fails as all fields are null
    }

    // ENGLISH SOLITAIRE //

    // Model Null
    try {
      this.c1 = new MarbleSolitaireControllerImpl(this.m0, this.v1, this.r1);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // Fails as model is null
    }
    // View null
    try {
      this.c1 = new MarbleSolitaireControllerImpl(this.m1, this.v0, this.r1);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // Fails as view is null
    }
    // Readable null
    try {
      this.c1 = new MarbleSolitaireControllerImpl(this.m1, this.v1, this.r0);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // Fails as readable is null
    }
    // Model okay, view and readable null
    try {
      this.c1 = new MarbleSolitaireControllerImpl(this.m1, this.v0, this.r0);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // Fails as view and readable are null
    }
    // Readable okay, model and view null
    try {
      this.c1 = new MarbleSolitaireControllerImpl(this.m0, this.v0, this.r1);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // Fails as model and view are null
    }
    // View okay, model and readable null
    try {
      this.c1 = new MarbleSolitaireControllerImpl(this.m0, this.v1, this.r0);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // Fails as model and readable are null
    }
    // all null
    try {
      this.c1 = new MarbleSolitaireControllerImpl(this.m0, this.v0, this.r0);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // Fails as all fields are null
    }
  }


  // tests for game over
  @Test
  public void testPlayGameGameOver() {

    // Triangle Solitaire //

    // board with one marble
    Appendable triangleOut0 = new StringBuilder();
    TriangleSolitaireModel triangleModel = new TriangleSolitaireModel();
    for (int i = 0; i < triangleModel.getBoardSize(); i++) {
      for (int j = 0; j < triangleModel.getBoardSize(); j++) {
        if (triangleModel.board.get(i).get(j) == MarbleSolitaireModelState.SlotState.Marble) {
          triangleModel.board.get(i).set(j, MarbleSolitaireModelState.SlotState.Empty);
        }
      }
    }
    triangleModel.board.get(3).set(3, MarbleSolitaireModelState.SlotState.Marble);
    TriangleSolitaireTextView triangleV0 = new TriangleSolitaireTextView(triangleModel,
            triangleOut0);
    Readable triangleR = new StringReader("q");
    MarbleSolitaireControllerImpl triangelTest0 = new MarbleSolitaireControllerImpl(triangleModel,
            triangleV0, triangleR);
    triangelTest0.playGame();
    assertEquals(triangleOut0.toString(), "    _\n" +
            "   _ _\n" +
            "  _ _ _\n" +
            " _ _ _ O\n" +
            "_ _ _ _ _\n" +
            "Score: 1\n" +
            "Game over!\n" +
            "    _\n" +
            "   _ _\n" +
            "  _ _ _\n" +
            " _ _ _ O\n" +
            "_ _ _ _ _\n" +
            "Score: 1\n");

    // board with all marbles
    Appendable triangleOut1 = new StringBuilder();
    TriangleSolitaireModel triangleModel1 = new TriangleSolitaireModel();
    triangleModel1.board.get(0).set(0, MarbleSolitaireModelState.SlotState.Marble);
    TriangleSolitaireTextView triangleV1 = new TriangleSolitaireTextView(triangleModel1,
            triangleOut1);
    Readable triangleR1 = new StringReader("q");
    MarbleSolitaireControllerImpl triangleTest1 = new MarbleSolitaireControllerImpl(triangleModel1,
            triangleV1, triangleR1);
    triangleTest1.playGame();
    assertEquals(triangleOut1.toString(), "    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 15\n" +
            "Game over!\n" +
            "    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 15\n");

    // board with no marbles
    Appendable triangleOut3 = new StringBuilder();
    TriangleSolitaireModel triangleModel2 = new TriangleSolitaireModel();
    for (int i = 0; i < triangleModel2.getBoardSize(); i++) {
      for (int j = 0; j < triangleModel2.getBoardSize(); j++) {
        if (triangleModel2.board.get(i).get(j) == MarbleSolitaireModelState.SlotState.Marble) {
          triangleModel2.board.get(i).set(j, MarbleSolitaireModelState.SlotState.Empty);
        }
      }
    }
    TriangleSolitaireTextView triangleV4 = new TriangleSolitaireTextView(triangleModel2,
            triangleOut3);
    Readable triangleR2 = new StringReader("Q");
    MarbleSolitaireControllerImpl triangleTest3 = new MarbleSolitaireControllerImpl(triangleModel2,
            triangleV4, triangleR2);
    triangleTest3.playGame();
    assertEquals(triangleOut3.toString(), "    _\n" +
            "   _ _\n" +
            "  _ _ _\n" +
            " _ _ _ _\n" +
            "_ _ _ _ _\n" +
            "Score: 0\n" +
            "Game over!\n" +
            "    _\n" +
            "   _ _\n" +
            "  _ _ _\n" +
            " _ _ _ _\n" +
            "_ _ _ _ _\n" +
            "Score: 0\n");

    // European Solitaire //

    // board with one marble
    Appendable europeanOut0 = new StringBuilder();
    EuropeanSolitaireModel europeanModel = new EuropeanSolitaireModel();
    for (int i = 0; i < europeanModel.getBoardSize(); i++) {
      for (int j = 0; j < europeanModel.getBoardSize(); j++) {
        if (europeanModel.board.get(i).get(j) == MarbleSolitaireModelState.SlotState.Marble) {
          europeanModel.board.get(i).set(j, MarbleSolitaireModelState.SlotState.Empty);
        }
      }
    }
    europeanModel.board.get(3).set(3, MarbleSolitaireModelState.SlotState.Marble);
    MarbleSolitaireTextView europeanV0 = new MarbleSolitaireTextView(europeanModel, europeanOut0);
    Readable europeanR = new StringReader("q");
    MarbleSolitaireControllerImpl europeanTest0 = new MarbleSolitaireControllerImpl(europeanModel,
            europeanV0, europeanR);
    europeanTest0.playGame();
    assertEquals(europeanOut0.toString(), "    _ _ _\n" +
            "  _ _ _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "  _ _ _ _ _\n" +
            "    _ _ _\n" +
            "Score: 1\n" +
            "Game over!\n" +
            "    _ _ _\n" +
            "  _ _ _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "  _ _ _ _ _\n" +
            "    _ _ _\n" +
            "Score: 1\n");

    // board with all marbles
    Appendable europeanOut1 = new StringBuilder();
    EuropeanSolitaireModel europeanModel1 = new EuropeanSolitaireModel();
    europeanModel1.board.get(3).set(3, MarbleSolitaireModelState.SlotState.Marble);
    MarbleSolitaireTextView europeanV1 = new MarbleSolitaireTextView(europeanModel1, europeanOut1);
    Readable europeanR1 = new StringReader("q");
    MarbleSolitaireControllerImpl europeanTest1 = new MarbleSolitaireControllerImpl(europeanModel1,
            europeanV1, europeanR1);
    europeanTest1.playGame();
    assertEquals(europeanOut1.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 37\n" +
            "Game over!\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 37\n");

    // board with no marbles
    Appendable europeanOut3 = new StringBuilder();
    EnglishSolitaireModel europeanModel2 = new EnglishSolitaireModel();
    for (int i = 0; i < europeanModel2.getBoardSize(); i++) {
      for (int j = 0; j < europeanModel2.getBoardSize(); j++) {
        if (europeanModel2.board.get(i).get(j) == MarbleSolitaireModelState.SlotState.Marble) {
          europeanModel2.board.get(i).set(j, MarbleSolitaireModelState.SlotState.Empty);
        }
      }
    }
    MarbleSolitaireTextView europeanV4 = new MarbleSolitaireTextView(europeanModel2, europeanOut3);
    Readable europeanR2 = new StringReader("Q");
    MarbleSolitaireControllerImpl europeanTest3 = new MarbleSolitaireControllerImpl(europeanModel2,
            europeanV4, europeanR2);
    europeanTest3.playGame();
    assertEquals(europeanOut3.toString(), "    _ _ _\n" +
            "    _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 0\n" +
            "Game over!\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 0\n");

    // English Solitaire //

    // board with one marble
    Appendable out0 = new StringBuilder();
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    for (int i = 0; i < model.getBoardSize(); i++) {
      for (int j = 0; j < model.getBoardSize(); j++) {
        if (model.board.get(i).get(j) == MarbleSolitaireModelState.SlotState.Marble) {
          model.board.get(i).set(j, MarbleSolitaireModelState.SlotState.Empty);
        }
      }
    }
    model.board.get(3).set(3, MarbleSolitaireModelState.SlotState.Marble);
    MarbleSolitaireTextView v0 = new MarbleSolitaireTextView(model, out0);
    Readable r = new StringReader("q");
    MarbleSolitaireControllerImpl test0 = new MarbleSolitaireControllerImpl(model, v0, r);
    test0.playGame();
    assertEquals(out0.toString(), "    _ _ _\n" +
            "    _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 1\n" +
            "Game over!\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 1\n");
    // board with all marbles
    Appendable out = new StringBuilder();
    EnglishSolitaireModel model1 = new EnglishSolitaireModel();
    model1.board.get(3).set(3, MarbleSolitaireModelState.SlotState.Marble);
    MarbleSolitaireTextView v1 = new MarbleSolitaireTextView(model1, out);
    Readable r1 = new StringReader("q");
    MarbleSolitaireControllerImpl test = new MarbleSolitaireControllerImpl(model1, v1, r1);
    test.playGame();
    assertEquals(out.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 33\n" +
            "Game over!\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 33\n");
    // board with no marbles
    Appendable out3 = new StringBuilder();
    EnglishSolitaireModel model2 = new EnglishSolitaireModel();
    for (int i = 0; i < model2.getBoardSize(); i++) {
      for (int j = 0; j < model2.getBoardSize(); j++) {
        if (model2.board.get(i).get(j) == MarbleSolitaireModelState.SlotState.Marble) {
          model2.board.get(i).set(j, MarbleSolitaireModelState.SlotState.Empty);
        }
      }
    }
    MarbleSolitaireTextView v4 = new MarbleSolitaireTextView(model2, out3);
    Readable r2 = new StringReader("Q");
    MarbleSolitaireControllerImpl test3 = new MarbleSolitaireControllerImpl(model2, v4, r2);
    test3.playGame();
    assertEquals(out3.toString(), "    _ _ _\n" +
            "    _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 0\n" +
            "Game over!\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 0\n");
  }

  // tests for default initialization of the game
  @Test
  public void testPlayGameDefault() {

    // Triangle Solitaire //

    // default
    Appendable triangleOut0 = new StringBuilder();
    TriangleSolitaireTextView triangleV0 = new TriangleSolitaireTextView(this.triangle2,
            triangleOut0);
    Readable triangleR0 = new StringReader("q");
    MarbleSolitaireControllerImpl triangleTest0 = new MarbleSolitaireControllerImpl(this.triangle2,
            triangleV0, triangleR0);
    triangleTest0.playGame();
    assertEquals(triangleOut0.toString(), "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n");

    // Skewed Empty Slot
    Appendable triangleOut1 = new StringBuilder();
    TriangleSolitaireTextView triangleV1 = new TriangleSolitaireTextView(this.triangle3,
            triangleOut1);
    Readable triangleR1 = new StringReader("q");
    MarbleSolitaireControllerImpl triangleTest1 = new MarbleSolitaireControllerImpl(this.triangle3,
            triangleV1, triangleR1);
    triangleTest1.playGame();
    assertEquals(triangleOut1.toString(), "    O\n" +
            "   _ O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O\n" +
            "   _ O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n");

    // Different dimensions
    Appendable triangleOut2 = new StringBuilder();
    TriangleSolitaireTextView triangleV2 = new TriangleSolitaireTextView(this.triangle4,
            triangleOut2);
    Readable triangleR2 = new StringReader("Q");
    MarbleSolitaireControllerImpl triangleTest2 = new MarbleSolitaireControllerImpl(this.triangle4,
            triangleV2, triangleR2);
    triangleTest2.playGame();
    assertEquals(triangleOut2.toString(), " _\n" +
            "O O\n" +
            "Score: 2\n" +
            "Game over!\n" +
            " _\n" +
            "O O\n" +
            "Score: 2\n");

    // European Solitaire //

    // default
    Appendable europeanOut0 = new StringBuilder();
    MarbleSolitaireTextView europeanV0 = new MarbleSolitaireTextView(this.europeanModel2,
            europeanOut0);
    Readable europeanR0 = new StringReader("q");
    MarbleSolitaireControllerImpl europeanTest0 = new MarbleSolitaireControllerImpl(
            this.europeanModel2, europeanV0, europeanR0);
    europeanTest0.playGame();
    assertEquals(europeanOut0.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n");

    // Skewed Empty Slot
    Appendable europeanOut1 = new StringBuilder();
    MarbleSolitaireTextView europeanV1 = new MarbleSolitaireTextView(this.europeanModel3,
            europeanOut1);
    Readable europeanR1 = new StringReader("q");
    MarbleSolitaireControllerImpl europeanTest1 = new MarbleSolitaireControllerImpl(
            this.europeanModel3, europeanV1, europeanR1);
    europeanTest1.playGame();
    assertEquals(europeanOut1.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O _ O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O _ O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n");

    // Different dimensions
    Appendable europeanOut2 = new StringBuilder();
    MarbleSolitaireTextView europeanV2 = new MarbleSolitaireTextView(this.europeanModel4,
            europeanOut2);
    Readable europeanR2 = new StringReader("Q");
    MarbleSolitaireControllerImpl europeanTest3 = new MarbleSolitaireControllerImpl(
            this.europeanModel4, europeanV2, europeanR2);
    europeanTest3.playGame();
    assertEquals(europeanOut2.toString(), "        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O\n" +
            "Score: 128\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O\n" +
            "Score: 128\n");

    // English Solitaire //

    // 3x3
    Appendable out0 = new StringBuilder();
    MarbleSolitaireTextView v0 = new MarbleSolitaireTextView(this.m1, out0);
    Readable r = new StringReader("q");
    MarbleSolitaireControllerImpl test0 = new MarbleSolitaireControllerImpl(this.m1, v0, r);
    test0.playGame();
    assertEquals(out0.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n");
    // Skewed Empty Slot
    Appendable out = new StringBuilder();
    MarbleSolitaireTextView v1 = new MarbleSolitaireTextView(this.m2, out);
    Readable r1 = new StringReader("q");
    MarbleSolitaireControllerImpl test = new MarbleSolitaireControllerImpl(this.m2, v1, r1);
    test.playGame();
    assertEquals(out.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n");
    // 5 x 5

    Appendable out3 = new StringBuilder();
    MarbleSolitaireTextView v4 = new MarbleSolitaireTextView(this.m3, out3);
    Readable r2 = new StringReader("Q");
    MarbleSolitaireControllerImpl test3 = new MarbleSolitaireControllerImpl(this.m3, v4, r2);
    test3.playGame();
    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 104\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 104\n", out3.toString());
  }

  @Test
  public void testPlayGameQuitTriangle() {
    // Triangle Solitaire //

    String defaultQuit = "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n";

    // quit reader
    Appendable triangleOut0 = new StringBuilder();
    TriangleSolitaireTextView triangleV0 = new TriangleSolitaireTextView(this.triangle2,
            triangleOut0);
    MarbleSolitaireControllerImpl test0 = new MarbleSolitaireControllerImpl(this.triangle2,
            triangleV0, r19);
    test0.playGame();
    assertEquals(triangleOut0.toString(), defaultQuit);

    Appendable triangleOut1 = new StringBuilder();
    TriangleSolitaireTextView triangleV1 = new TriangleSolitaireTextView(this.triangle2,
            triangleOut1);
    MarbleSolitaireControllerImpl test1 = new MarbleSolitaireControllerImpl(this.triangle2,
            triangleV1, this.r20);
    test1.playGame();
    assertEquals(triangleOut1.toString(), defaultQuit);

    // big Q quit reader
    Appendable triangleOut2 = new StringBuilder();
    TriangleSolitaireTextView triangleV2 = new TriangleSolitaireTextView(this.triangle2,
            triangleOut2);
    MarbleSolitaireControllerImpl test2 = new MarbleSolitaireControllerImpl(this.triangle2,
            triangleV2, this.r21);
    test2.playGame();
    assertEquals(triangleOut2.toString(), defaultQuit);

    Appendable triangleOut3 = new StringBuilder();
    this.triangle2 = new TriangleSolitaireModel();
    TriangleSolitaireTextView triangleV3 = new TriangleSolitaireTextView(this.triangle2,
            triangleOut3);
    MarbleSolitaireControllerImpl test3 = new MarbleSolitaireControllerImpl(this.triangle2,
            triangleV3, this.r22);
    test3.playGame();
    assertEquals(defaultQuit, triangleOut3.toString());

    // q in index 0 of reader
    Appendable triangleOut4 = new StringBuilder();
    this.triangle2 = new TriangleSolitaireModel();
    Readable triangleR4 = new StringReader("q 2 4 4");
    TriangleSolitaireTextView triangleV4 = new TriangleSolitaireTextView(this.triangle2,
            triangleOut4);
    MarbleSolitaireControllerImpl test4 = new MarbleSolitaireControllerImpl(this.triangle2,
            triangleV4, triangleR4);
    test4.playGame();
    assertEquals(triangleOut4.toString(), defaultQuit);

    // q in index 1 of reader
    Appendable triangleOut5 = new StringBuilder();
    this.triangle2 = new TriangleSolitaireModel();
    Readable triangleR5 = new StringReader("2 q 4 4");
    TriangleSolitaireTextView triangleV5 = new TriangleSolitaireTextView(this.triangle2,
            triangleOut5);
    MarbleSolitaireControllerImpl test5 = new MarbleSolitaireControllerImpl(this.triangle2,
            triangleV5, triangleR5);
    test5.playGame();
    assertEquals(triangleOut5.toString(), defaultQuit);

    // q in index 2 of reader
    Appendable triangleOut6 = new StringBuilder();
    this.triangle2 = new TriangleSolitaireModel();
    Readable triangleR6 = new StringReader("4 2 q 4");
    TriangleSolitaireTextView triangleV6 = new TriangleSolitaireTextView(this.triangle2,
            triangleOut6);
    MarbleSolitaireControllerImpl test6 = new MarbleSolitaireControllerImpl(this.triangle2,
            triangleV6, triangleR6);
    test6.playGame();
    assertEquals(triangleOut6.toString(), defaultQuit);

    // q in index 3 of reader
    Appendable triangleOut7 = new StringBuilder();
    this.triangle2 = new TriangleSolitaireModel();
    Readable triangleR7 = new StringReader("4 2 4 q");
    TriangleSolitaireTextView triangleV7 = new TriangleSolitaireTextView(this.triangle2,
            triangleOut7);
    MarbleSolitaireControllerImpl test7 = new MarbleSolitaireControllerImpl(this.triangle2,
            triangleV7, triangleR7);
    test7.playGame();
    assertEquals(triangleOut7.toString(), defaultQuit);

    // move to quit
    Appendable triangleOut8 = new StringBuilder();
    this.triangle2 = new TriangleSolitaireModel();
    Readable triangleR8 = new StringReader("3 3 1 1 Q");
    TriangleSolitaireTextView triangleV8 = new TriangleSolitaireTextView(this.triangle2,
            triangleOut8);
    MarbleSolitaireControllerImpl test8 = new MarbleSolitaireControllerImpl(this.triangle2,
            triangleV8, triangleR8);
    test8.playGame();
    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "    O\n" +
            "   O _\n" +
            "  O O _\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O\n" +
            "   O _\n" +
            "  O O _\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n", triangleOut8.toString());

    // two moves to quit
    Appendable triangleOut9 = new StringBuilder();
    this.triangle2 = new TriangleSolitaireModel();
    TriangleSolitaireTextView triangleV9 = new TriangleSolitaireTextView(this.triangle2,
            triangleOut9);
    Readable triangleR9 = new StringReader("3 3 1 1 3 1 3 3 Q");
    MarbleSolitaireControllerImpl test9 = new MarbleSolitaireControllerImpl(this.triangle2,
            triangleV9, triangleR9);
    test9.playGame();
    assertEquals(triangleOut9.toString(), "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "    O\n" +
            "   O _\n" +
            "  O O _\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n" +
            "    O\n" +
            "   O _\n" +
            "  _ _ O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 12\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O\n" +
            "   O _\n" +
            "  _ _ O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 12\n");

  }

  @Test
  public void testPlayGameQuitEuropean() {
    // European Solitaire //

    String defaultQuit = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n";

    // quit reader
    Appendable europeanOut0 = new StringBuilder();
    MarbleSolitaireTextView europeanV0 = new MarbleSolitaireTextView(this.europeanModel2,
            europeanOut0);
    MarbleSolitaireControllerImpl europeanTest0 = new MarbleSolitaireControllerImpl(
            this.europeanModel2, europeanV0, r19);
    europeanTest0.playGame();
    assertEquals(europeanOut0.toString(), defaultQuit);

    Appendable europeanOut1 = new StringBuilder();
    MarbleSolitaireTextView europeanV1 = new MarbleSolitaireTextView(this.europeanModel2,
            europeanOut1);
    MarbleSolitaireControllerImpl europeanTest1 = new MarbleSolitaireControllerImpl(
            this.europeanModel2, europeanV1, this.r20);
    europeanTest1.playGame();
    assertEquals(europeanOut1.toString(), defaultQuit);

    // big Q quit reader
    Appendable europeanOut2 = new StringBuilder();
    MarbleSolitaireTextView europeanV2 = new MarbleSolitaireTextView(this.europeanModel2,
            europeanOut2);
    MarbleSolitaireControllerImpl europeanTest2 = new MarbleSolitaireControllerImpl(
            this.europeanModel2, europeanV2, this.r21);
    europeanTest2.playGame();
    assertEquals(europeanOut2.toString(), defaultQuit);

    Appendable europeanOut3 = new StringBuilder();
    this.europeanModel2 = new EuropeanSolitaireModel();
    MarbleSolitaireTextView europeanV3 = new MarbleSolitaireTextView(this.europeanModel2,
            europeanOut3);
    MarbleSolitaireControllerImpl europeanTest3 = new MarbleSolitaireControllerImpl(
            this.europeanModel2, europeanV3, this.r22);
    europeanTest3.playGame();
    assertEquals(defaultQuit, europeanOut3.toString());

    // q in index 0 of reader
    Appendable europeanOut4 = new StringBuilder();
    this.europeanModel2 = new EuropeanSolitaireModel();
    Readable europeanR4 = new StringReader("q 2 4 4");
    MarbleSolitaireTextView europeanV4 = new MarbleSolitaireTextView(this.europeanModel2,
            europeanOut4);
    MarbleSolitaireControllerImpl europeanTest4 = new MarbleSolitaireControllerImpl(
            this.europeanModel2, europeanV4, europeanR4);
    europeanTest4.playGame();
    assertEquals(europeanOut4.toString(), defaultQuit);

    // q in index 1 of reader
    Appendable europeanOut5 = new StringBuilder();
    this.europeanModel2 = new EuropeanSolitaireModel();
    Readable europeanR5 = new StringReader("2 q 4 4");
    MarbleSolitaireTextView europeanV5 = new MarbleSolitaireTextView(this.europeanModel2,
            europeanOut5);
    MarbleSolitaireControllerImpl europeanTest5 = new MarbleSolitaireControllerImpl(
            this.europeanModel2, europeanV5, europeanR5);
    europeanTest5.playGame();
    assertEquals(europeanOut5.toString(), defaultQuit);

    // q in index 2 of reader
    Appendable europeanOut6 = new StringBuilder();
    this.europeanModel2 = new EuropeanSolitaireModel();
    Readable europeanR6 = new StringReader("4 2 q 4");
    MarbleSolitaireTextView europeanV6 = new MarbleSolitaireTextView(this.europeanModel2,
            europeanOut6);
    MarbleSolitaireControllerImpl europeanTest6 = new MarbleSolitaireControllerImpl(
            this.europeanModel2, europeanV6, europeanR6);
    europeanTest6.playGame();
    assertEquals(europeanOut6.toString(), defaultQuit);

    // q in index 3 of reader
    Appendable europeanOut7 = new StringBuilder();
    this.europeanModel2 = new EuropeanSolitaireModel();
    Readable europeanR7 = new StringReader("4 2 4 q");
    MarbleSolitaireTextView europeanV7 = new MarbleSolitaireTextView(this.europeanModel2,
            europeanOut7);
    MarbleSolitaireControllerImpl europeanTest7 = new MarbleSolitaireControllerImpl(
            this.europeanModel2, europeanV7, europeanR7);
    europeanTest7.playGame();
    assertEquals(europeanOut7.toString(), defaultQuit);

    // move to quit
    Appendable europeanOut8 = new StringBuilder();
    this.europeanModel2 = new EuropeanSolitaireModel();
    Readable europeanR8 = new StringReader("4 2 4 4 Q");
    MarbleSolitaireTextView europeanV8 = new MarbleSolitaireTextView(this.europeanModel2,
            europeanOut8);
    MarbleSolitaireControllerImpl europeanTest8 = new MarbleSolitaireControllerImpl(
            this.europeanModel2, europeanV8, europeanR8);
    europeanTest8.playGame();
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n", europeanOut8.toString());

    // two moves to quit
    Appendable europeanOut9 = new StringBuilder();
    this.europeanModel2 = new EuropeanSolitaireModel();
    MarbleSolitaireTextView europeanV9 = new MarbleSolitaireTextView(this.europeanModel2,
            europeanOut9);
    Readable europeanR9 = new StringReader("4 2 4 4 2 3 4 3 Q");
    MarbleSolitaireControllerImpl europeanTest9 = new MarbleSolitaireControllerImpl(
            this.europeanModel2, europeanV9, europeanR9);
    europeanTest9.playGame();
    assertEquals(europeanOut9.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "    O O O\n" +
            "  O _ O O O\n" +
            "O O _ O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O _ O O O\n" +
            "O O _ O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34\n");

  }

  // test for quitting the game
  @Test
  public void testPlayGameQuitEnglish() {

    // English Solitaire //

    // quit reader
    Appendable out0 = new StringBuilder();
    MarbleSolitaireTextView v0 = new MarbleSolitaireTextView(this.m1, out0);
    MarbleSolitaireControllerImpl test0 = new MarbleSolitaireControllerImpl(this.m1, v0, r19);
    test0.playGame();
    assertEquals(out0.toString(), this.s2);

    Appendable out = new StringBuilder();
    MarbleSolitaireTextView v1 = new MarbleSolitaireTextView(this.m1, out);
    MarbleSolitaireControllerImpl test = new MarbleSolitaireControllerImpl(this.m1, v1, this.r20);
    test.playGame();
    assertEquals(out.toString(), this.s2);
    // big Q quit reader
    Appendable out2 = new StringBuilder();
    MarbleSolitaireTextView v3 = new MarbleSolitaireTextView(this.m1, out2);
    MarbleSolitaireControllerImpl test2 = new MarbleSolitaireControllerImpl(this.m1, v3, this.r21);
    test2.playGame();
    assertEquals(out2.toString(), this.s2);

    Appendable out3 = new StringBuilder();
    this.m1 = new EnglishSolitaireModel();
    MarbleSolitaireTextView v4 = new MarbleSolitaireTextView(this.m1, out3);
    MarbleSolitaireControllerImpl test3 = new MarbleSolitaireControllerImpl(this.m1, v4, this.r22);
    test3.playGame();
    assertEquals(this.s2, out3.toString());

    // q in index 0 of reader
    Appendable outx = new StringBuilder();
    this.m1 = new EnglishSolitaireModel();
    Readable rx = new StringReader("q 2 4 4");
    MarbleSolitaireTextView vx = new MarbleSolitaireTextView(this.m1, outx);
    MarbleSolitaireControllerImpl testx = new MarbleSolitaireControllerImpl(this.m1, vx, rx);
    testx.playGame();
    assertEquals(outx.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n");
    // q in index 1 of reader
    Appendable outy = new StringBuilder();
    this.m1 = new EnglishSolitaireModel();
    Readable ry = new StringReader("2 q 4 4");
    MarbleSolitaireTextView vy = new MarbleSolitaireTextView(this.m1, outy);
    MarbleSolitaireControllerImpl testy = new MarbleSolitaireControllerImpl(this.m1, vy, ry);
    testy.playGame();
    assertEquals(outy.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n");
    // q in index 2 of reader
    Appendable outz = new StringBuilder();
    this.m1 = new EnglishSolitaireModel();
    Readable rz = new StringReader("4 2 q 4");
    MarbleSolitaireTextView vz = new MarbleSolitaireTextView(this.m1, outz);
    MarbleSolitaireControllerImpl testz = new MarbleSolitaireControllerImpl(this.m1, vz, rz);
    testz.playGame();
    assertEquals(outz.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n");
    // q in index 3 of reader
    Appendable outq = new StringBuilder();
    this.m1 = new EnglishSolitaireModel();
    Readable rq = new StringReader("4 2 4 q");
    MarbleSolitaireTextView vq = new MarbleSolitaireTextView(this.m1, outq);
    MarbleSolitaireControllerImpl testq = new MarbleSolitaireControllerImpl(this.m1, vq, rq);
    testq.playGame();
    assertEquals(outq.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n");

    // move to quit
    Appendable out4 = new StringBuilder();
    this.m1 = new EnglishSolitaireModel();
    Readable rt = new StringReader("4 2 4 4 Q");
    MarbleSolitaireTextView v5 = new MarbleSolitaireTextView(this.m1, out4);
    MarbleSolitaireControllerImpl test4 = new MarbleSolitaireControllerImpl(this.m1, v5, rt);
    test4.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n", out4.toString());

    // two moves to quit
    Appendable outc = new StringBuilder();
    MarbleSolitaireTextView vc = new MarbleSolitaireTextView(this.m1, outc);
    Readable rc = new StringReader("4 2 4 4 2 3 4 3 Q");
    MarbleSolitaireControllerImpl testc = new MarbleSolitaireControllerImpl(this.m1, vc, rc);
    testc.playGame();
    assertEquals(outc.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Invalid move. Play again. Please try other values.\n" +
            "    O O O\n" +
            "    _ O O\n" +
            "O O _ O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    _ O O\n" +
            "O O _ O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n");
  }

  //test for valid moves
  @Test
  public void testPlayGameValidMoveEnglish() {

    // two sets of move to quit
    Appendable out0 = new StringBuilder();
    this.m1 = new EnglishSolitaireModel();
    MarbleSolitaireTextView v0 = new MarbleSolitaireTextView(this.m1, out0);
    Readable r0 = new StringReader("4 2 4 4 2 3 4 3 Q");
    MarbleSolitaireControllerImpl testa = new MarbleSolitaireControllerImpl(this.m1, v0, r0);
    testa.playGame();
    assertEquals(out0.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "    _ O O\n" +
            "O O _ O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    _ O O\n" +
            "O O _ O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n");
    // leftwards movement reader
    Appendable out = new StringBuilder();
    this.m1 = new EnglishSolitaireModel();
    MarbleSolitaireTextView v1 = new MarbleSolitaireTextView(this.m1, out);
    MarbleSolitaireControllerImpl test0 = new MarbleSolitaireControllerImpl(this.m1, v1, this.r15);
    test0.playGame();
    assertEquals(out.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n");
    // rightwards movement reader
    Appendable out2 = new StringBuilder();
    this.m1 = new EnglishSolitaireModel();
    MarbleSolitaireTextView v3 = new MarbleSolitaireTextView(this.m1, out2);
    MarbleSolitaireControllerImpl test2 = new MarbleSolitaireControllerImpl(this.m1, v3, this.r16);
    test2.playGame();
    assertEquals(out2.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n");

    // upwards movement reader
    Appendable out1 = new StringBuilder();
    this.m1 = new EnglishSolitaireModel();
    MarbleSolitaireTextView v2 = new MarbleSolitaireTextView(this.m1, out1);
    MarbleSolitaireControllerImpl test1 = new MarbleSolitaireControllerImpl(this.m1, v2, this.r17);
    test1.playGame();
    assertEquals(out1.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 31\n");
    // downwards movement reader
    Appendable out3 = new StringBuilder();
    this.m1 = new EnglishSolitaireModel();
    MarbleSolitaireTextView v4 = new MarbleSolitaireTextView(this.m1, out3);
    MarbleSolitaireControllerImpl test3 = new MarbleSolitaireControllerImpl(this.m1, v4, this.r18);
    test3.playGame();
    assertEquals(out3.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n");
    // 3 valid moves to Q
    Appendable out4 = new StringBuilder();
    this.m1 = new EnglishSolitaireModel();
    Readable rt = new StringReader("4 2 4 4 2 3 4 3 4 4 4 2 Q");
    MarbleSolitaireTextView v5 = new MarbleSolitaireTextView(this.m1, out4);
    MarbleSolitaireControllerImpl test4 = new MarbleSolitaireControllerImpl(this.m1, v5, rt);
    test4.playGame();
    assertEquals(out4.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "    _ O O\n" +
            "O O _ O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "    O O O\n" +
            "    _ O O\n" +
            "O O _ O O O O\n" +
            "O O _ _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 29\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    _ O O\n" +
            "O O _ O O O O\n" +
            "O O _ _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 29\n");
    // Checking how my reader listens to valid to invalid to valid
    Appendable outi = new StringBuilder();
    this.m1 = new EnglishSolitaireModel();
    MarbleSolitaireTextView vi = new MarbleSolitaireTextView(this.m1, outi);
    Readable ri = new StringReader("4 2 a 4 4");
    MarbleSolitaireControllerImpl testi = new MarbleSolitaireControllerImpl(this.m1, vi, ri);
    testi.playGame();
    //    assertEquals(outi.toString(), "    O O O\n" +
    //            "    O O O\n" +
    //            "O O O O O O O\n" +
    //            "O O O _ O O O\n" +
    //            "O O O O O O O\n" +
    //            "    O O O\n" +
    //            "    O O O\n" +
    //            "Score: 32\n" +
    //            "Invalid input a please re-enter values\n" +
    //            "    O O O\n" +
    //            "    O O O\n" +
    //            "O O O O O O O\n" +
    //            "O _ _ O O O O\n" +
    //            "O O O O O O O\n" +
    //            "    O O O\n" +
    //            "    O O O\n" +
    //            "Score: 31\n");
    // Full Game finish
    Appendable outFinish = new StringBuilder();
    this.m1 = new EnglishSolitaireModel();
    MarbleSolitaireTextView vFinish = new MarbleSolitaireTextView(this.m1, outFinish);
    Readable rFinish = new StringReader("6 4 4 4 5 6 5 4 7 5 5 5 7 3 7 5 4 5 6 5 7 5 5 5 2 5 " +
            "4 5 3 7 3 5 5 7 3 7 3 4 3 6 3 7 3 5 3 2 3 4 1 3 3 3 1 5 1 3 4 3 2 3 1 3 3 3 6 3 4 3 " +
            "5 1 5 3 3 1 5 1 5 4 5 2 5 1 5 3 3 4 3 2 3 2 5 2 5 2 5 4 5 4 5 6 5 6 3 6 3 6 3 4 4 4 " +
            "4 6 2 4 4 4 4 3 4 5 4 6 4 4");
    MarbleSolitaireControllerImpl testFinish = new MarbleSolitaireControllerImpl(this.m1,
            vFinish, rFinish);
    testFinish.playGame();
    assertEquals(outFinish.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O _ O\n" +
            "    O _ _\n" +
            "    O O _\n" +
            "Score: 29\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O _ O\n" +
            "    O _ _\n" +
            "    _ _ O\n" +
            "Score: 28\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ O O\n" +
            "O O O O _ _ O\n" +
            "    O _ O\n" +
            "    _ _ O\n" +
            "Score: 27\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ O O\n" +
            "O O O O O _ O\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "Score: 26\n" +
            "    O O O\n" +
            "    O O _\n" +
            "O O O O _ O O\n" +
            "O O O O O O O\n" +
            "O O O O O _ O\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "Score: 25\n" +
            "    O O O\n" +
            "    O O _\n" +
            "O O O O O _ _\n" +
            "O O O O O O O\n" +
            "O O O O O _ O\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "Score: 24\n" +
            "    O O O\n" +
            "    O O _\n" +
            "O O O O O _ O\n" +
            "O O O O O O _\n" +
            "O O O O O _ _\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "Score: 23\n" +
            "    O O O\n" +
            "    O O _\n" +
            "O O O _ _ O O\n" +
            "O O O O O O _\n" +
            "O O O O O _ _\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "Score: 22\n" +
            "    O O O\n" +
            "    O O _\n" +
            "O O O _ O _ _\n" +
            "O O O O O O _\n" +
            "O O O O O _ _\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "Score: 21\n" +
            "    O O O\n" +
            "    O O _\n" +
            "O _ _ O O _ _\n" +
            "O O O O O O _\n" +
            "O O O O O _ _\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "Score: 20\n" +
            "    _ O O\n" +
            "    _ O _\n" +
            "O _ O O O _ _\n" +
            "O O O O O O _\n" +
            "O O O O O _ _\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "Score: 19\n" +
            "    O _ _\n" +
            "    _ O _\n" +
            "O _ O O O _ _\n" +
            "O O O O O O _\n" +
            "O O O O O _ _\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "Score: 18\n" +
            "    O _ _\n" +
            "    O O _\n" +
            "O _ _ O O _ _\n" +
            "O O _ O O O _\n" +
            "O O O O O _ _\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "Score: 17\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "O _ O O O _ _\n" +
            "O O _ O O O _\n" +
            "O O O O O _ _\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "Score: 16\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "O _ O O O _ _\n" +
            "O O O O O O _\n" +
            "O O _ O O _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 15\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "O _ O O O _ _\n" +
            "O O O O O O _\n" +
            "_ _ O O O _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 14\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "_ _ O O O _ _\n" +
            "_ O O O O O _\n" +
            "O _ O O O _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 13\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "_ _ O O O _ _\n" +
            "_ O O O O O _\n" +
            "O O _ _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 12\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "_ _ O O O _ _\n" +
            "_ O O O O O _\n" +
            "_ _ O _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 11\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "_ O _ _ O _ _\n" +
            "_ O O O O O _\n" +
            "_ _ O _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 10\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "_ _ _ _ O _ _\n" +
            "_ _ O O O O _\n" +
            "_ O O _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 9\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "_ _ _ _ O _ _\n" +
            "_ _ O O O O _\n" +
            "_ _ _ O O _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 8\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "_ _ _ _ O _ _\n" +
            "_ _ O O O O _\n" +
            "_ _ _ _ _ O _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 7\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "_ _ _ _ O O _\n" +
            "_ _ O O O _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 6\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ O O O _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 5\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ O _ _ O _\n" +
            "_ _ _ _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 4\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ O O _ O _\n" +
            "_ _ _ _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 3\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ _ O O _\n" +
            "_ _ _ _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 2\n" +
            "Game over!\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 1\n");

  }


  //test for valid moves
  @Test
  public void testPlayGameValidMoveEuropean() {

    // two sets of move to quit
    Appendable out0 = new StringBuilder();
    this.europeanModel2 = new EuropeanSolitaireModel();
    MarbleSolitaireTextView v0 = new MarbleSolitaireTextView(this.europeanModel2, out0);
    Readable r0 = new StringReader("4 2 4 4 2 3 4 3 Q");
    MarbleSolitaireControllerImpl testa = new MarbleSolitaireControllerImpl(this.europeanModel2,
            v0, r0);
    testa.playGame();
    assertEquals(out0.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "    O O O\n" +
            "  O _ O O O\n" +
            "O O _ O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O _ O O O\n" +
            "O O _ O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34\n");

    // leftwards movement reader
    Appendable out = new StringBuilder();
    this.europeanModel2 = new EuropeanSolitaireModel();
    MarbleSolitaireTextView v1 = new MarbleSolitaireTextView(this.europeanModel2, out);
    MarbleSolitaireControllerImpl test0 = new MarbleSolitaireControllerImpl(this.europeanModel2,
            v1, this.r15);
    test0.playGame();
    assertEquals(out.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n");

    // rightwards movement reader
    Appendable out2 = new StringBuilder();
    this.europeanModel2 = new EuropeanSolitaireModel();
    MarbleSolitaireTextView v3 = new MarbleSolitaireTextView(this.europeanModel2, out2);
    MarbleSolitaireControllerImpl test2 = new MarbleSolitaireControllerImpl(this.europeanModel2,
            v3, this.r16);
    test2.playGame();
    assertEquals(out2.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n");

    // upwards movement reader
    Appendable out1 = new StringBuilder();
    this.europeanModel2 = new EuropeanSolitaireModel();
    MarbleSolitaireTextView v2 = new MarbleSolitaireTextView(this.europeanModel2, out1);
    MarbleSolitaireControllerImpl test1 = new MarbleSolitaireControllerImpl(this.europeanModel2,
            v2, this.r17);
    test1.playGame();
    assertEquals(out1.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "  O O _ O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "  O O _ O O\n" +
            "    O O O\n" +
            "Score: 35\n");

    // downwards movement reader
    Appendable out3 = new StringBuilder();
    this.europeanModel2 = new EuropeanSolitaireModel();
    MarbleSolitaireTextView v4 = new MarbleSolitaireTextView(this.europeanModel2, out3);
    MarbleSolitaireControllerImpl test3 = new MarbleSolitaireControllerImpl(this.europeanModel2,
            v4, this.r18);
    test3.playGame();
    assertEquals(out3.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n");

    // 3 valid moves to Q
    Appendable out4 = new StringBuilder();
    this.europeanModel2 = new EuropeanSolitaireModel();
    Readable rt = new StringReader("4 2 4 4 2 3 4 3 4 4 4 2 Q");
    MarbleSolitaireTextView v5 = new MarbleSolitaireTextView(this.europeanModel2, out4);
    MarbleSolitaireControllerImpl test4 = new MarbleSolitaireControllerImpl(this.europeanModel2,
            v5, rt);
    test4.playGame();
    assertEquals(out4.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "    O O O\n" +
            "  O _ O O O\n" +
            "O O _ O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34\n" +
            "    O O O\n" +
            "  O _ O O O\n" +
            "O O _ O O O O\n" +
            "O O _ _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 33\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O _ O O O\n" +
            "O O _ O O O O\n" +
            "O O _ _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 33\n");

    // Checking how my reader listens to valid to invalid to valid
    //    Appendable outi = new StringBuilder();
    //    this.europeanModel2 = new EuropeanSolitaireModel();
    //    MarbleSolitaireTextView vi = new MarbleSolitaireTextView(this.europeanModel2, outi);
    //    Readable ri = new StringReader("4 2 a 4 4");
    //    MarbleSolitaireControllerImpl testi = new MarbleSolitaireControllerImpl
    //    (this.europeanModel2, vi, ri);
    //    testi.playGame();
    //    assertEquals(outi.toString(), "    O O O\n" +
    //            "  O O O O O\n" +
    //            "O O O O O O O\n" +
    //            "O O O _ O O O\n" +
    //            "O O O O O O O\n" +
    //            "   O O O O O\n" +
    //            "    O O O\n" +
    //            "Score: 32\n" +
    //            "Invalid input a please re-enter values\n" +
    //            "    O O O\n" +
    //            "  O O O O O\n" +
    //            "O O O O O O O\n" +
    //            "O _ _ O O O O\n" +
    //            "O O O O O O O\n" +
    //            "  O O O O O\n" +
    //            "    O O O\n" +
    //            "Score: 31\n");

    // Full Game finish
    Appendable outFinish = new StringBuilder();
    this.europeanModel2 = new EuropeanSolitaireModel(6, 2);
    MarbleSolitaireTextView vFinish = new MarbleSolitaireTextView(this.europeanModel2, outFinish);
    Readable rFinish = new StringReader("5 3 7 3 5 5 5 3 7 5 5 5 7 4 5 4 4 3 6 3 7 3 5 3 4 " +
            "1 4 3 6 2 4 2 5 4 5 2 5 1 5 3 3 2 5 2 5 2 5 4 3 4 3 2 3 1 3 3 1 4 3 4 2 2 2 4 4 " +
            "3 2 3 1 3 3 3 2 5 2 3 4 5 2 5 3 7 3 5 4 7 4 5 6 6 4 6 5 4 5 6 5 7 5 5 3 4 3 6 3 " +
            "6 5 6 5 6 5 4 5 4 3 4 3 3 3 5 2 6 2 4 4 5 2 5 1 5 3 5 2 3 2 5 2 5 4 5");
    MarbleSolitaireControllerImpl testFinish = new MarbleSolitaireControllerImpl(
            this.europeanModel2, vFinish, rFinish);
    testFinish.playGame();
    assertEquals(outFinish.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    _ O O\n" +
            "Score: 36\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O _ O O O O\n" +
            "  O _ O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ _ O O\n" +
            "  O _ O O O\n" +
            "    O O O\n" +
            "Score: 34\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "  O _ O _ O\n" +
            "    O O _\n" +
            "Score: 33\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O _ _ _ O\n" +
            "    O _ _\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O _ O O O O\n" +
            "O O _ O O O O\n" +
            "  O O _ _ O\n" +
            "    O _ _\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O _ O O O O\n" +
            "O O O O O O O\n" +
            "  O _ _ _ O\n" +
            "    _ _ _\n" +
            "Score: 30\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "_ _ O O O O O\n" +
            "O O O O O O O\n" +
            "  O _ _ _ O\n" +
            "    _ _ _\n" +
            "Score: 29\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "_ O O O O O O\n" +
            "O _ O O O O O\n" +
            "  _ _ _ _ O\n" +
            "    _ _ _\n" +
            "Score: 28\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "_ O O O O O O\n" +
            "O O _ _ O O O\n" +
            "  _ _ _ _ O\n" +
            "    _ _ _\n" +
            "Score: 27\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "_ O O O O O O\n" +
            "_ _ O _ O O O\n" +
            "  _ _ _ _ O\n" +
            "    _ _ _\n" +
            "Score: 26\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O _ O O O O O\n" +
            "_ _ O O O O O\n" +
            "_ O O _ O O O\n" +
            "  _ _ _ _ O\n" +
            "    _ _ _\n" +
            "Score: 25\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O _ O O O O O\n" +
            "_ _ O O O O O\n" +
            "_ _ _ O O O O\n" +
            "  _ _ _ _ O\n" +
            "    _ _ _\n" +
            "Score: 24\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O _ _ O O O\n" +
            "_ _ O O O O O\n" +
            "_ _ _ O O O O\n" +
            "  _ _ _ _ O\n" +
            "    _ _ _\n" +
            "Score: 23\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "_ _ O _ O O O\n" +
            "_ _ O O O O O\n" +
            "_ _ _ O O O O\n" +
            "  _ _ _ _ O\n" +
            "    _ _ _\n" +
            "Score: 22\n" +
            "    O _ O\n" +
            "  O O _ O O\n" +
            "_ _ O O O O O\n" +
            "_ _ O O O O O\n" +
            "_ _ _ O O O O\n" +
            "  _ _ _ _ O\n" +
            "    _ _ _\n" +
            "Score: 21\n" +
            "    O _ O\n" +
            "  _ _ O O O\n" +
            "_ _ O O O O O\n" +
            "_ _ O O O O O\n" +
            "_ _ _ O O O O\n" +
            "  _ _ _ _ O\n" +
            "    _ _ _\n" +
            "Score: 20\n" +
            "    O _ O\n" +
            "  _ O O O O\n" +
            "_ _ _ O O O O\n" +
            "_ _ _ O O O O\n" +
            "_ _ _ O O O O\n" +
            "  _ _ _ _ O\n" +
            "    _ _ _\n" +
            "Score: 19\n" +
            "    _ _ O\n" +
            "  _ _ O O O\n" +
            "_ _ O O O O O\n" +
            "_ _ _ O O O O\n" +
            "_ _ _ O O O O\n" +
            "  _ _ _ _ O\n" +
            "    _ _ _\n" +
            "Score: 18\n" +
            "    _ _ O\n" +
            "  _ O _ _ O\n" +
            "_ _ O O O O O\n" +
            "_ _ _ O O O O\n" +
            "_ _ _ O O O O\n" +
            "  _ _ _ _ O\n" +
            "    _ _ _\n" +
            "Score: 17\n" +
            "    _ _ O\n" +
            "  _ O _ O O\n" +
            "_ _ O O _ O O\n" +
            "_ _ _ O _ O O\n" +
            "_ _ _ O O O O\n" +
            "  _ _ _ _ O\n" +
            "    _ _ _\n" +
            "Score: 16\n" +
            "    _ _ O\n" +
            "  _ O _ O O\n" +
            "_ _ O O O _ _\n" +
            "_ _ _ O _ O O\n" +
            "_ _ _ O O O O\n" +
            "  _ _ _ _ O\n" +
            "    _ _ _\n" +
            "Score: 15\n" +
            "    _ _ O\n" +
            "  _ O _ O O\n" +
            "_ _ O O O _ _\n" +
            "_ _ _ O O _ _\n" +
            "_ _ _ O O O O\n" +
            "  _ _ _ _ O\n" +
            "    _ _ _\n" +
            "Score: 14\n" +
            "    _ _ O\n" +
            "  _ O _ O O\n" +
            "_ _ O O O _ _\n" +
            "_ _ _ O O O _\n" +
            "_ _ _ O O _ O\n" +
            "  _ _ _ _ _\n" +
            "    _ _ _\n" +
            "Score: 13\n" +
            "    _ _ O\n" +
            "  _ O _ O O\n" +
            "_ _ O O O _ _\n" +
            "_ _ _ O O O _\n" +
            "_ _ _ _ _ O O\n" +
            "  _ _ _ _ _\n" +
            "    _ _ _\n" +
            "Score: 12\n" +
            "    _ _ O\n" +
            "  _ O _ O O\n" +
            "_ _ O O O _ _\n" +
            "_ _ _ O O O _\n" +
            "_ _ _ _ O _ _\n" +
            "  _ _ _ _ _\n" +
            "    _ _ _\n" +
            "Score: 11\n" +
            "    _ _ O\n" +
            "  _ O _ O O\n" +
            "_ _ O _ _ O _\n" +
            "_ _ _ O O O _\n" +
            "_ _ _ _ O _ _\n" +
            "  _ _ _ _ _\n" +
            "    _ _ _\n" +
            "Score: 10\n" +
            "    _ _ O\n" +
            "  _ O _ O O\n" +
            "_ _ O _ _ _ _\n" +
            "_ _ _ O O _ _\n" +
            "_ _ _ _ O O _\n" +
            "  _ _ _ _ _\n" +
            "    _ _ _\n" +
            "Score: 9\n" +
            "    _ _ O\n" +
            "  _ O _ O O\n" +
            "_ _ O _ _ _ _\n" +
            "_ _ _ O O _ _\n" +
            "_ _ _ O _ _ _\n" +
            "  _ _ _ _ _\n" +
            "    _ _ _\n" +
            "Score: 8\n" +
            "    _ _ O\n" +
            "  _ O _ O O\n" +
            "_ _ O O _ _ _\n" +
            "_ _ _ _ O _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "  _ _ _ _ _\n" +
            "    _ _ _\n" +
            "Score: 7\n" +
            "    _ _ O\n" +
            "  _ O _ O O\n" +
            "_ _ _ _ O _ _\n" +
            "_ _ _ _ O _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "  _ _ _ _ _\n" +
            "    _ _ _\n" +
            "Score: 6\n" +
            "    _ _ O\n" +
            "  _ O O _ _\n" +
            "_ _ _ _ O _ _\n" +
            "_ _ _ _ O _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "  _ _ _ _ _\n" +
            "    _ _ _\n" +
            "Score: 5\n" +
            "    _ _ O\n" +
            "  _ O O O _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "  _ _ _ _ _\n" +
            "    _ _ _\n" +
            "Score: 4\n" +
            "    _ _ _\n" +
            "  _ O O _ _\n" +
            "_ _ _ _ O _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "  _ _ _ _ _\n" +
            "    _ _ _\n" +
            "Score: 3\n" +
            "    _ _ _\n" +
            "  _ _ _ O _\n" +
            "_ _ _ _ O _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "  _ _ _ _ _\n" +
            "    _ _ _\n" +
            "Score: 2\n" +
            "Game over!\n" +
            "    _ _ _\n" +
            "  _ _ _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ _ O _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "  _ _ _ _ _\n" +
            "    _ _ _\n" +
            "Score: 1\n");

  }

  //test for valid moves
  @Test
  public void testPlayGameValidMoveTriangle() {

    // two sets of move to quit
    Appendable out0 = new StringBuilder();
    this.triangle2 = new TriangleSolitaireModel();
    TriangleSolitaireTextView v0 = new TriangleSolitaireTextView(this.triangle2, out0);
    Readable r0 = new StringReader("3 1 1 1 3 3 3 1 Q");
    MarbleSolitaireControllerImpl testa = new MarbleSolitaireControllerImpl(this.triangle2, v0, r0);
    testa.playGame();
    assertEquals(out0.toString(), "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n" +
            "    O\n" +
            "   _ O\n" +
            "  O _ _\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 12\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O\n" +
            "   _ O\n" +
            "  O _ _\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 12\n");

    // leftwards movement reader
    Appendable out = new StringBuilder();
    this.triangle2 = new TriangleSolitaireModel();
    this.triangle2.board.get(2).set(0, MarbleSolitaireModelState.SlotState.Empty);
    Readable r1 = new StringReader("3 3 3 1 Q");
    TriangleSolitaireTextView v1 = new TriangleSolitaireTextView(this.triangle2, out);
    MarbleSolitaireControllerImpl test0 = new MarbleSolitaireControllerImpl(this.triangle2, v1, r1);
    test0.playGame();
    assertEquals(out.toString(), "    _\n" +
            "   O O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n" +
            "    _\n" +
            "   O O\n" +
            "  O _ _\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 12\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O _ _\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 12\n");

    // rightwards movement reader
    Appendable out2 = new StringBuilder();
    this.triangle2 = new TriangleSolitaireModel();
    this.triangle2.board.get(2).set(2, MarbleSolitaireModelState.SlotState.Empty);
    this.r16 = new StringReader("3 1 3 3 q");
    TriangleSolitaireTextView v3 = new TriangleSolitaireTextView(this.triangle2, out2);
    MarbleSolitaireControllerImpl test2 = new MarbleSolitaireControllerImpl(this.triangle2, v3,
            this.r16);
    test2.playGame();
    assertEquals(out2.toString(), "    _\n" +
            "   O O\n" +
            "  O O _\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n" +
            "    _\n" +
            "   O O\n" +
            "  _ _ O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 12\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  _ _ O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 12\n");

    // upwards/diagonal movement reader
    Appendable out1 = new StringBuilder();
    this.triangle2 = new TriangleSolitaireModel();
    this.r17 = new StringReader("3 1 1 1 q");
    TriangleSolitaireTextView v2 = new TriangleSolitaireTextView(this.triangle2, out1);
    MarbleSolitaireControllerImpl test1 = new MarbleSolitaireControllerImpl(this.triangle2, v2,
            this.r17);
    test1.playGame();
    assertEquals(out1.toString(), "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n");

    // downwards movement reader
    Appendable out3 = new StringBuilder();
    this.triangle2 = new TriangleSolitaireModel();
    this.triangle2.board.get(2).set(0, MarbleSolitaireModelState.SlotState.Empty);
    this.triangle2.board.get(0).set(0, MarbleSolitaireModelState.SlotState.Marble);
    this.r18 = new StringReader("1 1 3 1 q");
    TriangleSolitaireTextView v4 = new TriangleSolitaireTextView(this.triangle2, out3);
    MarbleSolitaireControllerImpl test3 = new MarbleSolitaireControllerImpl(this.triangle2, v4,
            this.r18);
    test3.playGame();
    assertEquals(out3.toString(), "    O\n" +
            "   O O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "    _\n" +
            "   _ O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   _ O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n");

    // 3 valid moves to Q
    Appendable out4 = new StringBuilder();
    this.triangle2 = new TriangleSolitaireModel();
    Readable rt = new StringReader("3 1 1 1 3 3 3 1 1 1 3 3 Q");
    TriangleSolitaireTextView v5 = new TriangleSolitaireTextView(this.triangle2, out4);
    MarbleSolitaireControllerImpl test4 = new MarbleSolitaireControllerImpl(this.triangle2, v5, rt);
    test4.playGame();
    assertEquals(out4.toString(), "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n" +
            "    O\n" +
            "   _ O\n" +
            "  O _ _\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 12\n" +
            "    _\n" +
            "   _ _\n" +
            "  O _ O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 11\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   _ _\n" +
            "  O _ O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 11\n");

    // Checking how my reader listens to valid to invalid to valid
    Appendable outi = new StringBuilder();
    this.triangle2 = new TriangleSolitaireModel();
    TriangleSolitaireTextView vi = new TriangleSolitaireTextView(this.triangle2, outi);
    Readable ri = new StringReader("4 2 a 4 4");
    MarbleSolitaireControllerImpl testi = new MarbleSolitaireControllerImpl(this.triangle2, vi, ri);
    // testi.playGame();

    // Full Game finish
    Appendable outFinish = new StringBuilder();
    this.triangle2 = new TriangleSolitaireModel();
    TriangleSolitaireTextView vFinish = new TriangleSolitaireTextView(this.triangle2, outFinish);
    Readable rFinish = new StringReader("3 1 1 1 3 3 3 1 1 1 3 3 4 1 2 1 4 4 2 2 5 2 3 2 " +
            "5 4 5 2 5 1 5 3 5 3 3 3 2 2 4 4 5 5 3 3 3 3 3 1 3 1 1 1 a");
    MarbleSolitaireControllerImpl testFinish = new MarbleSolitaireControllerImpl(this.triangle2,
            vFinish, rFinish);
    testFinish.playGame();
    assertEquals(outFinish.toString(), "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n" +
            "    O\n" +
            "   _ O\n" +
            "  O _ _\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 12\n" +
            "    _\n" +
            "   _ _\n" +
            "  O _ O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 11\n" +
            "    _\n" +
            "   O _\n" +
            "  _ _ O\n" +
            " _ O O O\n" +
            "O O O O O\n" +
            "Score: 10\n" +
            "    _\n" +
            "   O O\n" +
            "  _ _ _\n" +
            " _ O O _\n" +
            "O O O O O\n" +
            "Score: 9\n" +
            "    _\n" +
            "   O O\n" +
            "  _ O _\n" +
            " _ _ O _\n" +
            "O _ O O O\n" +
            "Score: 8\n" +
            "    _\n" +
            "   O O\n" +
            "  _ O _\n" +
            " _ _ O _\n" +
            "O O _ _ O\n" +
            "Score: 7\n" +
            "    _\n" +
            "   O O\n" +
            "  _ O _\n" +
            " _ _ O _\n" +
            "_ _ O _ O\n" +
            "Score: 6\n" +
            "    _\n" +
            "   O O\n" +
            "  _ O O\n" +
            " _ _ _ _\n" +
            "_ _ _ _ O\n" +
            "Score: 5\n" +
            "    _\n" +
            "   O _\n" +
            "  _ O _\n" +
            " _ _ _ O\n" +
            "_ _ _ _ O\n" +
            "Score: 4\n" +
            "    _\n" +
            "   O _\n" +
            "  _ O O\n" +
            " _ _ _ _\n" +
            "_ _ _ _ _\n" +
            "Score: 3\n" +
            "    _\n" +
            "   O _\n" +
            "  O _ _\n" +
            " _ _ _ _\n" +
            "_ _ _ _ _\n" +
            "Score: 2\n" +
            "Game over!\n" +
            "    O\n" +
            "   _ _\n" +
            "  _ _ _\n" +
            " _ _ _ _\n" +
            "_ _ _ _ _\n" +
            "Score: 1\n");

  }

  //test for invalid movements regarding marble placements
  @Test
  public void testPlayGameMoveInvalidMarbleEnglish() {
    // marble in to  reader
    Appendable out = new StringBuilder();
    MarbleSolitaireTextView v1 = new MarbleSolitaireTextView(this.m1, out);
    MarbleSolitaireControllerImpl test0 = new MarbleSolitaireControllerImpl(this.m1, v1, this.r12);
    test0.playGame();
    assertEquals(out.toString(), this.s1);

    // no marble in from reader
    Appendable out1 = new StringBuilder();
    MarbleSolitaireTextView v2 = new MarbleSolitaireTextView(this.m1, out1);
    MarbleSolitaireControllerImpl test1 = new MarbleSolitaireControllerImpl(this.m1, v2, this.r13);
    test1.playGame();
    assertEquals(out1.toString(), this.s1);
    // no marble in between reader
    Appendable out2 = new StringBuilder();
    MarbleSolitaireTextView v3 = new MarbleSolitaireTextView(this.m1, out2);
    MarbleSolitaireControllerImpl test2 = new MarbleSolitaireControllerImpl(this.m1, v3, this.r14);
    test2.playGame();
    assertEquals(out2.toString(), this.s1);

    // valid to Invalid Marble ( Marble in to)
    Appendable outa = new StringBuilder();
    MarbleSolitaireTextView va = new MarbleSolitaireTextView(this.m1, outa);
    Readable ra = new StringReader("4 2 4 4 4 1 4 3 q");
    MarbleSolitaireControllerImpl testa = new MarbleSolitaireControllerImpl(this.m1, va, ra);
    testa.playGame();
    assertEquals(outa.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Invalid move. Play again. Please try other values.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n");

    // valid to Invalid Marble ( no Marble in from)
    Appendable outb = new StringBuilder();
    MarbleSolitaireTextView vb = new MarbleSolitaireTextView(this.m1, outb);
    Readable rb = new StringReader("4 5 4 3 4 2 4 4 q");
    MarbleSolitaireControllerImpl testb = new MarbleSolitaireControllerImpl(this.m1, vb, rb);
    testb.playGame();
    assertEquals(outb.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O _ _ O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "Invalid move. Play again. Please try other values.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O _ _ O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n");

  }

  //test for invalid movements regarding marble placements
  @Test
  public void testPlayGameMoveInvalidMarbleEuropean() {

    String defaultInvalid = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Invalid move. Play again. Please try other values.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n";

    // marble in to  reader
    Appendable out = new StringBuilder();
    MarbleSolitaireTextView v1 = new MarbleSolitaireTextView(this.europeanModel2, out);
    MarbleSolitaireControllerImpl test0 = new MarbleSolitaireControllerImpl(this.europeanModel2,
            v1, this.r12);
    test0.playGame();
    assertEquals(out.toString(), defaultInvalid);

    // no marble in from reader
    Appendable out1 = new StringBuilder();
    MarbleSolitaireTextView v2 = new MarbleSolitaireTextView(this.europeanModel2, out1);
    MarbleSolitaireControllerImpl test1 = new MarbleSolitaireControllerImpl(this.europeanModel2,
            v2, this.r13);
    test1.playGame();
    assertEquals(out1.toString(), defaultInvalid);
    // no marble in between reader
    Appendable out2 = new StringBuilder();
    MarbleSolitaireTextView v3 = new MarbleSolitaireTextView(this.europeanModel2, out2);
    MarbleSolitaireControllerImpl test2 = new MarbleSolitaireControllerImpl(this.europeanModel2,
            v3, this.r14);
    test2.playGame();
    assertEquals(out2.toString(), defaultInvalid);

    // valid to Invalid Marble ( Marble in to)
    Appendable outa = new StringBuilder();
    MarbleSolitaireTextView va = new MarbleSolitaireTextView(this.europeanModel2, outa);
    Readable ra = new StringReader("4 2 4 4 4 1 4 3 q");
    MarbleSolitaireControllerImpl testa = new MarbleSolitaireControllerImpl(this.europeanModel2,
            va, ra);
    testa.playGame();
    assertEquals(outa.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "Invalid move. Play again. Please try other values.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n");

    // valid to Invalid Marble ( no Marble in from)
    Appendable outb = new StringBuilder();
    MarbleSolitaireTextView vb = new MarbleSolitaireTextView(this.europeanModel2, outb);
    Readable rb = new StringReader("4 5 4 3 4 2 4 4 q");
    MarbleSolitaireControllerImpl testb = new MarbleSolitaireControllerImpl(this.europeanModel2,
            vb, rb);
    testb.playGame();
    assertEquals(outb.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ O _ _ O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34\n" +
            "Invalid move. Play again. Please try other values.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ O _ _ O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34\n");

  }

  //test for invalid movements regarding marble placements
  @Test
  public void testPlayGameMoveInvalidMarbleTriangle() {

    String defaultInvalid = "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Invalid move. Play again. Please try other values.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n";

    // marble in to  reader
    Appendable out = new StringBuilder();
    TriangleSolitaireTextView v1 = new TriangleSolitaireTextView(this.triangle2, out);
    MarbleSolitaireControllerImpl test0 = new MarbleSolitaireControllerImpl(this.triangle2, v1,
            this.r12);
    test0.playGame();
    assertEquals(out.toString(), defaultInvalid);

    // no marble in from reader
    Appendable out1 = new StringBuilder();
    TriangleSolitaireTextView v2 = new TriangleSolitaireTextView(this.triangle2, out1);
    MarbleSolitaireControllerImpl test1 = new MarbleSolitaireControllerImpl(this.triangle2, v2,
            this.r13);
    test1.playGame();
    assertEquals(out1.toString(), defaultInvalid);
    // no marble in between reader
    Appendable out2 = new StringBuilder();
    TriangleSolitaireTextView v3 = new TriangleSolitaireTextView(this.triangle2, out2);
    MarbleSolitaireControllerImpl test2 = new MarbleSolitaireControllerImpl(this.triangle2, v3,
            this.r14);
    test2.playGame();
    assertEquals(out2.toString(), defaultInvalid);

    // valid to Invalid Marble ( Marble in to)
    Appendable outa = new StringBuilder();
    TriangleSolitaireTextView va = new TriangleSolitaireTextView(this.triangle2, outa);
    Readable ra = new StringReader("3 3 1 1 1 1 3 1 q");
    MarbleSolitaireControllerImpl testa = new MarbleSolitaireControllerImpl(this.triangle2, va, ra);
    testa.playGame();
    assertEquals(outa.toString(), "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "    O\n" +
            "   O _\n" +
            "  O O _\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n" +
            "Invalid move. Play again. Please try other values.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O\n" +
            "   O _\n" +
            "  O O _\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n");

    // valid to Invalid Marble ( no Marble in from)
    Appendable outb = new StringBuilder();
    this.triangle2 = new TriangleSolitaireModel();
    TriangleSolitaireTextView vb = new TriangleSolitaireTextView(this.triangle2, outb);
    Readable rb = new StringReader("3 3 1 1 3 3 1 1 q");
    MarbleSolitaireControllerImpl testb = new MarbleSolitaireControllerImpl(this.triangle2, vb, rb);
    testb.playGame();
    assertEquals(outb.toString(), "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "    O\n" +
            "   O _\n" +
            "  O O _\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n" +
            "Invalid move. Play again. Please try other values.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O\n" +
            "   O _\n" +
            "  O O _\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n");

  }

  // test for invalid move based on reader
  @Test
  public void testPlayGameInvalidMoveEnglish() {

    // less than 2 reader
    Appendable out = new StringBuilder();
    MarbleSolitaireTextView v1 = new MarbleSolitaireTextView(this.m1, out);
    MarbleSolitaireControllerImpl test0 = new MarbleSolitaireControllerImpl(this.m1, v1, this.r8);
    test0.playGame();
    assertEquals(out.toString(), this.s1);

    // more than 2 reader
    Appendable out1 = new StringBuilder();
    MarbleSolitaireTextView v2 = new MarbleSolitaireTextView(this.m1, out1);
    MarbleSolitaireControllerImpl test1 = new MarbleSolitaireControllerImpl(this.m1, v2, this.r9);
    test1.playGame();
    assertEquals(out1.toString(), this.s1);

    // invalid placement reader
    Appendable out2 = new StringBuilder();
    MarbleSolitaireTextView v3 = new MarbleSolitaireTextView(this.m1, out2);
    MarbleSolitaireControllerImpl test2 = new MarbleSolitaireControllerImpl(this.m1, v3, this.r10);
    test2.playGame();
    assertEquals(out2.toString(), this.s1);

    Appendable out3 = new StringBuilder();
    MarbleSolitaireTextView v4 = new MarbleSolitaireTextView(this.m1, out3);
    MarbleSolitaireControllerImpl test3 = new MarbleSolitaireControllerImpl(this.m1, v4, this.r11);
    test3.playGame();
    assertEquals(out3.toString(), this.s1);

    // negative
    Appendable out4 = new StringBuilder();
    MarbleSolitaireTextView v5 = new MarbleSolitaireTextView(this.m1, out4);
    MarbleSolitaireControllerImpl test4 = new MarbleSolitaireControllerImpl(this.m1, v5, this.r7);
    test4.playGame();
    assertEquals(out4.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Can't be negative integer, please re-enter values.\n" +
            "Can't be negative integer, please re-enter values.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n");
    // valid to invalid movement
    Appendable outa = new StringBuilder();
    MarbleSolitaireTextView va = new MarbleSolitaireTextView(this.m1, outa);
    Readable r0 = new StringReader("4 2 4 4 1 4 3 4 q");
    MarbleSolitaireControllerImpl testa = new MarbleSolitaireControllerImpl(this.m1, va, r0);
    testa.playGame();
    assertEquals(outa.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Invalid move. Play again. Please try other values.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n");

    // IO Exception when appendable can't transmit output.
    try {
      MarbleSolitaireView ve = new MarbleSolitaireCorruptibleView();
      Readable re = new StringReader("4 2 4 4 1 4 3 4 q");
      MarbleSolitaireControllerImpl teste = new MarbleSolitaireControllerImpl(this.m1, ve, re);
      teste.playGame();
    } catch (IllegalStateException e) {
      // throws an IllegalStateException as
    }

    // IO Exception when readable runs out of inputs.
    try {
      MarbleSolitaireView vt = new MarbleSolitaireCorruptibleView();
      Readable rt = new StringReader("4 2 4");
      MarbleSolitaireControllerImpl testt = new MarbleSolitaireControllerImpl(this.m1, vt, rt);
      testt.playGame();
    } catch (IllegalStateException e) {
      // throws an IllegalStateException as
    }

    // Exception when input is invalid and no more in readable.
    try {
      MarbleSolitaireView vt1 = new MarbleSolitaireCorruptibleView();
      Readable rt1 = new StringReader("b");
      MarbleSolitaireControllerImpl testt1 = new MarbleSolitaireControllerImpl(this.m1, vt1, rt1);
      testt1.playGame();
    } catch (IllegalStateException e) {
      // throws an IllegalStateException as
    }


  }

  // test for invalid move based on reader
  @Test
  public void testPlayGameInvalidMoveEuropean() {

    String invalidDefault = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Invalid move. Play again. Please try other values.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n";

    // less than 2 reader
    Appendable out = new StringBuilder();
    MarbleSolitaireTextView v1 = new MarbleSolitaireTextView(this.europeanModel2, out);
    MarbleSolitaireControllerImpl test0 = new MarbleSolitaireControllerImpl(this.europeanModel2,
            v1, this.r8);
    test0.playGame();
    assertEquals(out.toString(), invalidDefault);

    // more than 2 reader
    Appendable out1 = new StringBuilder();
    MarbleSolitaireTextView v2 = new MarbleSolitaireTextView(this.europeanModel2, out1);
    MarbleSolitaireControllerImpl test1 = new MarbleSolitaireControllerImpl(this.europeanModel2,
            v2, this.r9);
    test1.playGame();
    assertEquals(out1.toString(), invalidDefault);

    // invalid placement reader
    Appendable out2 = new StringBuilder();
    MarbleSolitaireTextView v3 = new MarbleSolitaireTextView(this.europeanModel2, out2);
    MarbleSolitaireControllerImpl test2 = new MarbleSolitaireControllerImpl(this.europeanModel2,
            v3, this.r10);
    test2.playGame();
    assertEquals(out2.toString(), invalidDefault);

    Appendable out3 = new StringBuilder();
    MarbleSolitaireTextView v4 = new MarbleSolitaireTextView(this.europeanModel2, out3);
    MarbleSolitaireControllerImpl test3 = new MarbleSolitaireControllerImpl(this.europeanModel2,
            v4, this.r11);
    test3.playGame();
    assertEquals(out3.toString(), invalidDefault);

    // negative
    Appendable out4 = new StringBuilder();
    MarbleSolitaireTextView v5 = new MarbleSolitaireTextView(this.europeanModel2, out4);
    MarbleSolitaireControllerImpl test4 = new MarbleSolitaireControllerImpl(this.europeanModel2,
            v5, this.r7);
    test4.playGame();
    assertEquals(out4.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Can't be negative integer, please re-enter values.\n" +
            "Can't be negative integer, please re-enter values.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n");
    // valid to invalid movement
    Appendable outa = new StringBuilder();
    MarbleSolitaireTextView va = new MarbleSolitaireTextView(this.europeanModel2, outa);
    Readable r0 = new StringReader("4 2 4 4 1 4 3 4 q");
    MarbleSolitaireControllerImpl testa = new MarbleSolitaireControllerImpl(this.europeanModel2,
            va, r0);
    testa.playGame();
    assertEquals(outa.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "Invalid move. Play again. Please try other values.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n");

    // IO Exception when appendable can't transmit output.
    try {
      MarbleSolitaireView ve = new MarbleSolitaireCorruptibleView();
      Readable re = new StringReader("4 2 4 4 1 4 3 4 q");
      MarbleSolitaireControllerImpl teste = new MarbleSolitaireControllerImpl(this.europeanModel2,
              ve, re);
      teste.playGame();
    } catch (IllegalStateException e) {
      // throws an IllegalStateException as
    }

    // IO Exception when readable runs out of inputs.
    try {
      MarbleSolitaireView vt = new MarbleSolitaireCorruptibleView();
      Readable rt = new StringReader("4 2 4");
      MarbleSolitaireControllerImpl testt = new MarbleSolitaireControllerImpl(this.europeanModel2,
              vt, rt);
      testt.playGame();
    } catch (IllegalStateException e) {
      // throws an IllegalStateException as
    }

    // Exception when input is invalid and no more in readable.
    try {
      MarbleSolitaireView vt1 = new MarbleSolitaireCorruptibleView();
      Readable rt1 = new StringReader("b");
      MarbleSolitaireControllerImpl testt1 = new MarbleSolitaireControllerImpl(this.europeanModel2,
              vt1, rt1);
      testt1.playGame();
    } catch (IllegalStateException e) {
      // throws an IllegalStateException as
    }
  }

  // test for invalid move based on reader
  @Test
  public void testPlayGameInvalidMoveTriangle() {

    String invalidDefault = "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Invalid move. Play again. Please try other values.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n";

    // less than 2 reader
    Appendable out = new StringBuilder();
    TriangleSolitaireTextView v1 = new TriangleSolitaireTextView(this.triangle2, out);
    MarbleSolitaireControllerImpl test0 = new MarbleSolitaireControllerImpl(this.triangle2, v1,
            this.r8);
    test0.playGame();
    assertEquals(out.toString(), invalidDefault);

    // more than 2 reader
    Appendable out1 = new StringBuilder();
    TriangleSolitaireTextView v2 = new TriangleSolitaireTextView(this.triangle2, out1);
    MarbleSolitaireControllerImpl test1 = new MarbleSolitaireControllerImpl(this.triangle2, v2,
            this.r9);
    test1.playGame();
    assertEquals(out1.toString(), invalidDefault);

    // invalid placement reader
    Appendable out2 = new StringBuilder();
    TriangleSolitaireTextView v3 = new TriangleSolitaireTextView(this.triangle2, out2);
    MarbleSolitaireControllerImpl test2 = new MarbleSolitaireControllerImpl(this.triangle2, v3,
            this.r10);
    test2.playGame();
    assertEquals(out2.toString(), invalidDefault);

    Appendable out3 = new StringBuilder();
    TriangleSolitaireTextView v4 = new TriangleSolitaireTextView(this.triangle2, out3);
    MarbleSolitaireControllerImpl test3 = new MarbleSolitaireControllerImpl(this.triangle2, v4,
            this.r11);
    test3.playGame();
    assertEquals(out3.toString(), invalidDefault);

    // negative
    Appendable out4 = new StringBuilder();
    TriangleSolitaireTextView v5 = new TriangleSolitaireTextView(this.triangle2, out4);
    MarbleSolitaireControllerImpl test4 = new MarbleSolitaireControllerImpl(this.triangle2, v5,
            this.r7);
    test4.playGame();
    assertEquals(out4.toString(), "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Can't be negative integer, please re-enter values.\n" +
            "Can't be negative integer, please re-enter values.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n");
    // valid to invalid movement
    Appendable outa = new StringBuilder();
    TriangleSolitaireTextView va = new TriangleSolitaireTextView(this.triangle2, outa);
    Readable r0 = new StringReader("3 3 1 1 1 4 3 4 q");
    MarbleSolitaireControllerImpl testa = new MarbleSolitaireControllerImpl(this.triangle2, va,
            r0);
    testa.playGame();
    assertEquals(outa.toString(), "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "    O\n" +
            "   O _\n" +
            "  O O _\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n" +
            "Invalid move. Play again. Please try other values.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O\n" +
            "   O _\n" +
            "  O O _\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n");

    // IO Exception when appendable can't transmit output.
    try {
      MarbleSolitaireView ve = new MarbleSolitaireCorruptibleView();
      Readable re = new StringReader("4 2 4 4 1 4 3 4 q");
      MarbleSolitaireControllerImpl teste = new MarbleSolitaireControllerImpl(this.triangle2, ve,
              re);
      teste.playGame();
    } catch (IllegalStateException e) {
      // throws an IllegalStateException as
    }

    // IO Exception when readable runs out of inputs.
    try {
      MarbleSolitaireView vt = new MarbleSolitaireCorruptibleView();
      Readable rt = new StringReader("4 2 4");
      MarbleSolitaireControllerImpl testt = new MarbleSolitaireControllerImpl(this.triangle2, vt,
              rt);
      testt.playGame();
    } catch (IllegalStateException e) {
      // throws an IllegalStateException as
    }

    // Exception when input is invalid and no more in readable.
    try {
      MarbleSolitaireView vt1 = new MarbleSolitaireCorruptibleView();
      Readable rt1 = new StringReader("b");
      MarbleSolitaireControllerImpl testt1 = new MarbleSolitaireControllerImpl(this.triangle2, vt1,
              rt1);
      testt1.playGame();
    } catch (IllegalStateException e) {
      // throws an IllegalStateException as
    }
  }

  // tests for invalid moves based on position on board
  @Test
  public void testPlayGameMoveInvalidPositionEnglish() {

    // outside the board reader
    Appendable out = new StringBuilder();
    MarbleSolitaireTextView v1 = new MarbleSolitaireTextView(this.m1, out);
    MarbleSolitaireControllerImpl mtest = new MarbleSolitaireControllerImpl(this.m1, v1, this.r5);
    mtest.playGame();
    assertEquals(out.toString(), this.s1);

    Appendable out1 = new StringBuilder();
    MarbleSolitaireTextView v2 = new MarbleSolitaireTextView(this.m1, out1);
    MarbleSolitaireControllerImpl mtest1 = new MarbleSolitaireControllerImpl(this.m1, v2, this.r6);
    mtest1.playGame();
    assertEquals(out1.toString(), this.s1);

    // From doesn't exist
    Appendable out2 = new StringBuilder();
    MarbleSolitaireTextView v3 = new MarbleSolitaireTextView(this.m1, out2);
    Readable r7 = new StringReader("1 2 1 4 q");
    MarbleSolitaireControllerImpl test2 = new MarbleSolitaireControllerImpl(this.m1, v3, r7);
    test2.playGame();
    assertEquals(out2.toString(), this.s1);

    // to doesn't exist
    Appendable out3 = new StringBuilder();
    MarbleSolitaireTextView v4 = new MarbleSolitaireTextView(this.m1, out3);
    Readable r8 = new StringReader("1 2 1 6 q");
    MarbleSolitaireControllerImpl test3 = new MarbleSolitaireControllerImpl(this.m1, v4, r8);
    test3.playGame();
    assertEquals(out3.toString(), this.s1);

    // move is diagonal
    Appendable out4 = new StringBuilder();
    MarbleSolitaireTextView v5 = new MarbleSolitaireTextView(this.m1, out4);
    Readable r9 = new StringReader("1 4 2 5 q");
    MarbleSolitaireControllerImpl test4 = new MarbleSolitaireControllerImpl(this.m1, v5, r9);
    test4.playGame();
    assertEquals(out4.toString(), this.s1);

    // valid to outside board movement
    Appendable out0 = new StringBuilder();
    this.m1 = new EnglishSolitaireModel();
    MarbleSolitaireTextView v0 = new MarbleSolitaireTextView(this.m1, out0);
    Readable r0 = new StringReader("4 2 4 4 1 4 1 6 q");
    MarbleSolitaireControllerImpl test0 = new MarbleSolitaireControllerImpl(this.m1, v0, r0);
    test0.playGame();
    assertEquals(out0.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Invalid move. Play again. Please try other values.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n");

  }


  // tests for invalid moves based on position on board
  @Test
  public void testPlayGameMoveInvalidPositionEuropean() {

    String defaultInvalid = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Invalid move. Play again. Please try other values.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n";

    // outside the board reader
    Appendable out = new StringBuilder();
    MarbleSolitaireTextView v1 = new MarbleSolitaireTextView(this.europeanModel2, out);
    MarbleSolitaireControllerImpl mtest = new MarbleSolitaireControllerImpl(this.europeanModel2,
            v1, this.r5);
    mtest.playGame();
    assertEquals(out.toString(), defaultInvalid);

    Appendable out1 = new StringBuilder();
    MarbleSolitaireTextView v2 = new MarbleSolitaireTextView(this.europeanModel2, out1);
    MarbleSolitaireControllerImpl mtest1 = new MarbleSolitaireControllerImpl(this.europeanModel2,
            v2, this.r6);
    mtest1.playGame();
    assertEquals(out1.toString(), defaultInvalid);

    // From doesn't exist
    Appendable out2 = new StringBuilder();
    MarbleSolitaireTextView v3 = new MarbleSolitaireTextView(this.europeanModel2, out2);
    Readable r7 = new StringReader("1 2 1 4 q");
    MarbleSolitaireControllerImpl test2 = new MarbleSolitaireControllerImpl(this.europeanModel2,
            v3, r7);
    test2.playGame();
    assertEquals(out2.toString(), defaultInvalid);

    // to doesn't exist
    Appendable out3 = new StringBuilder();
    MarbleSolitaireTextView v4 = new MarbleSolitaireTextView(this.europeanModel2, out3);
    Readable r8 = new StringReader("1 2 1 6 q");
    MarbleSolitaireControllerImpl test3 = new MarbleSolitaireControllerImpl(this.europeanModel2,
            v4, r8);
    test3.playGame();
    assertEquals(out3.toString(), defaultInvalid);

    // move is diagonal
    Appendable out4 = new StringBuilder();
    MarbleSolitaireTextView v5 = new MarbleSolitaireTextView(this.europeanModel2, out4);
    Readable r9 = new StringReader("1 4 2 5 q");
    MarbleSolitaireControllerImpl test4 = new MarbleSolitaireControllerImpl(this.europeanModel2,
            v5, r9);
    test4.playGame();
    assertEquals(out4.toString(), defaultInvalid);

    // valid to outside board movement
    Appendable out0 = new StringBuilder();
    this.m1 = new EnglishSolitaireModel();
    MarbleSolitaireTextView v0 = new MarbleSolitaireTextView(this.europeanModel2, out0);
    Readable r0 = new StringReader("4 2 4 4 1 4 1 6 q");
    MarbleSolitaireControllerImpl test0 = new MarbleSolitaireControllerImpl(this.europeanModel2,
            v0, r0);
    test0.playGame();
    assertEquals(out0.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "Invalid move. Play again. Please try other values.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n");

  }

  // tests for invalid moves based on position on board
  @Test
  public void testPlayGameMoveInvalidPositionTriangle() {

    String defaultInvalid = "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Invalid move. Play again. Please try other values.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n";

    // outside the board reader
    Appendable out = new StringBuilder();
    TriangleSolitaireTextView v1 = new TriangleSolitaireTextView(this.triangle2, out);
    MarbleSolitaireControllerImpl mtest = new MarbleSolitaireControllerImpl(this.triangle2, v1,
            this.r5);
    mtest.playGame();
    assertEquals(out.toString(), defaultInvalid);

    Appendable out1 = new StringBuilder();
    TriangleSolitaireTextView v2 = new TriangleSolitaireTextView(this.triangle2, out1);
    MarbleSolitaireControllerImpl mtest1 = new MarbleSolitaireControllerImpl(this.triangle2, v2,
            this.r6);
    mtest1.playGame();
    assertEquals(out1.toString(), defaultInvalid);

    // From doesn't exist
    Appendable out2 = new StringBuilder();
    TriangleSolitaireTextView v3 = new TriangleSolitaireTextView(this.triangle2, out2);
    Readable r7 = new StringReader("1 2 1 4 q");
    MarbleSolitaireControllerImpl test2 = new MarbleSolitaireControllerImpl(this.triangle2, v3, r7);
    test2.playGame();
    assertEquals(out2.toString(), defaultInvalid);

    // to doesn't exist
    Appendable out3 = new StringBuilder();
    TriangleSolitaireTextView v4 = new TriangleSolitaireTextView(this.triangle2, out3);
    Readable r8 = new StringReader("1 2 1 6 q");
    MarbleSolitaireControllerImpl test3 = new MarbleSolitaireControllerImpl(this.triangle2, v4, r8);
    test3.playGame();
    assertEquals(out3.toString(), defaultInvalid);

    // move is diagonal
    Appendable out4 = new StringBuilder();
    TriangleSolitaireTextView v5 = new TriangleSolitaireTextView(this.triangle2, out4);
    Readable r9 = new StringReader("1 4 2 5 q");
    MarbleSolitaireControllerImpl test4 = new MarbleSolitaireControllerImpl(this.triangle2, v5, r9);
    test4.playGame();
    assertEquals(out4.toString(), defaultInvalid);

    // valid to outside board movement
    Appendable out0 = new StringBuilder();
    this.m1 = new EnglishSolitaireModel();
    TriangleSolitaireTextView v0 = new TriangleSolitaireTextView(this.triangle2, out0);
    Readable r0 = new StringReader("4 2 4 4 1 4 1 6 q");
    MarbleSolitaireControllerImpl test0 = new MarbleSolitaireControllerImpl(this.triangle2, v0, r0);
    test0.playGame();
    assertEquals(out0.toString(), "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Invalid move. Play again. Please try other values.\n" +
            "Invalid move. Play again. Please try other values.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n");

  }

  // Tests the controller on the four new marbles in European Solitaire Game.
  @Test
  public void newMarblesEuropeanSolitaire() {
    // from top left
    Appendable out0 = new StringBuilder();
    this.europeanModel2 = new EuropeanSolitaireModel();
    this.europeanModel2.board.get(1).set(3, MarbleSolitaireModelState.SlotState.Empty);
    MarbleSolitaireTextView v0 = new MarbleSolitaireTextView(this.europeanModel2, out0);
    Readable r0 = new StringReader("2 2 2 4 q");
    MarbleSolitaireControllerImpl test0 = new MarbleSolitaireControllerImpl(this.europeanModel2,
            v0, r0);
    test0.playGame();
    assertEquals(out0.toString(), "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "    O O O\n" +
            "  _ _ O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  _ _ O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34\n");

    // to top left
    Appendable out1 = new StringBuilder();
    this.europeanModel2 = new EuropeanSolitaireModel();
    this.europeanModel2.board.get(1).set(1, MarbleSolitaireModelState.SlotState.Empty);
    MarbleSolitaireTextView v1 = new MarbleSolitaireTextView(this.europeanModel2, out1);
    Readable r1 = new StringReader("2 4 2 2 q");
    MarbleSolitaireControllerImpl test1 = new MarbleSolitaireControllerImpl(this.europeanModel2,
            v1, r1);
    test1.playGame();
    assertEquals(out1.toString(), "    O O O\n" +
            "  _ O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "    O O O\n" +
            "  O _ _ O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O _ _ O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34\n");

    // from top right
    Appendable out2 = new StringBuilder();
    this.europeanModel2 = new EuropeanSolitaireModel();
    this.europeanModel2.board.get(1).set(3, MarbleSolitaireModelState.SlotState.Empty);
    MarbleSolitaireTextView v2 = new MarbleSolitaireTextView(this.europeanModel2, out2);
    Readable r2 = new StringReader("2 6 2 4 q");
    MarbleSolitaireControllerImpl test2 = new MarbleSolitaireControllerImpl(this.europeanModel2,
            v2, r2);
    test2.playGame();
    assertEquals(out2.toString(), "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "    O O O\n" +
            "  O O O _ _\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O _ _\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34\n");

    // to top right
    Appendable out3 = new StringBuilder();
    this.europeanModel2 = new EuropeanSolitaireModel();
    this.europeanModel2.board.get(1).set(5, MarbleSolitaireModelState.SlotState.Empty);
    MarbleSolitaireTextView v3 = new MarbleSolitaireTextView(this.europeanModel2, out3);
    Readable r3 = new StringReader("2 4 2 6 q");
    MarbleSolitaireControllerImpl test3 = new MarbleSolitaireControllerImpl(this.europeanModel2,
            v3, r3);
    test3.playGame();
    assertEquals(out3.toString(), "    O O O\n" +
            "  O O O O _\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "    O O O\n" +
            "  O O _ _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O _ _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34\n");

    // from bottom left
    Appendable out4 = new StringBuilder();
    this.europeanModel2 = new EuropeanSolitaireModel();
    this.europeanModel2.board.get(5).set(3, MarbleSolitaireModelState.SlotState.Empty);
    MarbleSolitaireTextView v4 = new MarbleSolitaireTextView(this.europeanModel2, out4);
    Readable r4 = new StringReader("6 2 6 4 q");
    MarbleSolitaireControllerImpl test4 = new MarbleSolitaireControllerImpl(this.europeanModel2,
            v4, r4);
    test4.playGame();
    assertEquals(out4.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O _ O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  _ _ O O O\n" +
            "    O O O\n" +
            "Score: 34\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  _ _ O O O\n" +
            "    O O O\n" +
            "Score: 34\n");

    // to bottom left
    Appendable out5 = new StringBuilder();
    this.europeanModel2 = new EuropeanSolitaireModel();
    this.europeanModel2.board.get(5).set(1, MarbleSolitaireModelState.SlotState.Empty);
    MarbleSolitaireTextView v5 = new MarbleSolitaireTextView(this.europeanModel2, out5);
    Readable r5 = new StringReader("6 4 6 2 q");
    MarbleSolitaireControllerImpl test5 = new MarbleSolitaireControllerImpl(this.europeanModel2,
            v5, r5);
    test5.playGame();
    assertEquals(out5.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  _ O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O _ _ O O\n" +
            "    O O O\n" +
            "Score: 34\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O _ _ O O\n" +
            "    O O O\n" +
            "Score: 34\n");

    // from bottom right
    Appendable out6 = new StringBuilder();
    this.europeanModel2 = new EuropeanSolitaireModel();
    this.europeanModel2.board.get(5).set(3, MarbleSolitaireModelState.SlotState.Empty);
    MarbleSolitaireTextView v6 = new MarbleSolitaireTextView(this.europeanModel2, out6);
    Readable r6 = new StringReader("6 6 6 4 q");
    MarbleSolitaireControllerImpl test6 = new MarbleSolitaireControllerImpl(this.europeanModel2,
            v6, r6);
    test6.playGame();
    assertEquals(out6.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O _ O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O _ _\n" +
            "    O O O\n" +
            "Score: 34\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O _ _\n" +
            "    O O O\n" +
            "Score: 34\n");

    // to bottom right
    Appendable out7 = new StringBuilder();
    this.europeanModel2 = new EuropeanSolitaireModel();
    this.europeanModel2.board.get(5).set(5, MarbleSolitaireModelState.SlotState.Empty);
    MarbleSolitaireTextView v7 = new MarbleSolitaireTextView(this.europeanModel2, out7);
    Readable r7 = new StringReader("6 4 6 6 q");
    MarbleSolitaireControllerImpl test7 = new MarbleSolitaireControllerImpl(this.europeanModel2,
            v7, r7);
    test7.playGame();
    assertEquals(out7.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O _\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O _ _ O\n" +
            "    O O O\n" +
            "Score: 34\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O _ _ O\n" +
            "    O O O\n" +
            "Score: 34\n");


  }
  // If you're reading this, I would like to take the time and thank you for reading through
  // all my code and tests. Writing this has been a hectic and time-consuming experience,
  // I can only imagine the amount of work and time-sacrifice it takes to read through
  // so many students' code. So, I greatly appreciate the work you're doing and thank you
  // from the bottom of my heart!

}