package src.MenuItems;

import java.awt.Color;
import java.awt.Graphics;
import src.GameManager;

public class Button extends GameManager{
    private int tx,ty;
    private String name;
    public Button(int x,int y,String name)
    {
        this.tx=x;
        this.ty=y;
        this.name=name;
    }
    public void draw(Graphics g)
    {
        g.setColor(Color.DARK_GRAY);
        if(x>20&&x<20+50&&y>630&&y<630+50&&mouseDown==false)g.setColor(Color.LIGHT_GRAY);
        else if(x>20&&x<20+50&&y>630&&y<630+50&&mouseDown)
        {
            g.setColor(Color.GREEN);
            super.buttonPressed(name);
        }
        g.fillRect(x,y,50,50);
    }
    
}
