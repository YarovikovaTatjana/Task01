import model.figure.Figure;
import helper.ConsoleHelper;
import helper.FigureCreateHelper;
import helper.FileHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;

class Menu {
    private static String separator = "============================================\n";
    private static String mainMenu = "Выберете действие:\n" +
            "1 - Вывод всех фигур\n" +
            "2 - Выбор фигуры\n" +
            "3 - Добавление фигуры\n" +
            "4 - Удаление фигуры\n" +
            "5 - Выход";
    private static String actionSubMenu = "Выберете действие:\n" +
            "1 - Поворот фигуры\n" +
            "2 - Перемещение фигуры\n" +
            "3 - Масштабирование фигуры\n" +
            "4 - Рассчитать площадь\n" +
            "5 - Вернуться в главное меню";

    static void startMenu(){
        boolean isAction = true;
            while (isAction) {
                System.out.println(mainMenu);
                switch (ConsoleHelper.readInt()) {
                    case 1: FileHelper.AllFiguresInFileToString();
                        break;
                    case 2:
                        startActionFigureMenu();
                        break;
                    case 3:
                        startAddFigure();
                        break;
                    case 4:
                        startRemoveFigure();
                        break;
                    case 5:
                        isAction = false;
                        break;
                    default:
                        System.out.println("Введено некорректное значение");
                        break;
                }
                System.out.println(separator);
            }
    }

    private static void startRemoveFigure() {
        int number;
        ArrayList<Figure> temp = FileHelper.getAllFigiresInFile();
        System.out.println("Доступно фигур: " + temp.size() + ".\nВведите порядковый номер (начиная с 1) удаляемой фигуры");
        number = ConsoleHelper.readInt(0, temp.size() + 1);
        FileHelper.removeFigureInFile(temp.get(number - 1));
    }

    private static void startAddFigure() {
        System.out.println("Введите координаты новой фигуры. \nДля окружности введите 2 координаты крайних точек диаметра. \nДля остальных фигур - координаты вершин");
        Figure userFigure = FigureCreateHelper.getFigureByConsole();
        FileHelper.addFigureInFile(userFigure);
        System.out.println("Добавлена фигура: " + userFigure.getName() + "\n" + userFigure.toString());
    }

    private static void startActionFigureMenu() {
        int number;
        ArrayList<Figure> figures = FileHelper.getAllFigiresInFile();
        System.out.println("Доступно фигур: " + figures.size() + ".\nВведите порядковый номер (начиная с 1) для вывода желаемой фигуры");
        number = ConsoleHelper.readInt(0, figures.size() + 1);
        Figure figure = figures.get(number - 1);
        System.out.println(figure.getName() + "\n" + figure.toString());
        actionFigure(figure);
    }


    private static void actionFigure(Figure figure) {
        boolean isAction = true;
        Figure tempFigure = FigureCreateHelper.createCopyFigure(figure);
        while (isAction) {
            System.out.println(actionSubMenu);
            switch (ConsoleHelper.readInt()) {
                case 1:
                    makeTurnFigure(tempFigure);
                    saveFigureInFile(figure,tempFigure);
                    break;
                case 2:
                    makeMoveFigure(tempFigure);
                    saveFigureInFile(figure,tempFigure);
                    break;
                case 3:
                    makeTransformFigure(tempFigure);
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

    private static void makeTransformFigure(Figure tempFigure) {
        System.out.println("Введите параметр масштабирования в формате #.## \n" +
                "(от 0 до 1 - для уменьшения, больше 1 - для увеличения)");
        double size = ConsoleHelper.readDouble(0);
        tempFigure.transform(size);
        System.out.println(tempFigure.getName() + " после масштабирования\n" + tempFigure.toString());
    }

    private static void makeMoveFigure(Figure tempFigure) {
        System.out.println("Введите дистанцию на которую передвинуть по горизонтали");
        int x = ConsoleHelper.readInt();
        System.out.println("Введите дистанцию на которую передвинуть по вертикали");
        int y = ConsoleHelper.readInt();
        tempFigure.move(x, y);
        System.out.println(tempFigure.getName() + " после перемещения\n" + tempFigure.toString());
    }

    private static void makeTurnFigure(Figure tempFigure) {
        System.out.println("Введите градусы в формате #.##");
        double angle = ConsoleHelper.readDouble();
        tempFigure.turn(angle);
        System.out.println(tempFigure.getName() + " после поворота\n" + tempFigure.toString());
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
