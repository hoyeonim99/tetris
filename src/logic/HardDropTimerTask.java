package logic;

import java.util.TimerTask;

public class HardDropTimerTask extends TimerTask {//constantly checking for hard drops

    private TetrisGame game;

    public HardDropTimerTask(TetrisGame game){
        this.game = game;
    }

    void setGame(TetrisGame game){
        this.game = game;
    }

    @Override
    public void run(){
        if (game.getPaused() || game.getGameOver()){
            return;
        }
        if (game.getHardDropping() && game.getColliding()){
            game.glueBlock();
            game.updateShadowAndPreviewAndCurrentBlock();
            game.getBoard().clearFilledRows();
            game.placeShadow();
            game.setHardDropping(false);
        }
    }
}