// Copyright (c) 2010, 2012 Martin Ueding <dev@martin-ueding.de>

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener {

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	// Diese Methode wird aufgerufen, wenn eine Taste auf der Tastatur
	// gedrückt worden ist
	public void keyTyped(KeyEvent e) {
		// Wenn der eingegebene Buchstabe "B" ist, dann wird das B-Feld ein
		// oder ausgeschaltet. Der Modus wird auf 0 gesetzt, und die Automatik
		// greift nicht weiter ein.
		if (e.getKeyChar() == 'b') {
			// bfeld ist entweder "wahr" oder "falsch". Hier wird gesagt, dass
			// bfeld jetzt das Gegenteil von bfeld sein soll, also ist es nach
			// diesem Aufruf "wahr" (also an), wenn es vorher "falsch" (also
			// aus) war, und umgekehrt.
			Simulator.bfeld = !Simulator.bfeld;
			Simulator.mode = 0;
		}

		if (e.getKeyChar() == 'e') {
			Simulator.efeld = !Simulator.efeld;
			Simulator.mode = 0;
		}

		// Drückt man die 1, wird der Modus 1 gestartet. In diesem (und dem
		// anderen) Modus ist das magnetische Feld immer an, daher wird es hier
		// schon eingeschaltet.
		if (e.getKeyChar() == '1') {
			Simulator.mode = 1;
			Simulator.bfeld = true;
		}

		if (e.getKeyChar() == '2') {
			Simulator.mode = 2;
			Simulator.bfeld = true;
		}


		// Drückt man "r" oder "i", wird das Programm entsprechend
		// zurückgesetzt
		if (e.getKeyChar() == 'r')
			Simulator.init();

		if (e.getKeyChar() == 'i')
			Simulator.init_all();

		// Ein Druck auf Q beendet das Programm sofort
		if (e.getKeyChar() == 'q')
			System.exit(0);

	}
}
