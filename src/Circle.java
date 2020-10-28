import java.text.DecimalFormat;
import java.util.ArrayList;

public class Circle extends Figure  {
   private double radius;
   private Coordinate centre;



    Circle(ArrayList<Coordinate> coordinates) {
        super(coordinates);
        this.name = "Окружность";
        this.coordinates = coordinates;
        this.radius=calculateLine(coordinates.get(0),coordinates.get(1))/2;
        this.centre = calculateCentre();

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

    @Override
    public String toString() {
        return "Координата центра: x = " + decimalFormat.format(centre.getX()) + "; y = " + decimalFormat.format(centre.getY()) + ".\n" +
                "Радиус = " + decimalFormat.format(radius) + "\n";
    }
}
