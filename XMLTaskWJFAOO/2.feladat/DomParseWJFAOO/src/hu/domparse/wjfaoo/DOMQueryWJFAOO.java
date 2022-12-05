package hu.domparse.wjfaoo;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class DOMQueryWJFAOO {
    public static void main(String[] args) {
        try {
// DocumentBuilder létrehozása
            DocumentBuilderFactory documentBuilderFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder =
                    documentBuilderFactory.newDocumentBuilder();
            Document document =
                    documentBuilder.parse("XMLWjfaoo.xml");
            document.getDocumentElement().normalize();
// az XPath készítése
            XPath xPath =
                    XPathFactory.newInstance().newXPath();
// meg kell adni az elérési út kifejezést és csomópont listát
// a Tarsasjatek (Root elem) Kaszt gyerekelemeinek lekérdezése

            String expression = "Tarsasjatek / Kaszt";
// 3-as ID-jű Viselet lekérdezése
//String expression = "//Viselet[@V_id='03']";
// a második Jatek_nev kiválasztása
//String expression = "Tarsasjatek/Jatek_nev[2]";
// az a kaszt, aminek a képessége : + 1 kincslap húzás
//String expression = "//Kaszt[kepesseg='+1 kincslap húzás']";
// Jatek_nev kiegészítő neve, és kiadási dátuma
//String expression = "//kieg_nev | //kiad_datum";
//Kasztok akiknek van attribútuma
//String expression = "//Kaszt[@*]";
// Listakészítés, xPath kifejezés ----> fordítás + értékelés
            NodeList nodeList = (NodeList)
                    xPath.compile(expression).evaluate(document,
                            XPathConstants.NODESET);
// A NodeList csomópontjain való iterálás
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                System.out.println("\nAktuális elem: " + node.getNodeName());
// Csomópontvizsgálás, Subelement tesztelése (most Kaszt)
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("Kaszt")) {
                    Element element = (Element) node;
                    System.out.println("ID: " + element.getAttribute("K_id"));
                    System.out.println("Képesség: " + element.getElementsByTagName("kepesseg").item(0).getTextContent());
                    System.out.println("Alap tulajdonság: " + element.getElementsByTagName("alap_tul").item(0).getTextContent());
                    System.out.println("Kaszt nehézsége: " + element.getElementsByTagName("k_nehezseg").item(0).getTextContent());
                }
//Jatek_nev kiíratása
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("Jatek_nev")) {
                    Element element = (Element) node;
                    System.out.println("ID: " + element.getAttribute("Jatek_id"));
                    System.out.println("Kiegészítő neve: " + element.getElementsByTagName("kieg_nev").item(0).getTextContent());
                    System.out.println("A játék nyelve: " + element.getElementsByTagName("nyelv").item(0).getTextContent());
                    System.out.println("A jaték kiadási dátuma: " + element.getElementsByTagName("kiad_datum").item(0).getTextContent());
                }
 //Viselet kiíratása
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("Viselet")) {
                    Element element = (Element) node;
                    System.out.println("ID: " + element.getAttribute("V_id"));
                    System.out.println("Kaszt neve, melynek speciális felszerelése: " + element.getElementsByTagName("kaszt_spec").item(0).getTextContent());
                    System.out.println("Milyen fajnak a felszerelése: " + element.getElementsByTagName("fajta").item(0).getTextContent());
                    System.out.println("Milyen bónuszt ad: " + element.getElementsByTagName("bonusz").item(0).getTextContent());
                    System.out.println("Egyedi tulajdonság: " + element.getElementsByTagName("egyedi_tul").item(0).getTextContent());
                }
//Kaszt képességének kiírása
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("kepesseg")) {
                    Element element = (Element) node;
                    System.out.println("A kaszt képessége: " + element.getTextContent());
                }
// Jatek_Nev kieg_nev kiíratása
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("kieg_nev")) {
                    Element element = (Element) node;
                    System.out.println("A játék kiegészítőjének a neve: " + element.getTextContent());
                }
// Jatek_nev kiad_datum kiíratása
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("kiad_datum")) {
                    Element element = (Element) node;
                    System.out.println("Kiadási dátum: " + element.getTextContent());
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }


}
