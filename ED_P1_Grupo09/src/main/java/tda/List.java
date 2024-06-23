/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tda;

import java.util.Comparator;

/**
 *
 * @author asala
 * @param <E>
 */

public interface List<E> extends Iterable<E>{
    
    public boolean addLast(E e); //Inserta el elemento e al final
    public boolean isEmpty();
    public E find(Comparator<E> comp, E elemento); //Comparador
    public List<E> findAll(Comparator<E> comp, E elemento); //FindAll
}