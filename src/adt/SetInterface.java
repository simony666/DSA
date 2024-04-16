
package adt;

import java.util.Iterator;

public interface SetInterface<T> {
    public boolean add(T newElement);
    public boolean remove(T anElement);
    public boolean checkSubset(SetInterface anotherSet);
    public void union(SetInterface anotherSet);
    public SetInterface intersection(SetInterface anotherSet);
    public boolean isEmpty();
    //construct an iterator method that returing an iterator object
    public Iterator<T> getIterator();
    
}

/* 
iterator -> special object create from Iterator interface for the travelling purpose
         -> visit each of the object from the collection
         -> example when visiting, copy out the item value for display or any processing
*/