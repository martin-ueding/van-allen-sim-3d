# Copyright (c) 2010, 2012 Martin Ueding <dev@martin-ueding.de>
#
# This program is free software: you can redistribute it and/or modify it under
# the terms of the GNU General Public License as published by the Free Software
# Foundation, either version 3 of the License, or (at your option) any later
# version.
#
# This program is distributed in the hope that it will be useful, but WITHOUT
# ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
# FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
# details.
#
# You should have received a copy of the GNU General Public License along with
# this program. If not, see <http://www.gnu.org/licenses/>.

classpath=/usr/share/java/j3dcore.jar:/usr/share/java/j3dutils.jar:/usr/share/java/vecmath.jar

vanallensim3d.jar: manifest.txt VanAllenGuertel.class makefile photoLicense.txt
	jar cfm $@ manifest.txt *.class *.java erde.jpg

VanAllenGuertel.class: *.java
	javac -cp $(classpath) -sourcepath . VanAllenGuertel.java

clean:
	rm -f *.class *.jar
