package lucene;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import manager.Trim;
import org.apache.lucene.store.LockObtainFailedException;

public class Machine
{
    private static List<String> getBiggestCluster( int numberOfDocuments, double [][] cosineSimilarities, double limit )
    {
        List<String> selectedRepos = new ArrayList<>();

        for ( int i = 0; i < numberOfDocuments; i++ )
        {
            List<String> currentRepos = new ArrayList<>();
            getSimilarRepos( currentRepos, cosineSimilarities, numberOfDocuments, i, limit );

            if ( currentRepos.size() > selectedRepos.size() )
                selectedRepos = new ArrayList<>( currentRepos );
        }

        return selectedRepos;
    }

    private static void getSimilarRepos( List<String> selectedRepos, double [][] cosineSimilarities, int numberOfDocuments, int index, double limit )
    {
        try
        {
            BufferedReader br = new BufferedReader( new FileReader( "mergedFiles/merged" + index + ".txt" ) );

            br.readLine();
            selectedRepos.add( br.readLine() );
            br.close();

            for ( int i = 0; i < numberOfDocuments; i++ )
                if ( i != index && cosineSimilarities[ index ][ i ] >= limit )
                {
                    BufferedReader bufferedReader = new BufferedReader( new FileReader( "mergedFiles/merged" + i + ".txt" ) );


                    bufferedReader.readLine();
                    selectedRepos.add( bufferedReader.readLine() );
                }
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
    }

    public static void getCosineSimilarity( double limit ) throws LockObtainFailedException, IOException
    {
        double [][] cosineSimilarities = new double[ 40 ][ 40 ];

        Indexer index = new Indexer();
        index.index();
        VectorGenerator vectorGenerator = new VectorGenerator();
        vectorGenerator.GetAllTerms();
        DocVector[] docVector = vectorGenerator.GetDocumentVectors(); // getting document vectors

        for( int i = 0; i < docVector.length; i++ )
            for ( int j = 0; j < docVector.length; j++ )
                if ( i != j )
                    cosineSimilarities[ i ][ j ] = CosineSimilarity.CosineSimilarity( docVector[ i ], docVector[ j ] );
        for ( int i = 0; i < docVector.length; i++ )
        {
            for ( int j = 0; j < docVector.length; j++ )
                System.out.print( cosineSimilarities[ i ][ j ] + " " );
            System.out.println();
        }

        List<String> selectedRepos = getBiggestCluster( docVector.length, cosineSimilarities, limit );
        for ( String repoPath : selectedRepos )
        {
            System.out.println( "Selected: " + repoPath );
            Trim.addRemainingProject( repoPath );
        }
    }
}