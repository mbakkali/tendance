package server;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private static final String template_name = "Le nom d'utilisateur est : %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/user")
    public User myuser(@RequestParam(value="username", defaultValue="myusername") String username) {
        return new User(counter.incrementAndGet(),
                            String.format(template_name, username));
    }
}