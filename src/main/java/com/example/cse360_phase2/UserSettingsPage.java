package com.example.cse360_phase2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Map;

public class UserSettingsPage {
    private User user;
    private SceneController sceneController;

    public UserSettingsPage(User user, SceneController sceneController) {
        this.user = user;
        this.sceneController = sceneController;
    }

    public Scene getScene() {
        Scene mainScene = sceneController.getCurrentScene();
        sceneController.getStage().setTitle("BookBetter - Seller Dashboard");
        AnchorPane root = new AnchorPane();

//        HBox pieChartBox = categoriesPieChart(user);
//        HBox conditionsPieChart = conditionsPieChart(user);
//        AnchorPane.setBottomAnchor(pieChartBox, 0.0);
//        AnchorPane.setBottomAnchor(conditionsPieChart, mainScene.getHeight() / 2.0);
//        root.getChildren().addAll(pieChartBox, conditionsPieChart);
        VBox sidePanel = sidePanel(user);
        AnchorPane.setLeftAnchor(sidePanel, 0.0);
        AnchorPane.setTopAnchor(sidePanel, 0.0);
        AnchorPane.setBottomAnchor(sidePanel, 0.0);
        AnchorPane contentPane = contentPane(user);
        AnchorPane.setTopAnchor(contentPane, 0.0);
        AnchorPane.setLeftAnchor(contentPane, 200.0);
        AnchorPane.setBottomAnchor(contentPane, 0.0);
        root.getChildren().addAll(sidePanel, contentPane);
        Scene scene = new Scene(root, mainScene.getWidth(), mainScene.getHeight());

        return scene;
    }
    public VBox sidePanel(User user) {
        VBox sidePanel = new VBox();
        Image BookBetterCard = new Image("C:\\Users\\theba\\IdeaProjects\\CSE360_PROJECT1\\src\\main\\resources\\com\\example\\cse360_project1\\images\\BookBetterCard.png", 155.0, 30.0, true, true);
        ImageView bookBetterImageView = new ImageView(BookBetterCard);
        bookBetterImageView.setFitWidth(155.0);
        bookBetterImageView.setFitHeight(30.0);

        sidePanel.setAlignment(Pos.TOP_CENTER);
        sidePanel.setPrefWidth(200);
        sidePanel.setSpacing(20);
        sidePanel.setPadding(new Insets(20, 20, 20, 0));
        sidePanel.setPrefHeight(1280);
        sidePanel.getStyleClass().add("gray-sidebar");

        VBox generalArea = new VBox(10);
        generalArea.setPadding(new Insets(20, 20, 20, 20));
        Label generelLabel = new Label("General".toUpperCase());

        Button dashboard = new Button("Dashboard");
        dashboard.getStyleClass().add("sidepanel-button");
        dashboard.getStyleClass().add("selected");
        Button list = new Button("List");
        list.getStyleClass().add("sidepanel-button");
        Button transactions = new Button("Transactions");

        transactions.getStyleClass().add("sidepanel-button");
        generalArea.getChildren().addAll(generelLabel, dashboard, list, transactions);

        VBox supportArea = new VBox(10);
        supportArea.setPadding(new Insets(20, 20, 20, 20));
        Label supportLabel = new Label("Support".toUpperCase());

        Button settingsButton = new Button("Settings");
        settingsButton.getStyleClass().add("sidepanel-button");

        Button supportButton = new Button("Support");
        supportButton.getStyleClass().add("sidepanel-button");
        supportArea.getChildren().addAll(generalArea, supportLabel, supportButton, settingsButton);


        VBox userArea = new VBox(0);
        userArea.setPadding(new Insets(sceneController.getCurrentScene().getHeight() / 3.5, 20, 20, 20));
        Label userLabel = new Label(user.getName());

        userLabel.getStyleClass().add("focus");

        Label typeLabel = new Label(user.getUserType().toUpperCase());
        typeLabel.getStyleClass().add("type-label");
        userArea.getChildren().addAll(userLabel, typeLabel);
        userArea.setAlignment(Pos.BOTTOM_CENTER);

        sidePanel.getChildren().addAll(bookBetterImageView, generalArea, supportArea, userArea);
        String css = getClass().getResource("/com/example/cse360_project1/css/UserSettings.css").toExternalForm();
        sidePanel.getStylesheets().add(css);
        return sidePanel;
    }

    public AnchorPane contentPane(User user) {
        AnchorPane pane = new AnchorPane();
        Label accountSettingsLabel = new Label("Account Settings");
        accountSettingsLabel.getStyleClass().add("h1");
        accountSettingsLabel.setPadding(new Insets(20, 20, 20, 20));
        VBox personalBlurb = new VBox(0);
        personalBlurb.getStyleClass().add("blurb");
        personalBlurb.setPadding(new Insets(20, 20, 20, 20));

        HBox header = new HBox(20);
        Label blurbLabel = new Label("Personal information");
        blurbLabel.getStyleClass().add("h2");
        Button editButton = new Button("Edit");
        editButton.getStyleClass().add("secondary");
        editButton.getStyleClass().add("type-label");
        header.getChildren().addAll(blurbLabel, editButton);

        Label userLabel = new Label("User ID");
        userLabel.getStyleClass().add("blurb-text");
        userLabel.getStyleClass().add("gray");

        Label userIDLabel = new Label(user.getName());
        userIDLabel.getStyleClass().add("blurb-text");
        Label userTypeLabel = new Label("Type");
        userTypeLabel.getStyleClass().add("blurb-text");
        userTypeLabel.getStyleClass().add("gray");

        Label typeLabel = new Label(user.getUserType().toUpperCase());
        typeLabel.getStyleClass().add("blurb-text");

        personalBlurb.getChildren().addAll(header, userLabel, userIDLabel, userTypeLabel, typeLabel);

        VBox actionBlurb = new VBox(0);
        actionBlurb.setPadding(new Insets(20, 20, 20, 20));
        actionBlurb.getStyleClass().add("blurb");
        Label actionBlurbLabel = new Label("Action Center");
        actionBlurbLabel.getStyleClass().add("h2");

        Label changePasswordLabel = new Label("Change password");
        changePasswordLabel.getStyleClass().add("blurb-text");
        changePasswordLabel.getStyleClass().add("gray");

        Button changePasswordButton = new Button("Change Password");
        changePasswordButton.getStyleClass().add("primary");

        Label deleteAccountLabel = new Label("Change password");
        changePasswordLabel.getStyleClass().add("blurb-text");
        changePasswordLabel.getStyleClass().add("gray");

        Button deleteAccountButton = new Button("Delete Account");
        changePasswordButton.getStyleClass().add("danger");

        actionBlurb.getChildren().addAll(actionBlurbLabel, changePasswordLabel, changePasswordButton, deleteAccountLabel, deleteAccountButton);
        pane.getChildren().addAll(accountSettingsLabel, personalBlurb, actionBlurb);
        String css = getClass().getResource("/com/example/cse360_project1/css/UserSettings.css").toExternalForm();
        AnchorPane.setTopAnchor(personalBlurb, 110.0);
        AnchorPane.setTopAnchor(accountSettingsLabel, 30.0);
        AnchorPane.setTopAnchor(actionBlurb, 405.0);

        AnchorPane.setLeftAnchor(actionBlurb, 50.0);
        AnchorPane.setLeftAnchor(accountSettingsLabel, 50.0);
        AnchorPane.setLeftAnchor(personalBlurb, 50.0);
        pane.getStylesheets().add(css);
        return pane;
    }
}

