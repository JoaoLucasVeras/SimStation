package simstation;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;

public class SimulationFactory implements AppFactory {

    @Override
    public Model makeModel() {
        return new Simulation();
    }

    @Override
    public View makeView(Model model) {
        return new SimulationView(model);
    }

    @Override
    public String[] getEditCommands() {
        return new String[]{"Start", "Suspend", "Resume", "Stop", "Stats"};
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object source) {
        if (type.equals("Start")) return new StartCommand(model);
        if (type.equals("Suspend")) return new SuspendCommand(model);
        if (type.equals("Resume")) return new ResumeCommand(model);
        if (type.equals("Stop")) return new StopCommand(model);
        if (type.equals("Stats")) return new StatsCommand(model);
        return null;
    }

    @Override
    public String getTitle() {
        return "SimStation Simulation";
    }

    @Override
    public String[] getHelp() {
        return new String[] {
            "click Start to begin a new simulation",
            "click Suspend to temporarily pause the simulation",
            "click Resume to continue the simlation",
            "click Stop to terminate the simulation",
            "click Stats to get the statistics of the simulation"
    };
    }

    @Override
    public String about() {
        return "Simstation made by: Joao Lucas Veras, Nick Nguyen, Kyaw Soe Han ";
    }
}
