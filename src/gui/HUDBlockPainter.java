package gui;

import blocks.BlockType;
import blocks.Brick;

import java.awt.*;

class HUDBlockPainter {
    static void paintBlock(BlockType type, int x, int y, Graphics graphics){
        if (type == BlockType.IBlock){
            graphics.setColor(Color.CYAN);
            graphics.fillRect(x + 40, y, 40, 40);
            graphics.fillRect(x + 40, y + 40, 40, 40);
            graphics.fillRect(x + 40, y + 80, 40, 40);
            graphics.fillRect(x + 40, y + 120, 40, 40);

            graphics.setColor(Color.BLACK);
            graphics.fillRect(x + 40, y, 40, 2);
            graphics.fillRect(x + 40, y+ 40, 40, 2);
            graphics.fillRect(x + 40, y + 80, 40, 2);
            graphics.fillRect(x + 40, y + 120, 40, 2);
            graphics.fillRect(x + 40, y + 160, 42, 2);
            graphics.fillRect(x + 40, y, 2, 160);
            graphics.fillRect(x + 80, y, 2, 160);
        }
        else if (type == BlockType.OBlock){
            graphics.setColor(Color.YELLOW);
            graphics.fillRect(x + 40, y + 40, 40, 40);
            graphics.fillRect(x + 80, y + 40, 40, 40);
            graphics.fillRect(x + 40, y + 80, 40, 40);
            graphics.fillRect(x + 80, y + 80, 40, 40);

            graphics.setColor(Color.BLACK);
            graphics.fillRect(x + 40, y + 40, 2, 80);
            graphics.fillRect(x + 80, y + 40, 2, 80);
            graphics.fillRect(x + 120, y + 40, 2, 80);
            graphics.fillRect(x + 40, y + 40, 80, 2);
            graphics.fillRect(x + 40, y + 80, 80, 2);
            graphics.fillRect(x + 40, y + 120, 82, 2);
        }

        else if (type == BlockType.TBlock){
            graphics.setColor(Brick.purple);
            graphics.fillRect(x + 40, y + 40, 40, 40);
            graphics.fillRect(x, y + 80, 40, 40);
            graphics.fillRect(x + 40, y + 80, 40, 40);
            graphics.fillRect(x + 80, y + 80, 40, 40);

            graphics.setColor(Color.BLACK);
            graphics.fillRect(x + 40, y + 40, 40, 2);
            graphics.fillRect(x, y + 80, 120, 2);
            graphics.fillRect(x, y + 120, 122, 2);
            graphics.fillRect(x, y + 80, 2, 40);
            graphics.fillRect(x + 40, y + 40, 2, 80);
            graphics.fillRect(x + 80, y + 40, 2, 80);
            graphics.fillRect(x + 120, y + 80, 2, 40);
        }
        else if (type == BlockType.SBlock){
            graphics.setColor(Color.GREEN);
            graphics.fillRect(x + 80, y + 40, 40, 40);
            graphics.fillRect(x + 40, y + 40, 40, 40);
            graphics.fillRect(x + 40, y + 80, 40, 40);
            graphics.fillRect(x, y + 80, 40, 40);

            graphics.setColor(Color.BLACK);
            graphics.fillRect(x + 40, y + 40, 80, 2);
            graphics.fillRect(x, y + 80, 122, 2);
            graphics.fillRect(x, y + 120, 82, 2);
            graphics.fillRect(x, y + 80, 2, 40);
            graphics.fillRect(x + 40, y + 40, 2, 80);
            graphics.fillRect(x + 80, y + 40, 2, 80);
            graphics.fillRect(x + 120, y + 40, 2, 40);
        }
        else if (type == BlockType.ZBlock){
            graphics.setColor(Color.RED);
            graphics.fillRect(x, y + 40, 40, 40);
            graphics.fillRect(x + 40, y + 40, 40, 40);
            graphics.fillRect(x + 40, y + 80, 40, 40);
            graphics.fillRect(x + 80, y + 80, 40, 40);

            graphics.setColor(Color.BLACK);
            graphics.fillRect(x, y + 40, 80, 2);
            graphics.fillRect(x, y + 80, 120, 2);
            graphics.fillRect(x + 40, y + 120, 82, 2);
            graphics.fillRect(x, y + 40, 2, 40);
            graphics.fillRect(x + 40, y + 40, 2, 80);
            graphics.fillRect(x + 80, y + 40, 2, 80);
            graphics.fillRect(x + 120, y + 80, 2, 40);
        }
        else if (type == BlockType.JBlock){
            graphics.setColor(Color.BLUE);
            graphics.fillRect(x, y + 80, 40, 40);
            graphics.fillRect(x + 40, y + 80, 40, 40);
            graphics.fillRect(x + 40, y + 40, 40, 40);
            graphics.fillRect(x + 40, y, 40, 40);

            graphics.setColor(Color.BLACK);
            graphics.fillRect(x + 40, y, 40, 2);
            graphics.fillRect(x + 40, y + 40, 40, 2);
            graphics.fillRect(x, y + 80, 80, 2);
            graphics.fillRect(x, y + 120, 82, 2);
            graphics.fillRect(x, y + 80, 2, 40);
            graphics.fillRect(x + 40, y, 2, 120);
            graphics.fillRect(x + 80, y, 2, 120);
        }
        else if (type == BlockType.LBlock){
            graphics.setColor(Color.ORANGE);
            graphics.fillRect(x, y, 40, 40);
            graphics.fillRect(x, y + 40, 40, 40);
            graphics.fillRect(x, y + 80, 40, 40);
            graphics.fillRect(x + 40, y + 80, 40, 40);

            graphics.setColor(Color.BLACK);
            graphics.fillRect(x, y, 40, 2);
            graphics.fillRect(x, y + 40, 40, 2);
            graphics.fillRect(x, y + 80, 80, 2);
            graphics.fillRect(x,  y + 120, 82, 2);
            graphics.fillRect(x, y,2, 120);
            graphics.fillRect(x + 40, y, 2,120);
            graphics.fillRect(x + 80, y + 80, 2, 40);
        }
    }
}