import java.io.PrintStream;
import java.util.ArrayList;

import java.lang.*;
import flockbase.Position;
import flockbase.Bird;
import flockbase.Flock;

public class Bird031 extends Bird
{
    private int maxSpeed = 10;
    private boolean amLeader;
    private Position pos;
    private Position target;
    private Flock flock;
  
    public Bird031()
    {
        super();
        amLeader = false;
    }
  
    public String getName()
    {
        return "Bird031";
    }

    public int checkCollision(int dir1, int dir2)
    {
        ArrayList<Bird> fList = this.getFlock().getBirds();
        int currX = this.getPos().getX();
        int currY = this.getPos().getY();
        int tempX, tempY;
        int diffX, diffY;
        for(int i=0; i<fList.size(); i++)
        {
            tempX = fList.get(i).getPos().getX();
            tempY = fList.get(i).getPos().getY();
            if(tempX!=currX || tempY!=currY)
            {
                diffX = Math.abs(tempX - (currX + dir1*maxSpeed));
                diffY = Math.abs(tempY - (currY + dir2*maxSpeed));
                if(diffX<20 && diffY<20)
                    return 1;
            }
        }
        return 0;
    }

    protected void updatePos()
    {
        Position currPos = getPos();
        int currX = currPos.getX();
        int currY = currPos.getY();
        int tarX = getTarget().getX();
        int tarY = getTarget().getY();
        int diffX = tarX - currX;
        int diffY = tarY - currY;
        int boundX = 1000 - currX;
        int boundY = 1000 - currY;
        int check;
        
        if(!amLeader)
        {
            this.setTarget(this.getFlock().getLeader().getPos().getX(), this.getFlock().getLeader().getPos().getY());
        }

        else if(amLeader)
        {
            if(diffX==0 && diffY>0)
                this.setPos(currX, currY+maxSpeed);
            else if(diffX==0 && diffY<0)
                this.setPos(currX, currY-maxSpeed);
            else if(diffX>0 && diffY==0)
                this.setPos(currX+maxSpeed, currY);
            else if(diffX<0 && diffY==0)
                this.setPos(currX-maxSpeed, currY);
            else if(diffX>0 && diffY>0)
                this.setPos(currX+maxSpeed, currY+maxSpeed);
            else if(diffX>0 && diffY<0)
                this.setPos(currX+maxSpeed, currY-maxSpeed);
            else if(diffX<0 && diffY>0)
                this.setPos(currX-maxSpeed, currY+maxSpeed);
            else if(diffX<0 && diffY<0)
                this.setPos(currX-maxSpeed, currY-maxSpeed);
        }

        if(boundX == 0 || boundX<10)
        {
            check = this.checkCollision(1, 0);
            if(check==1)
                this.setPos(currX+maxSpeed, currY+maxSpeed);
            else
                this.setPos(currX+maxSpeed, currY);
        }

        else if(boundX == 1000 || boundX>990)
        {
            check = this.checkCollision(-1, 0);
            if(check==1)
                this.setPos(currX-maxSpeed, currY-maxSpeed);
            else
                this.setPos(currX-maxSpeed, currY);
        }

        else if(boundY == 0 || boundY<10)
        {
            check = this.checkCollision(0, 1);
            if(check==1)
                this.setPos(currX+maxSpeed, currY+maxSpeed);
            else
                this.setPos(currX, currY+maxSpeed);
        }

        else if(boundY == 1000 || boundY<990)
        {
            check = this.checkCollision(0, -1);
            if(check==1)
                this.setPos(currX-maxSpeed, currY-maxSpeed);
            else
                this.setPos(currX, currY-maxSpeed);
        }

        if(diffX > 0 && diffY > 0)
        {
            check = this.checkCollision(1, 1);
            if(check==1)
            {
                this.setPos(currX - maxSpeed, currY + maxSpeed);
            }
            else
                this.setPos(currX+maxSpeed, currY+maxSpeed);
        }

        else if(diffX > 0 && diffY < 0)
        {
            check = this.checkCollision(1, -1);
            if(check==1)
            {
                this.setPos(currX - maxSpeed, currY - maxSpeed);
            }
            else
                this.setPos(currX+maxSpeed, currY-maxSpeed);
        }

        else if(diffX < 0 && diffY > 0)
        {
            check = this.checkCollision(-1, 1);
            if(check==1)
            {
                this.setPos(currX + maxSpeed,currY + maxSpeed);
            }
            else
                this.setPos(currX-maxSpeed, currY+maxSpeed);
        }

        else if(diffX < 0 && diffY < 0)
        {
            check = this.checkCollision(-1, -1);
            if(check==1)
            {
                this.setPos(currX + maxSpeed, currY - maxSpeed);
            }
            else
                this.setPos(currX-maxSpeed, currY-maxSpeed);
        }

        else if(diffX == 0 && diffY > 0)
        {
            check = this.checkCollision(0, 1);
            if(check==1)
            {
                this.setPos(currX + maxSpeed, currY);
            }
            else
                this.setPos(currX, currY+maxSpeed);
        }
        
        else if(diffX == 0 && diffY < 0)
        {
            check = this.checkCollision(0, -1);
            if(check==1)
            {
                this.setPos(currX + maxSpeed, currY);
            }
            else
                this.setPos(currX, currY-maxSpeed);
        }

        else if(diffX < 0 && diffY == 0)
        {
            check = this.checkCollision(-1, 0);
            if(check==1)
            {
                this.setPos(currX, currY + maxSpeed);
            }
            else
                this.setPos(currX-maxSpeed, currY);
        }

        else if(diffX > 0 && diffY == 0)
        {
            check = this.checkCollision(1, 0);
            if(check==1)
            {
                this.setPos(currX, currY - maxSpeed);
            }
            else
                this.setPos(currX+maxSpeed, currY);
        }
    }

    public void becomeLeader()
    {
        System.out.println("lead " + this);
        amLeader = true;
    }
  
    public void retireLead()
    {
        System.out.println("retire " + this);
        amLeader = false;
    }
}