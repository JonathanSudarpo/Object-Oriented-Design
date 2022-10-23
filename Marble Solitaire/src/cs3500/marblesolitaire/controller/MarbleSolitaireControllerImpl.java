package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Represents the implementation of the controller of a game of {@code MarbleSolitaire}.
 * This class contains operations and methods that enables a player to supply arguments
 * to play a game of {@code MarbleSolitaire}.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {

  private MarbleSolitaireModel model;
  private MarbleSolitaireView view;
  private Readable readable;

  /**
   * A Constructor for the controller of a Marble Solitaire Game.
   *
   * @param model    The model that the Marble Solitaire Game instantiates with.
   * @param view     The view that the Marble Solitaire Game instantiates using.
   * @param readable Represents the user inputs that are supplied into the controller.
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
                                       Readable readable) throws IllegalArgumentException {
    if (model == null || view == null || readable == null) {
      throw new IllegalArgumentException("Fields cannot be null");
    } else {
      this.model = model;
      this.view = view;
      this.readable = readable;
    }

  }

  /**
   * Plays a game of Marble Solitaire using user inputs (Readable), and outputs the appropriate
   * view, and model based on those inputs.
   */
  @Override
  public void playGame() throws IllegalStateException {
    Scanner s = new Scanner(readable);
    int fromRow = 0;
    int fromCol = 0;
    int toRow = 0;
    int toCol = 0;
    int accumulator = 0;
    String next;

    //When the game is initially played, we render the state of the board and the current score.
    this.renderBoardHelper();
    this.renderMessageHelper("\nScore: " + (this.model.getScore()) + "\n");

    // while the game is NOT over,
    while (!(model.isGameOver())) {

      // while our readable has a next
      while (s.hasNext()) {
        // we make sure that the next is not empty using the following try/.catch.
        try {
          next = s.next();
          // if it is empty, then we throw an IllegalStateException
        } catch (NoSuchElementException e) {
          throw new IllegalStateException("Ran out of valid inputs\n");
        }
        // If the current readable is an integer
        if (this.isInteger(next)) {
          // make sure that it isn't negative
          if (Integer.parseInt(next) < 0) {
            this.renderMessageHelper("Can't be negative integer, please re-enter values.\n");
            continue;
            // check if it represents the fromRow
          } else if (accumulator % 4 == 0) {
            fromRow = Integer.parseInt(next) - 1;
            accumulator += 1;
            // check if it represents the fromCol
          } else if (accumulator % 4 == 1) {
            fromCol = Integer.parseInt(next) - 1;
            accumulator += 1;
            // check if it represents the toRow
          } else if (accumulator % 4 == 2) {
            toRow = Integer.parseInt(next) - 1;
            accumulator += 1;
            // check if it represents the toCol
          } else if (accumulator % 4 == 3) {
            toCol = Integer.parseInt(next) - 1;
            accumulator += 1;
            // if four moves is done, we try to supply values
            // to the move method using the following try/catch.
            try {
              this.model.move(fromRow, fromCol, toRow, toCol);
              // if the game is over after the move, then we break.
              if (this.model.isGameOver()) {
                break;
              }
              // and print the appropriate game over messages.
              this.isGameOverControl();
              // if however the values are invalid, then throw an error message and user try again.
            } catch (IllegalArgumentException e) {
              this.renderMessageHelper("Invalid move. Play again. Please try other values.\n");
              continue;
            }
          }
          // else, if next is quit, then run the isQuitGame method
          // which outputs proper quit messages.
        } else if (this.isQuitGame(next)) {
          return;
        }
      }
      continue;
    }
    // once the game is over, print the appropriate game over messages.
    this.isGameOverControl();
  }

  // Determines if the game is over. If it is, it renders the game over message,
  // the state of the board, and the final score.
  private boolean isGameOverControl() {
    // if it is game over, do the necessary messages
    if (this.model.isGameOver()) {
      this.renderMessageHelper("Game over!\n");
      this.renderBoardHelper();
      this.renderMessageHelper("\nScore: " + (this.model.getScore()) + "\n");
      return true;
    } else {
      // else, we just render the board
      this.renderBoardHelper();
      this.renderMessageHelper("\nScore: " + (this.model.getScore()) + "\n");
      return false;
    }
  }

  // Determines if the given String is "q" or "Q" and quits the game.
  // Once the game is quit, it then outputs the correct messages, the state of the board at quit,
  // and the score.
  private boolean isQuitGame(String in) throws IllegalStateException {
    // double checks that it was a q that was inputted
    if (in.equalsIgnoreCase("q") && !(this.model.isGameOver())) {
      // and then runs the quit stuff
      try {
        this.view.renderMessage("Game quit!\nState of game when quit:\n");
        this.view.renderBoard();
        this.view.renderMessage("\nScore: " + this.model.getScore() + "\n");
      } catch (IOException io) {
        throw new IllegalStateException("Can't transmit view\n");
      }
      return true;
      // if it's anything else but q, then we say it's an invalid input
      // and continue
    } else {
      try {
        this.view.renderMessage("Invalid input " + in + " please re-enter values\n");
        return false;
      } catch (IOException io) {
        throw new IllegalStateException("Can't transmit view\n");
      }
    }
  }

  // Determines if the given String contains all integers.
  private boolean isInteger(String next) {
    try {
      Integer.parseInt(next);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  // Renders the supplied String as a message on the board.
  // However, throws an IllegalStateException if there is an IOException.
  private void renderMessageHelper(String message) throws IllegalStateException {
    try {
      this.view.renderMessage(message);
    } catch (IOException io) {
      throw new IllegalStateException("Cannot render message\n");
    }
  }

  // Renders the current state of the board.
  // However, throws an IllegalStateException if there is an IOException.
  private void renderBoardHelper() throws IllegalStateException {
    try {
      this.view.renderBoard();
    } catch (IOException io) {
      throw new IllegalStateException("Error loading view\n");
    }
  }
}




