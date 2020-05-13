1
https://raw.githubusercontent.com/Luke-Grammer/IMDB-Database-Application/master/src/UI/DirectorPanel.java
/*
 * DirectorPanel.java
 * Luke Grammer, Lauren Haylock, and Davis Beilue
 * CSCE 315-907
 * 10/15/2019
 */

package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import data.DatabaseManager;
import data.model.*;

/**
 * The Class DirectorPanel is the panel adding functionality to search for all
 * actors or directors using a given year range in order to find all the actors
 * or directors who released a title every year in the range.
 */
public class DirectorPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	/** The Database Manager. */
	DatabaseManager manager;

	/** The text field introducing the profession drop down. */
	JLabel professionLabel = new JLabel("Profession to find:");

	/** A string array containing different options for the profession drop-down. */
	String professionOptions[] = { "", "Director", "Actor" };

	/** A drop down for user-selection of the profession to search. */
	JComboBox<String> professionInput = new JComboBox<String>(professionOptions);

	/** The text field introducing the year range. */
	JLabel yearLabel1 = new JLabel("Year Range:");

	/** The text field holding the minimum year in the range. */
	JTextField minYearInput = new JTextField(20);

	/** The text field joining the minimum and maximum year ranges. */
	JLabel yearLabel2 = new JLabel("and");

	/** The text field holding the maximum year in the range. */
	JTextField maxYearInput = new JTextField(20);

	/** The text field introducing the person to exclude. */
	JLabel excludeLabel = new JLabel("Exclude this person from the search:");

	/** The text field holding user input for the person to exclude. */
	JTextField excludePerson = new JTextField(20);

	/** The button that is used to find results. */
	JButton findResult = new JButton("Go!");

	/** The horizontal divider separating the menu options from the results. */
	JSeparator resultsSeparator = new JSeparator();

	/** The label introducing the results. */
	JLabel resultsLabel = new JLabel("Results:");

	/** The text field used to display results. */
	JTextArea results = new JTextArea("");

	/**
	 * Constructor for Director panel that adds and positions all the UI elements on
	 * the screen.
	 *
	 * @param manager an existing instance of a DatabaseManager
	 */
	DirectorPanel(DatabaseManager manager) {

		this.manager = manager;

		// adds and set bounds
		this.setBounds(500, 500, 500, 500);
		this.setLayout(null);

		this.add(professionLabel);
		professionLabel.setBounds(70, 20, 150, 20);
		this.add(professionInput);
		professionInput.setBounds(290, 20, 100, 20);

		this.add(yearLabel1);
		yearLabel1.setBounds(70, 50, 150, 20);
		this.add(minYearInput);
		minYearInput.setBounds(290, 50, 40, 20);
		this.add(yearLabel2);
		yearLabel2.setBounds(340, 50, 40, 20);
		this.add(maxYearInput);
		maxYearInput.setBounds(370, 50, 40, 20);

		this.add(excludeLabel);
		excludeLabel.setBounds(70, 80, 220, 20);
		this.add(excludePerson);
		excludePerson.setBounds(290, 80, 200, 20);

		this.add(findResult);
		findResult.setBounds(240, 130, 80, 20);

		this.add(resultsLabel);

		// set layout as vertical
		resultsSeparator.setOrientation(SwingConstants.HORIZONTAL);
		resultsSeparator.setBounds(20, 250, 535, 200);
		this.add(resultsSeparator);
		resultsLabel.setBounds(20, 230, 150, 20);
		this.add(results);
		results.setBounds(20, 280, 535, 480);
		results.setBackground(this.getBackground());
		results.setLineWrap(true);
		results.setEditable(false);

		findResult.addActionListener(this);
	}

	/**
	 * Search for all actors or directors using a given year range in order to find
	 * all the actors or directors who released a title every year in the range.
	 * 
	 * @param e the action that the user performed on the window
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == findResult) {

			results.setText("Collecting results, please wait...");
			Thread t = new Thread() {
				@Override
				public void run() {

					String return_val = "";

					// Validate year range input
					if (minYearInput.getText().trim().equals("") || maxYearInput.getText().trim().equals("")) {
						results.setText("Must provide valid year range for search!");
						return;
					}

					// Collect user input before starting search
					Integer firstYear = Integer.parseInt(minYearInput.getText());
					Integer lastYear = Integer.parseInt(maxYearInput.getText());

					Boolean actor = false;
					if (professionInput.getItemAt(professionInput.getSelectedIndex()).equals("Actor"))
						actor = true;
					else if (professionInput.getItemAt(professionInput.getSelectedIndex()).equals("")) {
						results.setText("Must select an occupation from the drop down!");
						return;
					}

					// Search for all the links between actors or directors in the database in the
					// given year range
					System.out.println("Getting links for year range");
					ArrayList<PersonTitleLink> links = manager.getPeopleTitlesByYear(actor, firstYear, lastYear);
					System.out.println("Links recieved");

					ArrayList<PersonTitleLink> linksToReturn = new ArrayList<PersonTitleLink>();
					Integer numYears = lastYear - firstYear + 1;

					// Search through all the links that were found
					Integer index = 0;
					while (index < links.size()) {
						System.out.println("\nFound person\n");
						Integer prevYear = -1;
						ArrayList<PersonTitleLink> tempLinks = new ArrayList<PersonTitleLink>();
						String person = links.get(index).getPersonID();
						Integer counter = 0;

						// Continue looping through the results while the person is the same,
						// incrementing a counter every time the year changes (this is
						// alright because the results are sorted by people and years)
						while (index < links.size() && person.equals(links.get(index).getPersonID())) {
							if (links.get(index).getYear() != prevYear) {
								System.out.println("Found new title for person");
								tempLinks.add(links.get(index));
								counter++;
								prevYear = links.get(index).getYear();
							}
							index++;
						}

						// If the counter for the person is the same as the number of distinct years,
						// add the person
						if (counter == numYears) {
							System.out.println("Adding all unique title links for person");
							linksToReturn.addAll(tempLinks);
						}
					}

					index = 0;
					// Generate string for results and find the title names of all the relevant
					// titles
					while (index < linksToReturn.size()) {
						System.out.println("Generating string for person " + (index + 1));
						Person person = manager.getPersonByID(linksToReturn.get(index).getPersonID());
						if (!person.getName().toLowerCase().trim()
								.equals(excludePerson.getText().toLowerCase().trim())) {
							return_val += person.getName() + "\n";

							while (index < linksToReturn.size()
									&& person.getPersonID().equals(linksToReturn.get(index).getPersonID())) {
								Title title = manager.getTitleByID(linksToReturn.get(index).getTitleID());
								return_val += "\t" + title.getTitle() + " (" + title.getStartYear() + ")\n";
								index++;
							}
						} else {
							while (index < linksToReturn.size()
									&& person.getPersonID().equals(linksToReturn.get(index).getPersonID())) {
								index++;
							}
						}
						return_val += "\n";
					}

					// Display the results to the console and the window
					System.out.println("Final Results:\n\n" + return_val);
					results.setText(return_val);
				}
			};
			t.start();
		}
	}
}
