package omahelp;

import java.util.Arrays;

public class Event {

    private int id;
    private String name;
    private String description;
    private String location;
    private String area;
    private String date;
    private String[] attendees;
    private String[] organizers;
    private String[] tags;

    public Event(String name, String description, String date, String location, String area, String[] tags) {
        super();
        this.name = name;
        this.description = description;
        this.date = date;
        this.location = location;
        this.area = area;
        this.tags = Arrays.copyOf(tags, tags.length);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String[] getAttendees() {
        return Arrays.copyOf(attendees, attendees.length);
    }

    public void setAttendees(String[] attendees) {
        this.attendees = Arrays.copyOf(attendees, attendees.length);
    }

    public String[] getOrganizers() {
        return Arrays.copyOf(organizers, organizers.length);
    }

    public void setOrganizers(String[] organizers) {
        this.organizers = Arrays.copyOf(organizers, organizers.length);
    }

    public String[] getTags() {
        return Arrays.copyOf(tags, tags.length);
    }

    public void setTags(String[] tags) {
        this.tags = Arrays.copyOf(tags, tags.length);
    }
}