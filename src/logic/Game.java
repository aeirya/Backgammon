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
    private Rule ruleRoot;

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
        initSetOfRules();
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
    private void initSetOfRules(){
        //Rule: bar cannot be chosen as the source of the command
        addRule(new Rule(this) {
            @Override
            public boolean isValid(Command command) {
                return command.source.type != Command.ColumnType.BAR;
            }
        });
        //Rule: source cannot be empty
        addRule(new Rule(this) {
            @Override
            public boolean isValid(Command command) {
                if (command.source.type == Command.ColumnType.MIDDLE)
                    return (command.source.color == Color.BLACK ? blackCapturedCheckers:whiteCapturedCheckers).getNumberOfCheckers() != 0;
                if (command.source.type == Command.ColumnType.POINT)
                    return points.get(command.source.index).getNumberOfCheckers() != 0;
                return false;
            }
        });
    }
    public boolean move(Command command){
        System.out.println(command);
        if (!ruleRoot.check(command)) {
            System.err.println("not possible");
            return false;
        }
        Choice source = command.source;
        Choice destination = command.destination;
        switch (source.type){
            case POINT:
                if (destination.type == Command.ColumnType.MIDDLE)
                    return false;
                if (destination.type == Command.ColumnType.BAR)
                    return moveToBar(command);
                if (destination.type == Command.ColumnType.POINT)
                    return moveFromPointToPoint(command);
                break;
            case MIDDLE:
                return moveFromMiddle(command);
            default:
        }
        //TODO what should the default return value be?
        return true;
    }

    private boolean moveFromMiddle(Command command) {
        if (command.destination.type != Command.ColumnType.POINT)
            return false;
//        if ()
        return false;
    }
    private boolean hit(Column column){
        if (column.getNumberOfCheckers() > 1)
            return false;
        switch (column.getColor()){
            case BLACK :
                return blackCapturedCheckers.addChecker(Color.BLACK);
            case WHITE:
                return whiteCapturedCheckers.addChecker(Color.WHITE);
            default:
                return false;
        }

    }

    private boolean moveFromPointToPoint(Command command){

        Color checkerColor = points.get(command.source.index).getColor();
        Column destinationColumn = points.get(command.destination.index);
        if (destinationColumn.getNumberOfCheckers() != 0)
            if (destinationColumn.getColor() != checkerColor)
               if (!hit(destinationColumn))
                   return false;


        if (!points.get(command.source.index).removeTop())
            return false;

        return destinationColumn.addChecker(checkerColor);
    }
    private boolean moveToBar(Command command){
        if (!points.get(command.source.index).removeTop())
            return false;
        switch (command.destination.color){
            case WHITE :
                return whiteBornOffCheckers.addChecker(Color.WHITE);
            case BLACK:
                return blackBornOffCheckers.addChecker(Color.BLACK);
            default:
                return false;
        }

    }

    private void addRule(Rule rule){
        rule.setNext(ruleRoot);
        ruleRoot = rule;
    }
}
