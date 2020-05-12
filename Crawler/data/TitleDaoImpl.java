1
https://raw.githubusercontent.com/Luke-Grammer/IMDB-Database-Application/master/src/data/dao/impl/TitleDaoImpl.java
/*
 * TitleDaoImpl.java
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
import data.model.Title;

/**
 * The Class TitleDaoImpl implements the TitleDao interface, providing useful
 * functions for accessing information about particular titles to be used by the
 * Database Manager.
 */
public class TitleDaoImpl implements TitleDao {

	/** The connection to the database. */
	Connection conn;

	/**
	 * Basic parameterized constructor for TitleDaoImpl.
	 *
	 * @param conn the already established database connection
	 */
	public TitleDaoImpl(Connection conn) {
		this.conn = conn;
	}

	/**
	 * Accesses the Database to get all the titles with a title name that contains a
	 * particular search string. Returns an empty ArrayList in case of failure. All
	 * unnecessary filters in the function call should be set to null.
	 *
	 * @param title        the search string for the title name
	 * @param type         filter for type of title (ex. 'movie')
	 * @param genre        filter for the genre of the title
	 * @param minStartYear filter for the minimum release year
	 * @param maxStartYear filter for the maximum release year
	 * @param minEndYear   filter for the minimum end year
	 * @param maxEndYear   filter for the maximum end year
	 * @param minRuntime   filter for the minimum runtime
	 * @param maxRuntime   filter for the maximum runtime
	 * @param minRating    filter for the minimum rating
	 * @param maxRating    filter for the maximum rating
	 * @param adult        filter for determining if a given title contains adult
	 *                     content
	 * @return the titles matching the search string and the applicable filters
	 * @throws SQLException when an exception is handled processing the database
	 *                      query
	 */
	public ArrayList<Title> getTitles(String title, String type, String genre, Integer minStartYear,
			Integer maxStartYear, Integer minEndYear, Integer maxEndYear, Integer minRuntime, Integer maxRuntime,
			Double minRating, Double maxRating, Boolean adult) throws SQLException {
		ArrayList<Title> return_val = new ArrayList<Title>();

		boolean firstStatement = true;

		// Add extra filters to query if applicable
		String extraFilters = "";
		if (title != null) {
			System.out.println("Title");
			firstStatement = false;
			extraFilters += " WHERE LOWER(title) = ?";
		}

		if (type != null && firstStatement) {
			System.out.println("Type");
			firstStatement = false;
			extraFilters += " WHERE LOWER(type) = ?";
		} else
			extraFilters += (type == null) ? "" : " AND LOWER(type) = ?";

		if (genre != null && firstStatement) {
			System.out.println("Genre");
			firstStatement = false;
			extraFilters += " WHERE LOWER(genres) like ?";
		} else
			extraFilters += (genre == null) ? "" : " AND LOWER(genres) like ?";

		if (minStartYear != null && firstStatement) {
			System.out.println("MinStartYear");
			firstStatement = false;
			extraFilters += " WHERE startYear >= ?";
		} else
			extraFilters += (minStartYear == null) ? "" : " AND startYear >= ?";

		if (maxStartYear != null && firstStatement) {
			System.out.println("MaxStartYear");
			firstStatement = false;
			extraFilters += " WHERE startYear <= ?";
		} else
			extraFilters += (maxStartYear == null) ? "" : " AND startYear <= ?";

		if (minEndYear != null && firstStatement) {
			System.out.println("MinStartYear");
			firstStatement = false;
			extraFilters += " WHERE endYear >= ?";
		} else
			extraFilters += (minEndYear == null) ? "" : " AND endYear >= ?";

		if (maxEndYear != null && firstStatement) {
			System.out.println("MaxEndYear");
			firstStatement = false;
			extraFilters += " WHERE endYear <= ?";
		} else
			extraFilters += (maxEndYear == null) ? "" : " AND endYear <= ?";

		if (minRuntime != null && firstStatement) {
			System.out.println("MinRuntime");
			firstStatement = false;
			extraFilters += " WHERE runtime >= ?";
		} else
			extraFilters += (minRuntime == null) ? "" : " AND runtime >= ?";

		if (maxRuntime != null && firstStatement) {
			System.out.println("MaxRuntime");
			firstStatement = false;
			extraFilters += " WHERE runtime <= ?";
		} else
			extraFilters += (maxRuntime == null) ? "" : " AND runtime <= ?";

		if (minRating != null && firstStatement) {
			System.out.println("MinRating");
			firstStatement = false;
			extraFilters += " WHERE rating >= ?";
		} else
			extraFilters += (minRating == null) ? "" : " AND rating >= ?";

		if (maxRating != null && firstStatement) {
			System.out.println("MaxRating");
			firstStatement = false;
			extraFilters += " WHERE rating <= ?";
		} else
			extraFilters += (maxRating == null) ? "" : " AND rating <= ?";

		if (adult != null) {
			if (adult) {
				if (firstStatement) {
					System.out.println("Adult");
					firstStatement = false;
					extraFilters += " WHERE adult IS true";
				} else
					extraFilters += " AND adult IS true";
			} else {
				if (firstStatement) {
					System.out.println("Adult");
					firstStatement = false;
					extraFilters += " WHERE adult IS false";
				} else
					extraFilters += " AND adult IS false";
			}
		}

		if (firstStatement) {
			return return_val;
		}

		else {
			System.out.println("Not Null");
			try {
				// Create a prepared statement object
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Titles" + extraFilters);

				System.out.println(stmt);

				Integer currentIndex = 1;

				// Fill in information including any applicable filters

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

				// Send statement to DBMS
				System.out.println(stmt);
				ResultSet result = stmt.executeQuery();

				// Collect output
				System.out.println("Collecting Output");
				while (result.next()) {
					String[] genres = null;
					if (result.getString("genres") != null)
						genres = result.getString("genres").split(",");

					String[] directors = null;
					if (result.getString("directors") != null)
						directors = result.getString("directors").split(",");

					String[] writers = null;
					if (result.getString("writers") != null)
						writers = result.getString("writers").split(",");

					return_val.add(new Title(result.getString("titleID"), result.getString("type"),
							result.getString("title"), result.getString("originalTitle"), result.getBoolean("adult"),
							result.getInt("startYear"), result.getInt("endYear"), result.getInt("runtime"), genres,
							result.getDouble("rating"), result.getInt("numVotes"), result.getInt("season"),
							result.getInt("episode"), directors, writers));
				}
			} catch (SQLException e) {
				throw e;
			}

		}

		System.out.println("Done");
		return return_val;
	}

	/**
	 * Accesses the Database to get all the episodes of a given title.
	 *
	 * @param title   the parent title
	 * @param season  the particular season number to retrieve
	 * @param episode a particular episode number to retrieve
	 * @return the episodes of the parent title
	 * @throws SQLException when an exception is handled processing the database
	 *                      query
	 */
	public ArrayList<Title> getEpisodesByShow(Title title, Integer season, Integer episode) throws SQLException {
		ArrayList<Title> return_val = new ArrayList<Title>();

		// Add extra filters to query if applicable
		String extraFilters = "";
		extraFilters += (season == null) ? "" : " AND t2.season = ?";
		extraFilters += (episode == null) ? "" : " AND t2.episode = ?";

		try {
			// Create a prepared statement object
			PreparedStatement stmt = conn.prepareStatement(
					"SELECT t2.* FROM Titles t1 JOIN Episodes e ON t1.titleID = e.titleID JOIN Titles t2 ON t2.titleID = e.episodeID WHERE t1.titleID = ?"
							+ extraFilters + " order by (t2.season, t2.episode)");

			Integer currentIndex = 1;

			// Fill in information including any applicable filters
			stmt.setString(currentIndex++, title.getTitleID());

			if (season != null)
				stmt.setInt(currentIndex++, season);

			if (episode != null)
				stmt.setInt(currentIndex++, episode);

			// Send statement to DBMS
			ResultSet result = stmt.executeQuery();

			// Collect output
			while (result.next()) {
				String[] genres = null;
				if (result.getString("genres") != null)
					genres = result.getString("genres").split(",");

				String[] directors = null;
				if (result.getString("directors") != null)
					directors = result.getString("directors").split(",");

				String[] writers = null;
				if (result.getString("writers") != null)
					writers = result.getString("writers").split(",");

				return_val.add(new Title(result.getString("titleID"), result.getString("type"),
						result.getString("title"), result.getString("originalTitle"), result.getBoolean("adult"),
						result.getInt("startYear"), result.getInt("endYear"), result.getInt("runtime"), genres,
						result.getDouble("rating"), result.getInt("numVotes"), result.getInt("season"),
						result.getInt("episode"), directors, writers));
			}
		} catch (SQLException e) {
			throw e;
		}
		return return_val;
	}

	/**
	 * Accesses the database and returns a particular title specified by their
	 * titleID. Returns null to indicate failure.
	 *
	 * @param ID the unique title identifier
	 * @return the title from the database with matching ID
	 * @throws SQLException when an exception is handled processing the database
	 *                      query
	 */
	public Title getTitleByID(String ID) throws SQLException {
		Title return_val = null;

		try {
			// Create a prepared statement object
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Titles WHERE titleID = ? LIMIT 1");

			// Fill in information
			stmt.setString(1, ID);

			// Send statement to DBMS
			ResultSet result = stmt.executeQuery();

			// Collect output
			if (result.next()) {
				String[] genres = null;
				if (result.getString("genres") != null)
					genres = result.getString("genres").split(",");

				String[] directors = null;
				if (result.getString("directors") != null)
					directors = result.getString("directors").split(",");

				String[] writers = null;
				if (result.getString("writers") != null)
					writers = result.getString("writers").split(",");

				return_val = new Title(result.getString("titleID"), result.getString("type"), result.getString("title"),
						result.getString("originalTitle"), result.getBoolean("adult"), result.getInt("startYear"),
						result.getInt("endYear"), result.getInt("runtime"), genres, result.getDouble("rating"),
						result.getInt("numVotes"), result.getInt("season"), result.getInt("episode"), directors,
						writers);
			}
		} catch (SQLException e) {
			throw e;
		}
		return return_val;
	}

	/**
	 * Accesses the database and returns information about the parent TV show
	 * specified by a titleID corresponding to a TV Episode. Returns null to
	 * indicate failure.
	 *
	 * @param ID the unique episode identifier
	 * @return the title in the database that corresponds to the parent of the
	 *         episode
	 * @throws SQLException when an exception is handled processing the database
	 *                      query
	 */
	public Title getShowByEpisodeID(String ID) throws SQLException {
		Title return_val = null;

		try {
			// Create a prepared statement object
			PreparedStatement stmt = conn.prepareStatement(
					"SELECT t.* FROM Titles t INNER JOIN Episodes e ON t.titleID = e.titleID WHERE e.episodeID = ?");

			// Fill in information
			stmt.setString(1, ID);

			// Send statement to DBMS
			ResultSet result = stmt.executeQuery();

			// Collect output
			if (result.next()) {
				String[] genres = null;
				if (result.getString("genres") != null)
					genres = result.getString("genres").split(",");

				String[] directors = null;
				if (result.getString("directors") != null)
					directors = result.getString("directors").split(",");

				String[] writers = null;
				if (result.getString("writers") != null)
					writers = result.getString("writers").split(",");

				return_val = new Title(result.getString("titleID"), result.getString("type"), result.getString("title"),
						result.getString("originalTitle"), result.getBoolean("adult"), result.getInt("startYear"),
						result.getInt("endYear"), result.getInt("runtime"), genres, result.getDouble("rating"),
						result.getInt("numVotes"), result.getInt("season"), result.getInt("episode"), directors,
						writers);
			}
		} catch (SQLException e) {
			throw e;
		}
		return return_val;
	}

	/**
	 * Accesses the database to get the all jobs involved with a particular title
	 * that were performed by people matching the appropriate filters Returns an
	 * empty ArrayList in case of failure.
	 *
	 * @param title        the title
	 * @param person       the name of the person that worked on the title
	 * @param profession   a primary profession of the person
	 * @param minBirthYear the minimum birth year of the person
	 * @param maxBirthYear the maximum birth year of the person
	 * @param minDeathYear the minimum death year of the person
	 * @param maxDeathYear the maximum death year of the person
	 * @param job          the job performed by the person
	 * @param character    the character played by the person
	 * @return all the jobs performed for the particular title by peoplem matching
	 *         the appropriate filters
	 * @throws SQLException when an exception is handled processing the database
	 *                      query
	 */
	public ArrayList<Job> getAllJobs(Title title, String person, String profession, Integer minBirthYear,
			Integer maxBirthYear, Integer minDeathYear, Integer maxDeathYear, String job, String character)
			throws SQLException {
		ArrayList<Job> jobs = new ArrayList<Job>();

		// Add extra filters to query if applicable
		String extraFilters = "";
		extraFilters += (person == null) ? "" : " AND LOWER(p.name) = ?";
		extraFilters += (profession == null) ? "" : " AND LOWER(professions) like ?";
		extraFilters += (minBirthYear == null) ? "" : " AND p.birthYear >= ?";
		extraFilters += (maxBirthYear == null) ? "" : " AND p.birthYear <= ?";
		extraFilters += (minDeathYear == null) ? "" : " AND p.deathYear >= ?";
		extraFilters += (maxDeathYear == null) ? "" : " AND p.deathYear <= ?";
		extraFilters += (job == null) ? "" : " AND LOWER(j.category) = ?";
		extraFilters += (character == null) ? "" : " AND LOWER(j.characters) like ?";

		try {
			// Create a prepared statement object
			PreparedStatement stmt = conn.prepareStatement(
					"SELECT * FROM jobs j, People p WHERE j.personID = p.personID AND j.titleID = ?" + extraFilters);

			Integer currentIndex = 1;

			stmt.setString(currentIndex++, title.getTitleID());

			if (person != null)
				stmt.setString(currentIndex++, person.toLowerCase());

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

			if (job != null)
				stmt.setString(currentIndex++, job.toLowerCase());

			if (character != null)
				stmt.setString(currentIndex++, "%" + character.toLowerCase() + "%");

			// Send statement to DBMS
			ResultSet result = stmt.executeQuery();

			PersonDao pDao = new PersonDaoImpl(conn);
			while (result.next()) {
				String[] characters = null;
				if (result.getString("characters") != null) {
					characters = result.getString("characters").split("\",\"");

					for (Integer i = 0; i < characters.length; i++) {
						characters[i] = characters[i].replaceAll("[\"\\[\\]]", "");
					}
				}

				jobs.add(new Job(title, pDao.getPersonByID(result.getString("personID")), result.getString("category"),
						result.getString("job"), characters));
			}
		} catch (SQLException e) {
			throw e;
		}

		return jobs;
	}
}
