package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import logic.Choice;

public class ColumnMouseEventHandler implements ActionListener {
    private GameFrame frame;
    private Choice choice;

    public ColumnMouseEventHandler(GameFrame frame, Choice choice) {
        this.frame = frame;
        this.choice = choice;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        frame.addChoice(this.choice);
    }
}
