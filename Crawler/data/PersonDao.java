1
https://raw.githubusercontent.com/Luke-Grammer/IMDB-Database-Application/master/src/data/dao/PersonDao.java
/*
 * PersonDao.java
 * Luke Grammer, Lauren Haylock, and Davis Beilue
 * CSCE 315-907
 * 10/15/2019
 */

package data.dao;

import data.model.*;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The Interface PersonDao specifies useful functions for accessing information
 * about particular people to be used by the Database Manager.
 */
public interface PersonDao {

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
	 * @param unique       flag for determining whether to return all matching
	 *                     values or the first matching value
	 * @return the list of people with names matching the search string and
	 *         applicable filters
	 * @throws SQLException when an exception is handled processing the database
	 *                      query
	 */
	public ArrayList<Person> getPeople(String name, String profession, Integer minBirthYear, Integer maxBirthYear,
			Integer minDeathYear, Integer maxDeathYear, Boolean unique) throws SQLException;

	/**
	 * Accesses the database and returns a particular person specified by their
	 * personID. Returns null to indicate failure.
	 *
	 * @param ID the unique identifier of the person
	 * @return the person with the corresponding ID or null
	 * @throws SQLException when an exception is handled processing the database
	 *                      query
	 */
	public Person getPersonByID(String ID) throws SQLException;

	/**
	 * Accesses the Database to get all jobs performed by a particular person (with
	 * matching personID) on titles that match the given title filters. Returns an
	 * empty ArrayList if the person did not have any jobs.
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
			Double minRating, Double maxRating, Boolean adult, String job, String character, Boolean unique) throws SQLException;

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
	public ArrayList<Job> getJobs(Person person, Title title) throws SQLException;

}
