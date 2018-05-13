import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHRepositorySearchBuilder;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.*;

public class Main
{
    public static void main( String args[] )
    {
        List<String> keywords = new ArrayList<>();
        keywords.add("crawler");
        keywords.add("github");
        keywords.add("trala");


        String language = "java";
        Crawler crawler = new Crawler();
        List<GHRepository> repositories = crawler.getReposList(keywords, language);
        List<GHRepository> repositoryList = crawler.getRepos(repositories, "updated");
        for(GHRepository repository : repositoryList)
        {
            System.out.println(repository.getName() + " + " + repository.getSvnUrl());

        }
    }
}