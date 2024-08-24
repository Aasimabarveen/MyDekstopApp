package com.StaffSubstitution.app;

//https://allaroundjava.com/setting-up-java-application-with-hibernate-example/
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.*;
import com.StaffSubstitution.Service.StaffService;
import com.StaffSubstitution.Service.TimeTableService;
import com.StaffSubstitution.Service.loadExcelData;
import com.StaffSubstitution.util.*;
import org.hibernate.SessionFactory;
import java.util.List;
import com.StaffSubstitution.Model.*;
import java.util.concurrent.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
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
        System.out.println("Hi");
    }

}
