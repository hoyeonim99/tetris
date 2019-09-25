package blocks.blocktype;

import blocks.BlockType;
import blocks.TetrisBlock;
import logic.Board;
import blocks.Brick;

import java.awt.*;

public class OBlock extends TetrisBlock {

    public OBlock(){
        this.horizontal1 = new int[][]{
                {1, 1},
                {1, 1},
        };
        this.size = 2;
        this.color = Color.YELLOW;
        this.type = BlockType.OBlock;
        this.reference = new Brick(4, -2); //overriding
        initialize();
    }

    @Override
    public void rotate(Board board){
        //do nothing
    }
}