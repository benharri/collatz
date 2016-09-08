// Ben Harris
// CS426 Collatz Assignment

import java.math.BigInteger;

class Collatz extends Thread {

  public static final int one_mil = 1000000;
  static int prev[] = new int[one_mil];
  static int curr = 1, maxpos = 0, maxsteps = 0;
  private Thread t;

  public static void main (String[] args) {
    System.out.println("javaCollatz");

    Collatz t1 = new Collatz();
    Collatz t2 = new Collatz();

    t1.start();
    t2.start();

    System.out.println("Longest path was at " + maxpos + " with " + maxsteps + " steps.");
  }

  public void start () {
    System.out.println("starting");
  }

  public void run () {
    while (curr <= one_mil) {
      synchronized (curr){
        BigInteger tmp = BigInteger.valueOf(curr);
        int start = curr;
        curr++;
      }
      int cnt = 0;

      while (tmp != 1) {
        if (tmp < start) cnt += prev[tmp];
        tmp = tmp%2 == 0 ? tmp/2 : 3*tmp + 1;
        cnt++;
      }

      prev[start] = cnt;

      if (cnt > maxsteps) {
        maxpos = start;
        maxsteps = cnt;
      }
    }
  }

  // int collatz (int start) {
  //   int cnt = 0;
  //   int initval = start;
  //   BigInteger tmp = BigInteger.valueOf(start);
  //   while(tmp.compareTo(BigInteger.ONE) != 0) {
  //     cnt++;
  //     if (tmp.compareTo(BigInteger.valueOf(one_mil)) < 0) {
  //       if (prev[tmp.intValue()] != 0) {
  //         tmp = BigInteger.valueOf(prev[tmp.intValue()]);
  //         continue;
  //       }
  //     }
  //     tmp = tmp.mod(BigInteger.valueOf(2)) == BigInteger.ZERO
  //       ? tmp.divide(BigInteger.valueOf(2))
  //       : tmp.multiply(BigInteger.valueOf(3)).add(BigInteger.ONE);
  //   }
  //   prev[initval] = cnt;
  //   if (cnt > max) {
  //     max = initval;
  //     maxsteps = cnt;
  //   }
  //   return cnt;
  // }


}
