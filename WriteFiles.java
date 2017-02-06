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
    AnalyseFile AnalyseObj = new AnalyseFile();
    BufferedWriter writer;
    BufferedWriter writer2;
    ArrayList<String> arr1 = new ArrayList<>();
    ArrayList<String> arr2 = new ArrayList<>();

    public void WriteGen(String key1, String key2, ArrayList<Gene> inters, String NameTextFile) throws NotValid{
        if(overlapObj.getOverlapGenes().isEmpty()){
            throw new NotValid("There is not overlap, so you can make any files");
        }

        try {

            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(NameTextFile), "utf-8"));
            writer2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(NameTextFile), "utf-8"));
            if(NameTextFile.equals("Gen.txt")){
                writer.write("GeneID" + "\t" + "Interaction" + "\t" + "Productname" + "\t" + "ProductName" + "\n");
            }

            if(NameTextFile.equals("PuhMed.txt")){
                writer2.write("GeneID" + "\t" + "Pubmed" + "\n");
            }
            for (int count = 0; count < inters.size(); count++) {
                Gene g1 = (Gene) inters.get(count);
                for (String gen : overlapObj.getOverlapGenes()) {
                    if (g1.getGenID().equals(gen) && g1.getinter().equals(key1) && !arr1.contains(g1.getGenID())) {

                        arr1.add(g1.getGenID());
                        if(NameTextFile.equals("Gen.txt"))
                            writer.write(g1.getGenID() + "\t" + g1.getinter() + "\t" + g1.getName() + "\t" + g1.getNum() + "\t" + "\n");
                        else{
                            System.out.println("h");
                            writer2.write(g1.getGenID() + "\t" + g1.getPub() + "\n");
                        }
                    }
                    if (g1.getGenID().equals(gen) && g1.getinter().equals(key2) && !arr2.contains(g1.getGenID())) {
                        arr2.add(g1.getGenID());

                        if(NameTextFile.equals("Gen.txt")){
                            writer.write(g1.getGenID() + "\t" + g1.getinter() + "\t" + g1.getName() + "\t" + g1.getNum() + "\t" + "\n");

                        }
                        else{
                            System.out.println("ha");
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