27
https://raw.githubusercontent.com/algorithmzuo/algorithmbasic2020/master/src/class10/Graph.java
package class10;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {
	public HashMap<Integer, Node> nodes;
	public HashSet<Edge> edges;
	
	public Graph() {
		nodes = new HashMap<>();
		edges = new HashSet<>();
	}
}
