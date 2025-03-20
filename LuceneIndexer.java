import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import java.nio.file.Paths;

public class LuceneIndexer {
    public static void main(String[] args) {
        try {
            // Create a directory to store the index on disk
            Directory directory = FSDirectory.open(Paths.get("lucene-index"));
            StandardAnalyzer analyzer = new StandardAnalyzer();
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            IndexWriter writer = new IndexWriter(directory, config);
            
            // Parse the XML file
            String xmlFile = args.length > 0 ? args[0] : "The-Waste-Land.xml";
            String content = XMLParser.parseXML(xmlFile);
            
            // Create a Lucene document and add fields
            Document doc = new Document();
            doc.add(new TextField("content", content, Field.Store.YES));
            writer.addDocument(doc);
            
            // Commit changes and close
            writer.commit();
            writer.close();
            directory.close();
            
            System.out.println("Indexing completed. Index stored in 'lucene-index' directory.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
