package com.StaffSubstitution.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ChoseAbsentStaff extends Application {
  private ComboBox<String> staffComboBox;
  private TextArea selectedStaffTextArea;
  // private final StaffSelectionController controller;

  // public ChoseAbsentStaff() {
  // controller = new StaffSelectionController(this); // Pass the view instance to
  // the controller
  // }
  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Staff Substitution Tool");

    Label selectLabel = new Label("Select Absent Staff:");
    staffComboBox = new ComboBox<>();

    Button addButton = new Button("Add Staff");
    addButton.setOnAction(event -> controller.addStaff());

    Label selectedLabel = new Label("Selected Absent Staff:");
    selectedStaffTextArea = new TextArea();
    selectedStaffTextArea.setEditable(false);

    GridPane gridPane = new GridPane();
    gridPane.setHgap(10);
    gridPane.setVgap(10);

    gridPane.add(selectLabel, 0, 0);
    gridPane.add(staffComboBox, 1, 0);
    gridPane.add(addButton, 2, 0);
    gridPane.add(selectedLabel, 0, 1, 2, 1);
    gridPane.add(selectedStaffTextArea, 0, 2, 3, 1);

    Scene scene = new Scene(gridPane, 400, 300);
    primaryStage.setScene(scene);
    primaryStage.show();

  }

  public ComboBox<String> getStaffComboBox() {
    return staffComboBox;
  }

  public TextArea getSelectedStaffTextArea() {
    return selectedStaffTextArea;
  }

}