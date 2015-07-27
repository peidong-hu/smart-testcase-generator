package com.bigtester.ate.ctg.ws;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Greeting.
 */
public class HTMLSource implements Serializable{

    
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5806929726322626369L;
	private String xpathOfFrame;
	/** The content. */
    private String content;

    /**
     * Instantiates a new greeting.
     *
     * @param content the content
     */
    public HTMLSource(String content) {
        
        this.content = content;
    }
    
    /**
     * Instantiates a new HTML source.
     */
    public HTMLSource() {
        
        
    }
    /**
     * Gets the content.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }
    
    /**
     * Sets the content.
     *
     * @param content the new content
     */
    public void setContent(String content) {
        this.content = content;
    }

	/**
	 * @return the xpathOfFrame
	 */
	public String getXpathOfFrame() {
		return xpathOfFrame;
	}

	/**
	 * @param xpathOfFrame the xpathOfFrame to set
	 */
	public void setXpathOfFrame(String xpathOfFrame) {
		this.xpathOfFrame = xpathOfFrame;
	}
}
