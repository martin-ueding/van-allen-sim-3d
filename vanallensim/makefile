# Copyright (c) 2010 Martin Ueding <dev@martin-ueding.de>

classpath=/usr/share/java/j3dcore.jar:/usr/share/java/j3dutils.jar:/usr/share/java/vecmath.jar

vanallensim3d.jar: manifest.txt VanAllenGuertel.class
	jar cfm $@ manifest.txt *.class *.java erde.jpg

VanAllenGuertel.class: *.java
	javac -cp $(classpath) -sourcepath . VanAllenGuertel.java

clean:
	rm -f *.class *.jar
