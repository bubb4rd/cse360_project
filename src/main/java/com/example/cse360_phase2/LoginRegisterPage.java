package com.example.cse360_phase2;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.ResultSet;

public class LoginRegisterPage {
    private SceneController sceneController;
    public LoginRegisterPage(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    public Scene getScene(Scene mainScene) {
        AnchorPane root = new AnchorPane();


        VBox mainBox = new VBox();
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setSpacing(100);
        // Login section
        HBox boxes = new HBox();
        VBox loginBox = new VBox(20);
        Label loginLabel = new Label("Login");

        TextField loginUsername = new TextField();
        loginUsername.setPromptText("User ID");

        PasswordField loginPassword = new PasswordField();
        loginPassword.setPromptText("Password");

        Button loginButton = new Button("Login");

        loginBox.setAlignment(Pos.TOP_CENTER);
        loginBox.getChildren().addAll(loginLabel, loginUsername, loginPassword, loginButton);

        // Register section
        VBox registerBox = new VBox(20);
        Label registerLabel = new Label("Register");
        TextField registerUsername = new TextField();
        registerUsername.setPromptText("User ID");

        PasswordField registerPassword = new PasswordField();
        registerPassword.setPromptText("Password");
        registerPassword.getStyleClass().add("password");

        PasswordField registerConfirmPassword = new PasswordField();
        registerConfirmPassword.setPromptText("Confirm Password");
        registerConfirmPassword.getStyleClass().add("password");

        ComboBox<String> registerType = new ComboBox();
        registerType.setValue("Choose Account Type");
        registerType.getItems().addAll("Buyer", "Seller");
        Button registerButton = new Button("Create Account");
        registerBox.setAlignment(Pos.CENTER);
        registerBox.getChildren().addAll(registerLabel, registerUsername, registerPassword, registerConfirmPassword, registerType, registerButton);

        boxes.getChildren().addAll(loginBox, registerBox);
        boxes.setSpacing(100);
        boxes.setAlignment(Pos.CENTER);
        mainBox.getChildren().addAll(boxes);
        root.getChildren().add(mainBox);
        AnchorPane.setTopAnchor(mainBox, mainScene.getHeight() / 4.0);
        AnchorPane.setLeftAnchor(mainBox, mainScene.getWidth() / 3.5);

        loginButton.setOnAction(event -> {
            String userID = loginUsername.getText();
            String password = loginPassword.getText();
            if (userID.isEmpty() || password.isEmpty()) {
                Error authError = new Error("Authentication failed: Incorrect ID or Password");
                authError.displayError(root, mainScene);
            }
            JDBCConnection connection = new JDBCConnection();
            ResultSet results = connection.logIn(loginUsername.getText(), loginPassword.getText());

            if (results == null) {
                Error authError = new Error("Authentication failed: Incorrect ID or Password");
                authError.displayError(root, mainScene);
            }
        });

        registerButton.setOnAction(event -> {
            String userID = registerUsername.getText();
            String password = registerPassword.getText();
            String confirmPassword = registerConfirmPassword.getText();
            String accountType = registerType.getSelectionModel().getSelectedItem();
            // Error handling
            if (userID.isEmpty() || password.isEmpty()) {
                Error registerError = new Error("Registration failed: Empty required field");
                registerError.displayError(root, mainScene);
            }

            else if (accountType.equals("Choose Account Type")) {
                Error accountTypeError = new Error("Registration failed: Account type invalid");
                accountTypeError.displayError(root, mainScene);
            }

            else if (confirmPassword.isEmpty()) {
                Error registerError = new Error("Registration failed: Confirm password not matching");
                registerError.displayError(root, mainScene);
            }

            if (password.equals(confirmPassword)) {
                JDBCConnection connection = new JDBCConnection();
                ResultSet results = connection.registerUser(userID, password);
            }
        });
        Scene loginRegisterScene = new Scene(root, mainScene.getWidth(), mainScene.getHeight());
        String css = getClass().getResource("/com/example/cse360_phase2/css/LoginRegister.css").toExternalForm();
        loginRegisterScene.getStylesheets().add(css);
        return loginRegisterScene;
    }
}
