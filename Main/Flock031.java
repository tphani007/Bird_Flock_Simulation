//import Bird;
import flockbase.Flock;
import flockbase.Bird;

import java.util.ArrayList;

public class Flock031 extends Flock
{
    private ArrayList<Bird> birds = new ArrayList();
    Bird lead = null;
  
    public Flock031()
    {}
  
    public void addBird(Bird b)
    {
        birds.add(b);
        b.setFlock(this);
    }
  
    public void setLeader(Bird leader)
    {
        if(lead != null)
        {
            lead.retireLead();
        }
        lead = leader;
        lead.becomeLeader();
    }
  
    public ArrayList<Bird> getBirds()
    {
        return birds;
    }

    public Bird getLeader()
    {
        return lead;
    }

    public Flock split(int pos)
    {
        Bird tempBird = birds.get(pos);
        Flock031 tempFlock = new Flock031();
        
        tempBird.becomeLeader();
        tempFlock.addBird(tempBird);
        tempFlock.setLeader(tempBird);
        for(int i=0; i<pos; i++)
            tempFlock.addBird(birds.get(i));
        for(int i=0; i<pos; i++)
            birds.remove(i);
        
        return tempFlock;
    }
  
    public void joinFlock(flockbase.Flock f)
    {
        for(int i=0; i<f.getBirds().size(); i++)
        {
            this.getBirds().add(f.getBirds().get(i));
            f.getBirds().get(i).setFlock(this);
            f.getBirds().get(i).retireLead();
        }

    }
}