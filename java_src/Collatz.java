class Collatz {
  public static void main() {
    System.out.println("collatz");
  }

  int collatz(int start) {
    int cnt = 0;
    while(start != 1) {
      cnt++;
      start = start % 2 == 0 ? start/2 : 3*start + 1;
    }
    return cnt;
  }
}