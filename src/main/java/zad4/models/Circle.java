package zad4.models;

import java.util.Objects;

public class Circle extends Shape {

    private double r;


    public Circle(double r) {
        this.r = r;

    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    @Override
    public double getArea() {
        return Math.pow(r, 2) * Math.PI;
    }


    @Override
    public double getPerimeter() {
        return 2 * Math.PI * r;
    }

    @Override
    public String getData() {
        return getClass().getSimpleName() + ","
                + r;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return Double.compare(r, circle.r) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(r);
    }

    @Override
    public String toString() {
        return super.toString() + " Koło o promieniu " + r;
    }


}