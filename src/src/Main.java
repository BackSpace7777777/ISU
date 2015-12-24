package src;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
    public static JFrame frame=new JFrame("Tower Defence ISU - Tyler Auslitz");
    protected static JPanel panel;
    private static GameManager gm;
    public static final boolean DEBUG=false;
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
        frame.add(panel);
        
        frame.setVisible(true);
    }
    
}
