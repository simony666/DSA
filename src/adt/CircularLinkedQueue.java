package adt;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author student
 */
public class CircularLinkedQueue<T> implements QueueInterface<T> {
    // only allow one external ireference
    Node lastNode;
    public CircularLinkedQueue(){
        lastNode = null;
    }
    
      public void enqueue(T newEntry){
         Node newNode = new Node(newEntry , null);
         
         // is it adding the first node to the empty queue
         if(isEmpty()){
             newNode.next = newNode; // self referencing
         }else{ // adding node to the existing queue with nodes
             newNode.next = lastNode;
             lastNode.next = newNode;
         }
          lastNode = newNode;
      }

  public T dequeue(){
       T front = null;
          
          if(!isEmpty()){
              front = getFront(); //obtain the node's data
              
              if(lastNode.next == lastNode){ 
                  lastNode = null;
              }else{
                  lastNode.next = lastNode.next.next;
              }
          }
          return front;
  }

  public T getFront(){
      T frontData = null; 
      if(!isEmpty()){ //lastNode.next -> always pointing to first inserted node
          frontData = lastNode.next.data;
      }
      return frontData;
  }

 
  public boolean isEmpty(){
      return lastNode == null;
  }


  public void clear(){
      lastNode = null;
  }
  
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
