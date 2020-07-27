package com.snackpub.design.factory.sample3.interfaces.impl;

import com.snackpub.design.factory.sample3.interfaces.Shape;

public class Square implements Shape {
 
   @Override
   public void draw() {
      System.out.println("Inside Square::draw() method.");
   }
}