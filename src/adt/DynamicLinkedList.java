package adt;

import javax.management.DynamicMBean;
import java.util.Iterator;

 public class DynamicLinkedList<T> implements DynamicLinkedListInterface <T>, Iterable<T> {
    private Node<T> head;
    private int size;

    public DynamicLinkedList() {
        head = null;
        size = 0;
    }
    
    @Override
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }
    
     public boolean remove(T data) {
         if (head == null) {
             return false; // List is empty, nothing to remove
         }

         if (head.getData().equals(data)) {
             head = head.getNext();
             size--;
             return true; // Removed from the beginning of the list
         }

         Node<T> current = head;
         Node<T> previous = null;

         while (current != null && !current.getData().equals(data)) {
             previous = current;
             current = current.getNext();
         }

         if (current == null) {
             return false; // Element not found
         }

         previous.setNext(current.getNext());
         size--;
         return true; // Removed from the middle or end of the list
     }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        return current.getData();
    }

    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        public T getData() {
            return data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
    
     @Override
     public Iterator<T> iterator() {
         return new LinkedListIterator();
     }

     private class LinkedListIterator implements Iterator<T> {

         private Node<T> current = head;
         private Node<T> previous = null;
         private boolean removeCalled = false;

         @Override
         public boolean hasNext() {
             return current != null;
         }

         @Override
         public T next() {
             if (!hasNext()) {
                 throw new java.util.NoSuchElementException();
             }

             T data = current.getData();
             previous = current;
             current = current.getNext();
             removeCalled = false;
             return data;
         }

         @Override
         public void remove() {
             if (previous == null || removeCalled) {
                 throw new IllegalStateException();
             }

             // Remove the element
             previous.setNext(current);
             size--;

             // Update references
             if (previous.getNext() == null) {
                 // Adjust tail reference if the last element is removed
             }

             removeCalled = true;
         }
     }


}