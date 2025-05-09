package com.youngzy.groovy.book.gina2.ch16.shapes

class Circle implements Shape {
    double radius
    Circle(double radius) { this.radius = radius }
    double area() { return Math.PI * radius ** 2 }
    double perimeter() { return 2 * Math.PI * radius }
}
