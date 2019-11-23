package dk.michaelwestergaard.controllers;

import dk.michaelwestergaard.PieceType;

import java.util.Scanner;

public class GameController {

    AIController aiController;
    BoardController boardController = new BoardController();
    Scanner scan = new Scanner(System.in);

    boolean gameInProgress, currentPlayer;
    PieceType playerType, aiType;
    String input, startPosition, endPosition;

    public void startGame(){
        boardController.resetBoard();
        chooseColor();
        playGame();
    }

    private void chooseColor(){
        System.out.println("Vælg farve: Sort (1) | Hvid (2)");
        int input;
        Scanner scanColor = new Scanner(System.in);
        boolean waitForInput = true;
        while(waitForInput){

            input = scanColor.nextInt();
            switch (input){
                case 1:
                    playerType = PieceType.BLACK;
                    aiType = PieceType.WHITE;
                    waitForInput = false;
                    currentPlayer = true;
                    System.out.println("Du spiller med de sorte brikker");
                    break;
                case 2:
                    playerType = PieceType.WHITE;
                    aiType = PieceType.BLACK;
                    currentPlayer = false;
                    System.out.println("Du spiller med de hvide brikker");
                    waitForInput = false;
                    break;
                default:
                    waitForInput = true;
                    System.out.println("Vælg venligst 1 eller 2!");
                    break;
            }

        }

        aiController = new AIController(aiType, boardController);
        gameInProgress = true;
    }

    private void playGame(){
        while(gameInProgress) {
            boardController.showBoard();
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
                aiController.playerToMove = aiType;
                aiController.makeMove(boardController.board);
                currentPlayer = true;
            }
        }
    }

}
