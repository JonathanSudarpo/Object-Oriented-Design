package cs3500.marblesolitaire.controller;

/**
 * This interface represents operations that should be offered by
 * a controller for the Marble Solitaire game.
 */
public interface MarbleSolitaireController {

  /**
   * Plays / instantiates a new game of Marble Solitaire.
   */
  void playGame() throws IllegalStateException;

}
