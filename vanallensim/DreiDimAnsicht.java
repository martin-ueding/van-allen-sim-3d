/*
 * Copyright (c) 2010 Martin Ueding <dev@martin-ueding.de>
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

import java.awt.GraphicsConfiguration;
import java.util.LinkedList;

import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.LineStripArray;
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.vecmath.AxisAngle4d;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;

@SuppressWarnings("serial")
public class DreiDimAnsicht extends JFrame {


	TransformGroup tgalles;
	BranchGroup group;

	public DreiDimAnsicht(String title) {
		super(title);

		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();

		Canvas3D canvas = new Canvas3D(config); 

		SimpleUniverse universe = new SimpleUniverse(canvas);

		add(canvas);

		group = new BranchGroup(); 

		tgalles = new TransformGroup();

		tgalles.setCapability( TransformGroup.ALLOW_TRANSFORM_WRITE );
		tgalles.setCapability( TransformGroup.ALLOW_TRANSFORM_READ );
		tgalles.setCapability( BranchGroup.ALLOW_CHILDREN_EXTEND );
		tgalles.setCapability( BranchGroup.ALLOW_CHILDREN_READ);
		tgalles.setCapability( BranchGroup.ALLOW_CHILDREN_WRITE);
		//		Erde
		
		Appearance ap = new Appearance();
//		Color3f col = new Color3f(0.0f, 0.0f, 1.0f); 
//		ColoringAttributes ca = new ColoringAttributes(col, ColoringAttributes.NICEST); 
//		ap.setColoringAttributes(ca);

	//	TextureLoader loader = new TextureLoader(ClassLoader.getSystemResource("erde.jpg"), "RGB", new Container());

//		Texture texture = loader.getTexture();

	//	texture.setBoundaryModeS(Texture.WRAP);

//		texture.setBoundaryModeT(Texture.WRAP);
//
//		texture.setBoundaryColor( new Color4f( 0.0f, 1.0f, 0.0f, 0.0f ) );

		int primflags = Primitive.GENERATE_NORMALS +

		   Primitive.GENERATE_TEXTURE_COORDS; 
		
		TransformGroup erdeGruppe = new TransformGroup();
		Transform3D erdeTransform = new Transform3D();
		erdeTransform.setRotation(new AxisAngle4d(1, 0, 0, Math.PI/2));
		
		erdeGruppe.setTransform(erdeTransform);
		
		Sphere erde = new Sphere((float)VanAllenGuertel.ERDRADIUS, primflags, ap);
		
		erdeGruppe.addChild(erde);


		// Set up the texture attributes

		//could be REPLACE, BLEND or DECAL instead of MODULATE

		TextureAttributes texAttr = new TextureAttributes();

		texAttr.setTextureMode(TextureAttributes.REPLACE);

//		ap.setTexture(texture);

		ap.setTextureAttributes(texAttr);

//		ap.setMaterial(new Material(col, new Color3f(0.0f, 1.0f, 0.0f), col, new Color3f(1.0f, 1.0f, 1.0f), .70f));
		erde.setAppearance(ap);

		tgalles.addChild(erdeGruppe);

		// Nordpol

		/*Sphere nordpolKugel = new Sphere((float)VanAllenGuertel.ERDRADIUS/20);
		Vector3f nordpolKugelPosition = new Vector3f((float)VanAllenGuertel.nordpol.x, (float)VanAllenGuertel.nordpol.y, (float)VanAllenGuertel.nordpol.z);
		Transform3D nordpolKugelTransformation = new Transform3D();;
		nordpolKugelTransformation.setTranslation(nordpolKugelPosition);
		Appearance nordpolKugelAppearance = new Appearance();
		Color3f nordpolKugelHauptfarbe = new Color3f(0.0f, 0.0f, 1.0f); 
		ColoringAttributes nordpolKugelColoringAttributes = new ColoringAttributes(nordpolKugelHauptfarbe, ColoringAttributes.NICEST); 
		nordpolKugelAppearance.setColoringAttributes(nordpolKugelColoringAttributes);

		nordpolKugelAppearance.setMaterial(new Material(nordpolKugelHauptfarbe, new Color3f(0.0f, 1.0f, 0.0f), nordpolKugelHauptfarbe, new Color3f(1.0f, 1.0f, 1.0f), .70f));
		nordpolKugel.setAppearance(nordpolKugelAppearance);

		TransformGroup nordpolGroup = new TransformGroup();
		nordpolGroup.addChild(nordpolKugel);
		nordpolGroup.setTransform(nordpolKugelTransformation);

		tgalles.addChild(nordpolGroup);*/


		// Feldlinien


		LinkedList<Vektor> feldpunkte = new LinkedList<Vektor>();

		Vektor aktuell;
		int anzahlRinge = 10;
		int radiusSegmente = 10;

		int[] stripLengths = new int[anzahlRinge*radiusSegmente];

		for (int i = 0; i < anzahlRinge*radiusSegmente; i++) {

			// Startpunkt bestimmen
			aktuell = new Vektor();
			double faktor = Math.sqrt((double)i/anzahlRinge/radiusSegmente)+0.2;
			Vektor versatz = new Vektor(faktor*Math.sin((i%anzahlRinge)*2*Math.PI/anzahlRinge), faktor*Math.cos((i%anzahlRinge)*2*Math.PI/anzahlRinge), Math.cos((i/anzahlRinge)*2*Math.PI/radiusSegmente));
			aktuell = VanAllenGuertel.nordpol.add(versatz.skalarmul(0.01));

			int iter = 0;
			feldpunkte.add(aktuell);

			while (aktuell.sub(VanAllenGuertel.suedpol).betrag() > 0.02 && iter < 100000) {

				aktuell = aktuell.add(VanAllenGuertel.bfeld(aktuell).skalarmul(0.001));

				feldpunkte.add(aktuell);
				iter++;
			}

			stripLengths[i] = iter+1;
		}
		int summe = 0;
		for (int i = 0; i < stripLengths.length; i++) {
			summe += stripLengths[i];
		}

		Point3f[] myCoords = new Point3f[feldpunkte.size()];
		int k = 0;
		for (Vektor vektor : feldpunkte) {
			myCoords[k++] = new Point3f((float)vektor.x, (float)vektor.y, (float)vektor.z);
		}

		LineStripArray myLines = new LineStripArray(
				myCoords.length,
				GeometryArray.COORDINATES,
				stripLengths );
		myLines.setCoordinates( 0, myCoords );

		Appearance ap11 = new Appearance();
		Color3f col11 = new Color3f(0.2f, 0.2f, 0.1f); 
		ColoringAttributes ca11 = new ColoringAttributes(col11, ColoringAttributes.NICEST); 
		ap11.setColoringAttributes(ca11);

		ap11.setMaterial(new Material(col11, new Color3f(0.0f, 0.0f, 0.0f), col11, new Color3f(1.0f, 1.0f, 1.0f), .70f));

		Shape3D myShape = new Shape3D( myLines, ap11 );

		myShape.setAppearance(ap11);

		tgalles.addChild(myShape);


		//		Zeilchen
		for (Teilchen t : VanAllenGuertel.teilchen) {
			tgalles.addChild(t.tg);
		}

		group.addChild(tgalles);
		
		
		// Sonne
		Sphere sonneKugel = new Sphere((float)VanAllenGuertel.ERDRADIUS*10);
		Vector3f sonneKugelPosition = new Vector3f(0, -30, 0);
		Transform3D sonneKugelTransformation = new Transform3D();;
		sonneKugelTransformation.setTranslation(sonneKugelPosition);
		Appearance sonneKugelAppearance = new Appearance();
		Color3f sonneKugelHauptfarbe = new Color3f(0.996f, 0.93f, 0.541f); 
		ColoringAttributes sonneKugelColoringAttributes = new ColoringAttributes(sonneKugelHauptfarbe, ColoringAttributes.NICEST); 
		sonneKugelAppearance.setColoringAttributes(sonneKugelColoringAttributes);

//		sonneKugelAppearance.setMaterial(new Material(sonneKugelHauptfarbe, new Color3f(1.0f, 1.0f, 1.0f), sonneKugelHauptfarbe, new Color3f(1.0f, 1.0f, 1.0f), .70f));
		sonneKugel.setAppearance(sonneKugelAppearance);

		TransformGroup sonneGroup = new TransformGroup();
		sonneGroup.addChild(sonneKugel);
		sonneGroup.setTransform(sonneKugelTransformation);

		tgalles.addChild(sonneGroup);
		

		BoundingSphere bounds = new BoundingSphere( new Point3d(0.0,0.0,0.0), 100.0 );
		MouseRotate behavior = new MouseRotate( tgalles );
		tgalles.addChild( behavior );
		behavior.setSchedulingBounds( bounds );
		tgalles.setCapability( TransformGroup.ALLOW_TRANSFORM_WRITE );
		tgalles.setCapability( TransformGroup.ALLOW_TRANSFORM_READ );


		MouseZoom behavior2 = new MouseZoom(tgalles);
		tgalles.addChild( behavior2 );
		behavior2.setSchedulingBounds( bounds );
		tgalles.setCapability( TransformGroup.ALLOW_TRANSFORM_WRITE );
		tgalles.setCapability( TransformGroup.ALLOW_TRANSFORM_READ );
		
		



		Color3f light1Color = new Color3f(1f, 1f, 1f); // green light


		Vector3f light1Direction = new Vector3f(0.0f, 1.0f, 0.0f);

		DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);

		light1.setInfluencingBounds(bounds);

		tgalles.addChild(light1);

		universe.getViewingPlatform().setNominalViewingTransform();

		// add the group of objects to the Universe

		universe.addBranchGraph(group);

	}

}
