/*
 * Copyright 2010 Martin Ueding <mu@martin-ueding.de>
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Anzeige extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Gibt an, welche Achse nicht angezeigt werden soll
	 */
	int projektionsModus;

	BufferedImage feld;

	int wAlt, hAlt;

	int mouseX, mouseY;

	public Anzeige (int i) {
		projektionsModus = i;

		addMouseMotionListener(new MouseTracker(this));
	}

	private Point project (Vektor v) {
		Point p = new Point();
		double x=0, y=0;
		if (projektionsModus == 1) {
			x = v.z;
			y = v.y;
		}
		else if (projektionsModus == 2) {
			x = v.x;
			y = v.z;
		}
		else if (projektionsModus == 3) {
			x = v.x;
			y = v.y;
		}

		p.x = (int)(getWidth()/2 + getWidth()*x/5);
		p.y = (int)(getHeight()/2 + getHeight()*y/5);

		return p;
	}

	protected void paintComponent (Graphics h) {
		if (getWidth() != wAlt || getHeight() != hAlt) {
			if (projektionsModus == 3)
				genFeldImage();
			else
				genFeldImageLinien();
		}



		final Graphics2D g = (Graphics2D)h;
		g.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());


		g.drawImage(feld, 0, 0, null);

		g.setColor(Color.WHITE);
		g.drawOval(
				project(new Vektor(-VanAllenGuertel.ERDRADIUS, -VanAllenGuertel.ERDRADIUS, -VanAllenGuertel.ERDRADIUS)).x, 
				project(new Vektor(-VanAllenGuertel.ERDRADIUS, -VanAllenGuertel.ERDRADIUS, -VanAllenGuertel.ERDRADIUS)).y, 
				project(new Vektor(VanAllenGuertel.ERDRADIUS, VanAllenGuertel.ERDRADIUS, VanAllenGuertel.ERDRADIUS)).x-project(new Vektor(-VanAllenGuertel.ERDRADIUS, -VanAllenGuertel.ERDRADIUS, -VanAllenGuertel.ERDRADIUS)).x, 
				project(new Vektor(VanAllenGuertel.ERDRADIUS, VanAllenGuertel.ERDRADIUS, VanAllenGuertel.ERDRADIUS)).y-project(new Vektor(-VanAllenGuertel.ERDRADIUS, -VanAllenGuertel.ERDRADIUS, -VanAllenGuertel.ERDRADIUS)).y
		);


		for (Teilchen t : VanAllenGuertel.teilchen) {
			Point tPos = project(t.position);

			if (t.position.betrag() > VanAllenGuertel.ERDRADIUS) {

				if (Math.abs(t.position.x) < 5.0/2.0 && Math.abs(t.position.y) < 5.0/2.0 && Math.abs(t.position.z) < 5.0/2.0) {

					g.setColor(t.farbe);
					g.fillOval(tPos.x-2, tPos.y-2, 5, 5);
					Point tVekEnd = project(t.position.add(t.v));
					Point tBEnd = project(t.position.add(VanAllenGuertel.bfeld(t.position).kreuzprodukt(t.v).skalarmul(-t.ladung*5)));


					if (VanAllenGuertel.zeichneVektoren) {
						if (VanAllenGuertel.zerlegeVektoren) {
						}
						else {
							g.setColor(t.vekFarbe);
							g.drawLine(tPos.x, tPos.y, tVekEnd.x, tVekEnd.y);
							g.setColor(t.lFarbe);
							g.drawLine(tPos.x, tPos.y, tBEnd.x, tBEnd.y);
						}
					}
				}
			}
		}

		g.setColor(Color.white);
		g.drawString("Teilchen: "+VanAllenGuertel.teilchen.size(), 10, 20);

	}

	private void genFeldImage() {
		wAlt = getWidth();
		hAlt = getHeight();


		feld = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics h = feld.getGraphics();
		final Graphics2D g = (Graphics2D)h;
		g.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
		// Feldvektoren zeichnen
		int gitterabstand = 60;
		g.setColor(Color.DARK_GRAY);
		for (int i = 0; i < getWidth(); i+= gitterabstand) {
			for (int j = 0; j < getHeight(); j += gitterabstand) {
				Vektor aktuell = new Vektor();

				Point bPunkt = null;

				if (projektionsModus == 1) {
					aktuell.z = 5.0*(2*i-getWidth())/(getWidth())/2;
					aktuell.y = 5.0*(2*j-getHeight())/(getHeight())/2;
					bPunkt = project(aktuell.add(VanAllenGuertel.bfeld(aktuell).skalarmul(0.1)));
				}
				else if (projektionsModus == 2) {
					aktuell.x = 5.0*(2*i-getWidth())/(getWidth())/2;
					aktuell.z = 5.0*(2*j-getHeight())/(getHeight())/2;
					bPunkt = project(aktuell.add(VanAllenGuertel.bfeld(aktuell).skalarmul(0.1)));
				}
				else if (projektionsModus == 3) {
					aktuell.x = 5.0*(2*i-getWidth())/(getWidth())/2;
					aktuell.y = 5.0*(2*j-getHeight())/(getHeight())/2;
					bPunkt = project(aktuell.add(VanAllenGuertel.bfeld(aktuell).skalarmul(2)));
				}



				//	g.setColor(new Color ((int)(255.0*i/getWidth()), (int)(255.0*j/getHeight()), 255));
				g.drawLine(i, j, bPunkt.x, bPunkt.y);
				g.fillRect(bPunkt.x-2, bPunkt.y-2, 5, 5);
			}
		}
	}

	private void genFeldImageLinien() {
		wAlt = getWidth();
		hAlt = getHeight();


		feld = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics h = feld.getGraphics();
		final Graphics2D g = (Graphics2D)h;
		g.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));

		// Feldvektoren zeichnen
		g.setColor(Color.DARK_GRAY);
		Vektor aktuell, aktuell_alt;
		double anzahlRinge = 31;
		for (int i = 0; i < anzahlRinge; i++) {

			// Startpunkt bestimmen
			aktuell = new Vektor();

			//			if (projektionsModus == 1) {
			//				aktuell.z = 0;
			//				aktuell.y = 5.0*(2*i-getHeight())/(getHeight())/2;
			//			}
			//			
			//			else if (projektionsModus == 2) {
			//				aktuell.x = 5.0*(2*i-getWidth())/(getWidth())/2;
			//				aktuell.z = 5.0*(2*0-getHeight())/(getHeight())/2;
			//			}

			aktuell = VanAllenGuertel.nordpol.add(new Vektor(0.01*Math.sin(i/anzahlRinge*2*Math.PI), 0, 0.01*Math.cos(i/anzahlRinge*2*Math.PI)));

			int iter = 0;

			while (aktuell.sub(VanAllenGuertel.suedpol).betrag() > 0.05 && iter++ < 100000) {

				aktuell_alt = aktuell.clone();
				aktuell = aktuell.add(VanAllenGuertel.bfeld(aktuell).skalarmul(0.003));

				g.drawLine(project(aktuell_alt).x, project(aktuell_alt).y, project(aktuell).x, project(aktuell).y);
			}
		}
	}
}
