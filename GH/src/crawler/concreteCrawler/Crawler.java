package crawler.concreteCrawler;

import crawler.sort.CriteriaSorter;
import crawler.subset.AllSubsets;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHRepositorySearchBuilder;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.*;

public class Crawler {

    private int maximumReposNumber;

    public Crawler( int maximumReposNumber )
    {
        this.maximumReposNumber = maximumReposNumber;
    }

    public List<GHRepository> getAllReposList(List<String> keywords, String language) {
        List<GHRepository> repositories = new ArrayList<>();
        List<List<String>> allSubsets = AllSubsets.generateSubsets(keywords);

        for (List<String> subset : allSubsets) {
            if(repositories.size()==RepoCrawler.MAX_SIZE)
                break;
            repositories.addAll(RepoCrawler.getReposList(subset, language, repositories.size()));
        }
        return repositories;
    }


    public List<GHRepository> getRepos(List<String> keywords, String language, int filter) throws Exception {

        if(keywords.isEmpty())
            throw new Exception("No keywords were found.");
        if(language==null)
            throw new Exception("Language field cannot be empty.");

        List<GHRepository> repositories = new ArrayList<>(maximumReposNumber);
        List<GHRepository> repositoryList = getAllReposList(keywords, language);
        CriteriaSorter.sortByCriteria(repositoryList, filter);
        for (int i = 0; i < maximumReposNumber && i < repositoryList.size(); i++) {
            repositories.add(repositoryList.get(i));
        }
        return repositories;
    }

}