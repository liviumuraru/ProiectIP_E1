package com.company;

import org.junit.Test;
import org.kohsuke.github.GHRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CrawlerTest {

    @Test
    public void getAllReposList() throws IOException {
        List<String> elements = new ArrayList<>();
        elements.add("github");
        elements.add("crawler");
        elements.add("cool");

        String language = "python";

        String expName = "wg-gesucht-crawler-ver2";

        List<GHRepository> result = RepoCrawler.getReposList(elements, language, 99);

        assertEquals(expName, result.get(0).getName());
    }

    @Test
    public void getRepos() throws IOException {
        List<String> elements = new ArrayList<>();
        elements.add("github");
        elements.add("crawler");
        elements.add("cool");

        String language = "python";

        String expName = "wg-gesucht-crawler-ver2";

        List<GHRepository> result = RepoCrawler.getReposList(elements, language, 99);

        assertEquals(expName, result.get(0).getName());
    }
}