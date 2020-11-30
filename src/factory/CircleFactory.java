package factory;

import model.coordinate.Coordinate;
import model.figure.Circle;
import model.figure.Figure;

import java.util.ArrayList;

public class CircleFactory implements FigureFactory {
    @Override
    public Figure createFigure(ArrayList<Coordinate> coordinates) {
        return new Circle(coordinates);
    }
}
