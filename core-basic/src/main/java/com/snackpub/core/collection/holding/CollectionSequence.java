package com.snackpub.core.collection.holding;

import com.snackpub.core.collection.iterator.InterfaceVsIterator;
import com.snackpub.core.typeinfo.pets.Pet;
import com.snackpub.core.typeinfo.pets.Pets;

import java.util.AbstractCollection;
import java.util.Iterator;

/**
 * 尽管这可以通过继承AbstractCollection而很容易地实现,但是你无论如何还是要被强制去实现iterator()和size(),
 * 以便提供AbstractCollection没有实现,但是AbstractCollection中的其他方法会使用到的方法：
 *
 * @author snackpub
 * @date 2021/4/3
 */
public class CollectionSequence extends AbstractCollection<Pet> {

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
    public int size() {
        return pets.length;
    }

    public static void main(String[] args) {
        CollectionSequence c = new CollectionSequence();
        InterfaceVsIterator.display(c);
        InterfaceVsIterator.display(c.iterator());
    }
}
