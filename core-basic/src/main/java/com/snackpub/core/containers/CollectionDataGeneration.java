package com.snackpub.core.containers;
// Using the Generators defined in the Arrays chapter.
import com.snackpub.core.util.CollectionData;
import com.snackpub.core.util.RandomGenerator;

import java.util.*;

public class CollectionDataGeneration {
  public static void main(String[] args) {
    System.out.println(new ArrayList<>(
            CollectionData.list( // Convenience method
                    new RandomGenerator.String(9), 10)));
    System.out.println(new HashSet<>(
            new CollectionData<>(
                    new RandomGenerator.Integer(), 10)));
  }
} /* Output:
[YNzbrnyGc, FOWZnTcQr, GseGZMmJM, RoEsuEcUO, neOEdLsmw, HLGEahKcx, rEqUCBbkI, naMesbtWH, kjUrUkZPg, wsqPzDyCy]
[573, 4779, 871, 4367, 6090, 7882, 2017, 8037, 3455, 299]
*///:~
