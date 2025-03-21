import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import java.nio.file.Paths;

public class LuceneSearcher {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java LuceneSearcher <search-term>");
            System.exit(1);
        }
        
        String searchTerm = args[0];
        
        try {
            // Open index
            Directory indexDirectory = FSDirectory.open(Paths.get("index"));
            IndexReader reader = DirectoryReader.open(indexDirectory);
            IndexSearcher searcher = new IndexSearcher(reader);
            
            // Parse query
            StandardAnalyzer analyzer = new StandardAnalyzer();
            QueryParser parser = new QueryParser("content", analyzer);
            Query query = parser.parse(searchTerm);
            
            // Search
            TopDocs results = searcher.search(query, 100); // Get top 100 results
            
            System.out.println("Found " + results.totalHits.value + " stanzas for '" + searchTerm + "'");
            
            // Display results - stanzas only
            for (int i = 0; i < results.scoreDocs.length; i++) {
                ScoreDoc scoreDoc = results.scoreDocs[i];
                Document doc = searcher.doc(scoreDoc.doc);
                
                String section = doc.get("section");
                String sectionTitle = doc.get("sectionTitle");
                String stanza = doc.get("stanza");
                String content = doc.get("content");
                
                System.out.println("\n----- Stanza " + stanza + " -----");
                System.out.println("Section: " + section + " - " + sectionTitle);
                System.out.println("\n" + content);
            }
            
            reader.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
