package gui;

import logic.Choice;
import logic.Color;
import logic.Command;
import logic.Game;
import util.resource.ImageResource;
import util.resource.ResourceManager;

import javax.swing.*;

import gui.columns.BarCheckerColumn;
import gui.columns.CheckerColumn;
import gui.columns.HitCheckerColumn;
import gui.columns.PointCheckerColumn;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GameFrame extends JFrame {
    public static final int DISPLAY_NUMBER = 0;
    public static final int GAME_FRAME_WIDTH = 673;
    public static final int GAME_FRAME_HEIGHT = 703;
    public static final int PIECE_WIDTH = 30;
    public static final int UP_LEFT_CORNER_X = 30;
    public static final int UP_LEFT_CORNER_Y = 30;
    public static final Dimension COLUMN_DIMENSION = new Dimension(47, 270);
    public static final int WIDTH_OF_CENTER_COLUMN = 30;

    private Game game;

    private JPanel pane;
    private JPanel board;
    private JLabel background;
    private List<PointCheckerColumn> points;
    private HitCheckerColumn whiteHitCheckers;
    private HitCheckerColumn blackHitCheckers;
    private BarCheckerColumn whiteBornOffCheckers;
    private BarCheckerColumn blackBornOffCheckers;
    private UpdatableLabel leftDie;
    private UpdatableLabel rightDie;
    private List<UpdatableComponent> updatableComponents = new ArrayList<>();
    private Queue<Choice> choices = new LinkedList<>();
    private JButton rollDiceButton;

    public GameFrame(Game game){
        this.game = game;


        //construct board components
        initComponents();
        alignComponents();

        updateComponents(game.getState());

        //settings
        setSize(new Dimension(GAME_FRAME_WIDTH, GAME_FRAME_HEIGHT));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Backgammon");

        // open on the [DISPLAY_NUMBER]-th display
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gd = ge.getScreenDevices();

        setLocation(gd[DISPLAY_NUMBER].getDefaultConfiguration().getBounds().x +
                    (gd[DISPLAY_NUMBER].getDefaultConfiguration().getBounds().width - GAME_FRAME_WIDTH) / 2, getY());



        setVisible(true);

        repaintFrame();
    }



    private void initComponents(){
        pane = new JPanel();
        board = new JPanel();
        board.setOpaque(false);
        background = new JLabel(new ImageIcon(ResourceManager.get(ImageResource.BACKGROUND)));
        setContentPane(pane);
        points = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            PointCheckerColumn point = new PointCheckerColumn(i < 12 ? CheckerColumn.StackDirection.UPWARDS : CheckerColumn.StackDirection.DOWNWARDS,
                    COLUMN_DIMENSION,i);
            points.add(point);


        }

        String dieSymbols = "??????????????????";

        leftDie = new UpdatableLabel() {
            @Override
            public void update(Game.GameState state) {
                setText(""+dieSymbols.charAt(state.numberOnDie1-1));
            }
        };
        leftDie.setPreferredSize(new Dimension(WIDTH_OF_CENTER_COLUMN/2,25));
        leftDie.setFont(new Font("Times",Font.BOLD,25));
        leftDie.setForeground(java.awt.Color.BLUE);
        rightDie = new UpdatableLabel() {
            @Override
            public void update(Game.GameState state) {
                setText(""+dieSymbols.charAt(state.numberOnDie2-1));
            }
        };
        rightDie.setPreferredSize(new Dimension(WIDTH_OF_CENTER_COLUMN / 2,30));
        rightDie.setFont(new Font("Times",Font.BOLD,25));
        rightDie.setForeground(java.awt.Color.BLUE);
        whiteHitCheckers = new HitCheckerColumn(CheckerColumn.StackDirection.DOWNWARDS,
                new Dimension(WIDTH_OF_CENTER_COLUMN, COLUMN_DIMENSION.height), Color.WHITE);
        blackHitCheckers = new HitCheckerColumn(CheckerColumn.StackDirection.UPWARDS,
                new Dimension(WIDTH_OF_CENTER_COLUMN, COLUMN_DIMENSION.height), Color.BLACK);
        whiteBornOffCheckers = new BarCheckerColumn(CheckerColumn.StackDirection.UPWARDS,
                new Dimension(PIECE_WIDTH, COLUMN_DIMENSION.height),Color.WHITE);
        blackBornOffCheckers = new BarCheckerColumn(CheckerColumn.StackDirection.DOWNWARDS,
                new Dimension(PIECE_WIDTH, COLUMN_DIMENSION.height),Color.BLACK);

        rollDiceButton = new JButton("Roll Dice!");
        rollDiceButton.setFont(new Font("Times", Font.BOLD, 10));
        rollDiceButton.setForeground(new java.awt.Color(145, 16, 170));

        rollDiceButton.setPreferredSize(new Dimension(COLUMN_DIMENSION.width * 2,30));
        addMouseAdapters();
        addUpdatables();
    }
    private void addUpdatables(){
        updatableComponents.addAll(points);

        updatableComponents.add(leftDie);
        updatableComponents.add(rightDie);

        updatableComponents.add(whiteHitCheckers);
        updatableComponents.add(blackHitCheckers);

        updatableComponents.add(whiteBornOffCheckers);
        updatableComponents.add(blackBornOffCheckers);


    }
    private void addMouseAdapters(){
        for (int i = 0; i < 24; i++) {
            points.get(i).addActionListener(new ColumnMouseEventHandler(this, new Choice(Command.ColumnType.POINT, i, null)));
        }
        blackHitCheckers.addActionListener(new ColumnMouseEventHandler(this, new Choice(Command.ColumnType.MIDDLE, 0, Color.BLACK)));
        whiteHitCheckers.addActionListener(new ColumnMouseEventHandler(this, new Choice(Command.ColumnType.MIDDLE, 0, Color.WHITE)));
        blackBornOffCheckers.addActionListener(new ColumnMouseEventHandler(this, new Choice(Command.ColumnType.BAR, 0, Color.BLACK)));
        whiteBornOffCheckers.addActionListener(new ColumnMouseEventHandler(this, new Choice(Command.ColumnType.BAR, 0, Color.WHITE)));

        rollDiceButton.addActionListener(e -> {
            game.rollDice();
            updateComponents(game.getState());
        });
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
        gbc.anchor = GridBagConstraints.CENTER;
        for (int i = 0; i < 12; i++) {
            CheckerColumn upperColumn = points.get(12 + i);
            CheckerColumn lowerColumn = points.get(11 - i);

            gbc.gridwidth = 1;
            gbc.gridheight = 1;

            gbc.gridx = i < 6 ? i : i+1;
            gbc.gridy = 0;
            board.add(upperColumn, gbc);

            gbc.gridy = 2;
            board.add(lowerColumn, gbc);
        }

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 6;
        gbc.gridy = 0;
        board.add(whiteHitCheckers, gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 6;
        gbc.gridy = 2;
        board.add(blackHitCheckers, gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 9;
        gbc.gridy = 1;
        board.add(leftDie, gbc);
        gbc.gridx += 1;
        board.add(rightDie, gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 13;
        gbc.gridy = 0;
        board.add(blackBornOffCheckers, gbc);
        gbc.gridy += 2;
        board.add(whiteBornOffCheckers, gbc);

        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.gridx = 2;
        gbc.gridy = 1;
        board.add(rollDiceButton, gbc);

    }


    public void updateComponents(Game.GameState state){
        for (UpdatableComponent component : updatableComponents) {
            component.update(state);
        }
        repaintFrame();
    }
    public void repaintFrame(){
        this.revalidate();
        this.repaint();
    }


    public void addChoice(Choice choice) {
        System.out.println(choice);
        choices.add(choice);
        if (choices.size() > 1){
            Choice source = choices.poll();
            Choice destination = choices.poll();
            game.move(new Command(source, destination));
            Game.GameState newState = game.getState();
            updateComponents(newState);
        }
    }
}
