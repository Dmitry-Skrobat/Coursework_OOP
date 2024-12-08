package application;


import java.time.LocalTime;
import java.util.Random;

public class EventType {
    private String name;
    private int duration;

    public EventType(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public Event generateEvent(LocalTime previousEndTime, boolean isFirstEvent) {
        Random random = new Random();
        int interval;

        if (isFirstEvent) {
            interval = 0;
        } else {
            interval = 2 + random.nextInt(4);
        }
        LocalTime startTime = previousEndTime.plusSeconds(interval);
        LocalTime endTime = startTime.plusSeconds(duration);
        return new Event(name, startTime, endTime);
    }


    @Override
    public String toString() {
        return "Тип события: " + name + ", Длительность: " + duration + " сек";
    }
}
