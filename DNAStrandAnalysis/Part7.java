import edu.duke.*;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part7 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        /*Write the method findStopCodon that has three parameters, 
         * a String parameter named dna, an integer parameter named startIndex
         * that represents where the first occurrence of ATG occurs in dna, 
         * and a String parameter named stopCodon. 
         * 
         * This method returns the index of the first occurrence of stopCodon
         * that appears past startIndex and is a multiple of 3 away from 
         * startIndex.
         *
         * If there is no such stopCodon, this method returns the length 
         * of the dna strand.*/
        int startCodon = dna.indexOf("ATG",startIndex);
        //System.out.println("Start codon: "+startCodon);
        
        int stopIndex = dna.indexOf(stopCodon,startCodon+1);
        //System.out.println("Stop index: "+stopIndex);
        
        int strLength = stopIndex+3-startCodon;
        //System.out.println("strLength: "+strLength);
        while (strLength%3 != 0){
            if (stopIndex == -1) {
                stopIndex = dna.length();
                break;
            }
            stopIndex = dna.indexOf(stopCodon,stopIndex+1);
            //System.out.println("Stop index inside loop: "+stopIndex);
            strLength = stopIndex+3-startCodon;
            //System.out.println("strLength inside loop: "+strLength);
            
        }
        //System.out.println("Final stop index: "+stopIndex);
        return stopIndex;
        //return dna.length();
    }
    
    public String findGene(String dna){
        /* Write the method findGene that has one String parameter dna, 
         * representing a string of DNA. In this method you should do the 
         * following:*/
         
         /*Find the index of the first occurrence of the start codon “ATG”. 
         * If there is no “ATG”, return the empty string.*/
         int firstATG = dna.indexOf("ATG");
         //System.out.println("first ATG: "+firstATG);
         if (firstATG == -1){return "";}
         /* Find the index of the first occurrence of the stop codon “TAA” 
         * after the first occurrence of “ATG” that is a multiple of three 
         * away from the “ATG”. Hint: call findStopCodon.*/
         /* Find the index of the first occurrence of the stop codon “TAG” 
         * after the first occurrence of “ATG” that is a multiple of three 
         * away from the “ATG”. Find the index of the first occurrence of 
         * the stop codon “TGA” after the first occurrence of “ATG” that 
         * is a multiple of three away from the “ATG”.
         */ 
        //System.out.println("Loc Stop Codon: "+locStopCodon(dna));
        if (locStopCodon(dna) == dna.length()){return "";}
        /* Return the gene formed from the “ATG” and the closest stop codon
         * that is a multiple of three away. If there is no valid stop codon
         * and therefore no gene, return the empty string.*/
        
        return dna.substring(firstATG,locStopCodon(dna)+3);
    }
    
    public int locStopCodon(String dna) {
        int firstATG = dna.indexOf("ATG");
        //System.out.println("Loc ATG: "+firstATG);
         if (firstATG == -1){return dna.length();}
        int firstTAA = findStopCodon(dna,firstATG,"TAA");
        //System.out.println("Loc TAA: "+firstTAA);
        int firstTAG = findStopCodon(dna,firstATG,"TAG");
        //System.out.println("Loc TAG: "+firstTAG);
        int firstTGA = findStopCodon(dna,firstATG,"TGA");
        //System.out.println("Loc TGA: "+firstTGA);
        /*System.out.println("Loc Stop Codon: "+
        Math.min(firstTAA,Math.min(firstTAG,firstTGA))); */
        return Math.min(firstTAA,Math.min(firstTAG,firstTGA)); 
    }
    
    public void testFindStopCodon() {
        /* Write the void method testFindStopCodon that calls the method 
         * findStopCodon with several examples and prints out the results 
         * to check if findStopCodon is working correctly. Think about what 
         * types of examples you should check. For example, you may want to 
         * check some strings of DNA that have genes and some that do not. 
         * What other examples should you check? */
        
        //             ATGv        
        String dna1 = "ATGACCCTAATTTTA";
        System.out.println("DNA: "+dna1);
        System.out.println(findStopCodon(dna1,0,"TAA"));

        //              ATG   TAA         
        String dna2 = "AATGACCTAACTAATTTAAATG";
        System.out.println("DNA: "+dna2);
        System.out.println(findStopCodon(dna2,0,"TAA"));

        //             ATGv     TAG   
        String dna3 = "ATGACCCTATAGTGA";
        System.out.println("DNA: "+dna3);
        System.out.println(findStopCodon(dna3,0,"TAG"));

        String dna4 = "ATTGACCCTATAGTGA";
        System.out.println("DNA: "+dna4);
        System.out.println(findStopCodon(dna4,0,"TAG"));

    }
    
    public void testFindGene() {
        /* Write the void method testFindGene that has no parameters. 
         * You should create five DNA strings. The strings should have 
         * specific test cases such as DNA with no “ATG”, DNA with “ATG” 
         * and one valid stop codon, DNA with “ATG” and multiple valid stop
         * codons, DNA with “ATG” and no valid stop codons. Think carefully
         * about what would be good examples to test. For each DNA string
         * you should:*/ 
          
         /* Print the DNA string.
          * Calculate the gene by sending this DNA string as an argument
          * to findGene. If a gene exists following our algorithm above, 
          * then print the gene, otherwise print the empty string.*/
          
        //             ATGv        
        String dna1 = "ATGACCCTAATTTTA";
        System.out.println("DNA : "+dna1);
        System.out.println("Gene: "+findGene(dna1));
        //              ATG   TAA         
        String dna2 = "AATGACCTAACTAATTTAAATG";
        System.out.println("DNA : "+dna2);
        System.out.println("Gene: "+findGene(dna2));
        //             ATGv     TAG   
        String dna3 = "ATGACCCTATAGTGA";
        System.out.println("DNA : "+dna3);
        System.out.println("Gene: "+findGene(dna3));
        
        String dna4 = "ATTGACCCTATAGTGA";
        System.out.println("DNA : "+dna4);
        System.out.println("Gene: "+findGene(dna4));
        
        String dna5 = "ATGATTGACCCTATAGTGAA";
        System.out.println("DNA : "+dna5);
        System.out.println("Gene: "+findGene(dna5));
        
    }
    
    public void printAllGenes(String dna) {
        /* Write the void method printAllGenes that has one String parameter 
         * dna, representing a string of DNA. In this method you should 
         * repeatedly find genes and print each one until there are no more 
         * genes. Hint: remember you learned a while(true) loop and break */
         while (true) {
             System.out.println("new dna: "+dna);
             String foundGene = findGene(dna);
             if (foundGene.length() == 0) {break;}
             System.out.println(foundGene);
             System.out.println("new stop codon loc:");
             System.out.println(locStopCodon(dna)+3);
             dna = dna.substring(locStopCodon(dna)+3,dna.length());
            }
    }
    
    ////////////////////////////
    
    public double cgRatio(String dna){
        /* Write the method cgRatio that has one String parameter dna, and 
         * returns the ratio of C’s and G’s in dna as a fraction of the 
         * entire strand of DNA. For example if the String were "ATGCCATAG" 
         * then cgRatio would return 4/9 or .4444444. */
         double cCount = 0.0;
         double gCount = 0.0;
         int indexC = dna.indexOf("C");
         int indexG = dna.indexOf("G");
         while (true) {
             
             if (indexC == -1){break;}
             indexC = dna.indexOf("C",indexC+1);
             cCount = cCount + 1.0;
            }
         while (true) {
             
             if (indexG == -1){break;}
             indexG = dna.indexOf("G",indexG+1);
             gCount = gCount + 1.0;
            }
         return (cCount+gCount)/dna.length();
    }
    
    public int countCTG(String dna){
        /* Write a method countCTG that has one String parameter dna, 
         * and returns the number of times the codon CTG appears in dna*/
         int ctgCount = 0;
         int ctgIndex = dna.indexOf("CTG");
         while(true){
             if (ctgIndex == -1){break;}
             ctgIndex = dna.indexOf("CTG",ctgIndex+1);
             ctgCount++;
            }
         return ctgCount;
    }
    
    public StorageResource getAllGenes(String dna) {
        /* Make a copy of the printAllGenes method called getAllGenes. 
         * Instead of printing the genes found, this method should create 
         * and return a StorageResource containing the genes found. 
         * Remember to import the edu.duke libraries otherwise you will get 
         * an error message cannot find the class StorageResource. */
         StorageResource sr = new StorageResource();
         while (true) {
             String foundGene = findGene(dna);
             if (foundGene.length() == 0) {break;}
             sr.add(foundGene);
             dna = dna.substring(locStopCodon(dna)+3,dna.length());
            }
            return sr;
    }
    
    public void processGenes(StorageResource sr){
        /* Write the void method processGenes that has one parameter sr, 
         * which is a StorageResource of strings. This method processes all 
         * the strings in sr to find out information about them. 
         * Specifically, it should:*/
         
        //print all Strings in sr that are longer than 9 characters
        //print the number of Strings in sr > 9 characters
        
        int countGenes = 0; // FOR DEBUGGING AND TESTING;
        
        int countStr9 = 0;
        System.out.println("Genes with length > 60");
        for (String item : sr.data()){
            if (item.length() > 60) {
                //System.out.println(item);
                countStr9++;
                
            }
            countGenes++;
        }
        System.out.println(countStr9);
        System.out.println("Total Count of genes (all sizes): "+countGenes);
        //print the Strings in sr whose C-G-ratio > 0.35
        //print the number of strings in sr whose C-G-ratio > 0.35
        int countCGR = 0;
        System.out.println("Genes with C-G-ratio > 0.35");
        for (String item : sr.data()){
            if (cgRatio(item) > 0.35) {
                //System.out.println(item);
                countCGR++;
            }
        }
        System.out.println(countCGR);
        
        //print the length of the longest gene in sr
        int lenLongest = 0;
        System.out.println("length of the longest gene:");
        for (String item : sr.data()){
            if (item.length() > lenLongest) {
                lenLongest = item.length();
            }
        }
        System.out.println(lenLongest);
    }
    
    public void testProcessGenes() {
        /* Write a method testProcessGenes. This method will call your 
         * processGenes method on different test cases. Think of five DNA 
         * strings to use as test cases. These should include: one DNA string
         * that has some genes longer than 9 characters, one DNA string that
         * has no genes longer than 9 characters, one DNA string that has
         * some genes whose C-G-ratio is higher than 0.35, and one DNA string
         * that has some genes whose C-G-ratio is lower than 0.35. Write code
         * in testProcessGenes to call processGenes five times with
         * StorageResources made from each of your five DNA string test cases.
         */
        //              v    12    v   v     15      v
        String dna1 = "AATGTCCGGATAGTAGATGGGGGTAGGATAAA";
        //                v   9   v v   9   v
        String dna2 = "TGAATGTTATAATATGAAATAATAG";
        //                 v    12    vv     15      v
        String dna3 = "ACTGATGCCCCCCTAGATGGGGGGGGGGTAATAA";
        //                 v   9   vv    12    v
        String dna4 = "ACTGATGTTTTAAATGAATGAATAATAA";
        //             v    9  v   v   9   v   v   9   v
        String dna5 = "ATGATGTAATAAATGAAATGATGAATGATGTAG";
        
        System.out.println("Testing dna1: "+dna1);
        processGenes(getAllGenes(dna1));
        System.out.println("===");
        System.out.println("Testing dna2: "+dna2);
        processGenes(getAllGenes(dna2));
        System.out.println("===");
        System.out.println("Testing dna3: "+dna3);
        processGenes(getAllGenes(dna3));
        System.out.println("===");
        System.out.println("Testing dna4: "+dna4);
        processGenes(getAllGenes(dna4));
        System.out.println("===");
        System.out.println("Testing dna5: "+dna5);
        processGenes(getAllGenes(dna5));
        System.out.println("===");
    }
    
    public void testBRCA1() {
        FileResource fr = new FileResource("brca1line.fa");
        String dna1 = fr.asString().toUpperCase();
        //System.out.println(dna1);
        processGenes(getAllGenes(dna1));
    }
    
    public void testGRch38dnapart(){
        //
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna1 = fr.asString().toUpperCase();
        //System.out.println(dna1);
        processGenes(getAllGenes(dna1));
        System.out.println("Count of CTGs: "+countCTG(dna1));
    }
    
    public void testGetAllGenes() {
        System.out.println("Testing getAllGenes()"); 
        String dnaString = "TTTATGATTGACCCTATAGTGTAGATGCGCTAG";
        System.out.println("DNA String: "+dnaString); 
        StorageResource testResource = getAllGenes(dnaString);
        for (String item : testResource.data()){
           System.out.println(item); 
        }
    }
    
    public void testCGRatio() {
        System.out.println("CG Ratio: "+cgRatio("ATGCCATAG"));
    }
    
    public void testCTGCount() {
        System.out.println("CTG count: "+countCTG("CTGCTGCTGTGCTG"));
    }
    
    public void debugTesting(){
        // Commands for testing purposes:
        
        //testFindStopCodon(); //test function findStopCodon
        //testFindGene(); //test function findGene
        //System.out.println("Gene: "+"TAAATGAAATGATGAATGATGTAG");
        //locStopCodon("TAAATGAAATGATGAATGATGTAG");
        
        // testing findStopCodon:
        System.out.println("===findstopcodon:");
        System.out.println("Gene: "+"TAAATGAAATGATGAATGATGTAG");
        System.out.println("parameters: start=3, stopCodon: TAA");
        System.out.println(findStopCodon("TAAATGAAATGATGAATGATGTAG",3,"TAA"));
        
        //test method printAllGenes:
        //printAllGenes("ATGATGTAATAAATGAAATGATGAATGATGTAG"); 
        //testGetAllGenes();
        //testCGRatio();
        //testCTGCount();
        
        
        //testProcessGenes();
    }
    
    public static void main (String[] args) {
        Part7 p1 = new Part7();
        System.out.println("===== Start ======");
        
        //p1.testProcessGenes();
        //p1.testBRCA1();
        p1.testGRch38dnapart();
        
        System.out.println("===== Finish =====");
    }
}
