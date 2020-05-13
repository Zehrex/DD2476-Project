1
https://raw.githubusercontent.com/Luke-Grammer/IMDB-Database-Application/master/src/UI/KevinBacon.java
/*
 * KevinBacon.java
 * Luke Grammer, Lauren Haylock, and Davis Beilue
 * CSCE 315-907
 * 10/15/2019
 */

package UI;

import java.awt.event.*;
import javax.swing.*;

import data.DatabaseManager;
import search.SearchManager;

/**
 * The Class KevinBacon is the panel adding functionality to search for the
 * shortest number of links between any two actors through their co-stars,
 * co-costars, etc...
 */
public class KevinBacon extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	/** The search manager. */
	SearchManager searchManager;

	/** The Database manager. */
	DatabaseManager manager;

	/** The text field introducing the actor text input fields. */
	JLabel findConnectionLabel = new JLabel("Find the connection between:");

	/** The first actor text input field. */
	JTextField firstPerson = new JTextField(20);

	/** The text field linking the first and second actor text input fields. */
	JLabel findConnectionAnd = new JLabel("and");

	/** The second actor text input field. */
	JTextField secondPerson = new JTextField(20);

	/** The text field introducing the exclusion input field. */
	JLabel excludeLabel = new JLabel("Exclude this person from the search:");

	/** The input field for an actor to exclude from the search. */
	JTextField excludePerson = new JTextField(20);

	/** The button that is used to find results. */
	JButton findConnection = new JButton("Go!");

	/** The horizontal divider separating the menu options from the results. */
	JSeparator resultsSeparator = new JSeparator();

	/** The label introducing the results. */
	JLabel resultsLabel = new JLabel("Results:");

	/** The text field used to display results. */
	JTextArea results = new JTextArea("");

	/**
	 * Constructor for Kevin Bacon panel that adds and positions all the UI elements
	 * on the screen.
	 *
	 * @param manager an existing instance of a DatabaseManager
	 */
	KevinBacon(DatabaseManager manager) {

		this.manager = manager;
		searchManager = new SearchManager(manager);

		// adds and set bounds
		this.setBounds(500, 500, 500, 500);
		this.setLayout(null);

		this.add(findConnectionLabel);
		findConnectionLabel.setBounds(195, 20, 200, 20);

		this.add(firstPerson);
		firstPerson.setBounds(180, 50, 200, 20);

		this.add(findConnectionAnd);
		findConnectionAnd.setBounds(270, 80, 50, 20);

		this.add(secondPerson);
		secondPerson.setBounds(180, 110, 200, 20);

		this.add(excludeLabel);
		excludeLabel.setBounds(70, 150, 220, 20);

		this.add(excludePerson);
		excludePerson.setBounds(290, 150, 200, 20);

		this.add(findConnection);
		findConnection.setBounds(240, 190, 80, 20);

		this.add(resultsLabel);

		// set layout as vertical
		resultsSeparator.setOrientation(SwingConstants.HORIZONTAL);
		resultsSeparator.setBounds(20, 250, 535, 200);
		this.add(resultsSeparator);
		resultsLabel.setBounds(20, 230, 150, 20);
		this.add(results);
		results.setBounds(20, 280, 535, 280);
		results.setBackground(this.getBackground());
		results.setLineWrap(true);
		results.setEditable(false);

		findConnection.addActionListener(this);
	}

	/**
	 * Search for the shortest link between any two actors through films they
	 * appeared in and their co-stars. Send the output results to the results text
	 * field.
	 * 
	 * @param e the action that the user performed on the window
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == findConnection) {
			results.setText("Collecting results, please wait...");
			Thread t = new Thread() {
				@Override
				public void run() {
					String result = null;
					if (!(firstPerson.getText().equals("") || secondPerson.getText().equals(""))) {
						String exclusion = ((excludePerson.getText().trim().equals("")) ? null
								: excludePerson.getText().toLowerCase().trim());

						result = searchManager.doSearch(firstPerson.getText(), secondPerson.getText(), exclusion);
					} else {
						result = "Please enter two actors to find a connection.";
					}
					results.setText(result);
				}
			};
			t.start();
		}
	}
}