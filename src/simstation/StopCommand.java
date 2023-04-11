package simstation;

import mvc.Command;
import mvc.Model;

public class StopCommand extends Command {

    public StopCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() throws Exception {
        if (!(model instanceof Simulation)) {
	        throw new Exception("Model must instantiate Simulation");
        }
        ((Simulation)model).stop();
    }
}
