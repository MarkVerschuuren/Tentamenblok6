import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Mark Verschuuren on 5-2-2017.
 */
public class Overlap {
    int overlap = 0;
    int bkey1int = 0;
    int bkey2int = 0;
    int akey1int = 0;
    int akey2int = 0;
    static HashSet<String> overlapGenes;

    /**
     * Gets the overlap of the interactions by getting the difference between before retainall and after retainall.
     *
     * @param key1 Key1 is the left interactions chosen by the user in the combobox, it is used to get the values from the hashmap.
     * @param key2 Key2 is the right interactions chosen by the user in the combobox, it is used to get the values from the hashmap.
     * @param collectionMap HashMap which contains an interaction as key and the GeneID's which are connected to that interaction.
     */
    public void AnalyseoverLap(String key1, String key2, HashMap<String, HashSet<String>> collectionMap){


        bkey1int = collectionMap.get(key1).size();
        bkey2int = collectionMap.get(key2).size();
        collectionMap.get(key1).retainAll(collectionMap.get(key2));
        overlap = collectionMap.get(key1).size();
        akey1int = bkey1int - overlap;
        akey2int = bkey2int  - overlap;
        overlapGenes = collectionMap.get(key1);
    }

    /**
     *
     * @return Returns the size of the overlap.
     */

    public int getoverlapsize(){
        return overlap;
    }
    /**
     *
     * @return Returns the size before retainall of interaction 1.
     */
    public int getbkey1int(){
        return bkey1int;
    }
    /**
     *
     * @return Returns the size before retainall of interaction 2.
     */
    public int getbkey2int(){
        return bkey2int;
    }
    /**
     *
     * @return Returns the size after retainall of interaction 1.
     */
    public int getakey1int(){
        return akey1int;
    }
    /**
     *
     * @return Returns the size after retainall of interaction 2.
     */
    public int getakey2int(){
        return akey2int;
    }
    /**
     *
     * @return Returns an hashset which contains all the genes who are in the overlap.
     */
    public HashSet<String> getOverlapGenes(){
        return overlapGenes;
    }
}
