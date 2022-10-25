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
			/*Dokumentumolvasó létrehozása, a gyár objektumot a SAXParserFactory segítségével készítjük el. */
			
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			
			/*Példányosítja a SAX értelmezõt, a visszakapott gyár állítja elõ a*/			
			SAXParser saxParser = saxParserFactory.newSAXParser();
			
			/*saját eseménykezelõ objektum létrehozása*/			
			SaxHandler handler = new SaxHandler();
			
			/*A dokumentum értelmezési folyamatának elindítása*/
			//A parse metódus elsõ paramétere a beolvasandó XML fájl neve, a másik pedig az eseménykezelõ
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
		indent++; //behúz mindent
		indent(); //csak a startot húzza be
		System.out.print(qName + formatAttributes(attributes)+ "\n start ");
	}
	
	@Override
	public void endElement(String uri, String localName, String qName){
		indent();
		indent--;
		System.out.print(qName+ " end\n");
	}
	//Szövegelem feldolgozása, characters metódust újraimplementáljuk.
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
