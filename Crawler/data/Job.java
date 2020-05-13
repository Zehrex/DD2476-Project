1
https://raw.githubusercontent.com/Luke-Grammer/IMDB-Database-Application/master/src/data/model/Job.java
/*
 * Job.java
 * Luke Grammer, Lauren Haylock, and Davis Beilue
 * CSCE 315-907
 * 10/15/2019
 */

package data.model;

/**
 * Job class to store person-title relationships retrieved from the database.
 */
public class Job {

	/** Title that the job corresponds with. */
	private Title title;

	/** Person that the job corresponds with. */
	private Person person;

	/** The job category (ex: 'actor', 'actress', 'director', ect...). */
	private String category;

	/** The specific job (often null). */
	private String job;

	/** The character(s) played (if any). */
	private String[] characters;

	/**
	 * Instantiates a new job.
	 *
	 * @param title      the title
	 * @param person     the person
	 * @param category   the job category
	 * @param job        the specific job
	 * @param characters the characters played (if any)
	 */
	public Job(Title title, Person person, String category, String job, String[] characters) {
		this.title = title;
		this.person = person;
		this.category = category;
		this.job = job;
		this.characters = characters;
	}

	/**
	 * Gets the title associated with the job.
	 *
	 * @return the title
	 */
	public Title getTitle() {
		return title;
	}

	/**
	 * Sets the title associated with the job.
	 *
	 * @param title the new title ID
	 */
	public void setTitleID(Title title) {
		this.title = title;
	}

	/**
	 * Gets the person associated with the job.
	 *
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * Sets the person associated with the job.
	 *
	 * @param person the new person
	 */
	public void setPerson(Person person) {
		this.person = person;
	}

	/**
	 * Gets the job category.
	 *
	 * @return the job category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Sets the job category.
	 *
	 * @param category the new job category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Gets the specific job performed.
	 *
	 * @return the job
	 */
	public String getJob() {
		return job;
	}

	/**
	 * Sets the specific job performed.
	 *
	 * @param job the new job
	 */
	public void setJob(String job) {
		this.job = job;
	}

	/**
	 * Gets the characters played.
	 *
	 * @return the characters played
	 */
	public String[] getCharacters() {
		return characters;
	}

	/**
	 * Sets the characters played.
	 *
	 * @param characters the new characters played
	 */
	public void setCharacters(String[] characters) {
		this.characters = characters;
	}
}
