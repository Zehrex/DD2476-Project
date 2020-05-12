3
https://raw.githubusercontent.com/SimonHGR/safari-threads/master/src/main/java/runnables/BadCounter.java
package runnables;

class Value implements Runnable {
  volatile long value = 0; // NO PROTECTION against read-modify-write
  @Override
  public void run() {
    for (int i = 0; i < 2_000_000; i++) {
      value++;
    }
  }
}
public class BadCounter {
  public static void main(String[] args) throws Throwable {
    Value v = new Value();
//    v.run();
//    v.run();
    Thread t1 = new Thread(v);
    t1.start();
    Thread t2 = new Thread(v);
    t2.start();
//    Thread.sleep(3000);
    t1.join(); // Handles timing
    t2.join();
    System.out.println("Value of v.value is " + v.value);
  }
}
