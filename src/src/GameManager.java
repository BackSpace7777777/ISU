package src;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import src.Enemies.LevelEnemy;
import src.Towers.Shooter;

public class GameManager extends Main{
    private int[] mpx,mpy;
    private boolean spawnEnemies=false,onMouseToPlace=false;
    //private Thread enemySpawner;
    public int health=100;
    private static int eSize;
    private long start=System.currentTimeMillis(),end=0;
    public static ArrayManager am=new ArrayManager();
    public GameManager()
    {
        mpx=new int[12];
        mpy=new int[12];
        mpx[0]=20;
        mpy[0]=-50;
        mpx[1]=mpx[0];//20
        mpy[1]=400;
        mpx[2]=100;
        mpy[2]=mpy[1];//20
        mpx[3]=mpx[2];//100
        mpy[3]=200;
        mpx[4]=250;
        mpy[4]=mpy[3];//200
        mpx[5]=mpx[4];//250
        mpy[5]=500;
        mpx[6]=100;
        mpy[6]=mpy[5];//500
        mpx[7]=mpx[6];//100
        mpy[7]=550;
        mpx[8]=400;
        mpy[8]=mpy[7];//600
        mpx[9]=mpx[8];//400
        mpy[9]=100;
        mpx[10]=550;
        mpy[10]=mpy[9];//100
        mpx[11]=mpx[10];//550
        mpy[11]=630;
//        enemySpawner=new Thread(new Runnable() {
//            public void run() {
//                while(true)
//                {
//                    if(spawnEnemies)
//                    {
//                        e.add(new LevelEnemy(3,mpx,mpy));
//                        eSize=e.size();
//                    }
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException ex) {}
//                }
//            }
//        });
    }
    public void draw(Graphics g)
    {
        if(end-start>500 && spawnEnemies)
        {
            start=System.currentTimeMillis();
            am.eAdd(new LevelEnemy(3,mpx,mpy));
        }
        end=System.currentTimeMillis();
        g.clearRect(0,0,panel.getWidth(),panel.getHeight());
        dp(g);
        drawEnemies(g);
        drawTowers(g);
        drawMenu(g);
        drawBoarder(g);
        if(DEBUG)drawWaypoints(g);
    }
    private void drawMenu(Graphics g)
    {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(5,630,700,200);
        g.setColor(Color.LIGHT_GRAY);
        if(x>20&&x<20+50&&y>640&&y<640+50&&mouseDown==false)g.setColor(Color.GRAY);
        else if(x>20&&x<20+50&&y>640&&y<640+50&&mouseDown)
        {
            g.setColor(Color.GREEN);
            buttonPressed("ShooterSpawner");
        }
        g.fillRect(20,640,50,50);
    }
    private void drawEnemies(Graphics g)
    {
        if(am.eSize()>0)
        {
            for(int i=0;i<am.eSize();i++)
            {
                if(am.getE(i).isThrough())
                {
                    am.removeE(i);
                }
                else
                {
                    am.getE(i).draw(g);
                }
            }
        }
    }
    private void drawTowers(Graphics g)
    {
        try
        {
            if(onMouseToPlace)
            {
                am.getT(am.tSize()-1).setX(x);
                am.getT(am.tSize()-1).setY(y);
                if(mouseDown)onMouseToPlace=false;
            }
        }
        catch(NullPointerException ex){}
        if(am.tSize()>0)
        {
            for(int i=0;i<am.tSize();i++)
            {
                am.getT(i).draw(g);
                am.getT(i).exec();
            }
        }
    }
    private void dp(Graphics g)
    {
        g.setColor(Color.BLUE);
        g.fillRect(mpx[0],mpy[0],25,mpy[1]+75);//0-1
        g.fillRect(mpx[1],mpy[1],(mpx[2]-mpx[1])+25,25);//1-2
        g.fillRect(mpx[3],mpy[3],25,mpy[2]-mpy[3]);//2-3
        g.fillRect(mpx[3],mpy[3],mpx[4]-mpx[3],25);//3-4
        g.fillRect(mpx[4],mpy[4],25,325);//4-5
        g.fillRect(mpx[6],mpy[6],mpx[5]-mpx[6],25);//5-6
        g.fillRect(mpx[6],mpy[6],25,mpy[7]-mpy[6]);//6-7
        g.fillRect(mpx[7],mpy[7],325,25);//7-8
        g.fillRect(mpx[9],mpy[9],25,mpy[8]-mpy[9]);//8-9
        g.fillRect(mpx[9],mpy[9],mpx[10]-mpx[9],25);//9-10
        g.fillRect(mpx[10],mpy[10],25,mpy[11]-mpy[10]);//10-11
    }
    private void drawWaypoints(Graphics g)
    {
        for(int i=0;i<mpx.length;i++)
        {
            g.setColor(Color.GREEN);
            g.fillRect(mpx[i],mpy[i],25,25);
            g.setColor(Color.BLACK);
            g.drawString("Point " + i,mpx[i],mpy[i]);
        }
    }
    private void drawBoarder(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,10,panel.getHeight());
        g.fillRect(0,0,panel.getWidth(),10);
        g.fillRect(panel.getWidth()-10,0,10,panel.getHeight());
        g.fillRect(0,panel.getHeight()-10,panel.getWidth(),10);
    }
    private void buttonPressed(String name)
    {
        if(name.equals("ShooterSpawner")&&onMouseToPlace==false)
        {
            mouseDown=false;
            am.tAdd(new Shooter());
            onMouseToPlace=true;
            spawnEnemies=true;
        }
    }
    protected Enemy getCloseBy(int rangeMod,String towerName,int x,int y)
    {
        Enemy closeBy=null;
        int tX=x+25,tY=y+25;
        if(am.eSize()>0)
        for(int i=0;i<am.eSize();i++)
        {
            int tempX=am.getE(i).getX()+12,tempY=am.getE(i).getY()+12;
            if(towerName.equals("Shooter"))
            {
                if(tempX>=tX-(100+rangeMod)&&tempX<=tX+(100+rangeMod)&&tempY>=tY-(100+rangeMod)&&tempY<=tY+(100+rangeMod))
                {
                    closeBy=am.getE(i);
                    break;
                }
            }
        }
        return closeBy;
    }
    protected void killLayer(Enemy in)
    {
        for(int i=0;i<am.eSize();i++)
        {
            Enemy comp=am.getE(i);
            if(comp==in)
            {
                am.getE(am.indexOfE(comp)).kill();
            }
        }
    }
    protected void removeEnemy(Enemy index)
    {
        am.removeE(index);
    }
}
