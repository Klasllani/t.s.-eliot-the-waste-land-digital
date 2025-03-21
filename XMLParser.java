import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.File;

public class XMLParser {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java XMLParser <xml-file>");
            System.exit(1);
        }
        
        String xmlFile = args[0];
        
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(xmlFile));
            
            document.getDocumentElement().normalize();
            
            // Print root element
            System.out.println("Root element: " + document.getDocumentElement().getNodeName());
            
            // Get all div elements
            NodeList divList = document.getElementsByTagName("div");
            System.out.println("Number of sections: " + divList.getLength());
            
            // Process each div (section)
            for (int i = 0; i < divList.getLength(); i++) {
                Element div = (Element) divList.item(i);
                String type = div.getAttribute("type");
                
                if (type.equals("section")) {
                    // Get section title
                    NodeList headList = div.getElementsByTagName("head");
                    String title = "Untitled";
                    if (headList.getLength() > 0) {
                        title = headList.item(0).getTextContent();
                    }
                    
                    System.out.println("\nSection " + (i+1) + ": " + title);
                    
                    // Get stanzas
                    NodeList lgList = div.getElementsByTagName("lg");
                    System.out.println("Number of stanzas: " + lgList.getLength());
                    
                    // Process each stanza
                    for (int j = 0; j < lgList.getLength(); j++) {
                        Element lg = (Element) lgList.item(j);
                        
                        System.out.println("\n  Stanza " + (j+1) + ":");
                        
                        // Get lines
                        NodeList lList = lg.getElementsByTagName("l");
                        
                        // Process each line
                        for (int k = 0; k < lList.getLength(); k++) {
                            Element l = (Element) lList.item(k);
                            String lineText = l.getTextContent();
                            System.out.println("    Line " + (k+1) + ": " + lineText);
                        }
                    }
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
