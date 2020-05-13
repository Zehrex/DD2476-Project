1
https://raw.githubusercontent.com/Luke-Grammer/IMDB-Database-Application/master/src/data/model/PersonTitleLink.java
/*
 * PersonTitleLink.java
 * Luke Grammer, Lauren Haylock, and Davis Beilue
 * CSCE 315-907
 * 10/15/2019
 */

package data.model;

/**
 * The Class PersonTitleLink is a simple object containing a unique person
 * identifier, title identifier, and the release year of the title. This class
 * is used to implement the actor/director search by year range.
 */
public class PersonTitleLink {

	/** The unique identifier of the person. */
	private String personID;

	/** The unique identifier of the title. */
	private String titleID;

	/** The release year of the title. */
	private int year;

	/**
	 * Basic field-based constructor for person title link
	 *
	 * @param personID the unique identifier of the person
	 * @param titleID  the unique identifier of the title
	 * @param year     the release year of the title
	 */
	public PersonTitleLink(String personID, String titleID, int year) {
		this.personID = personID;
		this.titleID = titleID;
		this.year = year;
	}

	/**
	 * Gets the unique identifier for the person.
	 *
	 * @return the unique identifier of the person
	 */
	public String getPersonID() {
		return personID;
	}

	/**
	 * Gets the unique identifier for the title.
	 *
	 * @return the unique title identifier
	 */
	public String getTitleID() {
		return titleID;
	}

	/**
	 * Gets the release year of the title.
	 *
	 * @return the release year of the title
	 */
	public int getYear() {
		return year;
	}
}
