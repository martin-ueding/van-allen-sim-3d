# Copyright © 2010, 2012-2013 Martin Ueding <dev@martin-ueding.de>
# Licensed under The GNU Public License Version 2 (or later)

classpath=/usr/share/java/j3dcore.jar:/usr/share/java/j3dutils.jar:/usr/share/java/vecmath.jar

vanallensim3d.jar: manifest.txt VanAllenGuertel.class makefile photoLicense.txt
	jar cfm $@ manifest.txt *.class *.java erde.jpg

install:
	mkdir -p "$(DESTDIR)/usr/share/van-allen-sim-3d"
	install -m 644 vanallensim3d.jar "$(DESTDIR)/usr/share/van-allen-sim-3d"

VanAllenGuertel.class: *.java
	javac -cp $(classpath) -sourcepath . VanAllenGuertel.java

clean:
	rm -f *.class *.jar
