package application;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event {
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isCompleted;

    public Event(String name, LocalTime startTime, LocalTime endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public boolean isActive(LocalTime currentTime) {
        return !currentTime.isBefore(startTime) && !currentTime.isAfter(endTime);
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Событие: " + name + " [" + startTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + " - " + endTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "]";
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public boolean isCompleted(LocalTime currentTime) {
        return currentTime.isAfter(this.endTime); // Если текущее время после времени окончания события
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markAsCompleted() {
        isCompleted = true;
    }

    public String getName() {
        return name;
    }
}
