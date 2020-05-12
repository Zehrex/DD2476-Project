1
https://raw.githubusercontent.com/Luke-Grammer/IMDB-Database-Application/master/src/file/FileManager.java
/*
 * FileManager.java
 * Luke Grammer, Lauren Haylock, and Davis Beilue
 * CSCE 315-907
 * 10/15/2019
 */

package file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Class FileManager handles printing large database query output to files.
 */
public class FileManager {

	/** Buffered writer for output to files. */
	private BufferedWriter writer;

	/**
	 * Constructor for FileManager class creates a new instance of BufferedWriter
	 * initialized to the specified filename.
	 *
	 * @param filename the filename to be written to
	 * @throws IOException Signals that an I/O exception has occurred while creating
	 *                     the FileWriter.
	 */
	public FileManager(String filename) throws IOException {
		writer = new BufferedWriter(new FileWriter(filename));
	}

	/**
	 * Close the FileWriter.
	 *
	 * @throws IOException Signals that an I/O exception has occurred while closing
	 *                     the FileWriter.
	 */
	public void close() throws IOException {
		writer.close();
	}

	/**
	 * Write to the specified file using the buffered FileWriter.
	 *
	 * @param string the string to write to the file
	 * @throws IOException Signals that an I/O exception has occurred while writing
	 *                     to the file.
	 */
	public void writeToFile(String string) throws IOException {
		writer.write(string);
	}
}
