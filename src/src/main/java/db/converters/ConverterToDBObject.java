package db.converters;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import db.model.figure.Figure;
import org.bson.Document;
import org.springframework.core.convert.converter.Converter;

public class ConverterToDBObject implements Converter<Figure, DBObject> {
 //   static int id;

    @Override
    public DBObject convert(Figure figure) {
        DBObject dbo = new BasicDBObject();
        dbo.put("id", figure.getId());
        dbo.put("typeFigure", figure.receiveType());
        BasicDBList innerList = new BasicDBList();
        for (int i = 0; i < figure.getCoordinates().size(); i++) {
            DBObject coordinate = new BasicDBObject();
            coordinate.put("x", figure.getCoordinates().get(i).getX());
            coordinate.put("y", figure.getCoordinates().get(i).getY());
            innerList.add(i, coordinate);

        }
        dbo.put("coordinates", innerList);
        //id++;
        return dbo;
    }


    public Document convertDoc(Figure figure){
        return Document.parse(this.convert(figure).toString());
    }



}
