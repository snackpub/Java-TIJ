package com.snackpub.core.collection.linkedlist;

import com.snackpub.core.typeinfo.pets.Hamster;
import com.snackpub.core.typeinfo.pets.Pet;
import com.snackpub.core.typeinfo.pets.Pets;
import com.snackpub.core.typeinfo.pets.Rat;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.LinkedList;

/**
 * @author snackpub
 * @date 2021/3/27
 */
public class LinkedListFeatures {


    public static void main(String[] args) {
        LinkedList<Pet> pets = new LinkedList<>(Pets.arrayList(5));
        // 相同，返回列表的第一个元素，如果list为空则抛出NoSuchElementException:
        System.out.println(pets);
        System.out.println("pets.getFirst(): " + pets.getFirst());
        System.out.println("pets.element(): " + pets.element());
        // 只是在空列表行为上有所不同
        System.out.println("pets.peek(): " + pets.peek());
        // 相同的;移除并返回第一个元素,在列表为空时抛出NoSuchElementException:
        System.out.println("pets.remove()： " + pets.remove());
        System.out.println("pets.removeFirst()：" + pets.removeFirst());
        // 唯一不同的是空列表的行为,poll()在列表为空时返回null:
        System.out.println("pets.poll()：" + pets.poll());
        System.out.println(pets);

        pets.addFirst(new Rat());
        System.out.println("After addFist(): " + pets);

        pets.offer(Pets.randomPet()); // 队列满了使用add()增加会抛出异常，而offer()不会
        System.out.println("After offer(): " + pets);

        pets.add(Pets.randomPet());
        System.out.println("After add(): " + pets);

        pets.addLast(new Hamster());
        System.out.println("After addLast(): " + pets);

        System.out.println("pets.removeLast(): " + pets.removeLast());

    }
}

