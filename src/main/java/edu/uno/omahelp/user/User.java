package edu.uno.omahelp.user;

import java.util.List;

import edu.uno.omahelp.organization.Organization;

/**
 * This class consists of all fields and methods necessary to represent
 * a user of the OmaHelp application. The fields of this class correspond
 * to the "User" table within the OmaHelp database.
 */
public class User {

    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Organization> organizations;
    private boolean admin;
    
    /**
     * Returns the User object's user identification number.
     *
     * @return The User object's integer userId.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the User object's user identification number.
     *
     * @param userId The integer userId value of a User.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Returns the User object's first name.
     *
     * @return The User object's firstName String.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the User object's first name.
     *
     * @param firstName The first name String of a User.
     */    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the User object's last name.
     *
     * @return The User object's lastName String.
     */    
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the User object's last name.
     *
     * @param lastName The last name String of a User.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the User object's email address.
     *
     * @return The User object's email String.
     */    
    public String getEmail() {
        return email;
    }

    /**
     * Sets the User object's email address.
     *
     * @param email The email String of a User.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the User object's password.
     *
     * @return The User object's password String.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the User object's password.
     *
     * @param password The password String of a User.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the User object's administrator status.
     *
     * @return The User object's boolean administrator status value.
     */    
    public boolean getAdmin() {
        return admin;
    }

    /**
     * Sets the User object's administrator status.
     *
     * @param admin The boolean value of a User's administrator status.
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     * Returns the User object's organizations.
     * 
     * @return The User object's organization list.
     */
	public List<Organization> getOrganizations() {
		return organizations;
	}

	/**
	 * Sets the User object's organizations.
	 * 
	 * @param organizations The list of organizations the user belongs to.
	 */
	public void setOrganizations(List<Organization> organizations) {
		this.organizations = organizations;
	}
}