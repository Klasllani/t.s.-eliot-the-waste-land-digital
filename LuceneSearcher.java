package com.example;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

public class LuceneSearcher {
    public static void main(String[] args) {
        try {
            // Initialize Lucene components
            Directory directory = new RAMDirectory(); // Use the same directory instance used in LuceneIndexer
            DirectoryReader reader = DirectoryReader.open(directory);
            IndexSearcher searcher = new IndexSearcher(reader);
            StandardAnalyzer analyzer = new StandardAnalyzer();
            
            // Parse the query
            QueryParser parser = new QueryParser("content", analyzer);
            Query query = parser.parse("search term"); // Replace with your search term
            
            // Execute the query
            TopDocs results = searcher.search(query, 10);
            for (ScoreDoc scoreDoc : results.scoreDocs) {
                Document doc = searcher.doc(scoreDoc.doc);
                System.out.println("Found document with content: " + doc.get("content"));
            }
            
            // Close reader
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
