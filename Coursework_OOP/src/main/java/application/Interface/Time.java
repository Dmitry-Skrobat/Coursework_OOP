package application.Interface;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Time {

    @FXML
    private Label currentTime;


    @FXML
    private Button powerButton;

    @FXML
    private Label title;

    private Timeline timeline;

    @FXML
    void initialize() {
        powerButton.setOnAction(actionEvent -> openWindow());
        currentTime.setAlignment(javafx.geometry.Pos.CENTER);
        startClock();
    }

    private String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date now = new Date();
        return sdf.format(now);
    }

    private void startClock() {
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), e -> updateTime()),
                new KeyFrame(Duration.seconds(1), e -> updateTime())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateTime() {
        String currentTimeText = getCurrentTime();
        currentTime.setText(currentTimeText);
    }

    private void openWindow(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/tracking.fxml"));

            Scene scene = new Scene(loader.load(),625,570);

            Stage stage = (Stage) powerButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


