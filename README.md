CS426 - Operating Systems

# Collatz assignment

The Collatz conjecture is simple.  It says that for every starting number
the sequence below always eventually gets to a 1.
    f(n) = n/2 if n is even
    f(n+1) = 3n+1 if n is odd

For example.
  17 -> 52 -> 26 -> 13 -> 40 -> 20 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1


### Your mission is to find the starting number that is less than 1,000,000 with the longest sequence.

This might take a long time.  To make it faster, you might cache previous
answers. Suppose you have an array prev[].  Prev[n] is the length of the 
sequence starting at n.  If, when computing a sequence, you ever get to a
number with a value in prev[], you can use that instead of continuing the 
computation.

If you have multiple threads, you must use good locking.

### Objectives

* Can compute the collatz sequence length for a given number.
* Can find the longest sequence.
* Uses Java threads
* Uses C++ Threads
* Uses the array prev[] above.
* Does better load balancing that simple partitioning.