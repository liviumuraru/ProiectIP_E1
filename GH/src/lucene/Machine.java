package lucene;

import java.io.File;
import java.io.IOException;
import java.util.*;

import filters.FilterFormat;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.TermFreqVector;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class Machine {

    String indexDir;
    String dataDir;
    Indexer indexer;
    Searcher searcher;

    public Machine( String indexDir, String dataDir )
    {
        this.indexDir = indexDir;
        this.dataDir = dataDir;
    }

    public void startMachine( String pathName ) {
        try
        {
            createIndex();

            File path = new File( pathName );

            Directory index= FSDirectory.open(path);
            IndexReader reader=IndexReader.open(index);

            List<Integer> docNumbers=new ArrayList<>();
            for(int i=0;i<reader.numDocs();i++)
                docNumbers.add(i);

            String [] fieldNames = new String[3];
            fieldNames[0]=LuceneConstants.CONTENTS;
            fieldNames[1]=LuceneConstants.FILE_PATH;
            fieldNames[2]=LuceneConstants.FILE_NAME;

            Set<String> stopWords = new TreeSet<>();
            stopWords.add("sohan");;

            Map<String,Integer> termFrequencyMap = getTermFrequencyMap(reader,docNumbers,fieldNames,stopWords);
            Set keys=termFrequencyMap.keySet();

            Object[] keyVector=keys.toArray(new String[keys.size()]);
            for(int i=0;i<keyVector.length;i++)
                System.out.println("word : "+ keyVector[i] + " frequency : "+termFrequencyMap.get(keyVector[i]));

            List<String> terms = new ArrayList<String>();// abstracting frequent terms collection for testing
            terms.add("Mohan");
            terms.add("Ramesh");
            terms.add("HelloWorld");

            for( String obj: terms ){ //searching by collection
                search(obj);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        } catch (ParseException e) { //catch parse exception for search call
            e.printStackTrace();
        }
    }

    private void createIndex() throws IOException {
        indexer = new Indexer(indexDir);
        int numIndexed;
        long startTime = System.currentTimeMillis();
        numIndexed = indexer.createIndex(dataDir, new FilterFormat( "txt" ) );
        long endTime = System.currentTimeMillis();
        indexer.close();
        System.out.println(numIndexed+" File indexed, time taken: "
                +(endTime-startTime)+" ms");
    }

    private void search(String searchQuery) throws IOException, ParseException {
        searcher = new Searcher(indexDir);
        long startTime = System.currentTimeMillis();
        TopDocs hits = searcher.search(searchQuery);
        long endTime = System.currentTimeMillis();

        System.out.println(hits.totalHits + " documents found. Time :" + (endTime - startTime));
        for(ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = searcher.getDocument(scoreDoc);
            System.out.println("File: " + doc.get(LuceneConstants.FILE_PATH));
            System.out.println("Score =  " + scoreDoc.score); //score field
            System.out.println("Score =  " + hits.getMaxScore()); //maximum score
        }
        searcher.close();
    }

    private Map<String,Integer> getTermFrequencyMap(IndexReader indexReader, List<Integer> docNumbers, String[] fieldNames, Set<String> stopWords)
            throws IOException {
        Map<String,Integer> totalTfv = new HashMap<String,Integer>(1024);

        for (Integer docNum : docNumbers) {
            for (String fieldName : fieldNames) {
                TermFreqVector tfv = indexReader.getTermFreqVector(docNum, fieldName);
                if (tfv == null) {
                    // ignore empty fields
                    continue;
                }

                String terms[] = tfv.getTerms();
                int termCount = terms.length;
                int freqs[] = tfv.getTermFrequencies();

                for (int t=0; t < termCount; t++) {
                    String term = terms[t];
                    int freq = freqs[t];

                    // filter out single-letter words and stop words
                    if (term.length() < 2 ||
                            stopWords.contains(term)) {
                        continue; // stop
                    }

                    Integer totalFreq = totalTfv.get(term);
                    totalFreq = (totalFreq == null) ? freq : freq + totalFreq;
                    totalTfv.put(term, totalFreq);
                }
            }
        }

        return totalTfv;
    }
	
	    public void cosineSimilarity(IndexReader indexReader, List<Integer> docNumbers, String[] fieldNames, Set<String> stopWords) throws IOException, ParseException {

        Map<String, Integer> termFrequencyMap = getTermFrequencyMap(indexReader, docNumbers, fieldNames, stopWords);
        Set keys = termFrequencyMap.keySet();

        for (Integer docNum : docNumbers) {

            for(int i=0;i<docNumbers.size();i++){
                for(int j=0;j<docNumbers.size();j++){
                    if(i==j) continue;

                    for (String fieldName : fieldNames) {
                        TermFreqVector tfv1 = indexReader.getTermFreqVector(i, fieldName);
                        TermFreqVector tfv2 = indexReader.getTermFreqVector(j, fieldName);
                        if (tfv1 == null || tfv2 == null) {
                            continue;
                        }

                        String terms1[] = tfv1.getTerms();
                        String terms2[] = tfv2.getTerms();

                        searcher = new Searcher(indexDir);
                        int cosine=0;
                        float a=0;
                        float b=0;
                        float ap=0;
                        float bp=0;
                        float nmr=0;
                        float nmi=0;

                        String path1="";
                        String path2="";

                        for(String term : terms1){

                            TopDocs hits = searcher.search(term);

                            for (ScoreDoc scoreDoc : hits.scoreDocs) {
                                Document doc = searcher.getDocument(scoreDoc);
                                if(doc == indexReader.document(i)){
                                    a=scoreDoc.score;
                                    ap += a*a;
                                    path1=doc.get(LuceneConstants.FILE_PATH);
                                }
                                if(doc == indexReader.document(j)){
                                    b=scoreDoc.score;
                                    bp += b*b;

                                    path2=doc.get(LuceneConstants.FILE_PATH);
                                }

                                }
                            nmr+=a*b;

                        }
                        nmi=ap*bp;


                        System.out.println(nmr+ " " +nmi + " for files : \n\t " + path1+"\n\t"+path2+'\n');

                        

                }
            }


            }
        }
    }
}
