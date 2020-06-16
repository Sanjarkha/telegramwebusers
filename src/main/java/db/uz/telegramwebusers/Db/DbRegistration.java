package db.uz.telegramwebusers.Db;

import db.uz.telegramwebusers.Service.Servicebot;
import db.uz.telegramwebusers.Settings;

import java.sql.*;

public class DbRegistration {
    Servicebot srbot=new Servicebot();
    Settings settings=new Settings();
    private String URL=settings.jdbcurl;
    private String USERNAME=settings.jdbclogin;
    private String PASSWORD=settings.jdbcpassword;
    public Connection con;
    public Statement stm;
    public ResultSet rs;
    public String resbranch=null;
    public String resdepartmen=null;
    public void converidname(Long branch){
        try{
            con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            stm=con.createStatement();
            rs = stm.executeQuery("SELECT * FROM branch WHERE id=" + branch + ";");
            while (rs.next()) {
                resbranch = rs.getString("name");
            }
            System.out.println("Database convert long to name");
        }catch (SQLException e){
            System.out.println("failed converter id name");
        }finally {
            try {
                con.close();
                System.out.println("converter id name is closed");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void convertdeptoname(Long departmen){
        try{
            con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            stm=con.createStatement();
            rs = stm.executeQuery("SELECT * FROM departmen WHERE id=" + departmen + ";");
            while (rs.next()) {
                resdepartmen = rs.getString("name");
            }
            System.out.println("Database convert long to name");
        }catch (SQLException e){
            System.out.println("failed converter id name");
        }finally {
            try {
                con.close();
                System.out.println("converter id name is closed");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    public void userstatus(int id){

        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            stm = con.createStatement();
            stm.execute("UPDATE d_users set status=1 where id="+id+";");
            System.out.println("Telegram User is confirm ");

        }catch (SQLException e){
            System.out.println("Eror userstatus web confirm");
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
   public void usergetchatid(int id){
      long chat_id=0;
       try {
           con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
           stm = con.createStatement();
           rs=stm.executeQuery("select * from d_users where id="+id+";");
           while (rs.next()){
               chat_id=rs.getLong("t_userid");
               System.out.println("chat id:"+chat_id);
               srbot.post_activestatus(chat_id);
           }

       }catch (SQLException e){
           System.out.println("Eror userstatus web confirm");
       }finally {
           try {
               con.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }

   }


}
