/*******************************************************************************
 * ATE, Automation Test Engine
 *
 * Copyright 2016, Montreal PROT, or individual contributors as
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
package com.bigtester.ate.tcg.model.domain;


import org.eclipse.jdt.annotation.Nullable;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class Greeting.
 */
@NodeEntity
public class HTMLSource extends BaseAteNode{
	
	/** The id. */
	@GraphId
	@Nullable
	private Long id; //NOPMD
	///** The Constant serialVersionUID. */
	//private static final long serialVersionUID = 5806929726322626369L;

	/** The timestamp. */
	@Nullable
	private Long timestamp=System.currentTimeMillis(); 
	
	/** The xpath of frame. */
	@Index
	private String xpathOfFrame;
	/** The content. */
	private String domDoc;

	/** The parent index. */
	private int parentIndex;
	
	/** The doc text. */
	private String docText;
	
	/** The visible. */
	private boolean visible;

	/**
	 * Instantiates a new HTML source.
	 * spring won't be able to match the class to json if
	 * there is no empty constructor
	 */
	public HTMLSource() {
		super("HTMLSource");
		xpathOfFrame = "";
		domDoc = "";
		docText = "";
		visible = true;
		
	}



	/**
	 * @return the xpathOfFrame
	 */
	public String getXpathOfFrame() {
		return xpathOfFrame;
	}

	/**
	 * @param xpathOfFrame
	 *            the xpathOfFrame to set
	 */
	public void setXpathOfFrame(String xpathOfFrame) {
		this.xpathOfFrame = xpathOfFrame;
	}

	/**
	 * @return the domDoc
	 */
	public String getDomDoc() {
		return domDoc;
	}

	/**
	 * @param domDoc the domDoc to set
	 */
	public void setDomDoc(String domDoc) {
		this.domDoc = domDoc;
	}



	/**
	 * @return the parentIndex
	 */
	public int getParentIndex() {
		return parentIndex;
	}



	/**
	 * @param parentIndex the parentIndex to set
	 */
	public void setParentIndex(int parentIndex) {
		this.parentIndex = parentIndex;
	}



	/**
	 * @return the id
	 */
	@Nullable
	public Long getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {//NOPMD
		this.id = id;
	}



	/**
	 * @return the docText
	 */
	public String getDocText() {
		return docText;
	}



	/**
	 * @param docText the docText to set
	 */
	public void setDocText(String docText) {
		this.docText = docText;
	}



	/**
	 * @return the timestamp
	 */
	@Nullable
	public Long getTimestamp() {
		return timestamp;
	}



	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}



	/**
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}



	/**
	 * @param visible the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}




}
