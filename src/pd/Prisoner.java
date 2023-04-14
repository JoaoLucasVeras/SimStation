package pd;

import java.awt.Color;
import java.awt.Graphics;

import mvc.Utilities;
import simstation.Agent;
import simstation.Heading;

class Prisoner extends Agent {
	  private int fitness = 0;
	  private boolean partnerCheated = false;
	  private int stratType = 0;
	  Strategy strategy;
	  
	  public Prisoner(Strategy s) 
	  {
		  this.strategy = s;
		  s.setPrisoner(this);
	  }
	  public boolean cooperate() 
	  {
		  return partnerCheated;
	  }
	  
	  public void updateFitness(int amount) 
	  {
		  fitness += amount;
	  }
	  
	  public int getFit() 
	  {
		  return fitness;
	  }
	  
	  public int getStrat() 
	  {
		  return stratType;
	  }
	  public void setStrat(int num)
	  {
		  stratType = num;
	  }
	@Override
	public void update() {
		// TODO Auto-generated method stub
		heading = Heading.random();
        int steps = Utilities.rng.nextInt(10) + 1;
        move(steps);
        
        Prisoner p = (Prisoner)world.getNeighbor(this, 8);
        if(p != null) 
        {
        	boolean p1 = this.strategy.cooperate();
        	boolean p2 = p.strategy.cooperate();
        	if(p1 && p2) 
        	{
        		this.updateFitness(3);
        		p.updateFitness(3);
        		
        		this.partnerCheated = false;
        		p.partnerCheated = false;
        	}
        	else if(p1 && !p2) 
        	{
        		p.updateFitness(5);
        		this.partnerCheated = true;
        	}
        	else if(!p1 && p2) 
        	{
        		this.updateFitness(5);
        		p.partnerCheated = true;
        	}
        	else 
        	{
        		this.updateFitness(1);
        		p.updateFitness(1);
        		this.partnerCheated = true;
        		p.partnerCheated = true;
        	}
        	
        }
	}
	
	@Override
	public synchronized void draw(Graphics gc) 
	{
		if(stratType == 1) {gc.setColor(Color.red);}
		if(stratType == 2) {gc.setColor(Color.blue);}
		if(stratType == 3) {gc.setColor(Color.green);}
		if(stratType == 4) {gc.setColor(Color.pink);}
		gc.drawRect(xc, yc, 2, 2);
	}
}
