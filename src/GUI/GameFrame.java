package GUI;

import logics.Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameFrame extends JFrame {
    public static final int GAME_FRAME_WIDTH = 673;
    public static final int GAME_FRAME_HEIGHT = 703;
    public static final int PIECE_WIDTH = 30;
    public static final int UP_LEFT_CORNER_X = 30;
    public static final int UP_LEFT_CORNER_Y = 30;
    public static final Dimension COLUMN_DIMENSION = new Dimension(46, 270);
    public static final int WIDTH_OF_CENTER_COLUMN = 30;

    public static final String BACKGROUND_PATH = "GameBackGround.png";
    public static final String WHITE_CHECKER_IMAGE_PATH = "whiteChecker.png";
    public static final String BLACK_CHECKER_IMAGE_PATH = "blackChecker.png";
    public static final String WHITE_CHECKER_VERTICAL_IMAGE_PATH = "whiteChecker.png";
    public static final String BLACK_CHECKER_VERTICAL_IMAGE_PATH = "blackChecker.png";

    public static final Image BACKGROUND_IMAGE = Toolkit.getDefaultToolkit().getImage(BACKGROUND_PATH);
    public static final Image WHITE_CHECKER_IMAGE = Toolkit.getDefaultToolkit().getImage(WHITE_CHECKER_IMAGE_PATH);
    public static final Image BLACK_CHECKER_IMAGE = Toolkit.getDefaultToolkit().getImage(BLACK_CHECKER_IMAGE_PATH);
    public static final Image WHITE_CHECKER_VERTICAL_IMAGE = Toolkit.getDefaultToolkit().getImage(WHITE_CHECKER_VERTICAL_IMAGE_PATH);
    public static final Image BLACK_CHECKER_VERTICAL_IMAGE = Toolkit.getDefaultToolkit().getImage(BLACK_CHECKER_VERTICAL_IMAGE_PATH);
    private Game game;

    private JPanel pane;
    private JPanel board;
    private JLabel background;
    private List<CheckerColumn> points;
    private CheckerColumn whiteHitCheckers;
    private CheckerColumn blackHitCheckers;
    private CheckerColumn whiteBornOffCheckers;
    private CheckerColumn blackBornOffCheckers;
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
        alignComponents();

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
        board = new JPanel();
        board.setOpaque(false);
        background = new JLabel(new ImageIcon(BACKGROUND_IMAGE));
        setContentPane(pane);
        points = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            points.add(new CheckerColumn(i < 12 ? CheckerColumn.StackDirection.downwards : CheckerColumn.StackDirection.upwards,
                    COLUMN_DIMENSION));

        }
        leftDie = new JLabel("⚂", JLabel.CENTER);
        leftDie.setPreferredSize(new Dimension(30,30));
        rightDie = new JLabel("⚂", JLabel.CENTER);
        rightDie.setPreferredSize(new Dimension(30,30));
        validMoves = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            validMoves.add(new JLabel("4"));
        }
        whiteHitCheckers = new CheckerColumn(CheckerColumn.StackDirection.downwards,
                                            new Dimension(WIDTH_OF_CENTER_COLUMN, COLUMN_DIMENSION.height));
        whiteHitCheckers.setCheckerImage(WHITE_CHECKER_IMAGE);
        blackHitCheckers = new CheckerColumn(CheckerColumn.StackDirection.upwards,
                new Dimension(WIDTH_OF_CENTER_COLUMN, COLUMN_DIMENSION.height));
        blackHitCheckers.setCheckerImage(BLACK_CHECKER_IMAGE);
        whiteBornOffCheckers = new CheckerColumn(CheckerColumn.StackDirection.upwards,
                                            new Dimension(UP_LEFT_CORNER_X, COLUMN_DIMENSION.height));
        whiteBornOffCheckers.setCheckerImage(WHITE_CHECKER_VERTICAL_IMAGE);
        blackBornOffCheckers = new CheckerColumn(CheckerColumn.StackDirection.downwards,
                new Dimension(UP_LEFT_CORNER_X, COLUMN_DIMENSION.height));
        blackBornOffCheckers.setCheckerImage(BLACK_CHECKER_VERTICAL_IMAGE);
    }
    private void alignComponents(){
        pane.setLayout(null);
        pane.add(board);
        pane.add(background);
        background.setBounds(0, 0, GAME_FRAME_WIDTH, GAME_FRAME_HEIGHT);
        board.setBounds( UP_LEFT_CORNER_X, UP_LEFT_CORNER_Y ,
                GAME_FRAME_WIDTH - UP_LEFT_CORNER_X,
                GAME_FRAME_HEIGHT - UP_LEFT_CORNER_Y * 2);
        board.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        for (int i = 0; i < 12; i++) {
            CheckerColumn upperColumn = points.get(12 + i);
            CheckerColumn lowerColumn = points.get(11 - i);

            gbc.gridwidth = 1;
            gbc.gridheight = 1;

            gbc.gridx = i < 6 ? i : i+2;
            gbc.gridy = 0;
            board.add(upperColumn, gbc);

            gbc.gridy = 2;
            board.add(lowerColumn, gbc);
        }

        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.gridx = 6;
        gbc.gridy = 0;
        board.add(whiteHitCheckers, gbc);

        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.gridx = 6;
        gbc.gridy = 2;
        board.add(blackHitCheckers, gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 6;
        gbc.gridy = 1;
        board.add(leftDie, gbc);
        gbc.gridx += 1;
        board.add(rightDie, gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 14;
        gbc.gridy = 0;
        board.add(blackBornOffCheckers, gbc);
        gbc.gridy += 2;
        board.add(whiteBornOffCheckers, gbc);

        for (int i = 0; i < 4; i++) {
            gbc.gridheight = 1;
            gbc.gridwidth = 1;
            gbc.gridx = 9 + i;
            gbc.gridy = 1;

            board.add(validMoves.get(i), gbc);
        }

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
