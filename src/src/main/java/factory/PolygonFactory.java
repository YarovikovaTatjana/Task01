package factory;

import db.model.coordinate.Coordinate;
import db.model.figure.Figure;
import db.model.figure.Polygon;
import db.model.figure.TypeFigure;

import java.util.ArrayList;

public class PolygonFactory  implements FigureFactory {
    @Override
    public Figure createFigure(ArrayList<Coordinate> coordinates) {
        return new Polygon(coordinates);
    }

    @Override
    public Figure createFigure(int id, ArrayList<Coordinate> coordinates, TypeFigure typeFigure) {
        return new Polygon(id,coordinates,typeFigure);
    }
}
