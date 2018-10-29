package edu.uno.omahelp.event;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import edu.uno.omahelp.user.User;

/**
 * This class consists of all fields and methods necessary to represent
 * an event within the OmaHelp application. The fields of this class correspond
 * to the "Event" table within the OmaHelp database.
 */
@JsonInclude(Include.NON_EMPTY)
public class Event {

	private int id;
	private String name;
	private String description;
	private String location;
	private String date;

	private List<User> attendees;
	private List<User> organizers;
	private List<String> tags;

    /**
     * Returns the Event object's event identification number.
     *
     * @return The Event object's integer identification number.
     */    
	public int getId() {
		return id;
	}

    /**
     * Sets the Event object's event identification number.
     *
     * @param id The integer id value of an Event.
     */    
	public void setId(int id) {
		this.id = id;
	}

    /**
     * Returns the Event object's name.
     *
     * @return The Event object's name String.
     */    
	public String getName() {
		return name;
	}

    /**
     * Sets the Event object's name.
     *
     * @param name The name String of an Event.
     */
	public void setName(String name) {
		this.name = name;
	}

    /**
     * Returns the Event object's description.
     *
     * @return The Event object's description String.
     */
	public String getDescription() {
		return description;
	}

    /**
     * Sets the Event object's description.
     *
     * @param description The description String of an Event.
     */    
	public void setDescription(String description) {
		this.description = description;
	}

    /**
     * Returns the Event object's location.
     *
     * @return The Event object's location String.
     */    
	public String getLocation() {
		return location;
	}

    /**
     * Sets the Event object's location.
     *
     * @param location The location String of an Event.
     */
	public void setLocation(String location) {
		this.location = location;
	}

    /**
     * Returns the Event object's date.
     *
     * @return The Event object's date String.
     */    
	public String getDate() {
		return date;
	}

    /**
     * Sets the Event object's date.
     *
     * @param date The date String of an Event.
     */    
	public void setDate(String date) {
		this.date = date;
	}

    /**
     * Returns the list of attendees for the Event object.
     *
     * @return The list of User objects who are attending the Event object.
     */    
	public List<User> getAttendees() {
		return attendees;
	}

    /**
     * Sets the Event object's attendees list.
     *
     * @param attendees The list of User objects who are attending the Event object.
     */    
	public void setAttendees(List<User> attendees) {
		this.attendees = attendees;
	}

    /**
     * Returns the list of organizers for the Event object.
     *
     * @return The list of User objects who organized the Event object.
     */    
	public List<User> getOrganizers() {
		return organizers;
	}

    /**
     * Sets the Event object's organizers list.
     *
     * @param organizers The list of User objects who organized the Event object.
     */    
	public void setOrganizers(List<User> organizers) {
		this.organizers = organizers;
	}

    /**
     * Returns the list of tags associated with the Event object.
     *
     * @return The list of String tags that are associated with the Event object.
     */    
	public List<String> getTags() {
		return tags;
	}

    /**
     * Sets the Event object's tag list.
     *
     * @param tags The list of String tags that are associated with the Event object.
     */    
	public void setTags(List<String> tags) {
		this.tags = tags;
	}	
}