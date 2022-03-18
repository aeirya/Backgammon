import GUI.CheckerColumn;
import GUI.GameFrame;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        GameFrame frame = GameFrame.getInstance();
        frame.initGame();

//        CheckerColumn column = new CheckerColumn(CheckerColumn.StackDirection.upwards,new Dimension(33, 200));
//        column.setCheckerImage(Toolkit.getDefaultToolkit().getImage("blackChecker.png"));
//        frame.getContentPane().add(column);
//        column.setBounds(0, 0, 40, 300);
//        column.setPreferredSize(new Dimension(40, 300));
//        column.setNumberOfCheckers(3);
//        frame.repaintFrame();

    }
}
