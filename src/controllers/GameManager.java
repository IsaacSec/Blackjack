package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class GameManager {

    @RequestMapping("/join")
    public ModelAndView enterToLobby(@RequestParam("roomName") String roomName,
                                     HttpSession session){
        ModelAndView mav = new ModelAndView("game");
        return mav;
    }

}
