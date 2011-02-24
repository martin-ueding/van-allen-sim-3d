// Copyright (c) 2010 Martin Ueding <dev@martin-ueding.de>

package de.martin_ueding.physik.ebSimulator;

// Hier werden die ben�tigten Klassen importiert, beispielsweise f�r die Grafikausgabe
import java.awt.GraphicsDevice;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.Timer;

import javax.swing.JFrame;

public class Simulator {

	static BufferedImage bild;	// Das Bild, in das die Spuren gemalt werden
	static SimAnzeige anzeige = new SimAnzeige();
	
	// Eine Liste aus Dezimalzahlen, in der die Position sowie die Geschwindigkeit des Teilchens gespeichert wereden
	// 0, 1 = X, Y
	// 2, 3 = Vx, Vy
	static double[] teilchen;
	
	// Zwei Wahrheitswerte, die speichern, ob man die entsprechenden Felder an oder aus hat
	static boolean efeld, bfeld;
	
	// Ist der Modus gleich 0, so werden die Felder nicht automatisch gesteuert. Modus 1 steuert die Felder so, dass die L�sung im Buch rauskommt, Modus 2 steuert die Felder so, wie ich es in der Aufgabe verstanden habe
	static int mode;
	
	// Die ist die Hauptmethode in dem Programm, sie wird aufgerufen, wenn das Programm gestartet wird
	public static void main(String[] args) {
		// Hier wird ein Fenster erzeugt und die Gr��e festgelegt
		JFrame f = new JFrame ("E-B-Simulator");
		f.setSize(1000, 500);
		f.add(anzeige);
		
		// Vollbild wird aktiviert ...
		GraphicsDevice myDevice = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		if (myDevice.isFullScreenSupported()) {
			f.setUndecorated(true);
			f.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			myDevice.setFullScreenWindow(f);

			f.setLocation(0, 0);

			f.setVisible(true);
		}
		
		
		f.setVisible(true);
		
		// Alle Werte, also die Position und Geschwindigkeit des Teilchens, die bisher gezeichneten Spuren, etc wird initialisiert, also auf einen festen Anfangswert gesetzt. Diese Funktion ist weiter unten definiert.
		init_all();
		
		// Der Timer wird alle 50 ms aufgerufen und bewegt das Teilchen ein St�ck weiter
		Timer timer = new Timer();
		timer.schedule(new MyTimerTask(), 1000, 50);
		
		// Der KeyListener achtet auf Tastatureingaben und �bernimmt die Steuerung des Programms
		f.addKeyListener(new MyKeyListener());
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	// Diese Funktion setzt einige Werte zur�ck, jedoch werden die Felder nicht ver�ndert
	static void init () {
		// Ein neues Bild f�r die Spuren wird generiert
		bild = new BufferedImage(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height, BufferedImage.TYPE_INT_RGB);

		// Die Liste mit den Teilcheneigenschaften wird erzeugt
		teilchen = new double[4];
		
		// Das Teilchen bekommt eine bestimmte Position, und zwar oben in der Mitte, ein klein wenig unterhalb des Bildschirmrandes (10 Pixel). Die Geschwindigkeit ist 0, da nichts anderes defininiert worden ist. In Java ist 0.0 der Standardwert f�r neue Dezimalzahlen.
		teilchen[0] = Toolkit.getDefaultToolkit().getScreenSize().width/2;
		teilchen[1] = 10;
		
		
	}
	
	// Diese Funktion hier schaltet beide Felder aus und ruft dann die init() Funktion auf, die den Rest zur�cksetzt
	static void init_all() {
		efeld = false;
		bfeld = false;
		
		mode = 0;
		
		init();
	}

}
