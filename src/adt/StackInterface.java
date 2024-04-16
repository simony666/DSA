/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt;

/**
 *
 * @author student
 */
public interface StackInterface<T> {

  
  public void push(T newElement);

  
  public T pop();

  
  public T peek();

  
  public boolean isEmpty();

  public void clear();
} // end StackInterface