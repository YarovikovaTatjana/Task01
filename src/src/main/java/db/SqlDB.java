package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlDB {

    static final String DBNAME = "baseTask02";
    static final String TABLENAME = "figures";
    static final String URL = "jdbc:postgresql://localhost:5432/"+DBNAME;
    static final String USERROLE="admin12";
    static final String USERPASSWORD = "admin12";
    Connection connection;
    Statement statement;


    public void connectDB(){
        try{
            connection = DriverManager.getConnection(URL,USERROLE,USERPASSWORD);
        }
        catch (Exception e){
            System.out.println("Connection failed!");
        }

    }

    public Statement getStatement() {
        connectDB();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("statement not create");;
        }
        return statement;
    }



    public void  disconnectDB(){
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("disconnectDB fail");
        }
    }

       public static String getTABLENAME() {
        return TABLENAME;
    }


}
