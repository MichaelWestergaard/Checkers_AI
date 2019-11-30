package dk.michaelwestergaard.controllers;

import dk.michaelwestergaard.PieceType;
import org.omg.Messaging.SyncScopeHelper;

import java.util.ArrayList;
import java.util.Arrays;
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

    void testBoard1(){
        board = new PieceType[][]{
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.WHITE, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.BLACK, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.BLACK, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
        };
    }

    void testBoard2(){
        board = new PieceType[][]{
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.BLACK, PieceType.WHITE, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.BLACK, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
        };
    }

    void testBoard3(){
        board = new PieceType[][]{
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.WHITE, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.BLACK, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
        };
    }

    void testBoard4(){
        board = new PieceType[][]{
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.WHITE, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.WHITE, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.BLACK, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
        };
    }

    void showBoard(){
        whiteLeft = 0;
        blackLeft = 0;
        System.out.println(" | A | B | C | D | E | F | G | H |");
        System.out.println("------------------------------------");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i+1);
            for (int j = 0; j < board[i].length; j++) {
                System.out.print("|");
                switch (board[i][j]){
                    case EMPTY:
                        System.out.print("   ");
                        break;
                    case WHITE:
                        System.out.print(" W ");
                        whiteLeft++;
                        break;
                    case CROWNED_WHITE:
                        System.out.print(" W*");
                        whiteLeft++;
                        break;
                    case BLACK:
                        System.out.print(" B ");
                        blackLeft++;
                        break;
                    case CROWNED_BLACK:
                        System.out.print(" B*");
                        blackLeft++;
                        break;
                    default:
                        System.out.print(" _ ");
                        break;
                }
            }
            System.out.println("|");
        }
        System.out.println("White pieces left: " + whiteLeft + " | Black pieces left: " + blackLeft);
    }

    boolean move(int[] start, int[] end){

        int[] startPos = start,
                endPos = end;

        if(startPos.length == 2 && endPos.length == 2){

            //If legalmove
            List<int[]> moves = getLegalMoves(startPos[0], startPos[1], false);
            boolean moved = false, attacked = false;

            for(int[] move : moves){
                if((startPos[0]+move[0]) == endPos[0] && (startPos[1]+move[1]) == endPos[1]){

                    if(Math.abs(startPos[0]-endPos[0]) == 2){
                        int x = startPos[0]+(move[0]/2);
                        int y = startPos[1]+(move[1]/2);
                        System.out.println("Jumped over: X " + x + " y " + y);
                        board[x][y] = PieceType.EMPTY;
                        attacked = true;
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

        if(endX == 0 || endX == 7){
            System.out.println("Make crowned");
            if(type.equals(PieceType.WHITE)){
                type = PieceType.CROWNED_WHITE;
            } else if(type.equals(PieceType.BLACK)){
                type = PieceType.CROWNED_BLACK;
            }
        }

        board[endX][endY] = type;
    }

    List<int[]> getLegalMoves(int x, int y, boolean AI){
        ArrayList<int[]> moves = new ArrayList<int[]>();

        PieceType piece = getType(x,y);

        //System.out.println(piece);

        if(piece.equals(PieceType.WHITE) || piece.equals(PieceType.CROWNED_WHITE)){
            //Attack moves
            if(canPieceAttack(piece, new int[]{x,y}, new int[]{x+1,y-1}, new int[]{x+2,y-2})){
                //System.out.println("can attack " + (x+1) + ", " + (y-1));
                if(AI){
                    moves.add(new int[]{x, y, 2, -2});
                    List<int[]> temp = new ArrayList<int[]>();
                    temp.add(new int[]{x, y, 2, -2});
                    return temp;
                } else {
                    moves.add(new int[]{2,-2});
                }
            }
            if(canPieceAttack(piece, new int[]{x,y}, new int[]{x+1,y+1}, new int[]{x+2,y+2})){
                //System.out.println("can attack " + (x+1) + ", " + (y+1));
                if(AI){
                    moves.add(new int[]{x, y, 2, 2});

                    List<int[]> temp = new ArrayList<int[]>();
                    temp.add(new int[]{x, y, 2, 2});
                    return temp;
                } else {
                    moves.add(new int[]{2,2});
                }
            }

            //Normal move
            if(PieceType.EMPTY.equals(getType(x+1,y+1)) && getType(x+1,y+1) != null){
                if(AI){
                    moves.add(new int[]{x, y, 1, 1});
                } else{
                    moves.add(new int[]{1, 1});
                }
            }
            if(PieceType.EMPTY.equals(getType(x+1,y-1)) && getType(x+1,y-1) != null){
                if(AI){
                    moves.add(new int[]{x, y, 1, -1});
                } else{
                    moves.add(new int[]{1, -1});
                }
            }

            //Crowned white moves (same as normal black)
            if(piece.equals(PieceType.CROWNED_WHITE)){
                //Attack moves
                if(canPieceAttack(piece, new int[]{x,y}, new int[]{x-1,y-1}, new int[]{x-2,y-2})){
                    //System.out.println("can attack " + (x-1) + ", " + (y-1));
                    if(AI){
                        moves.add(new int[]{x, y, -2, -2});

                        List<int[]> temp = new ArrayList<int[]>();
                        temp.add(new int[]{x, y, -2, -2});
                        return temp;
                    } else{
                        moves.add(new int[]{-2,-2});
                    }
                }
                if(canPieceAttack(piece, new int[]{x,y}, new int[]{x-1,y+1}, new int[]{x-2,y+2})){
                    //System.out.println("can attack " + (x-1) + ", " + (y+1));
                    if(AI){
                        moves.add(new int[]{x, y, -2, 2});
                        List<int[]> temp = new ArrayList<int[]>();
                        temp.add(new int[]{x, y, -2, 2});
                        return temp;
                    } else{
                        moves.add(new int[]{-2,2});
                    }
                }

                //Normal move
                if(PieceType.EMPTY.equals(getType(x-1,y-1)) && getType(x-1,y-1) != null){
                    if(AI){
                        moves.add(new int[]{x, y, -1, -1});
                    } else{
                        moves.add(new int[]{-1, -1});
                    }
                }
                if(PieceType.EMPTY.equals(getType(x-1,y+1)) && getType(x-1,y+1) != null){
                    if(AI){
                        moves.add(new int[]{x, y, -1, 1});
                    } else{
                        moves.add(new int[]{-1, 1});
                    }
                }
            }

        } else if(piece.equals(PieceType.BLACK) || piece.equals(PieceType.CROWNED_BLACK)){
            //Attack moves

            if(canPieceAttack(piece, new int[]{x,y}, new int[]{x-1,y-1}, new int[]{x-2,y-2})){
                //System.out.println("can attack " + (x-1) + ", " + (y-1));
                if(AI){
                    moves.add(new int[]{x, y, -2, -2});

                    List<int[]> temp = new ArrayList<int[]>();
                    temp.add(new int[]{x, y, -2, -2});
                    return temp;
                } else{
                    moves.add(new int[]{-2,-2});
                }
            }

            if(canPieceAttack(piece, new int[]{x,y}, new int[]{x-1,y+1}, new int[]{x-2,y+2})){
                //System.out.println("can attack " + (x-1) + ", " + (y+1));
                if(AI){
                    moves.add(new int[]{x, y, -2, 2});

                    List<int[]> temp = new ArrayList<int[]>();
                    temp.add(new int[]{x, y, -2, 2});
                    return temp;
                } else{
                    moves.add(new int[]{-2,2});
                }
            }

            //Normal move
            if(PieceType.EMPTY.equals(getType(x-1,y-1)) && getType(x-1,y-1) != null){
                if(AI){
                    moves.add(new int[]{x, y, -1, -1});
                } else{
                    moves.add(new int[]{-1, -1});
                }
            }
            if(PieceType.EMPTY.equals(getType(x-1,y+1)) && getType(x-1,y+1) != null){
                if(AI){
                    moves.add(new int[]{x, y, -1, 1});
                } else{
                    moves.add(new int[]{-1, 1});
                }
            }

            if(piece.equals(PieceType.CROWNED_BLACK)){
                //Attack moves
                if(canPieceAttack(piece, new int[]{x,y}, new int[]{x+1,y-1}, new int[]{x+2,y-2})){
                    //System.out.println("can attack " + (x+1) + ", " + (y-1));
                    if(AI){
                        moves.add(new int[]{x, y, 2, -2});

                        List<int[]> temp = new ArrayList<int[]>();
                        temp.add(new int[]{x, y, 2, -2});
                        return temp;
                    } else {
                        moves.add(new int[]{2,-2});
                    }
                }

                if(canPieceAttack(piece, new int[]{x,y}, new int[]{x+1,y+1}, new int[]{x+2,y+2})){
                    //System.out.println("can attack " + (x+1) + ", " + (y+1));
                    if(AI){
                        moves.add(new int[]{x, y, 2, 2});

                        List<int[]> temp = new ArrayList<int[]>();
                        temp.add(new int[]{x, y, 2, 2});
                        return temp;
                    } else {
                        moves.add(new int[]{2,2});
                    }
                }

                //Normal move
                if(PieceType.EMPTY.equals(getType(x+1,y+1)) && getType(x+1,y+1) != null){
                    if(AI){
                        moves.add(new int[]{x, y, 1, 1});
                    } else{
                        moves.add(new int[]{1, 1});
                    }
                }
                if(PieceType.EMPTY.equals(getType(x+1,y-1)) && getType(x+1,y-1) != null){
                    if(AI){
                        moves.add(new int[]{x, y, 1, -1});
                    } else{
                        moves.add(new int[]{1, -1});
                    }
                }
            }
        }

        return moves;
    }

    boolean canPieceAttack(PieceType type, int[] start, int[] opponent, int[] end){
        List<PieceType> oppositeColors = null;

        switch (type){
            case WHITE:
            case CROWNED_WHITE:
                oppositeColors = Arrays.asList(PieceType.BLACK, PieceType.CROWNED_BLACK);
                break;
            case BLACK:
            case CROWNED_BLACK:
                oppositeColors = Arrays.asList(PieceType.WHITE, PieceType.CROWNED_WHITE);
                break;
        }

        if(PieceType.EMPTY.equals(getType(end[0], end[1]))){
            if(oppositeColors != null){
                if(oppositeColors.contains(getType(opponent[0], opponent[1]))){
                    return true;
                }
            }
        }

        return false;
    }

    PieceType getType(int x, int y){

        if((x < 0 || x > 7) || (y < 0 || y > 7)) {
            return null;
        }

        return board[x][y];
    }

    public PieceType getWinner(PieceType[][] gameBoard){
        int whiteLeft = 0;
        int blackLeft = 0;
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                switch (gameBoard[i][j]){
                    case WHITE:
                        whiteLeft++;
                        break;
                    case CROWNED_WHITE:
                        whiteLeft++;
                        break;

                    case BLACK:
                        blackLeft++;
                        break;
                    case CROWNED_BLACK:
                        blackLeft++;
                        break;
                }
            }
        }

        //System.out.println("W: " + whiteLeft + " b: " + blackLeft);

        if(whiteLeft == 0){
            return PieceType.BLACK;
        } else if(blackLeft == 0){
            return PieceType.WHITE;
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
            return  PieceType.BLACK;
        } else if(blackLeft == 0){
            return PieceType.WHITE;
        } else {
            return null;
        }
    }

}