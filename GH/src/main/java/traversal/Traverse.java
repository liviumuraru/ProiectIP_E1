package traversal;

import java.io.File;
import java.io.IOException;

import concatenation.*;

/**
 * Class Traverse is used to access every file and directory existing in a given path
 */

public class Traverse {

    static Concat c = new Concat();
    public static void main (String args[]) throws IOException {

        displayIt(new File("D:\\Anul 2\\ProiectIP\\ProiectIPM3"));
    }

    /**
     * Parameter node represents the given path. Files and directories are recursively accessed and the run method
     * belonging to class Concat is called when a file is reached
     * @param node
     * @throws IOException
     */
    public static void displayIt(File node) throws IOException {
        if(node.isDirectory() && !node.getName().startsWith(".")){
            String[] subNote = node.list();
            for(String filename : subNote){
                displayIt(new File(node, filename));
                c.run(node.getAbsoluteFile());
            }
        }
        else return;
    }
}
