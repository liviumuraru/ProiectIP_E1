package lucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.FieldInfo;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.LockObtainFailedException;


/**
 * Class to create Lucene Index from files.
 * Remember this class will only index files inside a folder.
 * If there are  multiple folder inside the source folder it will not index
 * those files.
 *
 *  It will only index text files
 *
 */
public class Indexer {

    private final File sourceDirectory;
    private final File indexDirectory;
    private static String fieldName;

    public Indexer() {
        this.sourceDirectory = new File(Configuration.SOURCE_DIRECTORY_TO_INDEX);
        this.indexDirectory = new File(Configuration.INDEX_DIRECTORY);
        fieldName = Configuration.FIELD_CONTENT;
    }

    /**
     * Method to create Lucene Index
     * Keep in mind that always index text value to Lucene for calculating
     * Cosine Similarity.
     * You have to generate tokens, terms and their frequencies and store
     * them in the Lucene Index.
     * @throws CorruptIndexException
     * @throws LockObtainFailedException
     * @throws IOException
     */
    public void index() throws IOException {
        Directory dir = FSDirectory.open(indexDirectory);
        Analyzer analyzer = new StandardAnalyzer(StandardAnalyzer.STOP_WORDS_SET);  // using stop words
        IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_4_10_2, analyzer);

        if ( indexDirectory.exists()) {
            iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        } else {
            // Add new documents to an existing index:
            iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        }

        IndexWriter writer = new IndexWriter(dir, iwc);
        for (File f : sourceDirectory.listFiles()) {
            Document doc = new Document();
            FieldType fieldType = new FieldType();
            fieldType.setIndexed(true);
            fieldType.setIndexOptions( FieldInfo.IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
            fieldType.setStored(true);
            fieldType.setStoreTermVectors(true);
            fieldType.setTokenized(true);
            Field contentField = new Field(fieldName, getAllText(f), fieldType);
            doc.add(contentField);
            writer.addDocument(doc);
        }
        writer.close();
    }

    /**
     * Method to get all the texts of the text file.
     * Lucene cannot create the term vetors and tokens for reader class.
     * You have to index its text values to the index.
     * It would be more better if this was in another class.
     * I am lazy you know all.
     * @param f
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public String getAllText(File f) throws IOException {
        String textFileContent = "";
        for (String line : Files.readAllLines(Paths.get(f.getAbsolutePath()), StandardCharsets.ISO_8859_1)) {
        	textFileContent += line;
        }        	

        return textFileContent;
    }
}
