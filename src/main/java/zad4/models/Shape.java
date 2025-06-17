package zad4.models;

import java.util.Objects;
import java.util.Comparator;

public abstract class Shape {

    protected static int number = 0;

    public double getArea() {
        return 0;
    }

    public double getPerimeter() {
        return 0;
    }

    public static void setNumber(int number) {
        Shape.number = number;
    }

    private static int getNumber() {
        number = number + 1;
        return number;
    }

    public static Circle getCircle(int r) {
        return new Circle(r);
    }

    public static Square getSquare(int a) {
        return new Square(a);
    }

    public static Rectangle getRectangle(int a, int b) {
        return new Rectangle(a, b);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shape shape = (Shape) o;
        return Objects.equals(comparator, shape.comparator);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(comparator);
    }

    Comparator<Shape> comparator = new Comparator<Shape>() {
        @Override
        public int compare(Shape o1, Shape o2) {
            return 0;
        }
    };

    @Override
    public String toString() {
        return "Shape nr " + getNumber() + ": ";
    }

}