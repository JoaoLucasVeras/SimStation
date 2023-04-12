package Flocking;

import mvc.Utilities;
import simstation.Agent;
import simstation.Heading;

public class Bird extends Agent{
    private int speed;

    public Bird(){
        speed = Utilities.rng.nextInt(5) +1;
        heading = Heading.random();
    }
    
    public void update() {
		Bird nb = (Bird) world.getNeighbor(this, 20);
		if(nb != null) {
			this.speed = nb.speed;
			this.heading = nb.heading;
		}
        super.move(speed);
	}

    public int getSpeed(){
        return speed;
    }
}
