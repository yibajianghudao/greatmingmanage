package com.manage.greatming.greatmingmanage.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    
    @GetMapping
    public String Home(){
        return "/home.html";
    }

}
