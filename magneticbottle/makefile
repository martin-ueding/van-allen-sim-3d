# Copyright © 2010, 2012-2013 Martin Ueding <dev@martin-ueding.de>
# Licensed under The GNU Public License Version 2 (or later)

javafiles = $(wildcard *.java)
classfiles = $(javafiles:.java=.class)

magneticbottle.jar: manifest.txt $(classfiles) $(javafiles) makefile
	jar cfm $@ $^ *.class

install:
	mkdir -p "$(DESTDIR)/usr/share/van-allen-sim-3d"
	install -m 644 magneticbottle.jar "$(DESTDIR)/usr/share/van-allen-sim-3d"

%.class: %.java
	javac $^

.PHONY: clean
clean:
	$(RM) *.class *.jar
	$(RM) *.orig
