3
https://raw.githubusercontent.com/SimonHGR/safari-threads/master/src/main/java/runnables/Stopper.java
package runnables;

public class Stopper {
  public static volatile boolean stop = false;

  public static void main(String[] args) throws Throwable {
    Runnable r = () ->{
      System.out.println("Stopper job started...");
      while (! stop)  // Timing???
        ;
      System.out.println("Stopper job completed...");
    };

    new Thread(r).start();
    System.out.println("started...");
    Thread.sleep(1_000);
    System.out.println("about to set stop flag...");
    stop = true;
    System.out.println("Stop set to "  + stop);
  }
}
