package gui.columns;

import javax.swing.*;

import util.resource.ImageResource;
import util.resource.ResourceManager;

import java.awt.*;
public class CheckerColumn extends JButton{

    public enum StackDirection{
        UPWARDS, DOWNWARDS
    }

    private final StackDirection direction;
    private Image checkerImage;
    private final Dimension dimension;
    private int checkerHeight;
    private int checkerWidth;
    private int middleX;
    
    public CheckerColumn(StackDirection direction, Dimension dimension){
        setLayout(null);
        setOpaque(false);
        setFocusable(false);
        setBorderPainted(false);
        this.direction = direction;
        this.dimension = dimension;
        this.setPreferredSize(dimension);
        this.middleX = (int)Math.floor(dimension.getWidth() / 2);
    }
    public void setNumberOfCheckers(int n){
        this.removeAll();
        int base = 0;
        if (direction == StackDirection.UPWARDS)
            base = (int) Math.floor(dimension.getHeight()) - checkerImage.getHeight(null);

        for (int i = 0; i < n; i++) {
            JLabel checker = new JLabel(new ImageIcon(checkerImage));
            checker.setBounds(middleX - checkerWidth / 2, base, checkerImage.getWidth(null), checkerImage.getHeight(null));
            this.add(checker);
            base += direction == StackDirection.DOWNWARDS ? checkerHeight : -checkerHeight;
        }
    }

    public void setCheckerImage(ImageResource checkerImageType) {
        Image image = ResourceManager.get(checkerImageType);
        this.checkerImage = image;
        ImageIcon icon = new ImageIcon(image);
        this.checkerWidth = icon.getIconWidth();
        this.checkerHeight = icon.getIconHeight();
    }
}
