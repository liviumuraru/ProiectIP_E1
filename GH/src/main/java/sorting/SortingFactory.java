package sorting;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
 * This class keeps a map where for each type of sorting we have associated a string (describing the sorting and acting as a key)
 * and an object (from a class extending the abstract class SortingMethod) which handles the sorting.
 */
public class SortingFactory {

    private Map<String, SortingMethod> sortingMethods = new HashMap<>();

    /**
    * Verifies if the type of sorting specified by the string is possible (exists in the map or not). If not,
     * it throws an "Incorrect sorting method" IOException, else it returns the value associated to that key in the map
     * @param sortingMethod
     */
    public SortingMethod getSortingMethod(String sortingMethod) {
        try
        {
            if(!sortingMethods.containsKey(sortingMethod))
                throw (new IOException("no key"));
        }

        catch (IOException exception){
            System.out.println("Incorrect sorting method");
            return null;
        }

        return sortingMethods.get(sortingMethod);
    }

    public void AddSortingMethod(String name, SortingMethod implementation)
    {
        sortingMethods.put(name, implementation);
    }
}
