package manager;

import lucene.Repo;
import org.kohsuke.github.GHRepository;
import sorting.*;


import java.util.List;

import static manager.Trim.getRemaining;

public class SortingManager {
    private String stringMethod;
    private SortingMethod sortingMethod;
    private SortingFactory sortingFactory = new SortingFactory();
    private List<Repo> repositoryList = getRemaining();

    public void init(){
        sortingFactory.AddSortingMethod("SortByNumberOfContributorFollowers", new SortByNumberOfContributorFollowers());
        sortingFactory.AddSortingMethod("SortByNumberOfForks", new SortByNumberOfForks());
        sortingFactory.AddSortingMethod("SortByNumberOfReleases", new SortByNumberOfReleases());
    }

    public SortingManager() {
        // Default sorting method
        init();
        this.stringMethod = "SortByNumberOfReleases";
        this.sortingMethod = sortingFactory.getSortingMethod(stringMethod);
    }

    public SortingManager(String stringMethod) {
        this.stringMethod = stringMethod;
        this.sortingMethod = sortingFactory.getSortingMethod(stringMethod);
    }

    public String getStringMethod() {
        return stringMethod;
    }

    public void setStringMethod(String stringMethod) {
        this.stringMethod = stringMethod;
    }

    public List<GHRepository> callSortMethod(List<Repo> repositoryList) {
        return sortingMethod.sort(repositoryList);
    }
}
