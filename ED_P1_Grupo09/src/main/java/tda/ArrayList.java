/**
 *
 * @author andres b
 * @param <E>
 */

package tda;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<E> implements List<E> {

    private E[] elements;
    private int capacity = 100;
    private int effectiveSize;
    private final Class<E> type;

    public ArrayList(Class<E> type) {
        this.type = type;
        this.elements = createArray(type, capacity);
        this.effectiveSize = 0;
    }

    // Crear array genérico
    @SuppressWarnings("unchecked")
    private E[] createArray(Class<E> type, int size) {
        return (E[]) Array.newInstance(type, size);
    }

    private boolean isFull() {
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
        elements[effectiveSize++] = e;
        return true;
    }

    @Override
    public boolean addFirst(E e) {
        if (e == null) {
            return false;
        }
        if (isFull()) {
            addCapacity();
        }
        System.arraycopy(elements, 0, elements, 1, effectiveSize);
        elements[0] = e;
        effectiveSize++;
        return true;
    }

    @Override
    public boolean add(int index, E element) {
        if (element == null || index < 0 || index > effectiveSize) {
            return false;
        }
        if (isFull()) {
            addCapacity();
        }
        System.arraycopy(elements, index, elements, index + 1, effectiveSize - index);
        elements[index] = element;
        effectiveSize++;
        return true;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= effectiveSize) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + effectiveSize);
        }
        return elements[index];
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= effectiveSize) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + effectiveSize);
        }
        E removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, effectiveSize - index - 1);
        elements[--effectiveSize] = null;
        return removedElement;
    }

    @Override
    public E set(int index, E element) {
        if (element == null || index < 0 || index >= effectiveSize) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + effectiveSize);
        }
        E oldElement = elements[index];
        elements[index] = element;
        return oldElement;
    }

    @Override
    public boolean isEmpty() {
        return effectiveSize == 0;
    }

    @Override
    public int size() {
        return effectiveSize;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) >= 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < effectiveSize; i++) {
            elements[i] = null;
        }
        effectiveSize = 0;
    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            return -1;
        }
        for (int i = 0; i < effectiveSize; i++) {
            if (elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E element) {
        if (element == null) {
            return -1;
        }
        for (int i = effectiveSize - 1; i >= 0; i--) {
            if (elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
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
        List<E> intersection = new ArrayList<>(type);
        for (E e1 : this) {
            if (comp.compare(e1, elemento) == 0) {
                intersection.addLast(e1);
            }
        }
        return intersection;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < effectiveSize;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return elements[cursor++];
            }
        };
    }

    private void addCapacity() {
        E[] tmp = createArray(type, capacity * 2);
        System.arraycopy(elements, 0, tmp, 0, capacity);
        elements = tmp;
        capacity *= 2;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < effectiveSize - 1; i++) {
            sb.append(elements[i]).append(", ");
        }
        sb.append(elements[effectiveSize - 1]).append("]");
        return sb.toString();
    }
    
    @Override
    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return remove(0);
    }
}
