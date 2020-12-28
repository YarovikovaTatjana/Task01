package db.repositories;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import db.MongoDB;
import db.converters.ConverterToDBObject;
import db.converters.ConverterToFigure;
import db.model.figure.Figure;
import org.bson.Document;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mongodb.client.model.Updates.set;

public class FigureRepositoryCustom  {
 static MongoDB db = new MongoDB();
 static ConverterToDBObject converterD = new ConverterToDBObject();
 static ConverterToFigure converterF = new ConverterToFigure();




static public ArrayList<Figure> findAll() {
        db.connectDB();
        ArrayList<Document> figuresDB = db.getCollection().find().into(new ArrayList<>());
        ArrayList<Figure> figuresFromDB = new ArrayList<>();
        for (int i = 0; i < figuresDB.size(); i++) {
            Figure figure = converterF.convert(figuresDB.get(i));
            figuresFromDB.add(figure);
        }

    db.disconnectDB();
        return figuresFromDB;
    }


  static public Figure findFigureById(Integer id) {
        Document result = findDocById(id);
        Figure figure = converterF.convert(result);
      db.disconnectDB();
        return figure;
    }

  static private Document findDocById(Integer id){
        db.connectDB();
        BasicDBObject query = new BasicDBObject();
        query.put("id", id);
        ArrayList<Document> documents = db.getCollection().find(query).into(new ArrayList<>());
        Document doc = documents.get(0);
        db.disconnectDB();
        return doc;
    }


  static public void insert(Figure figure) {
        db.connectDB();
        DBObject dbObject = converterD.convert(figure);
        Document document = Document.parse(dbObject.toString());
        db.getCollection().insertOne(document);
        db.disconnectDB();
    }


 static public void update(Integer id, Figure figure) {
     db.connectDB();
     Document newFigure = converterD.convertDoc(figure);
     db.getCollection().updateOne(new BasicDBObject("id", id), set("coordinates", newFigure.get("coordinates")));
     db.disconnectDB();

    }

    static public void updateId(Integer id, Integer newId) {
        db.connectDB();
        db.getCollection().updateOne(new BasicDBObject("id", id), set("id", newId));
        db.disconnectDB();
    }


  static public void delete(Integer id) {
       Document result =  findDocById(id);
       db.connectDB();
        db.getCollection().deleteOne(result);
      db.disconnectDB();
    }

    static public int findCountDoc(){
        db.connectDB();
        long l = db.getCollection().countDocuments();
        db.disconnectDB();
        return (int)l;
    }

}
