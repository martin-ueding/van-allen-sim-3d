# Copyright © 2012-2013 Martin Ueding <dev@martin-ueding.de>
# Licensed under The GNU Public License Version 2 (or later)

javafiles = $(wildcard *.java)
classfiles = $(javafiles:.java=.class)

ebsimulator.jar: manifest.txt $(classfiles) $(javafiles) makefile
	jar -cfm $@ $^ *.class

install:
	mkdir -p "$(DESTDIR)/usr/share/van-allen-sim-3d"
	install -m 644 ebsimulator.jar "$(DESTDIR)/usr/share/van-allen-sim-3d"
	install -d "$(DESTDIR)/usr/share/applications"
	install -m 644 ebsimulator.desktop -t "$(DESTDIR)/usr/share/applications"

%.class: %.java
	javac $^

.PHONY: clean
clean:
	$(RM) *.class *.jar
	$(RM) *.orig
