package factory;

import db.model.coordinate.Coordinate;
import db.model.figure.Figure;
import db.model.figure.Rectangle;
import db.model.figure.TypeFigure;

import java.util.ArrayList;

public class RectangleFactory  implements FigureFactory {
    @Override
    public Figure createFigure(ArrayList<Coordinate> coordinates) {
        return new Rectangle(coordinates);
    }

    @Override
    public Figure createFigure(int id, ArrayList<Coordinate> coordinates, TypeFigure typeFigure) {
        return new Rectangle(id, coordinates, typeFigure);
    }
}
