package com.bitbucket.util.screen;

import com.bitbucket.util.helper.BitBucketCollaborator;
import com.bitbucket.util.helper.ProjectSelector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.control.cell.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 *
 * @author Ram Mishra
 */
public class BitBucketUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(BitBucketUI.class);

    private UserInformation bitBucketUserInfo;

    private BitBucketCollaborator bucketCollaborator;

    private TableView tableView;

    private String userName;

    private String userPwd;

    private TextField fromBranchField;

    private TextField toBranchField;

    private TextField reviewerField;

    public void setBitBucketUserInfo(UserInformation bitBucketUserInfo) {
        this.bitBucketUserInfo = bitBucketUserInfo;
    }

    public UserInformation getBitBucketUserInfo() {
        return bitBucketUserInfo;
    }

    public void build(Stage bitButcketScreenStage, String usrName, String usrPwd) {
        bitButcketScreenStage.setTitle("BitBucket Pull-request App");

        bucketCollaborator = new BitBucketCollaborator();

        userName = usrName;
        userPwd = usrPwd;

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(addHeader());
        borderPane.setCenter(addCenterPane());
        borderPane.setBottom(addBottomPane());

        bitButcketScreenStage.getIcons().setAll(new Image("/atlassian-bitbucket-logo.png"));

        Scene scene = new Scene(borderPane, 700, 500);
        bitButcketScreenStage.setScene(scene);
        bitButcketScreenStage.show();
    }

    private HBox addHeader() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(15, 20, 10, 10));
        hBox.setSpacing(20);
        hBox.setAlignment(Pos.CENTER);
        hBox.setStyle("-fx-background-color: #336699;");

        Label label = new Label("\t" + "BitBucket Pull-request");
        label.setAlignment(Pos.BASELINE_CENTER);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        label.setTextFill(Color.WHITESMOKE);

        ImageView imageView = new ImageView("/pull-request.png");
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);

        hBox.getChildren().addAll(imageView, label);
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

        TableColumn checkBoxColumn = new TableColumn<>();
        checkBoxColumn.setCellFactory(CheckBoxTableCell.forTableColumn(checkBoxColumn));

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

        Button createPullReqBtn = new Button("Request!!");

        createPullReqBtn.setOnAction((var actionEvent) -> {
            if (!toBranchField.getText().isEmpty() || !fromBranchField.getText().isEmpty() || !reviewerField.getText().isEmpty()) {
                bucketCollaborator.assembleSelectedProject(tableView,
                        new PullRequestInformation(userName, userPwd, toBranchField.getText(),
                                fromBranchField.getText(), reviewerField.getText()));
            }
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
        fromBranchField = new TextField();

        fromBranchHBox.getChildren().addAll(fromBranchLabel, fromBranchField);

        // To branch field
        HBox toBranchHBox = new HBox();
        toBranchHBox.setSpacing(10);

        Label toBranchLabel = new Label("To: ");
        toBranchField = new TextField();

        toBranchHBox.getChildren().addAll(toBranchLabel, toBranchField);

        brancHBox.getChildren().addAll(fromBranchHBox, toBranchHBox);

        return brancHBox;
    }

    private HBox drawCodeReviewersContainer() {
        HBox reviewerHBox = new HBox();
        reviewerHBox.setSpacing(10);
        reviewerHBox.setAlignment(Pos.CENTER);

        Label reviewLabel = new Label("Reviewers: ");
        reviewerField = new TextField();

        reviewerHBox.getChildren().addAll(reviewLabel, reviewerField);

        return reviewerHBox;
    }

}
