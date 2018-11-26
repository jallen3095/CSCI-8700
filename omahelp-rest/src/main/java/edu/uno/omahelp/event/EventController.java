package edu.uno.omahelp.event;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class provides the /event mappings for the REST API of OmaHelp.
 */
@CrossOrigin
@RestController
@RequestMapping("event")
public class EventController {

	@Autowired
	private EventDao eventDao;
    
    /**
     * Returns a list object that contains all the events in the database.
     * 
     * @return A List object that contains Event objects.
     * @throws URISyntaxException
     * @throws SQLException
     */
    @RequestMapping("list")
    public List<Event> listAllEvents() throws URISyntaxException, SQLException {
    	return eventDao.listAllEvents();
    }
    
    /**
     * Returns a list of events for a specified user.
     * 
     * @param userId The userId of the user to get events from.
     * @return A List object that contains Event objects.
     * @throws URISyntaxException
     * @throws SQLException
     */
    @RequestMapping("list/{userId}")
    public List<Event> listMyEvents(@PathVariable int userId) throws URISyntaxException, SQLException {
    	return eventDao.listMyEvents(userId);
    }
    
    /**
     * Creates a new event in the database.
     * 
     * @param event An Event object containing the data about the event.
     * @throws URISyntaxException
     * @throws SQLException
     */
    @RequestMapping(path="create", method=RequestMethod.POST)
    public void createEvent(@RequestBody Event event) throws URISyntaxException, SQLException {
    	eventDao.createEvent(event);
    }
    
    /**
     * Edits an existing event in the database.
     * 
     * @param event An Event object containing the new data to update with.
     * @throws URISyntaxException
     * @throws SQLException
     */
    @RequestMapping(path="edit", method=RequestMethod.PUT)
    public void editEvent(@RequestBody Event event) throws URISyntaxException, SQLException {
    	eventDao.editEvent(event);
	}
    
    /**
     * Deletes an event from the database.
     * 
     * @param eventId The eventId of the event to delete.
     * @throws URISyntaxException
     * @throws SQLException
     */
    @RequestMapping(path="delete", method=RequestMethod.DELETE)
    public void deleteEvent(@RequestParam int eventId) throws URISyntaxException, SQLException {
        eventDao.deleteEvent(eventId);
    }
    
    /**
     * Marks the specified user as attending a specified event.
     * 
     * @param userId The userId of the user that is attending.
     * @param eventId The eventId of the event that the user is not attending.
     * @throws SQLException
     * @throws URISyntaxException
     */
    @RequestMapping(method=RequestMethod.POST, path="attend")
    public void attendEvent(@RequestParam int userId, @RequestParam int eventId) throws SQLException, URISyntaxException {
    	eventDao.setEventAttending(userId, eventId, true);
    }
    
    /**
     * Marks the specified user as not attending a specified event.
     * 
     * @param userId The userId of the user that is not attending.
     * @param eventId The eventId of the event that the user is not attending.
     * @throws SQLException
     * @throws URISyntaxException
     */
    @RequestMapping(method=RequestMethod.DELETE, path="attend")
    public void unattendEvent(@RequestParam int userId, @RequestParam int eventId) throws SQLException, URISyntaxException {
    	eventDao.setEventAttending(userId, eventId, false);
    }

    /**
     * Marks the specified user as liking the specified event.
     * 
     * @param userId The userId of the user that liked an event.
     * @param eventId The eventId of the event that the user liked.
     * @throws SQLException
     * @throws URISyntaxException
     */
    @RequestMapping(method=RequestMethod.POST, path="like")
    public void likeEvent(@RequestParam int userId, @RequestParam int eventId) throws SQLException, URISyntaxException {
    	eventDao.setEventLiked(userId, eventId, true);
    }
    
    /**
     * Marks the specified user as not liking the specified event.
     * 
     * @param userId The userId of the user that unliked an event.
     * @param eventId The eventId of the event that the user unliked.
     * @throws SQLException
     * @throws URISyntaxException
     */
    @RequestMapping(method=RequestMethod.DELETE, path="like")
    public void unlikeEvent(@RequestParam int userId, @RequestParam int eventId) throws SQLException, URISyntaxException {
    	eventDao.setEventLiked(userId, eventId, false);
    }
}