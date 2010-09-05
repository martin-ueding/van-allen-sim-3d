import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseTracker implements MouseMotionListener {
	
	Anzeige a;
	
	public MouseTracker (Anzeige x) {
		a = x;
	}

	public void mouseDragged(MouseEvent e) {
		a.mouseX = e.getX();
		a.mouseY = e.getY();

	}

	public void mouseMoved(MouseEvent e) {

	}

}
