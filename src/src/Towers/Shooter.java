package src.Towers;

import java.awt.Color;
import java.awt.Graphics;
import src.Main;
import src.Tower;

public class Shooter implements Tower{
    private int x,y;
    private boolean hasBeenPlaced=false;
    public Shooter()
    {
        
    }
    public void draw(Graphics g) {
        if(hasBeenPlaced)
        {
            drawM(g);
        }
        else
        {
            x=Main.getMouseX();
            y=Main.getMouseY();
            drawM(g);
        }
    }
    private void drawM(Graphics g)
    {
        g.setColor(Color.RED);
        g.fillRect(x,y,10,10);
    }
    public int getX() {
        return y;
    }
    public int getY() {
        return x;
    }
    public boolean isPlaced()
    {
        return hasBeenPlaced;
    }
    public void place()
    {
        hasBeenPlaced=true;
    }
}
