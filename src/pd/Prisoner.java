package pd;

import java.awt.Color;
import java.awt.Graphics;

import mvc.Utilities;
import simstation.Agent;
import simstation.Heading;

class Prisoner extends Agent {
	  private int fitness = 0;
	  private boolean partnerCheated = false;
	  private Strat stratType;
	  Strategy strategy;
	  
	  public Prisoner(Strategy s, Strat strat) 
	  {
		  this.strategy = s;
		  s.setPrisoner(this);
		  this.stratType = strat;
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
	  
	  public Strat getStrat() 
	  {
		  return stratType;
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
			p.partnerCheated = false;
        	}
        	else if(!p1 && p2) 
        	{
        		this.updateFitness(5);
        		p.partnerCheated = true;
			this.partnerCheated = false;
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
		switch(stratType)
		{
			case coop:
			{
				gc.setColor(Color.red);
				break;
			}
			case cheat:{
				gc.setColor(Color.blue);
				break;
			}
			case rand:
			{
				gc.setColor(Color.green);
				break;
			}
			case titTat:
			{
				gc.setColor(Color.pink);
				break;
			}
		}
		gc.drawRect(xc, yc, 2, 2);
	}
}
