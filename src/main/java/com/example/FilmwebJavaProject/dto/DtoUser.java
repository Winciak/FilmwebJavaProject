package com.example.FilmwebJavaProject.dto;



import com.example.FilmwebJavaProject.validation.FieldMatch;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})
public class DtoUser {

	@NotNull(message = "is required")
	@Size(min = 3, max = 30, message = "has to be at least 3 characters/digit long")
	private String login;

	@NotNull(message = "is required")
	@Size(min = 5, max = 50, message = "has to be at least 5 characters/digit long")
	private String password;
	
	@NotNull(message = "is required")
	@Size(min = 5, max = 50, message = "has to be at least 5 characters/digit long")
	private String matchingPassword;

	@Pattern(regexp = "^[A-Z][A-Za-z]{2,39}", message = "first letter has to be uppercase, length 3-40 letters ")
	private String firstName;

	@Pattern(regexp = "^[A-Z][A-Za-z]{2,39}", message = "first letter has to be uppercase, length 3-40 letters ")
	private String lastName;

	@Size(max = 1000)
	private String about;

	public DtoUser() {

	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}
}
