package gui.columns;

import logic.Game;

import java.awt.*;

import gui.UpdatableComponent;
import gui.columns.CheckerColumn;

public class PointCheckerColumn extends CheckerColumn implements UpdatableComponent {
    private final int index;
    public PointCheckerColumn(StackDirection direction, Dimension dimension, int index) {
        super(direction, dimension);
        this.index = index;
    }

    @Override
    public void update(Game.GameState state) {
        switch (state.colorOfCheckersOnColumn.get(index)){
            case black:
                setCheckerImage(BLACK_CHECKER_IMAGE);
                break;
            case white:
                setCheckerImage(WHITE_CHECKER_IMAGE);
                break;
        }
        setNumberOfCheckers(state.numberOfCheckersOnColumn.get(index));
    }
}
