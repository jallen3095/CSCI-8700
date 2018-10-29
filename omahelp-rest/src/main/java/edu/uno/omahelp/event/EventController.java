package edu.uno.omahelp.event;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("event")
public class EventController {

	@Autowired
	private EventDao eventDao;
	
    @RequestMapping("list")
    public List<Event> listAllEvents() throws URISyntaxException, SQLException {
    	return eventDao.listAllEvents();
    }
    
    @RequestMapping("list/{userId}")
    public List<Event> listMyEvents(@PathVariable int userId) throws URISyntaxException, SQLException {
    	return eventDao.listMyEvents(userId);
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public void createEvent(@RequestBody Event event) throws URISyntaxException, SQLException {
    	eventDao.createEvent(event);
    }
    
    @RequestMapping(method=RequestMethod.PUT)
    public void editEvent(@RequestBody Event event) throws URISyntaxException, SQLException {
    	eventDao.editEvent(event);
	}
    
    @RequestMapping(method=RequestMethod.DELETE)
    public void deleteEvent(@RequestParam int eventId) throws URISyntaxException, SQLException {
        eventDao.deleteEvent(eventId);
    }
    
    @RequestMapping(method=RequestMethod.POST, path="attend")
    public void attendEvent(@RequestParam int userId, @RequestParam int eventId) throws SQLException, URISyntaxException {
    	eventDao.setEventAttending(userId, eventId, true);
    }
    
    @RequestMapping(method=RequestMethod.DELETE, path="attend")
    public void unattendEvent(@RequestParam int userId, @RequestParam int eventId) throws SQLException, URISyntaxException {
    	eventDao.setEventAttending(userId, eventId, false);
    }
    
}
