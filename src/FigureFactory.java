import java.util.ArrayList;

public interface FigureFactory {
    Figure createFigure(ArrayList<Coordinate> coordinates);
}
