package db.uz.telegramwebusers.Controller;


import db.uz.telegramwebusers.Dao.DaoRegistration;
import db.uz.telegramwebusers.Db.DbRegistration;
import db.uz.telegramwebusers.Model.ModelRegistration;
import db.uz.telegramwebusers.Service.Servicebot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControllerRegistration {
    DbRegistration db=new DbRegistration();
    @Autowired
    private DaoRegistration daoRegistration;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)

    private String getRegistartionList(Model model){

        List<ModelRegistration> regList=daoRegistration.findAll();

        model.addAttribute("regList", regList);

        return  "registration";
    }

    @PostMapping( "/doneus")
    public String doneus (Model model, HttpServletRequest request){
        request.getParameter("donebut");
        int userid=Integer.parseInt(request.getParameter("donebut"));
        System.out.println(userid);
        db.userstatus(userid);
        db.usergetchatid(userid);
        System.out.println(userid);
        List<ModelRegistration> regList=daoRegistration.findAll();

        model.addAttribute("regList", regList);
        return "registration";

    }
}
