1
https://raw.githubusercontent.com/Luke-Grammer/IMDB-Database-Application/master/src/search/SearchManager.java
/*
 * SearchManager.java
 * Luke Grammer, Lauren Haylock, and Davis Beilue
 * CSCE 315-907
 * 10/15/2019
 */

package search;

import java.util.*;

import data.DatabaseManager;
import data.model.Person;

/**
 * The Class SearchManager is used to search through a graph of actor nodes in
 * order to find the connection between two actors and the links between them.
 */
public class SearchManager {

	/** The database manager to provide the low level database access functions. */
	DatabaseManager manager;

	/**
	 * Basic field-based constructor for search manager.
	 *
	 * @param manager an existing instance of a Database Manager
	 */
	public SearchManager(DatabaseManager manager) {
		this.manager = manager;
	}

	/**
	 * Search for and return a string containing the names and titles of all of the
	 * links between two random actors.
	 *
	 * @param actor1Name  the name of the first actor (the root node in the search)
	 * @param actor2Name  the name of the second actor (the goal node in the search)
	 * @param excludeName the name of a person to exclude from the search.
	 * @return a printable string containing the actor names and links between them
	 */
	public String doSearch(String actor1Name, String actor2Name, String excludeName) {
		String return_val = "";
		Person actor1 = null;
		Person actor2 = null;
		Person exclude = null;

		// Get the information for the first person in the search
		ArrayList<Person> people = manager.getPersonInfo(actor1Name.toLowerCase(), null, null, null, null, null, true);
		if (people.size() > 0) {
			actor1 = people.get(0);
			if (manager.personJobSearch(actor1, "movie", null, null, null, null, null, null, null, null, null, null, null,
					null, true).size() == 0) {
				return "\'" + actor1Name + "\' did not act in any movies!";
			}
			
		} else {
			return "Could not find person \'" + actor1Name + "\'!";
		}


		// Get the information for the second person in the search
		people = manager.getPersonInfo(actor2Name.toLowerCase(), null, null, null, null, null, true);
		if (people.size() > 0) {
			actor2 = people.get(0);
			if (manager.personJobSearch(actor2, "movie", null, null, null, null, null, null, null, null, null, null, null,
					null, true).size() == 0) {
				return "\'" + actor2Name + "\' did not act in any movies!";
			}
		} else {
			return "Could not find person \'" + actor2Name + "\'!";
		}

		// Get the information for the person to exclude in the results
		if (excludeName != null) {
			people = manager.getPersonInfo(excludeName.toLowerCase(), null, null, null, null, null, true);
			if (people.size() > 0) {
				exclude = people.get(0);
			} else {
				return "Could not find person \'" + excludeName + "\'!";
			}
		}

		ActorNode startNode = new ActorNode(actor1.getPersonID(), null, null, 0, 0);
		ActorNode finalNode = null;
		Boolean exitFound = false;

		PriorityQueue<ActorNode> searchQueue = new PriorityQueue<ActorNode>();
		HashSet<String> discovered = new HashSet<String>();

		// Check if the starting node and the goal node are the same
		if (actor1.getPersonID().equals(actor2.getPersonID())) {
			return "Actor 1 and Actor 2 are the same!\n";
		}

		System.out.println("Starting search:");
		System.out.println("Actor 1: " + actor1.getPersonID());
		System.out.println("Actor 2: " + actor2.getPersonID());
		if (exclude != null) {
			System.out.println("Exclusion: " + exclude.getPersonID());
		}
		System.out.println();

		// Add the starting node to the search queue and mark as discovered
		searchQueue.add(startNode);
		discovered.add(startNode.getID());

		// While the goal has not been found and the search queue is not empty
		while (searchQueue.size() > 0 && !exitFound) {
			// Remove the next item from the search queue
			ActorNode currentNode = searchQueue.remove();
			System.out.println(
					"\nPopping " + currentNode.getID() + " from queue, priority: " + currentNode.getPriority() + "\n");
			if (exclude != null) {
				// Skip if the actor is excluded
				if (currentNode.getID().equals(exclude.getPersonID())) {
					continue;
				}
			}

			// Expand the node, finding all children
			manager.setConnectedPeople(currentNode, actor2);
			ArrayList<ActorNode> children = currentNode.getChildren();

			// Print the children information
			System.out.print("Children: ");

			for (ActorNode node : children) {
				System.out.print(node.getID() + " ");
			}

			System.out.println("\n");

			// Search all children to see if they match the goal
			for (int i = 0; i < children.size(); i++) {

				ActorNode newElement = children.get(i);

				// Final actor is found
				if (newElement.getID().equals(actor2.getPersonID())) {
					System.out.println("Found target!");
					exitFound = true;
					finalNode = newElement;
					break;
				}

				if (discovered.contains(newElement.ID)) {
					continue;
				} else {
					discovered.add(newElement.getID());
					searchQueue.add(newElement);
				}
			}
		}

		// Form the string expressing the link between the two actors
		if (!exitFound) {
			return_val = "Could not locate link between " + actor1Name + " and " + actor2Name + "!\n";
		} else {
			ActorNode currentNode = finalNode;
			if (currentNode.getDepth() == 1) {
				return_val += "There is one degree of separation:\n";
			} else {
				return_val += "There are " + currentNode.getDepth() + " degrees of separation:\n";
			}
			while (currentNode.getParent() != null) {
				return_val += manager.getPersonByID(currentNode.getID()).getName() + " was in "
						+ manager.getTitleByID(currentNode.getTitle()).getTitle() + " with "
						+ manager.getPersonByID(currentNode.getParent().getID()).getName() + "\n";
				currentNode = currentNode.getParent();
			}
		}

		return return_val;
	}
}
