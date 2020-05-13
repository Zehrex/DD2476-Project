1
https://raw.githubusercontent.com/Luke-Grammer/IMDB-Database-Application/master/src/search/ActorNode.java
/*
 * ActorNode.java
 * Luke Grammer, Lauren Haylock, and Davis Beilue
 * CSCE 315-907
 * 10/15/2019
 */

package search;

import java.util.ArrayList;

/**
 * The Class ActorNode is used to represent nodes in a graph search linking two
 * actors through a series of actors they performed with in certain titles.
 */
public class ActorNode implements Comparable<ActorNode> {

	/** The unique identifier of the actor in the node. */
	String ID;

	/** The parent node that generated the current node. */
	ActorNode parent;

	/**
	 * The unique identifier of the title that links the parent node with the
	 * current node.
	 */
	String title;

	/** The distance between the root node of the graph and the current node. */
	Integer depth;

	/**
	 * The list of nodes that are children of the current node. This list is
	 * initially empty until the node is expanded.
	 */
	ArrayList<ActorNode> children;

	/**
	 * The priority of the node. This is based on the estimated proximity to the
	 * goal node in the graph and the distance from the root node (to retain search
	 * optimality). Higher numbers indicate lower priority.
	 */
	Integer priority;

	/**
	 * Basic field-based constructor for a new ActorNode.
	 *
	 * @param ID       the identifier of the actor
	 * @param parent   the node corresponding to the parent
	 * @param title    the unique title ID of the title that links the current node
	 *                 to the parent
	 * @param depth    the graph distance from the root node to the current node
	 * @param priority a priority based on estimated proximity to the goal
	 */
	public ActorNode(String ID, ActorNode parent, String title, Integer depth, Integer priority) {
		this.ID = ID;
		this.parent = parent;
		this.title = title;
		this.depth = depth;
		this.priority = priority;
	}

	/**
	 * Gets the list of direct children of the node (the list of people that
	 * appeared in films with the current actor).
	 *
	 * @return the children
	 */
	public ArrayList<ActorNode> getChildren() {
		return children;
	}

	/**
	 * Gets the unique identifier of the actor in the current node.
	 *
	 * @return the unique identifier of the current node's actor
	 */
	public String getID() {
		return ID;
	}

	/**
	 * Gets the distance of the node in the graph to the root.
	 *
	 * @return the depth of the current node
	 */
	public Integer getDepth() {
		return depth;
	}

	/**
	 * Gets the parent of the current node in the graph.
	 *
	 * @return the parent of the current node
	 */
	public ActorNode getParent() {
		return parent;
	}

	/**
	 * Populates the list of children in the current node.
	 *
	 * @param children the new list of direct children
	 */
	public void setChildren(ArrayList<ActorNode> children) {
		this.children = children;
	}

	/**
	 * Gets the unique title ID of the link between the current node and it's parent
	 * in the graph.
	 *
	 * @return the unique title ID corresponding to the link between the current
	 *         node and it's parent
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Gets the priority of the current node.
	 *
	 * @return the priority of the current node in the graph
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * Compare nodes by priority. Used to store the nodes in a priority queue.
	 *
	 * @param arg0 the ActorNode to compare the current node against
	 * @return the result of the comparison of the current nodes priority with the
	 *         argument nodes priority
	 */
	@Override
	public int compareTo(ActorNode arg0) {
		return this.priority.compareTo(arg0.getPriority());
	}
}
