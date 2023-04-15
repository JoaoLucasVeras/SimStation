package pd;

import java.io.Serializable;

import mvc.Utilities;

abstract class Strategy implements Serializable {
	Prisoner p;
	abstract boolean cooperate();
	void setPrisoner (Prisoner p) { this.p = p; }
}

class Cooperate extends Strategy {
	@Override
	boolean cooperate() { return  true; }
}

class RandomlyCooperate extends Strategy {
	@Override
	boolean cooperate() {
		int rnd = Utilities.rng.nextInt(2);
		return (rnd==0);
	}
}

class Cheat extends Strategy {
	@Override
	boolean cooperate() { return false; }
}

class TitTat extends Strategy {
	@Override
	boolean cooperate() { return p.cooperate(); }
}
