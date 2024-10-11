package com.example.cse360_phase2;

import javafx.scene.Scene;

import java.sql.DriverManager;
import java.sql.*;

public class JDBCConnection {
    Connection connection;
    ResultSet result;
    Exception error;
    public JDBCConnection() {
    }

    public ResultSet fetchQuery(String query) {
        try {
            this.connection =  DriverManager.getConnection("jdbc:mysql://bookbetter-aws.czoua2woyqte.us-east-2.rds.amazonaws.com:3306/user", "admin", "!!mqsqlhubbard2024");

            Statement statement = connection.createStatement();
            this.result = statement.executeQuery(query);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Connection Started");

        return null;
    }

    public ResultSet logIn(String username, String password) {
        try {
            this.connection =  DriverManager.getConnection("jdbc:mysql://bookbetter-aws.czoua2woyqte.us-east-2.rds.amazonaws.com:3306/user", "admin", "!!mqsqlhubbard2024");
            Statement statement = connection.createStatement();
            this.result = statement.executeQuery("SELECT * FROM users WHERE username = '" + username + "'");

            if (result.next()) {
                String pass = result.getString("password");
                if (password.equals(pass)) {
                    System.out.println("Logged in ");

                    User user = new User((int) result.getInt("id"), username, "Seller", password);
                    SceneController sceneController = HelloApplication.sceneController;
                    UserInfo userInfoCreator = new UserInfo(user, sceneController);

                    // Get the user info scene and pass the main scene for returning
                    Scene userInfoScene = userInfoCreator.getScene();
                    sceneController.switchScene(userInfoScene);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public ResultSet registerUser(String username, String password) {
        try {
            Connection connection =  DriverManager.getConnection("jdbc:mysql://bookbetter-aws.czoua2woyqte.us-east-2.rds.amazonaws.com:3306/user", "admin", "!!mqsqlhubbard2024");
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("INSERT INTO users (username, password) VALUES ('" + username + "', '" + password + "')");
            System.out.println(results);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
