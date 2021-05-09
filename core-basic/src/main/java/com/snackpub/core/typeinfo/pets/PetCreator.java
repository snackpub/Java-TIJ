//: typeinfo/pets/PetCreator.java
// Creates random sequences of Pets.
package com.snackpub.core.typeinfo.pets;

import java.util.*;

public abstract class PetCreator extends C{
  private Random rand = new Random(47);
  // The List of the different types of Pet to create:
  public abstract List<Class<? extends Pet>> types();
  final static List<Class<? extends Pet>> types = new ArrayList<>();

  static {
    // 使用注册工厂的方式注册所有字面类
    types.addAll(Arrays.asList(
            Pet.class, Dog.class, Cat.class, Rodent.class,
            Mutt.class, Pug.class, EgyptianMau.class, Manx.class,
            Cymric.class, Rat.class, Mouse.class, Hamster.class));
  }

  public Pet randomPet() { // Create one random Pet
    int n = rand.nextInt(types.size());
    try {
      return types.get(n).newInstance();
    } catch(InstantiationException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }
  public Pet[] createArray(int size) {
    Pet[] result = new Pet[size];
    for(int i = 0; i < size; i++)
      result[i] = randomPet();
    return result;
  }
  public ArrayList<Pet> arrayList(int size) {
    ArrayList<Pet> result = new ArrayList<Pet>();
    Collections.addAll(result, createArray(size));
    return result;
  }
} ///:~
