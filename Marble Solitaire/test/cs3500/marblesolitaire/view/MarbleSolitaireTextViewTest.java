package cs3500.marblesolitaire.view;

import org.junit.Test;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Represents tests for the {@code MarbleSolitaireTextView} class.
 */
public class MarbleSolitaireTextViewTest {
  // EUROPEAN SOLITAIRE MODEL //
  EuropeanSolitaireModel eCons1 = new EuropeanSolitaireModel();
  MarbleSolitaireTextView eM1 = new MarbleSolitaireTextView(this.eCons1);

  EuropeanSolitaireModel eCons2 = new EuropeanSolitaireModel(2, 2);
  MarbleSolitaireTextView eM2 = new MarbleSolitaireTextView(this.eCons2);

  EuropeanSolitaireModel eCons3 = new EuropeanSolitaireModel(5);
  MarbleSolitaireTextView eM3 = new MarbleSolitaireTextView(this.eCons3);


  // ENGLISH SOLITAIRE MODEL //

  // Standard board with an armThickness of 3.
  EnglishSolitaireModel cons1 = new EnglishSolitaireModel();
  MarbleSolitaireTextView m1 = new MarbleSolitaireTextView(this.cons1);

  // Standard board with the empty slot moved.
  EnglishSolitaireModel cons2 = new EnglishSolitaireModel(2, 2);
  MarbleSolitaireTextView m2 = new MarbleSolitaireTextView(this.cons2);

  // Board with an armThickness of 5.
  EnglishSolitaireModel cons3 = new EnglishSolitaireModel(5);
  MarbleSolitaireTextView m3 = new MarbleSolitaireTextView(this.cons3);

  //tests for valid constructor
  @Test
  public void validConstructor() {

    // EUROPEAN SOLITAIRE MODEL //
    Appendable eOut = new StringBuilder();
    EuropeanSolitaireModel eCons1 = new EuropeanSolitaireModel();
    MarbleSolitaireTextView eM1 = new MarbleSolitaireTextView(eCons1, eOut);
    assertEquals(eOut.toString(), "");
    assertEquals(eM1.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O");

    Appendable eOut2 = new StringBuilder();
    EuropeanSolitaireModel eCons2 = new EuropeanSolitaireModel(2, 2);
    MarbleSolitaireTextView eM2 = new MarbleSolitaireTextView(eCons2, eOut2);
    assertEquals(eOut2.toString(), "");
    assertEquals(eM2.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O _ O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O");

    Appendable eOut3 = new StringBuilder();
    EuropeanSolitaireModel eCons3 = new EuropeanSolitaireModel(5);
    MarbleSolitaireTextView eM3 = new MarbleSolitaireTextView(eCons3, eOut3);
    assertEquals(eOut3.toString(), "");
    assertEquals(eM3.toString(), "        O O O O O\n" +
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
            "        O O O O O");


    // ENGLISH SOLITAIRE MODEL //

    // cons1 reconstructed
    Appendable out = new StringBuilder();
    EnglishSolitaireModel cons1 = new EnglishSolitaireModel();
    MarbleSolitaireTextView m1 = new MarbleSolitaireTextView(cons1, out);
    assertEquals(out.toString(), "");
    assertEquals(m1.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O");

    // cons2 reconstructed
    Appendable out2 = new StringBuilder();
    EnglishSolitaireModel cons3 = new EnglishSolitaireModel(2, 2);
    MarbleSolitaireTextView m3 = new MarbleSolitaireTextView(cons3, out2);
    assertEquals(out2.toString(), "");
    assertEquals(m3.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O _ O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O");

    // cons=3 reconstructed
    Appendable out1 = new StringBuilder();
    EnglishSolitaireModel cons2 = new EnglishSolitaireModel(5);
    MarbleSolitaireTextView m2 = new MarbleSolitaireTextView(cons2, out1);
    assertEquals(out1.toString(), "");
    assertEquals(m2.toString(), "        O O O O O\n" +
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
            "        O O O O O");


  }

  // tests for invalid constructors
  @Test
  public void invalidConstructor() {

    // EUROPEAN SOLITAIRE MODEL //

    try {
      this.eM1 = new MarbleSolitaireTextView(null);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // Fails as the supplied model is null
    }

    try {
      this.eM1 = new MarbleSolitaireTextView(this.cons1, null);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as the supplied appendable is null
    }
    Appendable a2 = System.out;
    try {
      this.eM1 = new MarbleSolitaireTextView(null, a2);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as the supplied model is null
    }


    // ENGLISH SOLITAIRE MODEL //

    try {
      this.m1 = new MarbleSolitaireTextView(null);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // Fails as the supplied model is null
    }

    try {
      this.m1 = new MarbleSolitaireTextView(this.cons1, null);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as the supplied appendable is null
    }
    Appendable a1 = System.out;
    try {
      this.m1 = new MarbleSolitaireTextView(null, a1);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as the supplied model is null
    }

  }

  // tests for renderboard
  @Test
  public void testRenderBoard() throws IOException {
    // EUROPEAN SOLITAIRE MODEL //

    // Render a EuropeanSolitaireModel 3 x 3 board
    Appendable outEuropean = new StringBuilder();
    EuropeanSolitaireModel europe1 = new EuropeanSolitaireModel();
    MarbleSolitaireTextView testEuropean = new MarbleSolitaireTextView(europe1, outEuropean);
    testEuropean.renderBoard();
    assertEquals(outEuropean.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O");

    // a 5 x 5 board
    Appendable outEuropean1 = new StringBuilder();
    MarbleSolitaireView testEA = new MarbleSolitaireTextView(this.eCons3, outEuropean1);
    testEA.renderBoard();
    assertEquals(outEuropean1.toString(),
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
                    "        O O O O O");

    // a board with a changed empty slot
    Appendable outEuropean2 = new StringBuilder();
    MarbleSolitaireTextView testE2 = new MarbleSolitaireTextView(this.eCons2, outEuropean2);
    testE2.renderBoard();
    assertEquals(outEuropean2.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O _ O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O");

    // a board with all marbles
    Appendable outEuropean3 = new StringBuilder();
    EuropeanSolitaireModel europeanA = new EuropeanSolitaireModel();
    europeanA.board.get(3).set(3, MarbleSolitaireModelState.SlotState.Marble);
    MarbleSolitaireTextView testE3 = new MarbleSolitaireTextView(europeanA, outEuropean3);
    testE3.renderBoard();
    assertEquals(outEuropean3.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O");

    // a board with only one marble
    Appendable outEuropean4 = new StringBuilder();
    EuropeanSolitaireModel european2 = new EuropeanSolitaireModel();
    for (int i = 0; i < european2.getBoardSize(); i++) {
      for (int j = 0; j < european2.getBoardSize(); j++) {
        if (european2.board.get(i).get(j) == MarbleSolitaireModelState.SlotState.Marble) {
          european2.board.get(i).set(j, MarbleSolitaireModelState.SlotState.Empty);
        }
      }
    }
    european2.board.get(3).set(3, MarbleSolitaireModelState.SlotState.Marble);
    MarbleSolitaireView testEuropean4 = new MarbleSolitaireTextView(european2, outEuropean4);
    testEuropean4.renderBoard();
    assertEquals(outEuropean4.toString(), "    _ _ _\n" +
            "  _ _ _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "  _ _ _ _ _\n" +
            "    _ _ _");

    // ENGLISH SOLITAIRE MODEL //

    // a 3 x 3 board
    Appendable out = new StringBuilder();
    MarbleSolitaireTextView test = new MarbleSolitaireTextView(this.cons1, out);
    test.renderBoard();
    assertEquals(out.toString(),
            "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O");
    // a 5 x 5 board
    MarbleSolitaireView testA = new MarbleSolitaireTextView(this.cons3, out);
    testA.renderBoard();
    assertEquals(out.toString(),
            "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O        O O O O O\n" +
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
                    "        O O O O O");
    // a board with a changed empty slot
    Appendable out2 = new StringBuilder();
    MarbleSolitaireTextView test2 = new MarbleSolitaireTextView(this.cons2, out2);
    test2.renderBoard();
    assertEquals(out2.toString(),
            "    O O O\n" +
                    "    O O O\n" +
                    "O O _ O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O");
    // a board with all marbles
    Appendable out3 = new StringBuilder();
    EnglishSolitaireModel e1 = new EnglishSolitaireModel();
    e1.board.get(3).set(3, MarbleSolitaireModelState.SlotState.Marble);
    MarbleSolitaireTextView test3 = new MarbleSolitaireTextView(e1, out3);
    test3.renderBoard();
    assertEquals(out3.toString(),
            "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O");
    // a board with only one marble
    Appendable out4 = new StringBuilder();
    EnglishSolitaireModel e2 = new EnglishSolitaireModel();
    for (int i = 0; i < e2.getBoardSize(); i++) {
      for (int j = 0; j < e2.getBoardSize(); j++) {
        if (e2.board.get(i).get(j) == MarbleSolitaireModelState.SlotState.Marble) {
          e2.board.get(i).set(j, MarbleSolitaireModelState.SlotState.Empty);
        }
      }
    }
    e2.board.get(3).set(3, MarbleSolitaireModelState.SlotState.Marble);
    MarbleSolitaireView test4 = new MarbleSolitaireTextView(e2, out4);
    test4.renderBoard();
    assertEquals(out4.toString(),
            "    _ _ _\n" +
                    "    _ _ _\n" +
                    "_ _ _ _ _ _ _\n" +
                    "_ _ _ O _ _ _\n" +
                    "_ _ _ _ _ _ _\n" +
                    "    _ _ _\n" +
                    "    _ _ _");
  }

  @Test
  public void testIOException() throws IOException {
    try {
      MarbleSolitaireView v1 = new MarbleSolitaireCorruptibleView();
      v1.renderMessage("Fails");
      fail("Should have thrown an IOException");
    } catch (IOException io) {
      // fails as v1 is corruptible.
    }
    try {
      MarbleSolitaireView v1 = new MarbleSolitaireCorruptibleView();
      v1.renderBoard();
      fail("Should have thrown an IOException");
    } catch (IOException io) {
      // fails as v1 is corruptible.
    }
  }

  // tests for render message
  @Test
  public void testRenderMessage() throws IOException {
    // EUROPEAN SOLITAIRE MODEL //
    Appendable outEuropean = new StringBuilder();
    MarbleSolitaireTextView testEuropean = new MarbleSolitaireTextView(this.eCons1, outEuropean);
    testEuropean.renderMessage("Game over!");
    assertEquals(outEuropean.toString(), "Game over!");

    testEuropean.renderMessage("\nScore: 10");
    assertEquals(outEuropean.toString(), "Game over!\n" +
            "Score: 10");

    // Empty string messages
    Appendable outEuropean2 = new StringBuilder();
    MarbleSolitaireTextView testEuropean2 = new MarbleSolitaireTextView(this.eCons1, outEuropean2);
    testEuropean2.renderMessage("");
    assertEquals(outEuropean2.toString(), "");

    // ENGLISH SOLITAIRE MODEL //

    // Normal messages
    Appendable out = new StringBuilder();
    MarbleSolitaireTextView test = new MarbleSolitaireTextView(this.cons1, out);
    test.renderMessage("Game over!");
    assertEquals(out.toString(), "Game over!");

    test.renderMessage("\nScore: 10");
    assertEquals(out.toString(), "Game over!\n" +
            "Score: 10");
    // Empty string messages
    Appendable out2 = new StringBuilder();
    MarbleSolitaireTextView test2 = new MarbleSolitaireTextView(this.cons1, out2);
    test2.renderMessage("");
    assertEquals(out2.toString(), "");

  }

  // tests for toString
  @Test
  public void testToString() {
    assertEquals(this.eM1.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O");
    assertEquals(this.eM2.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O _ O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O");
    assertEquals(this.eM3.toString(), "        O O O O O\n" +
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
            "        O O O O O");

    assertEquals(this.m1.toString(),
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O O O _ O O O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O");


    assertEquals(this.m2.toString(),
            "    O O O\n"
                    + "    O O O\n"
                    + "O O _ O O O O\n"
                    + "O O O O O O O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O");

    assertEquals(this.m3.toString(),
            "        O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O _ O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O");
  }

}