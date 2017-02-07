/**
 * Created by Mark Verschuuren on 1-2-2017.
 */
import java.io.*;
import java.util.*;

public class AnalyseFile {
    BufferedReader inFile;
    HashSet<String> GenA = new HashSet<>();
    HashSet<String> GenB = new HashSet<>();
    static HashSet<String> UniqueInteractions = new HashSet<>();
    ArrayList<Gene> TotalInteractions = new ArrayList<>();
    static HashMap<String, HashSet<String>> InteractionMap = new HashMap<>();

    /**
     * Method which reads the file and makes some hashset and arraylist to get some statistics. The first line of the file isnt added to the datastructures
     * Also this method sorts the TotalInteractions on Interactions. This happens in CompareTo within the gene class.
     *
     *
     * @param Path String which defines the pathway the user has chosen. It is used to open the chosen file.
     */

    public void readFile(String Path) {
        try {
            inFile = new BufferedReader(new FileReader(Path));
            String line;
            inFile.readLine();

            while ((line = inFile.readLine()) != null) {
                String[] lines = line.split("\t");
                GenA.add(lines[1]);
                GenB.add(lines[6]);
                UniqueInteractions.add(lines[4]);
                TotalInteractions.add(new Gene(lines[1], lines[4],lines[0],lines[2], lines[3],lines[9]));
            }

            Collections.sort(TotalInteractions); // Sorting the interactions from A-Z


            Hashmapper(TotalInteractions);
            inFile.close();
        } catch (IOException e) {
            System.out.println("Error in reading the file!");
        }
    }


    /**
     *Creates a HashMap with an interaction as key and an hashset with the connected GenID's attached to it.
     * Because now all the interactions are sorted you can determine when there is a different interaction and you can put the previous interactions
     * into the hashmap as an hashset.
     *
     * 
     * @param inters Arraylist which contains all the Gene objects from the file.
     */
    public void Hashmapper(ArrayList<Gene> inters) {
        HashSet<String> GenIdSet = new HashSet<>();
        for(int count = 0 ; count < inters.size()-1;count++){
            Gene g1 = (Gene) inters.get(count);
            Gene g2 = (Gene) inters.get(count+1);

            if (g1.getinteraction().equals(g2.getinteraction())) {
                GenIdSet.add(g1.getGenID());
                GenIdSet.add(g2.getGenID());
                InteractionMap.put(g1.getinteraction(),GenIdSet);
            }
            else{
                GenIdSet = new HashSet<>();
                GenIdSet.add(g1.getGenID());
                InteractionMap.put(g2.getinteraction(),GenIdSet);
            }
        }
    }

    /**
     *
     * @return Returns the size of all GenID1.
     */
    public int getASize(){
        return GenA.size();
    }

    /**
     *
     * @return Returns the size of all GenID2.
     */
    public int getBSize(){
        return GenB.size();
    }
    /**
     *
     * @return Returns the size of all unique interactions.
     */
    public int getUniqueInterSize(){
        return UniqueInteractions.size();
    }
    /**
     *
     * @return Returns an hashset with all unique interactions.
     */
    public HashSet<String> getUniqueInter(){
        return UniqueInteractions;
    }
    /**
     *
     * @return Returns the size of all uniqueinteractions.
     */
    public int getTotalInteractions(){
        return TotalInteractions.size();
    }
    /**
     *
     * @return Returns an arraylist of Geneobjects of all interactions.
     */
    public ArrayList<Gene> getInters(){
        return TotalInteractions;
    }

    /**
     *
     * @return Returns a hashmap with interactions and the corresponding GenID's of these interactions.
     */
    public HashMap<String, HashSet<String>> getHashMapgenes(){
        return InteractionMap;
    }
}
