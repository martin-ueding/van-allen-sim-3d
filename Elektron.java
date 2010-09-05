import java.awt.Color;

public class Elektron extends Teilchen {
	

	
	public Elektron () {
		// Farbe in der Anzeige
		farbe=Color.BLUE;
		bahnFarbe=Color.RED;
		vekFarbe=Color.RED;
		lFarbe=Color.YELLOW;
		
		// Materialkonstanten
		masse = 0.5;
		ladung = -0.1;
		
setAp();
	}
}
