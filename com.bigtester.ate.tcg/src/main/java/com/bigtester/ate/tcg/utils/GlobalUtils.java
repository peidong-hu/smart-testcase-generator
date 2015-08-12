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

import com.bigtester.ate.tcg.utils.exception.Html2DomException;

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
	 * @param source
	 *            the source
	 * @return the document
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @throws Html2DomException
	 */
	public static Document html2Dom(String source) throws IOException,
			ParserConfigurationException, Html2DomException {

		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		InputSource inputS = new InputSource();
		inputS.setCharacterStream(new StringReader(source));
		Document doc;
		// WebFormUserInputsCollector col;
		try {

			doc = docBuilder.parse(inputS);// NOPMD

			// printDocument(doc.getDocumentElement(), System.out);
			// col = new WebFormUserInputsCollector(webD, doc, xpathOfFrame);
		} catch (SAXException e) {
			// create an instance of HtmlCleaner
			HtmlCleaner cleaner = new HtmlCleaner();

			// take default cleaner properties
			CleanerProperties props = cleaner.getProperties();

			TagNode node = cleaner.clean(source);
			doc = new DomSerializer(props, true).createDOM(node);
		}
		if (null == doc)
			throw new Html2DomException("html can't be converted to dom");
		return doc;
	}

	/**
	 * Prints the document.
	 *
	 * @param doc
	 *            the doc
	 * @param out
	 *            the out
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws TransformerException
	 *             the transformer exception
	 */
	public static void printDocument(Node doc, OutputStream out)// NOPMD
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
	 * @param inputS
	 *            the is
	 * @return the string from input stream
	 * @throws IOException
	 */
	public static String getStringFromInputStream(InputStream inputS)
			throws IOException {

		BufferedReader reader;
		StringBuilder stringBuilder = new StringBuilder();
		String line;

		reader = new BufferedReader(new InputStreamReader(inputS));
		while ((line = reader.readLine()) != null) {// NOPMD
			stringBuilder.append(line);
		}
		if (reader != null) {
			reader.close();
		}
		String retVal = stringBuilder.toString();
		if (null == retVal)
			retVal = "";
		return retVal;

	}

}
