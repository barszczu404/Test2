package zad4.models;

import java.util.Objects;
import java.util.Comparator;

public abstract class Shape {

    private static int counter = 0;

    private int number;

    public Shape() {
    }

    public static Circle createCircle(int r) {
        Circle circle = new Circle(r);
        ((Shape)circle).setNumber(++counter);
        return circle;

    }

    public static Square createSquare(int a) {
        Square square =  new Square(a);
        ((Shape)square).setNumber(++counter);
        return square;
    }


    public static Rectangle createRectangle(int a, int b) {
        Rectangle rectangle =  new Rectangle(a, b);
        ((Shape)rectangle).setNumber(++counter);
        return rectangle;
    }

    private void setNumber(int number) {
        this.number = number;
    }

    public abstract double getArea() ;

    public abstract double getPerimeter();

    public static void setCounter(int counter) {
        Shape.counter = counter;
    }

    private static int getCounter() {
        counter = counter + 1;
        return counter;
    }

    public abstract String getData();


    public static Shape createFromData(String data){
        String[] split = data.split(",");
        return switch ((split[0])) {
            case "Circle" -> new Circle(Double.parseDouble(split[1]));
            case "Square" -> new Square(Double.parseDouble(split[1]));
            case "Rectangle" -> new Rectangle(Double.parseDouble(split[1]), Double.parseDouble(split[2]));
            default -> null;
        };
    }




    @Override
    public String toString() {
        return "Figura nr " + number + ": ";
    }

}