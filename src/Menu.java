import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;

class Menu {
    private static String separator = "============================================\n";

    static void startMenu(){
        boolean isAction = true;
        int number;

            while (isAction) {
                System.out.println("Выберете действие:\n1 - Вывод всех фигур\n2 - Выбор фигуры\n3 - Добавление фигуры\n4 - Удаление фигуры\n5 - Выход");
                switch (ConsoleHelper.readInt()) {
                    case 1: FileHelper.AllFiguresInFileToString();
                        System.out.println(separator);
                        break;
                    case 2:
                        ArrayList<Figure> figures = FileHelper.getAllFigiresInFile();
                        System.out.println("Доступно фигур: " + figures.size() + ".\nВведите порядковый номер (начиная с 1) для вывода желаемой фигуры");
                        number = ConsoleHelper.readInt(0, figures.size() + 1);
                        Figure figure = figures.get(number - 1);
                        System.out.println(figure.getName() + "\n" + figure.toString());
                        actionFigure(figure);
                        System.out.println(separator);
                        break;
                    case 3:
                        System.out.println("Введите координаты новой фигуры. \nДля окружности введите 2 координаты крайних точек диаметра. \nДля остальных фигур - координаты вершин");
                        Figure userFigure = FigureCreateHelper.getFigureByConsole();
                        FileHelper.addFigureInFile(userFigure);
                        System.out.println("Добавлена фигура: " + userFigure.getName() + "\n" + userFigure.toString());
                        System.out.println(separator);
                        break;
                    case 4:
                        ArrayList<Figure> temp = FileHelper.getAllFigiresInFile();
                        System.out.println("Доступно фигур: " + temp.size() + ".\nВведите порядковый номер (начиная с 1) удаляемой фигуры");
                        number = ConsoleHelper.readInt(0, temp.size() + 1);
                        FileHelper.removeFigureInFile(temp.get(number - 1));
                        System.out.println(separator);
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


    private static void actionFigure(Figure figure) {
        boolean isAction = true;
        Figure tempFigure = FigureCreateHelper.createCopyFigure(figure);
        while (isAction) {
            System.out.println("Выберете действие:\n1 - Поворот фигуры\n2 - Перемещение фигуры\n3 - Масштабирование фигуры\n4 - Рассчитать площадь\n5 - Вернуться в главное меню");
            switch (ConsoleHelper.readInt()) {
                case 1:
                    System.out.println("Введите градусы в формате #.##");
                    double angle = ConsoleHelper.readDouble();
                    tempFigure.turn(angle);
                    System.out.println(tempFigure.getName() + " после поворота\n" + tempFigure.toString());
                    saveFigureInFile(figure,tempFigure);
                    break;
                case 2:
                    System.out.println("Введите дистанцию на которую передвинуть по горизонтали");
                    int x = ConsoleHelper.readInt();
                    System.out.println("Введите дистанцию на которую передвинуть по вертикали");
                    int y = ConsoleHelper.readInt();
                    tempFigure.move(x, y);
                    System.out.println(tempFigure.getName() + " после перемещения\n" + tempFigure.toString());
                    saveFigureInFile(figure,tempFigure);
                    break;
                case 3:
                    System.out.println("Введите параметр масштабирования в формате #.## \n(от 0 до 1 - для уменьшения, больше 1 - для увеличения)");
                    double size = ConsoleHelper.readDouble(0);
                    tempFigure.transform(size);
                    System.out.println(tempFigure.getName() + " после масштабирования\n" + tempFigure.toString());
                    saveFigureInFile(figure,tempFigure);
                    break;
                case 4:
                    DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    System.out.println("Площадь равна " + decimalFormat.format(tempFigure.calculateArea()));
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

    private static void saveFigureInFile(Figure figure, Figure tempFigure){
        boolean isContinue = true;
        while (isContinue) {
            System.out.println("Выберете действие:\n1 - Сохранить\n2 - Отмена");
            switch (ConsoleHelper.readInt()) {
                case 1:
                    FileHelper.replaceFigureInFile(figure, tempFigure);
                    isContinue = false;
                    break;
                case 2:
                    isContinue = false;
                    break;
                default:
                    System.out.println("Введено некорректное значение");
                    break;
            }

        }

    }



}
