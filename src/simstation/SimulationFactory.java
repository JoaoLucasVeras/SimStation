package simstation;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;

public class SimulationFactory implements AppFactory {

    @Override
    public Model makeModel() {
        return null;
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
        return null;
    }

    @Override
    public String[] getHelp() {
        return new String[0];
    }

    @Override
    public String about() {
        return null;
    }
}
