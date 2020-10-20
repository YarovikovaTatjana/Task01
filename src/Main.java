import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
   private static FigureFactorySimple figureFactorySimple=new FigureFactorySimple();
   private static ConsoleHelper consoleHelper = new ConsoleHelper();


    public static void main(String[] args){
        Figure figure;

      try (BufferedReader reader = new BufferedReader(new FileReader("Figure.txt"))){
          while (reader.ready()) {
              String parametr1 = reader.readLine();
              ArrayList<Coordinate> coordinates = receiveCoordinates(parametr1.split(";"));
              figure=figureFactorySimple.createFigure(coordinates);
              System.out.println(figure.getName() + "\n" + figure.toString());
              actionFigure(figure);
              System.out.println("\n===============================================\n");
          }

      }
      catch (FileNotFoundException e){
          System.out.println("File not found");
      }
      catch (IOException e){
          System.out.println("Исключение " + e);
      }


    }

    private static void actionFigure(Figure figure) {
        boolean isAction = true;
        while (isAction) {
            System.out.println("Выберете действие:\n1 - Поворот фигуры\n2 - Перемещение фигуры\n3 - Масштабирование фигуры\n4 - Рассчитать площадь\n5 - Перейти к следующей фигуре");
            switch (consoleHelper.readInt()) {
                case 1:
                    System.out.println("Введите градусы в формате #.##");
                    double angle = consoleHelper.readDouble();
                    figure.turn(angle);
                    System.out.println(figure.getName() + " после поворота\n" + figure.toString());
                    break;
                case 2:
                    System.out.println("Введите дистанцию на которую передвинуть по горизонтали");
                    int x = consoleHelper.readInt();
                    System.out.println("Введите дистанцию на которую передвинуть по вертикали");
                    int y = consoleHelper.readInt();
                    figure.move(x, y);
                    System.out.println(figure.getName() + " после перемещения\n" + figure.toString());
                    break;
                case 3:
                    System.out.println("Введите параметр масштабирования в формате #.## (от 0 до 1 - для уменьшения, больше 1 - для увеличения)");
                    double size = consoleHelper.readDouble();
                    figure.transform(size);
                    System.out.println(figure.getName() + " после масштабирования\n" + figure.toString());
                    break;
                case 4:
                    DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    System.out.println("Площадь равна " + decimalFormat.format(figure.calculateArea()));
                    break;
                case 5:
                    isAction = false;
                    break;
                default:
                    System.out.println("Введено некорректное значение");
                    break;
            }

        }
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




}
