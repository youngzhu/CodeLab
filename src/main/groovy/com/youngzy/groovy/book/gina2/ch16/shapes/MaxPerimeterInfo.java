package com.youngzy.groovy.book.gina2.ch16.shapes;

public class MaxPerimeterInfo {
    void displayInfo(Square s, Circle c) {
        System.out.print("The shape with the biggest perimeter is: ");
        System.out.println(s.perimeter() > c.perimeter() ? "square" : "circle");
    }
}
