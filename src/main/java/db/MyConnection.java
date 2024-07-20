package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    public  static Connection connection =null;

    private static String url ="jdbc:mysql://localhost:3306/file_db";
    private static String user ="root";
    private static String password="rakshit123";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection =DriverManager.getConnection(url,user,password);
            //.getconnection()  sql exception throw karti hai isliye use handle kiya hai
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println("yessssssssssss!!!!!!!!");
        return connection;
    }

    public static void closeConnection(){
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e); // e.printstacktrace
            }
        }
    }

//    public static void main(String[] args) {
//        MyConnection.getConnection();
//    }
}
