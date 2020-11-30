package factory;

import model.coordinate.Coordinate;
import model.figure.Figure;

import java.util.ArrayList;

public interface FigureFactory {
    Figure createFigure(ArrayList<Coordinate> coordinates);
}
