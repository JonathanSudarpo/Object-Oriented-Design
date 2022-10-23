package cs3500.marblesolitaire.view;

import java.io.IOException;
import java.util.Arrays;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This interface represents operations that should be offered by a view for the Triangular
 * implementation of a {@code MarbleSolitaire} game.
 */
public class TriangleSolitaireTextView implements MarbleSolitaireView {
  MarbleSolitaireModelState model;
  Appendable appendable;

  /**
   * Constructor for {@code TriangleSolitaireView}.
   *
   * @param model a {@code MarbleSolitaireModelState} such as {@code EnglishSolitaireModel}.
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model) {
    if (model == null) {
      throw new IllegalArgumentException("Model can't be null");
    } else {
      this.model = model;
      this.appendable = System.out;
    }
  }

  /**
   * A Constructor for {@code MarbleSolitaireTextView} that produces an output based on the fields.
   *
   * @param model      The model that the game is outputted based on.
   * @param appendable The appendable that the output is produced on.
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model, Appendable appendable) {
    if (model == null || appendable == null) {
      throw new IllegalArgumentException("Neither Model or the Appendable can be Null");
    } else {
      this.model = model;
      this.appendable = appendable;
    }
  }

  /**
   * The String representation of a {@code MarbleSolitaireModelState}.
   *
   * @return a String which prints out the board representation of a
   * {@code MarbleSolitaireModelState}.
   */
  @Override
  public String toString() {

    StringBuilder result = new StringBuilder();
    char[] spaces;

    for (int i = 0; i < model.getBoardSize(); i++) {
      spaces = new char[this.model.getBoardSize() - 1 - i];
      Arrays.fill(spaces, ' ');
      result.append(spaces);
      for (int j = 0; j < model.getBoardSize(); j++) {
        switch (model.getSlotAt(i, j)) {
          case Invalid:
            result = result.append("  ");
            continue;
          case Marble:
            result = result.append("O ");
            continue;
          case Empty:
            result = result.append("_ ");
            continue;
          default:
            continue;
        }
      }
      while (result.charAt(result.length() - 1) == ' ') {
        result = new StringBuilder(result.substring(0, result.length() - 1));
      }
      result = result.append("\n");
    }
    result = new StringBuilder(result.substring(0, result.length() - 1));
    return result.toString();
  }


  @Override
  public void renderBoard() throws IOException {
    try {
      appendable.append(this.toString());
    } catch (IOException io) {
      throw new IOException("Transmission of data failed");
    }
  }

  @Override
  public void renderMessage(String message) throws IOException {
    try {
      appendable.append(message);
    } catch (IOException io) {
      throw new IOException("Transmission of data failed");
    }
  }

}





