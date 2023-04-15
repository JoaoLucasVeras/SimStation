package pd;

import mvc.AppPanel;
import mvc.Utilities;
import simstation.Agent;
import simstation.Simulation;
import simstation.SimulationPanel;

public class PDSimulation extends Simulation {

	public void populate() {
		for(int i = 0; i < 10; i++) {
			addAgent(new Prisoner(new Cooperate(), Strat.coop));
			addAgent(new Prisoner(new RandomlyCooperate(), Strat.rand));
			addAgent(new Prisoner(new Cheat(), Strat.cheat));
			addAgent(new Prisoner(new TitTat(), Strat.titTat));
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
            if (p.getStrat() == Strat.coop) { avgCoop += p.getFit(); }
            else if (p.getStrat() == Strat.cheat) { avgCheat += p.getFit(); }
            else if (p.getStrat() == Strat.titTat) { avgTitTat += p.getFit(); }
            else if (p.getStrat() == Strat.rand) { avgRandom += p.getFit(); }
        }

        Utilities.inform(new String[] {
                "#agents = " + agents.size(),
                "clock = " + clock,
                "Avg. Cooperate Strategy = " + String.format("%.2f",(double)avgCoop/agents.size()/4),
                "Avg. Cheat Strategy = " + String.format("%.2f", (double)avgCheat/agents.size()/4),
                "Avg. Randomly Cooperate Strategy = " + String.format("%.2f",(double)avgRandom/agents.size()/4),
                "Avg. Tit4Tat Strategy = " + String.format("%.2f", (double)avgTitTat/agents.size()/4)
        });
	}
	
	public static void main(String[] args) {
		AppPanel panel = new SimulationPanel(new PDFactory());
		panel.display();
	}
}
