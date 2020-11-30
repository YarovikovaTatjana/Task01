package factory;

import model.coordinate.Coordinate;
import model.figure.Figure;
import model.figure.Rectangle;

import java.util.ArrayList;

public class RectangleFactory  implements FigureFactory {
    @Override
    public Figure createFigure(ArrayList<Coordinate> coordinates) {
        return new Rectangle(coordinates);
    }
}
