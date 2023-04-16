package Flocking;

import mvc.AppPanel;
import mvc.Utilities;
import simstation.Agent;
import simstation.Simulation;
import simstation.SimulationPanel;

public class FlockSimulation extends Simulation {
    public void populate() {
        for(int i = 0 ; i < 40 ; i++) {
            addAgent(new Bird());
        }
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new FlockFactory());
        panel.display();
    }

    public void stats() {
		int s1 = 0;
		int s2 = 0;
		int s3 = 0;
		int s4 = 0;
		int s5 = 0;
		for(Agent a : agents) {
			Bird b = (Bird) a;
			int speed = b.getSpeed();
			if(speed == 1) s1++;
			else if (speed == 2) s2++;
			else if (speed == 3) s3++;
			else if (speed == 4) s4++;
			else if (speed == 5) s5++;
		}
		Utilities.inform(new String[] {
				"#agents = " + agents.size(),
                "clock = " + clock,
				"#birds @ speed 1 = " + s1,
				"#birds @ speed 2 = " + s2,
				"#birds @ speed 3 = " + s3,
				"#birds @ speed 4 = " + s4,
				"#birds @ speed 5 = " + s5
		});
	}
}
