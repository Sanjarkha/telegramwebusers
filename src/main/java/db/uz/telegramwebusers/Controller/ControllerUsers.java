package db.uz.telegramwebusers.Controller;

import db.uz.telegramwebusers.Dao.DaoRegistration;
import db.uz.telegramwebusers.Dao.DaoUsers;
import db.uz.telegramwebusers.Model.ModelRegistration;
import db.uz.telegramwebusers.Model.ModelUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ControllerUsers {
    @Autowired
    private DaoUsers daoUsers;

    @RequestMapping(value = "/users", method = RequestMethod.GET)

    private String getUserList(Model model){

        List<ModelUsers> userList=daoUsers.findAll();

        model.addAttribute("userList", userList);

        return  "users";
    }
}
