package controllers;

import data.VirtualStorage;
import data.model.PlayerInfo;
import data.model.User;
import data.model.states.PlayerState;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @RequestMapping(value = "/hit", produces = "application/json")
    @ResponseBody
    public String hit(@RequestParam("roomName") String roomName,
                                     HttpSession session){


        User user = (User) session.getAttribute("user");
        PlayerState state = VirtualStorage.getPlayerState(roomName, user.getNickname());
        PlayerInfo player = VirtualStorage.getPlaterInfo(roomName, user.getNickname());

        switch (state){
            case WAITING:
                return "{\"message\":\"Dont be a cheater\"}";
            case FINISHED:
                //VirtualStorage.nextTurn(roomName);
                return "{\"message\":\"ok\"}";
            default:
                break;
        }

        if (VirtualStorage.hitSuccess(roomName, user.getNickname())){

            int handValue = VirtualStorage.getHandValue(roomName, user.getNickname());

            if (handValue >= 21){

                player.setState(PlayerState.FINISHED);
                VirtualStorage.nextTurn(roomName);
            }
            return "{\"message\":\"ok\"}";
        } else {
            return "{\"message\":\"Dont be a cheater\"}";
        }

    }

    @RequestMapping(value = "/stand", produces = "application/json")
    @ResponseBody
    public String stand(@RequestParam("roomName") String roomName,
                      HttpSession session){


        User user = (User) session.getAttribute("user");
        PlayerState state = VirtualStorage.getPlayerState(roomName, user.getNickname());
        PlayerInfo player = VirtualStorage.getPlaterInfo(roomName, user.getNickname());

        switch (state){
            case WAITING:
                return "{\"message\":\"Dont be a cheater\"}";
            case FINISHED:
                return "{\"message\":\"ok\"}";
            case ON_TURN:
                player.setState(PlayerState.FINISHED);
                VirtualStorage.nextTurn(roomName);
                return "{\"message\":\"ok\"}";
            default:
                break;
        }

        return "{\"message\":\"ok\"}";
    }

    @RequestMapping(value = "/getGameInfo", produces = "application/json")
    @ResponseBody
    public String getGameInfo(@RequestParam("roomName") String roomName){


        //Check game status

        return VirtualStorage.gameToJSON(roomName);
    }

    public static void checkGameState(){
        
    }
}
