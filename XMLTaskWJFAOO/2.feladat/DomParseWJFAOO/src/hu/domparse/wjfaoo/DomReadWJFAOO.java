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

public class DomReadWJFAOO {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        //Forrás file
        File file = new File("XMLWjfaoo.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        Document doc = dBuilder.parse(file);

        doc.getDocumentElement().normalize();
        //Gyökérelem
        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
        //Gyerekelemek lementése
        NodeList nList = (NodeList) doc.getDocumentElement();

        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);


            //Ha Jatek_nev
            if(node.getNodeName() == "Jatek_nev"){
                if(!node.getNodeName().equals("#text")) {
                    System.out.println("\n");
                    System.out.println("Current element: " + node.getNodeName());
                }
                //Jatek_nev adatainak kiírása
                if(node.getNodeType()==Node.ELEMENT_NODE) {
                    Element elem = (Element) node;

                    String jatek_id = elem.getAttribute("Jatek_id");

                    Node jatek_node = elem.getElementsByTagName("kieg_nev").item(0);
                    String kieg_nev_name = jatek_node.getTextContent();

                    Node jatek_node2 = elem.getElementsByTagName("nyelv").item(0);
                    String nyelv_name = jatek_node2.getTextContent();

                    Node jatek_node3 = elem.getElementsByTagName("kiad_datum").item(0);
                    String kiad_datum_name = jatek_node3.getTextContent();

                    System.out.printf("Jatek id: %s%n", jatek_id);
                    System.out.printf("Kiegeszito neve: %s%n", kieg_nev_name);
                    System.out.printf("A jatek nyelve: %s%n", nyelv_name);
                    System.out.printf("Kiadas datuma: %s%n", kiad_datum_name);
                }
            }


        }
    }

}
