package simstation;

import java.util.Random;

public enum Heading {
    N,
	S,
	E,
	W,
	NE,
	NW,
	SE,
	SW;

	public static Heading random() {
		return Heading.values()[new Random().nextInt(Heading.values().length)];
	}

}
