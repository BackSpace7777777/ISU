package src;

import java.awt.Graphics;
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
    public static void main(String[] args) {
        frame.setSize(640,640);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(3);
        panel=new JPanel()
        {
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                gm.draw(g);
                repaint();
            }
        };
        gm=new GameManager();
        panel.setBounds(0,0,frame.getWidth()-5,frame.getHeight()-30); 
        panel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                x=e.getX();
                y=e.getY();
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
