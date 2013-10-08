# Copyright © 2011-2013 Martin Ueding <dev@martin-ueding.de>
# Licensed under The GNU Public License Version 2 (or later)

all:
	make -C ebsimulator
	make -C magneticbottle
	make -C vanallensim

install:
	make -C ebsimulator install
	make -C magneticbottle install
	make -C vanallensim install

clean:
	make -C ebsimulator clean
	make -C magneticbottle clean
	make -C vanallensim clean
