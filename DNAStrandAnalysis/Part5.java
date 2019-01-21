
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part5 {
    public int howMany(String stringa, String stringb) {
        /* Write the method named howMany that has two String parameters named 
         * stringa and stringb. This method returns an integer indicating how 
         * many times stringa appears in stringb, where each occurrence of 
         * stringa must not overlap with another occurrence of it. For example,
         * the call howMany(“GAA”, “ATGAACGAATTGAATC”) returns 3 as GAA 
         * occurs 3 times. The call howMany(“AA”, “ATAAAA”) returns 2. 
         * Note that the AA’s found cannot overlap.*/
         int startPoint = 0;
         int countFreq = 0;
         int indexStrA = stringb.indexOf(stringa,startPoint);
         if (indexStrA == -1){
                return countFreq;
            } else{
                while (true) {
                    countFreq++;
                    startPoint = startPoint+indexStrA+stringa.length();
                    if (stringb.indexOf(stringa,startPoint) == -1) {break;}
                } 
            }
         return countFreq;
    }
    public void testHowMany(){
        System.out.println(howMany("GAA", "ATGAACGAATTGAATC"));
        System.out.println(howMany("AA", "ATAAAA"));
    }
    public static void main (String[] args) {
        Part5 p2 = new Part5();
        p2.testHowMany();
    }
}
