package db.repositories;

import db.SqlDB;
import db.model.figure.Figure;
import factory.FigureFactoryFromSet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class FigureRepositoryCustom  {

 static SqlDB db = new SqlDB();
 static String table = db.getTABLENAME();
 static final String SELECT = "SELECT * FROM " + table;
 static final String WHERE_ID = " WHERE figuresid = ";
 static final String INSERT = "INSERT INTO " + table + " (figuresid, typefigure, coordinates) VALUES";
 static final String DELETE = "DELETE FROM " + table + WHERE_ID;
 static final String UPDATE_COORDINATES = "UPDATE " + table + " SET coordinates=";
 static final String UPDATE_ID = "UPDATE " + table + " SET figuresid=";
 static final String SELECT_COUNT = "SELECT COUNT(*) as count FROM " + table;



static public ArrayList<Figure> findAll()  {
        ArrayList<Figure> figuresFromDB = new ArrayList<>();
         try  {
            ResultSet set = getSelect(getQuerySelectALL());
                if (set != null) {
            while (set.next()) {
                    Figure figure= FigureFactoryFromSet.createFigure(set);
                    if (figure!=null) figuresFromDB.add(figure);

            }
        }
    } catch (SQLException e) {
             System.out.println("что-то пошло не так поиске следующего сета");
         }
    db.disconnectDB();
        return figuresFromDB;
    }

  static public Figure findFigureById(Integer id) {
        ResultSet set = getSelect(getQuerySelectWithParametrs(id));
      try {
          set.next();
      } catch (SQLException e) {
          System.out.println("что-то пошло не так при переходе на строку");;
      }
      Figure figure =  FigureFactoryFromSet.createFigure(set);
      db.disconnectDB();
        return figure;
    }




  static public void insert(Figure figure) {
        insertRow(getInsertQuery(figure));
        db.disconnectDB();
    }


 static public void update(Integer id, Figure figure) {
     updateOrDeleteRow(getUpdateCoordinatesQuery(figure, id));
     db.disconnectDB();
    }

    static public void updateId(Integer id, Integer newId) {
        updateOrDeleteRow(getUpdateIDQuery(id,newId));
        db.disconnectDB();
    }



    static public void delete(Integer id) {
      updateOrDeleteRow(getDeleteQuery(id));
      db.disconnectDB();
    }

    static public int findCountDoc() {
        ResultSet set = getSelect(SELECT_COUNT);
        int count = 0;
        try {
            set.next();
            count = set.getInt("count");
        } catch (SQLException e) {
            System.out.println("count not found");;
        }
        db.disconnectDB();
        return count;
    }

    private static String getQuerySelectALL (){
        String query = SELECT;
        return query;
    }

    private static String getQuerySelectWithParametrs (int id){
        String query = SELECT + WHERE_ID + id;
        return query;
    }

    private static String getInsertQuery (Figure figure){
        String valuesInsert = "(" + figure.getId() + ", '" + figure.receiveType() + "', '" + figure.receiveCoordinatesToString() + "')";
        String query = INSERT + valuesInsert;
        return query;
    }

    private static String getUpdateCoordinatesQuery (Figure figure, int id){
        String query =UPDATE_COORDINATES + "'" + figure.receiveCoordinatesToString() + "'" + WHERE_ID + id;
        return query;
    }
    private static String getUpdateIDQuery (int id, int new_id){
        String query =UPDATE_ID +  new_id  + WHERE_ID + id;
        return query;
    }

    private static String getDeleteQuery (int id){
        String query =DELETE + id;
        return query;
    }

    private static ResultSet getSelect(String query) {
        db.connectDB();
        Statement statement = db.getStatement();
        ResultSet set = null;
        try {
            set = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Не удалось выполнить поиск в БД");
        }
        return  set;

    }

    private static void updateOrDeleteRow(String query) {
        db.connectDB();
        Statement statement = db.getStatement();
        try {
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println("Не удалось внести изменения в БД");;
        }
    }

    private static void insertRow(String query) {
        db.connectDB();
        Statement statement = db.getStatement();
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Запись не добавлена в БД");;
        }
    }


}
