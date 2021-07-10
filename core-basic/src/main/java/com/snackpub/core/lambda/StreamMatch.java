package com.snackpub.core.lambda;

import com.snackpub.core.lambda.model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * allMatch：Stream 中全部元素符合传入的 predicate，返回 true
 * anyMatch：Stream 中只要有一个元素符合传入的 predicate，返回 true
 * noneMatch：Stream 中没有一个元素符合传入的 predicate，返回 true
 *
 * @author snackpub
 * @date 2020/4/11 17:56
 */
public class StreamMatch {

    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "name" + 1, 10));
        persons.add(new Person(2, "name" + 2, 21));
        persons.add(new Person(3, "name" + 3, 34));
        persons.add(new Person(4, "name" + 4, 6));
        persons.add(new Person(5, "name" + 5, 55));

        boolean isAllAdult = persons.stream().allMatch(p -> p.getAge() > 18);

        System.out.println("All are adult? " + isAllAdult);
        boolean isThereAnyChild = persons.stream().anyMatch(p -> p.getAge() < 12);
        System.out.println("Any child? " + isThereAnyChild);
    }


}
