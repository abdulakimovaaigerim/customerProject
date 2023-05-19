package peaksoft.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainApi {

    @GetMapping
    public String welcome() {
        return "main";

    }
}
