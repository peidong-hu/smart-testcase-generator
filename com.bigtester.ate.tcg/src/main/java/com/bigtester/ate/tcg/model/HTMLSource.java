package com.bigtester.ate.tcg.model;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Greeting.
 */
public class HTMLSource implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5806929726322626369L;

	/** The xpath of frame. */
	private String xpathOfFrame;
	/** The content. */
	private String domDoc;

	private int parentIndex;

	/**
	 * Instantiates a new HTML source.
	 */
	public HTMLSource() {// spring won't be able to match the class to json if
							// there is no empty constructor

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
}
