package simstation;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

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

        for (String s: new String[]{"Start", "Suspend", "Resume", "Stop", "Stats"}) {
            JButton b = new JButton(s);
            b.addActionListener(this);
            JPanel p = new JPanel();
            p.add(b);
            controlPanel.add(p);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        super.actionPerformed(ae);
        if (ae.getActionCommand().equals("Open")) {
            Simulation sim = (Simulation) model;
            sim.makeThreads();
            sim.suspend();
        }
    }
    
    public static void main(String[] args) {
    	AppPanel app = new SimulationPanel(new SimulationFactory());
    	app.display();
	}
}
