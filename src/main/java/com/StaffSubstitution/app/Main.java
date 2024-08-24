package com.StaffSubstitution.app;


import java.awt.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.StaffSubstitution.view.*;


public class Main extends Application {

    public static void main(String[] args) {
          Application.launch(StaffSelectionView.class, args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Welcome Message
        Label welcomeLabel = new Label("Staff Substitution App worked great");

        // Root layout
        VBox root = new VBox();
        root.getChildren().add(welcomeLabel);

        // Scene setup
        Scene scene = new Scene(root, 300, 200);

        // Stage (window) setup
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
