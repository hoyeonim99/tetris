package blocks.blocktype;

import blocks.BlockType;
import blocks.Brick;
import blocks.TetrisBlock;

public class TBlock extends TetrisBlock {
    public TBlock(){
        this.horizontal1 = new int[][] {
                {0, 1, 0},
                {1, 1, 1},
                {0, 0, 0}
        };
        this.vertical1 = new int[][] {
                {0, 1, 0},
                {0, 1, 1},
                {0, 1, 0}
        };
        this.horizontal2 = new int[][] {
                {0, 0, 0},
                {1, 1, 1},
                {0, 1, 0}
        };
        this.vertical2 = new int[][] {
                {0, 1, 0},
                {1, 1, 0},
                {0, 1, 0}
        };

        this.color = Brick.purple;
        this.type = BlockType.TBlock;
        initialize();
    }
}