package simstation;

import mvc.Model;

import mvc.Utilities;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Simulation extends Model {
    private Timer timer;
    protected List<Agent> agents;
    protected int clock;

    private void startTimer(){
        timer = new Timer();
        timer.scheduleAtFixedRate(new ClockUpdater(), 1000, 1000);
    }

    private class ClockUpdater extends TimerTask{
        public void run(){
            clock++;
        }
    }

    private void stopTimer(){
        timer.cancel();
        timer.purge();
    }

    public void start() { for (Agent a: agents) {
        clock = 0;
        startTimer();
        agents.clear();
        this.populate();
        Thread thread = new Thread(a);
        thread.start();
    } }
    public void suspend() { stopTimer(); for (Agent a: agents) a.suspend(); }
    public void resume() { startTimer(); for (Agent a: agents) a.resume(); }
    public void stop() { stopTimer(); for (Agent a: agents) a.stop(); }

    public Agent getNeighbor(Agent agent, double radius) {
        int start = Utilities.rng.nextInt(agents.size());
        int index = start;
        do {
            Agent target = agents.get(index);
            if (Math.hypot(target.getY()-agent.getY(),
                    target.getX()-agent.getX()) < radius) {
                if(!target.equals(agent)){   
                return target;
                }
            }
            if (index==agents.size()) { index = 0; }
        } while (++index != start);
        return null;
    }
    public void populate() {}

    public void addAgent(Agent a) { agents.add(a); }

    public void stats(){
        Utilities.inform(new String[] {"# of agents: " + agents.size(), "Clock: " + clock });
    }

}
