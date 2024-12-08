package application.Interface;

import application.AlarmClock;
import application.Event;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tracking {

    @FXML
    private ListView<Event> allEvent;

    @FXML
    private ListView<Event> currentEvent;

    @FXML
    private Label currentTime;

    @FXML
    private Button end;

    @FXML
    private Label nameAllEvent;

    @FXML
    private Label nameCurrentEvent;

    private Timeline timeline;

    private final AlarmClock alarmClock = new AlarmClock();

    @FXML
    void initialize() {
        currentTime.setAlignment(javafx.geometry.Pos.CENTER);
        nameAllEvent.setAlignment(javafx.geometry.Pos.CENTER);
        nameCurrentEvent.setAlignment(javafx.geometry.Pos.CENTER);
        end.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Завершение работы");
            alert.setHeaderText("Программа была завершена");
            alert.showAndWait();
            Platform.exit();
            System.exit(0);
        });

        alarmClock.loadEventTypes("D:\\Project\\Coursework_OOP\\src\\main\\resources\\events.txt");
        alarmClock.generateEvents(20);

        allEvent.getItems().addAll(alarmClock.getAllEvents());

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

    private void updateActiveEvents() {
        List<Event> activeEvents = new ArrayList<>();
        List<Event> allEvents = alarmClock.getAllEvents();

        LocalTime current = LocalTime.parse(getCurrentTime());

        for (Event event : allEvents) {
            if (event.isActive(current)) {
                activeEvents.add(event);
            } else if (event.isCompleted(current) && !event.isCompleted()) {

                event.setName(event.getName() + " не актуально.");
                event.markAsCompleted();
            }
        }

        currentEvent.getItems().setAll(activeEvents);

        allEvent.getItems().setAll(allEvents);
    }

    private void updateTime() {
        String currentTimeText = getCurrentTime();
        currentTime.setText(currentTimeText);
        updateActiveEvents();
    }

}

