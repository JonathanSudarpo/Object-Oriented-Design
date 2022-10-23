package cs3500.marblesolitaire;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;

import java.io.InputStreamReader;

/**
 * Represents a class that allows users to control/instantiate a game of either
 * English, European, or Triangle {@code MarbleSolitaire} with custom specifications/fields.
 */
public class MarbleSolitaire {

  private static Integer boardSize;
  private static Integer sRow;
  private static Integer sCol;

  /**
   * Represents the main method that allows users to run a game of {@code MarbleSolitaire}.
   *
   * @param args represents the users arguments' that runs a game of {@code MarbleSolitaire} with
   *             the option for custom specifications/fields.
   */
  public static void main(String[] args) {

    String gameType = args[0];
    MarbleSolitaireModel model;


    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-size")) {
        i++;
        boardSize = Integer.parseInt(args[i]);
      } else if (args[i].equals("-hole")) {
        sRow = Integer.parseInt(args[i + 1]);
        sCol = Integer.parseInt(args[i + 2]);
        i += 2;

      }
    }


    switch (gameType) {
      case "english":
        if (boardSize == null && sRow == null && sCol == null) {
          model = new EnglishSolitaireModel();
        } else if (boardSize == null && sRow != null && sCol != null) {
          model = new EnglishSolitaireModel(sRow, sCol);
        } else if (boardSize != null && sRow == null && sCol == null) {
          model = new EnglishSolitaireModel(boardSize);
        } else {
          model = new EnglishSolitaireModel(boardSize, sRow, sCol);
        }
        break;

      case "european":
        if (boardSize == null && sRow == null && sCol == null) {
          model = new EuropeanSolitaireModel();
        } else if (boardSize == null && sRow != null && sCol != null) {
          model = new EuropeanSolitaireModel(sRow, sCol);
        } else if (boardSize != null && sRow == null && sCol == null) {
          model = new EuropeanSolitaireModel(boardSize);
        } else {
          model = new EuropeanSolitaireModel(boardSize, sRow, sCol);
        }
        break;

      case "triangular":
        if (boardSize == null && sRow == null && sCol == null) {
          model = new TriangleSolitaireModel();
        } else if (boardSize == null && sRow != null && sCol != null) {
          model = new TriangleSolitaireModel(sRow, sCol);
        } else if (boardSize != null && sRow == null && sCol == null) {
          model = new TriangleSolitaireModel(boardSize);
        } else {
          model = new TriangleSolitaireModel(boardSize, sRow, sCol);
        }
        break;

      default:
        model = new EnglishSolitaireModel();
    }

    new MarbleSolitaireControllerImpl(model, new MarbleSolitaireTextView(model),
            new InputStreamReader(System.in)).playGame();
  }
}
