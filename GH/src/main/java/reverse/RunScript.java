package reverse;

import java.io.IOException;

/**
 * The class RunScript runs python scripts from command prompt with given arguments;
 * Said action is executed by the run method
 */

public class RunScript {
    /**
     * Parametres path and arguments are given and used in command prompt
     * @param path
     * @param arguments
     */
    public static void run(String path, String...arguments) {
        try {
            Process p = Runtime.getRuntime().exec("python " + path + " "
                    + (arguments == null ? " " : String.join(" ", arguments)) );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
