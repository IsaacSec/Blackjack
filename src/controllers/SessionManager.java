package controllers;

import data.database.services.definition.AccountService;
import data.database.services.definition.Accountable;
import org.springframework.beans.factory.annotation.Autowired;
import session.SessionBeanInterface;
import data.VirtualStorage;
import data.error.ErrorCode;
import data.error.ErrorMessage;
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

    SessionBeanInterface bean;

    @Autowired
    AccountService accountService;

    public SessionBeanInterface getBean() {
        return bean;
    }

    public void setBean(SessionBeanInterface bean) {
        this.bean = bean;
    }


    @RequestMapping("/")
    public ModelAndView getLogin(){
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    @RequestMapping("/logIn")
    public ModelAndView signIn( HttpSession session,
                                @RequestParam("nickname") String nickname,
                                @RequestParam("password") String password){
        ModelAndView mav;

        data.database.model.User user = accountService.getUserByNicknameAndPass(nickname, password);

        if (user != null) {
            mav = new ModelAndView("lobby");

            User virtualUser = new User(
                    nickname,
                    generateToken(),
                    null
            );

            session.setAttribute("user", virtualUser);
            VirtualStorage.addNewUser(virtualUser);

        } else {

            mav = new ModelAndView("login");
            ErrorCode code = ErrorCode.INVALID_NICK;
            mav.addObject("errorCode", code);
            mav.addObject("errorMessage", ErrorMessage.getErrorMessage(code));
        }
        /*
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
        */

        return mav;
    }

    @RequestMapping("/signIn")
    public ModelAndView logIn(  @RequestParam("nickname") String nickname,
                                @RequestParam("password") String password,
                                @RequestParam("name") String name){

        ModelAndView mav = new ModelAndView("login");

        //register in database
        data.database.model.User user = new data.database.model.User();
        user.setUsername(nickname);
        user.setPassword(password);
        user.setName(name);
        accountService.registerUser(user);

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
