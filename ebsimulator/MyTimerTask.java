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
import java.util.TimerTask;

public class MyTimerTask extends TimerTask {

	// Die Gamma Werte sind Korrekturwerte, so ist das E-Feld etwas
	// schwcher als das B-Feld. Diese Werte knnen verndert werden,
	// wenn sich das Teilchen generell schneller bewegen soll oder strker
	// auf das magnetische Feld reagieren soll
	final double gammaX = 1.0, gammaY = 1.0;
	final double gammaVX = 15.0, gammaVY = 15.0;
	final double gammaE = 0.25, gammaB = 0.5;

	// Damit die Bahnen nicht kantig wirken werden pro Zyklus 10 Punkte
	// berechnet. Wird dieser Wert erhht, werden die Kurven noch glatter
	final int iter = 10;

	@Override
	// Diese Methode wird bei jedem Zyklus des Timers aufgerufen
	public void run() {
		Graphics h = Simulator.bild.getGraphics();
		final Graphics2D g = (Graphics2D)h;
		g.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));

		for (int i = 0; i < iter; i++) {
			// Die alte Position der Teilchen wird gespeichert, damit spter
			// eine Linie zwischen neuer und alter Position gezeichnet werden
			// kann
			double xalt = Simulator.teilchen[0];
			double yalt = Simulator.teilchen[1];

			// Die Anweisung fr den ersten Modus. Das E-Feld soll an sein,
			// wenn sich das Teilchen nach rechts bewegt.
			if (Simulator.mode == 1) {
				Simulator.efeld = Simulator.teilchen[2] >= 0;

			}
			// Wenn der Modus 2 ist, dann soll das E-Feld auch an und
			// ausgeschaltet werden, jedoch ist die Regel hier etwas
			// komplizierter.
			else if (Simulator.mode == 2) {
				// Ist das E-Feld schon an, und bewegt sich das Teilchen dann
				// nach oben, soll das Feld abgeschaltet werden.
				if (Simulator.efeld && Simulator.teilchen[3] > 0) {
					Simulator.efeld = false;
				}
				// Ist das E-Feld aus, und bewegt sich das Teilchen nach unten,
				// soll es eingeschaltet werden.
				else if (!Simulator.efeld && Simulator.teilchen[3] <= 0) {
					Simulator.efeld = true;
				}
			}

			// Ist das E-Feld an (also die Variable efeld = "wahr"), dann soll
			// auf die X-Geschwindigkeitskomponente des Teilchens ein Betrag
			// addiert werden.
			if (Simulator.efeld) {
				Simulator.teilchen[2] += gammaE  / iter;
			}

			// Magnetische Kraft
			// Da die Kraft immer senkrecht zur Bewegung geht, addiert man auf
			// die X-Geschwindigkeit einen Teil von der Y-Geschwindigkeit und
			// zieht von der Y-Geschw. einen Teil von der X-Geschw. ab.
			if (Simulator.bfeld) {
				Simulator.teilchen[2] += gammaB * Simulator.teilchen[3]  / iter;
				Simulator.teilchen[3] += -gammaB * Simulator.teilchen[2]  / iter;
			}


			// Bewegung ausfhren
			// Auf die Position wird ein Teil der Geschwindigkeit addiert.
			// Dabei muss man vom Y-Wert abziehen, weil das Koordinatensystems
			// eines Computers die Y-Achse nach unten hin positiv hat.
			Simulator.teilchen[0] += gammaVX * Simulator.teilchen[2]  / iter;
			Simulator.teilchen[1] += - gammaVY * Simulator.teilchen[3]  / iter;

			// Je nach dem, welche Felder an sind, wird eine andere Farbe
			// benutzt
			// Ist das E-Feld an, wird Rot dazu gemischt,
			// ist das B-Feld an, wird Grn dazu gemischt
			// sowie immer blau.
			g.setColor(new Color((Simulator.efeld ? 255 : 0), (Simulator.bfeld ? 255 : 0), 255));

			// Jetzt wird die Linie von der alten Position zur neuen gezogen
			g.drawLine((int)xalt, (int)yalt, (int)Simulator.teilchen[0], (int)Simulator.teilchen[1]);
		}

		// Jetzt wird die Anzeige aktualisiert, sonst wren die
		// nderungen nicht sichtbar.
		Simulator.anzeige.repaint();
	}

}
