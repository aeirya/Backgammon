package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import logic.Choice;

public class ColumnMouseEventHandler extends MouseAdapter {
    private GameFrame frame;
    private Choice choice;

    public ColumnMouseEventHandler(GameFrame frame, Choice choice) {
        this.frame = frame;
        this.choice = choice;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        frame.addChoice(this.choice);
    }
}
