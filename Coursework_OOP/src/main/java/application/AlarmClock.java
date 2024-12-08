package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AlarmClock {
    private List<EventType> eventTypes = new ArrayList<>();
    private List<Event> events = new ArrayList<>();

    public void loadEventTypes(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                int duration = Integer.parseInt(parts[1]);
                eventTypes.add(new EventType(name, duration));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateEvents(int count) {
        Random random = new Random();
        LocalTime previousEndTime = LocalTime.now();

        for (int i = 0; i < count; i++) {
            boolean isFirstEvent = (i == 0);
            EventType randomType = eventTypes.get(random.nextInt(eventTypes.size()));
            Event event = randomType.generateEvent(previousEndTime, isFirstEvent);
            events.add(event);
            previousEndTime = event.getEndTime();
        }
    }


    public List<Event> getAllEvents() {
        return events;
    }
}
