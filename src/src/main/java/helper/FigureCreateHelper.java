package helper;

import db.model.figure.*;
import factory.*;
import db.model.coordinate.Coordinate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FigureCreateHelper {
    final static String FILENAME = "Figure.txt";


  public static Figure createCopyFigure(Figure figure){
        ArrayList<Coordinate> copyCoordinates = new ArrayList<>();
        for (int i = 0; i < figure.getCoordinates().size(); i++) {
            copyCoordinates.add(new Coordinate(figure.getCoordinates().get(i).getX(),figure.getCoordinates().get(i).getY()));
        }
        FigureFactory figureFactory = getFigureFactory(copyCoordinates);
        return figureFactory.createFigure(copyCoordinates);
    }


    public static ArrayList<Figure> getFiguresByFile() {
        ArrayList<Figure> figures = new ArrayList<>();
        FigureFactory figureFactory;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))){
            while (reader.ready()) {
                String parametr1 = reader.readLine();
                ArrayList <Coordinate> coordinates = receiveCoordinates(parametr1.split(";"));
                if (coordinates.size()>1) {
                    figureFactory = getFigureFactory(coordinates);
                    figures.add(figureFactory.createFigure(coordinates));
                }
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return figures;
    }

    public static ArrayList<Figure> getFiguresByFileHelper(ArrayList<String> lines) {
        ArrayList<Figure> figures = new ArrayList<>();
        FigureFactory figureFactory;
        try {
            for (String line : lines) {
                ArrayList<Coordinate> coordinates = receiveCoordinates(line.split(";"));
                if (coordinates.size()>1) {
                    figureFactory = getFigureFactory(coordinates);
                    figures.add(figureFactory.createFigure(coordinates));
                }
            }
        }
        catch (InputMismatchException e){
            e.printStackTrace();
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
            switch (coordinates.size()) {
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

    public static FigureFactory getFigureFactory(TypeFigure typeFigure) {
        FigureFactory figureFactory;
        switch (typeFigure) {
            case Circle:
                figureFactory = new CircleFactory();
                break;
            case Triangle:
                figureFactory = new TriangleFactory();
                break;
            case Rectangle:
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
                double x = scanner.nextDouble();
                double y = scanner.nextDouble();
                coordinates.add(new Coordinate(x, y));
            } catch (InputMismatchException e) {

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
                switch (ConsoleHelper.readString()) {
                    case "Y":
                        userCoordinates.add(getUserCoordinate());
                        break;
                    case "N":
                        isExit = true;
                        break;
                    case "y":
                        userCoordinates.add(getUserCoordinate());
                        break;
                    case "n":
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
        System.out.println("Введите х: ");
        double x = ConsoleHelper.readDouble();
        System.out.println("Введите y: ");
        double y = ConsoleHelper.readDouble();
        System.out.println("Координата добавлена");
        return new Coordinate(x,y);
    }


}
