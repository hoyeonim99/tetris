package blocks;

import blocks.blocktype.State;
import logic.Board;

import java.awt.*;
import java.util.*;
import java.util.List;

//four 'bricks' come together to compose a '(Tetris) block'
public abstract class TetrisBlock {
    protected BlockType type;
    private State state = State.HORIZONTAL1;
    protected Brick reference = new Brick(3, -2); //the reference point for rotations
    protected List<Brick> bricks;
    private List<Brick> hitbox; //to check collision from below
    //hardcoding default wall kick data for every block except for I and O
    protected int[][] wkData0 = {
            {-1, 0},
            {-1, -1},
            {0, 2},
            {-1, 2}
    };
    protected int[][] wkDataR = {
            {1, 0},
            {1, 1},
            {0, -2},
            {1, -2}
    };
    protected int[][] wkData2 = {
            {1, 0},
            {1, -1},
            {0, 2},
            {1, 2}
    };
    protected int[][] wkDataL = {
            {-1, 0},
            {-1, 1},
            {0, -2},
            {-1, -2}
    };
    /*the following int[][] will be hardcoded configurations of the blocks, to be
    specified in the subclasses. */
    protected int[][] vertical1;
    protected int[][] horizontal2;
    protected int[][] horizontal1;
    protected int[][] vertical2;
    protected Color color;
    protected int size = 3; //dimensions of int[][]. OBlock and IBlock will override 3
    //this is a temporary storage in case a rotation and its possible wall kicks fail
    private State savedState; //for storing original state in case rotation fails
    private Brick savedReference;
    private List<Brick> savedBlocks;

    protected void initialize(){
        this.bricks = new ArrayList<>();
        protoRotate(horizontal1, size, color);
        this.hitbox = new ArrayList<>();
        generateHitbox();
    }

    public void rotate(Board board){
        savedState = state;
        switch (state) { //turn state into next state and use appropriate wk data
            case HORIZONTAL1:
                state = State.VERTICAL1;
                protoRotateAndKick(board, vertical1, wkData0, color, size);
                break;
            case VERTICAL1:
                state = State.HORIZONTAL2;
                protoRotateAndKick(board, horizontal2, wkDataR, color, size);
                break;
            case HORIZONTAL2:
                state = State.VERTICAL2;
                protoRotateAndKick(board, vertical2, wkData2, color, size);
                break;
            default:
                state = State.HORIZONTAL1;
                protoRotateAndKick(board, horizontal1, wkDataL, color, size);
        }
    }

    //will be combined with wall kick algorithm to make protoRotateAndKick() method
    //this has no wall kicks and is a naive rotation
    private void protoRotate(int[][] data, int size, Color color){
        bricks.clear();
        for (int i = 0; i < size; ++i){
            for (int j = 0; j < size; ++j){
                if (data[i][j] == 1){
                    bricks.add(new Brick(reference.getX() + j,
                            reference.getY() + i, color));
                }
            }
        }
    }

    //simple rotation with possible wall kicks or complete cancellation of rotation
    private void protoRotateAndKick(Board board, int[][] nextConfig,
                                     int[][] wallKickData,
                                  Color color, int size){
        save();
        protoRotate(nextConfig, size, color); //initiate naive rotation
        if (!outOfBounds(board)){
            generateHitbox();
            return;
        }
        for (int i = 0; i < 4; ++i){
            move(wallKickData[i][0], wallKickData[i][1]); //trying a wall kick
            if (!outOfBounds(board)){
                generateHitbox();
                return;
            }
            move(-wallKickData[i][0], -wallKickData[i][1]); //undo unsuccessful wall kick
        }
        load();
        generateHitbox();
        state = savedState;
    }

    final public void move(int dx, int dy){
        bricks.forEach((Brick brick) -> brick.move(dx, dy));
        hitbox.forEach((Brick brick) -> brick.move(dx, dy));
        reference.move(dx, dy);
    }

    //will mainly be used to check when the block should be set and a new one be spawned
    final public boolean isColliding(Board board){
        for (Brick brick : hitbox){
            if (board.getFilledSpace().contains(brick) || brick.getY() > 19){
                return true;
            }
        }
        return false;
    }

    /*this will be used to make sure the block doesn't occupy illegal space */
    public boolean outOfBounds(Board board){
        for (Brick brick : bricks){
            if (brick.getX() < 0 || brick.getX() > 9 || brick.getY() < -3 || brick.getY() > 19){
                return true;
            }
            if (board.getFilledSpace().contains(brick)){
                return true;
            }
        }
        return false;
    }

    final public void fall(){
        move(0, 1);
    }

    final public void moveRight(Board board){
        move(1, 0);
        if (outOfBounds(board)){
            move (-1, 0);
        }
    }

    final public void moveLeft(Board board){
        move (-1, 0);
        if (outOfBounds(board)){
            move (1, 0);
        }
    }

    //method for saving the original position in case all wall kicks fail
    private void save(){
        savedReference = new Brick(reference.getX(), reference.getY());
        savedBlocks = new ArrayList<>();
        bricks.forEach((Brick brick) -> savedBlocks.add(
                new Brick(brick.getX(), brick.getY(), brick.getColor())));
    }

    //method for loading the saved position if all possible wall kicks fail
    private void load(){
        reference = savedReference;
        bricks = savedBlocks;
    }

    private void generateHitbox(){
        hitbox.clear();
        bricks.forEach((Brick brick) -> {
            Brick possibleHitbox = new Brick(brick.getX(), brick.getY() + 1);
            if (!bricks.contains(possibleHitbox)){
                hitbox.add(possibleHitbox);
            }
        });
    }

    final public BlockType getType(){
        return type;
    }

    final public List<Brick> getBricks(){
        return bricks;
    }

    final public Brick getReference(){
        return reference;
    }
}