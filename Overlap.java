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

    public void AnalyseoverLap(String key1, String key2, HashMap<String, HashSet<String>> collectionMap){


        bkey1int = collectionMap.get(key1).size();
        bkey2int = collectionMap.get(key2).size();
        collectionMap.get(key1).retainAll(collectionMap.get(key2));
        overlap = collectionMap.get(key1).size();
        akey1int = bkey1int - overlap;
        akey2int = bkey2int  - overlap;
        overlapGenes = collectionMap.get(key1);
    }

    public int getoverlap(){
        return overlap;
    }
    public int getbkey1int(){
        return bkey1int;
    }
    public int getbkey2int(){
        return bkey2int;
    }
    public int getakey1int(){
        return akey1int;
    }
    public int getakey2int(){
        return akey2int;
    }
    public HashSet<String> getOverlapGenes(){
        return overlapGenes;
    }
}
