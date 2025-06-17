package zad4.app;

import zad4.models.Circle;
import zad4.models.Rectangle;
import zad4.models.Shape;
import zad4.models.Square;

import java.io.*;
import java.util.*;

public class Runner {
    public static void main(String[] args) {


        List<Shape> shapes = Arrays.asList(Shape.getSquare(10), Shape.getCircle(20), Shape.getRectangle(10,20));
        for (Shape s : shapes) {
            System.out.println(s);
        }

        Circle testCircle = new Circle(8);

        System.out.println(testCircle);//nr zly

        System.out.println(shapes.contains(new Square(10))); //powinno wypisac true, nie dziala

        System.out.println(getShapeWithBiggestArea(shapes));
        System.out.println(getShapeWithTheBiggestPerimeter(shapes));
        File test = new File("test.txt");
        test.getAbsoluteFile();
        try {
            saveListOfFiguresToFile(shapes, test.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Shape> listTest = new ArrayList<>();
        getListOfShapesFromFile("src/main/java/zad4/models/test.txt", listTest);
    }

//    - znajdź figurę z największym obwodem
    public static Shape getShapeWithTheBiggestPerimeter(List<Shape> list) {
        Comparator<Shape> comparator = new Comparator<Shape>() {
            @Override
            public int compare(Shape o1, Shape o2) {
                return (int) (o1.getPerimeter() - o2.getPerimeter());
            }
        };
        Collections.sort(list, comparator);
        Shape biggestPerimeterShape = list.get(list.size() - 1);
        return biggestPerimeterShape;
    }

    //- znajdź figurę z największym polem
    public static Shape getShapeWithBiggestArea(List<Shape> list) {
        Comparator<Shape> comparator = new Comparator<Shape>() {
            @Override
            public int compare(Shape o1, Shape o2) {
                return (int) (o1.getArea() - o2.getArea());
            }
        };
        Collections.sort(list, comparator);
        Shape biggestAreaShape = list.get(list.size() - 1);
        return biggestAreaShape;
    }

    public static File saveListOfFiguresToFile(List<Shape> shapes, String fileName)  throws IOException {
        try {
            BufferedWriter bfw = new BufferedWriter(new FileWriter(fileName));
                for(Shape shape : shapes) {
                  if (shape instanceof Square square){
                      bfw.write("Kwadrat " + square.getA());
                  } else if (shape instanceof Circle circle) {
                      bfw.write("Koło " + circle.getR());
                  } else if (shape instanceof Rectangle rectangle){
                      bfw.write("Prostokąt " + rectangle.getA() + " " + rectangle.getB());
                  }
                  bfw.newLine();
                }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } //finally ze sprawdzeniem czy puste?
        return new File(fileName);//?
    }

    public static List getListOfShapesFromFile(String fileName, List<Shape> shapes){
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bfr.readLine()) != null) {
                String[] dataFromLine = line.split(" ");
                if (dataFromLine[0] == "Kwadrat") {
                    shapes.add(Shape.getSquare(Integer.parseInt(dataFromLine[1])));
                } else if (dataFromLine[0] == "Koło"){
                    shapes.add(Shape.getCircle(Integer.parseInt(dataFromLine[1])));
                } else if (dataFromLine[0] == "Postokąt"){
                    shapes.add(Shape.getRectangle(Integer.parseInt(dataFromLine[1]), Integer.parseInt(dataFromLine[2])));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return shapes;
    }
}
//UWAGA: tylko figury tworzone metodą fabryczną powinny mieć nadawany sekwencyjnie numer, w przypadku gdy uzywamy konstruktora to kazda
//figura ma numer 0.