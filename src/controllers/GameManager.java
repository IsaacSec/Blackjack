package controllers;

import data.VirtualStorage;
import data.model.Room;
import data.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class GameManager {

    @RequestMapping("/join")
    public ModelAndView enterToRoom(@RequestParam("roomName") String roomName,
                                     HttpSession session){

        User user = (User) session.getAttribute("user");
        VirtualStorage.addPlayerToRoom(roomName, user.getNickname());

        //Check game status

        ModelAndView mav = new ModelAndView("game");
        return mav;
    }

    @RequestMapping("/exitRoom")
    public ModelAndView exitFromRoom(@RequestParam("roomName") String roomName,
                                     HttpSession session){

        User user = (User) session.getAttribute("user");
        user.setRoomName(null);

        VirtualStorage.removePlayerFromRoom(roomName, user.getNickname());

        //Check game status

        ModelAndView mav = new ModelAndView("lobby");
        return mav;
    }

}
