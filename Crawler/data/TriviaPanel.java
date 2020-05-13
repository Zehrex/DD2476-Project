1
https://raw.githubusercontent.com/Luke-Grammer/IMDB-Database-Application/master/src/UI/TriviaPanel.java
/*
 * TriviaPanel.java
 * Luke Grammer, Lauren Haylock, and Davis Beilue
 * CSCE 315-907
 * 10/15/2019
 */

package UI;

import java.awt.event.*;
import javax.swing.*;

import data.DatabaseManager;
import data.model.Person;
import data.model.Title;

/**
 * The TriviaPanel panel adds functionality to generate and display trivia
 * questions with query results from the database for the user to attempt to
 * answer.
 */
public class TriviaPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	/** The Database Manager. */
	DatabaseManager manager;

	/** The text field introducing the question type. */
	JLabel typeOfQuestion = new JLabel("I want a question about");

	/** The string corresponding to the different question types. */
	String typeQuestions[] = { "", "Random", "People", "Titles" };

	/** The drop down menu displaying different question type options. */
	JComboBox<String> typeDropDown = new JComboBox<String>(typeQuestions);

	/** The button that is used to generate a trivia question. */
	JButton generateQuestion = new JButton("Go!");

	/** The text field used to display the trivia question. */
	JLabel triviaQuestion = new JLabel("");

	/** The text field introducing the user answer. */
	JLabel userAnswerLabel = new JLabel("Enter your answer:");

	/** The user-input corresponding to the question answer. */
	JTextField userAnswer = new JTextField(20);

	/** The button used to check the user's answer. */
	JButton checkAnswer = new JButton("Check my answer!");

	/** The string used to store the correct answer. */
	String correctAnswer = "";

	/** The text field used to display the correct answer. */
	JLabel answerResult = new JLabel("");

	/** The strings corresponding to different question types. */
	String myType[] = { "People", "Titles" };

	/** The strings corresponding to different people question categories. */
	String peopleCategories[] = { "Profession", "Age", "Birth Year", "Title Known For" };

	/** The strings corresponding to different title question categories. */
	String titleCategories[] = { "Start Year", "Rating", "Director", "Writer" };

	/** Status flag is true if random question type is chosen. */
	Boolean randomQ = false;

	/** Status flag is true if person question type is chosen. */
	Boolean peopleQ = false;

	/** Status flag is true if title question type is chosen. */
	Boolean titleQ = false;

	/**
	 * Constructor for Trivia panel that adds and positions all the UI elements on
	 * the screen.
	 *
	 * @param manager an existing instance of a DatabaseManager
	 */
	TriviaPanel(DatabaseManager passedManager) {
		manager = passedManager;
		// adds and set bounds
		this.setBounds(500, 500, 500, 500);
		this.setLayout(null);

		this.add(typeOfQuestion);
		typeOfQuestion.setBounds(20, 20, 150, 20);
		this.add(typeDropDown);
		typeDropDown.setBounds(160, 20, 140, 20);
		typeDropDown.addActionListener(this);

		this.add(generateQuestion);
		generateQuestion.setBounds(310, 20, 80, 20);
		generateQuestion.addActionListener(this);

		this.add(triviaQuestion);
		triviaQuestion.setBounds(20, 60, 400, 20);
		this.add(userAnswerLabel);
		userAnswerLabel.setBounds(20, 90, 150, 20);
		this.add(userAnswer);
		userAnswer.setBounds(140, 90, 180, 20);

		this.add(checkAnswer);
		checkAnswer.setBounds(330, 90, 150, 20);
		checkAnswer.addActionListener(this);

		this.add(answerResult);
		answerResult.setBounds(20, 130, 450, 20);

		setAnswersVisible(false);

	}

	/**
	 * Sets the answers section to be visible according to show
	 *
	 * @param show the status flag indicating whether or not to show or hide the
	 *             answer section
	 */
	public void setAnswersVisible(Boolean show) {
		triviaQuestion.setVisible(show);
		userAnswerLabel.setVisible(show);
		userAnswer.setVisible(show);
		checkAnswer.setVisible(show);
		answerResult.setVisible(show);
		userAnswer.setText("");
	}

	/**
	 * Gets a random integer between min and max.
	 *
	 * @param min the minimum number
	 * @param max the maximum number
	 * @return the random number in the range [min, max]
	 */
	public int getRandomNumInt(int min, int max) {
		int x = (int) (Math.random() * ((max - min) + 1)) + min;
		return x;
	}

	/**
	 * Generate a random movie title ID with valid, non-null attributes of interest.
	 *
	 * @return the unique title ID of the randomly generated title
	 */
	public String generateTitleId() {
		Boolean isGood = false;
		String titleId = "";
		while (!isGood) {
			titleId = "tt";
			int num = getRandomNumInt(1, 9899998);
			String titleNum = Integer.toString(num);
			int toFill = 7 - titleNum.length();

			for (int i = 0; i < toFill; i++) {
				titleId += "0";
			}
			titleId += titleNum;

			Title myTitle = manager.getTitleByID(titleId);
			if (myTitle == null) {
				isGood = false;
			} else if (myTitle.getDirectors() == null || myTitle.getWriters() == null) {
				isGood = false;
			} else if (myTitle.getStartYear() == null || myTitle.getSeason() == null) {
				isGood = false;
			} else if (myTitle.getType() == null) {
				isGood = false;
			} else if (!myTitle.getType().equals("movie")) {
				isGood = false;
			} else if (myTitle.getRating() == 0.0) {
				isGood = false;
			} else if (myTitle.getTitle() == null) {
				isGood = false;
			} else {
				isGood = true;
			}
		}
		return titleId;
	}

	/**
	 * Generate a random person ID with valid, non-null attributes of interest.
	 *
	 * @return the unique person ID of the randomly generated person
	 */
	public String generatePersonId() {
		Boolean isGood = false;
		String personId = "";
		while (!isGood) {
			personId = "nm";
			int num = getRandomNumInt(1, 9989997);
			String personNum = Integer.toString(num);
			int toFill = 7 - personNum.length();

			for (int i = 0; i < toFill; i++) {
				personId += "0";
			}
			personId += personNum;

			Person myGuy = manager.getPersonByID(personId);
			// validate ID for possible parameters
			if (myGuy == null) {
				isGood = false;
			} else if (myGuy.getProfessions() == null || myGuy.getProfessions()[0].equals("miscellaneous")) {
				isGood = false;
			} else if (myGuy.getBirthYear() == 0) {
				isGood = false;
			} else if (myGuy.getKnownFor() == null) {
				isGood = false;
			} else {
				isGood = true;
			}

		}
		return personId;
	}

	/**
	 * Generate a random trivia question, display it, and give the user the
	 * opportunity to answer.
	 * 
	 * @param e the action that the user performed on the window
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == generateQuestion) {
			triviaQuestion.setText("Creating question, please wait...");
			setAnswersVisible(false);
			triviaQuestion.setVisible(true);
			Thread t = new Thread() {
				@Override
				public void run() {
					correctAnswer = "";
					userAnswer.setText("");
					answerResult.setText("");
					// If randomly choosing between people of title questions, flip a coin
					if (randomQ) {
						int typeNum = getRandomNumInt(0, 1);
						if (typeNum == 0) {
							peopleQ = true;
							titleQ = false;
						} else {
							titleQ = true;
							peopleQ = false;
						}
					}

					if (peopleQ) {
						// generate random question about a person
						int categoryNum = getRandomNumInt(0, 3);
						String categoryChoice = peopleCategories[categoryNum];
						String personId = generatePersonId();

						// based on category, query db for attributes
						Person myGuy = manager.getPersonByID(personId);

						if (categoryChoice == "Profession") {
							correctAnswer = myGuy.getProfessions()[0];
							String personName = myGuy.getName();
							triviaQuestion.setText("What profession is " + personName + " most known for?");
						} else if (categoryChoice == "Age") {
							Integer BirthYear = myGuy.getBirthYear();
							Integer DeathYear = myGuy.getDeathYear();
							String personName = myGuy.getName();
							if (DeathYear == 0) {
								correctAnswer = Integer.toString(2019 - BirthYear);
								triviaQuestion.setText("How old is " + personName + "?");
							} else {
								correctAnswer = Integer.toString(DeathYear - BirthYear);
								triviaQuestion.setText("How old was " + personName + " when they died?");
							}
						} else if (categoryChoice == "Birth Year") {
							Integer BirthYear = myGuy.getBirthYear();
							String personName = myGuy.getName();
							correctAnswer = Integer.toString(BirthYear);
							triviaQuestion.setText("What year was " + personName + " born?");
						} else if (categoryChoice == "Title Known For") {
							String titleID = myGuy.getKnownFor()[0];
							Title myTitle = manager.getTitleByID(titleID);
							correctAnswer = myTitle.getTitle();
							String personName = myGuy.getName();
							triviaQuestion.setText("What title is " + personName + " most known for?");
						}
						setAnswersVisible(true);
					} else if (titleQ) {
						// generate a random question about a title
						int categoryNum = getRandomNumInt(0, 3);
						String categoryChoice = titleCategories[categoryNum];
						String titleId = generateTitleId();

						Title myTitle = manager.getTitleByID(titleId);

						if (categoryChoice == "Start Year") {

							String titleName = myTitle.getTitle();
							Integer StartYear = myTitle.getStartYear();
							correctAnswer = Integer.toString(StartYear);
							if (myTitle.getType().equals("movie") || myTitle.getType().equals("tvMovie")
									|| myTitle.getType().equals("short") || myTitle.getType().equals("tvShort)")) {
								triviaQuestion.setText("What year did \"" + titleName + "\" premiere?");
							} else if (myTitle.getType().equals("tvSeries")) {
								triviaQuestion.setText("What year did the show \"" + titleName + "\" begin airing?");
							}
						} else if (categoryChoice == "Rating") {
							Double myRating = myTitle.getRating();
							String titleName = myTitle.getTitle();
							correctAnswer = Double.toString(myRating);
							triviaQuestion.setText("What was the rating for the title \"" + titleName + "\"?");
						} else if (categoryChoice == "Director") {
							String directorID = myTitle.getDirectors()[0];
							correctAnswer = manager.getPersonByID(directorID).getName();
							String titleName = myTitle.getTitle();
							triviaQuestion.setText("Who was the chief director of \"" + titleName + "\"?");
						} else if (categoryChoice == "Writer") {
							String writerID = myTitle.getWriters()[0];
							correctAnswer = manager.getPersonByID(writerID).getName();
							String titleName = myTitle.getTitle();
							triviaQuestion.setText("Who was the chief writer of \"" + titleName + "\"?");
						}
						setAnswersVisible(true);
					} else {
						triviaQuestion.setText("Please select a category");
						triviaQuestion.setVisible(true);
					}
				}
			};
			t.start();
		} else if (e.getSource() == checkAnswer) {
			// check against stored answerResult
			String toCheck = userAnswer.getText().replaceAll("\\s+", "").toLowerCase();
			String newAnswer = correctAnswer.replaceAll("\\s+", "").toLowerCase();
			if (!newAnswer.equals(toCheck)) {
				answerResult.setText("Sorry, that is incorrect. The correct answer is " + correctAnswer + ".");
			} else {
				answerResult.setText("That's correct!");
			}

		} else if (e.getSource() == typeDropDown) {
			setAnswersVisible(false);
			if (typeDropDown.getSelectedItem().equals("Random")) {
				randomQ = true;
				peopleQ = false;
				titleQ = false;

			} else if (typeDropDown.getSelectedItem() == "People") {
				peopleQ = true;
				titleQ = false;
				randomQ = false;
			} else if (typeDropDown.getSelectedItem() == "Titles") {
				titleQ = true;
				peopleQ = false;
				randomQ = false;
			} else {
				randomQ = false;
				peopleQ = false;
				titleQ = false;
			}
		}
	}
}