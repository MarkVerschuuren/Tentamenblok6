import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by Mark Verschuuren on 6-2-2017.
 */
public class WriteFiles {
    Overlap overlapObj = new Overlap();
    BufferedWriter writer;
    BufferedWriter writer2;
    ArrayList<String> ControlInteraction1 = new ArrayList<>();
    ArrayList<String> ControlInteraction2 = new ArrayList<>();

    /**
     * When the button exportGen or ExportPub are clicked this method will be activated. It places all the overlapping genes in an text file.
     * Or places all the overlapping genes with pubmed ID in a textfile.
     *
     *
     *
     * @param key1 Key1 is the left interactions chosen by the user in the combobox, it is used to get the values from the hashmap.
     * @param key2 Key2 is the right interactions chosen by the user in the combobox, it is used to get the values from the hashmap.
     * @param inters Arraylist which contains all the Gene objects from the file.
     * @param NameTextFile String which defines the name of the file which has to be made, it can be Gen.txt or PubMed.txt.
     * @throws NotValid Custom exception which gives an error when there are no genes who are in the overlap.
     */

    public void WriteGen(String key1, String key2, ArrayList<Gene> inters, String NameTextFile) throws NotValid{
        if(overlapObj.getOverlapGenes().isEmpty()){
            throw new NotValid("There is not overlap, so you can make any files");
        }

        try {

            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(NameTextFile), "utf-8"));
            writer2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(NameTextFile), "utf-8"));
            if(NameTextFile.equals("Gen.txt")){
                writer.write("GeneID" + "\t" + "Interaction" + "\t" + "Productname" + "\t" + "ProductNumber" + "\n");
            }

            if(NameTextFile.equals("PubMed.txt")){
                writer2.write("GeneID" + "\t" + "Pubmed" + "\n");
            }
            for (int count = 0; count < inters.size(); count++) {
                Gene g1 = (Gene) inters.get(count);
                for (String gen : overlapObj.getOverlapGenes()) {
                    if (g1.getGenID().equals(gen) && !ControlInteraction1.contains(g1.getGenID())) {

                        ControlInteraction1.add(g1.getGenID());
                        if(NameTextFile.equals("Gen.txt"))
                            writer.write(g1.getGenID() + "\t" + g1.getinteraction() + "\t" + g1.getName() + "\t" + g1.getNum() + "\t" + "\n");
                        else{
                            writer2.write(g1.getGenID() + "\t" + g1.getPub() + "\n");
                        }
                    }
                }
            }
            writer.close();
            writer2.close();
        } catch (IOException e) {
            System.out.println("Input output error!");




        }
    }
}


class NotValid extends Exception{
    public NotValid(){
        super();

    }
    public NotValid(String err){
        super(err);
    }
}