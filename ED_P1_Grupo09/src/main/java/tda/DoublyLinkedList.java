/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tda;

import java.util.Comparator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author asala
 * @param <E>
 */

public class DoublyLinkedList<E> implements List<E>, Comparator<E> {
    private DoublyNodeList<E> header;
    private DoublyNodeList<E> last;

    public DoublyLinkedList() {
        this.header = null;
        this.last = null;
    }

    @Override
    public boolean isEmpty() {
        return header == null && last == null;
    }

    @Override
    public int size() {
        int count = 0;
        DoublyNodeList<E> current = header;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
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

    public DoublyNodeList<E> getPrevious(DoublyNodeList<E> node) {
        return node.getPrevious();
    }


    @Override
    public boolean addLast(E e) {
        if (e != null) {
            DoublyNodeList<E> newNode = new DoublyNodeList<>(e);
            if (header == null) {
                header = newNode;
                last = newNode;
            } else {
                newNode.setPrevious(last);
                last.setNext(newNode);
                last = newNode;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean addFirst(E e) {
        if (e != null) {
            DoublyNodeList<E> newNode = new DoublyNodeList<>(e);
            if (header == null) {
                header = newNode;
                last = newNode;
            } else {
                newNode.setNext(header);
                header.setPrevious(newNode);
                header = newNode;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean add(int index, E element) {
        if (index < 0 || (index > 0 && header == null)) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            return addFirst(element);
        }

        DoublyNodeList<E> current = header;
        for (int i = 1; i < index && current.getNext() != null; i++) {
            current = current.getNext();
        }

        if (current.getNext() == null) {
            return addLast(element);
        } else {
            DoublyNodeList<E> newNode = new DoublyNodeList<>(element);
            newNode.setNext(current.getNext());
            newNode.setPrevious(current);
            current.getNext().setPrevious(newNode);
            current.setNext(newNode);
            return true;
        }
    }

    @Override
    public E get(int index) {
        if (index < 0 || header == null) {
            throw new IndexOutOfBoundsException();
        }

        DoublyNodeList<E> current = header;
        for (int i = 0; i < index; i++) {
            if (current.getNext() == null) {
                throw new IndexOutOfBoundsException();
            }
            current = current.getNext();
        }
        return current.getContent();
    }

    @Override
    public E remove(int index) {
        if (index < 0 || header == null) {
            throw new IndexOutOfBoundsException();
        }

        DoublyNodeList<E> current = header;
        if (index == 0) {
            E content = current.getContent();
            header = current.getNext();
            if (header != null) {
                header.setPrevious(null);
            } else {
                last = null;
            }
            return content;
        }

        for (int i = 0; i < index; i++) {
            if (current.getNext() == null) {
                throw new IndexOutOfBoundsException();
            }
            current = current.getNext();
        }

        E content = current.getContent();
        DoublyNodeList<E> prev = current.getPrevious();
        DoublyNodeList<E> next = current.getNext();

        if (prev != null) {
            prev.setNext(next);
        }
        if (next != null) {
            next.setPrevious(prev);
        }
        if (current == last) {
            last = prev;
        }
        return content;
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || header == null) {
            throw new IndexOutOfBoundsException();
        }

        DoublyNodeList<E> current = header;
        for (int i = 0; i < index; i++) {
            if (current.getNext() == null) {
                throw new IndexOutOfBoundsException();
            }
            current = current.getNext();
        }

        E oldContent = current.getContent();
        current.setContent(element);
        return oldContent;
    }

    @Override
    public boolean contains(E element) {
        DoublyNodeList<E> current = header;
        while (current != null) {
            if (Objects.equals(current.getContent(), element)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public void clear() {
        header = null;
        last = null;
    }

    @Override
    public int indexOf(E element) {
        int index = 0;
        DoublyNodeList<E> current = header;
        while (current != null) {
            if (Objects.equals(current.getContent(), element)) {
                return index;
            }
            current = current.getNext();
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E element) {
        int index = 0;
        int lastIndex = -1;
        DoublyNodeList<E> current = header;
        while (current != null) {
            if (Objects.equals(current.getContent(), element)) {
                lastIndex = index;
            }
            current = current.getNext();
            index++;
        }
        return lastIndex;
    }

    @Override
    public E find(Comparator<E> comp, E elemento) {
        for (E e1 : this) {
            if (comp.compare(e1, elemento) == 0) {
                return e1;
            }
        }
        return null;
    }

    @Override
    public List<E> findAll(Comparator<E> comp, E elemento) {
        List<E> intersection = new DoublyLinkedList<>();
        for (E e1 : this) {
            if (comp.compare(e1, elemento) == 0) {
                intersection.addLast(e1);
            }
        }
        return intersection;
    }

    @Override
    public int compare(E e1, E e2) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            DoublyNodeList<E> cursor = header;

            @Override
            public boolean hasNext() {
                return cursor != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E e = cursor.getContent();
                cursor = cursor.getNext();
                return e;
            }
        };
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        throw new UnsupportedOperationException("Not supported yet.");

    }

    @Override
    public Spliterator<E> spliterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (DoublyNodeList<E> n = header; n != null; n = n.getNext()) {
            s.append(n.getContent()).append(", ");
        }
        return s.toString();
    }

}
