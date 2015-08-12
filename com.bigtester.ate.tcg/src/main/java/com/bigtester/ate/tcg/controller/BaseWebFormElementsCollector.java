/*******************************************************************************
 * ATE, Automation Test Engine
 *
 * Copyright 2015, Montreal PROT, or individual contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Montreal PROT.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.bigtester.ate.tcg.controller;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.bigtester.ate.tcg.utils.exception.Html2DomException;

// TODO: Auto-generated Javadoc
/**
 * This class FormElementCollector defines ....
 * 
 * @author Peidong Hu
 *
 */
public class BaseWebFormElementsCollector {

	/** The dom doc. */
	final private Document domDoc;

	/** The cleaned doc. */
	final private Document cleanedDoc;

	/** The parent frame. */
	private String xpathOfParentFrame;

	/**
	 * Instantiates a new web form elements collector.
	 *
	 * @param domDoc
	 *            the dom doc
	 * @param parentFrame
	 *            the parent frame, Nullable
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @throws Html2DomException
	 */
	public BaseWebFormElementsCollector(Document domDoc,
			String xpathOfParentFrame) throws ParserConfigurationException,
			Html2DomException {
		this.domDoc = domDoc;
		this.xpathOfParentFrame = "";
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document tempDoc = builder.newDocument();
		if (null == tempDoc)
			throw new Html2DomException("dom builder");
		cleanedDoc = tempDoc;

		Node root = cleanedDoc.importNode(domDoc.getDocumentElement(), true);
		cleanedDoc.appendChild(root);
		Element tempElement = cleanedDoc.getDocumentElement();
		if (tempElement == null)
			throw new Html2DomException("dom element");
		fnCleanNode(tempElement);
		this.setXpathOfParentFrame(xpathOfParentFrame);
	}

	/**
	 * Fn clean node.
	 *
	 * @param node
	 *            the node
	 */
	private void fnCleanNode(Node node) {
		int index = 0;
		NodeList cNodes = node.getChildNodes();
		Node tmpNode;
		while ((tmpNode = cNodes.item(index++)) != null)//NOPMD
			// NOPMD
			switch (tmpNode.getNodeType()) { // NOPMD
			case Node.ELEMENT_NODE: // Element Node
				if (tmpNode.getNodeName().equalsIgnoreCase("br")) {
					node.removeChild(tmpNode);
					index--;// NOPMD
					break;
				} else {
					fnCleanNode(tmpNode);
					break;
				}
			case Node.TEXT_NODE: // Text Node
				if (tmpNode.getNodeValue().trim().equals("")) {
					node.removeChild(tmpNode);
					index--; //NOPMD
					break;
				} else {
					break;
				}
			case Node.COMMENT_NODE: // Comment Node (and Text Node without
									// non-whitespace
				// content)
				node.removeChild(tmpNode);
				index--;
			}
	}

	/**
	 * Gets the dom doc.
	 *
	 * @return the webDriver
	 */
	public Document getDomDoc() {
		return domDoc;
	}

	/**
	 * Gets the cleaned doc.
	 *
	 * @return the cleanedDoc
	 */
	public final Document getCleanedDoc() {
		return cleanedDoc;
	}

	/**
	 * @return the xpathOfParentFrame
	 */
	public String getXpathOfParentFrame() {
		return xpathOfParentFrame;
	}

	/**
	 * @param xpathOfParentFrame
	 *            the xpathOfParentFrame to set
	 */
	public void setXpathOfParentFrame(String xpathOfParentFrame) {
		this.xpathOfParentFrame = xpathOfParentFrame;
	}
}
