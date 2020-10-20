import java.util.ArrayList;

public class Polygon extends Figure {
    Polygon(ArrayList<Coordinate> coordinates) {
        super(coordinates);
        this.name = "Многоугольник c " + coordinates.size() + " углами";
    }

    @Override
    public void move(int distanceX, int distanceY) {
        super.move(distanceX, distanceY);
    }

    @Override
    public void transform(double size) {
        ArrayList<Coordinate> newCoordinates = new ArrayList<>();
        Coordinate startCoordinate = coordinates.get(0);
        double startX = startCoordinate.getX();
        double startY = startCoordinate.getY();
        newCoordinates.add(startCoordinate);
        for (int i = 1; i < coordinates.size(); i++) {
            double x = coordinates.get(i).getX();
            double y = coordinates.get(i).getY();
            newCoordinates.add(new Coordinate((x-startX)*size,(y-startY)*size));
        }
        coordinates.clear();
        coordinates = newCoordinates;
    }


}
