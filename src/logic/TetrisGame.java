package logic;

import blocks.BlockType;
import blocks.Brick;
import blocks.TetrisBlock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*this class is for handling main game logic: generating blocks, landing blocks,
 , holding blocks, showing next blocks, and generating shadows.*/
public class TetrisGame {
    private Board board;
    private TetrisBlock currentBlock; //the block that player controls
    private TetrisBlock shadow; //shows where the block will land
    private BlockType heldType = null; //to implement the "hold" functionality
    private List<BlockType> futureTypes; //future currentBlock types to come
    private BlockType[] preview = new BlockType[3]; //shows next 3 blocks
    private boolean hardDropping = false; /*to stop fall timer task from gluing block if
    space-bar is pressed */
    private boolean colliding; /*storage variable so that the program doesn't have to call
    isColliding() method in TetrisBlock class on currentBlock all the time */
    private boolean paused = false;
    private boolean gameOver = false;

    public TetrisGame(Board board){
        this.board = board;
        futureTypes = new ArrayList<>();
        Collections.addAll(futureTypes, BlockType.IBlock, BlockType.JBlock,
                BlockType.LBlock, BlockType.ZBlock, BlockType.SBlock, BlockType.TBlock,
                BlockType.OBlock);
        Collections.shuffle(futureTypes);
        setCurrentAndFutureRandom();
        updatePreview();
        bringDownShadow();
    }

    void glueBlock(){
        try {
            currentBlock.getBricks().forEach((Brick brick) ->
                    board.getRows().put(brick.getY(),
                            board.getRows().get(brick.getY()) + 1));
            board.getFilledSpace().addAll(currentBlock.getBricks());
        }
        catch (NullPointerException e){ //this will be thrown when blocks go up too high
            gameOver = true;
        }
    }

    void updateShadowAndPreviewAndCurrentBlock(){ //should always go hand in hand with
        // glueBlock()
        setCurrentAndFutureRandom();
        bringDownShadow();
        updatePreview();
        hardDropping = false;
    }

    void hold(){
        if (heldType == null){
            heldType = currentBlock.getType();
            setCurrentAndFutureRandom();
            updatePreview();
        }
        else {
            BlockType temp = heldType;
            heldType = currentBlock.getType();
            setCurrentBlock(temp);
        }
        bringDownShadow();
    }

    private void bringDownShadow(){
        while (!shadow.isColliding(board)){
            shadow.move(0, 1);
        }
    }

    void placeShadow(){
        int dx =  currentBlock.getReference().getX() - shadow.getReference().getX();
        int dy =  currentBlock.getReference().getY() - shadow.getReference().getY();
        shadow.move(dx, dy);
        bringDownShadow();
    }

    void rotateShadow(){
        int dx =  currentBlock.getReference().getX() - shadow.getReference().getX();
        int dy =  currentBlock.getReference().getY() - shadow.getReference().getY();
        shadow.move(dx, dy);
        shadow.rotate(board);
        bringDownShadow();
    }

    private void updatePreview(){
        for (int i = 0; i < 3; ++i) {
            preview[i] = futureTypes.get(i);
        }
    }

    public TetrisBlock getCurrentBlock(){
        return currentBlock;
    }

    private void setCurrentBlock(BlockType type){
        currentBlock = type.createBlock();
        shadow = type.createBlock();
    }

    //makes a new current block then decides the order of next 7 blocks randomly
    private void setCurrentAndFutureRandom(){
        setCurrentBlock(futureTypes.get(0));
        futureTypes.remove(0);
        if (futureTypes.size() <= 3){
            randomizeFutureBlocks();
        }
    }

    private void randomizeFutureBlocks(){
        List<BlockType> futureBlocks = new ArrayList<>();
        Collections.addAll(futureBlocks, BlockType.IBlock, BlockType.JBlock,
                BlockType.LBlock, BlockType.ZBlock, BlockType.SBlock, BlockType.TBlock,
                BlockType.OBlock);
        Collections.shuffle(futureBlocks);
        futureTypes.addAll(futureBlocks);
    }

    public BlockType[] getPreview(){
        return preview;
    }

    public TetrisBlock getShadow(){
        return shadow;
    }

    public BlockType getHeldType(){
        return heldType;
    }

    public boolean getPaused(){
        return paused;
    }

    public Board getBoard(){
        return board;
    }

    public boolean getGameOver(){
        return gameOver;
    }

    void changePaused(){
        paused = !paused;
    }

    void changeGameOver(){
        gameOver = !gameOver;
    }

    boolean getHardDropping(){
        return hardDropping;
    }

    void setHardDropping(boolean bool){
        hardDropping = bool;
    }

    boolean getColliding(){
        return colliding;
    }

    boolean setAndReturnCurrentBlockColliding(){
        return colliding = currentBlock.isColliding(board);
    }
}