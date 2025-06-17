package zad4.models;

public class Circle extends Shape {

    private int r;


    public Circle(int r) {
        this.r = r;

    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
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
    public String toString() {
        return super.toString() + " Koło o promieniu " + r;
    }


}