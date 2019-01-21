import edu.duke.*;
import java.io.File;
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class URLScrapper {
    public void printURLContents() {
        // Use URLResource to read the file at 
        // http://www.dukelearntoprogram.com/course2/data/manylinks.html word by word
        URLResource url = new URLResource(
        "http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        // For each word, check to see if “youtube.com” is in it. 
        // If it is, find the double quote to the left and right of the occurrence of 
        // “youtube.com” to identify the beginning and end of the URL. 
        // Note, the double quotation mark is a special character in Java. 
        // To look for a double quote, look for (\”), since the backslash (\) 
        // character indicates we want the literal quotation marks (“) and not the 
        // Java character. The string you search for would be written “\”” for one 
        // double quotation mark.
        for (String word : url.words()) {
            // process each line in turn
            // For each word, check to see if “youtube.com” is in it.
            if (word.toLowerCase().indexOf("youtube.com") != -1) {
                //System.out.println(word);
                
                /* If it is, find the double quote to the left and right of the occurrence
                 * of “youtube.com” to identify the beginning and end of the URL */
                 System.out.println(
                 word.substring(word.indexOf('"')+1,word.indexOf('"',word.indexOf('"')+1))
                 );
            }
            //System.out.println(word);
        }
    
    }
    
    public static void main (String[] args) {
        URLScrapper p4 = new URLScrapper();
        System.out.println("=== Starting program... ===");
        p4.printURLContents();
        System.out.println("===      Finished!      ===");
    }
}