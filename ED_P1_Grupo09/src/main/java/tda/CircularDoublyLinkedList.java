/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tda;

/**
 *
 * @author asala
 */
public class CircularDoublyLinkedList<E> implements List<E>, Comparator<E>{
    
    private DoublyNodeList<E> last;

    public CircularDoublyLinkedList() {
        this.last = null;
    }
    
    public DoublyNodeList<E> getHeader(){
        return last.getNext();
    }
    public void setLast(DoublyNodeList<E> last) {
        this.last = last;
    }

    public DoublyNodeList<E> getNext() {
        return getHeader();
    }

    public DoublyNodeList getProvius() {
        if(last.getNext().getPrevious()==null){
            return last;
        }
        return last.getPrevious();
        
    }
    
    @Override
    public String toString(){
        String s="";
        for (DoublyNodeList<E> n=getHeader(); n!=last; n=n.getNext()) {
            s+=n.getContent()+", ";
        }
        s+=last.getContent();
        return s;
    }
    
    @Override
    public boolean addLast(E e) {
        if (e!=null){
            DoublyNodeList<E> newNode = new DoublyNodeList<>(e);
            if (last==null){
                last = newNode; 
                last.setPrevious(last);
                last.setNext(last);
            } else {
                DoublyNodeList<E> header = getHeader();
                last.setNext(newNode);
                newNode.setPrevious(last);
                newNode.setNext(header);
                header.setPrevious(newNode);
                last = newNode;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public Iterator<E> iterator() {
        Iterator<E> it=new Iterator<E>() {
            DoublyNodeList<E> cursor = getHeader();
                
            @Override
            public boolean hasNext() {
                return cursor != last;
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
        List<E> intersection = new CircularDoublyLinkedList<>();
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
