package hello;
import java.util.Base64;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public @ResponseBody Greeting greeting(
            @RequestParam(value="name", required=false, defaultValue="World") String name) {
    	System.out.println("==== in greeting ====");
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

    @RequestMapping(value = "/updateImage", method = RequestMethod.POST)
    public @ResponseBody String updateImage(@RequestParam(value="image", required=false, defaultValue="") String image){    	
    	System.out.println("log");
        String img = image.replace("data:image/png;base64,","");
        byte[] data = Base64.getDecoder().decode(img);
        try (OutputStream stream = new FileOutputStream("D:/test2.png")) {
		   stream.write(data);
    		return "OK";
    	} catch (IOException e){
    		return "Failed";
    	}
    }
}