//// NOTE:::: This class was used to play the game // test inputs during the assignment.
////           It's not part of the deliverables, but I've decided to keep it for assignment 3.
//
// package cs3500.marblesolitaire;
//
//import java.io.InputStreamReader;
//
//import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
//import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
//import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
//import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
//import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
//import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
//import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
//import cs3500.marblesolitaire.view.MarbleSolitaireView;
//import cs3500.marblesolitaire.view.TriangleSolitaireTextView;
//
//public class PlayGame {
//  public static void main(String[] args){
//
////    EnglishSolitaireModel model = new EnglishSolitaireModel();
////    TriangleSolitaireModel model = new TriangleSolitaireModel();
//    EuropeanSolitaireModel model = new EuropeanSolitaireModel(6, 2);
////    for(int i = 0; i < model.getBoardSize(); i ++){
////      for(int j = 0; j < model.getBoardSize(); j++){
////        if (model.board.get(i).get(j) == MarbleSolitaireModelState.SlotState.Marble){
////          model.board.get(i).set(j, MarbleSolitaireModelState.SlotState.Empty);
////        }
////      }
////    }
////    model.board.get(3).set(3, MarbleSolitaireModelState.SlotState.Marble);
////    model.board.get(2).set(2, MarbleSolitaireModelState.SlotState.Marble);
////    MarbleSolitaireView view = new MarbleSolitaireTextView(model);
////    MarbleSolitaireView view = new TriangleSolitaireTextView(model);
//      MarbleSolitaireView view = new MarbleSolitaireTextView(model);
//    Readable read = new InputStreamReader(System.in);
//
//    new MarbleSolitaireControllerImpl(model, view, read).playGame();
//
//  }
//}
