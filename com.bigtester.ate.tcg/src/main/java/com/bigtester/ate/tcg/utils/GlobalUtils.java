package com.bigtester.ate.tcg.utils;

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

// TODO: Auto-generated Javadoc
/**
 * The Class GlobalUtils.
 */
public final class GlobalUtils {
	
	private GlobalUtils() {
		
	}
	/**
	 * Html2 dom.
	 *
	 * @param source the source
	 * @return the document
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParserConfigurationException the parser configuration exception
	 */
	public static Document html2Dom(String source) throws IOException, ParserConfigurationException {
		
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		InputSource inputS = new InputSource();
		inputS.setCharacterStream(new StringReader(source));
		Document doc = null;
		//WebFormUserInputsCollector col;
		try {
			
				doc = docBuilder.parse(inputS);
			
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
	
	/**
	 * Prints the document.
	 *
	 * @param doc the doc
	 * @param out the out
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws TransformerException the transformer exception
	 */
	public static void printDocument(Node doc, OutputStream out)//NOPMD
			throws IOException, TransformerException {
		TransformerFactory transF = TransformerFactory.newInstance();
		Transformer transformer = transF.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		// transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		// transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		// transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		// transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
		// "4");

		transformer.transform(new DOMSource(doc), new StreamResult(
				new OutputStreamWriter(out, "UTF-8")));
	}

	/**
	 * Gets the string from input stream.
	 *
	 * @param inputS the is
	 * @return the string from input stream
	 */
	public static String getStringFromInputStream(InputStream inputS) {

		BufferedReader reader = null;
		StringBuilder stringBuilder = new StringBuilder();

		String line;
		try {

			reader = new BufferedReader(new InputStreamReader(inputS));
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return stringBuilder.toString();

	}

}
