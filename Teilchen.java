// Copyright (c) 2010 Martin Ueding [mu@martin-ueding.de]

package de.martin_ueding.physik.vanAllenGuertel;

import java.awt.Color;

import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.LineStripArray;
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Sphere;

public abstract class Teilchen {
	// Die Position des Teilchens
	Vektor position;
	// Die Geschwindigkeit des Teilchens
	Vektor v;

	// Farbe in der Anzeige
	Color farbe, bahnFarbe, vekFarbe, lFarbe;

	// Materialkonstanten
	double masse;
	double ladung;

	static int iter = 10;

	Color3f bahnFarbe3D = new Color3f((float)Math.random(), (float)Math.random(), (float)Math.random());


	TransformGroup tg = new TransformGroup();
	Transform3D transform = new Transform3D();
	Sphere sphere = new Sphere(0.01f);
	Vector3f position3f;


	int[] stripLengths;
	private Shape3D myShape;
	
	BranchGroup bg;

	public Teilchen () {
		v = new Vektor();
		position = new Vektor();
		position3f = new Vector3f();

		tg.setCapability( TransformGroup.ALLOW_TRANSFORM_WRITE );
		tg.setCapability( TransformGroup.ALLOW_TRANSFORM_READ );
		tg.addChild(sphere);
		
		stripLengths = new int[1];
		
		Appearance ap11 = new Appearance();
		ColoringAttributes ca11 = new ColoringAttributes(bahnFarbe3D, ColoringAttributes.NICEST); 
		ap11.setColoringAttributes(ca11);
		ap11.setMaterial(new Material(bahnFarbe3D, new Color3f(0.0f, 0.0f, 0.0f), bahnFarbe3D, new Color3f(1.0f, 1.0f, 1.0f), .70f));

	}

	protected void setAp () {
		Color3f col;

		Appearance ap = new Appearance();
		if (ladung > 0) {
			col = new Color3f(0.0f, 0.0f, 1.0f);
		}
		else {
			col = new Color3f(1.0f, 0.0f, 0.0f);
		}
		
		col = bahnFarbe3D;
		
		ColoringAttributes ca = new ColoringAttributes(col, ColoringAttributes.NICEST); 
		ap.setColoringAttributes(ca);
		ap.setMaterial(new Material(col, new Color3f(0.0f, 0.0f, 0.0f), col, new Color3f(1.0f, 1.0f, 1.0f), .70f));
		sphere.setAppearance(ap);
	}




	void bewegen () {
		Vektor alterPunkt = position.clone();
		for (int i = 0; i < iter/4; i++) {
			
			
			// Kraft bestimmen
			Vektor f = v.kreuzprodukt(VanAllenGuertel.bfeld(position)).skalarmul(ladung/iter);

			// Beschleunigung (a = F/m) auf die Geschwindigkeit addieren
			v = v.add(f.skalarmul(1/masse));

			// Bewegung aufrechnen
			position = position.add(v.skalarmul(1.0/iter));

			position3f = new Vector3f((float)position.x, (float)position.y, (float)position.z);


			


			transform.setTranslation(position3f);

			tg.setTransform(transform);

		}
		
		// Spur malen
		stripLengths[0] = 2;
		

		Point3f[] myCoords = new Point3f[2];
		myCoords[0] = new Point3f((float)alterPunkt.x, (float)alterPunkt.y, (float)alterPunkt.z);
		myCoords[1] = new Point3f((float)position.x, (float)position.y, (float)position.z);
		

		LineStripArray myLines = new LineStripArray(myCoords.length, GeometryArray.COORDINATES, stripLengths );
		myLines.setCoordinates( 0, myCoords );

		
		Appearance ap11 = new Appearance();
		ColoringAttributes ca11 = new ColoringAttributes(bahnFarbe3D, ColoringAttributes.NICEST); 
		ap11.setColoringAttributes(ca11);
		ap11.setMaterial(new Material(bahnFarbe3D, new Color3f(0.0f, 0.0f, 0.0f), bahnFarbe3D, new Color3f(1.0f, 1.0f, 1.0f), .70f));
		myShape = new Shape3D( myLines, ap11 );
		myShape.setAppearance(ap11);
		
		BranchGroup bg = new BranchGroup();
		bg.addChild(myShape);
		
		if (myShape != null)
			VanAllenGuertel.frame3d.tgalles.addChild(bg);
		else
			System.out.println("myShape == null");
	}

}
