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

import javax.media.j3d.BranchGroup;
import javax.swing.JFrame;

public class TeilchenBeweger extends TimerTask {
	
	int iter = 0;

	@Override
	public void run() {
		Teilchen del = null;
		for (Teilchen t : VanAllenGuertel.teilchen) {
			t.bewegen();
			if (t.v.betrag() > 1 || t.position.betrag() <= VanAllenGuertel.ERDRADIUS || t.position.betrag() > 10*VanAllenGuertel.ERDRADIUS) {
				t.position = new Vektor();
				t.bewegen();
				del = t;
			}
		}
		
		VanAllenGuertel.teilchen.remove(del);
//		VanAllenGuertel.frame3d.tgalles.removeChild(del.tg);
		
		for (JFrame fen : VanAllenGuertel.fenster) {
			fen.repaint();
		}
		
		if (iter++ % 1 == 0 && VanAllenGuertel.teilchen.size() < VanAllenGuertel.MAXTEILCHEN) {
			Teilchen t = (Math.random() > 0.5 ? new Elektron() : new Proton());

			t.position.x = VanAllenGuertel.ERDRADIUS*2*(Math.random()-0.5);
			t.position.y = VanAllenGuertel.ERDRADIUS*3*(Math.random()-0.5);
			t.position.z = 0.1*(Math.random()-0.5);
			
			t.v.x = 0.01*Math.random() + 0.01;
			t.v.y = -0.01*Math.random() - 0.05;
			t.v.z = 0.01*(Math.random()-0.5);
			
			VanAllenGuertel.teilchen.add(t);
			BranchGroup bg = new BranchGroup();
			bg.addChild(t.tg);
			VanAllenGuertel.frame3d.tgalles.addChild(bg);
		}
	}

}
