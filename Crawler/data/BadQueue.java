3
https://raw.githubusercontent.com/SimonHGR/safari-threads/master/src/main/java/queue/BadQueue.java
package queue;

public class BadQueue<E> {
  private E[] data = (E[])(new Object[10]);
  private int count = 0;

  public void put(E e) throws InterruptedException {
    synchronized (this) {
      while (count == 10)
        this.wait();
      data[count++] = e;
      this.notify();
    }
  }

  public E take() throws InterruptedException {
    synchronized (this) {
      while (count == 0)
        this.wait();
      E rv = data[0];
      System.arraycopy(data, 1, data, 0, --count);
      this.notify();
      return rv;
    }
  }

  public static void main(String[] args) {
    BadQueue<int[]> queue = new BadQueue<>();
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
