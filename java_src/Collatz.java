// Ben Harris
// CS426 Collatz Assignment

class Collatz {
  static final int one_mil = 1000000;

  static int[] prev = new int[one_mil];
  static int maxsteps = 0, maxpos = 0;

  public static void main (String[] args) {
    Curr curr = new Curr(1);
    CollatzCalc ccalc = new CollatzCalc(curr);
    System.out.println("Start threads");
    Thread t1, t2, t3, t4;
    while (curr.curr <= one_mil) {
      t1 = new Thread(ccalc);
      t1.start();
      t2 = new Thread(ccalc);
      t2.start();
      t3 = new Thread(ccalc);
      t3.start();
      t4 = new Thread(ccalc);
      t4.start();
      try{
        t1.join();
        t2.join();
        t3.join();
        t4.join();
      } catch (InterruptedException ie) {}
    }
    System.out.println("Longest path was " + maxsteps + " steps for i=" + maxpos);
  }
}

class Curr {
  int curr;
  public Curr (int curr) { this.curr = curr; }
  public void next () { curr++; }
}

class CollatzCalc implements Runnable {
  Curr curr;
  long tmp;
  int start, cnt;

  public CollatzCalc (Curr curr) {
    synchronized (curr) {
      this.curr = curr;
      start = curr.curr;
      tmp = curr.curr;
      // System.out.println("i= " + tmp);
      curr.next();
    }
    cnt = 0;
  }

  public void run () {
    // while (curr.curr <= Collatz.one_mil) {
      while (tmp != 1) {
        if (tmp < start){
          cnt += Collatz.prev[(int)tmp];
          break;
        }
        tmp = tmp%2 == 0 ? tmp/2: 3*tmp + 1;
        cnt++;
      }
      Collatz.prev[start] = cnt;
      if (cnt > Collatz.maxsteps) {
        Collatz.maxpos = start;
        Collatz.maxsteps = cnt;
      }
    // }
  }
}

