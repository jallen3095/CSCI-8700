package edu.uno.omahelp.user;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class provides the /user mappings for the REST API of OmaHelp.
 */
@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
    private UserDao userDao;
    
    /**
     * Returns a User object that represents the user in the database 
     * with a user_id of userId.
     * 
     * @param userId The user_id of the user to return.
     * @return The User object associated with the userId parameter.
     * @throws URISyntaxException
     * @throws SQLException
     */
    @RequestMapping(path = "list/{userId}")
    public User getUserById(@PathVariable int userId) throws URISyntaxException, SQLException {
        return userDao.getUserById(userId);
    }
    
    /**
     * Returns a List object that contains all users in the database.
     * 
     * @return A List object that contains User objects.
     * @throws URISyntaxException
     * @throws SQLException
     */
    @RequestMapping(path = "list") 
    public List<User> listAllUsers() throws URISyntaxException, SQLException {
        return userDao.listAllUsers();
    }
    
    /**
     * Creates a new record in the User database for registering a new user. 
     * A userId does not need to be sent in as these are automatically generated.
     * 
     * @param user A User object to add into the database.
     * @throws URISyntaxException
     * @throws SQLException
     */
    @RequestMapping(path = "register", method = RequestMethod.POST)
    public void createUser(@RequestBody User user) throws URISyntaxException, SQLException {
        userDao.createUser(user);
    }

    /**
     * Edits an existing user within the User database.
     * 
     * @param user A User object with updated field values to send to the database.
     * @throws URISyntaxException
     * @throws SQLException
     */
    @RequestMapping(path = "edit", method = RequestMethod.PUT)
    public void editUser(@RequestBody User user) throws URISyntaxException, SQLException {
        userDao.editUser(user);
    }

    /**
     *  Deletes a user from the User database.
     * 
     * @param userId The user_id value of the user to delete.
     * @throws URISyntaxException
     * @throws SQLException
     */
    @RequestMapping(path = "delete", method = RequestMethod.DELETE)
    public void deleteUser(@RequestParam int userId) throws URISyntaxException, SQLException {
        userDao.deleteUser(userId);
    }

    /**
     * Logs in a user by checking if the email and password correspond to a 
     * record in the User database.
     * 
     * @param email    The email the user registered with.
     * @param password The user's password.
     * @return A User object corresponding to the user that is logging in.
     * @throws Exception
     */ 
    @RequestMapping(path = "login")
    public User login(@RequestParam String email, @RequestParam String password) throws Exception {
        return userDao.login(email, password);
    }
}