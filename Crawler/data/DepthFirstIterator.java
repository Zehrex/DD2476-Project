38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/Iterator%20Pattern/src/DepthFirstIterator.java
public class DepthFirstIterator implements Iterator {
    // This would contain all class variables and
    // info required to do depth first operation
    int startNode;

    public DepthFirstIterator(int startNode) {
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
