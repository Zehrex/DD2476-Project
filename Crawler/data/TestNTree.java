1
https://raw.githubusercontent.com/FabianCristancho/Grammatical-Tree-LF/master/tst/tests/TestNTree.java
package tests;

import java.util.Comparator;

import model.NTree;
import model.NTreeNode;
import model.SimpleList;
import model.SimpleNode;

public class TestNTree {
	public static void main(String[] args) {
		Comparator<String> comparator = new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		};
		NTree<String> nTree = new NTree<String>(comparator);
		
		nTree.add("Root");
		nTree.add("1.1", "Root");
		nTree.add("1.2", "Root");
		nTree.add("1.3", "Root");
		
		SimpleList<NTreeNode<String>> sl = nTree.getRoot().getChilds();
		SimpleNode<NTreeNode<String>> aux = sl.getHead();
		
		while(aux!=null) {
			System.out.println(aux.getInfo().getInfo());
			aux = aux.getNext();
		}
	}
}
