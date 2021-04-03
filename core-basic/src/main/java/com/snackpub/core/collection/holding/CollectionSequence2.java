package com.snackpub.core.collection.holding;

import com.snackpub.core.collection.iterator.InterfaceVsIterator;
import com.snackpub.core.typeinfo.pets.Pet;
import com.snackpub.core.typeinfo.pets.Pets;

import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 *  练习30: 修改CollectionSequence.java,使其不要继承AbstractCollection,而是实现Collection
 *
 * @author snackpub
 * @date 2021/4/3
 */
public class CollectionSequence2 implements Collection<Pet> {

    private Pet[] pets = Pets.createArray(8);


    @Override
    public Iterator<Pet> iterator() {
        return new Iterator<Pet>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < pets.length;
            }

            @Override
            public Pet next() {
                return pets[index++];
            }

            @Override
            public void remove() { // Not implemented
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public Object[] toArray() {
        return pets;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Pet pet) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Pet> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return pets.length;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    public static void main(String[] args) {
        CollectionSequence2 c = new CollectionSequence2();
        InterfaceVsIterator.display(c);
        InterfaceVsIterator.display(c.iterator());
        System.out.println(Arrays.toString(c.toArray()));
    }
}
