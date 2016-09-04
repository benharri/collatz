// Ben Harris

class Collatz {

  int prev[] = new int[1000000];
  int max = 0;
  int maxsteps = 0;

  public static void main(String[] args) {
    System.out.println("javaCollatz");

    Collatz c = new Collatz();

    for(int i = 1; i <= 100000; i++){
      System.out.println(i + "\tnum of steps: " + c.collatz(i));
    }

    System.out.println("Longest path was at " + c.max + " with " + c.maxsteps + " steps.");
  }

  int collatz(int start) {
    int cnt = 0;
    int initval = start;
    while(start != 1) {
      cnt++;
      if(start < 1000000 && prev[start] != 0) {
        start = prev[start];
        continue;
      }
      start = start % 2 == 0 ? start/2 : 3*start + 1;
    }
    prev[start] = cnt;
    if(cnt > max){
      max = initval;
      maxsteps = cnt;
    }
    return cnt;
  }
}