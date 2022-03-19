package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    public static class GameState{
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
        public int numberOfWhiteBornOffCheckers;
        public int numberOfBlackBornOffCheckers;

    }

    private List<Column> points;
    private Column whiteCapturedCheckers;
    private Column blackCapturedCheckers;
    private Column whiteBornOffCheckers;
    private Column blackBornOffCheckers;

    public static final List<Integer> NUMBER_OF_CHECKERS = Arrays.asList(new Integer[]{2, 0, 0, 0, 0, 5,    0, 3, 0, 0, 0, 5,
                                                                                         5, 0, 0, 0, 3, 0,   5, 0, 0, 0, 0, 2});
    public static final List<Color> COLOR_OF_CHECKERS = Arrays.asList(new Color[]{Color.BLACK, null, null, null, null, Color.WHITE,
                                                                                null, Color.WHITE, null, null, null, Color.BLACK,
                                                                                Color.WHITE, null, null, null, Color.BLACK, null,
                                                                                Color.BLACK, null, null, null, null, Color.WHITE});

    public Game(){
        points = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            points.add(new Column(COLOR_OF_CHECKERS.get(i), NUMBER_OF_CHECKERS.get(i)));
        }
        whiteCapturedCheckers = new Column(Color.WHITE, 0);
        blackCapturedCheckers = new Column(Color.BLACK, 0);

        whiteBornOffCheckers = new Column(Color.WHITE, 0);
        blackBornOffCheckers = new Column(Color.BLACK, 0);
    }

    public GameState getState(){
        GameState state = new GameState();
        //TODO dice
        state.numberOnDie1 = 1;
        state.numberOnDie2 = 1;
        state.validMoves = Arrays.asList(1, 1, 1, 1);

        state.numberOfCheckersOnColumn = points.stream().map(Column::getNumberOfCheckers).collect(Collectors.toList());
        state.colorOfCheckersOnColumn = points.stream().map(Column::getColor).collect(Collectors.toList());

        state.numberOfBlackHitCheckers = blackCapturedCheckers.getNumberOfCheckers();
        state.numberOfBlackBornOffCheckers = blackBornOffCheckers.getNumberOfCheckers();

        state.numberOfWhiteHitCheckers = whiteCapturedCheckers.getNumberOfCheckers();
        state.numberOfWhiteBornOffCheckers = whiteBornOffCheckers.getNumberOfCheckers();

        return state;
    }
    public void setUp(){
    }
    public void move(Command command){
        System.out.println(command);
        Choice source = command.source;
        Choice destination = command.destination;
        switch (source.type){
            case POINT:

                break;
            default:
        }
    }
}
