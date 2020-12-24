package helper;

import db.model.figure.Figure;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileHelper {

  private static ArrayList<String> lines;
  private static Path fileWithFigures;
    static {
        fileWithFigures = Paths.get(FigureCreateHelper.FILENAME);
        try {
            lines = (ArrayList<String>) Files.readAllLines(fileWithFigures);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void replaceFigureInFile (Figure startFigure, Figure newFigure){
         String targetLine = startFigure.receiveCoordinatesToString();
         String newValue = newFigure.receiveCoordinatesToString();
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).equals(targetLine)) {
                lines.set(i,newValue);
            }
        }
        try {
            Files.write(fileWithFigures, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Figure> getAllFigiresInFile()   {
       return FigureCreateHelper.getFiguresByFileHelper(lines);
    }

    public static void AllFiguresInFileToString() {
          ArrayList<Figure> figures = getAllFigiresInFile();
          for (Figure figure : figures) {
              System.out.println(figure.receiveName() + "\n" + figure.toString());
          }
        System.out.println("Итого выведено фигур: " + figures.size());

    }

    public static void AllFiguresToString(ArrayList<Figure> figures) {
        for (Figure figure : figures) {
            System.out.println(figure.receiveName() + "\n" + figure.toString());
        }
        System.out.println("Итого выведено фигур: " + figures.size());

    }

    public static void removeFigureInFile(Figure figure){
        lines.removeIf(line -> line.equals(figure.receiveCoordinatesToString()));
        try {
            Files.write(fileWithFigures, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addFigureInFile(Figure figure){
        try {
            lines.add(figure.receiveCoordinatesToString());
            Files.write(fileWithFigures, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
