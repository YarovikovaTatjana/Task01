package db.model.figure;

import db.model.coordinate.Coordinate;

import java.util.ArrayList;

public class Triangle extends Figure {
    private double line1;
   private double line2;


    public Triangle(ArrayList<Coordinate> coordinates) {
        super(coordinates);
        this.typeFigure=TypeFigure.Triangle;
        this.line1=calculateLine(coordinates.get(0),coordinates.get(1));
        this.line2=calculateLine(coordinates.get(0),coordinates.get(2));
    }

    public Triangle() {
    }

    public Triangle(int id, ArrayList<Coordinate> coordinates, TypeFigure typeFigure) {
        super(id, coordinates, typeFigure);
        this.line1 = calculateLine(coordinates.get(0),coordinates.get(1));
        this.line2 = calculateLine(coordinates.get(0),coordinates.get(2));
    }

    @Override
    public double calculateArea() {
        return super.calculateArea();
    }


    @Override
    public void transform(double size) {
        Coordinate startCoordinate = coordinates.get(0);
        double startX = startCoordinate.getX();
        double startY = startCoordinate.getY();
        for (int i = 1; i < coordinates.size(); i++) {
            double x = (coordinates.get(i).getX() - startX) * size + startX;
            double y = (coordinates.get(i).getY() - startY) * size + startY;
            coordinates.get(i).setX(x);
            coordinates.get(i).setY(y);
        }
    }

    public String receiveName() {
        return TypeFigure.valueOf(this.getClass().getSimpleName()).toString();
    }

}
