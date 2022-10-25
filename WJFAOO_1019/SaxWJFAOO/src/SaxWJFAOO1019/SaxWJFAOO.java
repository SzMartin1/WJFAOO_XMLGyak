package SaxWJFAOO1019;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxWJFAOO {
	public static void main(String[] args) {
		try {
			/*Dokumentumolvas� l�trehoz�sa, a gy�r objektumot a SAXParserFactory seg�ts�g�vel k�sz�tj�k el. */
			
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			
			/*P�ld�nyos�tja a SAX �rtelmez�t, a visszakapott gy�r �ll�tja el� a*/			
			SAXParser saxParser = saxParserFactory.newSAXParser();
			
			/*saj�t esem�nykezel� objektum l�trehoz�sa*/			
			SaxHandler handler = new SaxHandler();
			
			/*A dokumentum �rtelmez�si folyamat�nak elind�t�sa*/
			//A parse met�dus els� param�tere a beolvasand� XML f�jl neve, a m�sik pedig az esem�nykezel�
			saxParser.parse(new File("SzM_kurzusfelvetel.xml"),handler);
		}
		catch(ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
			
		}
	}

}

class SaxHandler extends DefaultHandler {
	private int indent = 0;
	
	private String formatAttributes(Attributes attributes){
		int attrLength = attributes.getLength();
		if(attrLength==0){
			return "";
		}
		StringBuilder sb = new StringBuilder(", {");
		for(int i= 0; i < attrLength; i++){
			sb.append(attributes.getLocalName(i)+ "=" + attributes.getValue(i));
			if (i < attrLength -1){
				sb.append(", ");
			}
		}
		sb.append("}");
		return sb.toString();
	}
	private void indent(){
		for(int i = 0; i< indent; i++){
			System.out.print("");
		}
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,Attributes attributes){
		indent++; //beh�z mindent
		indent(); //csak a startot h�zza be
		System.out.print(qName + formatAttributes(attributes)+ "\n start ");
	}
	
	@Override
	public void endElement(String uri, String localName, String qName){
		indent();
		indent--;
		System.out.print(qName+ " end\n");
	}
	//Sz�vegelem feldolgoz�sa, characters met�dust �jraimplement�ljuk.
	@Override
	public void characters(char ch[], int start, int length){
		String chars = new String(ch,start,length).trim();
		if(!chars.isEmpty()){
			indent++;
			indent();
			indent--;
			System.out.println(chars);
		}
	}
}
