package com.bigtester.ate.ctg.ws;

import java.io.Serializable;

/**
 * The Class Greeting.
 */
public class HTMLSource implements Serializable{

    
    /**
	 * 
	 */
	private static final long serialVersionUID = 5806929726322626369L;
	/** The content. */
    private String content;

    /**
     * Instantiates a new greeting.
     *
     * @param mid the id
     * @param content the content
     */
    public HTMLSource(String content) {
        
        this.content = content;
    }
    
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
    public void setContent(String content) {
        this.content = content;
    }
}
