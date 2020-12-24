package db.model.figure;

import db.model.coordinate.Coordinate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
@Document(collection="figures")
public class Triangle extends Figure {
    private double line1;
   private double line2;


    public Triangle(ArrayList<Coordinate> coordinates) {
        super(coordinates);
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
        coordinates.get(1).setX(coordinates.get(1).getX()+ (line1*size));
        coordinates.get(2).setY(coordinates.get(2).getY()+ (line2*size));
    }

    public String receiveName() {
        return TypeFigure.valueOf(this.getClass().getSimpleName()).toString();
    }

}
