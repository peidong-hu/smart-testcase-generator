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
package com.bigtester.ate.tcg.controller;//NOPMD

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.jdt.annotation.Nullable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.common.collect.Iterables;
import com.bigtester.ate.tcg.model.UserInputDom;
import com.bigtester.ate.tcg.utils.exception.Html2DomException;

import static org.joox.JOOX.*;

// TODO: Auto-generated Javadoc
/**
 * This class InputsCollector defines ....
 * 
 * @author Peidong Hu
 *
 */
public class WebFormUserInputsCollector extends BaseWebFormElementsCollector {

	/** The Constant USER_NOT_CHANGABLE_INPUT_TYPES. */
	final static public String[] USER_NOT_CHANGABLE_INPUT_TYPES = { "hidden", "submit" };

	/** The Constant LEFT_LABELED_INPUT_TYPES. */
	final static public String[] LEFT_LABELED_INPUT_TYPES = { "text", "date",
			"button", "datetime", "datetime-local", "email", "file", "image",
			"month", "number", "password", "range", "reset", "search",
			 "tel", "time", "url", "week" };

	/** The Constant RIGHT_LABELED_INPUT_TYPES. */
	final static public String[] RIGHT_LABELED_INPUT_TYPES = { "radio",
			"checkbox" };

	/** The Constant USER_CHANGABLE_INPUT_TAGS. */
	final static public String[] USER_CHANGABLE_INPUT_TAGS = { "select",
			"input", "textarea" };

	/** The user inputs. */
	final private List<UserInputDom> userInputs = new ArrayList<UserInputDom>();

	/**
	 * Instantiates a new web form user inputs collector.
	 *
	 * @param domDoc
	 *            the dom doc
	 * @param parentFrame
	 *            the parent frame nullable
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @throws IOException
	 * @throws Html2DomException
	 */
	public WebFormUserInputsCollector(Document domDoc, String xpathOfParentFrame)
			throws ParserConfigurationException, IOException, Html2DomException {
		super(domDoc, xpathOfParentFrame);
		collectUserInputs(super.getCleanedDoc());
	}

	private boolean isUserChangableInputType(Node node) {
		boolean retVal = true; // NOPMD
		String nodeTag = node.getNodeName();

		if ("input".equalsIgnoreCase(nodeTag)) {
			if (null == $(node).attr("type")) {
				retVal = true;
			} else {
				for (int i = 0; i < USER_NOT_CHANGABLE_INPUT_TYPES.length; i++) {
					if ($(node).attr("type").equalsIgnoreCase(
							USER_NOT_CHANGABLE_INPUT_TYPES[i])) {
						retVal = false;// NOPMD
						break;
					}
				}
			}
		} else {
			retVal = true;
		}
		return retVal;
	}

	/**
	 * Collect user inputs.
	 *
	 * @param cleanedDoc
	 *            the cleaned doc
	 * @param originalDoc
	 *            the original doc
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void collectUserInputs(Document cleanedDoc) throws IOException {
		for (int j = 0; j < USER_CHANGABLE_INPUT_TAGS.length; j++) {
			NodeList htmlInputs = cleanedDoc
					.getElementsByTagName(USER_CHANGABLE_INPUT_TAGS[j]);
			for (int i = 0; i < htmlInputs.getLength(); i++) {
				Node coreNode = htmlInputs.item(i);
				List<Element> parentsUntilForm = $(coreNode).parentsUntil(
						"form").get();// NOPMD
				if (null != coreNode
						&& (parentsUntilForm.isEmpty() || !Iterables
								.get(parentsUntilForm,
										parentsUntilForm.size() - 1)
								.getNodeName().equalsIgnoreCase("html"))
						&& isUserChangableInputType(coreNode)
						&& !((Element) coreNode).getAttribute("ate-invisible")
								.equalsIgnoreCase("yes")) {

					if (parentsUntilForm.isEmpty()) {
						Node coreNodeParent = coreNode.getParentNode();

						userInputs.add(initUserInputDomInsideOfForm(cleanedDoc,
								coreNode, coreNodeParent));

					} else {
						List<Element> parents = $(coreNode)
								.parentsUntil("form").parent().get();
						Node tempNode = parents.get(parents.size() - 1);
						userInputs.add(initUserInputDomInsideOfForm(cleanedDoc,
								coreNode, tempNode));
					}
				} else {
					// TODO collect input out of form element
				}
			}
		}
	}

	private void fillOutNonLabeledFieldLabelDomPointer(
			UserInputDom valueHolder, Node searchStartingNode,
			Node searchUpEndingNode, boolean endingNodeInclusive,
			boolean leftLabeled) {
		Node tempParent2 = searchStartingNode;

		if (leftLabeled) {

			while (tempParent2.getPreviousSibling() == null
					&& !tempParent2.equals(searchUpEndingNode)) {
				// && tempParent2 != searchUpEndingNode) {
				tempParent2 = tempParent2.getParentNode();
			}
			// if tempParent2 is form node, we will use the form's
			// previous sibling as the label node;
			// Or we will use the nearest input sibling node as the
			// label node;
			if (tempParent2.equals(searchUpEndingNode) && !endingNodeInclusive)
				return; //NOPMD

			valueHolder.setLabelDomPointer(tempParent2.getPreviousSibling());
		} else {
			while (tempParent2.getNextSibling() == null
					&& !tempParent2.equals(searchUpEndingNode)) {
				tempParent2 = tempParent2.getParentNode();
			}
			// if tempParent2 is form node, we will use the form's
			// previous sibling as the label node;
			// Or we will use the nearest input sibling node as the
			// label node;
			if (tempParent2.equals(searchUpEndingNode) && !endingNodeInclusive)
				return;

			valueHolder.setLabelDomPointer(tempParent2.getNextSibling());
		}

	}

	private void fillOutAddtionalInfoNode(UserInputDom valueHolder,
			Node searchStartingNode, @Nullable Node searchUpEndingNode,
			boolean endingNodeInclusive, boolean nextSiblingOnly) {
		// fill out addtional info nodes
		Node tempParent2 = searchStartingNode;
		Node tempNode = tempParent2;// NOPMD

		while (tempParent2.getNextSibling() == null
				&& !tempParent2.equals(searchUpEndingNode)) {
			tempParent2 = tempParent2.getParentNode();
			tempNode = tempParent2; // NOPMD
		}
		if (tempNode.equals(searchUpEndingNode) && !endingNodeInclusive)
			return;
		List<Node> additionalInfoNodes = new ArrayList<Node>();
		if (nextSiblingOnly)
			additionalInfoNodes.add(tempNode.getNextSibling());
		else
			while (tempNode.getNextSibling() != null) {
				additionalInfoNodes.add(tempNode.getNextSibling());
				if ("form".equalsIgnoreCase(tempNode.getNodeName()))
					break;
				tempNode = tempNode.getNextSibling();
			}
		valueHolder.setAdditionalInfoNodes(additionalInfoNodes);
	}

	private void fillOutUserViewablePreviousSibling(UserInputDom valueHolder,
			Node searchStartingNode, @Nullable Node searchUpEndingNode,
			boolean endingNodeInclusive) {
		Node tempParent2 = searchStartingNode;

		while (tempParent2.getPreviousSibling() == null
				&& !tempParent2.equals(searchUpEndingNode)) {
			tempParent2 = tempParent2.getParentNode();
		}
		// if tempParent2 is form node, we will use the form's
		// previous sibling as the label node;
		// Or we will use the nearest input sibling node as the
		// label node;
		if (tempParent2.equals(searchUpEndingNode) && !endingNodeInclusive)
			return;
		valueHolder.setPreviousUserViewableHtmlSibling(tempParent2
				.getPreviousSibling());

	}

	private void fillOutUserViewableNextSibling(UserInputDom valueHolder,
			Node maxInputParentNoOtherChild, @Nullable Node searchUpEndingNode,
			boolean endingNodeInclusive) {

		Node tempParent2 = maxInputParentNoOtherChild;
		while (tempParent2.getNextSibling() == null
				&& !tempParent2.equals(searchUpEndingNode)) {
			// && tempParent2 != searchUpEndingNode) {
			tempParent2 = tempParent2.getParentNode();
		}
		if (tempParent2.equals(searchUpEndingNode) && !endingNodeInclusive)
			return;
		valueHolder
				.setNextUserViewableHtmlSibling(tempParent2.getNextSibling());
	}

	private Node getMaxInputParentNoOtherChild(Node inputNode) {
		Node tempParent2 = inputNode.getParentNode();
		Node maxInputParentNoOtherChild = inputNode; // NOPMD
		while (tempParent2 != null
				&& $(tempParent2).children().get().size() <= 1) {
			maxInputParentNoOtherChild = tempParent2;// NOPMD
			tempParent2 = tempParent2.getParentNode();
		}
		return maxInputParentNoOtherChild;
	}

	private int getNumberOfUserChangableInputsInNode(Node node) {
		int retVal = 0; // NOPMD
		for (int i = 0; i < USER_CHANGABLE_INPUT_TAGS.length; i++) {
			retVal = $(node).find(USER_CHANGABLE_INPUT_TAGS[i]).get().size()
					+ retVal;
		}
		return retVal;
	}

	private Node getMaxInputParentNoOtherInput(Node inputNode,
			boolean inputInForm) {
		Node tempParent = inputNode.getParentNode();
		Node maxInputParentNoOtherInput = inputNode; //NOPMD
		while (tempParent != null
				&& getNumberOfUserChangableInputsInNode(tempParent) <= 1) {
			maxInputParentNoOtherInput = tempParent; //NOPMD
			if (inputInForm
					&& tempParent.getNodeName().equalsIgnoreCase("form")) {
				break;
			}
			tempParent = tempParent.getParentNode();
		}
		return maxInputParentNoOtherInput;
	}

	private boolean isLeftLabeled(Node inputNode) {
		Node inputType = inputNode.getAttributes().getNamedItem("type");
		boolean retVal = true; // NOPMD
		if (null != inputType) { // NOPMD
			retVal = Arrays.asList(LEFT_LABELED_INPUT_TYPES).contains(
					inputType.getNodeValue());
		} else if (inputNode.getNodeName().equalsIgnoreCase("textarea")) {
			retVal = true;
		} else if (inputNode.getNodeName().equalsIgnoreCase("select")) {
			retVal = true;
		}
		return retVal;
	}

	private boolean siblingHasInput(Node node) {
		boolean retVal = false; // NOPMD
		for (int i = 0; i < USER_CHANGABLE_INPUT_TAGS.length; i++) {
			boolean nextSiblingIsInput = false; // NOPMD
			if (node.getNextSibling() != null
					&& $(node.getNextSibling()).tag() != null) {
				nextSiblingIsInput = Arrays.asList(USER_CHANGABLE_INPUT_TAGS)
						.contains($(node.getNextSibling()).tag().toLowerCase())
						|| $(node.getNextSibling()).find(
								USER_CHANGABLE_INPUT_TAGS[i]).isNotEmpty();
			}
			boolean previousSiblingIsInput = false; //NOPMD

			if (node.getPreviousSibling() != null
					&& $(node.getPreviousSibling()).tag() != null) {
				previousSiblingIsInput = Arrays.asList(
						USER_CHANGABLE_INPUT_TAGS).contains(
						$(node.getPreviousSibling()).tag().toLowerCase())
						|| $(node.getPreviousSibling()).find(
								USER_CHANGABLE_INPUT_TAGS[i]).isNotEmpty();
			}
			retVal = nextSiblingIsInput || previousSiblingIsInput;
			if (retVal)
				break;
		}
		return retVal;
	}

	private boolean leftRightDirectedSiblingHasNoInput(Node node,
			boolean leftLabeled) {
		boolean retVal = true; // NOPMD
		for (int i = 0; i < USER_CHANGABLE_INPUT_TAGS.length; i++) {

			if (leftLabeled) {
				if (node.getPreviousSibling() == null) {
					retVal = true; // NOPMD
				} else if (Arrays.asList(USER_CHANGABLE_INPUT_TAGS).contains(
						node.getPreviousSibling().getNodeName().toLowerCase()))
					retVal = false; //NOPMD
				else
					retVal = retVal
							&& $(node.getPreviousSibling()).find(
									USER_CHANGABLE_INPUT_TAGS[i]).isEmpty();
			}
			if (!leftLabeled) {
				if (node.getNextSibling() == null) {
					retVal = true; // NOPMD
				} else if (Arrays.asList(USER_CHANGABLE_INPUT_TAGS).contains(
						node.getNextSibling().getNodeName().toLowerCase()))
					retVal = false;// NOPMD
				else
					retVal = retVal
							&& $(node.getNextSibling()).find(
									USER_CHANGABLE_INPUT_TAGS[i]).isEmpty();
			}
		}
		return retVal;
	}

	private boolean leftRightDirectedSiblingWithLabel(Node node,
			boolean leftLabeled) {
		boolean retVal = false; // NOPMD
		if (leftLabeled) {
			if (node.getPreviousSibling() != null) {
				retVal = $(node.getPreviousSibling()).find("label") // NOPMD
						.isNotEmpty()
						|| node.getPreviousSibling().getNodeName()
								.equalsIgnoreCase("label");
			}
		} else {
			if (node.getNextSibling() != null) {
				retVal = $(node.getNextSibling()).find("label").isNotEmpty()
						|| node.getNextSibling().getNodeName()
								.equalsIgnoreCase("label");
			}
		}
		return retVal;
	}

	private List<Element> getLeftRightDirectedSiblingLabelInNode(Node node,
			boolean leftLabeled) {
		List<Element> labels2 = new ArrayList<Element>(); // NOPMD

		if (leftLabeled) {
			if (node.getPreviousSibling().getNodeName()
					.equalsIgnoreCase("label"))
				labels2.add((Element) node.getPreviousSibling());
			else
				labels2 = $(node.getPreviousSibling()).find("label").get();
		} else {
			if (node.getNextSibling().getNodeName().equalsIgnoreCase("label"))
				labels2.add((Element) node.getPreviousSibling());
			else
				labels2 = $(node.getNextSibling()).find("label").get();
		}
		if (labels2 == null)
			labels2 = new ArrayList<Element>();
		return labels2;
	}

	private UserInputDom initUserInputDomInsideOfForm(Document domDoc,
			Node inputNode, @Nullable Node form) {
		// NodeList allInputNodes, Node inputNodeParentForm) {
		UserInputDom retVal = new UserInputDom(inputNode);

		boolean leftLabeled = isLeftLabeled(inputNode); // NOPMD
		String tempstr = $(inputNode).xpath();
		if (tempstr != null)
			retVal.setXPath(tempstr);
		retVal.setParentFormPointer(form);

		Node maxInputParentNoOtherInput = getMaxInputParentNoOtherInput(
				inputNode, true);

		// Node tempParent = inputNode.getParentNode();
		boolean singleInputFieldForm = false; //NOPMD

		// Node maxInputParentNoOtherInput = tempParent;
		// while (($(tempParent).find("input").get().size() + $(tempParent)
		// .find("textarea").get().size()) <= 1) {
		// maxInputParentNoOtherInput = tempParent;
		// if (tempParent.getNodeName().equalsIgnoreCase("form")) {
		// singleFieldForm = true;
		// break;
		// }
		// tempParent = tempParent.getParentNode();
		// }
		// if signlefieldform leastInputsCommonParent is the form node
		Node leastInputsCommonParent;
		if (maxInputParentNoOtherInput.getNodeName().equalsIgnoreCase("form")) {
			leastInputsCommonParent = maxInputParentNoOtherInput; //NOPMD
			singleInputFieldForm = true;
		} else
			leastInputsCommonParent = maxInputParentNoOtherInput //NOPMD
					.getParentNode();

		boolean singleFieldFormInputHasNoSibling = false; //NOPMD
		Node maxInputParentNoOtherChild = getMaxInputParentNoOtherChild(inputNode);
		// Node tempParent2 = inputNode.getParentNode();
		// Node maxInputParentNoOtherChild = inputNode;
		// while ($(tempParent2).children().get().size() <= 1) {
		// maxInputParentNoOtherChild = tempParent2;
		if (maxInputParentNoOtherChild.getNodeName().equalsIgnoreCase("form")) {
			singleFieldFormInputHasNoSibling = true;
			// new a return result in a single input and no input
			// sibling form
			List<Node> temp1 = new ArrayList<Node>();
			temp1.add(maxInputParentNoOtherChild.getChildNodes().item(0));
			retVal.setMachineLearningDomHtmlPointer(temp1);

			retVal.setLabelDomPointer(maxInputParentNoOtherChild
					.getPreviousSibling());

			if (leftLabeled) {
				List<Node> temp = new ArrayList<Node>();
				temp.add(maxInputParentNoOtherChild.getNextSibling());
				retVal.setAdditionalInfoNodes(temp);
			}
			retVal.setPreviousUserViewableHtmlSibling(maxInputParentNoOtherChild
					.getPreviousSibling());
			retVal.setNextUserViewableHtmlSibling(maxInputParentNoOtherChild
					.getNextSibling());
			// break;
		}
		// tempParent2 = tempParent2.getParentNode();
		// }
		// TODO the parent of maxInputParentNoOtherChild might have input
		// fields, might not have input fields
		Node leastNonInputSiblingsParent = maxInputParentNoOtherChild //NOPMD
				.getParentNode();
		if (singleInputFieldForm && !singleFieldFormInputHasNoSibling) {
			List<Node> temp = new ArrayList<Node>();
			for (int i = 0; i < leastInputsCommonParent.getChildNodes()
					.getLength(); i++) {
				temp.add(leastInputsCommonParent.getChildNodes().item(i));
			}
			retVal.setMachineLearningDomHtmlPointer(temp);

			List<Element> labels = $(leastInputsCommonParent).find("label")
					.get();
			if (!labels.isEmpty()) { //NOPMD
				retVal.setLabelDomPointer(labels.get(0));
			} else {

				fillOutNonLabeledFieldLabelDomPointer(retVal,
						maxInputParentNoOtherChild, leastInputsCommonParent,
						false, leftLabeled);
				// tempParent2 = maxInputParentNoOtherChild;
				//
				// while (tempParent2.getPreviousSibling() == null
				// && !tempParent2.getNodeName().equalsIgnoreCase(
				// "form")) {
				// tempParent2 = tempParent2.getParentNode();
				// }
				// // if tempParent2 is form node, we will use the form's
				// // previous sibling as the label node;
				// // Or we will use the nearest input sibling node as the
				// // label node;
				// retVal.setLabelDomPointer(tempParent2.getPreviousSibling());

			}

			// fill out addtional info nodes
			if (leftLabeled)
				fillOutAddtionalInfoNode(retVal, maxInputParentNoOtherChild,
						leastInputsCommonParent, false, false);
			// tempParent2 = maxInputParentNoOtherChild;
			// List<Node> additionalInfoNodes = new ArrayList<Node>();
			// while (tempParent2.getNextSibling() == null
			// && !tempParent2.getNodeName().equalsIgnoreCase("form")) {
			// tempParent2 = tempParent2.getParentNode();
			// Node tempNode = tempParent2;
			// while (tempNode.getNextSibling() != null) {
			// additionalInfoNodes.add(tempNode.getNextSibling());
			// if (tempNode.getNodeName().equalsIgnoreCase("form")) break;
			// tempNode = tempNode.getNextSibling();
			// }
			// }
			// retVal.setAdditionalInfoNodes(additionalInfoNodes);

			// fill out non empty user viewable previous sibling
			fillOutUserViewablePreviousSibling(retVal,
					maxInputParentNoOtherChild, leastInputsCommonParent, false);
			// tempParent2 = maxInputParentNoOtherChild;
			//
			// while (tempParent2.getPreviousSibling() == null
			// && !tempParent2.getNodeName().equalsIgnoreCase(
			// "form")) {
			// tempParent2 = tempParent2.getParentNode();
			// }
			// // if tempParent2 is form node, we will use the form's
			// // previous sibling as the label node;
			// // Or we will use the nearest input sibling node as the
			// // label node;
			// retVal.setPreviousUserViewableHtmlSibling(tempParent2.getPreviousSibling());

			// set non empty user viewable next sibling
			fillOutUserViewableNextSibling(retVal, maxInputParentNoOtherChild,
					leastInputsCommonParent, false);
			// tempParent2 = maxInputParentNoOtherChild;
			// while (tempParent2.getNextSibling() == null
			// && !tempParent2.getNodeName().equalsIgnoreCase("form")) {
			// tempParent2 = tempParent2.getParentNode();
			// }
			// retVal.setNextUserViewableHtmlSibling(tempParent2.getNextSibling());

		} else if (!singleFieldFormInputHasNoSibling) {

			List<Element> labels = $(maxInputParentNoOtherInput).find("label")
					.get();
			if (!labels.isEmpty()) { //NOPMD

				List<Node> tempList = new ArrayList<Node>();
				tempList.add(maxInputParentNoOtherInput);
				retVal.setMachineLearningDomHtmlPointer(tempList);

				retVal.setLabelDomPointer(labels.get(0));
				if (leftLabeled && leastNonInputSiblingsParent!=null)
					fillOutAddtionalInfoNode(retVal,
							maxInputParentNoOtherChild,
							leastNonInputSiblingsParent, false, false);
				fillOutUserViewablePreviousSibling(retVal,
						maxInputParentNoOtherChild,
						leastNonInputSiblingsParent, false);
				fillOutUserViewableNextSibling(retVal,
						maxInputParentNoOtherChild,
						leastNonInputSiblingsParent, false);
			} else {
				if (leftRightDirectedSiblingHasNoInput(
						maxInputParentNoOtherInput, leftLabeled)
						&& leftRightDirectedSiblingWithLabel(
								maxInputParentNoOtherInput, leftLabeled)) {
					List<Element> labels2 = getLeftRightDirectedSiblingLabelInNode(
							maxInputParentNoOtherInput, leftLabeled);

					List<Node> tempList = new ArrayList<Node>();
					if (leftLabeled) {
						tempList.add(maxInputParentNoOtherInput
								.getPreviousSibling());
						tempList.add(maxInputParentNoOtherInput);
					} else {
						tempList.add(maxInputParentNoOtherInput);
						tempList.add(maxInputParentNoOtherInput
								.getNextSibling());
					}
					retVal.setMachineLearningDomHtmlPointer(tempList);

					retVal.setLabelDomPointer(labels2.get(0));

					if (leftLabeled
							&& leftRightDirectedSiblingHasNoInput(
									maxInputParentNoOtherInput, false))
						fillOutAddtionalInfoNode(retVal,
								maxInputParentNoOtherInput,
								maxInputParentNoOtherInput.getParentNode(),
								false, true);
					fillOutUserViewablePreviousSibling(retVal,
							maxInputParentNoOtherInput,
							maxInputParentNoOtherInput.getParentNode(), false);
					fillOutUserViewableNextSibling(retVal,
							maxInputParentNoOtherInput,
							maxInputParentNoOtherInput.getParentNode(), false);
				} else if (this.siblingHasInput(maxInputParentNoOtherInput)) {
					// most likely field (label equivelant information has been
					// inside Node
					// maxInputParentNoOtherInput
					List<Node> tempList = new ArrayList<Node>();
					tempList.add(maxInputParentNoOtherInput);
					retVal.setMachineLearningDomHtmlPointer(tempList);

					fillOutNonLabeledFieldLabelDomPointer(retVal,
							maxInputParentNoOtherChild,
							maxInputParentNoOtherInput, false, leftLabeled);
					if (leftLabeled)
						fillOutAddtionalInfoNode(retVal,
								maxInputParentNoOtherChild,
								maxInputParentNoOtherInput, false, false);
					fillOutUserViewablePreviousSibling(retVal,
							maxInputParentNoOtherChild,
							maxInputParentNoOtherInput, false);
					fillOutUserViewableNextSibling(retVal,
							maxInputParentNoOtherChild,
							maxInputParentNoOtherInput, false);

				} else if (leftRightDirectedSiblingHasNoInput(
						maxInputParentNoOtherInput, leftLabeled)) {
					// most likely field information is out of
					// maxInputParentNoOtherInput in its siblings with no label
					// tag
					List<Node> tempList = new ArrayList<Node>();
					if (leftLabeled) {
						tempList.add(maxInputParentNoOtherInput
								.getPreviousSibling());
						tempList.add(maxInputParentNoOtherInput);
					} else {
						tempList.add(maxInputParentNoOtherInput);
						tempList.add(maxInputParentNoOtherInput
								.getNextSibling());
					}
					retVal.setMachineLearningDomHtmlPointer(tempList);

					if (leftLabeled)
						retVal.setLabelDomPointer(maxInputParentNoOtherInput
								.getPreviousSibling());
					else
						retVal.setLabelDomPointer(maxInputParentNoOtherInput
								.getNextSibling());

					if (leftLabeled
							&& leftRightDirectedSiblingHasNoInput(
									maxInputParentNoOtherInput, false))
						fillOutAddtionalInfoNode(retVal,
								maxInputParentNoOtherInput,
								maxInputParentNoOtherInput.getParentNode(),
								false, true);

					fillOutUserViewablePreviousSibling(retVal,
							maxInputParentNoOtherInput,
							maxInputParentNoOtherInput.getParentNode(), false);
					fillOutUserViewableNextSibling(retVal,
							maxInputParentNoOtherInput,
							maxInputParentNoOtherInput.getParentNode(), false);

				}
			}
			// if (leastNonInputSiblingsParent != leastInputsCommonParent) {
			// tempParent2 = leastNonInputSiblingsParent;
			// while (tempParent2.getParentNode() != leastInputsCommonParent) {
			// tempParent2.getParentNode();
			// }
			// Node maxNonInputSiblingsParent = tempParent2;
			//
			// } else {
			// List<Element> allInputSiblings = $(leastInputsCommonParent)
			// .children().get();
			// for (int indexOfDirectChild; indexOfDirectChild <
			// allInputSiblings
			// .size(); indexOfDirectChild++) {
			// // if the sibling has form in it, then ignore this
			// // sibling since it and its children inputs are not
			// // related to this current processing input.
			// if ($(allInputSiblings.get(indexOfDirectChild))
			// .find("form").get().size() > 0) {
			// continue;
			// }
			//
			// int sizeOfInputElementInSibling = $(
			// allInputSiblings.get(indexOfDirectChild))
			// .find("input").get().size();
			// if (sizeOfInputElementInSibling == 1) {
			// // input block which might have label and additional
			// // info
			// Node nextSibling = allInputSiblings.get(
			// indexOfDirectChild).getNextSibling();
			// Node previousSibling = allInputSiblings.get(
			// indexOfDirectChild).getPreviousSibling();
			// if (previousSibling == null) {
			// // first child in least common parent
			//
			// } else if (nextSibling == null) {
			// // last child in least common parent
			// } else {
			// // neither first nor last child.
			// if ($(nextSibling).find("input").get().size() == 1) {
			// // this html block most likely a label
			// }
			// }
			// } else if (sizeOfInputElementInSibling == 0) {
			// // might be label or other information of the next
			// // input or might be non input related infor.
			// continue;
			//
			// } else if (sizeOfInputElementInSibling > 1) {
			// // html block has more than one input, need to find
			// // the least parent for these inputs.
			// // TODO need a nest function to handle this type of
			// // html block
			// }
			// }
			// }
		}
		return retVal;
	}

	/**
	 * @return the userInputs
	 */
	public final List<UserInputDom> getUserInputs() {
		return userInputs;
	}

	// private boolean has2ChildInputNodes(Node node) {
	// boolean retVal;
	// NodeList childNodes = node.getChildNodes();
	// }
	//
	// private Node findLabelNodeOfInput(Node inputNode, Document domDoc) {
	// Node retVal;
	// Node previousSibling = inputNode.getPreviousSibling();
	// if (previousSibling.getNodeName().equalsIgnoreCase("label")) {
	// retVal = previousSibling;
	// } else if
	// (inputNode.getParentNode().getPreviousSibling().getNodeName().equalsIgnoreCase("label"))
	// {
	// retVal = previousSibling;
	// } else {
	// while (inputNode.getParentNode().getChildNodes())
	// }
	// }
}
