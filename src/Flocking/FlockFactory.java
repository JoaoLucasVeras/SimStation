package Flocking;

import mvc.Model;
import simstation.SimulationFactory;

public class FlockFactory extends SimulationFactory{

    public Model makeModel(){
        return new FlockSimulation();
    }

    public String getTitle(){
        return "Flock Simulation";
    }
}
