package edu.uno.omahelp.event;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
class EventDao {

      public List<Event> listAllEvents() throws URISyntaxException, SQLException {
          Connection connection = getConnection();
          Statement stmt = connection.createStatement();
          String sql;
          sql = "SELECT * FROM \"Event\"";
          ResultSet rs = stmt.executeQuery(sql);
          List<Event> events = new ArrayList<>();
          while(rs.next()) {
            Event event = new Event();
              event.setId(rs.getInt("event_id"));
              event.setName(rs.getString("event_name"));
              event.setLocation(rs.getString("event_address"));
              event.setDate(rs.getString("event_date"));
              event.setDescription(rs.getString("event_description"));
              events.add(event);
          }

          return events;
      }

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
      String dbUrl = "jdbc:postgresql://" + dbURI.getHost() + ':'
                  + dbURI.getPort() + dbURI.getPath()
                  + "?sslmode=require";
      return DriverManager.getConnection(dbUrl, username, password);
    }
}
