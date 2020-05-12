1
https://raw.githubusercontent.com/FabianCristancho/Grammatical-Tree-LF/master/src/model/SimpleNode.java
package model;

public class SimpleNode <T> {
  protected T info;
  protected SimpleNode <T> next;

  public SimpleNode(T info, SimpleNode<T> next) {
    this.info = info;
    this.next = next;
  }
  
  public SimpleNode(T info) {
    this.info = info;
    this.next = null;
  }

  public T getInfo() {
    return info;
  }

  public void setInfo(T info) {
    this.info = info;
  }

  public SimpleNode<T> getNext() {
    return next;
  }

  public void setNext(SimpleNode<T> next) {
    this.next = next;
  }
}
