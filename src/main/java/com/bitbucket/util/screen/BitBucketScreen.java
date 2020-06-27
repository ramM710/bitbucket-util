package com.bitbucket.util.screen;

import java.io.File;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ram Mishra
 */
public class BitBucketScreen extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(BitBucketScreen.class);

    private Scene scene;

    private TextField userName;

    private List<String> projects;

    @Override
    public void start(Stage stage) throws Exception {
        //Title of the App
        stage.setTitle("BitBucket Pull-request App");

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(addHeader());
        borderPane.setCenter(addCenterPane());
        borderPane.setBottom(addBottomPane());

        projects = new ArrayList<>();

        scene = new Scene(borderPane, 1200, 700);
        stage.setScene(scene);
        stage.show();
    }

    public HBox addHeader() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(15, 20, 10, 10));
        hBox.setSpacing(20);
        hBox.setStyle("-fx-background-color: #336699;");

        Label label = new Label("\t" + "BitBucket Pull-request App");
        label.setAlignment(Pos.BASELINE_CENTER);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        label.setMinWidth(350);
        label.setPrefSize(500, 10);

        VBox userDetailVBox = new VBox();
        userDetailVBox.setSpacing(5);

        HBox userNameHBox = new HBox();
        userNameHBox.setSpacing(5);

        Label userNameLabel = new Label("\t" + "IP: " + userName.getText());
        userNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        userNameLabel.setTextFill(Color.web("#FFB22B"));

        TextField userNameField = new TextField();

        userNameHBox.getChildren().addAll(userNameLabel, userNameField);

        HBox userPwdHBox = new HBox();
        userPwdHBox.setSpacing(5);

        Label userPwdLabel = new Label("\t" + "IP: " + userName.getText());
        userPwdLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        userPwdLabel.setTextFill(Color.web("#FFB22B"));

        TextField userPwdField = new TextField();

        userPwdHBox.getChildren().addAll(userPwdLabel, userPwdField);

        userDetailVBox.getChildren().addAll(userNameHBox, userPwdHBox);

//        hBox.getChildren().addAll(new ImageView(new Image("/logo.png")), label, userDetailVBox);
        hBox.getChildren().addAll(label, userDetailVBox);
        return hBox;
    }

    public HBox addCenterPane() {
        HBox directoryHBox = new HBox();
        directoryHBox.setSpacing(5);

        Label directoryLabel = new Label("Enter the directory home path:");
        directoryLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        directoryLabel.setTextFill(Color.web("#FFB22B"));

        final TextField directoryField = new TextField();

        Button fetchButton = new Button("Fetch!!");

        fetchButton.setOnAction((ActionEvent event) -> {
            File file = new File(directoryField.getText());

            String[] directories = file.list((File current, String name) -> new File(current, name).isDirectory());

            projects.addAll(Arrays.asList(directories));
        });

        directoryHBox.getChildren().addAll(directoryLabel, directoryField, fetchButton);

        return directoryHBox;
    }

    public VBox addBottomPane() {
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(05, 10, 05, 05));
        vBox.setSpacing(15);

        Text title = new Text("PROJECTS:");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        title.setTextAlignment(TextAlignment.CENTER);

        TableView tableView = new TableView<>();
        tableView.setMaxHeight(250);
        tableView.setEditable(true);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        populateColumns(tableView);

        vBox.getChildren().addAll(title, tableView);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.vvalueProperty().bind(vBox.heightProperty());

        return vBox;
    }

    private void populateColumns(TableView tableView) {

        TableColumn<String, ProjectSelector> projectColumn = new TableColumn<>("Project");
        projectColumn.setCellValueFactory(new PropertyValueFactory<>("projectName"));

        TableColumn<CheckBox, ProjectSelector> checkBoxColumn = new TableColumn<>();
        checkBoxColumn.setCellValueFactory(new PropertyValueFactory<>("choose"));

        tableView.getColumns().addAll(projectColumn, checkBoxColumn);

        projects.forEach((project) -> {
            tableView.getItems().add(new ProjectSelector(project, new CheckBox()));
        });
    }

    class ProjectSelector {

        private final String projectName;

        private final CheckBox choose;

        public ProjectSelector(String projectName, CheckBox choose) {
            this.projectName = projectName;
            this.choose = choose;
        }

    }
}
