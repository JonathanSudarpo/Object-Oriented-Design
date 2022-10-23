package cs3500.marblesolitaire.view;

import java.io.IOException;

/**
 * A Corruptible class for {@code MarbleSolitaireView}.
 */
public class MarbleSolitaireCorruptibleView implements MarbleSolitaireView {

  @Override
  public void renderBoard() throws IOException {
    throw new IOException("Transmission of data failed");
  }

  @Override
  public void renderMessage(String message) throws IOException {
    throw new IOException("Transmission of data failed");
  }
}
