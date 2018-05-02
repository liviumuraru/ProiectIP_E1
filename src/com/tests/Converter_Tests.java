package com.tests;

import com.Convert.Convert;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;

public class Converter_Tests
{
    public static void main(String... args)
    {
        try {
            GitHub gitHub = GitHub.connectAnonymously();
            GHRepository proiectIP_e1 = gitHub.getRepository("kohsuke/github-api");
            Convert.Converter_GHRepository_to_Files.tryConvert(proiectIP_e1);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
