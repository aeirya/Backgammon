package gui.columns;

import logic.Color;
import logic.Game;
import util.resource.ImageResource;

import java.awt.*;

import gui.UpdatableComponent;

public class HitCheckerColumn extends CheckerColumn implements UpdatableComponent {
    private final Color color;

    public HitCheckerColumn(StackDirection direction, Dimension dimension, Color color) {
        super(direction, dimension);
        this.color = color;
        switch (color){
            case BLACK:
                setCheckerImage(ImageResource.BLACK_CHECKER);
                break;
            case WHITE:
                setCheckerImage(ImageResource.WHITE_CHECKER);
                break;
        }
    }

    @Override
    public void update(Game.GameState state) {
        switch (color){
            case BLACK :
                setNumberOfCheckers(state.numberOfBlackHitCheckers);
                break;
            case WHITE:
                setNumberOfCheckers(state.numberOfWhiteHitCheckers);
                break;
        }
    }
}
