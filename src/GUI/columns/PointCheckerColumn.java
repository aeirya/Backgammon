package gui.columns;

import logic.Game;
import util.resource.ImageResource;

import java.awt.*;

import gui.UpdatableComponent;

public class PointCheckerColumn extends CheckerColumn implements UpdatableComponent {
    private final int index;
    public PointCheckerColumn(StackDirection direction, Dimension dimension, int index) {
        super(direction, dimension);
        this.index = index;
    }

    @Override
    public void update(Game.GameState state) {
        switch (state.colorOfCheckersOnColumn.get(index)){
            case BLACK:
                setCheckerImage(ImageResource.BLACK_CHECKER);
                break;
            case WHITE:
                setCheckerImage(ImageResource.WHITE_CHECKER);
                break;
        }
        setNumberOfCheckers(state.numberOfCheckersOnColumn.get(index));
    }
}
