package model.figure;

import interfaces.IMovable;
import interfaces.ITransformable;
import interfaces.ITurnable;
import model.coordinate.Coordinate;

import java.text.DecimalFormat;
import java.util.ArrayList;

public abstract class Figure implements ITurnable, IMovable, ITransformable {
ArrayList <Coordinate> coordinates;
DecimalFormat decimalFormat = new DecimalFormat("#.###");

  public Figure(ArrayList<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public double calculateArea(){
    double sum1=0;
    double sum2=0;
    for (int i = 0; i <coordinates.size()-1 ; i++) {
        sum1=sum1+coordinates.get(i).getX()*coordinates.get(i+1).getY();
        sum2=sum2+coordinates.get(i).getY()*coordinates.get(i+1).getX();
    }

        return Math.abs(sum1-sum2)/2.0;
}

    @Override
    public void move(int distanceX, int distanceY) {
        for (Coordinate coordinate : coordinates) {
            double x = coordinate.getX() + distanceX;
            double y = coordinate.getY() + distanceY;
            coordinate.setX(x);
            coordinate.setY(y);
        }
    }

    public ArrayList<Coordinate> getCoordinates() {
        return coordinates;
    }

    public String getCoordinatesToString(){
        StringBuilder tempCoordinates  = new StringBuilder();
        for (int i = 0; i < coordinates.size(); i++) {
            tempCoordinates.append(coordinates.get(i).getX()).append(" ").append(coordinates.get(i).getY());
            if (i<coordinates.size()-1) tempCoordinates.append(";");
        }

        return tempCoordinates.toString().replace(".", ",");
    }

    public double calculateLine(Coordinate coordinate1, Coordinate coordinate2){
    return (Math.pow(Math.pow(coordinate2.getX()-coordinate1.getX(),2)+Math.pow(coordinate2.getY()-coordinate1.getY(),2),0.5));
    }

    @Override
    public void turn(double angle) {
        double radians = Math.toRadians(angle);
        for (Coordinate coordinate : coordinates) {
            double x = (coordinate.getX()*Math.cos(radians) - coordinate.getY() * Math.sin(radians));
            double y = (coordinate.getX()*Math.sin(radians) + coordinate.getY() * Math.cos(radians));
            coordinate.setX(x);
            coordinate.setY(y);
        }

    }
    public abstract String getName();

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < coordinates.size(); i++) {
            result.append("Координаты вершины № ").append(i + 1).append(": x=").append(decimalFormat.format(coordinates.get(i).getX())).append("; y=").append(decimalFormat.format(coordinates.get(i).getY())).append(".\n");
        }
        return result.toString();
    }
}
