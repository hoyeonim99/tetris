package blocks;

import blocks.blocktype.*;

import java.util.function.Supplier;

public enum BlockType {

    IBlock(IBlock::new), JBlock(JBlock::new), LBlock(LBlock::new), OBlock(OBlock::new),
    SBlock(SBlock::new), ZBlock(ZBlock::new), TBlock(TBlock::new);

    private Supplier<? extends TetrisBlock> supplier;

    BlockType(Supplier<? extends TetrisBlock> supplier){
        this.supplier = supplier;
    }

    public TetrisBlock createBlock(){
        return supplier.get();
    }
}