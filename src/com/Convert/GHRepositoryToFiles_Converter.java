package com.Convert;

import org.kohsuke.github.GHAsset;
import org.kohsuke.github.GHBranch;
import org.kohsuke.github.GHRelease;
import org.kohsuke.github.GHRepository;

import java.io.IOException;
import java.util.List;

public class GHRepositoryToFiles_Converter implements Converter
{
    public GHRepositoryToFiles_Converter()
    {

    }


    @Override
    public boolean tryConvert(Object... params)
    {
        GHRepository repo = (GHRepository)(params[0]);
        //String branchName = (String)(params[1]);
        GHBranch branch = null;
        GHRelease release = null;

        try
        {
            //branch = repo.getBranches().get(branchName);
            List<GHRelease> releases = repo.listReleases().asList();
            System.out.println(releases.size());
            release = releases.get(releases.size() - 1);

            List<GHAsset> releaseAssets = release.getAssets();
            for(GHAsset asset : releaseAssets)
            {
                System.out.println(asset.getBrowserDownloadUrl());
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public Object Convert(Object... params) {
        return null;
    }
}
