package model.figure;

import model.coordinate.Coordinate;

import java.util.ArrayList;

public class Polygon extends Figure {
    public Polygon(ArrayList<Coordinate> coordinates) {
        super(coordinates);
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
    public String getName() {
        return TypeFigure.valueOf(this.getClass().getSimpleName()).toString();
    }

}
