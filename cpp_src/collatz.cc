// Ben Harris
// CS426 Collatz Assignment

#include <unistd.h>
#include <pthread.h>
#include <cstdlib>
#include <iostream>
#include "collatz.h"
using namespace std;

#define ONE_MIL 1000000

pthread_mutex_t currMutex = PTHREAD_MUTEX_INITIALIZER;
int *prev = new int[ONE_MIL];
int curr = 1, maxpos = 0, maxsteps = 0;

int main (int argc, char **argv) {
  int NUM_THREADS = argc > 1 ? atoi(argv[1]): 4;
  pthread_t threads[NUM_THREADS];

  for(int i = 0; i < NUM_THREADS; i++)
    pthread_create(&threads[i], NULL, collatz, (void*)NULL);
  for(int i = 0; i < NUM_THREADS; i++)
    pthread_join(threads[i], NULL);

  cout << "Longest path was " << maxsteps + 1 << " steps for i=" << maxpos << endl;
  cout << "Calculated with " << NUM_THREADS << " thread(s)." << endl;
}

void* collatz (void* arg) {
  while (curr <= ONE_MIL) {
    pthread_mutex_lock(&currMutex);
    unsigned long long tmp = curr;
    unsigned int start = curr;
    curr++;
    pthread_mutex_unlock(&currMutex);

    int cnt = 0;
    while (tmp != 1) {
      if (tmp < start) { cnt += prev[tmp]; break; }
      tmp = tmp%2 == 0 ? tmp/2 : 3*tmp + 1;
      cnt++;
    }
    prev[start] = cnt;

    if (cnt > maxsteps) {
      maxpos = start;
      maxsteps = cnt;
    }

  }
  return((void*)NULL);
}
