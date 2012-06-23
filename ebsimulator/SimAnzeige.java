// Copyright (c) 2010, 2012 Martin Ueding <dev@martin-ueding.de>

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
	protected void paintComponent (Graphics h) {
		final Graphics2D g = (Graphics2D)h;
		g.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
			
		// Zuerst wird das Bild mit den Spuren eingefügt
		g.drawImage(Simulator.bild, null, 0, 0);
	
		
		// Es wird grüne Farbe ausgewählt ...
		g.setColor(Color.GREEN);
		
		// ... und ein Kreis ausgefüllt. Dieser stellt das Teilchen dar.
		g.fillOval((int)Simulator.teilchen[0]-3, (int)Simulator.teilchen[1]-3, 7, 7);
		
		
		// Jetzt werden noch die verschiedenen Anzeigen gemalt
		g.setColor(Color.MAGENTA);
		g.drawString("E-Feld ist: "+(Simulator.efeld ? "AN" : "AUS")+" [E]", 10, 15);
		
		g.setColor(Color.CYAN);
		g.drawString("B-Feld ist: "+(Simulator.bfeld ? "AN" : "AUS")+" [B]", 10, 30);
		
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
		g.fillRect(getWidth()-20, 5, 15, (int)(Math.sqrt(Math.pow(Simulator.teilchen[2], 2)+Math.pow(Simulator.teilchen[3], 2))*10));
		
	}

}
