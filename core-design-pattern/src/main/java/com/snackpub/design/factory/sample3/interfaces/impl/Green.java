package com.snackpub.design.factory.sample3.interfaces.impl;

import com.snackpub.design.factory.sample3.interfaces.Color;

public class Green implements Color {
 
   @Override
   public void fill() {
      System.out.println("Inside Green::fill() method.");
   }
}