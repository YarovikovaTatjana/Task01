package factory;

import db.model.coordinate.Coordinate;
import db.model.figure.Figure;
import db.model.figure.TypeFigure;

import java.util.ArrayList;

public interface FigureFactory {
    Figure createFigure(ArrayList<Coordinate> coordinates);

    Figure createFigure(int id, ArrayList<Coordinate> coordinates, TypeFigure typeFigure);
}
