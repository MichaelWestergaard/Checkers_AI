package dk.michaelwestergaard.controllers;

import dk.michaelwestergaard.PieceType;
import org.omg.Messaging.SyncScopeHelper;

import java.util.ArrayList;
import java.util.List;

public class BoardController {

    PieceType[][] board;
    int whiteLeft = 0, blackLeft = 0;

    void resetBoard(){
        //https://thumbs.dreamstime.com/z/checkers-board-chess-board-black-white-checkers-game-76944480.jpg
        board = new PieceType[][]{
                {PieceType.EMPTY, PieceType.WHITE, PieceType.EMPTY, PieceType.WHITE, PieceType.EMPTY, PieceType.WHITE, PieceType.EMPTY, PieceType.WHITE},
                {PieceType.WHITE, PieceType.EMPTY, PieceType.WHITE, PieceType.EMPTY, PieceType.WHITE, PieceType.EMPTY, PieceType.WHITE, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.WHITE, PieceType.EMPTY, PieceType.WHITE, PieceType.EMPTY, PieceType.WHITE, PieceType.EMPTY, PieceType.WHITE},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.BLACK, PieceType.EMPTY, PieceType.BLACK, PieceType.EMPTY, PieceType.BLACK, PieceType.EMPTY, PieceType.BLACK, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.BLACK, PieceType.EMPTY, PieceType.BLACK, PieceType.EMPTY, PieceType.BLACK, PieceType.EMPTY, PieceType.BLACK},
                {PieceType.BLACK, PieceType.EMPTY, PieceType.BLACK, PieceType.EMPTY, PieceType.BLACK, PieceType.EMPTY, PieceType.BLACK, PieceType.EMPTY},
        };
    }

    void showBoard(){
        whiteLeft = 0;
        blackLeft = 0;
        System.out.println(" |A|B|C|D|E|F|G|H|");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i+1);
            for (int j = 0; j < board[i].length; j++) {
                System.out.print("|");
                switch (board[i][j]){
                    case EMPTY:
                        System.out.print(" ");
                        break;
                    case WHITE:
                        System.out.print("W");
                        whiteLeft++;
                        break;
                    case BLACK:
                        System.out.print("B");
                        blackLeft++;
                        break;
                    default:
                        System.out.print("C");
                        break;
                }
            }
            System.out.println("|");
        }
        System.out.println("White pieces left: " + whiteLeft + " | Black pieces left: " + blackLeft);
    }

    boolean move(String start, String end){

        int[] startPos = getPostionFromString(start),
                endPos = getPostionFromString(end);

        if(startPos.length == 2 && endPos.length == 2){

            //If legalmove
            List<int[]> moves = getLegalMoves(startPos[0], startPos[1], false);
            boolean moved = false;

            for(int[] move : moves){
                if((startPos[0]+move[0]) == endPos[0] && (startPos[1]+move[1]) == endPos[1]){

                    if(Math.abs(startPos[0]-endPos[0]) == 2){
                        int x = startPos[0]+(move[0]/2);
                        int y = startPos[1]+(move[1]/2);
                        System.out.println("Jumped over: X " + x + " y " + y);
                        board[x][y] = PieceType.EMPTY;
                    }
                    movePosition(startPos[0], startPos[1], endPos[0], endPos[1]);
                    moved = true;
                    break;
                }
            }

            if(moved)
                return true;
            else
                return false;


        } else {
            //Error
            return false;
        }
    }

    int[] getPostionFromString(String position){
        char[] positionArray = position.toLowerCase().toCharArray();

        int[] result = new int[2];

        switch(positionArray[0]){
            case 'a':
                result[1] = 0;
                break;
            case 'b':
                result[1] = 1;
                break;
            case 'c':
                result[1] = 2;
                break;
            case 'd':
                result[1] = 3;
                break;
            case 'e':
                result[1] = 4;
                break;
            case 'f':
                result[1] = 5;
                break;
            case 'g':
                result[1] = 6;
                break;
            case 'h':
                result[1] = 7;
                break;

            default:
                result[1] = -1;
                break;
        }

        result[0] = Character.getNumericValue(positionArray[1]) - 1;

        return result;
    }

    void movePosition(int startX, int startY, int endX, int endY){
        PieceType type = getType(startX, startY);
        board[startX][startY] = PieceType.EMPTY;
        board[endX][endY] = type;
    }

    List<int[]> getLegalMoves(int x, int y, boolean AI){
        ArrayList<int[]> moves = new ArrayList<int[]>();

        PieceType piece = getType(x,y);


        //TODO: crowned movements
        if(piece.equals(PieceType.WHITE) || piece.equals(PieceType.CROWNED_WHITE)){
            //Attack moves
            if(PieceType.BLACK.equals(getType(x+1,y-1)) && PieceType.EMPTY.equals(getType(x+2,y-2))){
                //System.out.println("can attack " + (x+1) + ", " + (y-1));
                if(AI){
                    moves.add(new int[]{x, y, 2, -2});
                } else {
                    moves.add(new int[]{2,-2});
                }
            }
            if(PieceType.BLACK.equals(getType(x+1,y+1)) && PieceType.EMPTY.equals(getType(x+2,y+2))){
                //System.out.println("can attack " + (x+1) + ", " + (y+1));
                if(AI){
                    moves.add(new int[]{x, y, 2, 2});
                } else {
                    moves.add(new int[]{2,2});
                }
            }

            //Normal move
            if(PieceType.EMPTY.equals(getType(x+1,y+1))){
                if(AI){
                    moves.add(new int[]{x, y, 1, 1});
                } else{
                    moves.add(new int[]{1, 1});
                }
            }
            if(PieceType.EMPTY.equals(getType(x+1,y-1))){
                if(AI){
                    moves.add(new int[]{x, y, 1, -1});
                } else{
                    moves.add(new int[]{1, -1});
                }
            }
        } else if(piece.equals(PieceType.BLACK) || piece.equals(PieceType.CROWNED_BLACK)){
            //Attack moves
            if(PieceType.WHITE.equals(getType(x-1,y-1)) && PieceType.EMPTY.equals(getType(x-2,y-2))){
                //System.out.println("can attack " + (x-1) + ", " + (y-1));
                if(AI){
                    moves.add(new int[]{x, y, -2, -2});
                } else{
                    moves.add(new int[]{-2,-2});
                }
            }
            if(PieceType.WHITE.equals(getType(x-1,y+1)) && PieceType.EMPTY.equals(getType(x-2,y+2))){
                //System.out.println("can attack " + (x-1) + ", " + (y+1));
                if(AI){
                    moves.add(new int[]{x, y, -2, 2});
                } else{
                    moves.add(new int[]{-2,2});
                }
            }

            //Normal move
            if(PieceType.EMPTY.equals(getType(x-1,y-1))){
                if(AI){
                    moves.add(new int[]{x, y, -1, -1});
                } else{
                    moves.add(new int[]{-1, -1});
                }
            }
            if(PieceType.EMPTY.equals(getType(x-1,y+1))){
                if(AI){
                    moves.add(new int[]{x, y, -1, 1});
                } else{
                    moves.add(new int[]{-1, 1});
                }
            }
        }

        return moves;
    }

    PieceType getType(int x, int y){

        if((x < 0 || x >= board.length) || (y < 0 || y >= board[x].length)) {
            return null;
        }

        return board[x][y];
    }

    public PieceType getWinner(PieceType[][] gameBoard){
        whiteLeft = 0;
        blackLeft = 0;
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                switch (gameBoard[i][j]){
                    case WHITE:
                        whiteLeft++;
                        break;
                    case BLACK:
                        blackLeft++;
                        break;
                }
            }
        }
        if(whiteLeft == 0){
            return  PieceType.WHITE;
        } else if(blackLeft == 0){
            return PieceType.BLACK;
        } else {
            return null;
        }
    }

    void showBoard(PieceType[][] gameBoard){
        System.out.println(" |A|B|C|D|E|F|G|H|");
        for (int i = 0; i < gameBoard.length; i++) {
            System.out.print(i+1);
            for (int j = 0; j < gameBoard[i].length; j++) {
                System.out.print("|");
                switch (gameBoard[i][j]){
                    case EMPTY:
                        System.out.print(" ");
                        break;
                    case WHITE:
                        System.out.print("W");
                        whiteLeft++;
                        break;
                    case BLACK:
                        System.out.print("B");
                        blackLeft++;
                        break;
                    default:
                        System.out.print("C");
                        break;
                }
            }
            System.out.println("|");
        }
    }

    public PieceType getWinner(){
        if(whiteLeft == 0){
            return  PieceType.WHITE;
        } else if(blackLeft == 0){
            return PieceType.BLACK;
        } else {
            return null;
        }
    }

}