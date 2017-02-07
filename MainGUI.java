/**
 * Created by Mark Verschuuren on 1-2-2017.
 */
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class MainGUI extends JFrame implements ActionListener {
    JLabel labelFile, labelSelect;
    JTextField textFieldFile;
    JTextArea textAreaInfo;
    JButton buttonSearch, buttonOpen, buttonAnalyse, buttonExportGen, buttonExportPub;
    JComboBox comboBoxLeft, comboBoxRight;
    JFileChooser fileChooser;
    JPanel panelVenn;
    String key1, key2;
    AnalyseFile AnalyseObj = new AnalyseFile();

    /**
     * Constructor for the GUI. Sets size and some other configurations. The method to create the object in the GUI is defined here.
     */
    public MainGUI() {
        setTitle("Interactions Comparator");
        setSize(700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);


        CreateView();
    }

    /**
     * Method to create all the object in the GUI. ALl the button get an actionlistener.
     */
    public void CreateView() {
        JPanel panelMain = new JPanel();
        panelMain.setPreferredSize(new Dimension(700, 700));
        panelMain.setBorder(new TitledBorder("Main"));
        add(panelMain);

        labelFile = new JLabel("File:");
        panelMain.add(labelFile);

        textFieldFile = new JTextField(20);
        panelMain.add(textFieldFile);

        buttonSearch = new JButton("Search");
        buttonSearch.addActionListener(this);
        panelMain.add(buttonSearch);

        buttonOpen = new JButton("Open");
        buttonOpen.addActionListener(this);
        panelMain.add(buttonOpen);

        textAreaInfo = new JTextArea();
        textAreaInfo.setPreferredSize(new Dimension(600, 100));
        panelMain.add(textAreaInfo);

        labelSelect = new JLabel("Select types of interaction");
        panelMain.add(labelSelect);

        comboBoxLeft = new JComboBox();
        comboBoxRight = new JComboBox();
        panelMain.add(comboBoxLeft);
        panelMain.add(comboBoxRight);

        JPanel panelOutput = new JPanel();
        panelOutput.setPreferredSize(new Dimension(700, 400));
        panelMain.add(panelOutput);

        panelVenn = new JPanel();
        panelVenn.setPreferredSize(new Dimension(425, 350));
        panelOutput.add(panelVenn);

        JPanel panelButton = new JPanel();
        panelButton.setPreferredSize(new Dimension(200, 200));
        panelOutput.add(panelButton);


        buttonAnalyse = new JButton("Analyse");
        buttonAnalyse.setPreferredSize(new Dimension(150,25));
        buttonAnalyse.addActionListener(this);
        panelButton.add(buttonAnalyse);


        buttonExportGen = new JButton("Export Gen");
        buttonExportGen.setPreferredSize(new Dimension(150,25));
        buttonExportGen.addActionListener(this);
        panelButton.add(buttonExportGen);

        buttonExportPub = new JButton("Export Pubmed");
        buttonExportPub.setPreferredSize(new Dimension(150,25));
        buttonExportPub.addActionListener(this);
        panelButton.add(buttonExportPub);


    }

    /**
     * Method which get activated when there is an click on any button. The event.getSource statements define which button will be used.
     * The file gets selected with the buttonSearch.
     *
     * The file gets read with the buttonOpen. This will happen in Analysefile. The data wil also be analysed and will show some statistics.
     *
     * buttonAnalyse analyses the data which then will show an overlap.
     *
     * buttonExportgen add all overlapping genes in a textfile with the corresponding information of this gene.
     * buttonExportPub add all overlapping genes in a textfile with the corresponding pubmed ID.
     * When there are no overlapping genes there will be an screen which says you cant put any genes in the file, because there are none.
     *
     *
     * @param event Variable which know which button is activated when.
     */
    public void actionPerformed(ActionEvent event) {

        int path;
        File selectedFile;
        if (event.getSource() == buttonSearch) {
            fileChooser = new JFileChooser();
            path = fileChooser.showSaveDialog(this);
            if (path == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                textFieldFile.setText(selectedFile.getAbsolutePath());
            }

        }

        if (event.getSource() == buttonOpen) {

            AnalyseObj.readFile(textFieldFile.getText());

            textAreaInfo.setText("Tax ID1 : \t\t" + AnalyseObj.getASize() + " genes\n" + "Tax ID2 : \t\t"
                    + AnalyseObj.getBSize() + " genes\n" + "# interactions: \t\t" + AnalyseObj.getTotalInteractions() + " interactions \n"
                    + "# types of interactions: \t" + AnalyseObj.getUniqueInterSize() + " interactions");

            ArrayList<String> UniqInteractionSorted = new ArrayList<>(AnalyseObj.UniqueInteractions);
            Collections.sort(UniqInteractionSorted);

            for (String inter : UniqInteractionSorted) {

                comboBoxLeft.addItem(inter);
                comboBoxRight.addItem(inter);
            }
        }

        if (event.getSource() == buttonAnalyse) {

            key1 = comboBoxLeft.getSelectedItem().toString();
            key2 = comboBoxRight.getSelectedItem().toString();
            Overlap overlapObj = new Overlap();
            overlapObj.AnalyseoverLap(key1, key2, AnalyseObj.InteractionMap);

            Graphic(overlapObj.getakey1int(), overlapObj.getakey2int(), overlapObj.getoverlapsize(),key1,key2);

        }
        if (event.getSource() == buttonExportGen) {
            WriteFiles writeObj = new WriteFiles();

            try{
                writeObj.WriteGen(key1, key2, AnalyseObj.TotalInteractions, "Gen.txt");
            }
            catch (NotValid e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }


        }

        if(event.getSource() == buttonExportPub){
            WriteFiles writeObj = new WriteFiles();
            try{
                writeObj.WriteGen(key1, key2, AnalyseObj.TotalInteractions, "PubMed.txt");
            }
            catch (NotValid e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        }
    }


    /**
     * This method makes the circles which visualize the overlap. It also show which geneID are not in the overlap.
     *
     * @param left Variable to define the left part of interaction, it contains a int which defines the amount of genes who do not overlap
     * @param right Variable to define the right part of interaction, it contains a int which defines the amount of genes who do not overlap
     * @param middle Variable to define the overlap.
     */
    public void Graphic(int left, int right, int middle,String key1, String key2){
        Graphics g = panelVenn.getGraphics();
        g.drawString(key1, 110, 80);
        g.drawString(Integer.toString(left),165,165);
        g.drawOval(210,95,150,150);
        g.drawString(key2, 270, 80);
        g.drawString(Integer.toString(middle),220,165);
        g.drawOval(90,95,150,150);
        g.drawString(Integer.toString(right),270,165);

    }

    public static void main(String[] args) {
        new MainGUI().setVisible(true);
    }
}
