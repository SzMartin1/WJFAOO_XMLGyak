package hu.domparse.wjfaoo;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class DOMQueryWJFAOO {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        //file nev megadasa
        File file = new File("XMLWjfaoo.xml");
        //parszolas
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        Document doc = dBuilder.parse(file);
        doc.getDocumentElement().normalize();
        //gyokerelem kiiratasa
        System.out.print("Root element: ");
        System.out.println(doc.getDocumentElement().getNodeName());
        //Szorny mentese listaba
        NodeList nList = doc.getElementsByTagName("Szorny");

        System.out.println("----------------------------");

        //Szornyek es gyerek elemeik megjelenitese a consolon
        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);
            System.out.println("\nCurrent Element : "+node.getNodeName());
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;
                System.out.println("ID:"+elem.getAttribute("S_id"));
                NodeList nList2 = elem.getChildNodes();
                for (int j = 0; j < nList2.getLength(); j++) {
                    Node node2 = nList2.item(j);
                    if (node2.getNodeType() == Node.ELEMENT_NODE) {
                        Element elem2 = (Element) node2;
                        if(!node2.getNodeName().equals("jutalom")) {
                            System.out.println(node2.getNodeName()+" : "+node2.getTextContent());
                        }
                        NodeList nList3 = elem2.getChildNodes();
                        for (int k = 0; k < nList3.getLength(); k++) {
                            Node node3 = nList3.item(k);
                            if(node3.getNodeType()==Node.ELEMENT_NODE) {
                                Element elem3 = (Element) node3;
                                System.out.println("Szorny :	"+node3.getNodeName()+" : "+node3.getTextContent());
                            }
                        }
                    }
                }
            }
        }
    }

}
