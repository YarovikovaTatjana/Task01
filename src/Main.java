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
  private static FigureCreateHelper createHelper = new FigureCreateHelper();


    public static void main(String[] args){
       ArrayList<Figure> figures= createHelper.getFiguresByFile();
       Menu.startMenu(figures);
    }


}
