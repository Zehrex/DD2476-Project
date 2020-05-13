1
https://raw.githubusercontent.com/FabianCristancho/Grammatical-Tree-LF/master/src/model/NTree.java
package model;

import java.util.Comparator;

public class NTree <T>{
    private NTreeNode <T> root;
    private Comparator<T> comparator;

    public NTree(Comparator<T> comparator) {
        this.root = null;
        this.comparator = comparator;
    }

    public NTreeNode<T> getRoot() {
        return root;
    }
    
    
    public boolean isEmpty(){
       return this.root==null;
    }
    
    public boolean add(T info){
      this.root = new NTreeNode<>(info);
      return true;
    }

    public boolean add(T info, T parent){
        NTreeNode<T> auxParent = search(parent);
        if (auxParent!=null) {
            auxParent.childs.insert(new NTreeNode<>(info));
            return true;
        }
        else {
             return false;
        }
    }    
    
    public NTreeNode<T> search(T info){
      return search(root, info);  
    }
    
    private NTreeNode <T> search(NTreeNode<T> auxRoot, T info){
       if (comparator.compare(auxRoot.info,info)!=0) {
           SimpleNode <NTreeNode<T>> aux = auxRoot.childs.getHead();
           while(aux!=null) {
             NTreeNode<T> tmp = search(aux.getInfo(),info);
             if (tmp!=null) {
                return  tmp;
             }
             aux = aux.getNext();
           }
           return null;
       }
       else {
          return auxRoot;
       }
    }
    
}
