package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
    public static JFrame frame=new JFrame("Tower Defence ISU - Tyler Auslitz");
    protected static JPanel panel;
    private static GameManager gm;
    public static final boolean DEBUG=false;
    public static int x,y;
    public static boolean menuOpen=false,mouseDown=false,readMenuKey=true;
    public static void main(String[] args) {
        //=============Setting frame properties==================
        frame.setSize(640,840);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(3);
        //=======================================================
        gm=new GameManager();
        gm.startTime();
        panel=new JPanel()
        {
            public void paintComponent(Graphics g)//This is where everything gets called around 75 frames per second
            {
                super.paintComponent(g);
                if(gm.isAliveTotal())
                    gm.draw(g);//Draws everything
                else
                    loss(g);//Draws the information at the end screen
                System.out.println(gm.timeLasted());
                try {
                    Thread.sleep(13);//Delay to get around 75 frames per second
                    //  1000ms/75fps=about 13
                } catch (InterruptedException ex) {}
                repaint();//Makes sure that the paint method gets called again
            }
        };
        panel.setBounds(0,0,frame.getWidth()-5,frame.getHeight()-30); 
        panel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                x=e.getX();
                y=e.getY();
            }
        }); 
        panel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mouseDown=true;
            }
            public void mouseReleased(MouseEvent e) {
                mouseDown=false;
            }
        });
        frame.add(panel);
        frame.setVisible(true);
    }
    private static void loss(Graphics g)
    {
        g.clearRect(0,0,panel.getWidth(),panel.getHeight());
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
        g.setColor(Color.BLACK);
        g.drawString("You lasted " + gm.timeLasted() + " seconds",100,100);
        g.drawString("You killed " + gm.getNumberKilled() + " enemies",100,130);
    }
    public static int getMouseX()
    {
        return x;
    }
    public static int getMouseY()
    {
        return y;
    }
}
