import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FigureCreateHelper {
   private static ConsoleHelper consoleHelper = new ConsoleHelper();


    public static ArrayList<Figure> getFiguresByFile() {
        ArrayList<Figure> figures = new ArrayList<>();
        FigureFactory figureFactory;
        try (BufferedReader reader = new BufferedReader(new FileReader("Figure.txt"))){
            while (reader.ready()) {
                String parametr1 = reader.readLine();
                ArrayList <Coordinate> coordinates = receiveCoordinates(parametr1.split(";"));
                figureFactory = getFigureFactory(coordinates);
                figures.add(figureFactory.createFigure(coordinates));
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        catch (IOException e){
            System.out.println("Исключение " + e);
        }
        return figures;
    }

    public static Figure getFigureByConsole(){
        ArrayList <Coordinate> coordinates = userCoordinates();
        FigureFactory figureFactory = getFigureFactory(coordinates);
        return figureFactory.createFigure(coordinates);
    }

    private static FigureFactory getFigureFactory(ArrayList<Coordinate> coordinates) {
        FigureFactory figureFactory;
        switch (coordinates.size()){
            case 2:
                figureFactory = new CircleFactory();
                break;
            case 3:
                figureFactory = new TriangleFactory();
                break;
            case 4:
                figureFactory = new RectangleFactory();
                break;
            default:
                figureFactory = new PolygonFactory();
                break;
        }
        return figureFactory;
    }


    private static ArrayList<Coordinate> receiveCoordinates(String[] parametrs) {
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        for (String parametr : parametrs) {
            try (Scanner scanner = new Scanner(parametr)) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                coordinates.add(new Coordinate(x, y));
            } catch (InputMismatchException e) {
                System.out.println("InputMismatchException");
            }
        }
        return coordinates;
    }

    private static ArrayList<Coordinate> userCoordinates(){
        ArrayList<Coordinate> userCoordinates = new ArrayList<>();
        boolean isExit = false;
        int i =0;
        while (!isExit){
            if (i<2){
                userCoordinates.add(getUserCoordinate());
            }
            else {
                System.out.println("Ввести еще одну координату? Y/N");
                switch (consoleHelper.readString()) {
                    case "Y":
                        userCoordinates.add(getUserCoordinate());
                        break;
                    case "N":
                        isExit = true;
                        break;
                    default:
                        System.out.println("Введено некорректное значение");
                        break;
                }
            }
            i++;
        }

        return userCoordinates;
    }

    private static Coordinate getUserCoordinate() {
        double x = consoleHelper.readDouble();
        double y = consoleHelper.readDouble();
        System.out.println("Координата добавлена");
        return new Coordinate(x,y);
    }

}
