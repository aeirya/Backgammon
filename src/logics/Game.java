package logics;

import java.util.List;

public class Game {
    public class GameState{
        //dice
        public int numberOnDie1;
        public int numberOnDie2;
        // turn and moves
        public List<Integer> validMoves;
        public Color turn;
        // state of checkers
        public List<Integer> numberOfCheckersOnColumn;
        public List<Color> colorOfCheckersOnColumn;
        public int numberOfBlackHitCheckers;
        public int numberOfWhiteHitCheckers;

    }
    public GameState getState(){
        //TODO
        return null;
    }
    public void setUp(){
        //TODO
    }
}
