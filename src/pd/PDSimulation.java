package pd;

import mvc.AppPanel;
import mvc.Utilities;
import plague.Body;
import simstation.Agent;
import simstation.Simulation;
import simstation.SimulationPanel;

public class PDSimulation extends Simulation{

	public void populate() 
	{
		for(int i = 0; i < 40; i++) 
		{
			Prisoner p;
			if(i < 10) {
				
				addAgent(p = new Prisoner(new Cooperate()));
				p.setStrat(1);
			}
			else if(i < 20) {
				addAgent(p = new Prisoner(new RandomlyCooperate()));
				p.setStrat(2);
			}
			else if(i < 30) {
				addAgent(p = new Prisoner(new Cheat()));
				p.setStrat(3);
			}
			else {
				addAgent(p = new Prisoner(new TitTat()));
				p.setStrat(4);
			}
		}
	}
	
	public void stats() 
	{
		int avgCoop = 0;
		int avgCheat = 0;
		int avgTitTat = 0;
		int avgRandom = 0;
		for (Agent a: agents) {
            Prisoner p = (Prisoner)a;
            if(p.getStrat() == 1) 
            {
            	avgCoop += p.getFit();
            }
            if(p.getStrat() == 2) 
            {
            	avgCheat += p.getFit();
            
            }
            if(p.getStrat() == 3) 
            {
            	avgTitTat += p.getFit();
            	
            }
            if(p.getStrat() == 4) 
            {
            	avgRandom += p.getFit();
            	
            }
        }
		

        Utilities.inform(new String[] {
                "#agents = " + agents.size(),
                "clock = " + clock,
                "Avg. Cooperate Strategy = " + (double)avgCoop/agents.size()/4,
                "Avg. Cheat Strategy = " + (double)avgCheat/agents.size()/4,
                "Avg. Randomly Cooperate Strategy = " + (double)avgRandom/agents.size()/4,
                "Avg. Tit4Tat Strategy = " + (double)avgTitTat/agents.size()/4
        });
	}
	
	public static void main(String[] args) {
		AppPanel panel = new SimulationPanel(new PDFactory());
		panel.display();
	}
}
