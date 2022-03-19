package gui.columns;

import javax.swing.*;

import logic.Game;

import java.awt.*;
public class CheckerColumn extends JPanel{

    public static final String WHITE_CHECKER_IMAGE_PATH = "whiteChecker.png";
    public static final String BLACK_CHECKER_IMAGE_PATH = "blackChecker.png";
    public static final String WHITE_CHECKER_VERTICAL_IMAGE_PATH = "whiteCheckerVertical.png";
    public static final String BLACK_CHECKER_VERTICAL_IMAGE_PATH = "blackCheckerVertical.png";

    public static final Image WHITE_CHECKER_IMAGE = Toolkit.getDefaultToolkit().getImage(WHITE_CHECKER_IMAGE_PATH);
    public static final Image BLACK_CHECKER_IMAGE = Toolkit.getDefaultToolkit().getImage(BLACK_CHECKER_IMAGE_PATH);
    public static final Image WHITE_CHECKER_VERTICAL_IMAGE = Toolkit.getDefaultToolkit().getImage(WHITE_CHECKER_VERTICAL_IMAGE_PATH);
    public static final Image BLACK_CHECKER_VERTICAL_IMAGE = Toolkit.getDefaultToolkit().getImage(BLACK_CHECKER_VERTICAL_IMAGE_PATH);

    public enum StackDirection{
        upwards, downwards
    }

    private final StackDirection direction;
    private Image checkerImage;
    private final Dimension dimension;
    private int checkerHeight;
    private int checkerWidth;
    public CheckerColumn(StackDirection direction, Dimension dimension){
        setLayout(null);
        setOpaque(false);
        this.direction = direction;
        this.dimension = dimension;
        this.setPreferredSize(dimension);
    }
    public void setNumberOfCheckers(int n){
        this.removeAll();
        int base = 0;
        if (direction == StackDirection.upwards)
            base = (int) Math.floor(dimension.getHeight()) - checkerImage.getHeight(null);

        for (int i = 0; i < n; i++) {
            JLabel checker = new JLabel(new ImageIcon(checkerImage));
            checker.setBounds(0, base, checkerImage.getWidth(null), checkerImage.getHeight(null));
            this.add(checker);
            base += direction == StackDirection.downwards ? checkerHeight : -checkerHeight;
        }
    }

    public void setCheckerImage(Image checkerImage) {
        this.checkerImage = checkerImage;
        ImageIcon icon = new ImageIcon(checkerImage);
        this.checkerWidth = icon.getIconWidth();
        this.checkerHeight = icon.getIconHeight();
    }
}
