/**
 * Created by Mark Verschuuren on 4-2-2017.
 */
public class Gene implements Comparable<Gene> {
    private String GeneID;
    private String interaction;
    private String TaxID;
    private String ProductName;
    private String productnum;

    public Gene(String ID, String inter, String tax, String name, String number ){
        GeneID = ID;
        interaction = inter;
        TaxID = tax;
        ProductName = name;
        productnum = number;



    }
    public void setGeneID(String a){
        GeneID = a;
    }
    public String getGenID(){
        return GeneID;
    }
    public void setinter(String b){
        interaction = b;
    }
    public String getinter(){
        return interaction;
    }


    @Override
    public int compareTo(Gene gen){
        if(gen.getinter().compareTo(this.getinter()) > 0){
            return -1;
        }
        if(gen.getinter().compareTo(this.getinter()) < 0){
            return 1;
        }
        else{
            return 0;
        }



    }
}
