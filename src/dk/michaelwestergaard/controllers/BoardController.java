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
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.WHITE, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.WHITE, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.WHITE, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.WHITE, PieceType.EMPTY},
                {PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.EMPTY, PieceType.BLACK},
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
            List<int[]> moves = getLegalMoves(board, startPos[0], startPos[1], false);
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

            if(attacked){
                System.out.println("attacked");
                multipleMove(endPos[0], endPos[1]);
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

    private void multipleMove(int startX, int startY){
        List<int[]> moves = getLegalMoves(board, startX, startY, false);
        boolean attacked = false;

        int[] endPos = new int[2];
        System.out.println("start " + startX + ", " + startY);


        for(int[] move : moves){
            System.out.println("move loop: " + move[0] + ", " + move[1]);
            if(Math.abs(move[0]) == 2){
                int x = startX+(move[0]/2);
                int y = startY+(move[1]/2);
                board[x][y] = PieceType.EMPTY;
                attacked = true;
                endPos[0] = startX+move[0];
                endPos[1] = startY+move[1];

                movePosition(startX, startY, endPos[0], endPos[1]);
                break;
            }
        }

        if(attacked){
            multipleMove(endPos[0], endPos[1]);
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

    List<int[]> getLegalMoves(PieceType[][] gameBoard, int x, int y, boolean AI){
        ArrayList<int[]> moves = new ArrayList<int[]>();

        System.out.println("GetLegalMoves XY: " + x + ", " + y);


        PieceType piece = getType(gameBoard, x, y);

        System.out.println(piece);

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

    PieceType getType(PieceType[][] board, int x, int y){

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
        System.out.println(" | A | B | C | D | E | F | G | H |");
        System.out.println("------------------------------------");
        for (int i = 0; i < gameBoard.length; i++) {
            System.out.print(i+1);
            for (int j = 0; j < gameBoard[i].length; j++) {
                System.out.print("|");
                switch (gameBoard[i][j]){
                    case EMPTY:
                        System.out.print("   ");
                        break;
                    case WHITE:
                        System.out.print(" W ");
                        break;
                    case CROWNED_WHITE:
                        System.out.print(" W*");
                        break;
                    case BLACK:
                        System.out.print(" B ");
                        break;
                    case CROWNED_BLACK:
                        System.out.print(" B*");
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

    public PieceType getWinner(){
        if(whiteLeft == 0){
            return  PieceType.BLACK;
        } else if(blackLeft == 0){
            return PieceType.WHITE;
        } else {
            return null;
        }
    }

    int amountOfPiecesThatCanBeAttacked(PieceType[][] board, PieceType type){
        int result = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(!board[i][j].equals(PieceType.EMPTY)){
                    if(canBeAttacked(board, type, new int[] {i,j}))
                        result++;
                }
            }
        }

        return result;
    }

    boolean canBeAttacked(PieceType[][] board, PieceType type, int[] piece){
        List<PieceType> oppositeColors = null;
        List<PieceType> myColors = null;

        switch (type){
            case WHITE:
            case CROWNED_WHITE:
                oppositeColors = Arrays.asList(PieceType.BLACK, PieceType.CROWNED_BLACK);
                myColors = Arrays.asList(PieceType.WHITE, PieceType.CROWNED_WHITE);
                break;
            case BLACK:
            case CROWNED_BLACK:
                oppositeColors = Arrays.asList(PieceType.WHITE, PieceType.CROWNED_WHITE);
                myColors = Arrays.asList(PieceType.BLACK, PieceType.CROWNED_BLACK);
                break;
        }

        int[][] possibleDirections = new int[][] {{-1,-1}, {-1,1}, {1,-1}, {1,1}};
        int[][] directionAttacks = new int[][] {{1,1}, {1,-1}, {-1,1}, {-1,-1}};

        int i = 0;
        if(myColors.contains(getType(piece[0], piece[1])))
            for(int[] direction : possibleDirections){
                if(oppositeColors.contains(getType(board,piece[0]+direction[0],piece[1]+direction[1]))){
                    if(PieceType.EMPTY.equals(getType(board,piece[0]+directionAttacks[i][0],piece[1]+directionAttacks[i][1]))){
                        return true;
                    }
                }
                i++;
            }
        return false;
    }

}