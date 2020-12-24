package db.model.figure;

import db.model.coordinate.Coordinate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
@Document(collection="figures")
public class Rectangle extends Figure {
    private double length;
    private double hight;


    public Rectangle(ArrayList<Coordinate> coordinates) {
        super(coordinates);
        this.length =  calculateLine(coordinates.get(0),coordinates.get(1));
        this.hight =  calculateLine(coordinates.get(1),coordinates.get(2));
    }

    public Rectangle() {
    }

    public Rectangle(int id, ArrayList<Coordinate> coordinates, TypeFigure typeFigure) {
        super(id, coordinates, typeFigure);
        this.length =  calculateLine(coordinates.get(0),coordinates.get(1));
        this.hight =  calculateLine(coordinates.get(1),coordinates.get(2));
    }

    @Override
    public double calculateArea() {
        return length*hight;
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
        this.length = length*size;
        this.hight = hight*size;

    }

    public String receiveName() {
        return TypeFigure.valueOf(this.getClass().getSimpleName()).toString();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < coordinates.size(); i++) {
            result.append("Координаты вершины № ").append(i + 1).append(": x=").append(decimalFormat.format(coordinates.get(i).getX())).append("; y=").append(decimalFormat.format(coordinates.get(i).getY())).append(".\n");
        }
        result.append("Длина = ").append(decimalFormat.format(length)).append(". Высота = ").append(decimalFormat.format(hight)).append("\n");
        return result.toString();
    }
}
