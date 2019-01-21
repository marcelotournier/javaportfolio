
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
   public String findSimpleGene (String dna) {
       //Finds the index position of the start codon “ATG”
       int startCodon = dna.indexOf("ATG");
       //If there is no “ATG”, return the empty string
       if (startCodon == -1){
           return "";
        }
       //Finds the index position of the first stop codon “TAA” appearing after 
       //the “ATG” that was found
       int stopCodon = dna.indexOf("TAA",startCodon+3)+3;
       //If there is no such “TAA”, return the empty string.
       if (stopCodon == -1){
           return "";
        }
       //If the length of the substring between the “ATG” and “TAA” is a 
       // multiple of 3, then return the substring that starts with that “ATG”
       // and ends with that “TAA”.
       String gene = dna.substring(startCodon,stopCodon);
       if ((stopCodon-startCodon)%3 != 0){
           return "";
        }
       //System.out.println("Start: "+startCodon+"| Stop: "+stopCodon);
       return gene;
    }
   
   public void testSimpleGene() {
       /*
        * You should create five DNA strings. The strings should have specific 
        * test cases, such as: DNA with no “ATG”, DNA with no “TAA”, DNA with 
        * no “ATG” or “TAA”, DNA with ATG, TAA and the substring between them 
        * is a multiple of 3 (a gene), and DNA with ATG, TAA and the substring 
        * between them is not a multiple of 3. For each DNA string you should:
        * Print the DNA string.
        * See if there is a gene by calling findSimpleGene with this string as 
        * the parameter. If a gene exists following our algorithm above, 
        * then print the gene, otherwise print the empty string.
       */
       String dna1 = "AGGACGAATTAA"; //DNA with no ATG
       System.out.println("DNA String: "+dna1);
       System.out.println(findSimpleGene(dna1));
       String dna2 = "ATATGGCTCAGATTA"; //DNA with no TAA
       System.out.println("DNA String: "+dna2);
       System.out.println(findSimpleGene(dna2));
       String dna3 = "ATTAGCTCAGATTAG";//DNA with no ATG & no TAA
       System.out.println("DNA String: "+dna3);
       System.out.println(findSimpleGene(dna3));
       String dna4 = "ATTATGCTCGAATAAG";//DNA with ATG & TAA, multiple of 3 (GENE)
       System.out.println("DNA String: "+dna4);
       System.out.println(findSimpleGene(dna4));
       String dna5 = "ATTATGCTCAATAAG";//DNA with ATG & TAA, not multiple of 3
       System.out.println("DNA String: "+dna5);
       System.out.println(findSimpleGene(dna5));
       String dna6 = "AAATGCCCTAACTAGATTAAGAAACC";//Test question
       System.out.println("DNA String: "+dna6);
       System.out.println(findSimpleGene(dna6));
    }
   
   public static void main (String[] args) {
        Part1 p1 = new Part1();
        System.out.println("*** Testing DNA for Genes... ***");
        p1.testSimpleGene();
        System.out.println("***        Finished!         ***");
    }
}
