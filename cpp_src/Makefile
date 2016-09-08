CSW = -O3 -Wall
LSW = -pthread -lm

all:
	make collatz

collatz: collatz.o Makefile
	g++ collatz.o -o collatz $(LSW)

collatz.o: collatz.cc Makefile
	g++ collatz.cc -c -o collatz.o $(CSW)

collatz.cc: collatz.h Makefile
	touch collatz.cc

clean:
	touch Makefile; make
