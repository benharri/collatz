// Ben Harris
import java.math.BigInteger;
class Collatz {

  public static final int one_mil = 1000000;

  int prev[]   = new int[one_mil];
  int max      = 0;
  int maxsteps = 0;

  public static void main (String[] args) {
    System.out.println("javaCollatz");

    Collatz c = new Collatz();

    for(int i = 1; i <= one_mil; i++){
      System.out.println(i + "\tnum of steps: " + c.collatz(i));
    }

    System.out.println("Longest path was at " + c.max + " with " + c.maxsteps + " steps.");
  }

  int collatz (int start) {
    int cnt = 0;
    int initval = start;
    BigInteger tmp = BigInteger.valueOf(start);
    while(tmp.compareTo(BigInteger.ONE) != 0) {
      cnt++;
      if (tmp.compareTo(BigInteger.valueOf(one_mil)) < 0) {
        if (prev[tmp.intValue()] != 0) {
          tmp = BigInteger.valueOf(prev[tmp.intValue()]);
          continue;
        }
      }
      tmp = tmp.mod(BigInteger.valueOf(2)) == BigInteger.ZERO
        ? tmp.divide(BigInteger.valueOf(2))
        : tmp.multiply(BigInteger.valueOf(3)).add(BigInteger.ONE);
    }
    prev[initval] = cnt;
    if (cnt > max) {
      max = initval;
      maxsteps = cnt;
    }
    return cnt;
  }
}