package adt;

import entity.Student;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements ListInterface<T> {

    private T[] array;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 5;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initialCapacity) {
        numberOfEntries = 0;
        array = (T[]) new Object[initialCapacity];
    }

    @Override
    public boolean add(T newEntry) {
        if (isArrayFull()) {
            doubleArray();
        }

        array[numberOfEntries] = newEntry;
        numberOfEntries++;
        return true;
    }

    @Override
    public boolean add(int newPosition, T newEntry) {
        boolean isSuccessful = true;

  @Override
  public void clear() {
    numberOfEntries = 0;
  }

  @Override
  public boolean replace(int givenPosition, T newEntry) {
    boolean isSuccessful = true;

    if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
      array[givenPosition - 1] = newEntry;
    } else {
      isSuccessful = false;
    }

    return isSuccessful;
  }

  @Override
  public T getEntry(int givenPosition) {
    T result = null;

    if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
      result = array[givenPosition - 1];
    }

    return result;
  }

  @Override
  public boolean contains(T anEntry) {
    boolean found = false;
    for (int index = 0; !found && (index < numberOfEntries); index++) {
      if (anEntry.equals(array[index])) {
        found = true;
      }
    }
    return found;
  }

  @Override
  public int getNumberOfEntries() {
    return numberOfEntries;
  }

  @Override
  public boolean isEmpty() {
    return numberOfEntries == 0;
  }

  @Override
  public boolean isFull() {
    return false;
  }

  private void doubleArray() {
    T[] oldArray = array;
    array = (T[]) new Object[oldArray.length * 2];
    for (int i = 0; i < oldArray.length; i++) {
      array[i] = oldArray[i];
    }
  }

  private boolean isArrayFull() {
    return numberOfEntries == array.length;
  }

  @Override
  public String toString() {
    String outputStr = "";
    for (int index = 0; index < numberOfEntries; ++index) {
      outputStr += array[index] + "\n";
    }

    return outputStr;
  }
  
  public int indexof(T element){
      if(contains(element)){
            for (int index = 1; index < numberOfEntries; index++) {
              if (element.equals(array[index-1])) {
                return index;
              }
            }
            makeRoom(newPosition);
            array[newPosition - 1] = newEntry;
            numberOfEntries++;
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    @Override
    public T remove(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            result = array[givenPosition - 1];

            if (givenPosition < numberOfEntries) {
                removeGap(givenPosition);
            }

            numberOfEntries--;
        }

        return result;
    }
  }
  
    @Override
    public Iterator<T> getIterator() {
      return new ArrayListIterator();
  }

  private class ArrayListIterator implements Iterator<T> {
      private int currentIndex;

      public ArrayListIterator() {
          currentIndex = 0;
      }

      @Override
      public boolean hasNext() {
          return currentIndex < numberOfEntries;
      }

      @Override
      public T next() {
          if (!hasNext()) {
              throw new NoSuchElementException();
          }
          return array[currentIndex++];
      }
  }
  
}
