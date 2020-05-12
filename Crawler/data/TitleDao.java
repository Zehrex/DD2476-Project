1
https://raw.githubusercontent.com/Luke-Grammer/IMDB-Database-Application/master/src/data/dao/TitleDao.java
/*
 * TitleDao.java
 * Luke Grammer, Lauren Haylock, and Davis Beilue
 * CSCE 315-907
 * 10/15/2019
 */

package data.dao;

import data.model.*;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The Interface TitleDao specifies useful functions for accessing information
 * about particular titles to be used by the Database Manager.
 */
public interface TitleDao {

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
			Double minRating, Double maxRating, Boolean adult) throws SQLException;

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
	public ArrayList<Title> getEpisodesByShow(Title title, Integer season, Integer episode) throws SQLException;

	/**
	 * Accesses the database and returns a particular title specified by their
	 * titleID. Returns null to indicate failure.
	 *
	 * @param ID the unique identifier for the title
	 * @return the title with the corresponding ID value
	 * @throws SQLException when an exception is handled processing the database
	 *                      query
	 */
	public Title getTitleByID(String ID) throws SQLException;

	/**
	 * Accesses the database and returns information about the parent TV show
	 * specified by a titleID corresponding to a TV Episode. Returns null to
	 * indicate failure.
	 *
	 * @param ID the unique identifier of the episode
	 * @return the unique identifier of the parent title of the episode
	 * @throws SQLException when an exception is handled processing the database
	 *                      query
	 */
	public Title getShowByEpisodeID(String ID) throws SQLException;

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
			throws SQLException;
}
