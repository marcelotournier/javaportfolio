import edu.duke.*;
import org.apache.commons.csv.*;

public class Exports1 {
    
    public String countryInfo(CSVParser parser, String country){
        for (CSVRecord record : parser) {
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)"); 
                if (record.get("Country").contains(country)){
                    return (country+": "+exports+": "+value);
            }   
            }
         return "NOT FOUND";
     }
    
    public void listExportersTwoProducts(CSVParser parser, 
    String exportItem1,String exportItem2){
        /*Write a void method named listExportersTwoProducts that has three
         * parameters, parser is a CSVParser, exportItem1 is a String and
         * exportItem2 is a String. This method prints the names of all the
         * countries that have both exportItem1 and exportItem2 as export
         * items. For example, using the file exports_small.csv, this method
         * called with the items “gold” and “diamonds” would print the
         * countries 
           Namibia
           South Africa
           */
          for (CSVRecord record : parser) {
                String exports = record.get("Exports");
                String country = record.get("Country"); 
                if (record.get("Exports").contains(exportItem1) &&
                record.get("Exports").contains(exportItem2)
                ){
                     System.out.println(country);
            }   
            }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        /* Write a method named numberOfExporters, which has two parameters, 
        parser is a CSVParser, and exportItem is a String. This method 
        returns the number of countries that export exportItem. For example, 
        using the file exports_small.csv, this method called with the item 
        “gold” would return 3.*/
        int exportNumber = 0;
        for (CSVRecord record : parser) {
                if (record.get("Exports").contains(exportItem)){
                     exportNumber++;
            }   
            }
        //return number of countries that export exportItem
        return exportNumber;
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        /*Write a void method named bigExporters that has two parameters, 
         * parser is a CSVParser, and amount is a String in the format of a 
         * dollar sign, followed by an integer number with a comma separator 
         * every three digits from the right. An example of such a string 
         * might be “$400,000,000”. 
         * 
         * This method prints the names of countries
         * and their Value amount for all countries whose Value (dollars) 
         * string is longer than the amount string. 
         * You do not need to parse 
         * either string value as an integer, just compare the lengths of the
         * strings. For example, if bigExporters is called with the file 
         * exports_small.csv and amount with the string $999,999,999, then 
         * this method would print eight countries and their export values 
         * shown here: 
           Germany $1,547,000,000,000
           Macedonia $3,421,000,000
           Malawi $1,332,000,000
           Malaysia $231,300,000,000
           Namibia $4,597,000,000
           Peru $36,430,000,000
           South Africa $97,900,000,000
           United States $1,610,000,000,000
           */
        for (CSVRecord record : parser) {
                String country = record.get("Country");
                String value = record.get("Value (dollars)"); 
                if (record.get("Country").contains(country)){
                    if (amount.length() < value.length()){ 
                    System.out.println(country+" "+value);
                    }   
                }
            }
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //System.out.println(countryInfo(parser,"Nauru"));
        //listExportersTwoProducts(parser,"cotton","flowers");
        //System.out.println(numberOfExporters(parser,"cocoa"));
        bigExporters(parser,"$999,999,999,999");
    }
    
    public static void main (String[] args) {
        Exports1 e1 = new Exports1();
        System.out.println("*** Program start... ***");
        //
        e1.tester();
        System.out.println("*** Program finish!  ***");
    }
}
