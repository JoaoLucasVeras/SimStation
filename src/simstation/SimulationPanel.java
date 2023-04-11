package simstation;

import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import mvc.AppFactory;
import mvc.AppPanel;

public class SimulationPanel extends AppPanel {

    public SimulationPanel(AppFactory factory) {
        super(factory);
        Simulation simulation = (Simulation) model;
        simulation.addPropertyChangeListener(this);
        controlPanel.setLayout(new GridLayout(5,1));
        
        JButton start = new JButton("Start");
        start.addActionListener(this);
        JPanel p = new JPanel();
        p.add(start);
        controlPanel.add(p);
        
        JButton suspend = new JButton("Suspend");
        suspend.addActionListener(this);
        p = new JPanel();
        p.add(suspend);
        controlPanel.add(p);
        
        JButton resume = new JButton("Resume");
        resume.addActionListener(this);
        p = new JPanel();
        p.add(resume);
        controlPanel.add(p);
        
        JButton stop = new JButton("Stop");
        stop.addActionListener(this);
        p = new JPanel();
        p.add(stop);
        controlPanel.add(p);
        
        JButton stats = new JButton("Stats");
        stats.addActionListener(this);
        p = new JPanel();
        p.add(stats);
        controlPanel.add(p);
        
    }
    
    //I don't know if we are going to need this
    public void propertyChange(PropertyChangeEvent evt) 
    {
    	super.propertyChange(evt);
    	Simulation sim = (Simulation) model;
    }
    
    public static void main(String[] args) {
    	AppPanel app = new SimulationPanel(new SimulationFactory());
    	app.display();
	}
}
