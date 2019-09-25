package gui;

import logic.Board;
import logic.KeyboardListener;
import logic.TetrisGame;

import javax.swing.*;
import java.awt.*;

public class UserInterface implements Runnable {

    private JFrame frame;

    @Override
    public void run(){
        frame = new JFrame("Tetris2");
        frame.setPreferredSize(new Dimension(1000, 900));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container){
        Board board = new Board();
        TetrisGame game = new TetrisGame(board);
        board.initializeRows();
        DrawingBoard db = new DrawingBoard(game);
        db.startFall();
        db.startCheckingHardDrops();
        container.add(db);
        frame.addKeyListener(new KeyboardListener(game, db, db.getFallTask(), db.getHardDropTask()));
    }
}