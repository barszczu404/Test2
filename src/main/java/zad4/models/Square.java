package zad4.models;

import java.util.Objects;

public class Square extends Shape {

    private double a;

    public Square(double a) {
        this.a = a;

    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    @Override
    public double getArea() {
        return a * a;
    }

    @Override
    public double getPerimeter() {
        return a * 4;
    }

    @Override
    public String getData() {
        return getClass().getSimpleName() + ","
                + a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return Double.compare(a, square.a) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), a);
    }

    @Override
    public String toString() {
        return super.toString() + " Kwadrat o boku " + a;
    }
}
