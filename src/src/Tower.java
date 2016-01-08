package src;

import java.awt.Graphics;

public interface Tower {
    public void draw(Graphics g);
    public int getX();
    public int getY();
    public void setX(int i);
    public void setY(int i);
    public void place();
    public boolean isPlaced();
    public String getName();
    public void exec();
}
