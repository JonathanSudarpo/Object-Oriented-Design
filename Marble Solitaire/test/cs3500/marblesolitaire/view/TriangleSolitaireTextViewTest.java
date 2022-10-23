package cs3500.marblesolitaire.view;

import org.junit.Test;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Represents tests for the {@code TriangleSolitaireTextView} class.
 */
public class TriangleSolitaireTextViewTest {
  TriangleSolitaireModel cons1 = new TriangleSolitaireModel();
  TriangleSolitaireTextView text1 = new TriangleSolitaireTextView(this.cons1);

  TriangleSolitaireModel cons2 = new TriangleSolitaireModel(6);
  TriangleSolitaireTextView text2 = new TriangleSolitaireTextView(this.cons2);


  TriangleSolitaireModel cons3 = new TriangleSolitaireModel(2, 2);
  TriangleSolitaireTextView text3 = new TriangleSolitaireTextView(this.cons3);


  TriangleSolitaireModel cons4 = new TriangleSolitaireModel(2, 1, 0);
  TriangleSolitaireTextView text4 = new TriangleSolitaireTextView(this.cons4);


  // tests that the constructor is valid.
  @Test
  public void validConstructor() {
    Appendable out1 = new StringBuilder();
    TriangleSolitaireModel cons1 = new TriangleSolitaireModel();
    TriangleSolitaireTextView view1 = new TriangleSolitaireTextView(cons1, out1);
    assertEquals(out1.toString(), "");
    assertEquals(view1.toString(), "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O");

    Appendable out2 = new StringBuilder();
    TriangleSolitaireModel cons2 = new TriangleSolitaireModel(6);
    TriangleSolitaireTextView view2 = new TriangleSolitaireTextView(cons2, out2);
    assertEquals(out2.toString(), "");
    assertEquals(view2.toString(), "     _\n" +
            "    O O\n" +
            "   O O O\n" +
            "  O O O O\n" +
            " O O O O O\n" +
            "O O O O O O");

    Appendable out3 = new StringBuilder();
    TriangleSolitaireModel cons3 = new TriangleSolitaireModel(2, 2);
    TriangleSolitaireTextView view3 = new TriangleSolitaireTextView(cons3, out3);
    assertEquals(out3.toString(), "");
    assertEquals(view3.toString(), "    O\n" +
            "   O O\n" +
            "  O O _\n" +
            " O O O O\n" +
            "O O O O O");

    Appendable out4 = new StringBuilder();
    TriangleSolitaireModel cons4 = new TriangleSolitaireModel(2, 1, 0);
    TriangleSolitaireTextView view4 = new TriangleSolitaireTextView(cons4, out4);
    assertEquals(out4.toString(), "");
    assertEquals(view4.toString(), " O\n" +
            "_ O");
  }

  // tests for invalid constructors
  @Test
  public void invalidConstructor() {

    // EUROPEAN SOLITAIRE MODEL //

    try {
      this.text1 = new TriangleSolitaireTextView(null);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // Fails as the supplied model is null
    }

    try {
      this.text2 = new TriangleSolitaireTextView(this.cons1, null);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as the supplied appendable is null
    }

    Appendable a2 = System.out;
    try {
      this.text3 = new TriangleSolitaireTextView(null, a2);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // fails as the supplied model is null
    }

  }

  // tests the renderBoard method.
  @Test
  public void testRenderBoard() throws IOException {

    // Render a default TriangleSolitaireBoard
    Appendable out1 = new StringBuilder();
    TriangleSolitaireModel model1 = new TriangleSolitaireModel();
    TriangleSolitaireTextView test1 = new TriangleSolitaireTextView(model1, out1);
    test1.renderBoard();
    assertEquals(out1.toString(), "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O");

    // a length 6 TriangleSolitaireBoard
    Appendable out2 = new StringBuilder();
    TriangleSolitaireTextView test2 = new TriangleSolitaireTextView(this.cons2, out2);
    test2.renderBoard();
    assertEquals(out2.toString(), "     _\n" +
            "    O O\n" +
            "   O O O\n" +
            "  O O O O\n" +
            " O O O O O\n" +
            "O O O O O O");

    // a TriangleSolitaire Board with a changed empty slot
    Appendable out3 = new StringBuilder();
    TriangleSolitaireTextView test3 = new TriangleSolitaireTextView(this.cons3, out3);
    test3.renderBoard();
    assertEquals(out3.toString(), "    O\n" +
            "   O O\n" +
            "  O O _\n" +
            " O O O O\n" +
            "O O O O O");

    // a board with all marbles
    Appendable out4 = new StringBuilder();
    TriangleSolitaireModel model2 = new TriangleSolitaireModel();
    model2.board.get(0).set(0, MarbleSolitaireModelState.SlotState.Marble);
    TriangleSolitaireTextView test4 = new TriangleSolitaireTextView(model2, out4);
    test4.renderBoard();
    assertEquals(out4.toString(), "    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O");

    // a board with only one marble
    Appendable out5 = new StringBuilder();
    TriangleSolitaireModel model3 = new TriangleSolitaireModel();
    for (int i = 0; i < model3.getBoardSize(); i++) {
      for (int j = 0; j < model3.getBoardSize(); j++) {
        if (model3.board.get(i).get(j) == MarbleSolitaireModelState.SlotState.Marble) {
          model3.board.get(i).set(j, MarbleSolitaireModelState.SlotState.Empty);
        }
      }
    }
    model3.board.get(3).set(3, MarbleSolitaireModelState.SlotState.Marble);
    TriangleSolitaireTextView test5 = new TriangleSolitaireTextView(model3, out5);
    test5.renderBoard();
    assertEquals(out5.toString(), "    _\n" +
            "   _ _\n" +
            "  _ _ _\n" +
            " _ _ _ O\n" +
            "_ _ _ _ _");
  }

  //Tests IOException
  // Justification: I chose to use the same MarbleSolitaireCorruptibleView
  // as they have pretty much the same corrupted methods that throws the IOException.
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
    Appendable out1 = new StringBuilder();
    TriangleSolitaireTextView test1 = new TriangleSolitaireTextView(this.cons1, out1);
    test1.renderMessage("Game over!");
    assertEquals(out1.toString(), "Game over!");

    test1.renderMessage("\nScore: 10");
    assertEquals(out1.toString(), "Game over!\n" +
            "Score: 10");

    // Empty string messages
    Appendable out2 = new StringBuilder();
    TriangleSolitaireTextView test2 = new TriangleSolitaireTextView(this.cons1, out2);
    test2.renderMessage("");
    assertEquals(out2.toString(), "");
  }

  // tests for toString
  @Test
  public void testToString() {
    assertEquals(this.text1.toString(), "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O");
    assertEquals(this.text2.toString(), "     _\n" +
            "    O O\n" +
            "   O O O\n" +
            "  O O O O\n" +
            " O O O O O\n" +
            "O O O O O O");
    assertEquals(this.text3.toString(), "    O\n" +
            "   O O\n" +
            "  O O _\n" +
            " O O O O\n" +
            "O O O O O");
    assertEquals(this.text4.toString(), " O\n" +
            "_ O");
  }

}