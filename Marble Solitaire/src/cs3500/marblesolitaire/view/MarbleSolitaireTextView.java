package cs3500.marblesolitaire.view;

import java.io.IOException;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents operations to view a {@code MarbleSolitaire} game as a text.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {
  MarbleSolitaireModelState model;
  Appendable appendable;

  /**
   * Constructor for {@code MarbleSolitaireTextView}.
   *
   * @param model a {@code MarbleSolitaireModelState} such as {@code EnglishSolitaireModel}.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model) {
    if (model == null) {
      throw new IllegalArgumentException("Model can't be null");
    } else {
      this.model = model;
      this.appendable = System.out;
    }
  }

  /**
   * A Constructor for {@code MarbleSolitaireTextView} that produces an output based on the fields.
   * @param model The model that the game is outputted based on.
   * @param appendable The appendable that the output is produced on.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model, Appendable appendable) {
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

    for (int i = 0; i < model.getBoardSize(); i++) {
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

