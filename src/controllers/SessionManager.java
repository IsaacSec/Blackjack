package controllers;

import data.VirtualStorage;
import data.error.ErrorCode;
import data.error.ErrorMessage;
import data.model.Card;
import data.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("user")
public class SessionManager {

    public static final int TOKEN_LENGTH = 32;


    @RequestMapping("/")
    public ModelAndView getLogin(){
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    @RequestMapping("/signIn")
    public ModelAndView signIn(HttpSession session,
                               @RequestParam("nickname") String nickname){
        ModelAndView mav;

        if ( isNotUsed (nickname) ) {
            mav = new ModelAndView("lobby");

            User user = new User(
                    nickname,
                    generateToken(),
                    null
                    );

            session.setAttribute("user", user);
            VirtualStorage.addNewUser(user);  // Note: the reference may cause problems

        } else {
            mav = new ModelAndView("login");
            ErrorCode code = ErrorCode.DUPLICATED_NICK;
            mav.addObject("errorCode", code);
            mav.addObject("errorMessage", ErrorMessage.getErrorMessage(code));
        }


        return mav;
    }

    @RequestMapping("/signOut")
    public ModelAndView exitFromLobby(HttpSession session){

        User user = (User) session.getAttribute("user");
        VirtualStorage.removeUser(user.getNickname());

        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    public boolean isNotUsed(String nickname){
        return !VirtualStorage.isInUsers(nickname);
    }

    public static String generateToken(){
        String symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String token = "";

        for (int i = 0; i <TOKEN_LENGTH ; i++) {
            int randomInt = (int) (Math.random() * symbols.length());
            token += symbols.charAt(randomInt);
        }

        return token;
    }
}
