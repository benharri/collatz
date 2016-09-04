CSW = -O3 -Wall
LSW = -lfd

all:
	make collatz

collatz: collatz.o Makefile
	g++ collatz.o -o collatz

collatz.o: collatz.cc Makefile
	g++ collatz.cc -c -o collatz.o $(CSW)

collatz.cc: collatz.h Makefile
	touch collatz.cc

clean:
	touch Makefile; make
