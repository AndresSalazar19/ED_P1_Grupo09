/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tda;

/**
 *
 * @author asala
 */
public class ArrayList<E> implements List<E>, Comparator<E>{
    
    private E[] elements = null; //arreglo de elementos genericos
    private int capacity = 100;
    private int effectiveSize;
    
    public ArrayList (){
        elements = (E[])(new Object[capacity]); // SI FUNCIONA con Casting permitido con el arrayList
        effectiveSize = 0;
    }
    
    private boolean isFull(){
        return effectiveSize == capacity;
    }
 
    @Override
    public boolean addLast(E e) {
       if (e == null) {
            return false;
        }
        if (isFull()) {
            addCapacity();
        }
        elements[effectiveSize] = e;
        effectiveSize++;
        //elements[effectiveSize++] = e; otra forma de hacer las 2 lineas anteriores
        return true;
    }
   
    @Override
    public boolean isEmpty() {
        return effectiveSize == 0;
    }

    private void addCapacity() {
        E[] tmp = (E[]) new Object[capacity * 2];
        for (int i = 0; i < capacity; i++){
            tmp[i] = elements[i];
        }
        elements = tmp;
        capacity = capacity * 2;
    }
    
    public String toString() {
        String s="[";
        for (int i=0; i<effectiveSize-1; i++) {
            s+=elements[i]+", ";
        }
        s+=elements[effectiveSize-1]+"]";
        return s;
    }
    
    public Iterator<E> iterator(){
            Iterator<E> it=new Iterator<E>() {
                int cursor = 0;
                @Override
                public boolean hasNext() {
                    return cursor < effectiveSize;
                }

                @Override
                public E next() {
                    E e=elements[cursor];
                    cursor++;
                    return e;
                }
            };
        return it;   
    }
    
    @Override
    public E find(Comparator<E> comp, E elemento){
        E compare=null;
        for (E e1:this){
            if (comp.compare(e1, elemento) == 0){
                compare = e1;
            }
        }
        return compare;
    }
    
    @Override
    public List<E> findAll(Comparator<E> comp, E elemento){
        List<E> intersection = new ArrayList<>();
        for (E e1:this){
            if (comp.compare(e1, elemento)==0){
                intersection.addLast(e1);
            }
        }
        return intersection;
    }

    @Override
    public int compare(E e1, E e2) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
