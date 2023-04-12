package plague;

import mvc.Model;
import simstation.SimulationFactory;

public class PlagueFactory extends SimulationFactory {
    public Model makeModel(){
        return new PlagueSimulation();
    }
    public String getTitle(){
        return "Plague Simulation";
    }
}
