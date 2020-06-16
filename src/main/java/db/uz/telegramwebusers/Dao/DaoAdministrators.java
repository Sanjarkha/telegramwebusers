package db.uz.telegramwebusers.Dao;

import db.uz.telegramwebusers.Db.DbRegistration;
import db.uz.telegramwebusers.Model.ModelAdministrators;
import db.uz.telegramwebusers.Model.ModelUsers;
import db.uz.telegramwebusers.Settings;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DaoAdministrators {

    Settings settings=new Settings();
    private String URL=settings.jdbcurl;
    private String USERNAME=settings.jdbclogin;
    private String PASSWORD=settings.jdbcpassword;
    public Connection con;
    public Statement stm;
    public ResultSet rs;
    DbRegistration dbreg=new DbRegistration();

    public List<ModelAdministrators> findAll() {
        List<ModelAdministrators> addList = new ArrayList<>();
        /** Test connection create by Sanjar Kha date: 24:02:2020**/
        try{
            con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            stm=con.createStatement();
            rs=stm.executeQuery("SELECT * FROM d_users WHERE status=true ");
            while(rs.next()){
//                System.out.println(rs.getInt("id"));
                String id= String.valueOf(rs.getInt("id"));
                String date= String.valueOf(rs.getDate("created_date"));
                long branch=rs.getLong("branch");
                dbreg.converidname(branch);
                long departmen=rs.getLong("departmant");
                dbreg.convertdeptoname(departmen);
                String resbranch=dbreg.resbranch;
                String resdepartmen=dbreg.resdepartmen;
                addList.add(new ModelAdministrators(id, rs.getString("phonenumber"), rs.getString("firstname"), rs.getString("lastname"), resbranch, resdepartmen,date ));
            }
            System.out.println("Start userList");
        }catch (SQLException e){
            System.out.println("failed userlist");
        }finally {
            try {
                con.close();
                System.out.println("close connection userlist");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return  addList;
    }
}