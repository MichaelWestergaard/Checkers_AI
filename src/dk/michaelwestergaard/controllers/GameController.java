package dk.michaelwestergaard.controllers;

import java.util.Scanner;

public class GameController {

    AIController aiController;
    BoardController boardController = new BoardController();
    Scanner scan = new Scanner(System.in);

    boolean gameInProgress, currentPlayer;
    String input, startPosition, endPosition;

    public void startGame(){
        boardController.resetBoard();
        boardController.showBoard();

        gameInProgress = true;
        currentPlayer = true;
        playGame();
    }

    private void playGame(){
        while(gameInProgress) {
            if(currentPlayer) { //Players turn to play
                System.out.println("Det er din tur til at spille! Lav et træk.");

                boolean waitForLegalMove = true;
                while(waitForLegalMove){

                    input = scan.nextLine();
                    startPosition = input.substring(0, 2);
                    endPosition = input.substring(3);

                    if(boardController.move(startPosition, endPosition) == true) {
                        System.out.println("Træk accepteret");
                        waitForLegalMove = false;
                    } else {
                        System.out.println("Ulovligt træk! Prøv igen.");
                    }
                }

                currentPlayer = false;
            } else { //AI's turn to play
                System.out.println("AI's tur!");
                currentPlayer = true;
            }
        }
    }

}
