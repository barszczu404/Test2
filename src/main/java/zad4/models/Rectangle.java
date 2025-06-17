package zad4.models;

public class Rectangle extends Shape {

    private int a;
    private int b;

    public Rectangle(int a, int b) {
        this.a = a;
        this.b = b;
        number =0;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
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
    public String toString() {
        return super.toString() + " Prostokąt o bokach " + a + "x" + b;
    }
}
