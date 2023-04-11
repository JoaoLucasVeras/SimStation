package simstation;

import mvc.Utilities;

public enum Heading {
    N,
	S,
	E,
	W,
	NE,
	NW,
	SE,
	SW;

	public static Heading random() { return Heading.values()[Utilities.rng.nextInt(Heading.values().length)]; }

}
