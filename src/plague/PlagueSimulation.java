package plague;

import mvc.AppPanel;
import mvc.Utilities;
import simstation.Agent;
import simstation.Simulation;
import simstation.SimulationPanel;

public class PlagueSimulation extends Simulation {
    public static final int VIRULENCE  = 20; // % chance of infection
    public static final int RESISTANCE = 80; // % chance of resisting infection

    public void populate() {
        for(int i = 0 ; i < 40 ; i++){
            addAgent(new Body(VIRULENCE, RESISTANCE));
        }
        Body b = (Body)agents.get(0);
        b.forceInfected(true);
    }

    public void stats() {
        int hosts = 0;

        for (Agent a: agents) {
            Body b = (Body)a;
            if (b.isInfected()) hosts++;
        }

        Utilities.inform(new String[] {
                "#agents = " + agents.size(),
                "clock = " + clock,
                "%infected = " + ((double)Math.round(1000*((double)hosts)/agents.size()))/10
        });
    }

    public static void main(String[] args){
        AppPanel panel = new SimulationPanel(new PlagueFactory());
        panel.display();
    }
}
