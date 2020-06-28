package com.bitbucket.util.screen;

import com.bitbucket.util.helper.BitBucketCollaborator;
import com.bitbucket.util.helper.ProjectSelector;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Ram Mishra
 */
public class BitBucketUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(BitBucketUI.class);

    private UserInformation bitBucketUserInfo;

    private BitBucketCollaborator bucketCollaborator;

    private List<String> projects;

    private TableView tableView;

    public void setBitBucketUserInfo(UserInformation bitBucketUserInfo) {
        this.bitBucketUserInfo = bitBucketUserInfo;
    }

    public UserInformation getBitBucketUserInfo() {
        return bitBucketUserInfo;
    }

    public void build(Stage stage) {
        stage.setTitle("BitBucket Pull-request App");

        projects = new ArrayList<>();
        bucketCollaborator = new BitBucketCollaborator();

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(addHeader());
        borderPane.setCenter(addCenterPane());
        borderPane.setBottom(addBottomPane());

        Scene scene = new Scene(borderPane, 700, 500);
        stage.setScene(scene);
        stage.show();
    }

    private HBox addHeader() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(15, 20, 10, 10));
        hBox.setSpacing(20);
        hBox.setStyle("-fx-background-color: #336699;");

        Label label = new Label("\t" + "BitBucket Pull-request App");
        label.setAlignment(Pos.BASELINE_CENTER);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        label.setMinWidth(350);
        label.setPrefSize(500, 10);

        hBox.getChildren().addAll(new ImageView(new Image("file:pull-request.png")), label);
        return hBox;
    }

    private VBox addCenterPane() {
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(05, 10, 05, 05));
        vBox.setSpacing(15);

        Text title = new Text("PROJECTS:");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        title.setTextAlignment(TextAlignment.CENTER);

        tableView = new TableView<>();
        tableView.setMaxHeight(250);
        tableView.setEditable(true);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        TableColumn<String, ProjectSelector> projectColumn = new TableColumn<>("Project");
        projectColumn.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        projectColumn.setPrefWidth(500);

        TableColumn<CheckBox, ProjectSelector> checkBoxColumn = new TableColumn<>(new CheckBox() + "");
        checkBoxColumn.setCellValueFactory(new PropertyValueFactory<>("choose"));

        tableView.getColumns().addAll(projectColumn, checkBoxColumn);

        HBox fetchProjectBox = bucketCollaborator.getProjects(tableView);

        vBox.getChildren().addAll(fetchProjectBox, title, tableView);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.vvalueProperty().bind(vBox.heightProperty());

        return vBox;
    }

    private VBox addBottomPane() {
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);

        Text title = new Text("Create a pull request:");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        title.setTextAlignment(TextAlignment.CENTER);

        HBox brancHBox = drawPullRequestContainer();
        HBox reviewerHBox = drawCodeReviewersContainer();

        Button createPullReqBtn = new Button("Create pull request");

        createPullReqBtn.setOnAction((var actionEvent) -> {
            bucketCollaborator.assembleSelectedProject();
        });

        vBox.getChildren().addAll(title, brancHBox, reviewerHBox, createPullReqBtn);

        return vBox;
    }

    private HBox drawPullRequestContainer() {
        HBox brancHBox = new HBox();
        brancHBox.setSpacing(10);
        brancHBox.setAlignment(Pos.CENTER);

        // From branch field
        HBox fromBranchHBox = new HBox();
        fromBranchHBox.setSpacing(10);

        Label fromBranchLabel = new Label("From: ");
        TextField fromBranchField = new TextField();

        fromBranchHBox.getChildren().addAll(fromBranchLabel, fromBranchField);

        // To branch field
        HBox toBranchHBox = new HBox();
        toBranchHBox.setSpacing(10);

        Label toBranchLabel = new Label("To: ");
        TextField toBranchField = new TextField();

        toBranchHBox.getChildren().addAll(toBranchLabel, toBranchField);

        brancHBox.getChildren().addAll(fromBranchHBox, toBranchHBox);

        return brancHBox;
    }

    private HBox drawCodeReviewersContainer() {
        HBox reviewerHBox = new HBox();
        reviewerHBox.setSpacing(10);
        reviewerHBox.setAlignment(Pos.CENTER);

        Label reviewLabel = new Label("Reviewers: ");
        TextField reviewField = new TextField();

        reviewerHBox.getChildren().addAll(reviewLabel, reviewField);

        return reviewerHBox;
    }

}
