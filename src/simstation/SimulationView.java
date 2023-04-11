package simstation;

import java.awt.Color;
import java.awt.Graphics;

import mvc.Model;
import mvc.View;

public class SimulationView extends View {

    public SimulationView(Model model) {
        super(model);
    }
    
    public void paintComponent(Graphics gc) 
    {
    	super.paintComponent(gc);
    	Color oldColor = gc.getColor();
    	
    	Simulation sim = (Simulation)model;
    	if(sim.agents != null) {
	    	for(Agent a: sim.agents) 
	    	{
	    		a.draw((Graphics)gc);
	    	}
    	}
    	
    	gc.setColor(oldColor);
    }
}
