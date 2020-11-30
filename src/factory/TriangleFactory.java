package factory;

import model.coordinate.Coordinate;
import model.figure.Figure;
import model.figure.Triangle;

import java.util.ArrayList;

public class TriangleFactory  implements FigureFactory {
    @Override
    public Figure createFigure(ArrayList<Coordinate> coordinates) {
        return new Triangle(coordinates);
    }
}
