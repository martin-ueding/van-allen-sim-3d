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

public class Proton extends Teilchen {



	public Proton () {
		// Farbe in der Anzeige
		farbe=Color.RED;
		bahnFarbe=Color.RED;
		vekFarbe=Color.RED;
		lFarbe=Color.YELLOW;

		// Materialkonstanten
		masse = 0.5;
		ladung = 0.1;
	}
}
