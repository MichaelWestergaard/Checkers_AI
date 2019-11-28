package dk.michaelwestergaard;

import java.util.Arrays;

public class Move {

    int[] move;
    int score;

    public Move(int[] move, int score) {
        this.move = move;
        this.score = score;
    }

    public int[] getMove() {
        return move;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Move{" +
                "move=" + Arrays.toString(move) +
                ", score=" + score +
                '}';
    }
}
