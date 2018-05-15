import crawler.concreteCrawler.Crawler;
import crawler.sort.CriteriaSorter;
import filter.concreteFilters.CHeaderFilesOnly;
import filter.concreteFilters.TextFilesOnly;
import filter.filterTypes.ORFilter;
import lucene.Machine;
import merge.MergePipeline;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.kohsuke.github.GHRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainTest
{
    public static void printRepos( List<GHRepository> repositories )
    {
        for( int i = 0; i<repositories.size(); i++ )
            System.out.println(repositories.get( i ).getName() );
    }

    public static void printMergedFiles( List<File> mergedFiles )
    {
        for ( File file : mergedFiles )
        {
            BufferedReader br = null;
            try
            {
                br = new BufferedReader( new FileReader( file ) );
                String text = null;

                while ( ( text = br.readLine() ) != null )
                    System.out.println( text );
                System.out.println();
                System.out.println();
                System.out.println();
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
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
                mergePipeline.filters().add(new ORFilter<>(new CHeaderFilesOnly(), new TextFilesOnly()));

                mergedFiles.add( mergePipeline.GetMergedFile() );
            }
            catch ( GitAPIException | IOException e )
            {
                e.printStackTrace();
            }
        }

        return mergedFiles;
    }

    public static void main( String args[] )
    {
        // add keywords for crawler
        List<String> keywords = new ArrayList<>();
        keywords.add( "crawler" );
        keywords.add( "github" );

        // add language for crawler
        String language = "java";

        // create crawler and get related repos
        Crawler crawler = new Crawler( 3 );
        List<GHRepository > repositories = null;
        try
        {
            repositories = crawler.getRepos(keywords, language, CriteriaSorter.BY_STARS);
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }

        // print repos
        printRepos( repositories );

        // create a merged file for each repo
        List<File> mergedFiles = createMergedFiles( repositories );

        // print each merged file
        printMergedFiles( mergedFiles );

        // create lucene machine
        try
        {
            Machine.getCosineSimilarity();
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
    }
}
