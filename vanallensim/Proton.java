// Copyright (c) 2010 Martin Ueding <dev@martin-ueding.de>

import java.awt.Color;

public class Proton extends Teilchen {



	public Proton() {
		// Farbe in der Anzeige
		farbe = Color.RED;
		bahnFarbe = Color.RED;
		vekFarbe = Color.RED;
		lFarbe = Color.YELLOW;

		// Materialkonstanten
		masse = 0.5;
		ladung = 0.1;

		setAp();
	}
}
