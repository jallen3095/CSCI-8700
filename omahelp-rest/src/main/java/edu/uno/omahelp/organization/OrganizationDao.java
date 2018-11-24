package edu.uno.omahelp.organization;

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

/**
 * This class contains the methods necessary to handle the Organization database operations
 * requested by the OrganizationController.
 */
@Component
class OrganizationDao {

    private Connection connection;

    /**
     * Class constructor that creates a connection to the database.
     */
    public OrganizationDao() throws URISyntaxException, SQLException {
        connection = getConnection();
    }

    /**
     * Lists all the organizations in the Organization database and puts them in a list.
     * 
     * @return A List object containing an Organization object for each organization in the Organization database.
     * @throws URISyntaxException
     * @throws SQLException
     */
    public List<Organization> listAllOrganizations() throws URISyntaxException, SQLException {
        Statement stmt = connection.createStatement();
        String sql;
        sql = "SELECT * FROM \"Organization\"";
        ResultSet rs = stmt.executeQuery(sql);
        List<Organization> orgs = new ArrayList<>();
        while(rs.next()) {
        	Organization org = new Organization();
            org.setId(rs.getInt("org_id"));
            org.setName(rs.getString("org_name"));
            org.setPhone(rs.getString("phone"));
            org.setAddress(rs.getString("address"));
            orgs.add(org);
        }

        return orgs;
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