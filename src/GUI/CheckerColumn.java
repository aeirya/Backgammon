package GUI;

import javax.swing.*;
import java.awt.*;
// TODO must be package level
public class CheckerColumn extends JPanel {
    public enum StackDirection{
        upwards, downwards
    }
    private StackDirection direction;
    private Image checkerImage;
    private Dimension dimension;
    private int checkerHeight;
    private int checkerWidth;
    public CheckerColumn(StackDirection direction, Dimension dimension){
        this.direction = direction;
        this.dimension = dimension;
        this.setPreferredSize(dimension);

    }
    public void setNumberOfCheckers(int n){
        this.removeAll();
        int base = 0;
        if (direction == StackDirection.upwards)
            base = (int)Math.floor(dimension.getHeight()) - checkerImage.getHeight(null);
        for (int i = 0; i < n; i++) {
            JLabel checker = new JLabel(new ImageIcon(checkerImage));
            checker.setBounds(base, 0, checkerImage.getWidth(null), checkerImage.getHeight(null));
            this.add(checker);
            base += direction == StackDirection.downwards ? checkerHeight : -checkerHeight;
        }
    }

    public void setCheckerImage(Image checkerImage) {
        this.checkerImage = checkerImage;
        this.checkerWidth = checkerImage.getWidth(null);
        this.checkerHeight = checkerImage.getHeight(null);
    }
}
