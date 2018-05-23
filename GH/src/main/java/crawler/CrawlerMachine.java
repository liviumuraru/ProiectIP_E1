package crawler;

import filter.concreteFilters.CHeaderFilesOnly;
import filter.concreteFilters.JavaFilesOnly;
import filter.concreteFilters.TextFilesOnly;
import filter.filterTypes.ORFilter;
import manager.Trim;
import merge.MergePipeline;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.kohsuke.github.GHRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CrawlerMachine
{
    public static void printRepos( List<GHRepository> repositories )
    {
        for( int i = 0; i<repositories.size(); i++ )
            System.out.println(repositories.get( i ).getName() );
    }

    public static List<File> createMergedFiles( List<GHRepository> repositories )
    {
        long counter = 0;
        List<File> mergedFiles = new ArrayList<>();
        for ( GHRepository repository : repositories )
        {
            try
            {
                MergePipeline mergePipeline = new MergePipeline( repository.getOwnerName(), repository.getName(), counter++ );
                mergePipeline.filters().add( new ORFilter<>( new ORFilter<>( new CHeaderFilesOnly(), new TextFilesOnly() ), new JavaFilesOnly() ) );

                mergedFiles.add( mergePipeline.GetMergedFile() );
                Trim.add(mergePipeline.getDestinationDir().toString(), repository);

            }
            catch ( GitAPIException | IOException e )
            {
                e.printStackTrace();
            }
        }

        return mergedFiles;
    }

}
