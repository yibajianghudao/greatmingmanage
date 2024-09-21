package com.manage.greatming.greatmingmanage.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.manage.greatming.greatmingmanage.Utils.LogRead;




@Controller
@RequestMapping("/logread")
public class LogReaderController {
    
    @GetMapping
    public String LogReaderHome() {
        return "logread/home";
    }

    @PostMapping("/read")
    public ModelAndView ReadLog(@RequestParam String logData) {
        // System.out.println(logData);
        String oneDay =  LogRead.ReadLog(logData);
        ModelAndView modelAndView = new ModelAndView("logread/readed");
        modelAndView.addObject("oneDay", oneDay);
        return modelAndView;
        // return "redirect:/logread/readed";
    }

    @GetMapping("/counthome")
    public String CountHome() {
        return "logread/counthome";
    }

    @PostMapping("/count")
    public ModelAndView postMethodName(@RequestParam String logReadedData) {
        String CountedData = LogRead.CountData(logReadedData);
        System.out.println(CountedData);
        ModelAndView modelAndView = new ModelAndView("logread/counted");
        modelAndView.addObject("oneMonth", CountedData);
        return modelAndView;
    }
    
    


}
