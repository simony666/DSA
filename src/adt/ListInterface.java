package adt;

import java.util.Iterator;

public interface ListInterface<T> extends Iterable<T> {

    public boolean add(T newEntry);

    public boolean add(int newPosition, T newEntry);

    public T remove(int givenPosition);

    public void clear();

    public boolean replace(int givenPosition, T newEntry);

    public T getEntry(int givenPosition);

    public boolean contains(T anEntry);

    public int getNumberOfEntries();
    
    public int indexOf(T element);

  public boolean isFull();

  
    public boolean isEmpty();

    public Iterator<T> getIterator();

    @Override
    Iterator<T> iterator();
}
