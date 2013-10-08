/*
 * Copyright © 2010, 2012-2013 Martin Ueding <dev@martin-ueding.de>
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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class SimAnzeige extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	// Diese Methode gibt an, wie der Fensterinhalt zu zeichnen ist.
	protected void paintComponent(Graphics h) {
		final Graphics2D g = (Graphics2D)h;
		g.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));

		// Zuerst wird das Bild mit den Spuren eingefgt
		g.drawImage(Simulator.bild, null, 0, 0);


		// Es wird grne Farbe ausgewhlt ...
		g.setColor(Color.GREEN);

		// ... und ein Kreis ausgefllt. Dieser stellt das Teilchen dar.
		g.fillOval((int)Simulator.teilchen[0] - 3, (int)Simulator.teilchen[1] - 3, 7, 7);


		// Jetzt werden noch die verschiedenen Anzeigen gemalt
		g.setColor(Color.MAGENTA);
		g.drawString("E-Feld ist: " + (Simulator.efeld ? "AN" : "AUS") + " [E]", 10, 15);

		g.setColor(Color.CYAN);
		g.drawString("B-Feld ist: " + (Simulator.bfeld ? "AN" : "AUS") + " [B]", 10, 30);

		g.setColor(Color.red);
		g.drawString("Reset [R]", 10, 50);
		g.drawString("Init All [I]", 10, 65);
		g.drawString("Quit [Q]", 10, 80);

		if (Simulator.mode == 1) {
			g.setColor(Color.GREEN);
			g.drawString("E-Feld aus wenn komplett links", 10, 100);
		}

		if (Simulator.mode == 2) {
			g.setColor(Color.GREEN);
			g.drawString("E-Feld aus wenn generell links", 10, 100);
		}

		// Dies zeichnet die Geschwindigkeitsanzeige
		g.setColor(Color.WHITE);
		g.fillRect(getWidth() - 20, 5, 15, (int)(Math.sqrt(Math.pow(Simulator.teilchen[2], 2) + Math.pow(Simulator.teilchen[3], 2)) * 10));

	}

}
