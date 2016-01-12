package src.Enemies;

import java.awt.Color;
import java.awt.Graphics;
import src.Enemy;
import src.GameManager;

public class LevelEnemy extends GameManager implements Enemy{
    private int x,y,level,speed,arrayPos=0,index;
    private boolean isAlive;
    private int[] xTargets,yTargets;
    public LevelEnemy(int level,int[] xtarg,int[] ytarg)
    {
        //this.index=index;
        xTargets=xtarg;
        yTargets=ytarg;
        x=xTargets[0];
        y=yTargets[0];
        this.level=level;
        isAlive=true;
    }
    public void draw(Graphics g) 
    {
        if(level==0)
        {
            g.setColor(Color.RED);
            speed=1;
        }
        else if(level==1)
        {
            g.setColor(Color.BLUE);
            speed=1;
        }
        else if(level==2)
        {
            g.setColor(Color.GREEN);
            speed=1;
        }
        else if(level==3)
        {
            g.setColor(Color.YELLOW);
            speed=2;
        }
        else if(level==4)
        {
            g.setColor(Color.PINK);
            speed=2;
        }
        else if(level==5)
        {
            g.setColor(Color.ORANGE);
            speed=2;
        }
        g.fillRect(x,y,25,25);
        if(x==xTargets[arrayPos] && y==yTargets[arrayPos])
        {
            if(arrayPos==11)
                super.enemyThrough(this);
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
    public void exec() 
    {
        
    }
    
}