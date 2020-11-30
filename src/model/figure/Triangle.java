package model.figure;

import model.coordinate.Coordinate;

import java.util.ArrayList;

public class Triangle extends Figure {
   private double line1;
   private double line2;


    public Triangle(ArrayList<Coordinate> coordinates) {
        super(coordinates);
        this.line1=calculateLine(coordinates.get(0),coordinates.get(1));
        this.line2=calculateLine(coordinates.get(0),coordinates.get(2));
    }



    @Override
    public double calculateArea() {
        return super.calculateArea();
    }


    @Override
    public void transform(double size) {
        coordinates.get(1).setX(coordinates.get(1).getX()+ (line1*size));
        coordinates.get(2).setY(coordinates.get(2).getY()+ (line2*size));
    }

    public String getName() {
        return TypeFigure.valueOf(this.getClass().getSimpleName()).toString();
    }

}
