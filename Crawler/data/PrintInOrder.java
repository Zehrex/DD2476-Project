1
https://raw.githubusercontent.com/nishantc1527/LeetCode/master/src/PrintInOrder.java
public class PrintInOrder {

    private boolean firstDone, secondDone;

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        firstDone = true;
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while(!firstDone);
        printSecond.run();
        secondDone = true;
    }

    public void third(Runnable printThird) throws InterruptedException {
        while(!secondDone);
        printThird.run();
    }

}
