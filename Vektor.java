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
