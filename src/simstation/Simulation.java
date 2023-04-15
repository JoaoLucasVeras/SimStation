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
        if (timer == null) {
            timer = new Timer();
            timer.scheduleAtFixedRate(new ClockUpdater(), 1000, 1000);
        }
    }

    private class ClockUpdater extends TimerTask {
        public void run(){
            clock++;
        }
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
    }

    public void start() {
        for (Agent a: agents) a.stop();
        clock = 0;
        agents.clear();
        this.populate();
        startTimer();
        makeThreads();
    }

    public void makeThreads() {
        for (Agent a:agents) {
            Thread thread = new Thread(a);
            thread.start();
        }
    }

    public void suspend() {
        stopTimer();
        for (Agent a: agents) a.suspend();
    }

    public void resume() {
        startTimer();
        for (Agent a: agents) a.resume();
    }

    public void stop() {
        stopTimer();
        for (Agent a: agents) a.stop();
    }

    public Agent getNeighbor(Agent a, double radius) {
        int rand = Utilities.rng.nextInt(agents.size());
        for (int i=rand+1; i%agents.size()!=rand; i++) {
			Agent temp = agents.get(i%agents.size());
            if (temp.equals(a)) continue;
			int xdif = a.getX()-temp.getX();
			int ydif = a.getY()-temp.getY();
			if (Math.abs(Math.hypot(xdif,ydif)) <= radius) return temp;
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
