.PHONY: magneticbottle.jar
magneticbottle.jar:
	javac MagnetischeFlascheSimulator.java
	jar cfm magneticbottle.jar manifest.txt *.class *.java
