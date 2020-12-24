package db.converters;

import com.mongodb.DBObject;
import db.model.figure.Figure;
import factory.FigureFactoryFromJson;
import org.bson.Document;
import org.springframework.core.convert.converter.Converter;

;

public class ConverterToFigure implements Converter<DBObject, Figure> {
    @Override
    public Figure convert(DBObject dbObject) {
        FigureFactoryFromJson factory = new FigureFactoryFromJson();
        return factory.createFigure(Document.parse(dbObject.toString()));
    }

    public Figure convert(Document document) {
        FigureFactoryFromJson factory = new FigureFactoryFromJson();
        return factory.createFigure(document);
    }
}
