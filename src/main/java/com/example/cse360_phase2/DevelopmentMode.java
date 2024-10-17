package com.example.cse360_phase2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DevelopmentMode extends Application {
    static SceneController sceneController;
    static Scene userInfoScene;
    @Override
    public void start(Stage primaryStage) {
        VBox mainLayout = new VBox();
        Scene mainScene = new Scene(mainLayout, 1280, 830);
//        LoginRegisterPage loginRegisterPage = new LoginRegisterPage(sceneController);
//        Scene loginRegisterScene = loginRegisterPage.getScene(mainScene);
        sceneController = new SceneController(primaryStage);
        sceneController.setCurrentScene(mainScene);
        JDBCConnection connection = new JDBCConnection();
        User user = connection.logInReturnUser("username", "password");
        UserSettingsPage userSettingsPage = new UserSettingsPage(user, sceneController);
        primaryStage.setScene(userSettingsPage.getScene());
        primaryStage.show();



    }
    public static void main(String[] args) {
        launch(args);
    }
}
