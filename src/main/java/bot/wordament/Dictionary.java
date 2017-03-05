package bot.wordament;

import java.io.*;
import java.util.HashSet;

/**
 * This class handles the dictionary that your Boggle solver
 * will use to determine if some word is a valid English word.
 *
 * You have to construct it with a String that represents the
 * file where the words are (i.e., words.txt).  Dictionary will
 * open the file, read in and store the words.  If anything goes wrong,
 * the Dictionary will throw an exception.
 */

public class Dictionary {

    private HashSet<String> words;
    private HashSet<String> wordsExistThatStartWith;

    //private File configurationFile;

    /**
     * Construct the class by sending it a String with the
     * file name of the dictionary you want to use.
     */
    public Dictionary (String wordsFileNameString) throws Exception {

        // We'll use a HashSet to store the dictionary.
        words = new HashSet<String>();
        wordsExistThatStartWith = new HashSet<String>();

        if (wordsFileNameString == null) {
            throw new Exception("Can't open a null words file.");
        }
        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        File wordsFile = new File(classLoader.getResource(wordsFileNameString).getFile());

        // Make sure we can read the file.
        if (wordsFile.canRead() == false) {
            throw new Exception("Dictionary can't reads words from this file, make sure it exists and is readable: "+wordsFile.getAbsoluteFile());
        }

        try {

            // Open a buffer to the file.
            BufferedReader br = new BufferedReader(new FileReader(wordsFile));

            System.out.println("Opened file \""+wordsFile+"\", reading in dictionary...");

            while (br.ready()) {

                String line = br.readLine().trim();

                // Blank line
                if (line.length() < 1) {
                    continue;
                }

                // Add each word in the file to our dictionary.
                words.add(line.toUpperCase());

                // For each "prefix" in the word, remember that there is at least
                // one word that starts with that prefix
                for (int i=1; i < line.length(); i++) {
                    wordsExistThatStartWith.add(line.substring(0, i).toUpperCase());
                }

            }

        } catch (IOException e) {
            throw new Exception("An IO exception occurred when reading "+wordsFile+":\n"+e);
        }

        // Make sure we read at least one word.
        if (words.size() < 1) {
            throw new Exception("No words found in file: "+wordsFile);
        }

        System.out.println("Dictionary set up with "+words.size()+" words.");

    }

    /**
     * Returns whether a word is in the dictionary.
     */
    public boolean containsWord (String word) {
        return words.contains(word.toUpperCase());
    }

    /**
     * Returns whether we found any words in the dictionary that
     * started with some initial prefix.
     */
    public boolean wordsExistThatStartWith (String prefix) {
        return wordsExistThatStartWith.contains(prefix.toUpperCase());
    }
}
