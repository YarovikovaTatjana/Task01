package db.repositories;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import db.MongoDB;
import db.converters.ConverterToDBObject;
import db.converters.ConverterToFigure;
import db.model.figure.Figure;
import org.bson.Document;

import java.util.ArrayList;

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
     Document startFigure = findDocById(id);
     Document newFigure = converterD.convertDoc(figure);
     db.getCollection().updateOne(startFigure,newFigure);
     db.disconnectDB();

    }


  static public void delete(Integer id) {
       Document result =  findDocById(id);
        db.getCollection().deleteOne(result);
      db.disconnectDB();
    }



}
