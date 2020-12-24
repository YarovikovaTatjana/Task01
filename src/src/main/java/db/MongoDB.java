package db;

import com.mongodb.DBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import db.converters.ConverterToDBObject;
import db.model.figure.Figure;
import helper.FileHelper;
import org.bson.Document;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MongoDB {
    private MongoClient mongoClient;
    private MongoDatabase mongoDB;
    private MongoCollection<Document> collection;


    public void connectDB(){
        try {
            Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);


            mongoClient =  MongoClients.create("mongodb+srv://admin:admin12@cluster0.rma6s.mongodb.net/<baseTask02>?retryWrites=true&w=majority");
            mongoDB = mongoClient.getDatabase("baseTask02");
            collection = mongoDB.getCollection("figures");
            if (collection==null) fillingDBByFile();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void disconnectDB(){
        mongoClient.close();
    }

    public MongoDatabase getMongoDB() {
        return mongoDB;
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }

    private void fillingDBByFile() {
        ArrayList<Figure> figures = FileHelper.getAllFigiresInFile();
        ConverterToDBObject converterD = new ConverterToDBObject();
        for (int i = 0; i < figures.size(); i++) {
            DBObject dbObject = converterD.convert(figures.get(i));
            Document document = Document.parse(dbObject.toString());
            collection.insertOne(document);
        }
    }

}
