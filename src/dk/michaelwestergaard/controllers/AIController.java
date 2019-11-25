package dk.michaelwestergaard.controllers;

import dk.michaelwestergaard.PieceType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AIController {

    private final int POINT_WIN = 1000000; //biggest number
    private final int POINT_NORMAL_PIECE = 100;
    private final int POINT_CROWNED_PIECE = 1000;

    private final int MAX_SEARCH_DEPTH = 3;

    public PieceType playerToMove;
    private PieceType aiType;
    private BoardController boardController;

    int[] bestMove;

    public AIController(PieceType aiType, BoardController boardController) {
        this.aiType = aiType;
        this.boardController = boardController;
    }

    public void makeMove(PieceType[][] board) {
        bestMove = null;
        alphaBeta(board, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);

        if (bestMove != null) {
            System.out.println("bestMove final");
            System.out.println("Start (" + bestMove[0] + "," + bestMove[1] + ") bestMove (" + bestMove[2] + "," + bestMove[3] + ")");
            int[] startPos = {bestMove[0], bestMove[1]};
            int[] endPos = {(bestMove[0] + bestMove[2]), (bestMove[1] + bestMove[3])};
            //System.out.println("End ("+ (bestMove[0]+bestMove[2])+","+ (bestMove[1]+bestMove[3])+")");


            PieceType type = board[startPos[0]][startPos[1]];

            if (Math.abs(startPos[0] - endPos[0]) == 2) {
                int x = startPos[0] + (bestMove[2] / 2);
                int y = startPos[1] + (bestMove[3] / 2);
                //System.out.println(x + ", " + y);
                board[x][y] = PieceType.EMPTY;
            }

            if (endPos[0] == 0 || endPos[0] == 7) {
                if (type.equals(PieceType.WHITE)) {
                    type = PieceType.CROWNED_WHITE;
                } else if (type.equals(PieceType.BLACK)) {
                    type = PieceType.CROWNED_BLACK;
                }
            }

            board[startPos[0]][startPos[1]] = PieceType.EMPTY;
            board[endPos[0]][endPos[1]] = type;
        }
    }

    private int alphaBeta(PieceType[][] board, int depth, int alpha, int beta) {
        //System.out.println("Alpha = " + alpha + " Beta =  " + beta + " Depth = " + depth);

        if (boardController.getWinner(board) != null || depth == MAX_SEARCH_DEPTH) {
            int evaluationVal = evaluation(board, playerToMove);


/*
            System.out.println("Returning value = " + evaluationVal + " Depth: " + depth);
            System.out.println(aiType);
            System.out.println(playerToMove);

 */

                return evaluationVal - depth;

        }

        List<int[]> legalMoves = getAllLegalMoves(board, playerToMove);
        //Collections.shuffle(legalMoves);
        if (playerToMove.equals(aiType)) { //Maximizer
            int i = 0;
            while ((alpha < beta) && i < legalMoves.size()) {
                PieceType[][] newBoard = makeAIMove(board, legalMoves.get(i));

                int value = alphaBeta(newBoard, depth + 1, alpha, beta);
                /*
                System.out.println("Maximizer");
                System.out.println("Value = " +value);
                boardController.showBoard(newBoard);
                 */

                if (value > alpha) {
                    alpha = value;
                    bestMove = legalMoves.get(i);
                    /*
                    System.out.println("Changing alpha");
                    System.out.println("new best move");
                    System.out.println("Start ("+ bestMove[0] + ","+bestMove[1]+ ") bestMove ("+bestMove[2]+","+bestMove[3]+")");
                    */
                }
                i++;
            }
        } else { //Minimizer
            int i = 0;
            while ((alpha < beta) && i < legalMoves.size()) {
                PieceType[][] newBoard = makeAIMove(board, legalMoves.get(i));

                int value = alphaBeta(newBoard, depth + 1, alpha, beta);

                value = -1* value;
/*
                System.out.println("Minimizer");
                System.out.println("Value = " +value);
                boardController.showBoard(newBoard);
*/
                if (value < beta) {
                    beta = value;

                    bestMove = legalMoves.get(i);
                    /*
                    System.out.println("Changing beta");
                    System.out.println("new best move");
                    System.out.println("Start ("+ bestMove[0] + ","+bestMove[1]+ ") bestMove ("+bestMove[2]+","+bestMove[3]+")");
                    */
                }
                i++;
            }
        }

        return 0;
    }

    PieceType[][] makeAIMove(PieceType[][] oldBoard, int[] move) {
        PieceType[][] board = new PieceType[8][8];

        for (int i = 0; i < oldBoard.length; i++) {
            for (int j = 0; j < oldBoard[i].length; j++) {
                board[i][j] = oldBoard[i][j];
            }
        }
        //System.out.println("Start ("+ move[0] + ","+move[1]+ ") move ("+move[2]+","+move[3]+")");
        int[] startPos = {move[0], move[1]};
        int[] endPos = {(move[0] + move[2]), (move[1] + move[3])};
        //System.out.println("End ("+ (move[0]+move[2])+","+ (move[1]+move[3])+")");

        if (Math.abs(startPos[0] - endPos[0]) == 2) {
            int x = startPos[0] + (move[2] / 2);
            int y = startPos[1] + (move[3] / 2);
            board[x][y] = PieceType.EMPTY;
        }

        PieceType type = board[startPos[0]][startPos[1]];
        board[startPos[0]][startPos[1]] = PieceType.EMPTY;

        if (endPos[0] == 0 || endPos[0] == 7) {
            //System.out.println("Crown piece!!!");
            if (type.equals(PieceType.WHITE)) {
                type = PieceType.CROWNED_WHITE;
            } else if (type.equals(PieceType.BLACK)) {
                type = PieceType.CROWNED_BLACK;
            }
        }

        board[endPos[0]][endPos[1]] = type;

        if (playerToMove.equals(PieceType.BLACK)) {
            playerToMove = PieceType.WHITE;
        } else {
            playerToMove = PieceType.BLACK;
        }

        return board;
    }

    private List<int[]> getAllLegalMoves(PieceType[][] board, PieceType type) {
        List<int[]> moves = new ArrayList<int[]>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (type.equals(board[i][j])) {
                    List<int[]> aiMoves = boardController.getLegalMoves(i, j, true);
                    if (aiMoves.size() > 0)
                        moves.addAll(aiMoves);
                }
            }
        }

        /*
        System.out.println(type);
        for(int[] move : moves){
            System.out.println("("+move[0] + ", " + move[1] +")");
        }
        */

        return moves;
    }


    public int evaluation(PieceType[][] board, PieceType player) {
        int value = 0;

        //TODO: Board positions
        //System.out.println(player);

        PieceType winner = boardController.getWinner(board);

        if (winner != null) {
            System.out.println("Winner found");
            System.out.println(" Winner is " + winner);

            if (winner.equals(player)) {
                value += POINT_WIN;
            } else {
                value -= POINT_WIN;
            }
        } else {
            value += valueOfPieces(board, player);
        }

        return value;
    }

    private int valueOfPieces(PieceType[][] board, PieceType player) {
        int whitePoints = 0, blackPoints = 0, result = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                PieceType piece = board[i][j];

                if (piece.equals(PieceType.BLACK)) {
                    blackPoints += POINT_NORMAL_PIECE;
                } else if (piece.equals(PieceType.CROWNED_BLACK)) {
                    blackPoints += POINT_CROWNED_PIECE;
                } else if (piece.equals(PieceType.WHITE)) {
                    whitePoints += POINT_NORMAL_PIECE;
                } else if (piece.equals(PieceType.CROWNED_WHITE)) {
                    whitePoints += POINT_CROWNED_PIECE;
                }

            }
        }

        result = (player.equals(PieceType.BLACK)) ? blackPoints - whitePoints : whitePoints - blackPoints;

        //boardController.showBoard(board);

        //System.out.println("Black: " + blackPoints + " white: " + whitePoints);
        //System.out.println(result);

        return result;
    }


}
