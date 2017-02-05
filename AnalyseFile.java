/**
 * Created by Mark Verschuuren on 1-2-2017.
 */
import java.io.*;
import java.util.*;

public class AnalyseFile {
    BufferedReader inFile;
    HashSet<String> GenA = new HashSet<>();
    HashSet<String> GenB = new HashSet<>();
    HashSet<String> UniqueInteractions = new HashSet<>();
    ArrayList<Gene> TotalInteractions = new ArrayList<>();


    static HashMap<String, HashSet<String>> InteractionSet = new HashMap<>();

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
                TotalInteractions.add(new Gene(lines[1], lines[4],lines[0],lines[2], lines[3]));


            }
            Collections.sort(TotalInteractions);

            for (Object s : TotalInteractions) {
                Gene k = (Gene) s;
            }
            Hashmapper(TotalInteractions);
            inFile.close();
        } catch (IOException e) {
            System.out.println("Error in reading the file!");
        }

    }

    public void Hashmapper(ArrayList<Gene> inters) {
        HashSet<String> GenIdSet = new HashSet<>();
        for(int count = 0 ; count < inters.size()-1;count++){

            Gene g1 = (Gene) inters.get(count);
            Gene g2 = (Gene) inters.get(count+1);

          

            if (g1.getinter().equals(g2.getinter())) {
                GenIdSet.add(g1.getGenID());
                GenIdSet.add(g2.getGenID());
                InteractionSet.put(g1.getinter(),GenIdSet);

            }
            else{
                GenIdSet = new HashSet<>();
                GenIdSet.add(g1.getGenID());
                InteractionSet.put(g2.getinter(),GenIdSet);

            }
        }




    }







    public int getASize(){
        return GenA.size();
    }
    public int getBSize(){
        return GenB.size();
    }
    public int getUniqueInterSize(){
        return UniqueInteractions.size();
    }
    public HashSet<String> getUniqueInter(){
        return UniqueInteractions;
    }
    public int getTotalInteractions(){
        return TotalInteractions.size();
    }
    public ArrayList<Gene> getInters(){
        return TotalInteractions;
    }
    public HashMap<String, HashSet<String>> getHashMapgenes(){
        return InteractionSet;
    }
}
