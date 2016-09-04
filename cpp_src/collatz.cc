// Ben Harris

#import <unistd.h>
#import <cstdlib>
#import <string>
#import <iostream>
#import "collatz.h"
using namespace std;

int *prev = new int[1000000];
int maxcnt = 0, maxsteps = 0;

int main (int argc, char **argv) {
  cout << "cCollatz" << endl;
  for (int i = 1; i <= 100000; i++) {
    cout << i << "\tnum of steps: " << collatz(i) << endl;
  }
  cout << "Longest path was " << maxsteps << " steps for " << maxcnt << endl;
}

int collatz (int start) {
  int cnt = 0;
  int initval = start;
  while(start != 1) {
    cnt++;
    if (start < 1000000 && prev[start] != 0) {
      start = prev[start];
      continue;
    }
    start = start % 2 == 0 ? start/2 : 3*start + 1;
  }
  prev[start] = cnt;
  if (cnt > maxcnt) {
    maxcnt = initval;
    maxsteps = cnt;
  }
  return cnt;
}
