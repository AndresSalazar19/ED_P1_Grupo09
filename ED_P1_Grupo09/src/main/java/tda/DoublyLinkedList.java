/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tda;

/**
 *
 * @author asala
 */

public class DoublyLinkedList<E> implements List<E>, Comparator<E>{
     private DoublyNodeList<E> header;
    private DoublyNodeList<E> last;
    
    public DoublyLinkedList(){
        this.header = null;
        this.last = null;
    }

     @Override
    public boolean isEmpty(){
        return header == null && last == null;
    }
    
    public int size() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public DoublyNodeList<E> getHeader() {
        return header;
    }

    public void setHeader(DoublyNodeList<E> header) {
        this.header = header;
    }

    public DoublyNodeList<E> getLast() {
        return last;
    }

    public void setLast(DoublyNodeList<E> last) {
        this.last = last;
    }
    
    public DoublyNodeList<E> getPrevious(DoublyNodeList<E> node){
        return node.getPrevious();
    }
   
    private void recorrerHaciaAtras(){
        DoublyNodeList<E> n;
        for (n = last ; n != header; n = n.getPrevious()){
        }
    }
    
   
    @Override
    public boolean addLast(E e)
    {
        if (e != null){
            DoublyNodeList<E> newNode = new DoublyNodeList<>(e);
            if (header == null){
                header = newNode;
            }
            else if (last!=null){
                newNode.setPrevious(last);
                last.setNext(newNode);
                this.setLast(newNode);
            } else {
                last = newNode;
                header.setNext(newNode);
                last.setPrevious(header);
            }
            return true;
        }
        return false;
    }
    

    public String toString(){
        String s="";
        for (DoublyNodeList<E> n=header; n!=null; n=n.getNext()) {
            s+=n.getContent()+", ";
        }
        return s;
    }
    

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it=new Iterator<E>() {
            DoublyNodeList<E> cursor = header;
                
            @Override
            public boolean hasNext() {
                return cursor != null;
            }

            @Override
            public E next() {
                E e = cursor.getContent();
                cursor = cursor.getNext();
                return e;
            }
        };
        return it;
    }

    @Override
    public E find(Comparator<E> comp, E elemento) {
       E compare=null;
        for (E e1:this){
            if (comp.compare(e1, elemento) == 0){
                compare = e1;
            }
        }
        return compare;
    }

    @Override
    public List<E> findAll(Comparator<E> comp, E elemento) {
        List<E> intersection = new DoublyLinkedList<>();
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
