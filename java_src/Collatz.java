// Ben Harris
// CS426 Collatz Assignment

class Collatz {
  static final int one_mil = 1000000;

  public static void main (String[] args) {
    Curr curr = new Curr(1);
    Prev prev = new Prev(new int[one_mil]);
    Max max = new Max(0, 0);
    CollatzCalc ccalc = new CollatzCalc(curr, prev, max);

    int num_threads = args.length > 0 ? Integer.parseInt(args[0]) : 4;
    Thread threads[] = new Thread[num_threads];
    System.out.println("Starting " + num_threads + " thread(s)...");

    for (int t = 0; t < num_threads; t++){
      threads[t] = new Thread(ccalc);
      threads[t].start();
    }
    try{
      for (int t = 0; t < num_threads; t++)
        threads[t].join();
    } catch (InterruptedException ie) {}

    System.out.println("Longest path was " + max.maxsteps + " steps for i=" + max.maxpos);
  }
}

class CollatzCalc implements Runnable {
  Curr curr; Prev prev; Max max; long tmp; int start, cnt; boolean keep_going;

  public CollatzCalc (Curr curr, Prev prev, Max max) {
    this.curr = curr;
    this.prev = prev;
    this.max = max;
  }

  public synchronized void run () {
    while (true) {
      synchronized (curr) {
      	keep_going = curr.curr <= Collatz.one_mil;
        tmp = curr.curr;
        start = curr.curr;
        curr.curr++;
      }
      if (!keep_going) break;

      cnt = 0;
      while (tmp != 1) {
        if (tmp < start){
          synchronized (prev) {
            cnt += prev.prev[(int)tmp];
          }
          break;
        }
        tmp = tmp%2 == 0 ? tmp/2 : 3*tmp + 1;
        cnt++;
      }

      if (start < Collatz.one_mil) {
        synchronized (prev) {
          prev.prev[start] = cnt;
        }
      }

      synchronized (max) {
        if (cnt > max.maxsteps) {
          max.maxpos = start;
          max.maxsteps = cnt;
        }
      }

    }
  }
}

// current int that we're calculating
class Curr {
  int curr;
  Curr (int curr) { this.curr = curr; }
}

// cache of previous answers
class Prev {
  int prev[];
  Prev (int[] prev) { this.prev = prev; }
}

// max value and position
class Max {
  int maxpos, maxsteps;
  Max (int pos, int steps) { maxpos = pos; maxsteps = steps; }
}

