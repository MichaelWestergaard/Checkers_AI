package dk.michaelwestergaard.controllers;

import dk.michaelwestergaard.PieceType;

import java.util.List;
import java.util.Scanner;

public class GameController {

    AIController aiController;
    BoardController boardController = new BoardController();
    Scanner scan = new Scanner(System.in);

    boolean gameInProgress, currentPlayer;
    PieceType playerType, aiType;
    String input, startPosition, endPosition;

    AIController aiController1, aiController2;

    boolean botvsbot = false;

    public void startGame(){
        boardController.resetBoard();
        chooseColor();

        playGame();
    }

    private void chooseColor(){
        System.out.println("Vælg farve: Sort (1) | Hvid (2) | AI vs AI (3) | Load Game (4)");
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
                case 3:
                    playerType = PieceType.WHITE;
                    aiType = PieceType.BLACK;
                    botvsbot = true;
                    aiController1 = new AIController(aiType, playerType, boardController);
                    aiController2 = new AIController(playerType, aiType, boardController);
                    currentPlayer = false;
                    System.out.println("Du spiller med de hvide brikker");
                    waitForInput = false;
                    break;
                case 4:
                    System.out.println("Indsæt board (kun rækker 1-8)");
                    Scanner scannerBoard = new Scanner(System.in);
                    String[] gameRow = new String[8];

                    for (int i = 0; i < 8; i++) {
                        gameRow[i] = scannerBoard.nextLine();
                    }
                    scanColor = null;
                    loadGame(gameRow);

                    waitForInput = false;

                    break;
                default:
                    waitForInput = true;
                    System.out.println("Vælg venligst 1 eller 2!");
                    break;
            }

        }

        aiController = new AIController(aiType, playerType, boardController);
        gameInProgress = true;

        if(botvsbot){
            botvsbot();
        }
    }

    private void loadGame(String[] gameRow){
        PieceType[][] board = new PieceType[8][8];

        String[] test;
        for (int i = 0; i < gameRow.length; i++) {
            if(gameRow[i].substring(2,5).equals("   ")){
                board[i][0] = PieceType.EMPTY;
            } else if(gameRow[i].substring(2,5).equals(" W ")){
                board[i][0] = PieceType.WHITE;
            } else if(gameRow[i].substring(2,5).equals(" W*")){
                board[i][0] = PieceType.CROWNED_WHITE;
            } else if(gameRow[i].substring(2,5).equals(" B ")){
                board[i][0] = PieceType.BLACK;
            } else if(gameRow[i].substring(2,5).equals(" W*")){
                board[i][0] = PieceType.CROWNED_BLACK;
            }
            if(gameRow[i].substring(6,9).equals("   ")){
                board[i][1] = PieceType.EMPTY;
            } else if(gameRow[i].substring(6,9).equals(" W ")){
                board[i][1] = PieceType.WHITE;
            } else if(gameRow[i].substring(6,9).equals(" W*")){
                board[i][1] = PieceType.CROWNED_WHITE;
            } else if(gameRow[i].substring(6,9).equals(" B ")){
                board[i][1] = PieceType.BLACK;
            } else if(gameRow[i].substring(6,9).equals(" W*")){
                board[i][1] = PieceType.CROWNED_BLACK;
            }
            if(gameRow[i].substring(10,13).equals("   ")){
                board[i][2] = PieceType.EMPTY;
            } else if(gameRow[i].substring(10,13).equals(" W ")){
                board[i][2] = PieceType.WHITE;
            } else if(gameRow[i].substring(10,13).equals(" W*")){
                board[i][2] = PieceType.CROWNED_WHITE;
            } else if(gameRow[i].substring(10,13).equals(" B ")){
                board[i][2] = PieceType.BLACK;
            } else if(gameRow[i].substring(10,13).equals(" W*")){
                board[i][2] = PieceType.CROWNED_BLACK;
            }
            if(gameRow[i].substring(14,17).equals("   ")){
                board[i][3] = PieceType.EMPTY;
            } else if(gameRow[i].substring(14,17).equals(" W ")){
                board[i][3] = PieceType.WHITE;
            } else if(gameRow[i].substring(14,17).equals(" W*")){
                board[i][3] = PieceType.CROWNED_WHITE;
            } else if(gameRow[i].substring(14,17).equals(" B ")){
                board[i][3] = PieceType.BLACK;
            } else if(gameRow[i].substring(14,17).equals(" W*")){
                board[i][3] = PieceType.CROWNED_BLACK;
            }
            if(gameRow[i].substring(18,21).equals("   ")){
                board[i][4] = PieceType.EMPTY;
            } else if(gameRow[i].substring(18,21).equals(" W ")){
                board[i][4] = PieceType.WHITE;
            } else if(gameRow[i].substring(18,21).equals(" W*")){
                board[i][4] = PieceType.CROWNED_WHITE;
            } else if(gameRow[i].substring(18,21).equals(" B ")){
                board[i][4] = PieceType.BLACK;
            } else if(gameRow[i].substring(18,21).equals(" W*")){
                board[i][4] = PieceType.CROWNED_BLACK;
            }
            if(gameRow[i].substring(22,25).equals("   ")){
                board[i][5] = PieceType.EMPTY;
            } else if(gameRow[i].substring(22,25).equals(" W ")){
                board[i][5] = PieceType.WHITE;
            } else if(gameRow[i].substring(22,25).equals(" W*")){
                board[i][5] = PieceType.CROWNED_WHITE;
            } else if(gameRow[i].substring(22,25).equals(" B ")){
                board[i][5] = PieceType.BLACK;
            } else if(gameRow[i].substring(22,25).equals(" W*")){
                board[i][5] = PieceType.CROWNED_BLACK;
            }
            if(gameRow[i].substring(26,29).equals("   ")){
                board[i][6] = PieceType.EMPTY;
            } else if(gameRow[i].substring(26,29).equals(" W ")){
                board[i][6] = PieceType.WHITE;
            } else if(gameRow[i].substring(26,29).equals(" W*")){
                board[i][6] = PieceType.CROWNED_WHITE;
            } else if(gameRow[i].substring(26,29).equals(" B ")){
                board[i][6] = PieceType.BLACK;
            } else if(gameRow[i].substring(26,29).equals(" W*")){
                board[i][6] = PieceType.CROWNED_BLACK;
            }
            if(gameRow[i].substring(30,33).equals("   ")){
                board[i][7] = PieceType.EMPTY;
            } else if(gameRow[i].substring(30,33).equals(" W ")){
                board[i][7] = PieceType.WHITE;
            } else if(gameRow[i].substring(30,33).equals(" W*")){
                board[i][7] = PieceType.CROWNED_WHITE;
            } else if(gameRow[i].substring(30,33).equals(" B ")){
                board[i][7] = PieceType.BLACK;
            } else if(gameRow[i].substring(30,33).equals(" W*")){
                board[i][7] = PieceType.CROWNED_BLACK;
            }
        }

        boardController.board = board;
        boardController.showBoard();

        System.out.println("Vælg farve: Sort (1) | Hvid (2) | AI vs AI (3)");
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
                case 3:
                    playerType = PieceType.WHITE;
                    aiType = PieceType.BLACK;
                    botvsbot = true;
                    aiController1 = new AIController(aiType, playerType, boardController);
                    aiController2 = new AIController(playerType, aiType, boardController);
                    currentPlayer = false;
                    waitForInput = false;
                    break;
                default:
                    waitForInput = true;
                    System.out.println("Vælg venligst 1 eller 2!");
                    break;
            }

        }
    }

    private void botvsbot(){
        while(gameInProgress) {
            boardController.showBoard();

            if(boardController.getWinner() != null){
                System.out.println(boardController.getWinner() + " Vandt!");
                gameInProgress = false;
                break;
            }
            if(currentPlayer) { //Players turn to play
                System.out.println("AI1's tur!");
                aiController1.bestMove(boardController.board, false);
                currentPlayer = false;
            } else { //AI's turn to play
                System.out.println("AI2's tur!");
                aiController2.bestMove(boardController.board, false);
                currentPlayer = true;
            }
        }
    }

    private void playGame(){
        while(gameInProgress) {
            boardController.showBoard();

            if(boardController.getWinner() != null){
                System.out.println(boardController.getWinner() + " Vandt!");
                gameInProgress = false;
                break;
            }
            if(currentPlayer) { //Players turn to play
                System.out.println("Det er din tur til at spille! Lav et træk.");

                boolean waitForLegalMove = true;
                while(waitForLegalMove){

                    input = scan.nextLine();
                    startPosition = input.substring(0, 2);
                    endPosition = input.substring(3);

                    int[] startPos = boardController.getPostionFromString(startPosition),
                            endPos = boardController.getPostionFromString(endPosition);

                    if(boardController.move(startPos, endPos) == true) {
                        System.out.println("Træk accepteret");
                        waitForLegalMove = false;
                    } else {
                        System.out.println("Ulovligt træk! Prøv igen.");
                    }
                }

                currentPlayer = false;
            } else { //AI's turn to play
                System.out.println(gameInProgress);
                System.out.println("AI's tur!");
                aiController.bestMove(boardController.board, false);
                currentPlayer = true;
            }
        }
    }

}
