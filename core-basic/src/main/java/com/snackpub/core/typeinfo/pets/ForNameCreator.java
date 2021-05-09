//: typeinfo/pets/ForNameCreator.java
package com.snackpub.core.typeinfo.pets;

import java.util.ArrayList;
import java.util.List;

public class ForNameCreator extends PetCreator {
  private static List<Class<? extends Pet>> types =
          new ArrayList<>();
  // Types that you want to be randomly created:
  private static String[] typeNames = {
    "com.snackpub.core.typeinfo.pets.Mutt",
    "com.snackpub.core.typeinfo.pets.Pug",
    "com.snackpub.core.typeinfo.pets.EgyptianMau",
    "com.snackpub.core.typeinfo.pets.Manx",
    "com.snackpub.core.typeinfo.pets.Cymric",
    "com.snackpub.core.typeinfo.pets.Rat",
    "com.snackpub.core.typeinfo.pets.Mouse",
    "com.snackpub.core.typeinfo.pets.Hamster"
  };
  @SuppressWarnings("unchecked")
  private static void loader() {
/*    try {
     for(String name : typeNames)
        types.add(
          (Class<? extends Pet>)Class.forName(name));
    } catch(ClassNotFoundException e) {
      throw new RuntimeException(e);
    }*/

      types.addAll(PetCreator.types);
  }
  static { loader(); }
  public List<Class<? extends Pet>> types() {return types;}

  public static void main(String[] args) {
    System.out.println(types);
  }
} ///:~
