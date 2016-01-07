package src;

import java.util.ArrayList;

public class EnemyRemovalQueue extends GameManager{
    private ArrayList<Enemy> eList;
    private Thread removalThread;
    public EnemyRemovalQueue()
    {
        eList=new ArrayList<>();
        removalThread=new Thread(new Runnable() {
            public void run() {
                while(true)
                {
                    
                }
            }
        });
    }
    public void add(Enemy e)
    {
        eList.add(e);
    }
    public Enemy getFront() throws Exception
    {
        if(eList.size()>0)return eList.get(0);
        else throw new Exception("");
    }
    
}
