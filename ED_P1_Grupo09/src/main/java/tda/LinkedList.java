/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tda;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 *
 * @author asala
 * @param <E>
 */

public class LinkedList<E> implements List<E>, Comparator<E>{
    
    private NodeList<E> header;
    private NodeList<E> last;
    
    public LinkedList(){
        this.header=null;
        this.last=null;
    }
    public NodeList<E> getHeader() {
        return header;
    }

    public void setHeader(NodeList<E> header) {
        this.header = header;
    }

    public NodeList<E> getLast() {
        return last;
    }

    public void setLast(NodeList<E> last) {
        this.last = last;
    }


    @Override
    public boolean addLast(E e) {
        if(e!=null){
            NodeList<E> newNode = new NodeList<>(e);
            if(last != null){
                last.setNext(newNode);
            }
            last = newNode;
            
            if(header == null){
                header = newNode;
            }
            return true;
        } else {
            return false;
        }  
    }
    
    @Override
    public String toString() {
        String s="[";
        for (NodeList<E> n=header; n!=last; n=n.getNext()) {
            s+=n.getContent()+", ";
        }
        s+=last.getContent()+"]";
        return s;
    }
    

    public E find(Comparator<E> comp, E elemento){
        E compare=null;
        for (E e1:this){
            if (comp.compare(e1, elemento) == 0){
                compare = e1;
            }
        }
        return compare;
    }
    
    public List<E> findAll(Comparator<E> comp, E elemento){
        List<E> intersection = new LinkedList<>();
        for (E e1:this){
            if (comp.compare(e1, elemento)==0){
                intersection.addLast(e1);
            }
        }
        return intersection;
    } 
   
    public int compare(Integer e1, Integer e2){
        if(Objects.equals(e1, e2)){
            return 0;
        }else if(e1>e2){
            return 1;
        } else{
            return -1;
        }
    }

    @Override
    public int compare(E o1, E o2) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addFirst(E e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E get(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean contains(E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int indexOf(E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int lastIndexOf(E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E find(java.util.Comparator<E> comp, E elemento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<E> findAll(java.util.Comparator<E> comp, E elemento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        List.super.forEach(action); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Spliterator<E> spliterator() {
        return List.super.spliterator(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
}

