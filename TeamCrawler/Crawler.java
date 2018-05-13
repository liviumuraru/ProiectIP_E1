import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHRepositorySearchBuilder;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.*;

public class Crawler {

    public final static int MAX_REPOS = 10;

    public List<GHRepository> getReposList(List<String> keywords, String language) {

        List<GHRepository> repositories = new ArrayList<>();

        try {
            GitHub gitHub = GitHub.connectUsingPassword("ProiectIP2018", "ProiectIP2018-19");
            GHRepositorySearchBuilder ghRepositorySearchBuilder = gitHub.searchRepositories();
            ghRepositorySearchBuilder.language(language);

            for(int listLength = keywords.size() - 1; listLength >= 1; listLength--) {
                List<String> subKeywords = keywords.subList(0, listLength);
                for (int i = 0; i < subKeywords.size(); i++)
                    ghRepositorySearchBuilder.q(keywords.get(i));
            }

            for (GHRepository repo : ghRepositorySearchBuilder.list()) {
                repositories.add(repo);
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return repositories;
    }

    public List<GHRepository> getRepos(List<GHRepository> repositoryList, String filter) {
        List<GHRepository> repositories = new ArrayList<>(10);
        sortByCriteria(repositoryList, filter);
        for (int i = 0; i < MAX_REPOS && i < repositoryList.size(); i++) {
            repositories.add(repositoryList.get(i));
        }
        return repositories;
    }

    private void sortByKeywords(List<String> keywords){

    }

    private void sortByCriteria(List<GHRepository> repositoryList, String filter) {
        switch (filter) {
            case "stars":
                repositoryList.sort(new Comparator<GHRepository>() {
                    @Override
                    public int compare(GHRepository o1, GHRepository o2) {
                        return o1.getStargazersCount() - o2.getStargazersCount();
                    }
                }.reversed());
                break;
            case "followers":
                repositoryList.sort(new Comparator<GHRepository>() {
                    @Override
                    public int compare(GHRepository o1, GHRepository o2) {
                        return o1.getWatchers() - o2.getWatchers();
                    }
                }.reversed());
                break;
            case "create":
                repositoryList.sort(new Comparator<GHRepository>() {
                    @Override
                    public int compare(GHRepository o1, GHRepository o2) {
                        try {
                            if (o1.getCreatedAt().getTime() - o2.getCreatedAt().getTime() < 0)
                                return -1;
                            else if (o1.getCreatedAt().getTime() - o2.getCreatedAt().getTime() == 0)
                                return 0;
                            else
                                return 1;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return -2;
                    }
                }.reversed());
                break;
            case "update":
                repositoryList.sort(new Comparator<GHRepository>() {
                    @Override
                    public int compare(GHRepository o1, GHRepository o2) {
                        try {
                            if (o1.getUpdatedAt().getTime() - o2.getUpdatedAt().getTime() < 0)
                                return -1;
                            else if (o1.getUpdatedAt().getTime() - o2.getUpdatedAt().getTime() == 0)
                                return 0;
                            else
                                return 1;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return -2;
                    }
                }.reversed());
                break;

        }
    }
}