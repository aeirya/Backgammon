package GUI;

import logics.Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameFrame extends JFrame {
    public static final int GAME_FRAME_WIDTH = 693;
    public static final int GAME_FRAME_HEIGHT = 703;
    public static final int PIECE_WIDTH = 30;
    public static final String BACKGROUND_PATH = "GameBackGround.png";
    public static final Image BACKGROUND_IMAGE = Toolkit.getDefaultToolkit().getImage(BACKGROUND_PATH);

    private Game game;

    private JPanel pane;
    private JLabel background;
    private List<CheckerColumn> points;
    private JLabel leftDie;
    private JLabel rightDie;
    private List<JLabel> validMoves;
    private GameFrame(){

    }

    public void initGame(){
        // initiate game logical components
        game = new Game();
        game.setUp();

        //construct board components
        initComponents();
        aliginComponents();

        //settings
        setSize(new Dimension(GAME_FRAME_WIDTH, GAME_FRAME_HEIGHT));
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setTitle("Backgammon");
        setVisible(true);

        repaintFrame();
    }

    private void initComponents(){
        pane = new JPanel();
        background = new JLabel(new ImageIcon(BACKGROUND_IMAGE));
        setContentPane(pane);
        points = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            points.add(new CheckerColumn(i < 12 ? CheckerColumn.StackDirection.downwards : CheckerColumn.StackDirection.upwards,
                    new Dimension(PIECE_WIDTH, PIECE_WIDTH * 6)));

        }
        leftDie = new JLabel("⚂");
        rightDie = new JLabel("⚂");
        validMoves = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            validMoves.add(new JLabel("4"));
        }

    }
    private void aliginComponents(){
        pane.setLayout(null);
        leftDie.setBounds(0, 0, 30, 30);
        pane.add(background);
        background.setBounds(0, 0, GAME_FRAME_WIDTH, GAME_FRAME_HEIGHT);
        //TODO

    }

    public void repaintFrame(){
        this.revalidate();
        this.repaint();
    }

    private static GameFrame instance = new GameFrame();
    public static GameFrame getInstance(){
        return instance;
    }

}
