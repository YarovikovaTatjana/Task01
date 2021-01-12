package factory;

import db.model.coordinate.Coordinate;
import db.model.figure.Figure;
import db.model.figure.TypeFigure;
import helper.FigureCreateHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FigureFactoryFromSet {
    public static Figure createFigure(ResultSet set)  {
        try {
            int id = set.getInt("figuresid");
            String type = set.getString("typefigure");
            TypeFigure typeFigure = TypeFigure.valueOf(type);
            String coordinateString = set.getString("coordinates");
            ArrayList<Coordinate> coordinates = FigureCreateHelper.receiveCoordinates(coordinateString);
            FigureFactory factory = FigureCreateHelper.getFigureFactory(typeFigure);
            Figure newFigure = factory.createFigure(id,coordinates,typeFigure);
            return newFigure;
        }
        catch (SQLException e){
            System.out.println("Что-то пошло не так при вытаскивании данных из строки БД");
            return null;
        }


    }


}
