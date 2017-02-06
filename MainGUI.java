/**
 * Created by Mark Verschuuren on 1-2-2017.
 */
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class MainGUI extends JFrame implements ActionListener {
    JLabel labelFile, labelSelect;
    JTextField textFieldFile;
    JTextArea textAreaInfo;
    JButton buttonSearch, buttonOpen, buttonAnalyse, buttonExportGen, buttonExportPub;
    JComboBox comboBoxLeft, comboBoxRight;
    JFileChooser fileChooser;
    JPanel panelVenn;
    String key1, key2;
    Overlap overlapObj = new Overlap();
    AnalyseFile AnalyseObj = new AnalyseFile();
    BufferedWriter writer;
    ArrayList<String> arr1 = new ArrayList<>();
    ArrayList<String> arr2 = new ArrayList<>();

    public MainGUI() {
        setTitle("Interactions Comparator");
        setSize(700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);


        CreateView();
    }

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
        textAreaInfo.setPreferredSize(new Dimension(600, 200));
        panelMain.add(textAreaInfo);

        labelSelect = new JLabel("Select types of interaction");
        panelMain.add(labelSelect);

        comboBoxLeft = new JComboBox();
        comboBoxRight = new JComboBox();
        panelMain.add(comboBoxLeft);
        panelMain.add(comboBoxRight);

        JPanel panelOutput = new JPanel();
        panelOutput.setPreferredSize(new Dimension(700, 400));
        panelOutput.setBorder(new TitledBorder("Output"));
        panelMain.add(panelOutput);

        panelVenn = new JPanel();
        panelVenn.setPreferredSize(new Dimension(425, 350));
        panelVenn.setBorder(new TitledBorder("Vennborder"));
        panelOutput.add(panelVenn);

        JPanel panelwhy = new JPanel();
        panelwhy.setPreferredSize(new Dimension(200, 200));
        panelwhy.setBorder(new TitledBorder("why"));
        panelOutput.add(panelwhy);


        buttonAnalyse = new JButton("Analyse");
        buttonAnalyse.addActionListener(this);
        panelwhy.add(buttonAnalyse);


        buttonExportGen = new JButton("Export Gen");
        buttonExportGen.addActionListener(this);
        panelwhy.add(buttonExportGen);

        buttonExportPub = new JButton("Export Pubmed");
        buttonExportPub.addActionListener(this);
        panelwhy.add(buttonExportPub);


    }

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


            for (String inter : AnalyseObj.UniqueInteractions) {
                comboBoxLeft.addItem(inter);
                comboBoxRight.addItem(inter);
            }
        }

        if (event.getSource() == buttonAnalyse) {

            key1 = comboBoxLeft.getSelectedItem().toString();
            key2 = comboBoxRight.getSelectedItem().toString();
            Overlap overlapObj = new Overlap();
            overlapObj.AnalyseoverLap(key1, key2, AnalyseObj.InteractionSet);

            Graphic(overlapObj.getakey1int(), overlapObj.getakey2int(), overlapObj.getoverlap());

        }
        if (event.getSource() == buttonExportGen) {
            WriteFiles writeObj = new WriteFiles();

            try{
                writeObj.WriteGen(key1, key2, AnalyseObj.TotalInteractions, "PuhMed.txt");
            }
            catch (NotValid e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }


        }

        if(event.getSource() == buttonExportPub){
            WriteFiles writeObj = new WriteFiles();
            try{
                writeObj.WriteGen(key1, key2, AnalyseObj.TotalInteractions, "PuhMed.txt");
            }
            catch (NotValid e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        }
    }


    /**
     *
     * @param left
     * @param right
     * @param middle
     */
    public void Graphic(int left, int right, int middle){
        Graphics g = panelVenn.getGraphics();

        g.drawString(Integer.toString(left),165,165);
        g.drawOval(210,95,150,150);
        g.drawString(Integer.toString(middle),220,165);
        g.drawOval(90,95,150,150);
        g.drawString(Integer.toString(right),270,165);

    }

    public static void main(String[] args) {
        new MainGUI().setVisible(true);
    }
}
