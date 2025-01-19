package com.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

class TestConnection {
    public static void main(String[] args) {
        try {
            String url = "jdbc:mysql://localhost:3306/examdb";
            String user = "root";
            String password = "mahdy";
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM exam_table LIMIT 1");

            while (rs.next()) {
                System.out.println(rs.getString(1)); // Sample output
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class App {

    public void run(){

    }

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}
