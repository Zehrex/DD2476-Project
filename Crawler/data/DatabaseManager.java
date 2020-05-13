1
https://raw.githubusercontent.com/Luke-Grammer/IMDB-Database-Application/master/src/data/DatabaseManager.java
/*
 * DatabaseManager.java
 * Luke Grammer, Lauren Haylock, and Davis Beilue
 * CSCE 315-907
 * 10/15/2019
 */

package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

import data.dao.*;
import data.dao.impl.*;
import data.model.*;
import search.ActorNode;

/**
 * The DatabaseManager class utilizes the DAO database interfaces to provide a
 * single cohesive interface for interacting with the database through the GUI.
 * The Database Manager provides several useful functions for retrieving and
 * printing information from the database.
 */
public class DatabaseManager {

	/** The person DAO for getting information about people from the database. */
	private PersonDao pDao;

	/** The title DAO for getting information about titles from the database. */
	private TitleDao tDao;

	/**
	 * The connection to the database used by the DAOs to create and execute
	 * database queries.
	 */
	private Connection conn = null;

	/**
	 * Constructor initializes new DAOImpl objects and creates a connection to the
	 * database.
	 */
	public DatabaseManager() {

		// Building the connection to the DB
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://db-315.cse.tamu.edu/IMDB_Database",
					DBCredentials.USER, DBCredentials.PASS);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(-1);
		}

		pDao = new PersonDaoImpl(conn);
		tDao = new TitleDaoImpl(conn);
		System.out.println("Successfully Connected to Database");
	}

	/**
	 * Close connection to the database if it is still open.
	 */
	public void close() {
		try {
			if (!conn.isClosed())
				conn.close();
			System.out.println("Connection closed.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Open connection could not be closed.");
		}
	}

	/**
	 * Gets the all Titles from the database matching the title filters that were
	 * worked on by people matching the people filters. Returns an empty ArrayList
	 * if no Titles are found.
	 *
	 * @param personName   the search string indicating the name of the person
	 * @param titleName    the search string indicating the name of the title
	 * @param profession   a profession of the person
	 * @param minBirthYear the minimum birth year of the person
	 * @param maxBirthYear the maximum birth year of the person
	 * @param minDeathYear the minimum death year of the person
	 * @param maxDeathYear the maximum death year of the person
	 * @param type         the type of title
	 * @param genre        the genre of the title
	 * @param minStartYear the minimum release year of the title
	 * @param maxStartYear the maximum release year of the title
	 * @param minEndYear   the minimum final year of the title
	 * @param maxEndYear   the maximum final year of the title
	 * @param minRuntime   the minimum runtime of the title
	 * @param maxRuntime   the maximum runtime of the title
	 * @param minRating    the minimum rating of the title
	 * @param maxRating    the maximum rating of the title
	 * @param adult        a flag indicating if the title has adult content
	 * @return all the titles where the title matched the filters and the person was
	 *         a cast member
	 */
	public ArrayList<Title> getPersonTitles(String personName, String titleName, String profession,
			Integer minBirthYear, Integer maxBirthYear, Integer minDeathYear, Integer maxDeathYear, String type,
			String genre, Integer minStartYear, Integer maxStartYear, Integer minEndYear, Integer maxEndYear,
			Integer minRuntime, Integer maxRuntime, Double minRating, Double maxRating, Boolean adult) {

		ArrayList<Job> jobs = new ArrayList<Job>();
		ArrayList<Person> people = new ArrayList<Person>();
		ArrayList<Title> titles = new ArrayList<Title>();

		// Get information about the first person matching appropriate filters
		people = getPersonInfo(personName, profession, minBirthYear, maxBirthYear, minDeathYear, maxDeathYear, true);

		try {
			// Get all jobs for the person on titles that match the title filters
			for (Person person : people) {
				jobs.addAll(pDao.getAllJobs(person, titleName, type, genre, minStartYear, maxStartYear, minEndYear,
						maxEndYear, minRuntime, maxRuntime, minRating, maxRating, adult, null, null, false));
			}

			// Get all titles from jobs where the name of the title matches the filter title
			// name
			for (Job job : jobs) {
				titles.add(job.getTitle());
			}
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		}

		return titles;
	}

	/**
	 * Gets the all People from the database matching the people filters that worked
	 * on titles matching the title filters. Returns an empty ArrayList if no People
	 * are found.
	 *
	 * @param personName   the search string indicating the name of the person
	 * @param titleName    the search string indicating the name of the title
	 * @param profession   a profession of the person
	 * @param minBirthYear the minimum birth year of the person
	 * @param maxBirthYear the maximum birth year of the person
	 * @param minDeathYear the minimum death year of the person
	 * @param maxDeathYear the maximum death year of the person
	 * @param type         the type of title
	 * @param genre        the genre of the title
	 * @param minStartYear the minimum release year of the title
	 * @param maxStartYear the maximum release year of the title
	 * @param minEndYear   the minimum final year of the title
	 * @param maxEndYear   the maximum final year of the title
	 * @param minRuntime   the minimum runtime of the title
	 * @param maxRuntime   the maximum runtime of the title
	 * @param minRating    the minimum rating of the title
	 * @param maxRating    the maximum rating of the title
	 * @param adult        a flag indicating if the title has adult content
	 * @return all the people where the person matched the person filters and worked
	 *         on titles matching the title filters
	 */
	public ArrayList<Person> getTitlePeople(String personName, String titleName, String profession,
			Integer minBirthYear, Integer maxBirthYear, Integer minDeathYear, Integer maxDeathYear, String type,
			String genre, Integer minStartYear, Integer maxStartYear, Integer minEndYear, Integer maxEndYear,
			Integer minRuntime, Integer maxRuntime, Double minRating, Double maxRating, Boolean adult) {

		ArrayList<Job> jobs = new ArrayList<Job>();
		ArrayList<Person> people = new ArrayList<Person>();
		ArrayList<Title> titles = new ArrayList<Title>();

		// Get information about all titles matching appropriate filters
		titles = getTitleInfo(titleName, type, genre, minStartYear, maxStartYear, minEndYear, maxEndYear, minRuntime,
				maxRuntime, minRating, maxRating, adult);

		try {
			// Get all jobs for each title with people that match the people filters
			for (Title title : titles) {
				jobs.addAll(tDao.getAllJobs(title, personName, profession, minBirthYear, maxBirthYear, minDeathYear,
						maxDeathYear, null, null));
			}

			for (Job job : jobs) {
				people.add(job.getPerson());
			}

		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		}

		return people;
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
	 * @param unique       a flag indicating if a single matching person should be
	 *                     returned or all matching people
	 * @return the list of people with names matching the search string and
	 *         applicable filters
	 */
	public ArrayList<Person> getPersonInfo(String name, String profession, Integer minBirthYear, Integer maxBirthYear,
			Integer minDeathYear, Integer maxDeathYear, Boolean unique) {
		ArrayList<Person> people = null;

		try {
			people = pDao.getPeople(name, profession, minBirthYear, maxBirthYear, minDeathYear, maxDeathYear, unique);
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		}
		return people;
	}

	/**
	 * Gets the person with corresponding ID.
	 *
	 * @param ID the ID of the person to search for
	 * @return the person with corresponding ID
	 */
	public Person getPersonByID(String ID) {
		Person person = null;

		try {
			person = pDao.getPersonByID(ID);
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		}
		return person;
	}

	/**
	 * Gets the title with corresponding ID.
	 *
	 * @param ID the ID of the title to search for
	 * @return the title with corresponding ID
	 */
	public Title getTitleByID(String ID) {
		Title title = null;

		try {
			title = tDao.getTitleByID(ID);
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		}
		return title;
	}

	/**
	 * Find all the people-title relationships for a given person on titles that
	 * match the applicable filters. All filters should be null if unused.
	 *
	 * @param person       the person involved in the job
	 * @param type         the type of title that was worked on
	 * @param genre        the genre of the title
	 * @param minStartYear the minimum release year of the title
	 * @param maxStartYear the maximum release year of the title
	 * @param minEndYear   the minimum final year of the title
	 * @param maxEndYear   the maximum final year of the title
	 * @param minRuntime   the minimum runtime of the title
	 * @param maxRuntime   the maximum runtime of the title
	 * @param minRating    the minimum rating of the title
	 * @param maxRating    the maximum rating of the title
	 * @param adult        a flag indicating if the title contains adult content
	 * @param job          the job type that was performed
	 * @param character    the character that was played
	 * @return the list of people-title jobs involving the given person on titles
	 *         that satisfy the filters
	 */
	public ArrayList<Job> personJobSearch(Person person, String type, String genre, Integer minStartYear,
			Integer maxStartYear, Integer minEndYear, Integer maxEndYear, Integer minRuntime, Integer maxRuntime,
			Double minRating, Double maxRating, Boolean adult, String job, String character, Boolean unique) {
		ArrayList<Job> jobs = null;

		try {
			jobs = pDao.getAllJobs(person, null, type, genre, minStartYear, maxStartYear, minEndYear, maxEndYear,
					minRuntime, maxRuntime, minRating, maxRating, adult, job, character, unique);
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		}
		return jobs;
	}

	/**
	 * Accesses the Database to get all the titles with a title name that contains a
	 * particular search string. Returns an empty ArrayList in case of failure. All
	 * unnecessary filters in the function call should be set to null.
	 *
	 * @param title        the search string for the title name
	 * @param type         the type of title that was worked on
	 * @param genre        the genre of the title
	 * @param minStartYear the minimum release year of the title
	 * @param maxStartYear the maximum release year of the title
	 * @param minEndYear   the minimum final year of the title
	 * @param maxEndYear   the maximum final year of the title
	 * @param minRuntime   the minimum runtime of the title
	 * @param maxRuntime   the maximum runtime of the title
	 * @param minRating    the minimum rating of the title
	 * @param maxRating    the maximum rating of the title
	 * @param adult        a flag indicating if the title contains adult content
	 * @return the titles matching the search string and the applicable filters
	 */
	public ArrayList<Title> getTitleInfo(String title, String type, String genre, Integer minStartYear,
			Integer maxStartYear, Integer minEndYear, Integer maxEndYear, Integer minRuntime, Integer maxRuntime,
			Double minRating, Double maxRating, Boolean adult) {
		ArrayList<Title> titles = null;

		try {
			titles = tDao.getTitles(title, type, genre, minStartYear, maxStartYear, minEndYear, maxEndYear, minRuntime,
					maxRuntime, minRating, maxRating, adult);
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		}
		return titles;
	}

	/**
	 * Accesses the Database to get all the titles with a title name that contains a
	 * particular search string. Returns an empty ArrayList in case of failure. All
	 * unnecessary filters in the function call should be set to null.
	 *
	 * @param titleName    the title name
	 * @param genre        the genre of the title
	 * @param minStartYear the minimum release year of the title
	 * @param maxStartYear the maximum release year of the title
	 * @param minEndYear   the minimum final year of the title
	 * @param maxEndYear   the maximum final year of the title
	 * @param minRuntime   the minimum runtime of the title
	 * @param maxRuntime   the maximum runtime of the title
	 * @param minRating    the minimum rating of the title
	 * @param maxRating    the maximum rating of the title
	 * @param adult        a flag indicating if the title contains adult content
	 * @param season       the particular season number to retrieve
	 * @param episode      a particular episode number to retrieve
	 * @return the titles matching the search string and the applicable filters
	 */
	public ArrayList<Title> getTitleEpisodeInfo(String titleName, String genre, Integer minStartYear,
			Integer maxStartYear, Integer minEndYear, Integer maxEndYear, Integer minRuntime, Integer maxRuntime,
			Double minRating, Double maxRating, Boolean adult, Integer season, Integer episode) {
		ArrayList<Title> titles = new ArrayList<Title>();
		ArrayList<Title> episodes = new ArrayList<Title>();

		if (titleName == null) {
			return titles;
		}

		try {
			// Assume the title type is TV Series, find all titles that match the applicable
			// filters
			titles = tDao.getTitles(titleName, "tvSeries", genre, minStartYear, maxStartYear, minEndYear, maxEndYear,
					minRuntime, maxRuntime, minRating, maxRating, adult);

			for (Title title : titles) {
				// Get all episodes for each title
				episodes.addAll(tDao.getEpisodesByShow(title, season, episode));
			}
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		}
		return episodes;
	}

	/**
	 * Find information about all people in the database involved in the production
	 * of a particular title that match the applicable filters.
	 *
	 * @param title        the title
	 * @param profession   a primary profession of the cast member
	 * @param minBirthYear the minimum birth year of the cast member
	 * @param maxBirthYear the maximum birth year of the cast member
	 * @param minDeathYear the minimum death year of the cast member
	 * @param maxDeathYear the maximum death year of the cast member
	 * @param job          the job performed by the cast member
	 * @param character    the character played by the cast member
	 * @return the list of person-title jobs involving the given title
	 */
	public ArrayList<Job> titleCastSearch(Title title, String profession, Integer minBirthYear, Integer maxBirthYear,
			Integer minDeathYear, Integer maxDeathYear, String job, String character) {
		ArrayList<Job> jobs = null;

		try {
			jobs = tDao.getAllJobs(title, null, profession, minBirthYear, maxBirthYear, minDeathYear, maxDeathYear, job,
					character);
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		}

		return jobs;
	}

	/**
	 * Finds all people (actors or directors) that had titles released every year in
	 * a given range.
	 *
	 * @param actor   a flag indicating whether to search for actors or directors
	 * @param minYear the minimum year in the range
	 * @param maxYear the maximum year in the range
	 * @return a list of the people title links
	 */
	public ArrayList<PersonTitleLink> getPeopleTitlesByYear(Boolean actor, Integer minYear, Integer maxYear) {
		ArrayList<PersonTitleLink> return_val = new ArrayList<PersonTitleLink>();

		try {
			PreparedStatement stmt;

			// Get all actors/directors titles that fall in the given year range
			if (actor) {
				stmt = conn.prepareStatement(
						"SELECT * FROM actorsByYear WHERE year >= ? AND year <= ? ORDER BY actorID, year");
			} else {
				stmt = conn.prepareStatement(
						"SELECT * FROM directorsByYear WHERE year >= ? AND year <= ? ORDER BY directorID, year");
			}

			stmt.setInt(1, minYear);
			stmt.setInt(2, maxYear);

			ResultSet results;
			results = stmt.executeQuery();

			// Loop through the results
			while (results.next()) {
				String personID = "";

				if (actor) {
					personID = results.getString("actorID");
				} else {
					personID = results.getString("directorID");
				}

				return_val.add(new PersonTitleLink(personID, results.getString("titleID"), results.getInt("year")));
			}
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		}

		return return_val;
	}

	/**
	 * returns a String containing all non-null Person information in a legible
	 * printable format.
	 *
	 * @param person       the person
	 * @param findKnownFor a flag indicating whether or not the function should get
	 *                     information about the knownFor attribute for the person
	 *                     including titles and roles on the title
	 * @return a printable String containing all non-null person information
	 */
	public String getPersonString(Person person, Boolean findKnownFor) {
		String return_val = "";

		// Add the person's name
		return_val += person.getName();

		// Add birthyear - deathyear if the birthyear is non-null
		if (person.getBirthYear() != 0) {
			return_val += " (" + person.getBirthYear();

			if (person.getDeathYear() != 0) {
				return_val += " - " + person.getDeathYear() + ")";
			} else {
				return_val += " - Present)";
			}
		}

		// Add primary professions if the array is non-null and has non-empty length
		if (person.getProfessions() != null) {
			if (person.getProfessions()[0].length() > 0)
				return_val += "\nPrimary professions: "
						+ Arrays.toString(person.getProfessions()).replaceAll("[\\[\\]]", "").replaceAll("_", " ");
		}

		// Add the title names of all the titles the person is known for if the knownFor
		// field is non-null and findKnownFor is true
		if (findKnownFor) {
			if (person.getKnownFor() != null) {
				return_val += "\nKnown for:";

				String knownFor[] = person.getKnownFor();

				for (int i = 0; i < knownFor.length; i++) {
					// For each knownFor title, get the title name and the category of job
					// the actor performed on the title
					try {
						Title title = tDao.getTitleByID(knownFor[i]);

						if (title != null) {
							return_val += "\n\t" + title.getTitle();

							// Find the roles of the person on the title
							ArrayList<Job> jobs = pDao.getJobs(person, title);

							if (jobs.size() != 0) {
								return_val += " (";

								for (int j = 0; j < jobs.size(); j++) {
									if (jobs.get(j).getCategory() != null)
										return_val += jobs.get(j).getCategory();
									if (j != jobs.size() - 1)
										return_val += ", ";
								}

								return_val += ")";
							}
						}
					} catch (SQLException e) {
						System.err.println(e.getClass().getName() + ": " + e.getMessage());
						e.printStackTrace();
						System.exit(-1);
					}
				}
			}
		}
		return_val += "\n\n";

		// Return the string
		return return_val;
	}

	/**
	 * returns a String containing all non-null Title information in a legible
	 * printable format.
	 *
	 * @param title     the title
	 * @param findStaff a flag indicating whether or not the function should get
	 *                  information about the directors and writers attributes for
	 *                  the title
	 * @return a printable String containing all non-null title information
	 */
	public String getTitleString(Title title, Boolean findStaff) {
		String return_val = "";

		// check if the title is a TV episode
		if (title.getType() != null) {
			String type = title.getType();
			// If the title is a TV episode, get information about parent show
			if (type.equals("tvEpisode")) {
				try {
					Title parentTitle = tDao.getShowByEpisodeID(title.getTitleID());
					if (parentTitle != null) {
						return_val += parentTitle.getTitle();
						return_val += " S" + String.format("%02d", title.getSeason()) + "E"
								+ String.format("%02d", title.getEpisode());
						return_val += "\n";
					}
				} catch (SQLException e) {
					System.err.println(e.getClass().getName() + ": " + e.getMessage());
					e.printStackTrace();
					System.exit(-1);
				}
			}
		}

		// Add the title's name
		return_val += title.getTitle();

		// Add startyear - endyear if the startyear is non-null
		if (title.getStartYear() != 0) {
			return_val += " (" + title.getStartYear();

			if (title.getEndYear() != 0) {
				return_val += " - " + title.getEndYear();
			}
			return_val += ")";
		}

		if (title.getAdult() != null) {
			if (title.getAdult()) {
				return_val += " (adult)";
			}
		}

		if (title.getOriginalTitle() != null && !title.getOriginalTitle().equalsIgnoreCase(title.getTitle())) {
			return_val += "\nOriginal title: " + title.getOriginalTitle();
		}

		// Add title type if the type is non-null and translate camelCase types to Title
		// Case (and capitalize 'TV')
		if (title.getType() != null && title.getType() != "tvEpisode") {
			String type = title.getType().replaceAll(String.format("%s", "(?<=[^A-Z])(?=[A-Z])"), " ").replaceAll("tv",
					"TV");
			return_val += "\nType: " + type.substring(0, 1).toUpperCase() + type.substring(1);
		}

		if (title.getRuntime() != 0) {
			return_val += "\nRuntime: " + title.getRuntime() + " minutes";
		}

		if (title.getRating() != 0) {
			return_val += "\nRating: " + title.getRating();
		}

		// Add genres if the array is non-null and has non-empty length
		if (title.getGenres() != null) {
			if (title.getGenres()[0].length() > 0)
				return_val += "\nGenres: " + Arrays.toString(title.getGenres()).replaceAll("[\\[\\]]", "");
		}

		// Add the writer and director names to the title if the writers or directors
		// field is non-null and findStaff is true
		if (findStaff) {
			try {
				if (title.getDirectors() != null) {
					String directors[] = title.getDirectors();
					return_val += "\nDirectors: ";

					for (int i = 0; i < directors.length; i++) {
						// For each director ID, get the person name
						Person director = pDao.getPersonByID(directors[i]);
						if (director != null) {
							return_val += director.getName() + ((i == directors.length - 1) ? "" : ", ");
						}
					}
				}

				if (title.getWriters() != null) {
					String writers[] = title.getWriters();
					return_val += "\nWriters: ";

					for (int i = 0; i < writers.length; i++) {
						// For each writer ID, get the person name
						Person writer = pDao.getPersonByID(writers[i]);
						if (writer != null) {
							return_val += writer.getName() + ((i == writers.length - 1) ? "" : ", ");
						}
					}
				}
			} catch (SQLException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				e.printStackTrace();
				System.exit(-1);
			}
		}
		return_val += "\n\n";

		// Return the string
		return return_val;
	}

	/**
	 * returns a String containing all non-null Job information in a legible
	 * printable format.
	 *
	 * @param job the job
	 * @return a printable String containing all non-null job information
	 */
	public String getJobString(Job job) {
		String return_val = "";

		if (job.getTitle() == null || job.getPerson() == null) {
			return "Warning: job contained null person or title";
		}
		return_val += job.getPerson().getName() + " | " + job.getTitle().getTitle();

		if (job.getTitle().getType() != null) {
			return_val += "\nType: " + job.getTitle().getType().substring(0, 1).toUpperCase()
					+ job.getTitle().getType().substring(1);
		}

		if (job.getCategory() != null) {
			return_val += "\nJob: " + job.getCategory().substring(0, 1).toUpperCase() + job.getCategory().substring(1);
			if (job.getJob() != null) {
				return_val += " (" + job.getJob() + ")";
			}
		}

		if (job.getCharacters() != null) {
			return_val += "\nCharacter(s) played: " + Arrays.toString(job.getCharacters()).replaceAll("[\\[\\]]", "");
		}
		return_val += "\n\n";

		// Return the string
		return return_val;
	}

	/**
	 * Expands an Actor node in a multi-graph by dynamically finding all actors
	 * appearing in titles with the current actor.
	 *
	 * @param person the node to expand
	 * @param target the target person (used for calculating heuristic values)
	 */
	public void setConnectedPeople(ActorNode person, Person target) {
		ArrayList<ActorNode> links = new ArrayList<ActorNode>();

		try {
			// Create a prepared statement object
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ActorLinks WHERE p1id = ? or p2id = ?");

			// Fill in information
			stmt.setString(1, person.getID());
			stmt.setString(2, person.getID());

			// Send statement to DBMS
			ResultSet result = stmt.executeQuery();

			// Collect output
			while (result.next()) {
				Integer priority = 0;

				if (result.getString("p1id").equals(person.getID())) {
					if (!result.getString("p2id").equals(target.getPersonID()))
						priority = 10000 * (person.getDepth() + 1);

					if (target.getBirthYear() != 0) {
						priority += Math.abs(target.getBirthYear() - result.getInt("p2birthyear"));
					}
					links.add(new ActorNode(result.getString("p2id"), person, result.getString("tid"),
							person.getDepth() + 1, priority));
				} else {
					if (!result.getString("p1id").equals(target.getPersonID()))
						priority = 10000 * (person.getDepth() + 1);

					if (target.getBirthYear() != 0) {
						priority += Math.abs(target.getBirthYear() - result.getInt("p1birthyear"));
					}
					links.add(new ActorNode(result.getString("p1id"), person, result.getString("tid"),
							person.getDepth() + 1, priority));
				}
			}
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		}
		person.setChildren(links);
	}
}
