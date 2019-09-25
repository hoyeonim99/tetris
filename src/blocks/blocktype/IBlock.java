package blocks.blocktype;

import blocks.BlockType;
import blocks.TetrisBlock;

import java.awt.*;
import java.util.*;

public class IBlock extends TetrisBlock {
    public IBlock(){
        this.horizontal1 = new int[][] {
                {0, 0, 0, 0},
                {1, 1, 1, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        this.vertical1 = new int[][] {
                {0, 0, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 0},
        };
        this.horizontal2 = new int[][] {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 1, 1, 1},
                {0, 0, 0, 0},
        };
        this.vertical2 = new int[][] {
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 0, 0},
        };

        //IBlock wall kicking data is unique. Will overwrite the default wall kick data.
        this.wkData0 = new int[][]{
                {-2, 0},
                {1, 0},
                {-2, 1},
                {1, 2}
        };
        this.wkDataR = new int[][] {
                {-1, 0},
                {2, 0},
                {-1, -2},
                {2, 1}
        };
        this.wkData2 = new int[][] {
                {2, 0},
                {-1, 0},
                {2, -1},
                {-1, 2}
        };
        this.wkDataL = new int[][] {
                {1, 0},
                {-2, 0},
                {1, 2},
                {2, -1}
        };
        this.color = Color.CYAN;
        this.size = 4;
        this.type = BlockType.IBlock;
        this.bricks = new ArrayList<>();
        this.initialize();
    }
}