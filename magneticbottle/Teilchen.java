/*
 * Copyright (c) 2010, 2012 Martin Ueding <dev@martin-ueding.de>
 * 
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

import java.awt.Color;

public abstract class Teilchen {
	// Die Position des Teilchens
	Vektor position;
	
	// Die Geschwindigkeit des Teilchens
	Vektor v;
	
	// Farbe in der Anzeige
	Color farbe, bahnFarbe, vekFarbe, lFarbe;
	
	// Materialkonstanten
	double masse;
	double ladung;
	
	static int iter = 500;
	
	void bewegen () {
		for (int i = 0; i < iter/4; i++) {
			// Kraft bestimmen
			Vektor f = v.kreuzprodukt(MagnetischeFlascheSimulator.bfeld(position)).skalarmul(ladung/iter);
			
			// Beschleunigung (a = F/m) auf die Geschwindigkeit addieren
			v = v.add(f.skalarmul(1/masse));
			
			// Bewegung aufrechnen
			position = position.add(v.skalarmul(1.0/iter));
		}
	}

}
