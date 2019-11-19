package dk.michaelwestergaard.controllers;

import dk.michaelwestergaard.PieceType;
import org.omg.Messaging.SyncScopeHelper;

import java.util.ArrayList;
import java.util.List;

public class BoardController {

    PieceType[][] board;

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
        int whiteLeft = 0, blackLeft = 0;
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

    void move(int startX, int startY, int endX, int endY){
        PieceType type = getType(startX, startY);
        board[startX][startY] = PieceType.EMPTY;
        board[endX][endY] = type;
    }

    List<int[]> getLegalMoves(int x, int y){
        ArrayList<int[]> moves = new ArrayList<int[]>();

        PieceType piece = getType(x,y);

        if(piece.equals(PieceType.WHITE) || piece.equals(PieceType.CROWNED_WHITE)){
            if(getType(x+1,y+1).equals(PieceType.EMPTY)){
                moves.add(new int[]{1, 1});
            } else if(getType(x+1,y-1).equals(PieceType.EMPTY)){
                moves.add(new int[]{1, -1});
            }
            if(piece.equals(PieceType.CROWNED_WHITE)){
                if(getType(x-1,y+1).equals(PieceType.EMPTY)){
                    moves.add(new int[]{-1, 1});
                } else if(getType(x-1,y-1).equals(PieceType.EMPTY)){
                    moves.add(new int[]{-1, -1});
                }
            }
        } else if(piece.equals(PieceType.BLACK) || piece.equals(PieceType.CROWNED_BLACK)){
            if(getType(x+1,y+1).equals(PieceType.EMPTY)){
                moves.add(new int[]{1, 1});
            } else if(getType(x+1,y-1).equals(PieceType.EMPTY)){
                moves.add(new int[]{1, -1});
            }
            if(piece.equals(PieceType.CROWNED_BLACK)){
                if(getType(x-1,y+1).equals(PieceType.EMPTY)){
                    moves.add(new int[]{-1, 1});
                } else if(getType(x-1,y-1).equals(PieceType.EMPTY)){
                    moves.add(new int[]{-1, -1});
                }
            }
        }

        return moves;
    }

    PieceType getType(int x, int y){
        if(x < 0 || x > board.length || y < 0 || y > board[x].length)
            return null;

        return board[x][y];
    }

}