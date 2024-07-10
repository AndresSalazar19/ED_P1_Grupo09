/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tda;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 *
 * @author asala
 * @param <E>
 */

public class LinkedList<E> implements List<E>, Comparator<E> {

    private NodeList<E> header;
    private NodeList<E> last;

    public LinkedList() {
        this.header = null;
        this.last = null;
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
        if (e != null) {
            NodeList<E> newNode = new NodeList<>(e);
            if (last != null) {
                last.setNext(newNode);
            }
            last = newNode;

            if (header == null) {
                header = newNode;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addFirst(E e) {
        if (e != null) {
            NodeList<E> newNode = new NodeList<>(e);
            if (header != null) {
                newNode.setNext(header);
            }
            header = newNode;

            if (last == null) {
                last = newNode;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean add(int index, E element) {
        if (index < 0 || (index > 0 && header == null)) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            return addFirst(element);
        }

        NodeList<E> current = header;
        for (int i = 1; i < index && current.getNext() != null; i++) {
            current = current.getNext();
        }

        if (current.getNext() == null) {
            return addLast(element);
        } else {
            NodeList<E> newNode = new NodeList<>(element);
            newNode.setNext(current.getNext());
            current.setNext(newNode);
            return true;
        }
    }

    @Override
    public E get(int index) {
        if (index < 0 || header == null) {
            throw new IndexOutOfBoundsException();
        }

        NodeList<E> current = header;
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

        NodeList<E> current = header;
        if (index == 0) {
            E content = current.getContent();
            header = current.getNext();
            if (header == null) {
                last = null;
            }
            return content;
        }

        NodeList<E> prev = null;
        for (int i = 0; i < index; i++) {
            if (current.getNext() == null) {
                throw new IndexOutOfBoundsException();
            }
            prev = current;
            current = current.getNext();
        }

        prev.setNext(current.getNext());
        if (prev.getNext() == null) {
            last = prev;
        }
        return current.getContent();
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || header == null) {
            throw new IndexOutOfBoundsException();
        }

        NodeList<E> current = header;
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
    public boolean isEmpty() {
        return header == null;
    }

    @Override
    public int size() {
        int count = 0;
        NodeList<E> current = header;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    @Override
    public boolean contains(E element) {
        NodeList<E> current = header;
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
        NodeList<E> current = header;
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
        NodeList<E> current = header;
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
        List<E> intersection = new LinkedList<>();
        for (E e1 : this) {
            if (comp.compare(e1, elemento) == 0) {
                intersection.addLast(e1);
            }
        }
        return intersection;
    }

    public int compare(Integer e1, Integer e2) {
        if (Objects.equals(e1, e2)) {
            return 0;
        } else if (e1 > e2) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public int compare(E o1, E o2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private NodeList<E> current = header;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E content = current.getContent();
                current = current.getNext();
                return content;
            }
        };
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        for (E e : this) {
            action.accept(e);
        }
    }

    @Override
    public Spliterator<E> spliterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[");
        for (NodeList<E> n = header; n != null; n = n.getNext()) {
            s.append(n.getContent());
            if (n.getNext() != null) {
                s.append(", ");
            }
        }
        s.append("]");
        return s.toString();
    }
    
    @Override
    public E removeFirst() {
        if (header == null) {
            throw new NoSuchElementException();
        }
        E content = header.getContent();
        header = header.getNext();
        if (header == null) {
            last = null;
        }
        return content;
    }    
    
    public boolean remove(E element) {
        if (element == null || header == null) {
            return false;
        }

        // Caso especial: el elemento a eliminar está en el primer nodo
        if (Objects.equals(header.getContent(), element)) {
            header = header.getNext();
            if (header == null) {
                last = null; // Si la lista se vuelve vacía
            }
            return true;
        }

        NodeList<E> current = header;
        NodeList<E> previous = null;

        while (current != null && !Objects.equals(current.getContent(), element)) {
            previous = current;
            current = current.getNext();
        }

        // Si no se encontró el elemento
        if (current == null) {
            return false;
        }

        // Eliminar el nodo encontrado
        previous.setNext(current.getNext());

        // Caso especial: el elemento a eliminar está en el último nodo
        if (current == last) {
            last = previous;
        }

        return true;
    }

}

