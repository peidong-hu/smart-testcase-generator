package com.bigtester.ate.ctg.ws;

/**
 * The Class Greeting.
 */
public class Greeting {

    /** The id. */
    private final transient long mid;
    
    /** The content. */
    private final String content;

    /**
     * Instantiates a new greeting.
     *
     * @param mid the id
     * @param content the content
     */
    public Greeting(long mid, String content) {
        this.mid = mid;
        this.content = content;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public long getMid() {
        return mid;
    }

    /**
     * Gets the content.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }
}
