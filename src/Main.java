import logic.Game;

import static logic.Color.black;

import java.util.ArrayList;

import gui.GameFrame;

public class Main {
    public static void main(String[] args) {
        GameFrame frame = GameFrame.getInstance();
        frame.initGame();


        frame.updateComponents(testState());
        frame.repaintFrame();


    }

    static Game.GameState testState(){
        Game.GameState state = new Game.GameState();
        state.numberOnDie1 = 3;
        state.numberOnDie2 = 6;
        state.validMoves = new ArrayList<>();
        state.numberOfCheckersOnColumn = new ArrayList<>();
        state.colorOfCheckersOnColumn = new ArrayList<>();
        state.numberOfBlackHitCheckers = 3;
        state.numberOfWhiteHitCheckers = 2;
        state.numberOfBlackBornOffCheckers = 4;
        state.numberOfWhiteBornOffCheckers = 5;

        for (int i = 0; i < 24; i++) {
            state.numberOfCheckersOnColumn.add(i % 4 + 2);
            state.colorOfCheckersOnColumn.add(black);
        }
        return state;
    }
}
