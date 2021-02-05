package db.model.figure;

import db.model.coordinate.Coordinate;

import java.util.ArrayList;


public class Polygon extends Figure {
    public Polygon(ArrayList<Coordinate> coordinates) {
        super(coordinates);
        this.typeFigure=TypeFigure.Polygon;
    }

    public Polygon() {
    }

    public Polygon(int id, ArrayList<Coordinate> coordinates, TypeFigure typeFigure) {
        super(id, coordinates, typeFigure);
    }

    @Override
    public void move(int distanceX, int distanceY) {
        super.move(distanceX, distanceY);
    }

    @Override
    public void transform(double size) {
        Coordinate startCoordinate = coordinates.get(0);
        double startX = startCoordinate.getX();
        double startY = startCoordinate.getY();
        for (int i = 1; i < coordinates.size(); i++) {
            double x = (coordinates.get(i).getX()-startX)*size + startX;
            double y = (coordinates.get(i).getY()-startY)*size + startY;
            coordinates.get(i).setX(x);
            coordinates.get(i).setY(y);
        }

    }
    public String receiveName() {
        return TypeFigure.valueOf(this.getClass().getSimpleName()).toString();
    }

}
