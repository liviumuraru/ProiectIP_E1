package softwareevolution_gui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class MyToolbar extends JSplitPane implements ActionListener {

    private final SoftwareEvolution_GUI frame;
    private boolean sap;
    private JComboBox combo;
    private CheckComboStore[] stores;
    private JTextArea ta;
    private JPanel form;
    private JPanel language;
    private JLabel l1;
    private final String[] langs = {"Java", "JavaScript", "Python", "C++", "C", "HTML", "CSS", "PHP", "C#", "Ruby"};
    private final Boolean[] values
            = {
                Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE
            };
    private final String default_text = "Input description here...";

    public boolean isSap() {
        return sap;
    }

    public JComboBox getCombo() {
        return combo;
    }

    public CheckComboStore[] getStores() {
        return stores;
    }

    public SoftwareEvolution_GUI getFrame() {
        return frame;
    }

    public JTextArea getTa() {
        return ta;
    }

    public void setStores(CheckComboStore[] stores) {
        this.stores = stores;
    }

    public void setSap(boolean sap) {
        this.sap = sap;
    }

    public MyToolbar(SoftwareEvolution_GUI frame) {
        this.sap = false;
        this.frame = frame;

        init();
    }

    private void init() {

        initialStyling();

        configForm();

        setupLeftSide();

        /**
         * *****************************************************
         */
        configLangs();
        
        initializeStores();
        
        configCombo();

        setupComboPadding();
        

        this.setLeftComponent(form);
        this.setRightComponent(language);
    }

    private void initialStyling() {
        this.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        this.setResizeWeight(.5d);
        this.setBackground(Color.LIGHT_GRAY);
    }

    private void configForm() {
        form = new JPanel();

        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBorder(BorderFactory.createTitledBorder("Search form"));
        form.setBorder(new EmptyBorder(20, 20, 20, 20));
        form.add(Box.createRigidArea(new Dimension(0, 10)));
        form.setBackground(Color.LIGHT_GRAY);
    }

    private void setupLeftSide() {
        l1 = new JLabel("Description of the project");
        l1.setForeground(Color.black);
        l1.setFont(l1.getFont().deriveFont(20f));  // Label font

        ta = new JTextArea(default_text, 10, 20);//"Input description here..."
        ta.setLineWrap(true);
        ta.setFont(new Font("Serif", Font.PLAIN, 22));

        ta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (ta.getText().compareTo(default_text) == 0) {
                    ta.setText("");   // Mouse event
                }
            }
        });

        form.add(l1);
        form.add(Box.createRigidArea(new Dimension(0, 10)));

        form.add(new JScrollPane(ta));
        form.add(Box.createRigidArea(new Dimension(0, 12)));
    }

    private void configLangs() {
        language = new JPanel();

        language.setLayout((new BoxLayout(language, BoxLayout.Y_AXIS)));
        language.add(Box.createRigidArea(new Dimension(50, 50)));
        language.setBackground(Color.LIGHT_GRAY);
    }

    private void initializeStores() {
        stores = new CheckComboStore[langs.length];
        for (int j = 0; j < langs.length; j++) {
            stores[j] = new CheckComboStore(langs[j], values[j]);
        }
    }

    private void configCombo() {
        combo = new JComboBox(stores);

        combo.setSelectedIndex(-1);
        combo.setRenderer(new CheckComboRenderer("LANGUAGES", this));
        combo.addActionListener(this);
        combo.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void setupComboPadding() {
        JPanel comboOuter = new JPanel();
        comboOuter.setBorder(new EmptyBorder(20, 20, 20, 20));
        comboOuter.add(combo);
        comboOuter.setBackground(Color.LIGHT_GRAY);

        language.add(comboOuter);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
        CheckComboStore store = (CheckComboStore) cb.getSelectedItem();
        CheckComboRenderer ccr = (CheckComboRenderer) cb.getRenderer();
        ccr.getCheckBox().setSelected((store.state = !store.state));

        cb.setSelectedIndex(-1);
        sap = true;
    }
}
