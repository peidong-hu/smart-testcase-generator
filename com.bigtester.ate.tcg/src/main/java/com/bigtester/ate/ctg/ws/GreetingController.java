package com.bigtester.ate.ctg.ws;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping("/greeting3")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(TEMPLATE, name));
    }
}
