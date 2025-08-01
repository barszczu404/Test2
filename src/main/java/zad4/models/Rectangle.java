package zad4.models;

import java.util.Objects;

public class Rectangle extends Shape {

    private double a;
    private double b;

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    @Override
    public double getArea() {
        return a * b;
    }

    @Override
    public double getPerimeter() {
        return (2 * a) + (2 * b);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(a, rectangle.a) == 0 && Double.compare(b, rectangle.b) == 0;
    }

    @Override
    public String getData() {
        return getClass().getSimpleName() + ","
                + getNumber() + ","
                + a + ","
                + b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    @Override
    public String toString() {
        return super.toString() + " Prostokąt o bokach " + a + "x" + b;
    }
}
