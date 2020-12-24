package start;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import db.repositories.FigureRepositoryCustom;
import helper.ConsoleHelper;
import helper.FigureCreateHelper;
import helper.FileHelper;
import db.model.figure.Figure;

import java.io.IOException;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;


class Menu {
   private static StringWriter writer = new StringWriter();
   private static ObjectMapper mapper = new ObjectMapper();
   private static FigureCollection collection;
   private static String result;
   private static FigureRepositoryCustom repository = new FigureRepositoryCustom();

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
        //serializeFigures();
       // deserializeFigures();
        boolean isAction = true;
            while (isAction) {
                System.out.println(mainMenu);
                switch (ConsoleHelper.readInt()) {
                    case 1:
                        FileHelper.AllFiguresToString(FigureRepositoryCustom.findAll());
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
        //ArrayList<Figure> temp = FileHelper.getAllFigiresInFile();
        ArrayList<Figure> temp = FigureRepositoryCustom.findAll();
        System.out.println("Доступно фигур: " + temp.size() + ".\nВведите порядковый номер (начиная с 1) удаляемой фигуры");
        number = ConsoleHelper.readInt(0, temp.size() + 1);
        //FileHelper.removeFigureInFile(temp.get(number - 1));
        FigureRepositoryCustom.delete(number - 1);
    }

    private static void startAddFigure() {
        System.out.println("Введите координаты новой фигуры. \nДля окружности введите 2 координаты крайних точек диаметра. \nДля остальных фигур - координаты вершин");
        Figure userFigure = FigureCreateHelper.getFigureByConsole();
       // FileHelper.addFigureInFile(userFigure);
        FigureRepositoryCustom.insert(userFigure);
        System.out.println("Добавлена фигура: " + userFigure.receiveName() + "\n" + userFigure.toString());
    }

    private static void startActionFigureMenu() {
        int number;
        ArrayList<Figure> figures = FigureRepositoryCustom.findAll();
        System.out.println("Доступно фигур: " + figures.size() + ".\nВведите порядковый номер (начиная с 1) для вывода желаемой фигуры");
        number = ConsoleHelper.readInt(0, figures.size() + 1);
        Figure figure = FigureRepositoryCustom.findFigureById(number - 1);
        System.out.println(figure.receiveName() + "\n" + figure.toString());
        actionFigure(figure);
    }


    private static void actionFigure(Figure figure) {
        boolean isAction = true;
        int id = figure.getId();
       // Figure tempFigure = FigureCreateHelper.createCopyFigure(figure);
        while (isAction) {
            System.out.println(actionSubMenu);
            switch (ConsoleHelper.readInt()) {
                case 1:
                    makeTurnFigure(figure);
                    saveFigureInDB(figure,id);
                   // saveFigureInFile(figure,tempFigure);
                    break;
                case 2:
                    makeMoveFigure(figure);
                    saveFigureInDB(figure,id);
                    // saveFigureInFile(figure,tempFigure);
                    break;
                case 3:
                    makeTransformFigure(figure);
                    saveFigureInDB(figure,id);
                    // saveFigureInFile(figure,tempFigure);
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

    private static void serializeFigures (){
        collection = new FigureCollection();
        for (Figure f: FileHelper.getAllFigiresInFile()) {
            collection.figureCollection.add(f);
        }
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            try {
                mapper.writeValue(writer,collection);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        result = writer.toString();
        System.out.println(result);
    }

    private static void deserializeFigures (){
       try {
           collection = mapper.readValue(result,FigureCollection.class);
       }
       catch (IOException e){
           e.printStackTrace();
       }
        for (Figure figure: collection.figureCollection) {
            System.out.println(figure.toString());
        }

    }

    private static void makeTransformFigure(Figure tempFigure) {
        System.out.println("Введите параметр масштабирования в формате #.## \n" +
                "(от 0 до 1 - для уменьшения, больше 1 - для увеличения)");
        double size = ConsoleHelper.readDouble(0);
        tempFigure.transform(size);
        System.out.println(tempFigure.receiveName() + " после масштабирования\n" + tempFigure.toString());
    }

    private static void makeMoveFigure(Figure tempFigure) {
        System.out.println("Введите дистанцию на которую передвинуть по горизонтали");
        int x = ConsoleHelper.readInt();
        System.out.println("Введите дистанцию на которую передвинуть по вертикали");
        int y = ConsoleHelper.readInt();
        tempFigure.move(x, y);
        System.out.println(tempFigure.receiveName() + " после перемещения\n" + tempFigure.toString());
    }

    private static void makeTurnFigure(Figure tempFigure) {
        System.out.println("Введите градусы в формате #.##");
        double angle = ConsoleHelper.readDouble();
        tempFigure.turn(angle);
        System.out.println(tempFigure.receiveName() + " после поворота\n" + tempFigure.toString());
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

    private static void saveFigureInDB(Figure figure, Integer id){
        boolean isContinue = true;
        while (isContinue) {
            System.out.println("Выберете действие:\n1 - Сохранить\n2 - Отмена");
            switch (ConsoleHelper.readInt()) {
                case 1:
                    FigureRepositoryCustom.update(id, figure);
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

@JsonAutoDetect
    public static class FigureCollection{
        public ArrayList<Figure> figureCollection;

        public FigureCollection() {
            this.figureCollection = new ArrayList<>();
        }
    }


}
