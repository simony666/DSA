/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt;

import java.util.Iterator;

/**
 *
 * @author Eddie Chua
 */
public interface DynamicLinkedListInterface <T> {
    void add(T data);
    
    int size();
    
    boolean isEmpty();
    
    T get(int index);

    public Iterator<T> iterator();
}
