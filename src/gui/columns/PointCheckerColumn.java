package gui.columns;

import logic.Color;
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

        if (Color.BLACK.equals(state.colorOfCheckersOnColumn.get(index)))
            setCheckerImage(ImageResource.BLACK_CHECKER);
        else
            setCheckerImage(ImageResource.WHITE_CHECKER);


        setNumberOfCheckers(state.numberOfCheckersOnColumn.get(index));
    }
}
