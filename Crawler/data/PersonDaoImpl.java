1
https://raw.githubusercontent.com/Luke-Grammer/IMDB-Database-Application/master/src/data/dao/impl/PersonDaoImpl.java
/*
 * PersonDaoImpl.java
 * Luke Grammer, Lauren Haylock, and Davis Beilue
 * CSCE 315-907
 * 10/15/2019
 */

package data.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data.dao.PersonDao;
import data.dao.TitleDao;
import data.model.Job;
import data.model.Person;
import data.model.Title;

/**
 * The Class PersonDaoImpl implements the PersonDao interface, providing useful
 * functions for accessing information about particular people to be used by the
 * Database Manager.
 */
public class PersonDaoImpl implements PersonDao {

	/** The connection to the database. */
	Connection conn = null;

	/**
	 * Basic parameterized constructor for PersonDaoImpl.
	 *
	 * @param conn the already established connection to the database
	 */
	public PersonDaoImpl(Connection conn) {
		this.conn = conn;
	}

	/**
	 * Gets general information about all people in the database with names matching
	 * a search string and applicable filters. Returns an empty ArrayList in case of
	 * failure. All unnecessary filters in the function call should be set to null.
	 *
	 * @param name         the search string corresponding to the person's name
	 * @param profession   filter for profession (ex. 'actress')
	 * @param minBirthYear filter for minimum birth year
	 * @param maxBirthYear filter for maximum birth year
	 * @param minDeathYear filter for minimum death year
	 * @param maxDeathYear filter for maximum death year
	 * @param unique       a flag indicated whether or not to return all matching
	 *                     people or just one
	 * @return the list of people with names matching the search string and
	 *         applicable filters
	 * @throws SQLException when an exception is handled processing the database
	 *                      query
	 */
	public ArrayList<Person> getPeople(String name, String profession, Integer minBirthYear, Integer maxBirthYear,
			Integer minDeathYear, Integer maxDeathYear, Boolean unique) throws SQLException {

		ArrayList<Person> return_val = new ArrayList<Person>();
		if (name == null)
			return return_val;

		name = name.trim();
		if (name.equalsIgnoreCase(""))
			return return_val;

		// Add extra filters to query if applicable
		String extraFilters = "";
		extraFilters += (profession == null) ? "" : " AND LOWER(professions) like ?";
		extraFilters += (minBirthYear == null) ? "" : " AND birthYear >= ?";
		extraFilters += (maxBirthYear == null) ? "" : " AND birthYear <= ?";
		extraFilters += (minDeathYear == null) ? "" : " AND deathYear >= ?";
		extraFilters += (maxDeathYear == null) ? "" : " AND deathYear <= ?";

		if (unique) {
			extraFilters += " limit 1";
		}

		try {
			// Create a prepared statement object
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM People where LOWER(name) = ?" + extraFilters);

			// Fill in information including any applicable filters
			Integer currentIndex = 1;

			stmt.setString(currentIndex++, name.toLowerCase());

			if (profession != null)
				stmt.setString(currentIndex++, "%" + profession.toLowerCase() + "%");

			if (minBirthYear != null)
				stmt.setInt(currentIndex++, minBirthYear);

			if (maxBirthYear != null)
				stmt.setInt(currentIndex++, maxBirthYear);

			if (minDeathYear != null)
				stmt.setInt(currentIndex++, minDeathYear);

			if (maxDeathYear != null)
				stmt.setInt(currentIndex++, maxDeathYear);

			// Send statement to DBMS
			ResultSet result = stmt.executeQuery();

			// Collect output
			while (result.next()) {
				String[] professions = null;
				if (result.getString("professions") != null)
					professions = result.getString("professions").split(",");

				String[] knownFor = null;
				if (result.getString("knownFor") != null)
					knownFor = result.getString("knownFor").split(",");

				return_val.add(new Person(result.getString("personID"), result.getString("name"),
						result.getInt("birthYear"), result.getInt("deathYear"), professions, knownFor));
			}
		} catch (SQLException e) {
			throw e;
		}
		return return_val;
	}

	/**
	 * Accesses the database and returns a particular person specified by their
	 * personID. Returns null to indicate failure.
	 *
	 * @param ID the unique identifier of the person to find
	 * @return the person in the database with matching ID
	 * @throws SQLException when an exception is handled processing the database
	 *                      query
	 */
	public Person getPersonByID(String ID) throws SQLException {
		Person return_val = null;

		try {
			// Create a prepared statement object
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM People where personID = ? LIMIT 1");

			// Fill in information
			stmt.setString(1, ID);

			// Send statement to DBMS
			ResultSet result = stmt.executeQuery();

			// Collect output
			if (result.next()) {
				String[] professions = null;
				if (result.getString("professions") != null)
					professions = result.getString("professions").split(",");

				String[] knownFor = null;
				if (result.getString("knownFor") != null)
					knownFor = result.getString("knownFor").split(",");

				return_val = new Person(result.getString("personID"), result.getString("name"),
						result.getInt("birthYear"), result.getInt("deathYear"), professions, knownFor);
			}
		} catch (SQLException e) {
			throw e;
		}
		return return_val;
	}

	/**
	 * Accesses the Database to get all jobs performed by a particular person (with
	 * matching personID) on titles that match the given title filters. Returns an
	 * empty ArrayList if the person did not have any jobs.
	 * 
	 * Note: will not return any results if the type of the title is 'tvEpisode'.
	 * The query takes much too long to run and produces too much output if
	 * individual TV episodes are included in an actors filmography, the series
	 * should suffice.
	 *
	 * @param person       the person involved in the job
	 * @param title        the name of the title the person worked on
	 * @param type         the type of title that was worked on
	 * @param genre        the genre of the title
	 * @param minStartYear the minimum release year of the title
	 * @param maxStartYear the maximum release year of the title
	 * @param minEndYear   the minimum end year of the title
	 * @param maxEndYear   the maximum end year of the title
	 * @param minRuntime   the minimum runtime of the title
	 * @param maxRuntime   the maximum runtime of the title
	 * @param minRating    the minimum rating of the title
	 * @param maxRating    the maximum rating of the title
	 * @param adult        a flag indicating if the title contains adult content
	 * @param job          the job performed by the person
	 * @param character    the character played by the person
	 * @return the list of people-title jobs involving the given person on titles
	 *         that satisfy the filters
	 * @throws SQLException when an exception is handled processing the database
	 *                      query
	 */
	public ArrayList<Job> getAllJobs(Person person, String title, String type, String genre, Integer minStartYear,
			Integer maxStartYear, Integer minEndYear, Integer maxEndYear, Integer minRuntime, Integer maxRuntime,
			Double minRating, Double maxRating, Boolean adult, String job, String character, Boolean unique) throws SQLException {
		ArrayList<Job> jobs = new ArrayList<Job>();

		// Add extra filters to query if applicable
		String extraFilters = "";
		extraFilters += (title == null) ? "" : " AND LOWER(t.title) = ?";
		extraFilters += (type == null) ? "" : " AND LOWER(t.type) = ?";
		extraFilters += (genre == null) ? "" : " AND LOWER(t.genres) like ?";
		extraFilters += (minStartYear == null) ? "" : " AND t.startYear >= ?";
		extraFilters += (maxStartYear == null) ? "" : " AND t.startYear <= ?";
		extraFilters += (minEndYear == null) ? "" : " AND t.endYear >= ?";
		extraFilters += (maxEndYear == null) ? "" : " AND t.endYear <= ?";
		extraFilters += (minRuntime == null) ? "" : " AND t.runtime >= ?";
		extraFilters += (maxRuntime == null) ? "" : " AND t.runtime <= ?";
		extraFilters += (minRating == null) ? "" : " AND t.rating >= ?";
		extraFilters += (maxRating == null) ? "" : " AND t.rating <= ?";
		extraFilters += (job == null) ? "" : " AND LOWER(j.category) = ?";
		extraFilters += (character == null) ? "" : " AND LOWER(j.characters) like ?";

		if (adult != null) {
			if (adult) {
				extraFilters += " AND t.adult IS true";
			} else {
				extraFilters += " AND t.adult IS false";
			}
		}
		
		if (unique) {
			extraFilters += " LIMIT 1";
		}

		try {
			// Create a prepared statement object
			PreparedStatement stmt = conn.prepareStatement(
					"SELECT * FROM Jobs j, Titles t WHERE t.titleID = j.titleID AND t.type <> 'tvEpisode' AND j.personID = ?"
							+ extraFilters);

			Integer currentIndex = 1;

			// Fill in information
			stmt.setString(currentIndex++, person.getPersonID());

			if (title != null)
				stmt.setString(currentIndex++, title.toLowerCase());

			if (type != null)
				stmt.setString(currentIndex++, type.toLowerCase().replaceAll(" ", ""));

			if (genre != null)
				stmt.setString(currentIndex++, "%" + genre.toLowerCase() + "%");

			if (minStartYear != null)
				stmt.setInt(currentIndex++, minStartYear);

			if (maxStartYear != null)
				stmt.setInt(currentIndex++, maxStartYear);

			if (minEndYear != null)
				stmt.setInt(currentIndex++, minEndYear);

			if (maxEndYear != null)
				stmt.setInt(currentIndex++, maxEndYear);

			if (minRuntime != null)
				stmt.setInt(currentIndex++, minRuntime);

			if (maxRuntime != null)
				stmt.setInt(currentIndex++, maxRuntime);

			if (minRating != null)
				stmt.setDouble(currentIndex++, minRating);

			if (maxRating != null)
				stmt.setDouble(currentIndex++, maxRating);

			if (job != null)
				stmt.setString(currentIndex++, job.toLowerCase());

			if (character != null)
				stmt.setString(currentIndex++, "%" + character.toLowerCase() + "%");

			// Send statement to DBMS
			ResultSet result = stmt.executeQuery();

			// Collect output
			TitleDao tDao = new TitleDaoImpl(conn);
			while (result.next()) {
				String[] characters = null;
				if (result.getString("characters") != null) {
					characters = result.getString("characters").split("\",\"");

					for (Integer i = 0; i < characters.length; i++) {
						characters[i] = characters[i].replaceAll("[\"\\[\\]]", "");
					}
				}

				jobs.add(new Job(tDao.getTitleByID(result.getString("titleID")), person, result.getString("category"),
						result.getString("job"), characters));
			}
		} catch (SQLException e) {
			throw e;
		}

		return jobs;
	}

	/**
	 * Accesses the Database to get all jobs performed by a particular person (with
	 * matching personID) on a particular title (with matching title ID). Returns an
	 * empty ArrayList if the person did not have any jobs working on that title.
	 *
	 * @param person the person
	 * @param title  the title
	 * @return all the jobs performed by the particular individual
	 * @throws SQLException when an exception is handled processing the database
	 *                      query
	 */
	public ArrayList<Job> getJobs(Person person, Title title) throws SQLException {
		ArrayList<Job> jobs = new ArrayList<Job>();

		try {
			// Create a prepared statement object
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Jobs WHERE personID = ? AND titleID = ?");

			// Fill in information
			stmt.setString(1, person.getPersonID());
			stmt.setString(2, title.getTitleID());

			// Send statement to DBMS
			ResultSet result = stmt.executeQuery();

			// Collect output
			while (result.next()) {
				String[] characters = null;
				if (result.getString("characters") != null) {
					characters = result.getString("characters").split("\",\"");

					for (Integer i = 0; i < characters.length; i++) {
						characters[i] = characters[i].replaceAll("[\"\\[\\]]", "");
					}
				}

				jobs.add(new Job(title, person, result.getString("category"), result.getString("job"), characters));
			}
		} catch (SQLException e) {
			throw e;
		}

		return jobs;
	}
}
