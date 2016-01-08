package src;

import java.awt.Graphics;

public interface Enemy {
    public void draw(Graphics g);
    public int getX();
    public int getY();
    public void exec();
}
