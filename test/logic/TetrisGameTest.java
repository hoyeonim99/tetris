package logic;

import blocks.TetrisBlock;
import org.junit.Test;

import static org.junit.Assert.*;

public class TetrisGameTest {

    @Test
    public void testInitialCollisionTest() {
        Board board = new Board();
        TetrisGame game = new TetrisGame(board);
        assertFalse(game.getCurrentBlock().isColliding(board));
        assertFalse(game.getCurrentBlock().outOfBounds(board));
    }

    @Test
    public void afterDropCollisionTest() {
        Board board = new Board();
        TetrisGame game = new TetrisGame(board);
        while (!game.getCurrentBlock().isColliding(board)){
            game.getCurrentBlock().fall();
        }
        assertTrue(game.getCurrentBlock().isColliding(board));
        assertTrue(game.getShadow().isColliding(board));
    }

    @Test
    public void glueBlockTest() {
        Board board = new Board();
        board.initializeRows();
        TetrisGame game = new TetrisGame(board);
        while (!game.setAndReturnCurrentBlockColliding()){
            game.getCurrentBlock().fall();
        }
        TetrisBlock current = game.getCurrentBlock();
        assertEquals(current, game.getCurrentBlock());
        assertFalse(game.getGameOver());

        game.glueBlock();
        game.updateShadowAndPreviewAndCurrentBlock();
        assertNotEquals(current, game.getCurrentBlock());
        assertFalse(game.getGameOver());
        assertEquals(4, game.getBoard().getFilledSpace().size());
    }

    @Test
    public void shadowCollisionTest(){
        Board board = new Board();
        board.initializeRows();
        TetrisGame game = new TetrisGame(board);
        assertTrue(game.getShadow().isColliding(board));
        while (!game.setAndReturnCurrentBlockColliding()){
            game.getCurrentBlock().fall();
        }
        game.glueBlock();
        game.updateShadowAndPreviewAndCurrentBlock();
        assertTrue(game.getShadow().isColliding(board));

        game.getCurrentBlock().rotate(board);
        assertTrue(game.getShadow().isColliding(board));

        game.getCurrentBlock().moveLeft(board);
        assertTrue(game.getShadow().isColliding(board));
    }
}