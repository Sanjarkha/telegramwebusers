package db.uz.telegramwebusers.Controller;

import db.uz.telegramwebusers.Model.ModelLoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
//@RequestMapping(name = "/login")
public class ControlleLogin {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginForm(){

return "login";
    }
    @RequestMapping(value ="/login",method = RequestMethod.POST)
    public String login (@ModelAttribute(name = "loginForm") ModelLoginForm loginForm, Model model){
        String username=loginForm.getUsername();
        String password=loginForm.getPassword();

        if ("admin".equals(username)&& "admin".equals(password)){
            return "registration";
        }
        model.addAttribute("invalidCredentials",true);
        return "login";
    }
}
