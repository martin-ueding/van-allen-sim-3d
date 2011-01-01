/*
 * Copyright 2010 Martin Ueding <mu@martin-ueding.de>
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

import java.util.ArrayList;
import java.util.Timer;

import javax.swing.JFrame;

public class VanAllenGuertel {
	
	static ArrayList<Teilchen> teilchen;
	static ArrayList<JFrame> fenster;
	
	static DreiDimAnsicht frame3d;
	static boolean zeichneVektoren=true, zerlegeVektoren;
	
	static final double ERDRADIUS = 0.3;
	
	static final int MAXTEILCHEN = 3;
	
	
	public static void main(String[] args) {
		teilchen = new ArrayList<Teilchen>();
		fenster = new ArrayList<JFrame>();
		

		
	

//		JFrame xframe = new JFrame("X-Projektion");
		JFrame yframe = new JFrame("Y-Projektion");
		JFrame zframe = new JFrame("Z-Projektion");
		frame3d = new DreiDimAnsicht("3D-Ansicht");
		
//		fenster.add(xframe);
		fenster.add(yframe);
		fenster.add(zframe);
		fenster.add(frame3d);

		frame3d.setLocation(505, 0);
		yframe.setLocation(0, 0);
		zframe.setLocation(0, 550);
		

//		Anzeige xpanel = new Anzeige(1);
		Anzeige ypanel = new Anzeige(2);
		Anzeige zpanel = new Anzeige(3);

//		xframe.add(xpanel);
		yframe.add(ypanel);
		zframe.add(zpanel);
		
		for (JFrame fen : fenster) {
			fen.setVisible(true);
			fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			fen.setSize(500, 520);
		}
		
		Timer timer = new Timer();
		timer.schedule(new TeilchenBeweger(), 300, 40);
		

	}
	
	@SuppressWarnings("unused")
	private static void genElektron () {
		Elektron e = new Elektron();
		
		Vektor p = new Vektor();
		p.x = 0.8*(Math.random()-0.5);
		p.y = 0.8*(Math.random()-0.5);
		p.z = 0.8*(Math.random()-0.5);
		
		Vektor v = new Vektor();
		v.x = 0.2*(Math.random()-0.5);
		v.y = 0.2*(Math.random()-0.5);
		v.z = 0.2*(Math.random()-0.5);
		
		e.position = p;
		e.v = v;
		
		teilchen.add(e);
	}
	
	@SuppressWarnings("unused")
	private static void genProton () {
		Proton pr = new Proton();
		
		Vektor p = new Vektor();
		p.x = 0.8*(Math.random()-0.5);
		p.y = 0.8*(Math.random()-0.5);
		p.z = 0.8*(Math.random()-0.5);
		
		Vektor v = new Vektor();
		v.x = 0.2*(Math.random()-0.5);
		v.y = 0.2*(Math.random()-0.5);
		v.z = 0.2*(Math.random()-0.5);
		
		pr.position = p;
		pr.v = v;
		
		teilchen.add(pr);
	}
	
	/**
	 * Gibt das den Magnetfeldvektor an einem Punkt p zurück
	 * @param p
	 * @return
	 */
	static Vektor nordpol = new Vektor(0, 0, ERDRADIUS);
	static Vektor suedpol = new Vektor(0, 0, -ERDRADIUS);
	public static Vektor bfeld (Vektor p) {
		Vektor b = new Vektor();
		
		double abstandNord = p.sub(nordpol).betrag();
		double abstandSued = p.sub(suedpol).betrag();
		
		b = p.sub(nordpol).skalarmul(1.0/Math.pow(abstandNord, 2)).sub(p.sub(suedpol).skalarmul(1.0/Math.pow(abstandSued, 2))).skalarmul(1);
		
		return b;
	}

}
