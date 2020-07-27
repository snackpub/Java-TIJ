package com.snackpub.design.factory.sample3.interfaces.impl;

import com.snackpub.design.factory.sample3.interfaces.Color;

public class Blue implements Color {
 
   @Override
   public void fill() {
      System.out.println("Inside Blue::fill() method.");
   }
}