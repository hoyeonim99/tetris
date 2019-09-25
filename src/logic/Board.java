package logic;

import blocks.Brick;

import java.util.*;

/*Board houses static variables and static methods for board control, like clearing filled
lines and bringing down upper bricks*/

public class Board {
    private final List<Brick> filledSpace = new ArrayList<>(); //every class should
    // access the list of filled up space
    private final Map<Integer, Integer> rows = new HashMap<>(); /*this map will be
    used to
    check if a row is filled. The key is the row number, and its value is the number of
     bricks in that row. */

    public Board(){

    }

    public List<Brick> getFilledSpace() {
        return filledSpace;
    }

    Map<Integer, Integer> getRows() {
        return rows;
    }

    public void initializeRows(){
        for (int i = 0; i < 20; ++i){
            rows.put(i, 0);
        }
    }

    void clearFilledRows(){ //deleting filled row + bringing down bricks
        List<Integer> clearedRows = new ArrayList<>();
        for (int row : rows.keySet()){
            if (rows.get(row) > 9){
                deleteBlocks(row);
                rows.put(row, 0);
                clearedRows.add(row);
            }
        }
        for (int cleared : clearedRows) {
            bringDownBlocks(cleared);
        }
        updateRowHashMap();
    }

    private void deleteBlocks(int row){
        List<Brick> toBeRemoved = new ArrayList<>();
        for (Brick block : filledSpace){
            if (block.getY() == row){
                toBeRemoved.add(block);
            }
        }
        filledSpace.removeAll(toBeRemoved);
        rows.put(row, 0);
    }

    private void bringDownBlocks(int row){
        for (int i = row - 1; i > 0; --i) {
            for (Brick block : filledSpace){
                if (block.getY() == i){
                    block.move(0, 1);
                }
            }
            try {
                Thread.sleep(5);
            }
            catch (InterruptedException e){
                //do nothing
            }
        }
    }

    //every time a a line is cleared, the map is to be manually updated to stay accurate
    private void updateRowHashMap(){
        rows.clear();
        initializeRows();
        for (Brick block : filledSpace){
            rows.put(block.getY(), rows.get(block.getY()) + 1);
        }
    }
}