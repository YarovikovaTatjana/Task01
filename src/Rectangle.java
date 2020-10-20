import java.util.ArrayList;

public class Rectangle extends Figure {
    private double length;
    private double hight;


    Rectangle(ArrayList<Coordinate> coordinates) {
        super(coordinates);
        this.length =  calculateLine(coordinates.get(0),coordinates.get(1));
        this.hight =  calculateLine(coordinates.get(1),coordinates.get(2));
        this.name = (this.length!=hight) ? "Прямогугольник" : "Квадрат";

    }

    private void setCoordinates(Coordinate coordinate, double length, double hight) {
        coordinates.clear();
        coordinates.add(0, coordinate);
        coordinates.add(1, new Coordinate(coordinate.getX()+length,coordinate.getY()));
        coordinates.add(2, new Coordinate(coordinate.getX()+length,coordinate.getY()+hight));
        coordinates.add(3, new Coordinate(coordinate.getX(),coordinate.getY()+hight));
        this.length = length;
        this.hight = hight;
    }

    @Override
    public double calculateArea() {
        return length*hight;
    }


    @Override
    public void transform(double size) {
    setCoordinates(coordinates.get(0), (length*size),(hight*size));
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
