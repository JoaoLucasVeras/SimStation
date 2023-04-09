package simstation;

import mvc.Model;

import mvc.Utilities;

import java.util.List;

public class Simulation extends Model {
    protected List<Agent> agents;
    protected int clock;

    public void start() { for (Agent a: agents) {
        agents.clear();
        this.populate();
        Thread thread = new Thread(a);
        thread.start();
    } }
    public void suspend() { for (Agent a: agents) a.suspend(); }
    public void resume() { for (Agent a: agents) a.resume(); }
    public void stop() { for (Agent a: agents) a.stop(); }
    public Agent getNeighbor(Agent agent, double radius) {
        int start = Utilities.rng.nextInt(agents.size());
        int index = start;
        do {
            Agent target = agents.get(index);
            if (Math.hypot(target.getY()-agent.getY(),
                    target.getX()-agent.getX()) < radius) {
                return target;
            }
            if (index==agents.size()) { index = 0; }
        } while (++index != start);
        return null;
    }
    public void populate() {}

    public void addAgent(Agent a) { agents.add(a); }


}
