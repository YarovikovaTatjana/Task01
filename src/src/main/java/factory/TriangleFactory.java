package factory;

import db.model.coordinate.Coordinate;
import db.model.figure.Figure;
import db.model.figure.Triangle;
import db.model.figure.TypeFigure;

import java.util.ArrayList;

public class TriangleFactory  implements FigureFactory {
    @Override
    public Figure createFigure(ArrayList<Coordinate> coordinates) {
        return new Triangle(coordinates);
    }

    @Override
    public Figure createFigure(int id, ArrayList<Coordinate> coordinates, TypeFigure typeFigure) {
        return new Triangle(id,coordinates,typeFigure);
    }
}
