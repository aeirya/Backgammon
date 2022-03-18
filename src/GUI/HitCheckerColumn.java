package GUI;

import logics.Color;
import logics.Game;

import java.awt.*;

public class HitCheckerColumn extends CheckerColumn implements UpdatableComponent{
    private final Color color;

    public HitCheckerColumn(StackDirection direction, Dimension dimension, Color color) {
        super(direction, dimension);
        this.color = color;
        switch (color){
            case black:
                setCheckerImage(BLACK_CHECKER_IMAGE);
                break;
            case white:
                setCheckerImage(WHITE_CHECKER_IMAGE);
                break;
        }
    }

    @Override
    public void update(Game.GameState state) {
        switch (color){
            case black :
                setNumberOfCheckers(state.numberOfBlackHitCheckers);
                break;
            case white:
                setNumberOfCheckers(state.numberOfWhiteHitCheckers);
                break;
        }
    }
}
