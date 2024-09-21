package com.manage.greatming.greatmingmanage.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.manage.Exception.UserException;
import com.manage.greatming.greatmingmanage.Service.UserService;

@Controller
@RequestMapping("/balance")
public class BalanceController {


    @Autowired
    private UserService userService;

    @GetMapping
    public String balanceHome(Model model) {
        return "balance/home";
    }

    @PostMapping("/count")
    public String balanceCount(@RequestParam("userData") String userData) {
        for (String stringline : userData.split("\\n")) {
            String[] strl = stringline.split(" ");
            if (strl.length == 3) {
                String username = strl[0];
                Integer attendance = Integer.parseInt(strl[1]);
                Integer kills = Integer.parseInt(strl[2].strip());
                // System.out.println(username);
                // System.out.println(attendance);
                // System.out.println(kills);
                try {
                    userService.AKBCount(username, attendance, kills);
                } catch (UserException e) {
                    System.out.println(e.getMessage());
                    // e.printStackTrace();
                }
            }

        }

        return "redirect:/balance";
    }

}
