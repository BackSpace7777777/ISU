package src;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import src.Enemies.HeavyEnemy;
import src.Enemies.LevelEnemy;
import src.Towers.Explosive;
import src.Towers.Shooter;
import src.Towers.SuperShooter;

public class GameManager extends Main{
    private final int[] mpx,mpy;
    private boolean spawnEnemies=false,onMouseToPlace=false,noMoreTime=false;
    public static int money=500,health=100;
    private long start=System.currentTimeMillis(),end=0,hstart=start,hend=0,waveTimeStart=System.currentTimeMillis(),waveTimeNow;
    private long[] waves=new long[6];
    private int amountOfExplosive=0,amountOfSuper=0,amountOfShooter=0;
    public static ArrayManager am=new ArrayManager();
    private static long killed=0,started,ended;
    public GameManager()
    {
        started=System.currentTimeMillis();
        Random r=new Random();
        //Time for each wave to add some randomness
        /*-------*/
        waves[0]=r.nextInt(10000)+20000;
        waves[1]=r.nextInt(20000)+50000;
        waves[2]=r.nextInt(20000)+100000;
        waves[3]=r.nextInt(20000)+200000;
        waves[4]=r.nextInt(20000)+300000;
        waves[5]=r.nextInt(20000)+500000;
        /*----------*/
        //Map Coords
        /*-----------*/
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
        //End of map Coords
        /*-----------*/
    }
    public void draw(Graphics g)
    {
        //Paint draw method of the game manager that gets called by the main class
        ended=System.currentTimeMillis();//Setting the ending time so that it tells you how long you have played
        addingEnemies();//Checks to see if it needs to add enemies
        g.clearRect(0,0,panel.getWidth(),panel.getHeight());//Clears the screen
        dp(g);//Draws the blue path
        drawEnemies(g);//Draws the enemies and runs the enemy logic
        drawTowers(g);//Draws the Towers and runs the logic
        drawMenu(g);//Draws menu where the buttons and information is
        drawBoarder(g);//Draws the black border at the edges
        if(DEBUG)drawWaypoints(g);//For debuging purposes
    }
    public boolean isAliveTotal()//Checking health for the main class
    {
        if(health<=0)
        {
            ended=System.currentTimeMillis();
            return false;
        }
        else return true;
    }
    public long timeLasted()//Getting the time that player lasted
    {
        return ended-started;
    }
    public long getNumberKilled()//Getting number of kills
    {
        return killed;
    }
    private void addingEnemies()
    {
        if(spawnEnemies && noMoreTime==false)
        {//Checks what wave it is based on the values that were generated in the constructor
            if(waveTimeNow-waveTimeStart<waves[0])
            {
                if(end-start>750)//Checks the time and if the difference is 750 or greater is spawns one
                {
                    start=System.currentTimeMillis();//Sets the time to now so that the difference is 0 again
                    am.eAdd(new LevelEnemy(2,mpx,mpy));//Adds one to the array manager
                }
            }
            else if(waveTimeNow-waveTimeStart<waves[1])
            {
                if(end-start>500)
                {
                    start=System.currentTimeMillis();
                    am.eAdd(new LevelEnemy(2,mpx,mpy));
                }
            }
            else if(waveTimeNow-waveTimeStart<waves[2])
            {
                if(end-start>450)
                {
                    start=System.currentTimeMillis();
                    am.eAdd(new LevelEnemy(3,mpx,mpy));
                }
            }
            else if(waveTimeNow-waveTimeStart<waves[3])
            {
                if(end-start>400)
                {
                    start=System.currentTimeMillis();
                    am.eAdd(new LevelEnemy(4,mpx,mpy));
                }
            }
            else if(waveTimeNow-waveTimeStart<waves[4])
            {
                if(end-start>300)
                {
                    start=System.currentTimeMillis();
                    am.eAdd(new LevelEnemy(5,mpx,mpy));
                }
                if(hend-hstart>5000)
                {
                    hstart=System.currentTimeMillis();
                    am.eAdd(new HeavyEnemy(mpx,mpy));
                }
            }
            else if(waveTimeNow-waveTimeStart<waves[5])
            {
                if(end-start>100)
                {
                    start=System.currentTimeMillis();
                    am.eAdd(new LevelEnemy(5,mpx,mpy));
                }
                if(hend-hstart>2500)
                {
                    hstart=System.currentTimeMillis();
                    am.eAdd(new HeavyEnemy(mpx,mpy));
                }
            }
            else
            {
                noMoreTime=true;
            }
            waveTimeNow=System.currentTimeMillis();
            end=System.currentTimeMillis();
        }
        if(noMoreTime)//If there are no more values in the array it goes to this because there are no more preset waves
        {
            if(end-start>10)
            {
                start=System.currentTimeMillis();
                am.eAdd(new LevelEnemy(5,mpx,mpy));
            }
            if(hend-hstart>500)
            {
                hstart=System.currentTimeMillis();
                am.eAdd(new HeavyEnemy(mpx,mpy));
            }
            waveTimeNow=System.currentTimeMillis();
            end=System.currentTimeMillis();
            hend=end;
        }
    }
    private void drawMenu(Graphics g)
    {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(5,630,700,200);//Draws the grey rectange at the bottem
        g.setColor(Color.LIGHT_GRAY);
        if(x>20&&x<20+50&&y>640&&y<640+50&&mouseDown==false)g.setColor(Color.GRAY);//Checks for the mouse position
        else if(x>20&&x<20+50&&y>640&&y<640+50&&mouseDown)//Checks if the mouse is clicked
        {
            g.setColor(Color.GREEN);
            buttonPressed("ShooterSpawner");//Button logic method call
        }
        g.fillRect(20,640,50,50);
        g.setColor(Color.LIGHT_GRAY);
        if(x>20&&x<20+50&&y>700&&y<700+50&&mouseDown==false)g.setColor(Color.GRAY);
        else if(x>20&&x<20+50&&y>700&&y<700+50&&mouseDown)
        {
            g.setColor(Color.GREEN);
            buttonPressed("SuperShooterSpawned");
        }
        g.fillRect(20,700,50,50);
        g.setColor(Color.LIGHT_GRAY);
        if(x>80&&x<80+50&&y>640&&y<640+50&&mouseDown==false)g.setColor(Color.GRAY);
        else if(x>80&&x<80+50&&y>640&&y<640+50&&mouseDown)
        {
            g.setColor(Color.GREEN);
            buttonPressed("ExplosiveSpawned");
        }
        g.fillRect(80,640,50,50);
        g.setColor(Color.WHITE);//Information text like lives and money
        g.drawString("You have " + money + " monies",(frame.getWidth()/2)-75,650);
        g.drawString("You have " + health + " lives",(frame.getWidth()/2)-70,675);
        g.setColor(Color.BLACK);//Button text
        g.drawString("Shooter",22,658);
        g.drawString("$"+(175+(5*amountOfShooter)),24,670);
        g.drawString("Super",22,717);
        g.drawString("Shooter",22,730);
        g.drawString("$"+(1250+(600*amountOfSuper)),22,742);
        g.drawString("Explosive",80,658);
        g.drawString("Tower",82,670);
        g.drawString("$"+(850+(500*amountOfExplosive)),82,681);
    }
    private void drawEnemies(Graphics g)
    {
        if(am.eSize()>0)
        {
            for(int i=0;i<am.eSize();i++)
            {
                if(am.getE(i).isThrough())//Checks if enemy is at the end
                {
                    am.removeE(i);
                    health--;
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
            if(onMouseToPlace)//Ifor when you press one of the buttons it follows your mouse until you press it again
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
                am.getT(i).draw(g);//Draws then runs the tower logic
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
    private void drawWaypoints(Graphics g)//Debug
    {
        for(int i=0;i<mpx.length;i++)
        {
            g.setColor(Color.GREEN);
            g.fillRect(mpx[i],mpy[i],25,25);
            g.setColor(Color.BLACK);
            g.drawString("Point " + i,mpx[i],mpy[i]);
        }
    }
    private void drawBoarder(Graphics g)//Draws the border
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
            if(money-(175+(5*amountOfShooter))>=0)//Checks if you have money before it takes it away
            {
                money-=(175+(5*amountOfShooter));
                mouseDown=false;
                am.tAdd(new Shooter());
                amountOfShooter++;
                onMouseToPlace=true;
            }
            spawnEnemies=true;
        }
        else if(name.equals("SuperShooterSpawned")&&onMouseToPlace==false)
        {
            if(money-(1250+(600*amountOfSuper))>=0)
            {
                money-=(1250+(600*amountOfSuper));
                mouseDown=false;
                am.tAdd(new SuperShooter());
                amountOfSuper++;
                onMouseToPlace=true;
            }
            spawnEnemies=true;
        }
        else if(name.equals("ExplosiveSpawned")&&onMouseToPlace==false)
        {
            if(money-(850+(500*amountOfExplosive))>=0)
            {
                money-=(850+(500*amountOfExplosive));
                mouseDown=false;
                am.tAdd(new Explosive());
                amountOfExplosive++;
                onMouseToPlace=true;
            }
            spawnEnemies=true;
        }
    }
    protected Enemy getCloseBy(int rangeMod,String towerName,int x,int y)//For Tower logic
    {//Gets the enemy in range and at the front most position of the array
        Enemy closeBy=null;//Target that gets returned
        int tX=x+25,tY=y+25;
        if(am.eSize()>0)
        for(int i=0;i<am.eSize();i++)
        {
            int tempX=am.getE(i).getX()+12,tempY=am.getE(i).getY()+12;
            if(towerName.equals("Shooter"))//Checking what tower it is
            {
                if(tempX>=tX-(100+rangeMod)&&tempX<=tX+(100+rangeMod)&&tempY>=tY-(100+rangeMod)&&tempY<=tY+(100+rangeMod))
                {
                    closeBy=am.getE(i);//sets the target as this
                    break;
                }
            }
            else if(towerName.equals("SuperShooter"))
            {
                if(tempX>=tX-(250+rangeMod)&&tempX<=tX+(250+rangeMod)&&tempY>=tY-(250+rangeMod)&&tempY<=tY+(250+rangeMod))
                {
                    closeBy=am.getE(i);
                    break;
                }
            }
            else if((towerName.equals("Explosive")))
            {
                if(tempX>=tX-(250+rangeMod)&&tempX<=tX+(250+rangeMod)&&tempY>=tY-(250+rangeMod)&&tempY<=tY+(250+rangeMod))
                {
                    closeBy=am.getE(i);
                    break;
                }
            }
        }
        return closeBy;
    }
    protected Enemy[] splash(int rad,int x,int y)//Used only by the explosive tower
    {//Used to get whats in a radius
        Enemy[] inside;//The return array
        ArrayList<Enemy> alist=new ArrayList<>();//Temp arraylist used for collecting
        for(int i=0;i<am.eSize();i++)
        {
            Enemy temp=am.getE(i);
            if(temp.getX()>(x-rad)&&temp.getX()<(x+rad)&&temp.getY()>(y-rad)&&temp.getY()<(y+rad))//Checks if enemy is in radius
            {
                alist.add(temp);
            }
        }
        inside=new Enemy[alist.size()];//Defines the return array as the same size as the collecting arraylist
        for(int i=0;i<alist.size();i++)
        {
            inside[i]=alist.get(i);//inserts the array of enemies to be returned
        }
        return inside;
    }
    protected void killLayer(Enemy in)//Used by the towers to remove a layer
    {
        for(int i=0;i<am.eSize();i++)
        {
            Enemy comp=am.getE(i);//Temp enemy to compare to the others
            if(comp==in)
            {
                money++;
                killed++;
                am.getE(am.indexOfE(comp)).kill();//Removes layer
            }
        }
    }
    protected void removeEnemy(Enemy index)
    {
        am.removeE(index);
    }
}
