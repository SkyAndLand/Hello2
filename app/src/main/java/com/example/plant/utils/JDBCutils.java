package com.example.plant.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCutils {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static Connection getConn() {
        Connection  conn = null;
        try {
            conn= DriverManager.getConnection("jdbc:mysql://192.168.43.215:3306/user","root","1234");//注意格式和空格
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return conn;
    }

    public static void close(Connection conn){
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}


