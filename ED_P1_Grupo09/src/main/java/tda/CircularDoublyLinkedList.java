/**
 *
 * @author andres b
 * @param <E>
 */

package tda;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularDoublyLinkedList<E> implements List<E> {

    private DoublyNodeList<E> last;
    private int size;

    public CircularDoublyLinkedList() {
        this.last = null;
        this.size = 0;
    }

    @Override
    public boolean addLast(E e) {
        if (e == null) {
            return false;
        }
        DoublyNodeList<E> newNode = new DoublyNodeList<>(e);
        if (last == null) {
            last = newNode;
            last.setNext(last);
            last.setPrevious(last);
        } else {
            DoublyNodeList<E> header = last.getNext();
            last.setNext(newNode);
            newNode.setPrevious(last);
            newNode.setNext(header);
            header.setPrevious(newNode);
            last = newNode;
        }
        size++;
        return true;
    }

    @Override
    public boolean addFirst(E e) {
        if (e == null) {
            return false;
        }
        DoublyNodeList<E> newNode = new DoublyNodeList<>(e);
        if (last == null) {
            last = newNode;
            last.setNext(last);
            last.setPrevious(last);
        } else {
            DoublyNodeList<E> header = last.getNext();
            newNode.setNext(header);
            newNode.setPrevious(last);
            last.setNext(newNode);
            header.setPrevious(newNode);
        }
        size++;
        return true;
    }

    @Override
    public boolean add(int index, E element) {
    if (element == null || index < 0 || index > size) {
        return false;
    }
    if (index == 0) {
        return addFirst(element);
    } else if (index == size) {
        return addLast(element);
    } else {
        DoublyNodeList<E> current = last.getNext();
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }
        DoublyNodeList<E> newNode = new DoublyNodeList<>(element);
        newNode.setNext(current.getNext());
        newNode.setPrevious(current);
        current.getNext().setPrevious(newNode);
        current.setNext(newNode);
        size++;
        return true;
    }
}


    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        DoublyNodeList<E> current = last.getNext();
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getContent();
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        DoublyNodeList<E> current = last.getNext();
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        E removedElement = current.getContent();
        if (size == 1) {
            last = null;
        } else {
            current.getPrevious().setNext(current.getNext());
            current.getNext().setPrevious(current.getPrevious());
            if (current == last) {
                last = current.getPrevious();
            }
        }
        size--;
        return removedElement;
    }

    @Override
    public E set(int index, E element) {
        if (element == null || index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        DoublyNodeList<E> current = last.getNext();
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        E oldElement = current.getContent();
        current.setContent(element);
        return oldElement;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) >= 0;
    }

    @Override
    public void clear() {
        last = null;
        size = 0;
    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            return -1;
        }
        DoublyNodeList<E> current = last.getNext();
        for (int i = 0; i < size; i++) {
            if (current.getContent().equals(element)) {
                return i;
            }
            current = current.getNext();
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E element) {
        if (element == null) {
            return -1;
        }
        DoublyNodeList<E> current = last;
        for (int i = size - 1; i >= 0; i--) {
            if (current.getContent().equals(element)) {
                return i;
            }
            current = current.getPrevious();
        }
        return -1;
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
        List<E> intersection = new CircularDoublyLinkedList<>();
        for (E e : this) {
            if (comp.compare(e, elemento) == 0) {
                intersection.addLast(e);
            }
        }
        return intersection;
    }
    
    
    @Override
    public E removeFirst() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        DoublyNodeList<E> header = last.getNext();
        E content = header.getContent();
        if (header == last) {
            last = null;
        } else {
            last.setNext(header.getNext());
            header.getNext().setPrevious(last);
        }
        size--;
        return content;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private DoublyNodeList<E> current = (last == null) ? null : last.getNext();
            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E e = current.getContent();
                current = current.getNext();
                count++;
                return e;
            }
        };
    }
    
    public int getSize(){
        return size;
    }

    public int compare(E e1, E e2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}