# Copyright (c) 2012 Martin Ueding <dev@martin-ueding.de>

javafiles = $(wildcard *.java)
classfiles = $(javafiles:.java=.class)

ebsimulator.jar: $(classfiles) manifest.txt
	jar -cfm $@ $^

%.class: %.java
	javac $^

.PHONY: clean
clean:
	$(RM) *.class *.jar
	$(RM) *.orig
