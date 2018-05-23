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

    public SortingManager() {
        sortingFactory.AddSortingMethod("SortByNumberOfContributorFollowers", new SortByNumberOfContributorFollowers());
        sortingFactory.AddSortingMethod("SortByNumberOfForks", new SortByNumberOfForks());
        sortingFactory.AddSortingMethod("SortByNumberOfReleases", new SortByNumberOfReleases());
    }

    public SortingMethod getSortingMethod() {
        return sortingFactory.getSortingMethod(stringMethod);
    }

    public String getStringMethod() {
        return stringMethod;
    }

    public void setStringMethod(String stringMethod) {
        this.stringMethod = stringMethod;
    }
}