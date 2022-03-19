package gui.columns;

import logic.Color;
import logic.Game;
import util.resource.ImageResource;

import java.awt.*;

import gui.UpdatableComponent;


public class BarCheckerColumn extends CheckerColumn implements UpdatableComponent {
    private final Color color;

    public BarCheckerColumn(StackDirection direction, Dimension dimension, Color color) {
        super(direction, dimension);
        this.color = color;

        setCheckerImage(
            color.equals(Color.BLACK) ? ImageResource.BLACK_CHECKER_VERTICAL : ImageResource.WHITE_CHECKER_VERTICAL
        );
    }

    @Override
    public void update(Game.GameState state) {
        switch (color){
            case BLACK:
                setNumberOfCheckers(state.numberOfBlackBornOffCheckers);
                break;
            case WHITE:
                setNumberOfCheckers(state.numberOfWhiteBornOffCheckers);
                break;
        }
    }
}
