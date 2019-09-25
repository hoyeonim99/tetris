package logic;

import gui.DrawingBoard;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

    private TetrisGame game;
    private DrawingBoard db;
    private HardDropTimerTask hardDropTask ;
    private FallTimerTask fallTask;

    public KeyboardListener(TetrisGame game, DrawingBoard db, FallTimerTask fallTask,
                            HardDropTimerTask hardDropTask) {
        this.game = game;
        this.db = db;
        this.hardDropTask = hardDropTask;
        this.fallTask = fallTask;
    }

    @Override
    public void keyPressed(KeyEvent ke){
        if (ke.getKeyCode() == KeyEvent.VK_P){
            game.changePaused();
        }
        else if (ke.getKeyCode() == KeyEvent.VK_ENTER && game.getGameOver()){
            game.changeGameOver();
            Board board = new Board();
            board.initializeRows();
            TetrisGame newGame = new TetrisGame(board);
            db.setGame(newGame);
            fallTask.setGame(newGame);
            hardDropTask.setGame(newGame);
            this.game = newGame;
            db.setBackground(new Color(200, 200, 200));
        }
        if (game.getPaused() || game.getGameOver()){
            return;
        }
        if (ke.getKeyCode() == KeyEvent.VK_UP){
            game.getCurrentBlock().rotate(game.getBoard());
            game.rotateShadow();
        }
        else if (ke.getKeyCode() == KeyEvent.VK_LEFT){
            game.getCurrentBlock().moveLeft(game.getBoard());
            game.placeShadow();
        }
        else if (ke.getKeyCode() == KeyEvent.VK_RIGHT){
            game.getCurrentBlock().moveRight(game.getBoard());
            game.placeShadow();
        }
        else if (ke.getKeyCode() == KeyEvent.VK_DOWN){
            if (!game.getCurrentBlock().isColliding(game.getBoard())){
                game.getCurrentBlock().fall();
            }
        }
        else if (ke.getKeyCode() == KeyEvent.VK_E){
            game.hold();
        }
        else if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
            while (!game.setAndReturnCurrentBlockColliding()) {
                game.getCurrentBlock().fall();
            }
            game.setHardDropping(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent ke){
    }

    @Override
    public void keyTyped(KeyEvent ke){
    }
}