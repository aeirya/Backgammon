package gui;

import logic.Game;

public interface UpdatableComponent {
    void update(Game.GameState state);
}
