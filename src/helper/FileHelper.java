package helper;

import model.figure.Figure;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

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
         String targetLine = startFigure.getCoordinatesToString();
         String newValue = newFigure.getCoordinatesToString();
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
              System.out.println(figure.getName() + "\n" + figure.toString());
          }
        System.out.println("Итого выведено фигур: " + figures.size());

    }

    public static void removeFigureInFile(Figure figure){
        lines.removeIf(line -> line.equals(figure.getCoordinatesToString()));
        try {
            Files.write(fileWithFigures, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addFigureInFile(Figure figure){
        try {
            lines.add(figure.getCoordinatesToString());
            Files.write(fileWithFigures, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
