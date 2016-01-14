package src;

import java.awt.Graphics;

public interface Enemy {
    public void draw(Graphics g);
    public int getX();
    public int getY();
    public void setX(int i);
    public void setY(int i);
    public int getLevel();
    public boolean isThrough();
    public void exec();
}
