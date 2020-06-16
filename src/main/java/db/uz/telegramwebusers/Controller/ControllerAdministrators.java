package db.uz.telegramwebusers.Controller;

import db.uz.telegramwebusers.Dao.DaoAdministrators;
import db.uz.telegramwebusers.Dao.DaoUsers;
import db.uz.telegramwebusers.Model.ModelAdministrators;
import db.uz.telegramwebusers.Model.ModelUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControllerAdministrators {

    @Autowired
    private DaoAdministrators daoAdministrators;

    @RequestMapping(value = "/administrators", method = RequestMethod.GET)

    private String getUserList(Model model){

        List<ModelAdministrators> addList=daoAdministrators.findAll();

        model.addAttribute("addList", addList);

        return  "administrators";
    }

    @PostMapping("/add_admin")
    public String addAdmin(Model model, HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("addadmin"));
        List<ModelAdministrators> addList=daoAdministrators.findAll();
        System.out.println(id);
        model.addAttribute("addList", addList);
        return  "administrators";
    }

}
