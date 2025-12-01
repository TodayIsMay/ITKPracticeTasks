package com.example.itk;

import com.example.geometry.Rectangle;
import com.example.geometry.Shape;

public class Main {
    public static void main(String[] args) {
        Shape rectangle = new Rectangle(10, 3);
        System.out.println("rectangle perimeter is: " + rectangle.getPerimeter());
        System.out.println("rectangle area is: " + rectangle.getArea());
    }
}