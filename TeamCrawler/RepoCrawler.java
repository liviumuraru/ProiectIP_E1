import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHRepositorySearchBuilder;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RepoCrawler {

    public static List<GHRepository> getReposList(List<String> set, String language) {

        List<GHRepository> repositories = new ArrayList<>();

        try {
            GitHub gitHub = GitHub.connectUsingPassword("ProiectIP2018", "ProiectIP2018-19");
            GHRepositorySearchBuilder ghRepositorySearchBuilder = gitHub.searchRepositories();
            ghRepositorySearchBuilder.language(language);

            for (String element : set) {
                ghRepositorySearchBuilder.q(element);
            }
            for (GHRepository repo : ghRepositorySearchBuilder.list()) {
                repositories.add(repo);
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return repositories;
    }
}
