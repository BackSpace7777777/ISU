package src.Towers;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import src.Enemy;
import src.GameManager;
import src.Tower;

public class Explosive extends GameManager implements Tower{
    private int x,y,direction=0;
    private boolean hasBeenPlaced=false,BeingPlaced=true;
    private long sTimeStart=System.currentTimeMillis(),sTimeEnd=System.currentTimeMillis()+1000;
    public Explosive(int inx,int iny)
    {
        x=inx;
        y=iny;
    }
    public Explosive()
    {
        this(0,0);
    }
    public void draw(Graphics g) {
        g.setColor(Color.RED);
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
        return "Explosive";
    }
    private boolean canShoot()
    {
        if((sTimeEnd-sTimeStart)>800)return true;
        else return false;
    }
    public void exec() {
        if(canShoot())
        {
            Enemy target=super.getCloseBy(-50, "Explosive", x, y);
            Enemy[] targets;
            try
            {
                if(target.getX()<x)direction=3;
                else if(target.getY()>y)direction=2;
                else if(target.getX()>x)direction=1;
                else if(target.getY()<y)direction=0;
                Random r=new Random();
                targets=super.splash((r.nextInt(75)+75),target.getX(),target.getY());
                for(int i=0;i<targets.length;i++)
                {
                    super.killLayer(targets[i]);
                }
            }catch(NullPointerException ex){}
            
            sTimeStart=System.currentTimeMillis();
        }
        sTimeEnd=System.currentTimeMillis();
    }
}
