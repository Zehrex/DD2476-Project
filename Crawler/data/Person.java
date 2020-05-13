1
https://raw.githubusercontent.com/Luke-Grammer/IMDB-Database-Application/master/src/data/model/Person.java
/*
 * Person.java
 * Luke Grammer, Lauren Haylock, and Davis Beilue
 * CSCE 315-907
 * 10/15/2019
 */

package data.model;

/**
 * Person class to store person information retrieved from the database.
 */
public class Person {

	/** The unique person identifier. */
	private String personID;

	/** The full name of the person. */
	private String name;

	/** The person's birth year. */
	private Integer birthYear;

	/** The person's death year (if applicable). */
	private Integer deathYear;

	/**
	 * The person's three primary professions (ex: 'actor', 'actress', 'director',
	 * etc...)
	 */
	private String[] professions;

	/** A list of title identifiers the person is best known for. */
	private String[] knownFor;

	/**
	 * Basic parameterized constructor for Person.
	 *
	 * @param personID    the unique person ID
	 * @param name        the full name of the person
	 * @param birthYear   the person's birth year
	 * @param deathYear   the person's death year
	 * @param professions the person's primary professions
	 * @param knownFor    titles the person is best known for
	 */
	public Person(String personID, String name, Integer birthYear, Integer deathYear, String[] professions,
			String[] knownFor) {
		this.personID = personID;
		this.name = name;
		this.birthYear = birthYear;
		this.deathYear = deathYear;
		this.professions = professions;
		this.knownFor = knownFor;
	}

	/**
	 * Gets the unique person ID.
	 *
	 * @return the person ID
	 */
	public String getPersonID() {
		return personID;
	}

	/**
	 * Sets the unique person ID.
	 *
	 * @param personID the new person ID
	 */
	public void setPersonID(String personID) {
		this.personID = personID;
	}

	/**
	 * Gets the person's full name.
	 *
	 * @return the person's full name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the person's full name.
	 *
	 * @param name the new full name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the birth year.
	 *
	 * @return the birth year
	 */
	public Integer getBirthYear() {
		return birthYear;
	}

	/**
	 * Sets the person's birth year.
	 *
	 * @param birthYear the new birth year
	 */
	public void setBirthYear(Integer birthYear) {
		this.birthYear = birthYear;
	}

	/**
	 * Gets the person's death year.
	 *
	 * @return the person's death year
	 */
	public Integer getDeathYear() {
		return deathYear;
	}

	/**
	 * Sets the person's death year.
	 *
	 * @param deathYear the person's death year
	 */
	public void setDeathYear(Integer deathYear) {
		this.deathYear = deathYear;
	}

	/**
	 * Gets the person's primary professions.
	 *
	 * @return the list of primary professions
	 */
	public String[] getProfessions() {
		return professions;
	}

	/**
	 * Sets the person's primary professions.
	 *
	 * @param professions the new list of primary professions
	 */
	public void setProfessions(String[] professions) {
		this.professions = professions;
	}

	/**
	 * Gets the titles IDs of the titles the person is best known for.
	 *
	 * @return the list of title IDs of the titles the person is known for
	 */
	public String[] getKnownFor() {
		return knownFor;
	}

	/**
	 * Sets the title IDs of the titles the person is best known for.
	 *
	 * @param knownFor the list of title IDs
	 */
	public void setKnownFor(String[] knownFor) {
		this.knownFor = knownFor;
	}
}
