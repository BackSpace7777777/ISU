package src.Towers;

import java.awt.Color;
import java.awt.Graphics;
import src.Enemy;
import src.GameManager;
import src.Main;
import src.Tower;

public class Shooter extends GameManager implements Tower{
    private int x,y;
    private boolean hasBeenPlaced=false;
    private long sTimeStart=System.currentTimeMillis(),sTimeEnd=System.currentTimeMillis()+1000;
    public Shooter(int inx,int iny)
    {
        x=inx;
        y=iny;
    }
    public Shooter()
    {
        this(0,0);
    }
    public void draw(Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(x,y,50,50);
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
    public void setX(int i) {
        x=i;
    }
    public void setY(int i) {
        y=i;
    }
    public String getName()
    {
        return "Shooter";
    }
    private boolean canShoot()
    {
        if((sTimeEnd-sTimeStart)>500)return true;
        else return false;
    }
    public void exec() {
        if(canShoot())
        {
            Enemy target=super.getCloseBy(0, "Shooter", x, y);
            
            super.killLayer(target);
            sTimeStart=System.currentTimeMillis();
        }
        sTimeEnd=System.currentTimeMillis();
    }
}
