package com.bigtester.ate.tcg.model.domain;

import java.io.Serializable;

import org.eclipse.jdt.annotation.Nullable;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class Greeting.
 */
@NodeEntity
public class HTMLSource implements Serializable {
	
	/** The id. */
	@GraphId
	@Nullable
	private Long id; //NOPMD
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5806929726322626369L;

	/** The xpath of frame. */
	private String xpathOfFrame;
	/** The content. */
	private String domDoc;

	/** The parent index. */
	private int parentIndex;

	/**
	 * Instantiates a new HTML source.
	 * spring won't be able to match the class to json if
	 * there is no empty constructor
	 */
	public HTMLSource() {
		super();
		xpathOfFrame = "";
		domDoc = "";
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
}
