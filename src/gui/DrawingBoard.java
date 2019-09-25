package gui;

import blocks.Brick;
import logic.FallTimerTask;
import logic.HardDropTimerTask;
import logic.TetrisGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawingBoard extends JPanel implements ActionListener {

    private TetrisGame game;
    private javax.swing.Timer timer;
    private java.util.Timer fallTimer;
    private java.util.Timer hardDropTimer;
    private FallTimerTask task;
    private HardDropTimerTask dropTask;

    DrawingBoard(TetrisGame game) {
        this.game = game;
        this.task = new FallTimerTask(game);
        this.dropTask = new HardDropTimerTask(game);
        this.timer = new javax.swing.Timer(20, this);
        this.fallTimer = new java.util.Timer();
        this.hardDropTimer = new java.util.Timer();
        setBackground(new Color(200, 200, 200));
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (game.getGameOver()){
            setBackground(Color.BLACK);
            Font font = g.getFont().deriveFont( 100.0f );
            g.setFont(font);
            g.setColor(Color.WHITE);
            g.drawString("GAME OVER", 200, 450);
            Font smallerFont = g.getFont().deriveFont(30.0f);
            g.setFont(smallerFont);
            g.drawString("Press ENTER to restart", 300, 530);
            return;
        }
        try {
            g.setColor(Color.GRAY);
            game.getShadow().getBricks().forEach((Brick brick) ->
                g.fillRect(brick.getX() * 40, brick.getY() * 40, 40, 40));

            game.getCurrentBlock().getBricks().forEach((Brick brick) -> {
                g.setColor(brick.getColor());
                g.fillRect(brick.getX() * 40, brick.getY() * 40, 40, 40);
            });

            game.getBoard().getFilledSpace().forEach((Brick brick) -> {
                g.setColor(brick.getColor());
                g.fillRect(brick.getX() * 40, brick.getY() * 40, 40, 40);
            });
        }
        catch (Exception e){
            //do nothing
        }

        Font font = g.getFont().deriveFont( 25.0f );
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("NEXT: ", 660, 50);
        g.drawString("HELD: ", 550, 720);

        HUDBlockPainter.paintBlock(game.getHeldType(), 680, 650, g);
        HUDBlockPainter.paintBlock(game.getPreview()[0], 650, 80, g);
        HUDBlockPainter.paintBlock(game.getPreview()[1], 650, 280, g);
        HUDBlockPainter.paintBlock(game.getPreview()[2], 650, 460, g);

        g.setColor(Color.BLACK);
        for (int i = 1; i < 11; i++){
            g.fillRect(i * 40, 0, 2, 800);
        }
        for (int j = 1; j < 21; j++){
            if (j == 20){
                g.fillRect(0, j * 40, 402, 2);
            }
            else {
                g.fillRect(0, j * 40, 400, 2);
            }
        }
        if (game.getPaused()){
            Font bigFont = g.getFont().deriveFont( 100.0f );
            g.setFont(bigFont);
            g.setColor(Color.BLACK);
            g.drawString("PAUSED", 230, 450);
        }
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        super.repaint();
    }

    void startFall(){
        fallTimer.schedule(task, 100, 500);
    }

    void startCheckingHardDrops(){
        hardDropTimer.schedule(dropTask, 100, 20);
    }

    public void setGame(TetrisGame game){
        this.game = game;
    }

    FallTimerTask getFallTask(){
        return task;
    }

    HardDropTimerTask getHardDropTask(){
        return dropTask;
    }
}