package softwareevolution_gui;

import javax.swing.*;
import java.awt.*;


class CheckComboRenderer implements ListCellRenderer {

    private JCheckBox checkBox;
    private final MyToolbar mt;
    private JLabel label;
    private String title;
    private CheckComboStore store;

    public CheckComboRenderer(String title, MyToolbar mt) {
        this.checkBox = new JCheckBox();
        this.label = new JLabel();
        this.mt = mt;
        this.title = title;
    }

    public JCheckBox getCheckBox() {
        return checkBox;
    }

    @Override
    public Component getListCellRendererComponent(JList list,
                                                  Object value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {

        store = (CheckComboStore) value;

        if (index == -1 && value == null) {

            if (mt.isSap()) {
                mt.getCombo().showPopup(); //show it again
                mt.setSap(false); //and remove the flag
            }

            configLabel(isSelected);
            return label;
        }
        else {
            configCheckbox(isSelected);
            return checkBox;
        }
    }

    private void configLabel(boolean isSelected) {
        label.setText(title);

        label.setBackground(isSelected ? Color.red : Color.white);
        label.setForeground(isSelected ? Color.white : Color.black);
    }

    private void configCheckbox(boolean isSelected) {
        checkBox.setText(store.id);
        checkBox.setSelected((store.state));

        checkBox.setBackground(isSelected ? Color.red : Color.white);
        checkBox.setForeground(isSelected ? Color.white : Color.black);
    }
}