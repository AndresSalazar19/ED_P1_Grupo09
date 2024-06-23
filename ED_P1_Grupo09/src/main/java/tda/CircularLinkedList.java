/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tda;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 *
 * @author asala
 * @param <E>
 */

public class CircularLinkedList<E> implements List<E>, Comparator<E> {
    private CircularNodeList<E> last;

    public CircularNodeList<E> getLast() {
        return last;
    }

    public CircularNodeList<E> getHeader() {
        return last.getNext();
    }

    public void setLast(CircularNodeList<E> last) {
        this.last = last;
    }

    @Override
    public String toString() {
        if (last == null) {
            return "";
        }
        StringBuilder s = new StringBuilder();
        CircularNodeList<E> n = getHeader();
        do {
            s.append(n.getContent()).append(", ");
            n = n.getNext();
        } while (n != getHeader());
        return s.substring(0, s.length() - 2);
    }

    @Override
    public boolean addLast(E e) {
        if (e != null) {
            CircularNodeList<E> newNode = new CircularNodeList<>(e);
            if (last == null) {
                last = newNode;
                last.setNext(last);
            } else {
                CircularNodeList<E> header = getHeader();
                last.setNext(newNode);
                newNode.setNext(header);
                last = newNode;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isEmpty() {
        return last == null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private CircularNodeList<E> cursor = (last != null) ? getHeader() : null;
            private boolean completedCircle = false;

            @Override
            public boolean hasNext() {
                return cursor != null && (!completedCircle || cursor != getHeader());
            }

            @Override
            public E next() {
                if (cursor == null || (completedCircle && cursor == getHeader())) {
                    throw new NoSuchElementException();
                }
                E e = cursor.getContent();
                cursor = cursor.getNext();
                if (cursor == getHeader()) {
                    completedCircle = true;
                }
                return e;
            }
        };
    }

    @Override
    public E find(Comparator<E> comp, E elemento) {
        for (E e : this) {
            if (comp.compare(e, elemento) == 0) {
                return e;
            }
        }
        return null;
    }

    @Override
    public List<E> findAll(Comparator<E> comp, E elemento) {
        List<E> intersection = new CircularLinkedList<>();
        for (E e : this) {
            if (comp.compare(e, elemento) == 0) {
                intersection.addLast(e);
            }
        }
        return intersection;
    }

    @Override
    public int compare(E e1, E e2) {
        return ((Comparable<E>) e1).compareTo(e2);
    }

    @Override
    public boolean addFirst(E e) {
        if (e != null) {
            CircularNodeList<E> newNode = new CircularNodeList<>(e);
            if (last == null) {
                last = newNode;
                last.setNext(last);
            } else {
                CircularNodeList<E> header = getHeader();
                newNode.setNext(header);
                last.setNext(newNode);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean add(int index, E element) {
        if (index < 0 || element == null) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            return addFirst(element);
        }

        CircularNodeList<E> current = getHeader();
        for (int i = 0; i < index - 1; i++) {
            if (current.getNext() == getHeader()) {
                throw new IndexOutOfBoundsException();
            }
            current = current.getNext();
        }

        CircularNodeList<E> newNode = new CircularNodeList<>(element);
        newNode.setNext(current.getNext());
        current.setNext(newNode);
        if (current == last) {
            last = newNode;
        }
        return true;
    }

    @Override
    public E get(int index) {
        if (index < 0 || last == null) {
            throw new IndexOutOfBoundsException();
        }

        CircularNodeList<E> current = getHeader();
        for (int i = 0; i < index; i++) {
            if (current == last) {
                throw new IndexOutOfBoundsException();
            }
            current = current.getNext();
        }
        return current.getContent();
    }

    @Override
    public E remove(int index) {
        if (index < 0 || last == null) {
            throw new IndexOutOfBoundsException();
        }

        CircularNodeList<E> current = getHeader();
        CircularNodeList<E> prev = last;
        for (int i = 0; i < index; i++) {
            if (current == last) {
                throw new IndexOutOfBoundsException();
            }
            prev = current;
            current = current.getNext();
        }

        if (current == last && current.getNext() == last) {
            last = null;
        } else {
            prev.setNext(current.getNext());
            if (current == last) {
                last = prev;
            }
        }
        return current.getContent();
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || element == null) {
            throw new IndexOutOfBoundsException();
        }

        CircularNodeList<E> current = getHeader();
        for (int i = 0; i < index; i++) {
            if (current == last) {
                throw new IndexOutOfBoundsException();
            }
            current = current.getNext();
        }
        E oldContent = current.getContent();
        current.setContent(element);
        return oldContent;
    }

    @Override
    public int size() {
        if (last == null) {
            return 0;
        }

        int count = 1;
        for (CircularNodeList<E> n = getHeader(); n != last; n = n.getNext()) {
            count++;
        }
        return count;
    }

    @Override
    public boolean contains(E element) {
        if (element == null) {
            return false;
        }

        for (E e : this) {
            if (e.equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        last = null;
    }

    @Override
    public int indexOf(E element) {
        if (element == null || last == null) {
            return -1;
        }

        int index = 0;
        for (E e : this) {
            if (e.equals(element)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E element) {
        if (element == null || last == null) {
            return -1;
        }

        int index = -1;
        int currentIndex = 0;
        for (E e : this) {
            if (e.equals(element)) {
                index = currentIndex;
            }
            currentIndex++;
        }
        return index;
    }



    @Override
    public void forEach(Consumer<? super E> action) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Spliterator<E> spliterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}


