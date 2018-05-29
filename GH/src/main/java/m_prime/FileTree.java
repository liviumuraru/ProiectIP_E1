package m_prime;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Vector;

/**
 * Display a file system in a JTree view
 *
 */
public class FileTree extends JPanel {
    /**
     * Construct a FileTree
     *
     */
    FileTree(File dir, Vector<String> directors) {

        setLayout(new BorderLayout());

        // Make a tree list with all the nodes, and make it a JTree

        DefaultMutableTreeNode d = new DefaultMutableTreeNode();


        for (String dr :
                directors) {
            System.out.println(dr);
            d.add(addNodes(null, new File(dir.getAbsolutePath() + File.separator + File.separator + dr)));
        }
        JTree t = new JTree(d);

        // Add a listener
        t.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
            System.out.println("You selected " + node);

            if (node.isLeaf()) {
                String s = (e.getPath().getParentPath().toString());
                String[] nonUsedVariable = s.split(",");
                String anotherOne = nonUsedVariable[nonUsedVariable.length - 1] + (t.getLastSelectedPathComponent().toString());
                anotherOne = anotherOne.replace(']', '\\');

                System.out.println(t.getLastSelectedPathComponent().toString());
                System.out.println(anotherOne);

                if (anotherOne.charAt(0) == '[') {
                    anotherOne = anotherOne.substring(1, anotherOne.length());
                }

                File file = new File(anotherOne.trim());

                try {
                    Desktop desktop = Desktop.getDesktop();
                    desktop.open(file);
                } catch (IOException e1) {
                    System.out.println("");
                }
            }
        });


        // Lastly, put the JTree into a JScrollPane.
        JScrollPane scrollpane = new JScrollPane();
        scrollpane.getViewport().add(t);
        add(BorderLayout.CENTER, scrollpane);
    }

    /**
     * Add nodes from under "dir" into curTop. Highly recursive.
     */
    private DefaultMutableTreeNode addNodes(DefaultMutableTreeNode curTop, File dir) {
        String curPath = dir.getPath();
        DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(curPath);
        if (curTop != null) { // should only be null at root
            curTop.add(curDir);
        }
        Vector<String> ol = new Vector<>();
        String[] tmp = dir.list();
        for (String aTmp : Objects.requireNonNull(tmp)) ol.addElement(aTmp);
        ol.sort(String.CASE_INSENSITIVE_ORDER);
        File f;
        Vector<String> files = new Vector<>();

        // Make two passes, one for Dirs and one for Files. This is #1.
        for (int i = 0; i < ol.size(); i++) {
            String thisObject = ol.elementAt(i);
            String newPath;
            if (curPath.equals("."))
                newPath = thisObject;
            else
                newPath = curPath + File.separator + File.separator + thisObject;
            if ((f = new File(newPath)).isDirectory())
                addNodes(curDir, f);
            else
                files.addElement(thisObject);
        }

        // Pass two: for files.
        for (int fnum = 0; fnum < files.size(); fnum++) {
            curDir.add(new DefaultMutableTreeNode(files.elementAt(fnum)));
        }
        return curDir;
    }

    public Dimension getMinimumSize() {
        return new Dimension(200, 400);
    }

    public Dimension getPreferredSize() {
        return new Dimension(200, 400);
    }


}