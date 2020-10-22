import java.util.ArrayList;

public class CircleFactory implements FigureFactory {
    @Override
    public Figure createFigure(ArrayList<Coordinate> coordinates) {
        return new Circle(coordinates);
    }
}
