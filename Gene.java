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

    /**
     * GeneObject which saves all the data from 1 line, it can then be referenced later because the objects are known.
     *
     * @param ID ID from the gene.
     * @param inter Interaction of the gene.
     * @param tax TaxID of the gene.
     * @param name ProductName of the gene.
     * @param number ProductNumber of the gene.
     * @param Pubmed Pubmed value of the gene.
     */
    public Gene(String ID, String inter, String tax, String name, String number, String Pubmed ){
        GeneID = ID;
        interaction = inter;
        TaxID = tax;
        ProductName = name;
        productnum = number;
        pub = Pubmed;




    }

    /**
     *
     * @param a variable to set GenID
     */
    public void setGeneID(String a){
        GeneID = a;
    }
    /**
     *
     * @return get GenID from Gene object
     */
    public String getGenID(){
        return GeneID;
    }
    /**
     *
     * @param b variable to set interaction
     */
    public void setinter(String b){
        interaction = b;
    }

    /**
     *
     * @return @return get interaction from Gene object
     */
    public String getinteraction(){
        return interaction;
    }

    /**
     *
     * @param c variable to set taxID
     */

    public void setTax(String c){
        TaxID = c;
    }
    /**
     *
     * @return return the Tax id
     */
    public String getTax(){
        return TaxID;
    }

    /**
     *
     * @param d variable to set product name
     */
    public void setName(String d){
        ProductName= d;
    }
    /**
     *
     * @return return the product name
     */
    public String getName(){
        return ProductName;
    }

    /**
     *
     * @param e variable to set product number
     */
    public void setNum(String e){
        ProductName= e;
    }

    /**
     *
     * @return return the productnumber
     */
    public String getNum (){
        return productnum;
    }



    public void setPub(String f){
        pub = f;
    }
    public String getPub(){
        return pub;
    }


    /**
     *
     * @param gen gen is a genobject which has to be given to the comparable
     * @return return 0, 1 or -1. 0 when the values are the same, 1 when the interaction has a higher value than the other interaction
     * and -1 when the interaction has a lower value than the other interaction.
     */

    @Override
    public int compareTo(Gene gen){
        if(gen.getinteraction().compareTo(this.getinteraction()) > 0){
            return -1;
        }
        if(gen.getinteraction().compareTo(this.getinteraction()) < 0){
            return 1;
        }
        else{
            return 0;
        }



    }
}
