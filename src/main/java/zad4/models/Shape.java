package zad4.models;


public abstract class Shape {

    private static int counter = 0;

    private int number;

    public Shape() {
    }


    public static Circle createCircle(int r) {
        Circle circle = new Circle(r);
        ((Shape) circle).setNumber(++counter);
        return circle;

    }

    public static Square createSquare(int a) {
        Square square = new Square(a);
        ((Shape) square).setNumber(++counter);
        return square;
    }


    public static Rectangle createRectangle(int a, int b) {
        Rectangle rectangle = new Rectangle(a, b);
        ((Shape) rectangle).setNumber(++counter);
        return rectangle;
    }

    private void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public abstract double getArea();

    public abstract double getPerimeter();


    public abstract String getData();


    public static Shape createFromData(String data) {
        String[] split = data.split(",");
        Shape shape = null;
        switch ((split[0])) {
            case "Circle": {
                Circle circle = new Circle(Double.parseDouble(split[2])); //setNumber(split[]); ?
                ((Shape) circle).setNumber(Integer.parseInt(split[1]));
                shape = circle;
            }
            break;
            case "Square":

                Square square = new Square(Double.parseDouble(split[2]));
                ((Shape)square).setNumber(Integer.parseInt(split[1]));
                shape = square;
                break;
            case "Rectangle":
                Rectangle rectangle = new Rectangle(Double.parseDouble(split[2]), Double.parseDouble(split[3]));
                ((Shape)rectangle).setNumber(Integer.parseInt(split[1]));
                shape = rectangle;

        }
        return shape;
    }


    @Override
    public String toString() {
        return "Figura nr " + number + ": ";
    }

}