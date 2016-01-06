package src;

import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.Towers.Shooter;

public class GameManager extends Main{
    private Enemy e[];
    private Tower t[];
    private int[] mpx,mpy;
    private Tower placingTower;
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
    }
    public void draw(Graphics g)
    {
        g.clearRect(0,0,panel.getWidth(),panel.getHeight());
        if(Main.menuOpen)drawMenu(g);
        else
        {
            dp(g);
            drawTowers(g);
            drawBoarder(g);
            if(DEBUG)drawWaypoints(g);
        }
    }
    private void drawMenu(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,panel.getWidth(),panel.getHeight());
        g.setColor(Color.BLACK);
        if(x>5&&x<55&&y>5&&y<55&&mouseDown==false)g.setColor(Color.DARK_GRAY);
        else if(x>5&&x<55&&y>5&&y<55&&mouseDown)
        {
            placingTower=new Shooter();
            menuOpen=false;
        }
        g.fillRect(5,5,50,50);
        g.setColor(Color.WHITE);
        g.drawString("Shooter",10,20);
        g.drawString("Tower",10,35);
    }
    private void drawTowers(Graphics g)
    {
        if(placingTower.isPlaced())
        {
            
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
}
