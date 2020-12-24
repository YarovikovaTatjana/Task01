package factory;

import db.model.coordinate.Coordinate;
import db.model.figure.Circle;
import db.model.figure.Figure;
import db.model.figure.TypeFigure;

import java.util.ArrayList;

public class CircleFactory implements FigureFactory {
    @Override
    public Figure createFigure(ArrayList<Coordinate> coordinates) {
        return new Circle(coordinates);
    }

    @Override
    public Figure createFigure(int id, ArrayList<Coordinate> coordinates, TypeFigure typeFigure) {
        return new Circle(id, coordinates, typeFigure);
    }
}
