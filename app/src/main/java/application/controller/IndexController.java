package application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    public String home() {
        return "WEB-INF/home.jsp";
    }
}

