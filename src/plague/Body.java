package plague;

import mvc.Utilities;
import simstation.Agent;
import simstation.Heading;

import java.awt.*;

public class Body extends Agent {
    private final int VIRULENCE;
    private final int RESISTANCE;
    private boolean infected;

    public Body(int virulence, int resistance) {
        super();
        VIRULENCE = virulence;
        RESISTANCE = resistance;
        infected = (Utilities.rng.nextInt(100)<virulence &&
                Utilities.rng.nextInt(100)>resistance);
    }

    @Override
    public void update() {
        heading = Heading.random();
        int steps = Utilities.rng.nextInt(10) + 1;
        move(steps);

        if (!infected) return;
        Body nb = (Body)world.getNeighbor(this, 8);
        if (nb != null && !nb.isInfected()) nb.exposed(VIRULENCE);
    }

    private void exposed(int virulence) {
        infected = (Utilities.rng.nextInt(100)<virulence &&
                Utilities.rng.nextInt(100)>RESISTANCE);
    }
    public boolean isInfected() { return infected; }

    @Override
    public synchronized void draw (Graphics gc) {
        gc.setColor((infected) ? Color.RED : Color.GREEN);
        gc.drawRect(xc, yc, 1, 1);
    }
}
