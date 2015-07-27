package com.bigtester.ate.ctg.ws;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import static org.joox.JOOX.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;

import com.bigtester.ate.ctg.model.Greeting;
import com.bigtester.ate.ctg.utils.GlobalUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class GreetingController.
 */
@RestController
public class GreetingController {

    /** The Constant template. */
    private static final String TEMPLATE = "Hello, %s!";
    
    /** The counter. */
    private final transient AtomicLong counter = new AtomicLong();

    /**
     * Greeting.
     *
     * @param name the name
     * @return the greeting
     * @throws ParserConfigurationException 
     * @throws IOException 
     * @throws TransformerException 
     */
    @CrossOrigin
    @RequestMapping(value="/greeting3", method = RequestMethod.POST)
    public Greeting greeting(@RequestBody HTMLSource dom) throws IOException, ParserConfigurationException, TransformerException {
    	Document doc = GlobalUtils.html2Dom(dom.getContent());
    	ByteArrayOutputStream stringOutput = new ByteArrayOutputStream();
		GlobalUtils.printDocument($(doc).find("input").get(0), stringOutput);
		String temp = stringOutput.toString();
        return new Greeting(counter.incrementAndGet(),
                            temp);
    }
    //@CrossOrigin
    @RequestMapping("/greeting4")
    public Greeting greeting() {
        return new Greeting(counter.incrementAndGet(),
                            String.format(TEMPLATE, "world"));
    }
}
