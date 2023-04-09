package randomwalk;

import mvc.Model;
import simstation.SimulationFactory;

public class RandomWalkFactory extends SimulationFactory {
    public Model makeModel() { return new RandomWalkSimulation(); }
    public String getTitle() { return "Random Walks";}
}
