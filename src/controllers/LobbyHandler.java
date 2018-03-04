package controllers;

import data.VirtualStorage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LobbyHandler {

    @RequestMapping("/lobby")
    public ModelAndView enterToLobby(){
        ModelAndView mav = new ModelAndView("lobby");
        return mav;
    }

    @RequestMapping(value = "/createRoom", produces = "application/json")
    @ResponseBody
    public String createRoom(@RequestParam("roomName") String roomName){
        VirtualStorage.addNewRoom(roomName);
        return "";
    }

    @RequestMapping(value = "/getRooms", produces = "application/json")
    @ResponseBody
    public String createRoom(){

        String json = VirtualStorage.allRoomsToJSON();

        return json;
    }
}
