package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SessionManager {

    @RequestMapping("/")
    public ModelAndView mainMethod(){
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }
}
