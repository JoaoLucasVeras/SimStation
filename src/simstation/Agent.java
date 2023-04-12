package simstation;

import java.io.Serializable;

import mvc.Utilities;
import java.awt.Color;
import java.awt.Graphics;

public abstract class Agent implements Serializable, Runnable {

    protected String name;
    protected int xc;
    protected int yc;
    protected Heading heading;
    protected boolean suspended = false;
    protected boolean stopped = false;
    transient protected Thread myThread;
    protected Simulation world;

    public Agent(){
        xc = Utilities.rng.nextInt(250);
        yc = Utilities.rng.nextInt(250);
    }

    public void setWorld(Simulation s){
        world = s;
    }
    
    public int getX(){
        return xc;
    }

    public int getY(){
        return yc;
    }

    public synchronized boolean isStopped(){
        return stopped;
    }

    public synchronized boolean isSuspended(){
        return suspended;
    }

    public synchronized void checkSuspended() {
        try{
            while(!stopped && suspended) {
                wait();
                suspended = false;
            }
        } catch (InterruptedException e) {
            Utilities.error(e);
        }
    }

    public synchronized void join() {
        try{
            if(myThread != null) {
                myThread.join();
            }
        } catch(InterruptedException e) {
            Utilities.error(e);
        }
    }

    @Override
    public void run() {
        myThread = Thread.currentThread();
        while ( !isStopped()){
            try {
                update();
                Thread.sleep(20);
                checkSuspended();
            } catch (InterruptedException e){
                Utilities.error(e);
            }
        }
    }

    public synchronized void suspend(){
        suspended = true;
    }

    public synchronized void resume(){
        notify();  
    }

    public synchronized void stop(){
        stopped = true;
    }

    public abstract void update();
    
    public synchronized void move(int steps) {
	
		switch(heading) {
			case N:{
				yc -= steps;
				if (yc < 0) { yc += 250; }
				break;
			}
			case S:{
				yc += steps;
				if (yc > 250) { yc -= 250; }
				break;
			}
			case E: {
				xc += steps;
				if (xc > 250) { xc -= 250; }
				break;
			}
			case W:{
				xc -= steps;
				if (xc < 0) { xc += 250; }
				break;
			}
			case NW:{
				yc -= steps;
				xc -= steps;
				if (yc < 0) { yc += 250; }
				if (xc < 0) { xc += 250; }
				break;
			}
			case NE:{
				yc -= steps;
				xc += steps;
				if(yc < 0) { yc += 250; }
				if (xc > 250) { xc -= 250; }
				break;
			}
			case SW:{
				yc += steps;
				xc -= steps;
				if (yc > 250) { yc -= 250; }
				if (xc < 0) { xc += 250; }
				break;
			}
			case SE:{
				yc += steps;
				xc += steps;
				if (yc > 250) { yc -= 250; }
				if (xc > 250) { xc -= 250; }
				break;
			}
		}
		world.changed();
	}

    public synchronized void draw (Graphics gc) {
		gc.setColor(Color.blue);
		gc.drawRect(xc, yc, 1, 1);
	}
}
