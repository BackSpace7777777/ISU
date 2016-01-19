package src;

import java.util.ArrayList;

public class ArrayManager {
    private ArrayList<Enemy> e;
    private ArrayList<Tower> t;
    public ArrayManager()
    {
        e=new ArrayList<>();
        t=new ArrayList<>();
    }
    public int indexOfE(Enemy enemy)
    {
        return e.indexOf(enemy);
    }
    public int tSize()
    {
        return t.size();
    }
    public void tAdd(Tower tower)
    {
        t.add(tower);
    }
    public void removeT(int index)
    {
        t.remove(index);
    }
    public Tower getT(int index)
    {
        return t.get(index);
    }
    public Enemy getE(int index)
    {
        return e.get(index);
    }
    public void eAdd(Enemy enemy)
    {
        e.add(enemy);
    }
    public void removeE(Enemy index)
    {
        e.remove(index);
    }
    public void removeE(int index)
    {
        e.remove(index);
    }
    public int eSize()
    {
        return e.size();
    }
}
