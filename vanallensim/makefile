# Copyright © 2010, 2012-2013 Martin Ueding <dev@martin-ueding.de>
# Licensed under The GNU Public License Version 2 (or later)

classpath=/usr/share/java/j3dcore.jar:/usr/share/java/j3dutils.jar:/usr/share/java/vecmath.jar
javac=javac -encoding UTF-8 -cp $(classpath) -sourcepath .

vanallensim3d.jar: manifest.txt VanAllenGuertel.class makefile photoLicense.txt
	jar cfm $@ manifest.txt *.class *.java erde.jpg

install:
	install -d "$(DESTDIR)/usr/share/van-allen-sim-3d"
	install -m 644 vanallensim3d.jar -t "$(DESTDIR)/usr/share/van-allen-sim-3d"
	install -d "$(DESTDIR)/usr/share/applications"
	install -m 644 vanallensim.desktop -t "$(DESTDIR)/usr/share/applications"

VanAllenGuertel.class: *.java
	$(javac) VanAllenGuertel.java

clean:
	rm -f *.class *.jar
