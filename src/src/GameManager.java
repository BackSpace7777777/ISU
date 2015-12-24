package src;

import java.awt.Color;
import java.awt.Graphics;

public class GameManager extends Main{
    private Enemy e[];
    private Tower t[];
    private int[] mpx,mpy;
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
        dp(g);
        drawBoarder(g);
        if(DEBUG)drawWaypoints(g);
    }
    private void dp(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(mpx[0],mpy[0],25,150);
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
        g.clearRect(0,0,panel.getWidth(),panel.getHeight());
        g.setColor(Color.BLACK);
        g.fillRect(0,0,10,panel.getHeight());
        g.fillRect(0,0,panel.getWidth(),10);
        g.fillRect(panel.getWidth()-10,0,10,panel.getHeight());
        g.fillRect(0,panel.getHeight()-10,panel.getWidth(),10);
    }
}
