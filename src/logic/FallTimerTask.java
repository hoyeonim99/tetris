package logic;

import java.util.TimerTask;

public class FallTimerTask extends TimerTask { //timer to make pieces fall periodically

    private TetrisGame game;

    public FallTimerTask(TetrisGame game){
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
        if (game.setAndReturnCurrentBlockColliding()){
            if (!game.getHardDropping()) {
                game.glueBlock();
                game.updateShadowAndPreviewAndCurrentBlock();
                game.getBoard().clearFilledRows();
                game.placeShadow();
            }
        }
        else {
            game.getCurrentBlock().fall();
        }
    }
}