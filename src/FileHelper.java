import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;

class FileHelper {

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

    static void replaceFigureInFile (Figure startFigure, Figure newFigure){
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

    static ArrayList<Figure> getAllFigiresInFile()   {
       return FigureCreateHelper.getFiguresByFileHelper(lines);
    }

    static void AllFiguresInFileToString() {
          ArrayList<Figure> figures = getAllFigiresInFile();
          for (Figure figure : figures) {
              System.out.println(figure.getName() + "\n" + figure.toString());
          }
        System.out.println("Итого выведено фигур: " + figures.size());

    }

    static void removeFigureInFile(Figure figure){
        Iterator<String> iterator = lines.iterator();
        while (iterator.hasNext()) {
           String line = iterator.next();
            if (line.equals(figure.getCoordinatesToString())) {
                iterator.remove();
            }
        }
        try {
            Files.write(fileWithFigures, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void addFigureInFile(Figure figure){
        try {
            lines.add(figure.getCoordinatesToString());
            Files.write(fileWithFigures, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
