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
    }

    private void playGame(){
        while(gameInProgress) {
            if(currentPlayer) { //Players turn to play
                System.out.println("Det er din tur til at spille! Lav et træk.");

                input = scan.nextLine();
                startPosition = input.substring(0, 1);
                endPosition = input.substring(3);

                boardController.move(startPosition, endPosition);
            } else { //AI's turn to play

            }
        }
    }

}
