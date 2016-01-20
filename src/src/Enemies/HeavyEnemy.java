package src.Enemies;

import java.awt.Color;
import java.awt.Graphics;
import src.Enemy;
import src.GameManager;

public class HeavyEnemy extends GameManager implements Enemy{
    private int x,y,level,speed,arrayPos=0,index;
    private boolean isAlive,isThrough=false;
    private int[] xTargets,yTargets;
    public HeavyEnemy(int[] xtarg,int[] ytarg)
    {
        //this.index=index;
        xTargets=xtarg;
        yTargets=ytarg;
        x=xTargets[0];
        y=yTargets[0];
        this.level=255;
        isAlive=true;
    }
    public void draw(Graphics g) 
    {
        g.setColor(new Color(level,150,150));
        g.fillRect(x,y,25,25);
        if(x==xTargets[arrayPos] && y==yTargets[arrayPos])
        {
            if(arrayPos==11)
                isThrough=true;
            else
                arrayPos++;
        }
        else
        {
            if(x>xTargets[arrayPos])x-=speed;
            else if(x<xTargets[arrayPos])x+=speed;
            if(y>yTargets[arrayPos])y-=speed;
            else if(y<yTargets[arrayPos])y+=speed;
        }
    }
    public boolean isThrough()
    {
        return isThrough;
    }
    public boolean isAlive()
    {
        return isAlive;
    }
    public void setY(int i)
    {
        y=i;
    }
    public void setX(int i)
    {
        x=i;
    }
    public int getX() 
    {
        return x;
    }
    public int getY() 
    {
        return y;
    }
    public int getLevel()
    {
        return level;
    }
    public void kill()
    {
        if(level==0)
        {
            super.removeEnemy(this);
        }
        else level--;
    }
    public void exec() 
    {
        
    }
}
