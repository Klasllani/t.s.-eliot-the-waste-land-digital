import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.nio.file.Paths;

public class LuceneIndexer {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java LuceneIndexer <xml-file>");
            System.exit(1);
        }
        
        String xmlFile = args[0];
        
        try {
            // Parse XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            org.w3c.dom.Document xmlDoc = builder.parse(new File(xmlFile));
            
            // Setup Lucene
            Directory indexDirectory = FSDirectory.open(Paths.get("index"));
            StandardAnalyzer analyzer = new StandardAnalyzer();
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            IndexWriter writer = new IndexWriter(indexDirectory, config);
            
            // Clear previous index
            writer.deleteAll();
            
            // Process poem by section, stanza
            NodeList divElements = xmlDoc.getElementsByTagName("div");
            int sectionCount = 0;
            
            for (int i = 0; i < divElements.getLength(); i++) {
                Element divElement = (Element) divElements.item(i);
                String type = divElement.getAttribute("type");
                
                if (type.equals("section")) {
                    sectionCount++;
                    String sectionTitle = getSectionTitle(divElement);
                    
                    // Get stanzas (lg elements)
                    NodeList lgElements = divElement.getElementsByTagName("lg");
                    int stanzaCount = 0;
                    
                    for (int j = 0; j < lgElements.getLength(); j++) {
                        Element lgElement = (Element) lgElements.item(j);
                        stanzaCount++;
                        
                        // Get lines (l elements)
                        NodeList lElements = lgElement.getElementsByTagName("l");
                        StringBuilder stanzaText = new StringBuilder();
                        
                        for (int k = 0; k < lElements.getLength(); k++) {
                            Element lElement = (Element) lElements.item(k);
                            String lineText = lElement.getTextContent();
                            stanzaText.append(lineText).append("\n");
                        }
                        
                        // Create stanza document only
                        Document stanzaDoc = new Document();
                        stanzaDoc.add(new StringField("section", String.valueOf(sectionCount), Field.Store.YES));
                        stanzaDoc.add(new StringField("sectionTitle", sectionTitle, Field.Store.YES));
                        stanzaDoc.add(new StringField("stanza", String.valueOf(stanzaCount), Field.Store.YES));
                        stanzaDoc.add(new TextField("content", stanzaText.toString(), Field.Store.YES));
                        writer.addDocument(stanzaDoc);
                    }
                }
            }
            
            writer.close();
            System.out.println("Indexing complete!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static String getSectionTitle(Element sectionElement) {
        NodeList headElements = sectionElement.getElementsByTagName("head");
        if (headElements.getLength() > 0) {
            return headElements.item(0).getTextContent();
        }
        return "Untitled Section";
    }
}
