package com.bigtester.ate.ctg.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

//import utils.form.WebFormUserInputsCollector;

public class GlobalUtils {
	
	public static Document html2Dom(String source) throws IOException, ParserConfigurationException {
		
		DocumentBuilder db = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(source));
		Document doc = null;
		//WebFormUserInputsCollector col;
		try {
			
				doc = db.parse(is);
			
			//printDocument(doc.getDocumentElement(), System.out);
			//col = new WebFormUserInputsCollector(webD, doc, xpathOfFrame);
		} catch (SAXException e) {
			// create an instance of HtmlCleaner
			HtmlCleaner cleaner = new HtmlCleaner();
			 
			// take default cleaner properties
			CleanerProperties props = cleaner.getProperties();
			 
			TagNode node = cleaner.clean(source);
			 
			try {
				doc = new DomSerializer(props, true).createDOM(node);
			} catch (ParserConfigurationException e1) {
				// TODO Auto-generated catch block
				doc = null;
			}

		//	TolerantSaxDocumentBuilder tolerantSaxDocumentBuilder = new TolerantSaxDocumentBuilder(
//					XMLUnit.newTestParser());
//			HTMLDocumentBuilder db1 = new HTMLDocumentBuilder(
//					tolerantSaxDocumentBuilder);
//			doc = db1.parse(new StringReader(source));
			//printDocument(doc.getDocumentElement(), System.out);
			//col = new WebFormUserInputsCollector(webD, doc, xpathOfFrame);
		}
		return doc;
	}
	
	public static void printDocument(Node doc, OutputStream out)
			throws IOException, TransformerException {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		// transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		// transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		// transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		// transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
		// "4");

		transformer.transform(new DOMSource(doc), new StreamResult(
				new OutputStreamWriter(out, "UTF-8")));
	}

	public static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}

}
