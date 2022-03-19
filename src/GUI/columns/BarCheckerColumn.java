package gui.columns;

import logic.Color;
import logic.Game;

import java.awt.*;

import gui.UpdatableComponent;


public class BarCheckerColumn extends CheckerColumn implements UpdatableComponent {
    private final Color color;

    public BarCheckerColumn(StackDirection direction, Dimension dimension, Color color) {
        super(direction, dimension);
        this.color = color;
        switch (color){
            case black:
                setCheckerImage(BLACK_CHECKER_VERTICAL_IMAGE);
                break;
            case white:
                setCheckerImage(WHITE_CHECKER_VERTICAL_IMAGE);
                break;
        }
    }

    @Override
    public void update(Game.GameState state) {
        switch (color){
            case black:
                setNumberOfCheckers(state.numberOfBlackBornOffCheckers);
                break;
            case white:
                setNumberOfCheckers(state.numberOfWhiteBornOffCheckers);
                break;
        }
    }
}
