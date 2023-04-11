package simstation;

import mvc.Command;
import mvc.Model;

public class SuspendCommand extends Command {

    public SuspendCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() throws Exception {
        if (!(model instanceof Simulation)) {
	        throw new Exception("Model must instantiate Simulation");
        }
        ((Simulation)model).suspend();
    }
}
