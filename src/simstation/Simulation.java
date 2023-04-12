package simstation;

import mvc.Model;

import mvc.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Simulation extends Model {
    transient private Timer timer;
    protected List<Agent> agents;
    protected int clock;

    public Simulation() {
        super();
        agents = new ArrayList<>();
    }

    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new ClockUpdater(), 1000, 1000);
    }

    private class ClockUpdater extends TimerTask {
        public void run(){
            clock++;
        }
    }

    private void stopTimer() {
        timer.cancel();
        timer.purge();
    }

    public void start() {
        clock = 0;
        agents.clear();
        this.populate();
        startTimer();
        for (Agent a:agents) {
            Thread thread = new Thread(a);
            thread.start();
        }
    }
    public void suspend() { stopTimer(); for (Agent a: agents) a.suspend(); }
    public void resume() { startTimer(); for (Agent a: agents) a.resume(); }
    public void stop() { stopTimer(); for (Agent a: agents) a.stop(); }

    public Agent getNeighbor(Agent a, double radius) {

        int rand = Utilities.rng.nextInt(agents.size());
		for (int i = rand; i < agents.size(); i++) {
			Agent temp = agents.get(i);
			double xdif = Math.abs(a.getX()-temp.getX());
			double ydif = Math.abs(a.getY()-temp.getY());
			if (Math.hypot(xdif,ydif) <= radius) {
				if(!temp.equals(a)) {
					return temp;
				}
			}
		}
		for ( int i = 0 ; i < rand ; i++) {
			Agent temp = agents.get(i);
			double xdif = Math.abs(a.getX()-temp.getX());
			double ydif = Math.abs(a.getY()-temp.getY());
			if (Math.hypot(xdif,ydif) <= radius) {
				if(!temp.equals(a)) {
					return temp;
				}
			}
		}
		return null;
    }
    public void populate() {}

    public void addAgent(Agent a) {
        agents.add(a);
        a.setWorld(this);
    }

    public void stats(){
        Utilities.inform(new String[] {"# of agents: " + agents.size(), "Clock: " + clock });
    }

}
