package com.bitbucket.util.helper;

import com.bitbucket.util.automate.test.helper.CommonFunctions;
import com.bitbucket.util.screen.PullRequestInformation;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 *
 * @author Ram Mishra
 */
public class BitBucketCollaborator {

    public TableView insertDataInTable(TableView tableView, List<String> projects) {

        if (projects != null) {
            projects.forEach((project) -> {
                tableView.getItems().add(new ProjectSelector(project, new CheckBox()));
            });
        }

        return tableView;
    }

    public HBox getProjects(final TableView tableView) {
        HBox directoryHBox = new HBox();
        directoryHBox.setSpacing(5);

        Label directoryLabel = new Label("Select the project directory:");
        directoryLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        directoryLabel.setTextFill(Color.web("#FFB22B"));

        final TextField directoryField = new TextField();

        Button fetchButton = new Button("Fetch!!");

        fetchButton.setOnAction((var event) -> {
            tableView.getItems().clear();

            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDir = directoryChooser.showDialog(new Stage());

            if (selectedDir != null) {
                String[] directories = selectedDir.list((File current, String name) -> new File(current, name).isDirectory());

                insertDataInTable(tableView, Arrays.asList(directories));
            }
        });

        directoryHBox.getChildren().addAll(directoryLabel, directoryField, fetchButton);

        return directoryHBox;
    }

    public void assembleSelectedProject(TableView tableView, PullRequestInformation requestInformation) {
        ObservableList<ProjectSelector> data = tableView.getItems();

        List<String> projects = new ArrayList<>();
        data.forEach((var project) -> {
            if (project.getCheckBox().isSelected()) {
                projects.add(project.getProjectName());
            }
        });

        // sending data to selenium
        CommonFunctions commonFunctions = new CommonFunctions();
        commonFunctions.generatePullRequest(projects, requestInformation);
    }

}
