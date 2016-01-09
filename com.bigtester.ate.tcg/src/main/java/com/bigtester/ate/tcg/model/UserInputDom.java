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
package com.bigtester.ate.tcg.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.Nullable;
import org.w3c.dom.Node;

// TODO: Auto-generated Javadoc
/**
 * This class InputDom defines ....
 * @author Peidong Hu
 *
 */
public class UserInputDom extends WebElementDom {
	
	/** The label dom pointer. */
	
	private List<Node> labelDomPointers = new ArrayList<Node>();
	
	/** The machine learning dom html pointers. */
	private transient List<Node> machineLearningDomHtmlPointers = new ArrayList<Node>();
	
	/** The additional info nodes. */
	private List<Node> additionalInfoNodes = new ArrayList<Node>();
	
	/** The previous sibling. */
	@Nullable
	private UserInputDom previousSibling;
	
	/** The next sibling. */
	@Nullable
	private UserInputDom nextSibling;
	
	/** The previous user viewable html sibling. */
	@Nullable
	private Node previousUserViewableHtmlSibling;
	
	/** The next user viewable html sibling. */
	@Nullable
	private Node nextUserViewableHtmlSibling;
	
	/** The parent form pointer. */
	@Nullable
	private Node parentFormPointer;
	
	/**
	 * Instantiates a new user input dom.
	 *
	 * @param inputDomNodePointer the input dom node pointer
	 */
	public UserInputDom(Node inputDomNodePointer) {
		super(inputDomNodePointer);
	}
	/**
	 * @return the labelDomPointer
	 */
	@Nullable
	public final List<Node> getLabelDomPointers() {
		return labelDomPointers;
	}
	/**
	 * @param labelDomPointer the labelDomPointer to set
	 */
	public final void setLabelDomPointers(List<Node> labelDomPointers) {
		this.labelDomPointers = labelDomPointers;
	}
	
	public final void addLabelDomPointer(@Nullable Node labelDomPointer) {
		this.labelDomPointers.add(labelDomPointer);
	}
	/**
	 * @return the machineLearningDomHtmlPointer
	 */
	public final List<Node> getMachineLearningDomHtmlPointers() {
		return machineLearningDomHtmlPointers;
	}
	/**
	 * @param machineLearningDomHtmlPointer the machineLearningDomHtmlPointer to set
	 */
	public final void setMachineLearningDomHtmlPointers(
			List<Node> machineLearningDomHtmlPointers) {
		this.machineLearningDomHtmlPointers = machineLearningDomHtmlPointers;
	}
	
	public final void addMachineLearningDomHtmlPointers(
			Node machineLearningDomHtmlPointer) {
		this.machineLearningDomHtmlPointers.add(machineLearningDomHtmlPointer);
	}
	/**
	 * @return the additionalInfoNodes
	 */
	public final List<Node> getAdditionalInfoNodes() {
		return additionalInfoNodes;
	}
	/**
	 * @param additionalInfoNodes the additionalInfoNodes to set
	 */
	public final void setAdditionalInfoNodes(List<Node> additionalInfoNodes) {
		this.additionalInfoNodes = additionalInfoNodes;
	}
	/**
	 * @return the previousSibling
	 */
	@Nullable
	public final UserInputDom getPreviousSibling() {
		return previousSibling;
	}
	/**
	 * @param previousSibling the previousSibling to set
	 */
	public final void setPreviousSibling(UserInputDom previousSibling) {
		this.previousSibling = previousSibling;
	}
	/**
	 * @return the nextSibling
	 */
	@Nullable
	public final UserInputDom getNextSibling() {
		return nextSibling;
	}
	/**
	 * @param nextSibling the nextSibling to set
	 */
	public final void setNextSibling(UserInputDom nextSibling) {
		this.nextSibling = nextSibling;
	}
//		labelDomPointer = findLabelNode();
//		additionalInfoNodes = findAdditionalInfoNodes();
//		previousSibling = findPreviousSibling();
//		nextSibling = findNextSibling();
	/**
	 * @return the previousUserViewableHtmlSibling
	 */
	@Nullable
	public Node getPreviousUserViewableHtmlSibling() {
		return previousUserViewableHtmlSibling;
	}
	/**
	 * @param previousUserViewableHtmlSibling the previousUserViewableHtmlSibling to set
	 */
	public void setPreviousUserViewableHtmlSibling(
			@Nullable Node previousUserViewableHtmlSibling) {
		this.previousUserViewableHtmlSibling = previousUserViewableHtmlSibling;
	}
	/**
	 * @return the nextUserViewableHtmlSibling
	 */
	@Nullable
	public Node getNextUserViewableHtmlSibling() {
		return nextUserViewableHtmlSibling;
	}
	/**
	 * @param nextUserViewableHtmlSibling the nextUserViewableHtmlSibling to set
	 */
	public void setNextUserViewableHtmlSibling(
			@Nullable Node nextUserViewableHtmlSibling) {
		this.nextUserViewableHtmlSibling = nextUserViewableHtmlSibling;
	}
	/**
	 * @return the parentFormPointer
	 */
	@Nullable
	public Node getParentFormPointer() {
		return parentFormPointer;
	}
	/**
	 * @param parentFormPointer the parentFormPointer to set
	 */
	public void setParentFormPointer(@Nullable Node parentFormPointer) {
		this.parentFormPointer = parentFormPointer;
	}
	
	
//	private Node findLabelNode() {
//		
//	}
//	private List<Node> findAdditionalInfoNodes() {
//		
//	}
//	
//	private UserInputDom findPreviousSibling() {
//		
//	}
//	private UserInputDom findNextSibling() {
//		
//	}
}
