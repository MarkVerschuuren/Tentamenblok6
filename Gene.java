/**
 * Created by Mark Verschuuren on 4-2-2017.
 */
public class Gene implements Comparable<Gene> {
    private String GeneID;
    private String interaction;
    private String TaxID;
    private String ProductName;
    private String productnum;
    private String pub;

    public Gene(String ID, String inter, String tax, String name, String number, String Pubmed ){
        GeneID = ID;
        interaction = inter;
        TaxID = tax;
        ProductName = name;
        productnum = number;
        pub = Pubmed;




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
    public void setTax(String c){
        TaxID = c;
    }
    public String getTax(){
        return TaxID;
    }
    public void setName(String d){
        ProductName= d;
    }
    public String getName(){
        return ProductName;
    }
    public void setNum(String e){
        ProductName= e;
    }
    public String getNum (){
        return productnum;
    }
    public void setPub(String f){
        pub = f;
    }
    public String getPub(){
        return pub;
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
