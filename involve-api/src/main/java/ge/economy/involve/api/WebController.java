package ge.economy.involve.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by nl on 7/15/2016.
 */
@Controller
@RequestMapping("/")
public class WebController {

    @RequestMapping("/{page}")
    public String home(@PathVariable String page) {
        return page;
    }

}
