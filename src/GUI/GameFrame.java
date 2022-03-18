package GUI;

import logics.Color;
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
    public static final String WHITE_CHECKER_VERTICAL_IMAGE_PATH = "whiteCheckerVertical.png";
    public static final String BLACK_CHECKER_VERTICAL_IMAGE_PATH = "blackCheckerVertical.png";

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
    private HitCheckerColumn whiteHitCheckers;
    private HitCheckerColumn blackHitCheckers;
    private BarCheckerColumn whiteBornOffCheckers;
    private BarCheckerColumn blackBornOffCheckers;
    private UpdatableLabel leftDie;
    private UpdatableLabel rightDie;
    private List<UpdatableLabel> validMoves;
    private List<UpdatableComponent> updatableComponents = new ArrayList<>();

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
            PointCheckerColumn point = new PointCheckerColumn(i < 12 ? CheckerColumn.StackDirection.upwards : CheckerColumn.StackDirection.downwards,
                    COLUMN_DIMENSION,i);
            points.add(point);
            updatableComponents.add(point);
        }

        String dieSymbols = "⚀⚁⚂⚃⚄⚅";

        leftDie = new UpdatableLabel() {
            @Override
            public void update(Game.GameState state) {
                setText(""+dieSymbols.charAt(state.numberOnDie1-1));
            }
        };
        leftDie.setPreferredSize(new Dimension(30,30));
        updatableComponents.add(leftDie);
        rightDie = new UpdatableLabel() {
            @Override
            public void update(Game.GameState state) {
                setText(""+dieSymbols.charAt(state.numberOnDie2-1));
            }
        };
        rightDie.setPreferredSize(new Dimension(30,30));
        updatableComponents.add(rightDie);
        validMoves = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            UpdatableLabel label = new UpdatableLabel() {
                @Override
                public void update(Game.GameState state) {
                    //TODO
                }
            };
            validMoves.add(label);
            updatableComponents.add(label);
        }
        whiteHitCheckers = new HitCheckerColumn(CheckerColumn.StackDirection.downwards,
                new Dimension(WIDTH_OF_CENTER_COLUMN, COLUMN_DIMENSION.height), Color.white);
        blackHitCheckers = new HitCheckerColumn(CheckerColumn.StackDirection.upwards,
                new Dimension(WIDTH_OF_CENTER_COLUMN, COLUMN_DIMENSION.height), Color.black);
        whiteBornOffCheckers = new BarCheckerColumn(CheckerColumn.StackDirection.upwards,
                new Dimension(PIECE_WIDTH, COLUMN_DIMENSION.height),Color.white);
        blackBornOffCheckers = new BarCheckerColumn(CheckerColumn.StackDirection.downwards,
                new Dimension(PIECE_WIDTH, COLUMN_DIMENSION.height),Color.black);

        updatableComponents.add(whiteHitCheckers);
        updatableComponents.add(blackHitCheckers);

        updatableComponents.add(whiteBornOffCheckers);
        updatableComponents.add(blackBornOffCheckers);

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

    public void updateComponents(Game.GameState state){
        for (UpdatableComponent component : updatableComponents) {
            component.update(state);
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
