package com.bigtester.ate.ctg.ws;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;

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
     */
    @CrossOrigin
    @RequestMapping(value="/greeting3", method = RequestMethod.POST)
    public Greeting greeting(@RequestBody HTMLSource dom) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(TEMPLATE, dom.getContent().substring(0, 5)));
    }
    @CrossOrigin
    @RequestMapping("/greeting4")
    public Greeting greeting() {
        return new Greeting(counter.incrementAndGet(),
                            String.format(TEMPLATE, "world"));
    }
}
