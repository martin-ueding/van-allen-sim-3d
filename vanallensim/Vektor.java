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

public class Vektor {
	double x, y, z;
	
	public Vektor () {
		
	}
	
	public Vektor (double a, double b, double c) {
		x = a;
		y = b;
		z = c;
	}
	
	public Vektor kreuzprodukt (Vektor b) {
		Vektor c = new Vektor();
		
		c.x = y*b.z - z*b.y;
		c.y = z*b.x - x*b.z;
		c.z = x*b.y - y*b.x;
		
		return c;
	}
	
	public Vektor add (Vektor b) {
		Vektor c = new Vektor();
		
		c.x = x + b.x;
		c.y = y + b.y;
		c.z = z + b.z;
		
		return c;
	}
	
	public Vektor sub (Vektor b) {
		Vektor c = new Vektor();
		
		c.x = x - b.x;
		c.y = y - b.y;
		c.z = z - b.z;
		
		return c;
	}
	
	public Vektor skalarmul (double b) {
		Vektor c = new Vektor();
		
		c.x = x * b;
		c.y = y * b;
		c.z = z * b;
		
		return c;
	}
	
	public double betrag () {
		return Math.sqrt(x*x + y*y + z*z);
	}

	public String toString () {
		return "X: "+x+" Y: "+y+" Z: "+z;
	}
	
	public Vektor clone() {
		Vektor c = new Vektor();
		c.x = x;
		c.y = y;
		c.z = z;
		
		return c;
	}
}
