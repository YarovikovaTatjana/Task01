import java.util.ArrayList;

public class RectangleFactory  implements FigureFactory {
    @Override
    public Figure createFigure(ArrayList<Coordinate> coordinates) {
        return new Rectangle(coordinates);
    }
}
