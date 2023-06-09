package pd;

import mvc.Model;
import simstation.SimulationFactory;

public class PDFactory extends SimulationFactory{

	public Model makeModel() 
	{
		return new PDSimulation();
	}
	
	public String getTitle() 
	{
		return "Prisoner's Dilemma";
	}
	public String about() { return "Prisoner's Dilemma Simulation made by : Joao Lucas Veras"; }
}
