import java.util.ArrayList;

public class Triangle extends Figure {
   private double line1;
   private double line2;


    Triangle(ArrayList<Coordinate> coordinates) {
        super(coordinates);
        this.name="Треугольник";
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



}
