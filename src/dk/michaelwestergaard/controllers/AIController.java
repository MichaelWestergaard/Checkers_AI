package dk.michaelwestergaard.controllers;

import dk.michaelwestergaard.Move;
import dk.michaelwestergaard.PieceType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AIController {

    private PieceType aiType;
    private PieceType playerType;

    private BoardController boardController;

    private List<Move> bestMoves;

    private final int MAX_SEARCH_DEPTH = 6;

    private int[] lastAttackEndPosition = null;

    public AIController(PieceType aiType, PieceType playerType, BoardController boardController) {
        this.aiType = aiType;
        this.playerType = playerType;
        this.boardController = boardController;
    }

    public void bestMove(PieceType[][] board, boolean hasJustAttacked) {
        bestMoves = new ArrayList<Move>();
        if(!hasJustAttacked)
            lastAttackEndPosition = null;

        alphaBeta(board, 0, aiType, Integer.MIN_VALUE, Integer.MAX_VALUE);
        Collections.shuffle(bestMoves);

        int maxValue;
        int bestIndex = Integer.MIN_VALUE;
        boolean hasAttackMove = false;

        //Loop bestMoves and find the best score
        if(aiType.equals(PieceType.BLACK)){ //Maximizer
            maxValue = Integer.MIN_VALUE;

            for (int i = 0; i < bestMoves.size(); i++) {
                Move currentMove = bestMoves.get(i);
                System.out.println(currentMove);

                if(Math.abs(currentMove.getMove()[2]) == 2) {
                    if(hasJustAttacked){
                        if(currentMove.getMove()[0] != lastAttackEndPosition[0] && currentMove.getMove()[1] != lastAttackEndPosition[1]){
                            continue;
                        }
                    }

                    if(hasAttackMove){
                        if(maxValue < currentMove.getScore()){
                            maxValue = currentMove.getScore();
                            bestIndex = i;
                        }
                    } else {
                        hasAttackMove = true;
                        maxValue = currentMove.getScore();
                        bestIndex = i;
                    }
                } else { //Normal move
                    if(!hasAttackMove && !hasJustAttacked) {
                        if (maxValue < currentMove.getScore()) {
                            maxValue = currentMove.getScore();
                            bestIndex = i;
                        }
                    }
                }

            }

        } else { //Minimizer
            maxValue = Integer.MAX_VALUE;

            for (int i = 0; i < bestMoves.size(); i++) {
                Move currentMove = bestMoves.get(i);

                if(Math.abs(currentMove.getMove()[2]) == 2) {
                    if(hasJustAttacked){
                        if(currentMove.getMove()[0] != lastAttackEndPosition[0] && currentMove.getMove()[1] != lastAttackEndPosition[1]){
                            continue;
                        }
                    }

                    if(hasAttackMove){
                        if(maxValue > currentMove.getScore()){
                            maxValue = currentMove.getScore();
                            bestIndex = i;
                        }
                    } else {
                        hasAttackMove = true;
                        maxValue = currentMove.getScore();
                        bestIndex = i;
                    }
                } else { //Normal move
                    if(!hasAttackMove && !hasJustAttacked) {
                        if (maxValue > currentMove.getScore()) {
                            maxValue = currentMove.getScore();
                            bestIndex = i;
                        }
                    }
                }

            }

        }
        int[] endPos = new int[2];
        PieceType startType = null;
        //We found the best move
        if(bestIndex != Integer.MIN_VALUE) {
            Move bestMove = bestMoves.get(bestIndex);
            System.out.println("Best index: " + bestIndex + " Best Move: " + bestMove);
            startType = boardController.getType(board, bestMove.getMove()[0], bestMove.getMove()[1]);
            move(bestMove.getMove());
            if(hasAttackMove){
                endPos = new int[]{(bestMove.getMove()[0] + bestMove.getMove()[2]), (bestMove.getMove()[1] + bestMove.getMove()[3])};
                lastAttackEndPosition = endPos;
            }

        } else {
            hasAttackMove = false;
        }

        if(hasAttackMove) {
            if(endPos[0] == 0 || endPos[0] == 7){
                if(!startType.equals(PieceType.BLACK) && !startType.equals(PieceType.WHITE)){
                    bestMove(boardController.board, true);
                }
            } else {
                bestMove(boardController.board, true);
            }
        }
    }

    private void move(int[] move){
        if (move != null) {
            int[] startPos = {move[0], move[1]};
            int[] endPos = {(move[0] + move[2]), (move[1] + move[3])};

            PieceType type = boardController.board[startPos[0]][startPos[1]];

            //attack move
            if (Math.abs(startPos[0] - endPos[0]) == 2) {
                int x = startPos[0] + (move[2] / 2);
                int y = startPos[1] + (move[3] / 2);
                boardController.board[x][y] = PieceType.EMPTY;
            }

            if (endPos[0] == 0 || endPos[0] == 7) {
                if (type.equals(PieceType.WHITE)) {
                    type = PieceType.CROWNED_WHITE;
                } else if (type.equals(PieceType.BLACK)) {
                    type = PieceType.CROWNED_BLACK;
                }
            }

            boardController.board[startPos[0]][startPos[1]] = PieceType.EMPTY;
            boardController.board[endPos[0]][endPos[1]] = type;

        }
    }

    private int alphaBeta(PieceType[][] board, int depth, PieceType player, int alpha, int beta){

        //If max depth or game is ended return evaluation of the current board
        if(depth == MAX_SEARCH_DEPTH || boardController.getWinner(board) != null){
            if(boardController.getWinner(board) != null){
                if(boardController.getWinner(board).equals(PieceType.BLACK)){
                    if(aiType.equals(PieceType.BLACK)){
                        return 100000;
                    } else {
                        return -100000;
                    }
                } else if(boardController.getWinner(board).equals(PieceType.WHITE)) {
                    if(aiType.equals(PieceType.WHITE)){
                        return 100000;
                    } else {
                        return -100000;
                    }
                }
            }

            //No winner yet, evaluate the board instead
            return staticEvaluation(board);
        }

        //https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning#Pseudocode - alphabeta pseudocode
        if(player.equals(aiType)){ //Maximizer
            int bestValue = Integer.MIN_VALUE;

            List<int[]> legalMoves = getAllLegalMoves(board, player);
            for (int i = 0; i < legalMoves.size(); i++) {

                //Make move
                PieceType[][] newBoard = makeAIMove(board, legalMoves.get(i));

                int currentValue = alphaBeta(newBoard, depth+1, playerType, alpha, beta);

                //Find max value
                bestValue = Math.max(bestValue, currentValue);

                //alpha = (alpha >= bestValue) ? alpha : bestValue;
                alpha = Integer.max(alpha, currentValue);

                if(alpha >= beta) {
                    break;
                }

                //If depth 0 add move and score to bestmoves list for later use
                if(depth == 0){
                    bestMoves.add(new Move(legalMoves.get(i), bestValue));
                }
            }
            return bestValue;
        } else { //Minimizer
            int bestValue = Integer.MAX_VALUE;

            List<int[]> legalMoves = getAllLegalMoves(board, player);
            for (int i = 0; i < legalMoves.size(); i++) {

                //Make move
                PieceType[][] newBoard = makeAIMove(board, legalMoves.get(i));

                int currentValue = alphaBeta(newBoard, depth+1, aiType, alpha, beta);
                //Find min value
                bestValue = Integer.min(bestValue, currentValue);

                //beta = (beta <= currentValue) ? beta : currentValue;
                beta = Integer.min(beta, currentValue);

                if(alpha >= beta) {
                    break;
                }

                if(depth == 0){
                    bestMoves.add(new Move(legalMoves.get(i), bestValue));
                }
            }

            return bestValue;
        }
    }

    private int staticEvaluation(PieceType[][] board){
        int score = 0;

        int blackNormal = 0, blackCrowned = 0, whiteNormal = 0, whiteCrowned = 0;

        //Get amount of pieces on the board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                PieceType piece = board[i][j];

                if (piece.equals(PieceType.BLACK)) {
                    blackNormal++;
                } else if (piece.equals(PieceType.CROWNED_BLACK)) {
                    blackCrowned++;
                } else if (piece.equals(PieceType.WHITE)) {
                    whiteNormal++;
                } else if (piece.equals(PieceType.CROWNED_WHITE)) {
                    whiteCrowned++;
                }

            }
        }

        //300 points for pieces that have been killed
        if(aiType.equals(PieceType.BLACK)){
            score += Math.abs((whiteNormal+whiteCrowned)-12)*300;
            score -= Math.abs((blackNormal+blackCrowned)-12)*300;
        } else {
            score += Math.abs((blackNormal+blackCrowned)-12)*300;
            score -= Math.abs((whiteNormal+whiteCrowned)-12)*300;
        }

        //500 point for crowned pieces
        if(aiType.equals(PieceType.BLACK)){
            score += blackCrowned*500;
            score -= whiteCrowned*500;
        } else {
            score += whiteCrowned*500;
            score -= blackCrowned*500;
        }

        //300 point for pieces that can be attacked next turn
        /*
        if(aiType.equals(PieceType.BLACK)){
            score += boardController.amountOfPiecesThatCanBeAttacked(board, PieceType.WHITE)*300;
            score -= boardController.amountOfPiecesThatCanBeAttacked(board, PieceType.BLACK)*300;
        } else {
            score += boardController.amountOfPiecesThatCanBeAttacked(board, PieceType.BLACK)*300;
            score -= boardController.amountOfPiecesThatCanBeAttacked(board, PieceType.WHITE)*300;
        }
        */


        //Get higher points the closer the pieces is to getting upgraded to crowned (only if there is nothing to do)

        return score;
    }

    private List<int[]> getAllLegalMoves(PieceType[][] board, PieceType type) {
        List<int[]> moves = new ArrayList<int[]>();
        List<PieceType> types = new ArrayList<PieceType>();
        if (type.equals(PieceType.BLACK)) {
            types.add(PieceType.CROWNED_BLACK);
            types.add(PieceType.BLACK);
        } else {
            types.add(PieceType.WHITE);
            types.add(PieceType.CROWNED_WHITE);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (types.contains(board[i][j])) {
                    List<int[]> aiMoves = boardController.getLegalMoves(board, i, j, true);
                    if (aiMoves.size() > 0)
                        moves.addAll(aiMoves);
                }
            }
        }
        return moves;
    }

    PieceType[][] makeAIMove(PieceType[][] oldBoard, int[] move) {
        PieceType[][] board = new PieceType[8][8];

        for (int i = 0; i < oldBoard.length; i++) {
            for (int j = 0; j < oldBoard[i].length; j++) {
                board[i][j] = oldBoard[i][j];
            }
        }
        int[] startPos = {move[0], move[1]};
        int[] endPos = {(move[0] + move[2]), (move[1] + move[3])};

        if (Math.abs(startPos[0] - endPos[0]) == 2) {
            int x = startPos[0] + (move[2] / 2);
            int y = startPos[1] + (move[3] / 2);
            board[x][y] = PieceType.EMPTY;
        }

        PieceType type = board[startPos[0]][startPos[1]];

        board[startPos[0]][startPos[1]] = PieceType.EMPTY;

        if (endPos[0] == 0 || endPos[0] == 7) {
            if (type.equals(PieceType.WHITE)) {
                type = PieceType.CROWNED_WHITE;
            } else if (type.equals(PieceType.BLACK)) {
                type = PieceType.CROWNED_BLACK;
            }
        }

        board[endPos[0]][endPos[1]] = type;

        return board;
    }
}
