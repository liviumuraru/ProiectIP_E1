
import org.kohsuke.github.GHRepository;

import java.util.*;

public class Crawler {

    private final static int MAX_REPOS = 10;

    public List<GHRepository> getAllReposList(List<String> keywords, String language) {
        List<GHRepository> repositories = new ArrayList<>();
        List<List<String>> allSubsets = AllSubsets.generateSubsets(keywords);

        for (List<String> subset : allSubsets) {
            repositories.addAll(RepoCrawler.getReposList(subset, language));
        }
        return repositories;
    }


    public List<GHRepository> getRepos(List<String> keywords, String language, int filter) {
        List<GHRepository> repositories = new ArrayList<>(MAX_REPOS);
        List<GHRepository> repositoryList = getAllReposList(keywords, language);
        CriteriaSorter.sortByCriteria(repositoryList, filter);
        for (int i = 0; i < MAX_REPOS && i < repositoryList.size(); i++) {
            repositories.add(repositoryList.get(i));
        }
        return repositories;
    }

}