38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/Iterator%20Pattern/src/BreadthFirstIterator.java
public class BreadthFirstIterator implements Iterator {
    // This would contain all class variables and
    // info required to do breadth first operation
    int startNode;
    public BreadthFirstIterator(int startNode) {
        this.startNode = startNode;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public GraphNode next() {
        return null;
    }
}
