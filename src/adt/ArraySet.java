
package adt;
import java.util.Iterator;

public class ArraySet<T> implements SetInterface{
    T[] setArray;
    int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 25;
    //constructor
    public ArraySet(){
        this(DEFAULT_CAPACITY);
    }
    //another constructor
    public ArraySet(int initialCapacity){
        numberOfEntries = 0;
        //initialize the array-->int []a = new int[5];
        setArray = (T[]) new Object[initialCapacity];
    }
    
    public boolean add(T newElement){//only accpet the unique value
        //if the newElement is a duplicate value , return false
        for (int i = 0; i < numberOfEntries; i++){
            if(setArray[i].equals(newElement)){
                return false;
            }
        }
        
        //if the newElement is unique
        setArray[numberOfEntries] = newElement;
        numberOfEntries++;
        return true;
        
    }
    
    public boolean remove(T anElement){
        for (int i = 0; i < numberOfEntries; i++){
            if(setArray[i].equals(anElement)){
                removeGap(i);
                numberOfEntries--;
                return true;
            }
        }
            return false;
    }        
    
    private void removeGap(int index){
        for(int i = index; i < nubmerOfEntries ; i++){
            //       2             3
            setArray[i] = setArray[i+1];
        }
    }
    // accepting the super interface class type
    public boolean checkSubset(SetInterface anotehrSet){
        if(anotherSet instanceof Arrayset){
            //convert
            ArraySet aSet = (ArraySet) anotherSet;
            //if the given set size (3 times) is large than the current (2 times)
            if (aSet.numberOfEntries>this.numberOfEntries){
                return false;
            }
            //compare see the given set item is appear in the current set or not
            for (int i =0; j<this.numberOfEntries;i++){//given set
                boolean found = false;
                for (int j=0; j < this.numberOfEntries && !found; j++){// current set
                    if (aSet.setArray[i].equals(this.setArray[j])){
                        found = true;
                    }
                }
                if(!found){
                    return true;
                }
            
            }
        }
        return false;
    }
    
    public void union(SetInterface anotherSet){
        //instanceof a method given by java to check the pass in object (super class type)
        // is it referencing to a particular sub class object
        if (anotherSet instanceof Arrayset){
                ArraySet aSet = (ArraySet) anotherSet;
                for (int i = 0;i<aSet.numberOfEntries;i++){
                    add((T)aSet.setArray[i]);
                }
            }
        }
    
    
    public SetInterface intersection(SetInterface anotherSet){
        SetInterface resultSet = new ArraySet();

            if (anotherSet instanceof ArraySet){
                ArraySet aSet = (ArraySet) anotherSet;
                for(int i =0 ; i<aSet.numberOfEntries; i++){
                    boolean found = false;
                    for(int j=0; j<this.numberOfEntries && !found; j++){
                        if(aSet.setArray[i].equals(this.setArray[j])){
                        found = true;
                    }
                }
                if(found){
                    resultSet.add((T)aSet.setArray[i]);
                }
            }
        }
        return intersectionSet;
    }
    
    public boolean isEmpty(){
        return numberOfEntries == 0;
    }
    
    //return an iterator object -> thus need a class to construct the object
    public Iterator<T> getIterator(){
        return new SetArrayIterator();
    }
    
    //define a private inner class that compulsory to implement Iterator interface
    //from the iterator interface-> 2 compulsory methods need to override
    //next ==> returning the visited item
    //hasNext ==> to check is it nay item still available to be visit 
    private class SetArrayIterator implements Iterator<T>{
        //attrribute -> counter
        int nextIndex;
        //constructor
        public SetArrayIterator(){
            nextIndex = 0;
        }
        
        public boolean hasNext(){
            //
            //        0            3
            return nextIndex<numberOfEntries;
        }
        
        public T next(){
            T item = null;
            if (hasNext()){// true , still got item
                item = setArray[nextIndex++];
 
            }else{
                item = null;
            }
            return item;
            
        }
    }
}
