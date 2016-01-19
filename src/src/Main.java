package src;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        frame.setSize(640,840);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(3);
        gm=new GameManager();
        panel=new JPanel()
        {
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                gm.draw(g);
                try {
                    Thread.sleep(13);
                } catch (InterruptedException ex) {}
                repaint();
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
    public static int getMouseX()
    {
        return x;
    }
    public static int getMouseY()
    {
        return y;
    }
}
