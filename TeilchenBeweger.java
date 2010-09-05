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
