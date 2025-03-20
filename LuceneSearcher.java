import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import java.nio.file.Paths;

public class LuceneSearcher {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java LuceneSearcher <search term>");
            return;
        }
        
        String searchTerm = args[0];
        
        try {
            // Open the directory where the index was stored
            Directory directory = FSDirectory.open(Paths.get("lucene-index"));
            DirectoryReader reader = DirectoryReader.open(directory);
            IndexSearcher searcher = new IndexSearcher(reader);
            StandardAnalyzer analyzer = new StandardAnalyzer();
            
            // Parse the query
            QueryParser parser = new QueryParser("content", analyzer);
            Query query = parser.parse(searchTerm);
            
            // Execute the query
            TopDocs results = searcher.search(query, 10);
            System.out.println("Found " + results.scoreDocs.length + " matching documents for '" + searchTerm + "'");
            
            for (ScoreDoc scoreDoc : results.scoreDocs) {
                Document doc = searcher.doc(scoreDoc.doc);
                // Print the first 200 characters of the content to avoid overwhelming output
                String content = doc.get("content");
                String preview = content.length() > 200 ? content.substring(0, 200) + "..." : content;
                System.out.println("Document preview: " + preview);
            }
            
            reader.close();
            directory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
