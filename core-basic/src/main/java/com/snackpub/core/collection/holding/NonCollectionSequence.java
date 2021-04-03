package com.snackpub.core.collection.holding;

import com.snackpub.core.collection.iterator.InterfaceVsIterator;
import com.snackpub.core.typeinfo.pets.Pet;
import com.snackpub.core.typeinfo.pets.Pets;

import java.util.Iterator;

/**
 * 生成Iterator是将队列与消费队列的方法连接在一起耦合度最小的方式,并且与实现Collection相比,它在序列类上所施加的约束也少得多。
 *
 * @author snackpub
 * @date 2021/4/3
 */

class PetSequence {
    protected Pet[] pets = Pets.createArray(8);
}

public class NonCollectionSequence extends PetSequence {

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
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void main(String[] args) {
        NonCollectionSequence nc = new NonCollectionSequence();
        InterfaceVsIterator.display(nc.iterator());
    }

}
