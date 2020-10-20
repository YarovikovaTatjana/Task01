import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

class FigureFactorySimple {
    private Figure figure = null;


    Figure createFigure(ArrayList<Coordinate> coordinates){

    try  {
        switch (coordinates.size()){
            case 2:
                figure= new Circle(coordinates);
                break;
            case 3:
                figure= new Triangle(coordinates);
                break;
            case 4:
                figure= new Rectangle(coordinates);
                break;
            default:
                figure=new Polygon(coordinates);
                break;
        }
    }
    catch (NullPointerException e){
        System.out.println("NullPointerException");
    }
    return figure;

    }





}
