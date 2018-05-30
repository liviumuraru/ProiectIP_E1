package manager;

import lucene.Repo;
import org.kohsuke.github.GHRepository;
import sorting.*;


import java.util.List;

import static manager.Trim.getRemaining;

public class SortingManager {
    private String stringMethod = "SortByNumberOfForks"; //default
    private SortingMethod sortingMethod;
    private SortingFactory sortingFactory = new SortingFactory();
    private List<Repo> repositoryList = getRemaining();

     /**
     * Adds the type of sorting available to the sortingFactory object
     */
    public SortingManager() {
        sortingFactory.AddSortingMethod("SortByNumberOfContributorFollowers", new SortByNumberOfContributorFollowers());
        sortingFactory.AddSortingMethod("SortByNumberOfForks", new SortByNumberOfForks());
        sortingFactory.AddSortingMethod("SortByNumberOfReleases", new SortByNumberOfReleases());
    }

     /**
     * Returns the sorting method corresponding to the stringMethod attribute
     */
    public SortingMethod getSortingMethod() {
        return sortingFactory.getSortingMethod(stringMethod);
    }

     /**
     * Returns the stringMethod attribute
     */
    public String getStringMethod() {
        return stringMethod;
    }

     /**
     * Sets the value of the stringMethod attribute
     * @param stringMethod
     */
    public void setStringMethod(String stringMethod) {
        this.stringMethod = stringMethod;
    }
}
