package hu.domparse.wjfaoo;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMModifyWJFAOO {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        try {
            //Forrás file
            File inputFile = new File("XML2Wjfaoo.xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(inputFile);

            //ELső és harmadik Kaszt mentése
            Node kaszt1 = doc.getElementsByTagName("Kaszt").item(0);
            Node kaszt3 = doc.getElementsByTagName("Kaszt").item(2);
            //Gyökérelem
            Node Tarsasjatek = doc.getFirstChild();

            //Harmadik Kasztnak az  ID váltása
            NamedNodeMap attr = kaszt3.getAttributes();
            Node nodeAttr = attr.getNamedItem("K_id");
            nodeAttr.setTextContent("06");

            //Első Kaszt képességének megváltoztatása
            NodeList list = kaszt1.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    if ("kepesseg".equals(elem.getNodeName())) {
                        elem.setTextContent("nincs képessége");
                    }
                }
            }

            //Első Kasztnak az alap tulajdonságának a megváltoztatása
            NodeList list1 = kaszt1.getChildNodes();
            for (int i = 0; i < list1.getLength(); i++) {
                Node node1 = list1.item(i);
                if (node1.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem1 = (Element) node1;
                    if ("alap_tul".equals(elem1.getNodeName())) {
                        elem1.setTextContent("nincs alap tulajdonsága");
                    }
                }
            }

            //Fajok törlése
            NodeList childNodes = Tarsasjatek.getChildNodes();
            for(int i = 0; i < childNodes.getLength(); i++) {
                Node node = childNodes.item(i);

                if("Faj".equals(node.getNodeName()))
                    Tarsasjatek.removeChild(node);
            }

            //Konzolra kírás
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            System.out.println("New File");
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
