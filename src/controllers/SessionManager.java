package controllers;

import data.VirtualStorage;
import data.error.ErrorCode;
import data.error.ErrorMessage;
import data.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("user")
public class SessionManager {

    public static final int TOKEN_LENGTH = 32;

    @ModelAttribute("user")
    public User setUpUserForm() {
        return new User(null, null, null);
    }


    @RequestMapping("/")
    public ModelAndView getLogin(){
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    @RequestMapping("/signIn")
    public ModelAndView signIn(@ModelAttribute("user") User user,
                               @RequestParam("nickname") String nickname){
        ModelAndView mav;

        if ( isNotUsed (nickname) ) {
            user.setNickname(nickname);
            user.setToken(generateToken());

            VirtualStorage.users.put(nickname, user);   // Note: the reference may cause problems
            mav = new ModelAndView("lobby");

        } else {
            mav = new ModelAndView("login");
            ErrorCode code = ErrorCode.DUPLICATED_NICK;
            mav.addObject("errorCode", code);
            mav.addObject("errorMessage", ErrorMessage.getErrorMessage(code));
        }


        return mav;
    }

    public boolean isNotUsed(String nickname){
        return !VirtualStorage.users.containsKey(nickname);
    }

    public static String generateToken(){
        String token = "";

        for (int i = 0; i <TOKEN_LENGTH ; i++) {
            int randomInt = (int) (Math.random() * TOKEN_LENGTH)-1;
            token += (char) randomInt;
        }

        return token;
    }
}
