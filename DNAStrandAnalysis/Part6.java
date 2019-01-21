
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part6 {
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
        int stopIndex = dna.indexOf(stopCodon,startCodon+1);
        int strLength = stopIndex+3-startCodon;
        while (strLength%3 != 0){
            stopIndex = dna.indexOf(stopCodon,stopIndex+1);
            strLength = stopIndex+3-startCodon;
            if (stopIndex == -1) {
                stopIndex = dna.length();
                break;
            }
        }
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
        
        if (locStopCodon(dna) == dna.length()){return "";}
        /* Return the gene formed from the “ATG” and the closest stop codon
         * that is a multiple of three away. If there is no valid stop codon
         * and therefore no gene, return the empty string.*/
        
        return dna.substring(firstATG,locStopCodon(dna)+3);
    }
    public int locStopCodon(String dna) {
        int firstATG = dna.indexOf("ATG");
         if (firstATG == -1){return dna.length();}
        int firstTAA = findStopCodon(dna,firstATG,"TAA");
        int firstTAG = findStopCodon(dna,firstATG,"TAG");
        int firstTGA = findStopCodon(dna,firstATG,"TGA");
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
             String foundGene = findGene(dna);
             if (foundGene.length() == 0) {break;}
             System.out.println(foundGene);
             dna = dna.substring(locStopCodon(dna)+3,dna.length());
            }
    }
    
    public int countGenes(String dna) {
        /* Write the method named countGenes that has a String parameter 
         * named dna representing a string of DNA. This method returns the 
         * number of genes found in dna. For example the call 
         * countGenes(“ATGTAAGATGCCCTAGT”) returns 2, finding the gene 
         * ATGTAA first and then the gene ATGCCCTAG. Hint: This is very 
         * similar to finding all genes and printing them, except that 
         * instead of printing all the genes you will count them. */
         int countFound = 0;
         while (true) {
             String foundGene = findGene(dna);
             if (foundGene.length() == 0) {break;}
             countFound++;
             dna = dna.substring(locStopCodon(dna)+3,dna.length());
            }
         return countFound;
    }
    
    public void testCountGenes() {
        /* Write the void method named testCountGenes that has no parameters.
         * This method calls countGenes with many example strings and prints
         * the result for each. You should create several examples with 
         * different numbers of genes to test your code. */
         System.out.println(countGenes("ATGTAAGATGCCCTAGT")); //returns 2
         System.out.println(countGenes("TTTATGATTGACCCTATAGTGTAGATGCGCTAG"
         )); //returns 2 
         System.out.println(countGenes("ATTGACCCTATAGTGA")); // returns 0
         System.out.println(countGenes("AATGACCTAACTAATTTAAATG"));//returns 1
         System.out.println(countGenes("AATGCTAACTAGCTGACTAAT"));
    }
    
    public static void main (String[] args) {
        Part6 p3 = new Part6();
        System.out.println("===== Start ======");
        //p3.testFindStopCodon(); //test function findStopCodon
        //p3.testFindGene(); //test function findGene
        
        //test method printAllGenes:
        //p3.printAllGenes("AATGCTAACTAGCTGACTAAT"); 
        p3.testCountGenes();
        System.out.println("===== Finish =====");
    }
}
