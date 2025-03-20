import java.io.File;
import java.nio.file.Files;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLParser {
    
    public static String parseXML(String filename) {
        StringBuilder textContent = new StringBuilder();
        
        try {
            // Check if file exists
            File xmlFile = new File(filename);
            if (!xmlFile.exists()) {
                System.err.println("File not found: " + filename);
                return "";
            }
            
            // Create DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            
            // Extract text from all <l> elements (lines)
            NodeList lineNodes = doc.getElementsByTagName("l");
            for (int i = 0; i < lineNodes.getLength(); i++) {
                Node node = lineNodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    textContent.append(element.getTextContent()).append("\n");
                }
            }
            
            System.out.println("Successfully parsed XML file: " + filename);
            
        } catch (Exception e) {
            System.err.println("Error parsing XML: " + e.getMessage());
            e.printStackTrace();
        }
        
        return textContent.toString();
    }
}
