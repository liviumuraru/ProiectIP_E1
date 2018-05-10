package laboaratoare;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

import javafx.util.Pair;
import org.apache.lucene.document.Document;
//import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.TermFreqVector;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class LuceneTester {

    String indexDir = "E:\\Lucene\\Index";
    String dataDir = "E:\\Lucene\\Data";
    Indexer indexer;
    Searcher searcher;

    public static void main(String[] args) {
        LuceneTester tester;
        /*try {
            tester = new LuceneTester();
            tester.createIndex();
            tester.search("Mohan");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        try{
            tester=new LuceneTester();
            tester.createIndex();
            File path = new File("E:\\Lucene\\Index");
            Directory index= FSDirectory.open(path);
            IndexReader reader=IndexReader.open(index);
            List<Integer> docNumbers=new ArrayList<>();
            for(int i=0;i<reader.numDocs();i++)
                docNumbers.add(i);
            String [] fieldnames = new String[3];
            fieldnames[0]=LuceneConstants.CONTENTS;
            fieldnames[1]=LuceneConstants.FILE_PATH;
            fieldnames[2]=LuceneConstants.FILE_NAME;
            Set<String> stopWords = new TreeSet<>();
            stopWords.add("sohan");;
            Map<String,Integer> termFrequencyMap=tester.getTermFrequencyMap(reader,docNumbers,fieldnames,stopWords);
            Set keys=termFrequencyMap.keySet();
            Object[] keyVector=keys.toArray(new String[keys.size()]);
            for(int i=0;i<keyVector.length;i++)
                System.out.println("word : "+ keyVector[i] + " frequency : "+termFrequencyMap.get(keyVector[i]));



        }
        catch (IOException e)
        {
            e.printStackTrace();
        }



    }

    private void createIndex() throws IOException {
        indexer = new Indexer(indexDir);
        int numIndexed;
        long startTime = System.currentTimeMillis();
        numIndexed = indexer.createIndex(dataDir, new TextFileFilter());
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

        System.out.println(hits.totalHits +
                " documents found. Time :" + (endTime - startTime));
        for(ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = searcher.getDocument(scoreDoc);
            System.out.println("File: "
                    + doc.get(LuceneConstants.FILE_PATH));
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
}