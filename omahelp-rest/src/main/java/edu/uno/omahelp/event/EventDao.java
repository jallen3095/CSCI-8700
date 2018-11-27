package edu.uno.omahelp.event;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.uno.omahelp.user.User;
import edu.uno.omahelp.user.UserDao;

/**
 * This class contains the methods necessary to handle the User database operations
 * requested by the EventController.
 */
@Component
class EventDao {

	@Autowired
    private UserDao userDao;

    private Connection connection;
    
    /**
     * Class constructor that creates a connection to the database.
     */
    public EventDao() throws URISyntaxException, SQLException {
        connection = getConnection();
    }

    /**
     * Lists all the events in the Event database and puts them in a list.
     * 
     * @return A List object containing an Event object for each event in the database.
     * @throws URISyntaxException
     * @throws SQLException
     */
    public List<Event> listAllEvents() throws URISyntaxException, SQLException {
        Statement stmt = connection.createStatement();
        String sql;
        sql = "SELECT * FROM \"Event\"";
        ResultSet rs = stmt.executeQuery(sql);
        List<Event> events = new ArrayList<>();
        while(rs.next()) {
        	Event event = mapEvent(rs);
        	event.setOrganizers(getOrganizingUsers(event.getId()));
        	events.add(event);
        }

        return events;
    }

    /**
     * Lists all the events in the Event_User database corresponding to userId.
     * 
     * @param userId The userId of the user to return events for.
     * @return A List object containing Event objects.
     * @throws URISyntaxException
     * @throws SQLException
     */
    public List<Event> listMyEvents(int userId) throws URISyntaxException, SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT DISTINCT E.* FROM \"Event\" E INNER JOIN \"Event_User\" EU ON E.event_id = EU.event_id WHERE EU.user_id = ? AND (EU.attending = 't' OR EU.organizing = 't')");
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();
        List<Event> events = new ArrayList<>();
        while(rs.next()) {
        	Event event = mapEvent(rs);
        	event.setOrganizers(getOrganizingUsers(event.getId()));
            events.add(event);
        }

        return events;
    }

    /**
     * Performs the actual insertion of an Event into the Event database.
     * 
     * @param event An Event object to add into the database.
     * @throws URISyntaxException
     * @throws SQLException
     */
    public void createEvent(Event event) throws URISyntaxException, SQLException {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO \"Event\" (event_name, event_address, event_date, event_description) VALUES (?, ?, ?, ?)");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = event.getDate();

        try {
            Date date = convertJavaDateToSqlDate(format.parse(dateString));
            stmt.setString(1, event.getName());
            stmt.setString(2, event.getLocation());
            stmt.setDate(3, date);
            stmt.setString(4, event.getDescription());
            stmt.executeUpdate();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        List<Event> allEvents = listAllEvents();
        Event createdEvent = allEvents.get(allEvents.size() - 1);
        if (event.getOrganizers() != null) {
        	for (User user : event.getOrganizers()) {
                setEventOrganizing(user.getUserId(), createdEvent.getId(), true);
        	}
        }
    }

    /**
     * Performs the actual editing of an event in the database.
     * 
     * @param event An Event object containing updated values.
     * @throws SQLException
     * @throws URISyntaxException
     */
    public void editEvent(Event event) throws SQLException, URISyntaxException {
        PreparedStatement stmt = connection.prepareStatement("UPDATE \"Event\" SET event_name = ?, event_address = ?, event_date = ?, event_description = ? WHERE event_id = ?");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = event.getDate();

        try {
            Date date = convertJavaDateToSqlDate(format.parse(dateString));
            stmt.setString(1, event.getName());
            stmt.setString(2, event.getLocation());
            stmt.setDate(3, date);
            stmt.setString(4, event.getDescription());
            stmt.setInt(5, event.getId());
            stmt.executeUpdate();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Performs the actual deletion of an event in the Event database.
     * 
     * @param eventId The eventId of the Event to delete.
     * @throws URISyntaxException
     * @throws SQLException
     */
	public void deleteEvent(int eventId) throws URISyntaxException, SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM \"Event\" WHERE event_id = ?");
        stmt.setInt(1, eventId);
        stmt.executeUpdate();
	}

    /**
     * Returns true if the specified user and event is in the Event_User database and false otherwise.
     * 
     * @param userId The userId of the user.
     * @param eventId The eventId of the event.
     * @return Truth value of the existence of the user and event in the Event_User database.
     * @throws SQLException
     * @throws URISyntaxException
     */
	public boolean hasEventUser(int userId, int eventId) throws SQLException, URISyntaxException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM \"Event_User\" WHERE user_id = ? AND event_id = ?");
        stmt.setInt(1, userId);
        stmt.setInt(2, eventId);
        return stmt.executeQuery().next();
	}

    /**
     * Marks a specified user as liking a specified event in the Event_User database.
     * 
     * @param userId The userId of the user.
     * @param eventId The eventId of the event.
     * @param liked The truth value of what to set the 'liked' field to.
     * @throws SQLException
     * @throws URISyntaxException
     */
	public void setEventLiked(int userId, int eventId, boolean liked) throws SQLException, URISyntaxException {
		if (hasEventUser(userId, eventId)) {
            PreparedStatement stmt = connection.prepareStatement("UPDATE \"Event_User\" SET liked = ? WHERE user_id = ? AND event_id = ?");
            stmt.setBoolean(1, liked);
            stmt.setInt(2, userId);
            stmt.setInt(3, eventId);
            stmt.executeUpdate();
		} else {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO \"Event_User\" VALUES (?, ?, ?, ?, ?)");
            stmt.setInt(1, userId);
            stmt.setInt(2, eventId);
            stmt.setBoolean(3, false);
            stmt.setBoolean(4, liked);
            stmt.setBoolean(5, false);
            stmt.executeUpdate();
		}
	}

    /**
     * Returns the list of users who have liked a specified event.
     * 
     * @param eventId The eventId of the event.
     * @return A List object containing User objects.
     * @throws SQLException
     * @throws URISyntaxException
     */
    public List<User> getLikedUsers(int eventId) throws SQLException, URISyntaxException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM \"Event_User\" WHERE event_id = ? AND liked = 't'");
        stmt.setInt(1, eventId);
        ResultSet rs = stmt.executeQuery();
        List<User> users = new ArrayList<>();
        while (rs.next()) {
          int userId = rs.getInt("user_id");
          User user = userDao.getUserById(userId);
          if (user != null) {
        	  users.add(user);
          }
        }
        return users;
    }

    /**
     * Marks a specified user as attending a specified event.
     * 
     * @param userId The userId of the user.
     * @param eventId The eventId of the event.
     * @param attending The truth value of if the user is attending the event.
     * @throws SQLException
     * @throws URISyntaxException
     */
	public void setEventAttending(int userId, int eventId, boolean attending) throws SQLException, URISyntaxException {
		if (hasEventUser(userId, eventId)) {
            PreparedStatement stmt = connection.prepareStatement("UPDATE \"Event_User\" SET attending = ? WHERE user_id = ? AND event_id = ?");
            stmt.setBoolean(1, attending);
            stmt.setInt(2, userId);
            stmt.setInt(3, eventId);
            stmt.executeUpdate();
		} else {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO \"Event_User\" VALUES (?, ?, ?, ?, ?)");
            stmt.setInt(1, userId);
            stmt.setInt(2, eventId);
            stmt.setBoolean(3, attending);
            stmt.setBoolean(4, false);
            stmt.setBoolean(5, false);
            stmt.executeUpdate();
		}
	}
	
	/**
     * Set event organizing
     * 
     * @param userId The userId of the user.
     * @param eventId The eventId of the event.
     * @param organizing The truth value of if the user is organizing the event.
     * @throws SQLException
     * @throws URISyntaxException
     */
	public void setEventOrganizing(int userId, int eventId, boolean organizing) throws SQLException, URISyntaxException {
		if (hasEventUser(userId, eventId)) {
            PreparedStatement stmt = connection.prepareStatement("UPDATE \"Event_User\" SET organizing = ? WHERE user_id = ? AND event_id = ?");
            stmt.setBoolean(1, organizing);
            stmt.setInt(2, userId);
            stmt.setInt(3, eventId);
            stmt.executeUpdate();
		} else {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO \"Event_User\" VALUES (?, ?, ?, ?, ?)");
            stmt.setInt(1, userId);
            stmt.setInt(2, eventId);
            stmt.setBoolean(3, false);
            stmt.setBoolean(4, false);
            stmt.setBoolean(5, organizing);
            stmt.executeUpdate();
		}
	}

    /**
     * Returns the list of users who are attending a specified event.
     * 
     * @param eventId The eventId of the event.
     * @return A List object containing User objects.
     * @throws SQLException
     * @throws URISyntaxException
     */
	public List<User> getAttendingUsers(int eventId) throws SQLException, URISyntaxException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM \"Event_User\" WHERE event_id = ? AND attending ='t'");
        stmt.setInt(1, eventId);
        ResultSet rs = stmt.executeQuery();
        List<User> users = new ArrayList<>();
        while (rs.next()) {
        	int userId = rs.getInt("user_id");
        	User user = userDao.getUserById(userId);
        	if (user != null) {
        		users.add(user);
        	}
        }
        return users;
	}
	
    /**
     * Returns the list of users who are organizing a specified event.
     * 
     * @param eventId The eventId of the event.
     * @return A List object containing User objects.
     * @throws SQLException
     * @throws URISyntaxException
     */
	public List<User> getOrganizingUsers(int eventId) throws SQLException, URISyntaxException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM \"Event_User\" WHERE event_id = ? AND organizing ='t'");
        stmt.setInt(1, eventId);
        ResultSet rs = stmt.executeQuery();
        List<User> users = new ArrayList<>();
        while (rs.next()) {
        	int userId = rs.getInt("user_id");
        	User user = userDao.getUserById(userId);
        	if (user != null) {
        		users.add(user);
        	}
        }
        return users;
	}

    /**
     * Maps the field values from a ResultSet object into a new Event object.
     * 
     * @param rs A ResultSet that needs to be converted into an Event object.
     * @return An Event object representing a row from the Event database.
     * @throws SQLException
     * @throws URISyntaxException
     */
    private Event mapEvent(ResultSet rs) throws SQLException, URISyntaxException {
    	Event event = new Event();
        event.setId(rs.getInt("event_id"));
        event.setName(rs.getString("event_name"));
        event.setLocation(rs.getString("event_address"));
        event.setDate(rs.getString("event_date"));
        event.setDescription(rs.getString("event_description"));
        event.setAttendees(getAttendingUsers(event.getId()));
        event.setInterested(getLikedUsers(event.getId()));
        return event;
    }

    /**
     * Converts a java.util.Date into a java.sql.Date to be used in SQL queries.
     * 
     * @param date A java.util.Date to be converted.
     * @return A java.sql.Date object.
     */
    private java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    /**
     * Performs the connection to the database.
     * 
     * @return A Connection object for the database connection.
     * @throws URISyntaxException
     * @throws SQLException
     */
    private Connection getConnection() throws URISyntaxException, SQLException {
        URI dbURI = null;

        if(System.getenv("DATABASE_URL") != null) {
            dbURI = new URI(System.getenv("DATABASE_URL"));
        } else {
            String DATABASE_URL = "postgres://wajuhzuzopvjva:15e166e3b55bacf46b2e4040ddd127fa17ac8b5f6da3fd602961415e8ff6273f@ec2-54-225-76-201.compute-1.amazonaws.com:5432/db0fu95pd1fvq";
            dbURI = new URI(DATABASE_URL);
        }

		String username = dbURI.getUserInfo().split(":")[0];
		String password = dbURI.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbURI.getHost() + ':' + dbURI.getPort() + dbURI.getPath() + "?sslmode=require";

		return DriverManager.getConnection(dbUrl, username, password);
	}
}