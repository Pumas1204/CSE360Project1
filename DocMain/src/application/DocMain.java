package application;

import javafx.scene.control.Label;

import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicReference;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.control.TextField;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;




public class DocMain extends Application {

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void start(Stage primaryStage) {
        Label title = new Label("Doctor Main Screen");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        
        

        Label docName = new Label("Doctor:");
        Label search = new Label("Patient Search");

        HBox titleHBox = new HBox(10);
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.getChildren().addAll(title);

        HBox docNameHBox = new HBox(10);
        docNameHBox.setAlignment(Pos.CENTER); 
        docNameHBox.getChildren().addAll(docName);

        HBox searchHBox = new HBox(10);
        searchHBox.setAlignment(Pos.CENTER); 
        searchHBox.getChildren().addAll(search);

        TextField firstName = new TextField();
        firstName.setPromptText("First Name");
        TextField lastName = new TextField();
        lastName.setPromptText("Last Name");
        TextField birthDateField = new TextField();
        birthDateField.setPromptText("YYYY-MM-DD");

        // Add a ChangeListener to validate the birth date input
        birthDateField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!isValidDate(newValue)) {
                birthDateField.setStyle("-fx-border-color: red;");
            } else {
                birthDateField.setStyle("");
            }
        });

        Button searchButton = new Button("Submit");
        searchButton.setOnAction(e -> {
            String birthDateText = birthDateField.getText();
            if (isValidDate(birthDateText)) {
                LocalDate birthDate = LocalDate.parse(birthDateText, dateFormatter);
                System.out.println("Valid birth date entered: " + birthDate);
            } else {
                showAlert("Invalid Date", "Please enter a valid date in the format YYYY-MM-DD.");
            }
        });
        
        HBox inputHBox = new HBox(10, firstName, lastName, birthDateField);
        inputHBox.setAlignment(Pos.CENTER);

        VBox mainContent = new VBox(10, titleHBox, docNameHBox, searchHBox, inputHBox, searchButton);
        mainContent.setAlignment(Pos.CENTER); // Center vertically and horizontally
        mainContent.setPadding(new javafx.geometry.Insets(10));
        
        
        Button topRightButton = new Button("Top Right");
        HBox topRightHBox = new HBox(topRightButton);
        topRightHBox.setAlignment(Pos.TOP_RIGHT);
        topRightHBox.setPadding(new Insets(70)); 
        
        Button logout= new Button("Top Right");
        HBox LogHBox = new HBox(logout);
        topRightHBox.setAlignment(Pos.TOP_RIGHT);
        topRightHBox.setPrefWidth(Double.MAX_VALUE); // Make HBox take full width

        // Create an outer VBox to hold the top HBox and main content VBox
        VBox outerVBox = new VBox(topRightHBox, mainContent);
        VBox.setVgrow(mainContent, javafx.scene.layout.Priority.ALWAYS); // Make the main content take up available space
        outerVBox.setPrefWidth(400); 
        
        

        Scene scene = new Scene(outerVBox, 600, 400); // Increased width to accommodate horizontal layout
        primaryStage.setTitle("Doctor Main Screen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr, dateFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}