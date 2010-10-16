/*
 * Copyright 2010 Martin Ueding <mu@martin-ueding.de>
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

import java.util.TimerTask;

import javax.swing.JFrame;

public class TeilchenBeweger extends TimerTask {

	@Override
	public void run() {
		Teilchen del = null;
		for (Teilchen t : MagnetischeFlascheSimulator.teilchen) {
			t.bewegen();
			if (t.v.betrag() > 0.3)
				del = t;
		}
		
		MagnetischeFlascheSimulator.teilchen.remove(del);
		
		for (JFrame fen : MagnetischeFlascheSimulator.fenster) {
			fen.repaint();
		}
	}

}
