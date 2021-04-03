package com.snackpub.core.collection.iterator;

import com.snackpub.core.typeinfo.pets.Pet;
import com.snackpub.core.typeinfo.pets.Pets;

import java.util.*;

/**
 * 两个版本的display()方法都可以使用Map或Collection的子类型来工作,而且Collection接口和Iterator都可以将display()方法与底层容器的特定实现解耦。
 * 在本例中,这两种方式都可以凑效。事实上, Collection要更方便一点,因为它是Iterable类型,因此,在display(Collection)实现中,
 * 可以使用foreach结构,从而使代码更加清晰。当你要实现一个不是Collection的外部类时,
 * 由于让它去实现Collecion接口可能非常困难或麻烦,因此使用Iterator就会变得非常吸引人。
 * 例如,如果我们通过继承一个持有Pet对象的类来创建一个Collection的实现,
 * 那么我们必须实现所有的Collection方法,即使我们在display()方法中不必使用它们,也必须如此。
 *
 * @author snackpub
 * @date 2021/3/28
 */
public class InterfaceVsIterator {

    public static void display(Iterator<Pet> it) {
        while (it.hasNext()) {
            Pet p = it.next();
            System.out.print(p.id() + ":" + p + " ");
        }
        System.out.println();
    }

    public static void display(Collection<Pet> pets) {
        for (Pet p : pets)
            System.out.print(p.id() + ":" + p + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        List<Pet> petList = Pets.arrayList(8);
        Set<Pet> petSet = new HashSet<>(petList);
        Map<String, Pet> petMap = new LinkedHashMap<>();
        String[] names = ("Ralph, Eric, Robin. Lacey, Britney, Sam, Spot, Fluffy").split(", ");
        for (int i = 0; i < names.length; i++)
            petMap.put(names[i], petList.get(i));
        display(petList);
        display(petSet);
        display(petList.iterator());
        display(petSet.iterator());
        System.out.println(petMap);
        System.out.println(petMap.keySet());
        display(petMap.values());
        display(petMap.values().iterator());
    }
}
