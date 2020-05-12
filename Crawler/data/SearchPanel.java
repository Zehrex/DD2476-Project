1
https://raw.githubusercontent.com/Luke-Grammer/IMDB-Database-Application/master/src/UI/SearchPanel.java
/*
 * SearchPanel.java
 * Luke Grammer, Lauren Haylock, and Davis Beilue
 * CSCE 315-907
 * 10/15/2019
 */

package UI;

import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.awt.*;
import javax.swing.*;

import data.DatabaseManager;
import data.model.*;
import file.FileManager;

/**
 * The SearchPanel class is the panel that implements functionality to search
 * the database in order to retrieve specific results about people, titles, or
 * episodes. Supports various filters to narrow down results for various
 * different types of queries
 */
public class SearchPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	/** The text field introducing the 'search by' drop down. */
	JLabel SearchBy = new JLabel("Search By:");

	/** The strings of different options the user is able to search by. */
	String searchOptions[] = { "", "People", "Titles" };

	/**
	 * The drop down menu containing the various options the user is able to search
	 * by.
	 */
	JComboBox<String> searchDropDown = new JComboBox<String>(searchOptions);

	/**
	 * The text field introducing the different query types for the selected 'search
	 * by' category.
	 */
	JLabel wantToKnow = new JLabel("I want to know:");

	/** The strings of different query types the user is able to perform. */
	String wantToKnowOptions[] = { "" };

	/**
	 * The drop down menu containing the various query types the user is able to
	 * perform.
	 */
	JComboBox<String> wantDropDown = new JComboBox<String>(wantToKnowOptions);

	/** The text field introducing the query filters section. */
	JLabel filter = new JLabel("Filters");

	/**
	 * The text field introducing the user input field for person name to search.
	 */
	JLabel personName = new JLabel("Person Name(s):");

	/** The user input for the name of the person to search. */
	JTextField personNameInput = new JTextField(20);

	/** the text field introducing the person profession drop-down. */
	JLabel personProfession = new JLabel("Profession:");

	/** The strings for particular people professions to query. */
	String professionOptions[] = { "", "Actor", "Actress", "Director", "Writer", "Producer", "Composer", "Soundtrack",
			"Cinematographer" };

	/** The drop down containing different profession options. */
	JComboBox<String> professionDropDown = new JComboBox<String>(professionOptions);

	/** The text field introducing the person birth year filters. */
	JLabel personBirth = new JLabel("Birth Year between");

	/** The user input field for the minimum birth year filter. */
	JTextField birthYearMin = new JTextField(20);

	/** The connective text field between the birth year user input fields. */
	JLabel birthAnd = new JLabel("and");

	/** The user input field for the maximum birth year filter. */
	JTextField birthYearMax = new JTextField(20);

	/** The text field introducing the person death year filters. */
	JLabel personDeath = new JLabel("Death Year between");

	/** The user input field for the minimum death year filter. */
	JTextField deathYearMin = new JTextField(20);

	/** The connective text field between the death year user input fields. */
	JLabel deathAnd = new JLabel("and");

	/** The user input field for the maximum death year filter. */
	JTextField deathYearMax = new JTextField(20);

	/**
	 * The text field introducing the title input field for title name to search.
	 */
	JLabel titleName = new JLabel("Title Name(s):");

	/** The user input for the name of the title to search. */
	JTextField titleNameInput = new JTextField(20);

	/** the text field introducing the title type drop-down. */
	JLabel titleType = new JLabel("Type:");

	/** The strings for particular title types to query. */
	String typeOptions[] = { "", "Movie", "TV Series", "Short", "Video Game", "TV Special", "TV Short", "TV Episode",
			"Video", "TV Movie", "TV Miniseries" };

	/** The drop down containing different type options. */
	JComboBox<String> typeDropDown = new JComboBox<String>(typeOptions);

	/** The text field introducing the adult film feature. */
	JLabel titleAdult = new JLabel("Adult:");

	/** The strings representing the different adult film status types. */
	String adultOptions[] = { "", "True", "False" };

	/**
	 * The drop down menu containing the different filters for adult film status.
	 */
	JComboBox<String> adultDropDown = new JComboBox<String>(adultOptions);

	/** The text field introducing the title start year filter. */
	JLabel titleStart = new JLabel("Start Year between");

	/** The user input field for the minimum start year filter. */
	JTextField startYearMin = new JTextField(20);

	/** The connective text field between the start year user input fields. */
	JLabel startYearAnd = new JLabel("and");

	/** The user input field for the maximum start year filter. */
	JTextField startYearMax = new JTextField(20);

	/** The text field introducing the title end year filter. */
	JLabel titleEnd = new JLabel("End Year between");

	/** The user input field for the minimum end year filter. */
	JTextField endYearMin = new JTextField(20);

	/** The connective text field between the end year user input fields. */
	JLabel endYearAnd = new JLabel("and");

	/** The user input field for the maximum end year filter. */
	JTextField endYearMax = new JTextField(20);

	/** The text field introducing the title run time filter. */
	JLabel titleRuntime = new JLabel("Runtime between");

	/** The connective text field between the run time user input fields. */
	JLabel runtimeAnd = new JLabel("and");

	/** The user input field for the minimum run time filter. */
	JTextField runtimeMin = new JTextField(20);

	/** The user input field for the maximum run time filter. */
	JTextField runtimeMax = new JTextField(20);

	/** The text field introducing the title rating filter. */
	JLabel titleRating = new JLabel("Rating between");

	/** The connective text field between the rating user input fields. */
	JLabel ratingAnd = new JLabel("and");

	/** The user input field for the minimum rating filter. */
	JTextField ratingMin = new JTextField(20);

	/** The user input field for the maximum rating filter. */
	JTextField ratingMax = new JTextField(20);

	/** The text field introducing the title season filter. */
	JLabel seasonText = new JLabel("Season:");

	/** The user input field for the title season filter. */
	JTextField seasonInput = new JTextField(20);

	/** The text field introducing the title episode filter. */
	JLabel episodeText = new JLabel("Episode:");

	/** The user input field for the title episode filter. */
	JTextField episodeInput = new JTextField(20);

	/** The text field introducing the actor-title character played filter */
	JLabel characterText = new JLabel("Character:");

	/** The user input for the actor-title character played filter. */
	JTextField characterInput = new JTextField(20);

	/** The search button. */
	JButton searchButton = new JButton("Search");

	/** The horizontal divider separating the menu options from the results. */
	JSeparator resultsSeparator = new JSeparator();

	/** The label introducing the results. */
	JLabel resultsLabel = new JLabel("Results:");

	/** The text field used to display results. */
	JTextArea results = new JTextArea("");

	/** The label for output if there are too many results to display */
	JLabel outputLabel = new JLabel(
			"There are too many results to display. Please enter a file name to send the results to:");

	/** The user input field for output filename. */
	JTextField outputFile = new JTextField(20);

	/** The button to send results to output file. */
	JButton outputButton = new JButton("Send");

	/** The label indicating the status of the data transfer to the output file. */
	JLabel outputDone = new JLabel("Done!");

	/** The string to store results before being written to an output file. */
	String finalResults = ""; // string to save results

	/** Status flag is true if no query type is selected. */
	boolean noSearch;

	/** Status flag is true if person query type is selected. */
	boolean pSearch;

	/** Status flag is true if person general info category is selected. */
	boolean pGenInfo;

	/** Status flag is true if person related titles category is selected. */
	boolean pRelTitles;

	/** Status flag is true if person jobs category is selected. */
	boolean pJobs;

	/** Status flag is true if title query type is selected. */
	boolean tSearch;

	/** Status flag is true if title general information category is selected. */
	boolean tGenInfo;

	/** Status flag is true if title related people category is selected. */
	boolean tRelPeople;

	/** Status flag is true if title episodes category is selected. */
	boolean tEpisodes;

	/** An instantiation of the Database manager. */
	DatabaseManager manager;

	/**
	 * Constructor for Search panel that adds and positions all the UI elements on
	 * the screen.
	 *
	 * @param manager an existing instance of a DatabaseManager
	 */
	@SuppressWarnings("unchecked")
	SearchPanel(DatabaseManager passedManager) {
		manager = passedManager;

		this.setBounds(500, 500, 500, 500);
		this.setLayout(null);

		this.add(SearchBy);
		SearchBy.setBounds(20, 20, 100, 20);

		this.add(searchDropDown);
		searchDropDown.setBounds(120, 20, 100, 20);
		searchDropDown.addActionListener(this);

		this.add(wantToKnow);
		wantToKnow.setBounds(20, 50, 90, 20);

		this.add(wantDropDown);
		wantDropDown.setBounds(120, 50, 140, 20);
		wantDropDown.addActionListener(this);

		this.add(resultsLabel);

		// Set vertical layout
		resultsSeparator.setOrientation(SwingConstants.HORIZONTAL);
		resultsSeparator.setBounds(20, 450, 535, 200);
		this.add(resultsSeparator);
		resultsLabel.setBounds(20, 430, 150, 20);
		this.add(results);
		results.setBounds(20, 455, 535, 280);
		results.setBackground(this.getBackground());
		results.setLineWrap(true);
		results.setEditable(false);

		this.add(outputLabel);
		outputLabel.setBounds(20, 380, 540, 20);
		this.add(outputFile);
		outputFile.setBounds(20, 405, 200, 20);
		outputFile.setEditable(true);
		this.add(outputButton);
		outputButton.setBounds(240, 405, 100, 20);
		outputButton.addActionListener(this);
		this.add(outputDone);
		outputDone.setBounds(360, 405, 100, 20);
		outputDone.setVisible(false);
		setOutputFileVisible(false, 0);

		this.add(filter);
		filter.setVisible(false);
		filter.setBounds(20, 90, 60, 20);
		Font font = filter.getFont();

		// getAttributes for Swing Font object requires use of raw map type
		@SuppressWarnings("rawtypes")
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		filter.setFont(font.deriveFont(attributes));

		searchButton.addActionListener(this);
	}

	/**
	 * Resets user input text options for people and shows/hides people UI elements
	 * according to the boolean 'show'.
	 *
	 * @param show is used to determine whether to show/hide the UI elements for
	 *             people
	 */
	public void setPersonSectionVisible(Boolean show) {
		personName.setVisible(show);
		personNameInput.setVisible(show);
		personNameInput.setText("");

		personProfession.setVisible(show);
		professionDropDown.setVisible(show);
		professionDropDown.setSelectedItem("");

		personBirth.setVisible(show);
		birthYearMin.setVisible(show);
		birthYearMin.setText("");
		birthAnd.setVisible(show);
		birthYearMax.setVisible(show);
		birthYearMax.setText("");

		personDeath.setVisible(show);
		deathYearMin.setVisible(show);
		deathYearMin.setText("");
		deathAnd.setVisible(show);
		deathYearMax.setVisible(show);
		deathYearMax.setText("");
	}

	/**
	 * Resets user input text options for titles and shows/hides title UI elements
	 * according to the boolean 'show'.
	 *
	 * @param show is used to determine whether to show/hide the UI elements for
	 *             titles
	 */
	public void setTitleSectionVisible(Boolean show) {
		titleName.setVisible(show);
		titleNameInput.setVisible(show);
		titleNameInput.setText("");

		titleType.setVisible(show);
		typeDropDown.setVisible(show);
		typeDropDown.setSelectedItem("");

		titleAdult.setVisible(show);
		adultDropDown.setVisible(show);
		adultDropDown.setSelectedItem("");

		titleStart.setVisible(show);
		startYearMin.setVisible(show);
		startYearMin.setText("");
		startYearAnd.setVisible(show);
		startYearMax.setVisible(show);
		startYearMax.setText("");

		titleEnd.setVisible(show);
		endYearMin.setVisible(show);
		endYearMin.setText("");
		endYearAnd.setVisible(show);
		endYearMax.setVisible(show);
		endYearMax.setText("");

		titleRating.setVisible(show);
		ratingMin.setVisible(show);
		ratingMin.setText("");
		ratingAnd.setVisible(show);
		ratingMax.setVisible(show);
		ratingMax.setText("");

		titleRuntime.setVisible(show);
		runtimeMin.setVisible(show);
		runtimeMin.setText("");
		runtimeAnd.setVisible(show);
		runtimeMax.setVisible(show);
		runtimeMax.setText("");
	}

	/**
	 * Resets user input text options for episodes and shows/hides episode UI
	 * elements according to the boolean 'show'.
	 *
	 * @param show is used to determine whether to show/hide the UI elements for
	 *             episodes
	 */
	public void setEpisodeSectionVisible(Boolean show) {
		seasonText.setVisible(show);
		seasonInput.setVisible(show);
		seasonInput.setText("");

		episodeText.setVisible(show);
		episodeInput.setVisible(show);
		episodeInput.setText("");
	}

	/**
	 * Resets user input text options for jobs and shows/hides job UI elements
	 * according to the boolean 'show'.
	 *
	 * @param show is used to determine whether to show/hide the UI elements for
	 *             jobs
	 */
	public void setJobSectionVisible(Boolean show) {
		characterText.setVisible(show);

		characterInput.setVisible(show);
		characterInput.setText("");
	}

	/**
	 * Resets user input text options for file I/O and shows/hides file UI elements
	 * according to the boolean 'show'.
	 *
	 * @param show       is used to determine whether to show/hide the UI elements
	 *                   for writing to output files
	 * @param numResults if 'show' is true, this shows the number of results that
	 *                   were returned by the search
	 */
	public void setOutputFileVisible(Boolean show, Integer numResults) {
		results.setText("");
		outputLabel
				.setText("Search returned " + numResults + " results. Please enter a filename to send the results to:");
		outputLabel.setVisible(show);
		outputFile.setVisible(show);
		outputButton.setVisible(show);
	}

	/**
	 * Writes the contents of finalResults to an output file specified by the text
	 * field 'outputFile'.
	 */
	public void writeFile() {
		try {
			FileManager fManager = new FileManager(outputFile.getText());
			fManager.writeToFile(finalResults);
			fManager.close();
			outputDone.setVisible(true);
		} catch (IOException e) {
			outputLabel.setText("Output failed! There was an exception writing to the specified file");
		}
	}

	/**
	 * This method runs when a UI event occurs, REPL.
	 *
	 * @param e the action performed
	 */
	public void actionPerformed(ActionEvent e) {
		// check if the source of the UI event was the search button
		if (e.getSource() == searchButton) {
			results.setText("Collecting results, please wait...");
			// Execute query in a new thread to show status text
			Thread t = new Thread() {
				@Override
				public void run() {
					// Initialize query and filter variables to be used in the database manager
					// functions
					String name = ((personNameInput.getText().trim().equals("")) ? null
							: personNameInput.getText().toLowerCase().trim());
					String title = ((titleNameInput.getText().trim().equals("")) ? null
							: titleNameInput.getText().toLowerCase().trim());
					String profession = ((professionDropDown.getItemAt(professionDropDown.getSelectedIndex())
							.equals("")) ? null
									: professionDropDown.getItemAt(professionDropDown.getSelectedIndex())
											.toLowerCase());
					Integer minBirthYear = ((birthYearMin.getText().trim().equals("")) ? null
							: Integer.parseInt(birthYearMin.getText().trim()));
					Integer maxBirthYear = ((birthYearMax.getText().trim().equals("")) ? null
							: Integer.parseInt(birthYearMax.getText().trim()));
					Integer minDeathYear = ((deathYearMin.getText().trim().equals("")) ? null
							: Integer.parseInt(deathYearMin.getText().trim()));
					Integer maxDeathYear = ((deathYearMax.getText().trim().equals("")) ? null
							: Integer.parseInt(deathYearMax.getText().trim()));
					String type = ((typeDropDown.getItemAt(typeDropDown.getSelectedIndex()) == "") ? null
							: typeDropDown.getItemAt(typeDropDown.getSelectedIndex()));

					// Genre compatibility added in back-end, no UI support ATM
					// String genre = ((genreInput.getText().trim().equals("")) ? null :
					// genreInput.getText().trim());
					String genre = null;

					Integer minStartYear = ((startYearMin.getText().trim().equals("")) ? null
							: Integer.parseInt(startYearMin.getText().trim()));
					Integer maxStartYear = ((startYearMax.getText().trim().equals("")) ? null
							: Integer.parseInt(startYearMax.getText().trim()));
					Integer minEndYear = ((endYearMin.getText().trim().equals("")) ? null
							: Integer.parseInt(endYearMin.getText().trim()));
					Integer maxEndYear = ((endYearMax.getText().trim().equals("")) ? null
							: Integer.parseInt(endYearMax.getText().trim()));
					Integer minRuntime = ((runtimeMin.getText().trim().equals("")) ? null
							: Integer.parseInt(runtimeMin.getText().trim()));
					Integer maxRuntime = ((runtimeMax.getText().trim().equals("")) ? null
							: Integer.parseInt(runtimeMax.getText().trim()));
					Double minRating = ((ratingMin.getText().trim().equals("")) ? null
							: Double.parseDouble(ratingMin.getText().trim()));
					Double maxRating = ((ratingMax.getText().trim().equals("")) ? null
							: Double.parseDouble(ratingMax.getText().trim()));
					Boolean adult = ((adultDropDown.getItemAt(adultDropDown.getSelectedIndex()).trim().equals(""))
							? null
							: Boolean.parseBoolean(
									adultDropDown.getItemAt(adultDropDown.getSelectedIndex()).toLowerCase().trim()));
					Integer season = ((seasonInput.getText().trim().equals("")) ? null
							: Integer.parseInt(seasonInput.getText().trim()));
					Integer episode = ((episodeInput.getText().trim().equals("")) ? null
							: Integer.parseInt(episodeInput.getText().trim()));
					String character = ((characterInput.getText().trim().equals("")) ? null
							: characterInput.getText().toLowerCase().trim());

					if (pGenInfo) {
						// print all info about the people who fit the given criteria
						ArrayList<Person> people = manager.getPersonInfo(name, profession, minBirthYear, maxBirthYear,
								minDeathYear, maxDeathYear, false);

						String return_val = "";
						for (Person person : people) {
							return_val += manager.getPersonString(person, true);
						}

						if (!return_val.equals("")) {
							if (people.size() > 5) {
								// show output to file result if there are too many people returned
								finalResults = return_val;
								setOutputFileVisible(true, people.size());
							} else {
								results.setText(return_val);
							}
						} else {
							results.setText("No results!");
						}
					} else if (pRelTitles) {
						// prints all the titles a given person worked on that fit the given criteria
						// for people and titles
						ArrayList<Title> titles = manager.getPersonTitles(name, title, profession, minBirthYear,
								maxBirthYear, minDeathYear, maxDeathYear, type, genre, minStartYear, maxStartYear,
								minEndYear, maxEndYear, minRuntime, maxRuntime, minRating, maxRating, adult);

						String return_val = "";
						for (Title t : titles) {
							return_val += manager.getTitleString(t, true);
						}

						if (!return_val.equals("")) {
							if (titles.size() > 5) {
								// show output to file result if there are too many titles returned
								finalResults = return_val;
								setOutputFileVisible(true, titles.size());
							} else {
								results.setText(return_val);
							}
						} else {
							results.setText("No results!");
						}
					} else if (pJobs) {
						// print out info about jobs worked on by a particular person
						ArrayList<Person> people = manager.getPersonInfo(name, profession, minBirthYear, maxBirthYear,
								minDeathYear, maxDeathYear, true);

						ArrayList<Job> jobs = new ArrayList<Job>();
						for (Person person : people) {
							jobs.addAll(manager.personJobSearch(person, type, genre, minStartYear, maxStartYear,
									minEndYear, maxEndYear, minRuntime, maxRuntime, minRating, maxRating, adult, null,
									character, false));
						}

						String return_val = "";
						for (Job job : jobs) {
							return_val += manager.getJobString(job);
						}

						if (!return_val.equals("")) {
							if (jobs.size() > 5) {
								// show output to file result if there are too many jobs returned
								finalResults = return_val;
								setOutputFileVisible(true, jobs.size());
							} else {
								results.setText(return_val);
							}
						} else {
							results.setText("No results!");
						}
					} else if (tGenInfo) {
						// print all info about titles that fit the given criteria
						ArrayList<Title> titles = manager.getTitleInfo(title, type, genre, minStartYear, maxStartYear,
								minEndYear, maxEndYear, minRuntime, maxRuntime, minRating, maxRating, adult);

						String return_val = "";

						if (titles == null) {
							return_val = "Enter search criteria";
							results.setText(return_val);
						}

						else {
							for (Title titleName : titles) {
								return_val += manager.getTitleString(titleName, true);
							}

							if (!return_val.equals("")) {
								if (titles.size() > 5) {
									// show output to file result if there are too many titles returned
									finalResults = return_val;
									setOutputFileVisible(true, titles.size());
								} else {
									results.setText(return_val);
								}
							} else {
								results.setText("No results!");
							}

						}
					} else if (tRelPeople) {
						// print all info about jobs worked on the given title by the given person
						ArrayList<Title> titles = manager.getTitleInfo(title, type, genre, minStartYear, maxStartYear,
								minEndYear, maxEndYear, minRuntime, maxRuntime, minRating, maxRating, adult);

						ArrayList<Job> jobs = new ArrayList<Job>();
						ArrayList<Job> tempJobs = new ArrayList<Job>();
						for (Title t : titles) {
							tempJobs.addAll(manager.titleCastSearch(t, profession, minBirthYear, maxBirthYear,
									minDeathYear, maxDeathYear, null, character));
						}

						for (Job job : tempJobs) {
							if (name != null) {
								if (job.getPerson().getName().trim().toLowerCase().equals(name)) {
									jobs.add(job);
								}
							} else {
								jobs.add(job);
							}
						}

						// add every job to the string we will print
						String return_val = "";
						for (Job job : jobs) {
							return_val += manager.getJobString(job);
						}

						// if there were results found
						if (!return_val.equals("")) {
							if (jobs.size() > 5) {
								// show output to file result if there are too many jobs returned
								finalResults = return_val;
								setOutputFileVisible(true, jobs.size());
							} else {
								results.setText(return_val);
							}
						} else {
							results.setText("No results!");
						}
					} else if (tEpisodes) {
						// print out info about episodes with the given ep. number/season and title
						// filters
						ArrayList<Title> titles = manager.getTitleEpisodeInfo(title, genre, minStartYear, maxStartYear,
								minEndYear, maxEndYear, minRuntime, maxRuntime, minRating, maxRating, adult, season,
								episode);

						String return_val = "";
						for (Title titleName : titles) {
							return_val += manager.getTitleString(titleName, true);
						}

						if (!return_val.equals("")) {
							if (titles.size() > 5) {
								// show output to file result if there are too many titles returned
								finalResults = return_val;
								setOutputFileVisible(true, titles.size());
							} else {
								results.setText(return_val);
							}
						} else {
							results.setText("No results!");
						}
					}
				}
			};
			t.start();
		} else if (e.getSource() == outputButton) {
			// send results to file
			writeFile();
		}
		// if it was the search drop down
		else if (e.getSource() == searchDropDown) {
			// clear options for want to know
			if (searchDropDown.getSelectedItem() == "People") {
				noSearch = false;

				pSearch = true;
				pGenInfo = true;
				pRelTitles = false;
				pJobs = false;

				tSearch = false;
				tGenInfo = false;
				tRelPeople = false;
				tEpisodes = false;

				wantDropDown.removeAllItems();
				// add the new options
				wantDropDown.addItem("General Information");
				wantDropDown.addItem("Related Titles");
				wantDropDown.addItem("Job Search");
				// make it editable if not already
			} else if (searchDropDown.getSelectedItem() == "Titles") {
				noSearch = false;

				pSearch = false;
				pGenInfo = false;
				pRelTitles = false;
				pJobs = false;

				tSearch = true;
				tGenInfo = true;
				tRelPeople = false;
				tEpisodes = false;

				// clear all other things

				wantDropDown.removeAllItems();
				wantDropDown.addItem("General Information");
				wantDropDown.addItem("Cast Search");
				wantDropDown.addItem("Episode Search");
			} else {
				noSearch = true;

				pSearch = false;
				pGenInfo = false;
				pRelTitles = false;
				pJobs = false;

				tSearch = false;
				tGenInfo = false;
				tRelPeople = false;
				tEpisodes = false;

				// picked the "" in search
				// clear all other things
				results.setText("");
				setPersonSectionVisible(false);
				setTitleSectionVisible(false);
				setEpisodeSectionVisible(false);
				setJobSectionVisible(false);
				searchButton.setVisible(false);
				outputDone.setVisible(false);
				setOutputFileVisible(false, 0);

				wantDropDown.removeAllItems();
			}
		} else if (e.getSource() == wantDropDown) {
			if (searchDropDown.getSelectedItem() == "People") {
				filter.setVisible(true);

				// set of filters for search by
				this.add(personName);
				personName.setBounds(20, 110, 120, 20);
				this.add(personNameInput);
				personNameInput.setBounds(120, 110, 380, 20);

				this.add(personProfession);
				personProfession.setBounds(20, 140, 120, 20);
				this.add(professionDropDown);
				professionDropDown.setBounds(120, 140, 120, 20);

				this.add(personBirth);
				personBirth.setBounds(260, 140, 150, 20);
				this.add(birthYearMin);
				birthYearMin.setBounds(380, 140, 40, 20);
				this.add(birthAnd);
				birthAnd.setBounds(430, 140, 40, 20);
				this.add(birthYearMax);
				birthYearMax.setBounds(460, 140, 40, 20);

				this.add(characterText);
				characterText.setBounds(20, 170, 120, 20);
				this.add(characterInput);
				characterInput.setBounds(120, 170, 120, 20);

				this.add(personDeath);
				personDeath.setBounds(260, 170, 150, 20);
				this.add(deathYearMin);
				deathYearMin.setBounds(380, 170, 40, 20);
				this.add(deathAnd);
				deathAnd.setBounds(430, 170, 40, 20);
				this.add(deathYearMax);
				deathYearMax.setBounds(460, 170, 40, 20);

				// specify title filters
				this.add(titleName);
				titleName.setBounds(20, 220, 120, 20);
				this.add(titleNameInput);
				titleNameInput.setBounds(120, 220, 380, 20);

				this.add(titleType);
				titleType.setBounds(20, 250, 120, 20);
				this.add(typeDropDown);
				typeDropDown.setBounds(120, 250, 120, 20);

				this.add(titleAdult);
				titleAdult.setBounds(20, 280, 120, 20);
				this.add(adultDropDown);
				adultDropDown.setBounds(120, 280, 120, 20);

				this.add(titleRating);
				titleRating.setBounds(20, 310, 150, 20);
				this.add(ratingMin);
				ratingMin.setBounds(120, 310, 40, 20);
				this.add(ratingAnd);
				ratingAnd.setBounds(170, 310, 40, 20);
				this.add(ratingMax);
				ratingMax.setBounds(200, 310, 40, 20);

				this.add(titleStart);
				titleStart.setBounds(260, 250, 150, 20);
				this.add(startYearMin);
				startYearMin.setBounds(380, 250, 40, 20);
				this.add(startYearAnd);
				startYearAnd.setBounds(430, 250, 40, 20);
				this.add(startYearMax);
				startYearMax.setBounds(460, 250, 40, 20);

				this.add(titleEnd);
				titleEnd.setBounds(260, 280, 150, 20);
				this.add(endYearMin);
				endYearMin.setBounds(380, 280, 40, 20);
				this.add(endYearAnd);
				endYearAnd.setBounds(430, 280, 40, 20);
				this.add(endYearMax);
				endYearMax.setBounds(460, 280, 40, 20);

				this.add(titleRuntime);
				titleRuntime.setBounds(260, 310, 150, 20);
				this.add(runtimeMin);
				runtimeMin.setBounds(380, 310, 40, 20);
				this.add(runtimeAnd);
				runtimeAnd.setBounds(430, 310, 40, 20);
				this.add(runtimeMax);
				runtimeMax.setBounds(460, 310, 40, 20);

				this.add(searchButton);

				if (wantDropDown.getSelectedItem() == "General Information") {
					pGenInfo = true;
					pRelTitles = false;
					pJobs = false;

					searchButton.setBounds(240, 210, 80, 20);
					results.setText("");
					setPersonSectionVisible(true);
					setTitleSectionVisible(false);
					setEpisodeSectionVisible(false);
					setJobSectionVisible(false);
					outputDone.setVisible(false);
					setOutputFileVisible(false, 0);
					searchButton.setVisible(true);
				}

				else if (wantDropDown.getSelectedItem() == "Related Titles") {
					pGenInfo = false;
					pRelTitles = true;
					pJobs = false;

					searchButton.setBounds(240, 350, 80, 20);
					results.setText("");
					setPersonSectionVisible(true);
					setTitleSectionVisible(true);
					setEpisodeSectionVisible(false);
					setJobSectionVisible(false);
					outputDone.setVisible(false);
					setOutputFileVisible(false, 0);
					searchButton.setVisible(true);

				} else if (wantDropDown.getSelectedItem() == "Job Search") {
					pGenInfo = false;
					pRelTitles = false;
					pJobs = true;

					searchButton.setBounds(240, 210, 80, 20);
					results.setText("");
					setPersonSectionVisible(true);
					setTitleSectionVisible(false);
					setEpisodeSectionVisible(false);
					setJobSectionVisible(true);
					outputDone.setVisible(false);
					setOutputFileVisible(false, 0);
					searchButton.setVisible(true);

				}
			} else if (searchDropDown.getSelectedItem() == "Titles") {
				filter.setVisible(true);

				this.add(titleName);
				titleName.setBounds(20, 110, 120, 20);
				this.add(titleNameInput);
				titleNameInput.setBounds(120, 110, 380, 20);

				this.add(titleType);
				titleType.setBounds(20, 140, 120, 20);
				this.add(typeDropDown);
				typeDropDown.setBounds(120, 140, 120, 20);

				this.add(titleAdult);
				titleAdult.setBounds(20, 170, 120, 20);
				this.add(adultDropDown);
				adultDropDown.setBounds(120, 170, 120, 20);

				this.add(titleRating);
				titleRating.setBounds(20, 200, 150, 20);
				this.add(ratingMin);
				ratingMin.setBounds(120, 200, 40, 20);
				this.add(ratingAnd);
				ratingAnd.setBounds(170, 200, 40, 20);
				this.add(ratingMax);
				ratingMax.setBounds(200, 200, 40, 20);

				this.add(titleStart);
				titleStart.setBounds(260, 140, 150, 20);
				this.add(startYearMin);
				startYearMin.setBounds(380, 140, 40, 20);
				this.add(startYearAnd);
				startYearAnd.setBounds(430, 140, 40, 20);
				this.add(startYearMax);
				startYearMax.setBounds(460, 140, 40, 20);

				this.add(titleEnd);
				titleEnd.setBounds(260, 170, 150, 20);
				this.add(endYearMin);
				endYearMin.setBounds(380, 170, 40, 20);
				this.add(endYearAnd);
				endYearAnd.setBounds(430, 170, 40, 20);
				this.add(endYearMax);
				endYearMax.setBounds(460, 170, 40, 20);

				this.add(titleRuntime);
				titleRuntime.setBounds(260, 200, 150, 20);
				this.add(runtimeMin);
				runtimeMin.setBounds(380, 200, 40, 20);
				this.add(runtimeAnd);
				runtimeAnd.setBounds(430, 200, 40, 20);
				this.add(runtimeMax);
				runtimeMax.setBounds(460, 200, 40, 20);

				this.add(personName);
				personName.setBounds(20, 240, 120, 20);
				this.add(personNameInput);
				personNameInput.setBounds(120, 240, 380, 20);

				this.add(personBirth);
				personBirth.setBounds(260, 270, 150, 20);
				this.add(birthYearMin);
				birthYearMin.setBounds(380, 270, 40, 20);
				this.add(birthAnd);
				birthAnd.setBounds(430, 270, 40, 20);
				this.add(birthYearMax);
				birthYearMax.setBounds(460, 270, 40, 20);

				this.add(personDeath);
				personDeath.setBounds(260, 300, 150, 20);
				this.add(deathYearMin);
				deathYearMin.setBounds(380, 300, 40, 20);
				this.add(deathAnd);
				deathAnd.setBounds(430, 300, 40, 20);
				this.add(deathYearMax);
				deathYearMax.setBounds(460, 300, 40, 20);

				this.add(personProfession);
				personProfession.setBounds(20, 270, 120, 20);
				this.add(professionDropDown);
				professionDropDown.setBounds(120, 270, 120, 20);

				this.add(characterText);
				characterText.setBounds(20, 300, 120, 20);
				this.add(characterInput);
				characterInput.setBounds(120, 300, 120, 20);

				this.add(seasonText);
				seasonText.setBounds(20, 240, 120, 20);
				this.add(seasonInput);
				seasonInput.setBounds(120, 240, 120, 20);

				this.add(episodeText);
				episodeText.setBounds(260, 240, 120, 20);
				this.add(episodeInput);
				episodeInput.setBounds(380, 240, 120, 20);

				this.add(searchButton);

				if (wantDropDown.getSelectedItem() == "General Information") {
					tGenInfo = true;
					tRelPeople = false;
					tEpisodes = false;

					searchButton.setBounds(240, 240, 80, 20);
					results.setText("");
					setEpisodeSectionVisible(false);
					setPersonSectionVisible(false);
					setTitleSectionVisible(true);
					setJobSectionVisible(false);
					outputDone.setVisible(false);
					setOutputFileVisible(false, 0);
					searchButton.setVisible(true);

				} else if (wantDropDown.getSelectedItem() == "Cast Search") {
					tGenInfo = false;
					tRelPeople = true;
					tEpisodes = false;

					searchButton.setBounds(240, 340, 80, 20);
					results.setText("");
					setEpisodeSectionVisible(false);
					setPersonSectionVisible(true);
					setTitleSectionVisible(true);
					setJobSectionVisible(true);
					outputDone.setVisible(false);
					setOutputFileVisible(false, 0);
					searchButton.setVisible(true);

				} else if (wantDropDown.getSelectedItem() == "Episode Search") {
					tGenInfo = false;
					tRelPeople = false;
					tEpisodes = true;

					searchButton.setBounds(240, 280, 80, 20);
					results.setText("");
					setEpisodeSectionVisible(true);
					setPersonSectionVisible(false);
					setTitleSectionVisible(true);
					setJobSectionVisible(false);
					outputDone.setVisible(false);
					setOutputFileVisible(false, 0);

					titleType.setVisible(false);
					typeDropDown.setVisible(false);
					typeDropDown.setSelectedItem("");

					searchButton.setVisible(true);
				}
			}
		}
	}
}