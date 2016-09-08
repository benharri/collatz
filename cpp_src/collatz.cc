// Ben Harris

#include <unistd.h>
#include <pthread.h>
#include <cstdlib>
#include <string>
#include <iostream>
#include "collatz.h"
using namespace std;

#define ONE_MIL 1000000

pthread_mutex_t aMutex = PTHREAD_MUTEX_INITIALIZER;
int *prev = new int[ONE_MIL];
unsigned int curr = 1;
int maxpos = 0, maxsteps = 0;

int main (int argc, char **argv) {
  cout << "cCollatz" << endl;
  // for (unsigned int i = 1; i <= ONE_MIL; i++) {
  //   if(i %1000 == 0)
  //   cout << i << "\tnum of steps: " << collatz(i) << endl;
  // }
  pthread_t thread1, thread2, thread3, thread4;
  while (curr <= ONE_MIL) {
    pthread_create(&thread1, NULL, collatz_multithread, (void*)NULL);
    pthread_create(&thread2, NULL, collatz_multithread, (void*)NULL);
    pthread_create(&thread3, NULL, collatz_multithread, (void*)NULL);
    pthread_create(&thread4, NULL, collatz_multithread, (void*)NULL);
    pthread_join(thread1, NULL);
    pthread_join(thread2, NULL);
    pthread_join(thread3, NULL);
    pthread_join(thread4, NULL);
  }
  cout << "Longest path was " << maxsteps << " steps for " << maxpos << endl;
}

// int collatz (unsigned int curr) {
//   int cnt = 0;
//   unsigned long long start = curr;

//   while(start != 1) {
//     if (start < curr) {
//       cnt += prev[curr];
//       break;
//     }
//     start = start % 2 == 0 ? start/2 : 3*start + 1;
//     cnt++;
//   }

//   prev[curr] = cnt;

//   if (cnt > maxpos) {
//     maxpos = curr;
//     maxsteps = cnt;
//   }
//   return cnt;
// }

void* collatz_multithread (void* arg) {
  while (curr <= ONE_MIL) {

    pthread_mutex_lock(&aMutex);
    if(curr%1000 == 0) cout << curr << "\tnum of steps: " << prev[curr] << endl;
    unsigned long long working_var = curr;
    unsigned int startval = curr;
    pthread_mutex_unlock(&aMutex);

    int cnt = 0;

    while (working_var != 1) {
      if (working_var < startval) {
        cnt += prev[curr];
        // break;
      }
      working_var = working_var%2 == 0 ? working_var/2 : 3*working_var + 1;
      cnt++;
    }

    prev[startval] = cnt;

    pthread_mutex_lock(&aMutex);
    curr++;
    if (cnt > maxsteps) {
      maxpos = startval;
      maxsteps = cnt;
    }
    pthread_mutex_unlock(&aMutex);

  }
  return((void*)NULL);
}
