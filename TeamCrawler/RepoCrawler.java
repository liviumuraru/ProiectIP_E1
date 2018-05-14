package com.company;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHRepositorySearchBuilder;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RepoCrawler {

    public final static int MAX_SIZE = 100;

    public static List<GHRepository> getReposList(List<String> set, String language, int SIZE) {

        List<GHRepository> repositories = new ArrayList<>();

        try {
            GitHub gitHub = GitHub.connectUsingPassword("ProiectIP2018", "ProiectIP2018-19");
            GHRepositorySearchBuilder ghRepositorySearchBuilder = gitHub.searchRepositories();
            ghRepositorySearchBuilder.language(language);

            for (String element : set) {
                ghRepositorySearchBuilder.q(element);
            }
            for (GHRepository repo : ghRepositorySearchBuilder.list()) {
                if(SIZE==MAX_SIZE)
                    return repositories;
                repositories.add(repo);
                SIZE++;
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return repositories;
    }
}
