/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tda;

/**
 *
 * @author asala
 * @param <E>
 */
public class CircularNodeList<E> {
    private E content;
    private CircularNodeList<E> next;
    private CircularNodeList<E> previous;
    
    public CircularNodeList(E content){
        this.content = content;
        this.next = this; // Instancia de un nodo el next ya no apunta a null sino a si mismo
        this.previous = this;  //Apunta a si mismo igualmente al ser circular.
    }
    
   public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public CircularNodeList<E> getNext() {
        return next;
    }

    public void setNext(CircularNodeList<E> next) {
        this.next = next;
    }

    public CircularNodeList<E> getPrevious() {
        return previous;
    }

    public void setPrevious(CircularNodeList<E> previous) {
        this.previous = previous;
    }
}

