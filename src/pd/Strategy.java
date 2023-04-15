package pd;

import java.io.Serializable;

import mvc.Utilities;

abstract class Strategy implements Serializable{
	Prisoner p;
	Strat s;
	abstract boolean cooperate();
	void setPrisoner (Prisoner p) 
	{
		this.p = p;
	}
}

class Cooperate extends Strategy
{
	@Override
	boolean cooperate() {
		// TODO Auto-generated method stub
		return  true;
	}
	
}

class RandomlyCooperate extends Strategy
{
	@Override
	boolean cooperate() {
		// TODO Auto-generated method stub
		int rnd = Utilities.rng.nextInt(2);
		
		if(rnd == 0) 
		{
			return true;
		}
		return false;
	}
	
}

class Cheat extends Strategy
{
	@Override
	boolean cooperate() {
		// TODO Auto-generated method stub
		return false;
	}
	
}

class TitTat extends Strategy
{
	@Override
	boolean cooperate() {
		// TODO Auto-generated method stub
		if(p.cooperate()) 
		{
			return true;
		}
		return false;
	}
	
}
