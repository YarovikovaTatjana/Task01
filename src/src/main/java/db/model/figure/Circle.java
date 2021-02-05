package db.model.figure;

import db.model.coordinate.Coordinate;

import java.util.ArrayList;

public class Circle extends Figure {
   private double radius;
   private Coordinate centre;

    public Circle(int id, ArrayList<Coordinate> coordinates, TypeFigure typeFigure) {
        super(id, coordinates, typeFigure);
        this.radius=calculateLine(coordinates.get(0),coordinates.get(1))/2;
        this.centre = calculateCentre();
    }

    public Circle(ArrayList<Coordinate> coordinates) {
        super(coordinates);
        this.typeFigure=TypeFigure.Circle;
        this.radius=calculateLine(coordinates.get(0),coordinates.get(1))/2;
        this.centre = calculateCentre();

    }

    public Circle() {
    }

    public double getRadius() {
        return radius;
    }

    public Coordinate getCentre() {
        return centre;
    }

    private Coordinate calculateCentre() {
        double x = coordinates.get(0).getX() + (coordinates.get(1).getX()-coordinates.get(0).getX())/2;
        double y = coordinates.get(0).getY() + (coordinates.get(1).getY()-coordinates.get(0).getY())/2;
        return new Coordinate(x,y);
    }

    @Override
    public double calculateArea() {
        return 3.14*Math.pow(radius,2);
    }

    private void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public void transform(double size) {
       setRadius(this.radius*size);
       coordinates.get(0).setX(centre.getX()-radius);
       coordinates.get(1).setX(centre.getX()+radius);
    }

    @Override
    public void move(int distanceX, int distanceY) {
        this.centre.setX(this.centre.getX()+distanceX);
        this.centre.setY(this.centre.getY()+distanceY);
        for (Coordinate coordinate : coordinates) {
            coordinate.setX(coordinate.getX() + distanceX);
            coordinate.setY(coordinate.getY() + distanceY);

        }

    }

    @Override
    public void turn(double angle) {
        System.out.println("Вращение окружности не производится");
    }

    public String receiveName() {
        return TypeFigure.valueOf(this.getClass().getSimpleName()).toString();
    }

    @Override
    public String toString() {
        return "Координата центра: x = " + decimalFormat.format(centre.getX()) + "; y = " + decimalFormat.format(centre.getY()) + ".\n" +
                "Радиус = " + decimalFormat.format(radius) + "\n";
    }
}
