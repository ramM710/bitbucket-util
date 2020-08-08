package com.bitbucket.util.screen;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author Ram Mishra
 */
public class UserInformation extends Application {

    public void build(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage userStage) {

        VBox userDetailVBox = new VBox();
        userDetailVBox.setSpacing(10);
        userDetailVBox.setAlignment(Pos.CENTER);

        HBox userNameHBox = new HBox();
        userNameHBox.setSpacing(5);
        userNameHBox.setAlignment(Pos.CENTER);

        Label userNameLabel = new Label("\t" + "User Name: ");
        userNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        userNameLabel.setTextFill(Color.web("#FFB22B"));

        TextField userNameField = new TextField();

        userNameHBox.getChildren().addAll(userNameLabel, userNameField);

        HBox userPwdHBox = new HBox();
        userPwdHBox.setSpacing(5);
        userPwdHBox.setAlignment(Pos.CENTER);

        Label userPwdLabel = new Label("\t" + "Password: ");
        userPwdLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        userPwdLabel.setTextFill(Color.web("#FFB22B"));

        PasswordField userPwdField = new PasswordField();

        userPwdHBox.getChildren().addAll(userPwdLabel, userPwdField);

        Button submitBtn = new Button("Submit!!");
        submitBtn.setOnAction((var event) -> {

            BitBucketUI bitBucketUI = new BitBucketUI();
            bitBucketUI.setBitBucketUserInfo(this);
            bitBucketUI.build(new Stage(), userNameField.getText(), userPwdField.getText());

            userStage.hide();
        });

        ImageView imageView = new ImageView(new Image("/atlassian-bitbucket-logo.png"));

        userDetailVBox.getChildren().addAll(imageView, userNameHBox, userPwdHBox, submitBtn);

        userStage.getIcons().setAll(new Image("/atlassian-bitbucket-logo.png"));

        Scene scene = new Scene(userDetailVBox, 500, 400);
        userStage.setScene(scene);
        userStage.show();
    }

}
