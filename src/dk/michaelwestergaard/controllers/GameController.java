package dk.michaelwestergaard.controllers;

public class GameController {

    AIController aiController;
    BoardController boardController = new BoardController();

    public void startGame(){
        boardController.resetBoard();
        boardController.showBoard();
    }

    private void playGame(){

    }

}
