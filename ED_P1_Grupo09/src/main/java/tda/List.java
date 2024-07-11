/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tda;

import java.util.Comparator;
import modelo.Vehiculo;

/**
 *
 * @author asala
 * @param <E>
 */

public interface List<E> extends Iterable<E> {
    
    boolean addLast(E e); // Inserta el elemento e al final
    boolean addFirst(E e); // Inserta el elemento e al inicio
    boolean add(int index, E element); // Inserta el elemento en la posición especificada
    E get(int index); // Obtiene el elemento en la posición especificada
    E remove(int index); // Elimina el elemento en la posición especificada
    E set(int index, E element); // Reemplaza el elemento en la posición especificada
    boolean isEmpty(); // Verifica si la lista está vacía
    int size(); // Retorna el número de elementos en la lista
    boolean contains(E element); // Verifica si la lista contiene el elemento especificado
    void clear(); // Elimina todos los elementos de la lista
    int indexOf(E element); // Retorna el índice del primer elemento igual al especificado
    int lastIndexOf(E element); // Retorna el índice del último elemento igual al especificado
    E find(Comparator<E> comp, E elemento); // Encuentra un elemento utilizando un comparador
    List<E> findAll(Comparator<E> comp, E elemento); // Encuentra todos los elementos utilizando un comparador
    E removeFirst(); // Elimina y retorna el primer elemento de la lista


}