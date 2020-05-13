1
https://raw.githubusercontent.com/FabianCristancho/Grammatical-Tree-LF/master/src/model/SimpleList.java
package model;

import java.util.Comparator;

public class SimpleList<T> {
    protected SimpleNode<T> head;

    public SimpleList() {
        this.head = null;
    }

    public SimpleNode<T> getHead() {
        return head;
    }

    public void setHead(SimpleNode<T> head) {
        this.head = head;
    }
    
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public void add(T info) {
        if (head == null) {
            head = new SimpleNode(info);
        } else {
            SimpleNode aux = head;
            while (aux.next != null) {
                aux = aux.next;
            }
            aux.next = new SimpleNode(info);
        }
    }

    public void insert(T info) {
        head = new SimpleNode(info, head);
    }


    public void delete(T info) {
        if (head != null) {
            SimpleNode aux = head;
            SimpleNode ant = null;
            while (aux != null && info != aux.info) {
                ant = aux;
                aux = aux.next;
            }
            if (info == aux.info) {
                if (aux == head) {
                    head = aux.next;
                } else {
                    ant.next = aux.next;
                }
            }
        }
    }


}
