package zad4.app;

import zad4.models.Circle;
import zad4.models.Rectangle;
import zad4.models.Shape;
import zad4.models.Square;

import java.io.*;
import java.util.*;

public class Runner {
    public static void main(String[] args) {


        List<Shape> shapes = Arrays.asList(Shape.createSquare(10), Shape.createCircle(20), Shape.createRectangle(10,20));
        for (Shape s : shapes) {
            System.out.println(s);
        }

        Circle testCircle = new Circle(8);

        System.out.println(testCircle);

        //readShapesFromFileToList();

        System.out.println(shapes.contains(new Square(10))); //contains sprwadza czy kolekcja zwwiera obiekt strukturalnie identyczn a nie ten konkretnie obiekt

        Shape shapeWithBiggestArea = getShapeWithBiggestArea(shapes);
        System.out.println("shapeWithBiggestArea = " + shapeWithBiggestArea);

        Shape shapeWithTheBiggestPerimeter = getShapeWithTheBiggestPerimeter(shapes);
        System.out.println("shapeWithTheBiggestPerimeter = " + shapeWithTheBiggestPerimeter);


        saveListOfFiguresToFile(shapes, "shejpy.txt");
        List<Shape> shapes1 = readShapesFromFileToList("shejpy.txt");

    }

//    - znajdź figurę z największym obwodem
    public static Shape getShapeWithTheBiggestPerimeter(List<Shape> list) {
     Shape biggestPerimeterShape = null;
     for (Shape shape : list){
         if (biggestPerimeterShape == null || biggestPerimeterShape.getPerimeter() < shape.getPerimeter()){
             biggestPerimeterShape = shape;
         }
     }
        return biggestPerimeterShape;
    }

    //- znajdź figurę z największym polem
    public static Shape getShapeWithBiggestArea(List<Shape> list) {
       Shape biggestAreaShape = null;
       for (Shape shape : list){
           if (biggestAreaShape == null || biggestAreaShape.getArea() < shape.getArea()){
               biggestAreaShape = shape;
           }
       }
        return biggestAreaShape;
    }

    public static void saveListOfFiguresToFile(List<Shape> shapes, String fileName) {
        try (
                BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))
                ){
            for (Shape shape : shapes){
                if (shape != null){
                bw.write(shape.getData());
                bw.newLine();
                }
            }

        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    public static List<Shape> readShapesFromFileToList (String fileName){
        List<Shape> loadedShapes = new ArrayList<>();
        try (
                FileReader fr = new FileReader(fileName);
                BufferedReader br = new BufferedReader(fr);
                )
        {
            String line = null;
            while ((line = br.readLine()) != null){
                Shape shapeFromData = Shape.createFromData(line);
                loadedShapes.add(shapeFromData);
            }

        } catch (IOException e){
            e.printStackTrace();
        }
        return loadedShapes;
    }

}
