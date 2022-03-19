package gui;

import javax.swing.*;

public abstract class UpdatableLabel extends JLabel implements UpdatableComponent{
    public UpdatableLabel(){
        super("--", JLabel.CENTER);
    }
}
