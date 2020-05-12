import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class PageRank {

    /**
     *   Maximal number of documents. We're assuming here that we
     *   don't have more docs than we can keep in main memory.
     */
    final static int MAX_NUMBER_OF_DOCS = 2000000;

    // Number of results to print out
    final static int NUM_RESULTS = 30;

    /**
     *   Mapping from document names to document numbers.
     */
    HashMap<String, Integer> docNumber = new HashMap<>();

    /**
     *   Mapping from document numbers to document names
     */
    String[] docName = new String[MAX_NUMBER_OF_DOCS];

    /**
     *   A memory-efficient representation of the transition matrix.
     *   The outlinks are represented as a HashMap, whose keys are
     *   the document number linked from.<p>
     *
     *   The value corresponding to key i is a HashMap whose keys are
     *   all the numbers of documents j that i links to.<p>
     *
     *   If there are no outlinks from i, then the value corresponding
     *   key i is null.
     */
    HashMap<Integer, HashMap<Integer, Boolean>> link = new HashMap<>();

    /**
     *   The number of outlinks from each node.
     */
    int[] out = new int[MAX_NUMBER_OF_DOCS];

    /**
     *   The probability that the surfer will be bored, stop
     *   following links, and take a random jump somewhere.
     */
    final static double BORED = 0.15;

    /**
     *   Convergence criterion: Transition probabilities do not
     *   change more that EPSILON from one iteration to another.
     */
    final static double EPSILON = 0.0001;


    /* --------------------------------------------- */


    public PageRank( String filename ) {
      int noOfDocs = readDocs (filename);
      iterate (noOfDocs, 1000);
    }


    /* --------------------------------------------- */


    /**
     *   Reads the documents and fills the data structures.
     *
     *   @return the number of documents read.
     */
    int readDocs( String filename ) {
      int fileIndex = 0;
      try {
        System.err.print( "Reading file... " );
        BufferedReader in = new BufferedReader( new FileReader( filename ));
        String line;
        while ((line = in.readLine()) != null && fileIndex < MAX_NUMBER_OF_DOCS) {
          int index = line.indexOf(";");
          String title = line.substring(0, index);
          Integer fromdoc = docNumber.get(title);
          //  Have we seen this document before?
          if (fromdoc == null) {
            // This is a previously unseen doc, so add it to the table.
            fromdoc = fileIndex++;
            docNumber.put(title, fromdoc);
            docName[fromdoc] = title;
          }
          // Check all outlinks.
          StringTokenizer tok = new StringTokenizer(line.substring(index+1), ",");
          while (tok.hasMoreTokens() && fileIndex < MAX_NUMBER_OF_DOCS) {
            String otherTitle = tok.nextToken();
            Integer otherDoc = docNumber.get(otherTitle);

            if (otherDoc == null) {
              // This is a previousy unseen doc, so add it to the table.
              otherDoc = fileIndex++;
              docNumber.put(otherTitle, otherDoc);
              docName[otherDoc] = otherTitle;
            }

            // Set the probability to 0 for now, to indicate that there is
            // a link from fromdoc to otherDoc.
            if (link.get(fromdoc) == null) {
              link.put(fromdoc, new HashMap<Integer,Boolean>());
            }

            if (link.get(fromdoc).get(otherDoc) == null) {
              link.get(fromdoc).put(otherDoc, true);
              out[fromdoc]++;
            }
          }
        }

        if ( fileIndex >= MAX_NUMBER_OF_DOCS ) {
          System.err.print( "stopped reading since documents table is full. " );
        }
        else {
          System.err.print( "done. " );
        }
      }

      catch (FileNotFoundException e) {
        System.err.println( "File " + filename + " not found!" );
      }

      catch (IOException e) {
        System.err.println( "Error reading file " + filename );
      }

      System.err.println( "Read " + fileIndex + " number of documents" );
      return fileIndex;
    }


    /* --------------------------------------------- */


    /*
     *   Chooses a probability vector a, and repeatedly computes
     *   aP, aP^2, aP^3... until aP^i = aP^(i+1).
     */
    void iterate (int numberOfDocs, int maxIterations) {


      double[] prevVector = new double[numberOfDocs];
      double[] curVector = new double[numberOfDocs];

      // Initilize curVector entries
      curVector[0] = 1.0;

      int numIterations = 0;

      // Loop until we hit epsilon or maxIterations
      while (diff(prevVector, curVector) > EPSILON && numIterations <
            maxIterations) {

        // Update variables for next iteration
        prevVector = curVector;
        numIterations++;

        curVector = multiplyMatrices(curVector);
        System.out.println("Iteration number: " + numIterations);

        if (numIterations > 3) break;
      }

      printResults(curVector);
    }

    /*
     * Calculate the differnce between two vectors
     */
    private double diff (double[] v1, double[] v2) {
      assert (v1.length == v2.length);
      double ret = 0.0;

      // Get the difference between each value
      for (int i = 0; i < v1.length; i++) {
        ret += Math.abs(v1[i] - v2[i]);
      }

      return ret;
    }

    /*
     * Multiply the vector with the weird representation of the graph
     */
    private double[] multiplyMatrices (double[] curVector) {
      // System.out.println(Arrays.toString(curVector));

      int numberOfDocs = curVector.length;

      double[] res = new double[numberOfDocs];

      for (int doc = 0; doc < numberOfDocs; doc++) {
        HashMap<Integer, Boolean> docLinks = link.get(doc);

        // We have a sink
        if (out[doc] == 0) {
          for (int doc2 = 0; doc2 < numberOfDocs; doc2++) {
            if (doc != doc2) {
              res[doc2] += (curVector[doc] / (numberOfDocs ));
            }
          }
        } else {
          // Add the probabilities of each link
          if (docLinks != null) {
            for (int outDoc : docLinks.keySet()) {
              res[outDoc] += (1.0 - BORED) * (curVector[doc] / docLinks.size());
            }
          }

          // Add the bored portion
          for (int doc2 = 0; doc2 < numberOfDocs; doc2++) {
            res[doc2] += BORED * curVector[doc] / numberOfDocs;
          }
        }
      }

      return res;
    }

    /*
     * print the top scoring documents
     */
    private void printResults(double[] vector) {
      int numFound = 0;
      double limit = Double.MAX_VALUE;
      DecimalFormat df = new DecimalFormat("#.#####");

      // Loop until we've found the top NUM_RESULTS results
      while (numFound < NUM_RESULTS) {
        double highest = -1.0;
        int highestIndex = -1;

        // Find the next highest score
        for (int i = 0; i < vector.length; i++) {
          double curVal = Math.abs(vector[i]);

          if (curVal > highest && curVal < limit) {
            highest = curVal;
            highestIndex = i;
          }
        }

        System.out.println(docName[highestIndex] + ": " + df.format(highest));

        // Set the limit to the highest we just found
        limit = highest;
        numFound++;
      }
    }


    /* --------------------------------------------- */


    public static void main( String[] args ) {
      if (args.length != 1) {
        System.err.println( "Please give the name of the link file" );
      }
      else {
        new PageRank(args[0]);
      }
    }
}
