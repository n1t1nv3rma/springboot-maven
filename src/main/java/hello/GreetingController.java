package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;

@RestController
public class GreetingController {
    
    private final AtomicLong counter = new AtomicLong();
    private final String hostname = System.getenv("HOSTNAME");
    private final String myAppVer;
    
    
    @Autowired
    public GreetingController (@Value("${appver}") String myAppVer){
        this.myAppVer = myAppVer;
    }
    
    @RequestMapping("/greet")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format("Hello, %s! From host: " + hostname + " and Ver: " + myAppVer + ".", name));
    }
    
}