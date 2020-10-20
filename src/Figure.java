import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Formatter;

public abstract class Figure implements Turnable, Movable, Transformable {
ArrayList <Coordinate> coordinates;
String name;
DecimalFormat decimalFormat = new DecimalFormat("#.###");

    Figure(ArrayList<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public double calculateArea(){
    double sum1=0;
    double sum2=0;
    for (int i = 0; i <coordinates.size()-1 ; i++) {
        sum1=sum1+coordinates.get(i).getX()*coordinates.get(i+1).getY();
        sum2=sum2+coordinates.get(i).getY()*coordinates.get(i+1).getX();
    }

        return (sum1+sum2)/2.0;
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

    String getName() {
        return name;
    }

    double calculateLine(Coordinate coordinate1, Coordinate coordinate2){
    return (Math.pow(Math.pow(coordinate2.getX()-coordinate1.getX(),2)+Math.pow(coordinate2.getY()-coordinate1.getY(),2),0.5));
    }

    @Override
    public void turn(double angle) {
        ArrayList <Coordinate> newCoordinates = new ArrayList<>();
        for (Coordinate coordinate : coordinates) {
            double x = (coordinate.getX() + Math.cos(angle) - coordinate.getY() * Math.sin(angle));
            double y = (coordinate.getX() + Math.sin(angle) + coordinate.getY() * Math.cos(angle));
            newCoordinates.add(new Coordinate(x, y));
        }
        coordinates.clear();
        coordinates=newCoordinates;

    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < coordinates.size(); i++) {
            result.append("Координаты вершины № ").append(i + 1).append(": x=").append(decimalFormat.format(coordinates.get(i).getX())).append("; y=").append(decimalFormat.format(coordinates.get(i).getY())).append(".\n");
        }
        return result.toString();
    }
}
