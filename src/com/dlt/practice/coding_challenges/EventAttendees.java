package com.dlt.practice.coding_challenges;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EventAttendees {
    private List<String> attendees;

    private Map<String, List<String>> attendeesMapping;

    public EventAttendees(List<String> attendees, Map<String, List<String>> attendeesMapping) {
        this.attendees = attendees;
        this.attendeesMapping = attendeesMapping;

    }

    public List<String> missingAttandees() {
        List<String> eventAttendees = attendeesMapping
                .values()
                .stream()
                .flatMap(current -> current.stream())
                .collect(Collectors.toList());

        List<String> eventAttendeesMissing = attendees
                .stream()
                .filter(attendee -> !eventAttendees.contains(attendee))
                .collect(Collectors.toList());

        return eventAttendeesMissing;
    }

    public List<String> incompleteAttandees() {
        Map<String, Integer> attendeeEventCount = attendees
                .stream()
                .collect(Collectors.toMap(attendee -> attendee, count -> 0));

        attendeesMapping
                .values()
                .forEach(eventAttendees -> eventAttendees.stream()
                        .filter(eventAttendee -> attendeeEventCount.containsKey(eventAttendee))
                        .forEach(eventAttendee -> attendeeEventCount.put(eventAttendee,
                                attendeeEventCount.get(eventAttendee) + 1)));

        return attendeeEventCount
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() < 2)
                .map(entry -> entry.getKey())
                .collect((Collectors.toList()));
    }

    public static void main(String[] args) {
        List<String> attendees = List.of("A", "B", "C", "D", "E", "F", "G");

        Map<String, List<String>> attendeesMapping = Map.of(
                "Event 1", List.of("A", "B", "C"),
                "Event 2", List.of("B", "C", "D"),
                "Event 3", List.of("C", "D", "E", "Z"));

        EventAttendees volunteers = new EventAttendees(attendees, attendeesMapping);
        System.out.println("Incomplete: " + volunteers.incompleteAttandees()); // Should be A E F G
        System.out.println("Missing: " + volunteers.missingAttandees()); // Should be F G

    }
}
