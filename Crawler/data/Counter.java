3
https://raw.githubusercontent.com/SimonHGR/safari-threads/master/src/main/java/runnables/Counter.java
package runnables;

class GoodValue implements Runnable {
  long value = 0;
  @Override
  public void run() {
    for (int i = 0; i < 2_000_000; i++) {
      synchronized(this) {
        value++;
      }
    }
  }
}

public class Counter {
  public static void main(String[] args) throws Throwable {
    GoodValue v = new GoodValue();
    Thread t1 = new Thread(v);
    t1.start();
    Thread t2 = new Thread(v);
    t2.start();
    t1.join();
    t2.join();
    System.out.println("Value of v.value is " + v.value);
  }
}
