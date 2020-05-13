3
https://raw.githubusercontent.com/SimonHGR/safari-threads/master/src/main/java/queue/BetterYetQueue.java
package queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BetterYetQueue<E> {
  private ReentrantLock lock = new ReentrantLock();
  private Condition notFull = lock.newCondition();
  private Condition notEmpty = lock.newCondition();

  private E[] data = (E[])(new Object[10]);
  private int count = 0;

  public void put(E e) throws InterruptedException {
    lock.lock(); // can be interruptible, or with timeout!
    try {
      while (count == 10)
        notFull.await();
      data[count++] = e;
      notEmpty.signal();
    } finally {
      lock.unlock();
    }
  }

  public E take() throws InterruptedException {
    lock.lock();
    try {
      while (count == 0) // MIGHT wake up for wrong reasons (OS limitation)
        notEmpty.await();
      E rv = data[0];
      System.arraycopy(data, 1, data, 0, --count);
      notFull.signal();
      return rv;
    } finally {
      lock.unlock();
    }
  }

  public static void main(String[] args) {
    BetterYetQueue<int[]> queue = new BetterYetQueue<>();
    new Thread(()-> {
      System.out.println("Producer starting...");
      for (int i = 0; i < 10_000; i++) {
        int [] data = {i, 0};
        try {
          if (i < 100) {
            Thread.sleep(1);
          }
          data[1] = i;
          if (i == 5_000) {
            data[0] = -99;
          }
          queue.put(data); data = null; // SIMON SAYS!!!
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      System.out.println("Producer ending...");
    }).start();
    new Thread(()-> {
      System.out.println("Consumer starting...");
      for (int i = 0; i < 10_000; i++) {
        try {
          int [] data = queue.take();
          if (data[0] != i || data[1] != i) {
            System.out.println("**** ERROR at index " + i);
          }
          if (i > 9_900) {
            Thread.sleep(1);
          }
        } catch (InterruptedException ie) {
          ie.printStackTrace();
        }
      }
      System.out.println("Consumer ending...");
    }).start();
  }
}
