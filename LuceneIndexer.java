package com.example;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

public class LuceneIndexer {
    public static void main(String[] args) {
        try {
            // Initialize Lucene components
            Directory directory = new RAMDirectory();
            StandardAnalyzer analyzer = new StandardAnalyzer();
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            IndexWriter writer = new IndexWriter(directory, config);
            
            // Parse the XML file
            String content = XMLParser.parseXML("src/resources/The-Waste-Land.xml");
            
            // Create a Lucene document and add fields
            Document doc = new Document();
            doc.add(new TextField("content", content, Field.Store.YES));
            writer.addDocument(doc);
            
            // Commit changes and close writer
            writer.commit();
            writer.close();
            
            System.out.println("Indexing completed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
