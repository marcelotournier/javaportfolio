/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb) {
        /*
         * Write the method named twoOccurrences that has two String parameters 
         * named stringa and stringb. 
         * 
         * This method returns true if stringa appears at least twice in stringb, 
         * otherwise it returns false. 
         * 
         * For example, the call twoOccurrences(“by”, “A story by Abby Long”) 
         * returns true as there are two occurrences of “by”, 
         * the call twoOccurrences(“a”, “banana”) returns true as there are three
         * occurrences of “a” so “a” occurs at least twice, 
         * and the call twoOccurrences(“atg”, “ctgtatgta”) returns false as there is
         * only one occurence of “atg”.
           */
        
        if (stringb.indexOf(stringa) == -1) {
            return false;
        }
        if (stringb.indexOf(stringa,stringb.indexOf(stringa)+1) == -1) {
            return false;
        } 
            return true;
    }
    
    public String lastPart(String stringa, String stringb) {
        /* Write the method named lastPart that has two String parameters named 
         * stringa and stringb. This method finds the first occurrence of stringa 
         * in stringb, and returns the part of stringb that follows stringa. 
         * If stringa does not occur in stringb, then return stringb. 
         * For example, the call lastPart(“an”, “banana”) returns the string “ana”, 
         * the part of the string after the first “an”. The call lastPart(“zoo”, “forest”)
         * returns the string “forest” since “zoo” does not appear in that word.
           */
        
        if (stringb.indexOf(stringa) == -1) {
            return stringb;
        }
            return stringb.substring(stringb.indexOf(stringa)+stringa.length(),
            stringb.length());
    }
    
    public void testing() {
        /*Write the void method named testing that has no parameters. 
         * This method should call twoOccurrences on several pairs of strings and print 
         * the strings and the result of calling twoOccurrences (true or false) for each 
         * pair. Be sure to test examples that should result in true and examples that 
         * should result in false.
           */
          String string1a = "atg";
          String string1b = "ctgtatgtatg";
          System.out.println("Strings: "+string1a+","+string1b);
          System.out.println("2 or more occurrences: "+twoOccurrences(string1a, string1b));
          String string2a = "l";
          String string2b = "ctgtatgtatg";
          System.out.println("Strings: "+string2a+","+string2b);
          System.out.println("2 or more occurrences: "+twoOccurrences(string2a, string2b));
          
          System.out.println("=== Testing lastPart... ===");
          String string1c = "an";
          String string1d = "banana";
          System.out.println("Strings: "+string1c+","+string1d);
          System.out.println("Last part: "+lastPart(string1c,string1d));
          String string2c = "zoo";
          String string2d = "forest";
          System.out.println("Strings: "+string2c+","+string2d);
          System.out.println("Last part: "+lastPart(string2c,string2d));
    }
    
    public static void main (String[] args) {
        Part3 p3 = new Part3();
        System.out.println("=== Starting program... ===");
        p3.testing();
        System.out.println("===      Finished!      ===");
    }
    
}
