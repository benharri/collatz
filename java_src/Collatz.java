// Ben Harris
// CS426 Collatz Assignment
import java.math.BigInteger;

class Collatz {
  static int[] prev = new int[1000000];
  static int maxsteps = 0, maxpos = 0;

  public static void main (String[] args) {
    Curr curr = new Curr(1);
    CollatzCalc t1 = new CollatzCalc(curr);
    CollatzCalc t2 = new CollatzCalc(curr);
    t1.start();
    t2.start();

  }
}

class Curr {
  int curr;
  public Curr (int curr) { this.curr = curr; }
}

class CollatzCalc extends Thread {
  Curr curr;
  BigInteger tmp;
  int start, cnt;

  public CollatzCalc (Curr curr) {
    this.curr = curr;
    start = curr.curr;
    tmp = BigInteger.valueOf(curr.curr);
    cnt = 0;
  }

  public void run () {
    while (curr.curr <= 1000000) {
      while (tmp.compareTo(BigInteger.ONE) != 0) {
        if (tmp.compareTo(BigInteger.valueOf((long)start)) < 0)
          cnt += Collatz.prev[tmp.intValue()];
        if (tmp.mod(BigInteger.valueOf((long)2)) == BigInteger.ZERO)
          tmp = tmp.divide(BigInteger.valueOf((long)2));
        else
          tmp = tmp.multiply(BigInteger.valueOf((long)3)).add(BigInteger.ONE);
      }
      Collatz.prev[start] = cnt;
      if (cnt > Collatz.maxsteps) {
        Collatz.maxpos = start;
        Collatz.maxsteps = cnt;
      }
    }
  }
}

