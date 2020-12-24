package factory;

import db.model.coordinate.Coordinate;
import db.model.figure.Figure;
import db.model.figure.TypeFigure;
import helper.FigureCreateHelper;
import org.bson.Document;

import java.util.ArrayList;


public class FigureFactoryFromJson {

    public Figure createFigure (Document figure){
        int id = figure.getInteger("id");
        String type = figure.getString("typeFigure");
        TypeFigure typeFigure = TypeFigure.valueOf(type);
        ArrayList<Document> coordinatesDoc = (ArrayList<Document>) figure.get("coordinates");
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        coordinatesDoc.forEach(c -> coordinates.add(new Coordinate(c.getDouble("x"),c.getDouble("y"))));

        FigureFactory factory = FigureCreateHelper.getFigureFactory(typeFigure);
        Figure newFigure = factory.createFigure(id,coordinates,typeFigure);
        return newFigure;
    }

}
