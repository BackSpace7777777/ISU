package src.Towers;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import src.Enemy;
import src.GameManager;
import src.Tower;

public class SuperShooter extends GameManager implements Tower{
    private int x,y,direction=0;
    private boolean hasBeenPlaced=false,BeingPlaced=true;
    private long sTimeStart=System.currentTimeMillis(),sTimeEnd=System.currentTimeMillis()+1000;
    public SuperShooter(int inx,int iny)
    {
        x=inx;
        y=iny;
    }
    public SuperShooter()
    {
        this(0,0);
    }
    public void draw(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x,y,50,50);
        if(direction==0)g.fillRect(x+20,y-15,10,15);
        else if(direction==1)g.fillRect(x+50,y+20,15,10);
        else if(direction==2)g.fillRect(x+20,y+50,10,15);
        else if(direction==3)g.fillRect(x-15,y+20,15,10);
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
        BeingPlaced=false;
    }
    public void setX(int i) {
        x=i;
    }
    public void setY(int i) {
        y=i;
    }
    public String getName()
    {
        return "SuperShooter";
    }
    private boolean canShoot()
    {
        if((sTimeEnd-sTimeStart)>75)return true;
        else return false;
    }
    public void exec() {
        if(canShoot())
        {
            Enemy target=super.getCloseBy(0, "SuperShooter", x, y);
            try
            {
                if(target.getX()<x)direction=3;
                else if(target.getY()>y)direction=2;
                else if(target.getX()>x)direction=1;
                else if(target.getY()<y)direction=0;
            }catch(NullPointerException ex){}
            super.killLayer(target);
            sTimeStart=System.currentTimeMillis();
        }
        sTimeEnd=System.currentTimeMillis();
    }
}
