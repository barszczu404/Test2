package zad4.models;

public class Square extends Shape {

    private int a;

    public Square(int a) {
        this.a = a;

    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
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
    public String toString() {
        return super.toString() + " Kwadrat o boku " + a;
    }
}
