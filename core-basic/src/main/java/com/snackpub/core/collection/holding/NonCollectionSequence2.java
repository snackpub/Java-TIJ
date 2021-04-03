package com.snackpub.core.collection.holding;

import com.snackpub.core.typeinfo.pets.Pet;
import com.snackpub.core.typeinfo.pets.Pets;

import java.util.*;

/**
 * 练习32: (2)按照MultilterableClass示例,在NonCollectionSequence.java中添加reversed()和randomized()方法,
 * 并让NonCollectionSequence实现Iterable,然后在foreach语句中展示所有的使用方式。
 *
 * @author snackpub
 * @date 2021/4/3
 */

class PetSequence2 implements Iterable<Pet> {
    protected Pet[] pets = Pets.createArray(8);
    protected Random rd = new Random(47);

    @Override
    public Iterator<Pet> iterator() {
        return new Iterator<Pet>() {
            private int current = 0;
            @Override
            public boolean hasNext() {
                return current < pets.length;
            }

            @Override
            public Pet next() {
                return pets[current++];
            }
        };
    }
}

public class NonCollectionSequence2 extends PetSequence2 {

    public Iterable<Pet> reversed() {
        return () -> new Iterator<Pet>() {
            int current = pets.length - 1;

            @Override
            public boolean hasNext() {
                return current > -1;
            }

            @Override
            public Pet next() {
                return pets[current--];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public Iterable<Pet> randomized() {
        return () -> {
            List<Pet> list = new ArrayList<>(Arrays.asList(pets));
            Collections.shuffle(list, rd);
            return list.iterator();
        };
    }


    public static void main(String[] args) {
        NonCollectionSequence2 nc2 = new NonCollectionSequence2();
        for (Pet p : nc2)
            System.out.print(p +" ");

        System.out.println();

        for (Pet p : nc2.reversed())
            System.out.print(p +" ");

        System.out.println();

        for (Pet p : nc2.randomized())
            System.out.print(p +" ");

    }

}
