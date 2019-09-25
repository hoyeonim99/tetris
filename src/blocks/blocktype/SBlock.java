package blocks.blocktype;

import blocks.BlockType;
import blocks.TetrisBlock;

import java.awt.*;

public class SBlock extends TetrisBlock {
    public SBlock(){
        this.horizontal1 = new int[][]{
                {0, 1, 1},
                {1, 1, 0},
                {0, 0, 0}
        };
        this.vertical1 = new int[][] {
                {0, 1, 0},
                {0, 1, 1},
                {0, 0, 1}
        };
        this.horizontal2 = new int[][] {
                {0, 0, 0},
                {0, 1, 1},
                {1, 1, 0}
        };
        this.vertical2 = new int[][] {
                {1, 0, 0},
                {1, 1, 0},
                {0, 1, 0}
        };

        this.color = Color.GREEN;
        this.type = BlockType.SBlock;
        initialize();
    }
}