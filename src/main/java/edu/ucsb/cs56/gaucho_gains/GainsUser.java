/*
 * Maga Kim and Howard Lin
 * GauchoGains Web App
 * MongoDB Implementation
 * UCSB CS56 Summer 2018
 */
package edu.ucsb.cs56.gaucho_gains;

import edu.ucsb.cs56.gaucho_gains.GainsPassword;

public class GainsUser {
	private String valid;
	private String phoneno;
	private String email;
	private String firstName;
	private String lastName;
	private GainsPassword password;

	public GainsUser (String email, String phoneno, String firstName, String lastName,
			String password) {
		checkValidUser(email, phoneno, firstName, lastName, password);
		this.email = email;
		this.phoneno = phoneno;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = new GainsPassword(password);
	}

	/*
	 * Getters and Setters
	 */
	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getPasswordSalt() {
		return this.password.getSaltString();
	}

	public String getPasswordHash() {
		return this.password.getHashString();
	}

	public String getPhoneNo() {
		return this.phoneno;
	}

	public String getEmail() {
		return this.email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	/*
	 * Define valid user parameters here
	 */

	private void checkValidUser(String email, String phoneno, String firstName, String lastName, String password) {
		if (email.length() == 0 || firstName.length() == 0 || lastName.length() == 0 || password.length() == 0)
			this.valid = "Please fill in all forms";
		else if (!email.contains("@") || email.length() < 5 || email.charAt(email.length()-4) != '.' ||
				email.charAt(email.indexOf("@")+1) == '.')
			this.valid = "Invalid Email";
		else if (password.length() < 6)
			this.valid = "Please enter a password with length greater than 6";
		else if (!phoneno.chars().allMatch(x -> Character.isDigit(x)) || phoneno.length() != 10)
			this.valid = "Phone number should be in format ##########";
		else 
			this.valid = "valid";
	}
	
	//Getter to check for valid user
	public String getValidUserCheck() {
		return this.valid;
	}
	
	//Not currently in use
	@Override
	public String toString() {
		return this.email + " " + this.firstName + " " + this.lastName;
	}

}
