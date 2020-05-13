1
https://raw.githubusercontent.com/Luke-Grammer/IMDB-Database-Application/master/src/UI/GUIWindow.java
/*
 * GUIWindow.java
 * Luke Grammer, Lauren Haylock, and Davis Beilue
 * CSCE 315-907
 * 10/15/2019
 */

package UI;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import data.DatabaseManager;

/**
 * The Driver Class containing the main method and the main window frame.
 */
public class GUIWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * A new instance of the Database Manager to pass along to all the things that
	 * need it.
	 */
	DatabaseManager manager = new DatabaseManager();

	/** The main window for the program. */
	JFrame mainFrame = new JFrame("IMDb GUI");

	/** The search panel tab for finding information from the database. */
	JPanel searchPanel = new SearchPanel(manager);

	/** The kevin bacon tab for finding links between actors. */
	JPanel kevinBacon = new KevinBacon(manager);

	/** The trivia panel for getting trivia questions from the database. */
	JPanel triviaPanel = new TriviaPanel(manager);

	/**
	 * The director panel for getting actors/directors and their titles for a given
	 * year range.
	 */
	JPanel directorPanel = new DirectorPanel(manager);

	/** The tabs. */
	JTabbedPane tabs = new JTabbedPane();

	/**
	 * A listener to safely close the connection to the database if the window is
	 * closed.
	 */
	WindowListener exitListener = new WindowAdapter() {
		@Override
		public void windowClosing(WindowEvent e) {
			manager.close();
			System.exit(0);
		}
	};

	/**
	 * Instantiates a new GUI window and adds all the panels.
	 */
	GUIWindow() {
		mainFrame.addWindowListener(exitListener);

		// Make main window appear in the center of the screen
		mainFrame.setBounds((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - 300,
				(Toolkit.getDefaultToolkit().getScreenSize().height / 2) - 400, 600, 800);
		// searchPanel.setBounds(500, 500, 500, 500);
		// triviaPanel.setBounds(500, 500, 500, 500);

		mainFrame.add(tabs);
		tabs.addTab("Search", searchPanel);
		tabs.addTab("Trivia", triviaPanel);
		tabs.addTab("Connections", kevinBacon);
		tabs.addTab("Actor/Director List", directorPanel);

		mainFrame.setVisible(true);
	}

	/**
	 * The main method that creates and runs the main window.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				// The GUIWindow object does not need to be used in the run method
				@SuppressWarnings("unused")
				GUIWindow Main = new GUIWindow();
			}
		});
	}

}