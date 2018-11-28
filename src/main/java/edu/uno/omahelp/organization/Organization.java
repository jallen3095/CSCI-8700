package edu.uno.omahelp.organization;

/**
 * This class consists of all fields and methods necessary to represent
 * an organization within the OmaHelp application. The fields of this class correspond
 * to the "Organization" table within the OmaHelp database.
 */
public class Organization {

	private int id;
	private String name;
	private String phone;
	private String address;

	/**
	 * Returns the Organization object's identification number.
	 * 
	 * @return The Organization object's integer id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the Organization object's identification number.
	 * 
	 * @param id The integer id value of an Organization.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the Organization object's name.
	 * 
	 * @return The name String of an Organization.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Organization object's name.
	 * 
	 * @param name The name String of an Organization.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the Organization object's phone number.
	 * 
	 * @return The phone number String of an Organization.
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the Organization object's phone number.
	 * 
	 * @param phone The phone number String of an Organization.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Returns the Organization object's address.
	 * 
	 * @return The address String of an Organization.
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the Organization object's address.
	 * 
	 * @param address The address String of an Organization.
	 */
	public void setAddress(String address) {
		this.address = address;
	}
}