3
https://raw.githubusercontent.com/SimonHGR/safari-threads/master/src/main/java/runnables/Example1.java
package runnables;

class MyJob implements Runnable {
  int i = 0;
  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName()
        + " MyRunnable starting...");
    for (; i < 10_000; i++) {
      System.out.println(Thread.currentThread().getName()
          + " i is " + i);
    }
    System.out.println(Thread.currentThread().getName()
        + " MyRunnable ending...");
  }
}

public class Example1 {
  public static void main(String[] args) {
    Runnable r = new MyJob();
    Thread t = new Thread(r);
//    t.run(); // WRONG!!!!
    t.start(); // RIGHT!!!!
    Thread t2 = new Thread(r);
    t2.start();
    System.out.println(Thread.currentThread().getName()
        + " main method ending...");
  }
}
