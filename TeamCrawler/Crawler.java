package com.company;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHRepositorySearchBuilder;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.*;

public class Crawler {

    public final static int MAX_REPOS = 10;

    public List<GHRepository> getRepos(List<String> keywords, String language) {

        List<GHRepository> repositories = new ArrayList<>();

        try
        {
            GitHub gitHub = GitHub.connectUsingPassword("ProiectIP2018", "ProiectIP2018-19");
            GHRepositorySearchBuilder ghRepositorySearchBuilder = gitHub.searchRepositories();

            for(int i=0; i < keywords.size(); i++)
                ghRepositorySearchBuilder.q( keywords.get(i) );

            ghRepositorySearchBuilder.language( language );

            //ghRepositorySearchBuilder.stars("0");

            int repo_counter = 0;
            for( GHRepository repo : ghRepositorySearchBuilder.list())
            {
                repositories.add(repo);
                repo_counter++;
                if(repo_counter == MAX_REPOS)
                    break;
            }
        }
        catch ( IOException e )
        {
            System.err.println( e.getMessage() );
        }

        return repositories;
    }
}
