/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

/**
 *
 * @author student
 */
public class LinkedStack<T> implements StackInterface<T>{
    // one reference reference (pointer)
    
    Node topNode;
    public LinkedStack(){
        topNode = null;
    }
    
    public void push(T newElement){
        Node newNode = new Node(newElement, topNode);
            topNode = newNode;
        }
    

  
  public T pop(){
      T top = peek(); // get the return value from peek
      if(topNode !=null){
          topNode = topNode.next;
      }
      return top;
  }

  // to capture the node data, before being remove
  public T peek(){
      T data = null;
      if(topNode != null){
          data = topNode.data;
          
      }
      return data;
  }

  
  public boolean isEmpty(){
      return topNode == null;
  }

  public void clear(){
      topNode = null;
  }

    
    //linked = node
    private class Node{
        T data;
        Node next;
        
        public Node(T data)
        {
            this.data = data;
            this.next = null;
            
        }
        
         public Node(T data, Node next)
        {
            this.data = data;
            this.next = next;
            
        }
        
    }
    
}
