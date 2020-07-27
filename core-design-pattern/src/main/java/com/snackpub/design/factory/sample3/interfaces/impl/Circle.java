package com.snackpub.design.factory.sample3.interfaces.impl;

import com.snackpub.design.factory.sample3.interfaces.Shape;

public class Circle implements Shape {
 
   @Override
   public void draw() {
      System.out.println("Inside Circle::draw() method.");
   }
}