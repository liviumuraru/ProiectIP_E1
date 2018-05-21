package lucene;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.store.LockObtainFailedException;

public class Machine
{
    private static List<Repo> getBiggestCluster( int numberOfDocuments, double [][] cosineSimilarities, double limit )
    {
        List<Repo> selectedRepos = new ArrayList<>();

        for ( int i = 0; i < numberOfDocuments; i++ )
        {
            boolean [] selected = new boolean [ numberOfDocuments + 1 ];
            for ( int j = 0; j < numberOfDocuments; j++ )
                selected[ j ] = false;

            List<Repo> currentRepos = new ArrayList<>();
            getSimilarRepos( selected, currentRepos, cosineSimilarities, numberOfDocuments, i, limit );

            if ( currentRepos.size() > selectedRepos.size() )
                selectedRepos = new ArrayList<>( currentRepos );
        }

        return selectedRepos;
    }

    private static void getSimilarRepos( boolean [] selected, List<Repo> selectedRepos, double [][] cosineSimilarities, int numberOfDocuments, int index, double limit )
    {
        try
        {
            //System.out.println( index + " : mergedFiles/merged" + index + ".txt" );
            BufferedReader br = new BufferedReader( new FileReader( "mergedFiles/merged" + index + ".txt" ) );

            String repoURL = br.readLine();
            String destinationDIR = br.readLine();
            //System.out.println( repoURL + " " + destinationDIR );
            selectedRepos.add( new Repo( repoURL, destinationDIR ) );
            /*for ( Repo repo : selectedRepos )
                System.out.println( repo );*/
            br.close();
            selected[ index ] = true;

            for ( int i = 0; i < numberOfDocuments; i++ )
                if ( i != index && cosineSimilarities[ index ][ i ] >= limit && !selected[ i ] )
                    getSimilarRepos( selected, selectedRepos, cosineSimilarities, numberOfDocuments, i, limit );
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
    }

    public static List<Repo> getCosineSimilarity( double limit ) throws LockObtainFailedException, IOException
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

        return getBiggestCluster( docVector.length, cosineSimilarities, limit );
    }
}