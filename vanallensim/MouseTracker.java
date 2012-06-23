/*
 * Copyright (c) 2010, 2012 Martin Ueding <dev@martin-ueding.de>
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
