import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;

public class WeatherMultiple {
    //
    public CSVRecord coldestHourInFile(CSVParser parser){
        /* Write a method named coldestHourInFile that has one parameter, a 
         * CSVParser named parser. This method returns the CSVRecord with the 
         * coldest temperature in the file and thus all the information about 
         * the coldest temperature, such as the hour of the coldest 
         * temperature.  */
         CSVRecord coldestRecord = null;
         double coldestTemp = 999999999999999999999999999999.9;
        for (CSVRecord record : parser) {
            double rowTemp = Double.parseDouble(record.get("TemperatureF"));
            //System.out.println(rowTemp);
            if ((rowTemp < coldestTemp) && (rowTemp > -100.0)){
                coldestTemp = rowTemp;
                coldestRecord = record;
                //System.out.println(rowTemp);
            }
            }
        return coldestRecord;
    }
    
    public String fileWithColdestTemperature() {
        /* Write the method fileWithColdestTemperature that has no 
         * parameters. This method should return a string that is the name 
         * of the file from selected files that has the coldest temperature. 
           */
          DirectoryResource dr = new DirectoryResource();
          File coldestFile = null;
          double coldestTemperature = 99999999999999999999.9;
          CSVParser coldestParser = null;
          for (File f : dr.selectedFiles()) {
             FileResource fr = new FileResource(f);
             CSVParser parser = fr.getCSVParser();
             CSVRecord thisColdTemp = coldestHourInFile(parser);
             
             double fileTemperature = Double.parseDouble(
                                      thisColdTemp.get("TemperatureF"));
             
             
             if (fileTemperature < coldestTemperature){
                 //
                 coldestTemperature = fileTemperature;
                 coldestFile = f;
                 coldestParser = parser;
                }
             
             
          }
          System.out.println("Coldest day was in file "+coldestFile.getName());
          System.out.println("Coldest temperature on that day was "+
          coldestTemperature);
         System.out.println("All the Temperatures on the coldest day were:");
         
         FileResource coldFile = new FileResource(coldestFile);
         CSVParser coldParser = coldFile.getCSVParser();
         for (CSVRecord record : coldParser) {
            String coldDate = record.get("DateUTC");
            String coldTime = record.get("TimeEST");
            String coldTemp = record.get("TemperatureF");
            System.out.println(coldDate.substring(0,19)+": "+coldTemp);
            }
         String coldestName = coldestFile.getName();
            return coldestName;
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        /* Write a method named lowestHumidityInFile that has one parameter, 
         * a CSVParser named parser. This method returns the CSVRecord that 
         * has the lowest humidity. If there is a tie, then return the first 
         * such record that was found.
           
         * Note that sometimes there is not a number in the Humidity column 
         * but instead there is the string “N/A”. This only happens very 
         * rarely. You should check to make sure the value you get is not 
         * “N/A” before converting it to a number.
         * 
         * Also note that the header for the time is either TimeEST or 
         * TimeEDT, depending on the time of year. You will instead use 
         * the DateUTC field at the right end of the data file to get both 
         * the date and time of a temperature reading.
           */
        CSVRecord humidityRecord = null;
         double worstHumidity = 999999999999999999999999999999.9;
         double rowHumid = 0.0;
        for (CSVRecord record : parser) {
            if (record.get("Humidity").equals("N/A")){
                rowHumid = 999999999999999999999999999999.9;
            } else {
                rowHumid = Double.parseDouble(record.get("Humidity"));
            }
            //System.out.println(rowTemp);
            if (rowHumid < worstHumidity){
                worstHumidity = rowHumid;
                humidityRecord = record;
                //System.out.println(rowTemp);
            }
            }
        return humidityRecord;
        
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        /* Write the method lowestHumidityInManyFiles that has no parameters.
         * This method returns a CSVRecord that has the lowest humidity over
         * all the files. If there is a tie, then return the first such 
         * record that was found.  */
         DirectoryResource dr = new DirectoryResource();
         
         double worstHumidity = 99999999999999999999.9;
         CSVRecord worstRecord = null;
         for (File f : dr.selectedFiles()) {
             FileResource fr = new FileResource(f);
             CSVParser parser = fr.getCSVParser();
             CSVRecord thisFile = lowestHumidityInFile(parser);
             double actualHumidity = Double.parseDouble(thisFile.get("Humidity"));
             //System.out.println(thisFile);
             if(actualHumidity < worstHumidity){
                 //
                 worstHumidity = actualHumidity;
                 worstRecord = thisFile;
                 
                }
                
            }
        return worstRecord;
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        /* Write the method averageTemperatureInFile that has one parameter, 
         * a CSVParser named parser. This method returns a double that 
         * represents the average temperature in the file. 
           */
           double temperatureTotal = 0.0;
           int numRows = 0;
        for (CSVRecord record : parser) {
            String stringTemp = record.get("TemperatureF");
            double actualTemp = Double.parseDouble(stringTemp);
            if (actualTemp > -100.0){
                temperatureTotal = temperatureTotal+actualTemp;
                numRows++;
                //System.out.println(rowTemp);
            }
            }
        return temperatureTotal/numRows;
        
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser,
    int value){
        /* Write the method averageTemperatureWithHighHumidityInFile that 
         * has two parameters, a CSVParser named parser and an integer named 
         * value. This method returns a double that represents the average 
         * temperature of only those temperatures when the humidity was 
         * greater than or equal to value. 
           */
        double temperatureTotal = 0.0;
           int numRows = 0;
        for (CSVRecord record : parser) {
            String stringTemp = record.get("TemperatureF");
            double actualTemp = Double.parseDouble(stringTemp);
            String stringHumid = record.get("Humidity");
            double actualHumid = Double.parseDouble(stringHumid);
            if ((actualTemp > -100.0) && (actualHumid >= value)){
                temperatureTotal = temperatureTotal+actualTemp;
                
                numRows++;
                
            }
            }
        //System.out.println(temperatureTotal/numRows);
            return temperatureTotal/numRows;
    }
    
    public void testColdestHourInFile() {
        /* You should also write a void method named 
         * testColdestHourInFile() to test this method and print out 
         * information about that coldest temperature, such as the time of 
         * its occurrence.*/
        
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(coldestHourInFile(parser).get("TemperatureF"));
    }
    
    public void testFileWithColdestTemperature() {
        /* You should also write a void method named 
         * testFileWithColdestTemperature() to test this method. 
         * Note that after determining the filename, you could call the 
         * method coldestHourInFile to determine the coldest temperature on 
         * that day. When fileWithColdestTemperature runs and selects the 
         * files for January 1–3 in 2014, the method should print out:
         * 
           Coldest day was in file weather-2014-01-03.csv
           Coldest temperature on that day was 21.9
           All the Temperatures on the coldest day were:
           2014-01-03 05:51:00: 41.0
           2014-01-03 06:51:00: 39.0
           2014-01-03 07:51:00: 35.1
           2014-01-03 08:51:00: 30.9
           2014-01-03 09:51:00: 28.0*/
           
        fileWithColdestTemperature();
    }
    
    public void testLowestHumidityInFile(){
        /* and then prints the lowest humidity AND the time the 
         * lowest humidity occurred. For example, for the file 
         * weather-2014-01-20.csv, the output should be:
           Lowest Humidity was 24 at 2014-01-20 19:51:00
           */
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.print("Lowest Humidity was ");
        System.out.print(csv.get("Humidity")+" at ");
        System.out.println(csv.get("DateUTC"));
        }
        
    public void testLowestHumidityInManyFiles(){
        /* You should also write a void method named 
         * testLowestHumidityInManyFiles() to test this method and to print 
         * the lowest humidity AND the time the lowest humidity occurred. 
         * Be sure to test this method on two files so you can check if it 
         * is working correctly. If you run this program and select the 
         * files for January 19, 2014 and January 20, 2014, you should get:
         * 
         * Lowest Humidity was 24 at 2014-01-20 19:51:00 */
         CSVRecord csv = lowestHumidityInManyFiles();
         System.out.print("Lowest Humidity was ");
         System.out.print(csv.get("Humidity")+" at ");
         System.out.println(csv.get("DateUTC"));
    }
    
    public void testAverageTemperatureInFile(){
        /* You should also 
         * write a void method named testAverageTemperatureInFile() to test 
         * this method. When this method runs and selects the file for 
         * January 20, 2014, the method should print out
           Average temperature in file is 44.93333333333334*/
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.print("Average temperature in file is ");
        System.out.println(averageTemperatureInFile(parser));
           
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        /* You should also write a void 
         * method named testAverageTemperatureWithHighHumidityInFile() to 
         * test this method. When this method runs checking for humidity 
         * greater than or equal to 80 and selects the file for January 20, 
         * 2014, the method should print out
           
           No temperatures with that humidity */
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double tempHumid = averageTemperatureWithHighHumidityInFile(parser,80);
        if (Double.isNaN(tempHumid)){
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.print("Average Temp when high Humidity is ");
            System.out.println(tempHumid);}
        
    }
    
    public static void main (String[] args) {
        WeatherMultiple w1 = new WeatherMultiple();
        System.out.println("=== start ===");
        
        //w1.testColdestHourInFile();
        w1.testFileWithColdestTemperature();
        //w1.testLowestHumidityInFile();
        //w1.testLowestHumidityInManyFiles();
        //w1.testAverageTemperatureInFile();
        //w1.testAverageTemperatureWithHighHumidityInFile();
        
        System.out.println("=== finish ===");
    }
}
