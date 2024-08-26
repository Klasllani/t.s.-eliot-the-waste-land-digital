package com.example;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class XMLParser {
    public static String parseXML(String filePath) {
        StringBuilder content = new StringBuilder();
        try {
            File file = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();
            
            // Extract text from all <l> tags within <lg> tags
            NodeList lgNodeList = doc.getElementsByTagName("lg");
            for (int i = 0; i < lgNodeList.getLength(); i++) {
                Node lgNode = lgNodeList.item(i);
                if (lgNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element lgElement = (Element) lgNode;
                    NodeList lNodeList = lgElement.getElementsByTagName("l");
                    for (int j = 0; j < lNodeList.getLength(); j++) {
                        Node lNode = lNodeList.item(j);
                        if (lNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element lElement = (Element) lNode;
                            content.append(lElement.getTextContent()).append("\n");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
