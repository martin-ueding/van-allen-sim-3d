# Copyright (c) 2010, 2012 Martin Ueding <dev@martin-ueding.de>

javafiles = $(wildcard *.java)
classfiles = $(javafiles:.java=.class)

magneticbottle.jar: manifest.txt $(classfiles)
	jar cfm $@ $^ *.class

%.class: %.java
	javac $^

.PHONY: clean
clean:
	$(RM) *.class *.jar
	$(RM) *.orig
