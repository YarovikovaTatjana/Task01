import java.text.DecimalFormat;
import java.util.ArrayList;

public class Menu {
    private static ConsoleHelper consoleHelper = new ConsoleHelper();
    private static FigureCreateHelper figureCreateHelper = new FigureCreateHelper();


    public static void startMenu(ArrayList<Figure> figures){
        boolean isAction = true;
        int number;
        while (isAction) {
            System.out.println("Выберете действие:\n1 - Вывод всех фигур\n2 - Выбор фигуры\n3 - Добавление фигуры\n4 - Удаление фигуры\n5 - Выход");
            switch (consoleHelper.readInt()) {
                case 1:
                    for (Figure figure: figures) {
                        System.out.println(figure.getName() + "\n" + figure.toString());
                    }
                    break;
                case 2:
                    System.out.println("Доступно " + figures.size() + " фигур. Введите порядковый номер (начиная с 1) для вывода желаемой фигуры");
                    number = consoleHelper.readInt(0,figures.size()+1);
                    Figure figure = figures.get(number-1);
                    System.out.println(figure.getName() + "\n" + figure.toString());
                    actionFigure(figure);
                    break;
                case 3:
                    System.out.println("Введите координаты новой фигуры. Для окружности введите 2 координаты крайних точек диаметра. Для остальных фигур - координаты вершин");
                    Figure userFigure = figureCreateHelper.getFigureByConsole();
                    figures.add(userFigure);
                    System.out.println("Добавлена фигура: " + userFigure.getName() + "\n" + userFigure.toString());
                    break;
                case 4:
                    System.out.println("Доступно " + figures.size() + " фигур. Введите порядковый номер (начиная с 1) удаляемой фигуры");
                    number = consoleHelper.readInt(0,figures.size()+1);
                    figures.remove(number-1);
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

    public static void actionFigure(Figure figure) {
        boolean isAction = true;
        while (isAction) {
            System.out.println("Выберете действие:\n1 - Поворот фигуры\n2 - Перемещение фигуры\n3 - Масштабирование фигуры\n4 - Рассчитать площадь\n5 - Вернуться в главное меню");
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
                    double size = consoleHelper.readDouble(0);
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




}
