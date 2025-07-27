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

        System.out.println(shapes.get(0));
        System.out.println(shapes.get(0));
        System.out.println(shapes.get(0));
        System.out.println(shapes.get(0));
        System.out.println(shapes.get(0));
        System.out.println(shapes.get(0));

        Circle testCircle = new Circle(8);

        System.out.println(testCircle);//nr zly

        readShapesFromFileToList();



        System.out.println(shapes.contains(new Square(10))); //contains sprwadza czy kolekcja zwwiera obiekt strukturalnie identyczn a nie ten konkretnie obiekt

//        System.out.println(getShapeWithBiggestArea(shapes));
//        System.out.println(getShapeWithTheBiggestPerimeter(shapes));
//        File test = new File("test.txt");
//        test.getAbsoluteFile();
//        try {
//            saveListOfFiguresToFile(shapes, test.getAbsolutePath());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        List<Shape> listTest = new ArrayList<>();
//        getListOfShapesFromFile("src/main/java/zad4/models/test.txt", listTest);
    }

//    - znajdź figurę z największym obwodem
    public static Shape getShapeWithTheBiggestPerimeter(List<Shape> list) {
     Shape biggestPerimeterShape = null;
     for (Shape shape : list){
         if (biggestPerimeterShape == null || biggestPerimeterShape.getPerimeter() < shape.getPerimeter()){
             biggestPerimeterShape = shape;
         }
     }


//        Collections.sort(list, comparator);
//        Shape biggestPerimeterShape = list.get(list.size() - 1);
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


//            BufferedWriter bfw = new BufferedWriter(new FileWriter(fileName));
//                for(Shape shape : shapes) {
//                  if (shape instanceof Square square){
//                      bfw.write("Kwadrat " + square.getA());
//                  } else if (shape instanceof Circle circle) {
//                      bfw.write("Koło " + circle.getR());
//                  } else if (shape instanceof Rectangle rectangle){
//                      bfw.write("Prostokąt " + rectangle.getA() + " " + rectangle.getB());
//                  }
//                  bfw.newLine();

        } catch (IOException e) {
           e.printStackTrace();
        } //finally ze sprawdzeniem czy puste?
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

    public static List getListOfShapesFromFile(String fileName, List<Shape> shapes){
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bfr.readLine()) != null) {
                String[] dataFromLine = line.split(" ");
                if (dataFromLine[0] == "Kwadrat") {
                    shapes.add(Shape.createSquare(Integer.parseInt(dataFromLine[1])));
                } else if (dataFromLine[0] == "Koło"){
                    shapes.add(Shape.createCircle(Integer.parseInt(dataFromLine[1])));
                } else if (dataFromLine[0] == "Postokąt"){
                    shapes.add(Shape.createRectangle(Integer.parseInt(dataFromLine[1]), Integer.parseInt(dataFromLine[2])));
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