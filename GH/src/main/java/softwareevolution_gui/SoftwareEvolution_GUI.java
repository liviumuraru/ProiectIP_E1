package softwareevolution_gui;

import crawler.CrawlerMachine;
import crawler.concreteCrawler.Crawler;
import lucene.Machine;
import lucene.Repo;
import org.kohsuke.github.GHRepository;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import m_prime.*;

import static crawler.CrawlerMachine.createMergedFiles;

public class SoftwareEvolution_GUI implements ActionListener {

    private MyToolbar toolb = null;
    private ArrayList<String> selectedLangs = new ArrayList<>();
    private ArrayList<String> tags = new ArrayList<>();
    private JFrame frame = null;
    private JLabel label_img = null;
    private JButton searchButton = null;

    public MyToolbar getToolb() {
        return toolb;
    }

    private static void createPanelList()
    {
        PanelList frame = new PanelList();
        //frame.setBackground(Color.LIGHT_GRAY);
        frame.setSize(820,680);

        frame.setLocationRelativeTo(null);
        //frame.pack();
        frame.setVisible ( true );
        frame.setDefaultCloseOperation ( WindowConstants.EXIT_ON_CLOSE );
    }

    private void assignTags() {

        tags.clear();
        String desc = toolb.getTa().getText();
        if (toolb.getTa().getText().compareTo("Input description here...") != 0) {

            String delim = " \n\r\t";
            StringTokenizer st = new StringTokenizer(desc, delim);

            while (st.hasMoreTokens()) {
                tags.add(st.nextToken());
            }
        }

    }


    public ArrayList<String> getSelectedLangs() {
        return selectedLangs;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    private void assignLanguages() {

        selectedLangs.clear();

        for (CheckComboStore store : toolb.getStores()) {
            if (store.state == true) {
                selectedLangs.add(store.id);
            }
        }

    }

    public boolean displayInfo(ArrayList<String> tags, ArrayList<String> selectedLangs) {

        if (!tags.isEmpty() && !selectedLangs.isEmpty()) {

            System.out.println("TAGS:");
            for (int i = 0; i < tags.size(); i++) {
                System.out.println(tags.get(i));
            }
            System.out.println("\nLANGUAGES:");
            for (int i = 0; i < selectedLangs.size(); i++) {
                System.out.println(selectedLangs.get(i));
            }
            System.out.println();

            List<GHRepository > repositories = null;
            try
            {
                Crawler crawler = new Crawler( 7 );
                repositories = crawler.getRepos( getTags(), getSelectedLangs() );

                // print repos
                CrawlerMachine.printRepos( repositories );

                // create a merged file for each repo
                List<File> mergedFiles = createMergedFiles( repositories );

                // create lucene machine
                try
                {
                    Machine.getCosineSimilarity( 0.6 );
                }
                catch ( IOException e )
                {
                    e.printStackTrace();
                }

                // add RE part before
                createPanelList();
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
        } else if (selectedLangs.isEmpty() && tags.isEmpty()) {
            JOptionPane.showMessageDialog(frame,
                    "Please select at least 1 (one) language and input a description.");
            return false;
        } else if (selectedLangs.isEmpty() && !tags.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please select at least 1 (one) language.");
            return false;
        } else if (!selectedLangs.isEmpty() && tags.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please input a description.");
            return false;
        }

        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        assignTags();
        assignLanguages();

        boolean displayInfo = displayInfo(this.tags, this.selectedLangs);
    }

    public SoftwareEvolution_GUI() {
        EventQueue.invokeLater(() -> {
            frame = new JFrame("Software Evolution");

            toolb = new MyToolbar(this);
            toolb.setBorder(new EmptyBorder(10, 10, 10, 10));

            searchButton = new SearchButton(this);
            searchButton.addActionListener(this);

            ImageIcon image;
            image = new ImageIcon("title.jpg");

            label_img = new JLabel();
            label_img.setIcon(image);

            init();
        });
    }

    private void init() {
        frame.setResizable(false);
        frame.add(label_img, BorderLayout.NORTH);
        frame.add(toolb, BorderLayout.WEST);
        frame.add(Box.createRigidArea(new Dimension(0, 20)));
        frame.add(searchButton, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String args[]) {
        SoftwareEvolution_GUI softwareEvolution_GUI = new SoftwareEvolution_GUI();
    }
}