package blocks.blocktype;

import blocks.BlockType;
import blocks.TetrisBlock;

import java.awt.*;

public class JBlock extends TetrisBlock {
    public JBlock(){
        this.horizontal1 = new int[][] {
                {1, 0, 0},
                {1, 1, 1},
                {0, 0, 0}
        };
        this.vertical1 = new int[][] {
                {0, 1, 1},
                {0, 1, 0},
                {0, 1, 0}
        };
        this.horizontal2 = new int[][]{
                {0, 0, 0},
                {1, 1, 1},
                {0, 0, 1}
        };
        this.vertical2 = new int[][] {
                {0, 1, 0},
                {0, 1, 0},
                {1, 1, 0}
        };
        this.color = Color.BLUE;
        this.type = BlockType.JBlock;
        initialize();
    }
}