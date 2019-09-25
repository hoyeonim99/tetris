package blocks;

import java.awt.*;

public class Brick {
    private int x;
    private int y;
    private Color color = Color.BLACK;
    public final static Color purple = new Color(112, 39, 195);

    public Brick(int x, int y){
        this.x = x;
        this.y = y;
    }

    Brick(int x, int y, Color color){
        this(x, y);
        this.color = color;
    }

    public Color getColor(){
        return color;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void move(int dx, int dy){
        x += dx;
        y += dy;
    }

    @Override
    public boolean equals(Object object){
        if (object == null){
            return false;
        }
        if (this.getClass() != object.getClass()){
            return false;
        }
        Brick compare = (Brick) object;
        return (this.x == compare.x && this.y == compare.y);
    }

    @Override
    public int hashCode(){
        return ("" + x).hashCode() + y;
    }
}